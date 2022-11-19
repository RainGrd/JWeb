/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.myinvest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.myinvest.BizBorrowVo;
import com.yscf.api.vo.myinvest.NewStandardArgsVo;
import com.yscf.api.vo.myinvest.NewStandardRecordVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;

/**
 * 
 * Description：<br>
 * 新手标API控制
 * 
 * @author JunJie.Liu
 * @date 2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/newStandardApi")
public class NewStandardApi  extends EscfBaseApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public NewStandardApi(BizBorrowServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}
	
	@Resource
	private BizFinanceProductsServiceImpl bizFinanceProductsServiceImpl;
	
	@Resource
	private BizBorrowDetailServiceImpl bizBorrowDetailService;
	
	@Resource
	private CustomerAccountServiceImpl customerAccountService;
	
	@Resource
	private SysParamsServiceImpl sysParamsService;
	
	@Resource
	private SysVipinfoServiceImpl sysVipinfoService;
	
	@Resource
	private CusTomerServiceImpl customerService;
	
	/**
	 * 
	 * Description：<br> 
	 * 获取最新的新手标
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/unique")
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			
			BizBorrow model = service.getLastBorrowByBorrowType(Constant.BORROW_TYPE_4);
			
			//获取新手标对象
			if(model==null){
				throw new RuntimeException("新手标对象为null");
			}
			map.put("des", model.getBorrowDesc());
			map.put("yearRate", model.getBorrowRate());
			map.put("deadline", model.getBorDeadline());
			map.put("pid", model.getPid());
			map.put("borStatus", model.getBorStatus());
			map.put("borProgress", model.getBorrowProgress() == null ? BigDecimal.ZERO : model.getBorrowProgress());
			
			jsonObject.setResult(map);
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		
		return ApiUtil.getEncryptStr(jsonObject);
		
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 新手标详情
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public String info(HttpServletRequest request, HttpServletResponse response)
					throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			NewStandardArgsVo args = (NewStandardArgsVo) ApiUtil.convertObjectByBody(request, NewStandardArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("新手标id为null");
			}
			
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			
			BizBorrow borrow = service.getBizBorrowById(args.getBorrowId());
			BizFinanceProducts bfp = bizFinanceProductsServiceImpl
					.getBizFinanceProductsByBorrowId(args.getBorrowId());
			
			//获取新手标对象，以及理财产品介绍
			BizBorrowVo bVo = ConvertObjectUtil.convertObject(borrow, BizBorrowVo.class);
			
			bVo.setJoinCondition(bfp.getJoinCondition());
			map.put("borrow", bVo);
			
			jsonObject.setResult(map);
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			
		} catch (Exception e) {
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 新手标投资记录
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param borrowId
	 * 			新手标id
	 * @param pageNum
	 * 			页码
	 * @param pageSize
	 * 			条数
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	@ResponseBody
	public String detail(
			HttpServletRequest request, HttpServletResponse response)
					throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			NewStandardArgsVo args = (NewStandardArgsVo) ApiUtil.convertObjectByBody(request, NewStandardArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("新手标id为null");
			}
			
			Integer pageNum = (args.getPageNum() == null ? 1 : args.getPageNum());
			Integer pageSize = (args.getPageSize() == null ? 10 : args.getPageSize());
			pageNum = (pageNum - 1) * pageSize;
			
			// 获取投资记录
			List<BizBorrowDetail> bizBorrowDetailList = bizBorrowDetailService.selectBizBorrowDetailByBorrowId(args.getBorrowId(),pageNum,pageSize);
			Integer count = bizBorrowDetailService.selectBizBorrowDetailByBorrowIdCount(args.getBorrowId());
			List<NewStandardRecordVo> list = (List<NewStandardRecordVo>) ConvertObjectUtil.batchConvertObject(bizBorrowDetailList, NewStandardRecordVo.class);
			map.put("bizBorrowDetailList", list);
			map.put("total", count);
			
			jsonObject.setResult(map);
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			
		} catch (Exception e) {
			
			jsonObject.setCode("900");
			jsonObject.setMessage("操作失败");
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * Description：<br> 
	 * 立即投资确认页面
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * 			客户id
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/investInfo", method = RequestMethod.POST)
	@ResponseBody
	public String infoOk(HttpServletRequest request, HttpServletResponse response)
					throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerVo tcustomer = getCustomer(xtoken);
			
			String userid = tcustomer.getPid();
			if(!StringUtils.hasLength(userid)){
				throw new NullArgumentException("用户id为null");
			}
			NewStandardArgsVo args = (NewStandardArgsVo) ApiUtil.convertObjectByBody(request, NewStandardArgsVo.class);
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("新手标id为null");
			}
			
			CustomerAccount ca = null;
			if(StringUtils.hasLength(userid)){
				// 用户已登陆，查询其余额
				ca = customerAccountService.getByCusterId(userid);
			}
			CusTomer cusTomer = customerService.selectByPrimaryKey(userid);
			// 获取用户现在的管理费率
			SysVipinfo svip = sysVipinfoService.getByLevel(cusTomer.getVipLevel());
			BigDecimal manageFee = new BigDecimal(0.1);
			if(null != svip){
				manageFee = svip.getDiscount();
			}
			
			//获取新手标对象，以及理财产品介绍
			map.put("availableAmount", ca.getAvailableAmount());
			map.put("manageFee", manageFee);
			
			// 获取新手标投资范围
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			
			BizBorrow borrow = service.getBizBorrowById(args.getBorrowId());
			// 投资范围
			// 最小金额
			map.put("minAmount", borrow.getStartMoney());
			// 最大金额,如果
			BigDecimal maxAmount = borrow.getEndMoney();
			maxAmount = maxAmount == null ? borrow.getSurplusMoney() : maxAmount;
			if(maxAmount.compareTo(borrow.getSurplusMoney())>0){
				maxAmount = borrow.getSurplusMoney();
			}
			map.put("maxAmount", maxAmount);
			// 年化率
			map.put("yearRate", borrow.getBorrowRate());
			// 期限
			map.put("borDeadline", borrow.getBorDeadline());
			// 是否是整数倍投资
			map.put("isTimesInvest", borrow.getIsTimesInvest());
			
			
			jsonObject.setResult(map);
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			
		} catch (Exception e) {
			
			jsonObject.setCode("900");
			jsonObject.setMessage("操作失败");
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 立即投资
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * 			客户id
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/invest", method = RequestMethod.POST)
	@ResponseBody
	public String invest(HttpServletRequest request, HttpServletResponse response)
					throws HttpRequestException {
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		
		String userid = tcustomer.getPid();
		JsonObject jsonObject = new JsonObject();
		try {
			NewStandardArgsVo args = (NewStandardArgsVo) ApiUtil.convertObjectByBody(request, NewStandardArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("新手标id为null");
			}
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("客户id为null");
			}
			if(!StringUtils.hasLength(args.getAmount())){
			
				throw new NullArgumentException("客户金额为null");
			}
			
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			
			BigDecimal bAmount = new BigDecimal(args.getAmount());
			BizBorrow bb = new BizBorrow();
			bb.setPid(args.getBorrowId());
			// 购买
			service.invest(bb,bAmount,args.getUserId(),Constant.INVEST_TYPE_2);
			
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("操作成功");
		
	
		}catch (RuntimeException e) {
			// 不满足条件
			jsonObject.setCode("900");
			jsonObject.setMessage(e.getMessage());
		}catch (Exception e) {
			
			jsonObject.setCode("900");
			jsonObject.setMessage("操作失败");
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

}
