/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体验标API控制
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月30日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.myinvest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.myinvest.BizBorrowVo;
import com.yscf.api.vo.myinvest.ExperienceBorrowApiVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.service.activity.impl.ActExpActDetailServiceImpl;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.core.service.financial.impl.BizAccountExperienceServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.util.MiscUtil;

/**
 * Description：<br> 
 * 体验标API控制
 * @author  Jie.Zou
 * @date    2015年12月30日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/experienceBorrowApi")
public class ExperienceBorrowApi extends EscfBaseApi {
	
	@Resource
	private CusTomerServiceImpl customerService;
	
	@Resource
	private BizAccountExperienceServiceImpl bizAccountExperienceService;
	
	@Resource
	private BizBorrowServiceImpl bizBorrowService;
	
	@Resource
	private ActExpActDetailServiceImpl actExpActDetailService;
	
	@Resource
	private SysParamsServiceImpl sysParamsServiceImpl;
	
	@Resource
	private BizFinanceProductsServiceImpl bizFinanceProductsServiceImpl;
	
	@Autowired
	public ExperienceBorrowApi(BizBorrowServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取最新的体验标
	 * @author  Jie.Zou
	 * @date    2016年1月5日
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
			BizBorrow model = service.getLastBorrowByBorrowType(Constant.BORROW_TYPE_5);
			//获取体验标对象
			if(model==null){
				return returnResult( jsonObject, "查询成功，没有体验标");
			}
			BizFinanceProducts bfp = bizFinanceProductsServiceImpl
					.getBizFinanceProductsByBorrowId(model.getPid());
			map.put("yearRate", model.getBorrowRate());
			map.put("deadline", model.getBorDeadline());
			map.put("pid", model.getPid());
			map.put("borStatus", model.getBorStatus());
			map.put("borProgress", model.getBorrowProgress());
			map.put("desc", bfp.getJoinCondition());
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
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
	 * 体验标详情
	 * @author  Jie.Zou
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public String info(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			ExperienceBorrowApiVo eBorrowVo = (ExperienceBorrowApiVo)ApiUtil.convertObjectByBody(request, ExperienceBorrowApiVo.class);
			if(eBorrowVo == null){
				return returnResult(jsonObject, "查询失败，参数对象为null");
			}
			if(!StringUtils.hasText(eBorrowVo.getBorrowId())){
				return returnResult(jsonObject,  "查询失败，体验标id为null");
			}
			
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			BizBorrow borrow = service.getBizBorrowById(eBorrowVo.getBorrowId());
			BizBorrowVo bVo = ConvertObjectUtil.convertObject(borrow, BizBorrowVo.class);
			
			map.put("borrow", bVo);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
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
	 * 使用体验金接口
	 * @author  Jie.Zou
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param userId 用户Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/useExperience", method = RequestMethod.POST)
	@ResponseBody
	public String useExperience(
			HttpServletRequest request, HttpServletResponse response){
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			ExperienceBorrowApiVo eBorrowVo = (ExperienceBorrowApiVo)ApiUtil.convertObjectByBody(request, ExperienceBorrowApiVo.class);
			if(eBorrowVo == null){
				return returnResult( jsonObject, "查询失败，参数对象为null");
			}
			eBorrowVo.setUserId(tcustomer.getPid());
			if(!StringUtils.hasText(eBorrowVo.getUserId())){
				return returnResult(jsonObject, "查询失败，用户id为null");
			}
			if(!StringUtils.hasText(eBorrowVo.getBorrowId())){
				return returnResult(jsonObject,  "查询失败，体验标Id为空");
			}
			List<ActExpActDetail> experiences = actExpActDetailService.selectCustTomerExperienceGold(eBorrowVo.getUserId());
			for (ActExpActDetail actExpActDetail : experiences) {
				actExpActDetail.setExpDays(MiscUtil.daysBetween(DateUtil.format(DateUtil.getSystemDate()),actExpActDetail.getExpireTime()));
			}
			map.put("borrowId",eBorrowVo.getBorrowId());
			map.put("experiences", experiences);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
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
	 * 封装返回结果
	 * @author  Jie.Zou
	 * @date    2016年1月22日
	 * @version v1.0.0
	 * @param jsonObject
	 * @param resultMsg
	 * @return
	 */
	private String returnResult(JsonObject jsonObject,String resultMsg){
		processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,resultMsg,false);
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 投资体验标
	 * @author  Jie.Zou
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param userId 用户Id
	 * @param tradePwd 交易密码
	 * @param epces 体验金卷数组
	 * @param borrowId 借款Id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/investExperience", method = RequestMethod.POST)
	@ResponseBody
	public String investExperience(
			HttpServletRequest request, HttpServletResponse response){
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			ExperienceBorrowApiVo eBorrowVo = (ExperienceBorrowApiVo)ApiUtil.convertObjectByBody(request, ExperienceBorrowApiVo.class);
			if(eBorrowVo == null){
				return returnResult(jsonObject, "操作失败，参数对象为null");
			} 
			eBorrowVo.setUserId(tcustomer.getPid());
			if(null==eBorrowVo.getEpces()||eBorrowVo.getEpces().length==0){
				return returnResult(jsonObject, "操作失败，epces体验金卷不能为空");
			}
			if(!StringUtils.hasText(eBorrowVo.getUserId())){
				return returnResult(jsonObject, "操作失败，userId不能为空");
			}
			if(!StringUtils.hasText(eBorrowVo.getBorrowId())){
				return returnResult(jsonObject, "操作失败，体验标Id为空");
			}
			List<ActExpActDetail> aeads = actExpActDetailService.selectExperienceGoldByPidArr(eBorrowVo.getEpces());
			BigDecimal amount = BigDecimal.ZERO;
			for (ActExpActDetail aead : aeads) {
				amount = amount.add(aead.getExpAmount());
			}
			BizBorrow bizBorrow = bizBorrowService.getBizBorrowById(eBorrowVo.getBorrowId());
			if(bizBorrow==null){
				map.put("investExperienceResult", false);
				return returnResult(jsonObject, "操作失败，该体验标不存在");
			}
			bizBorrowService.investExperience(bizBorrow, amount, eBorrowVo.getUserId(), bizBorrow.getRepaymentType(), eBorrowVo.getEpces());
			//预期利息
			BigDecimal interest = MiscUtil.calculationInterestByAmount(amount, bizBorrow);
			map.put("amount", amount);
			map.put("borDeadline", bizBorrow.getBorDeadline());
			map.put("borrowRate", bizBorrow.getBorrowRate());
			map.put("borrowCode", bizBorrow.getBorrowCode());
			map.put("interest", interest);
			map.put("investExperienceResult", true);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


