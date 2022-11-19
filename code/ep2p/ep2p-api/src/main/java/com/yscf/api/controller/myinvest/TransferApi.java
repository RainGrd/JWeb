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
import java.util.ArrayList;
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.myinvest.BizReceiptPlanVo;
import com.yscf.api.vo.myinvest.BizReceiptTransferApiVo;
import com.yscf.api.vo.myinvest.BizReceiptTransferCenterVo;
import com.yscf.api.vo.myinvest.TransferApiArgsVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.BizReceiptTransferCenterModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferFrontModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferModel;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.impl.BizReceiptPlanServiceImpl;
import com.yscf.core.service.business.impl.BizReceiptTransferServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.util.RepalceUtil;

/**
 * 
 * Description：<br>
 * 债权转让API控制
 * 
 * @author JunJie.Liu
 * @date 2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/transferApi")
public class TransferApi extends EscfBaseApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public TransferApi(BizReceiptTransferServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizReceiptTransfer.class;
	}
	
	@Resource
	private CusTomerServiceImpl customerService;
	
	@Resource
	private BizReceiptPlanServiceImpl bizReceiptPlanService;
	
	@Resource
	private CustomerAccountServiceImpl customerAccountService;

	/**
	 * 
	 * Description：<br>
	 * 债权转让列表
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param pageNum
	 *            页码：1
	 * @param pageSize
	 *            条数：10
	 * @param type
	 *            年利率：1,转让价格：2  (综合排序:其他或null)
	 * @param desc
	 *            排序：false (false:代表升序 true:代表降序) 默认为false，综合排序注意值。
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String list(
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			IBizReceiptTransferService service = (BizReceiptTransferServiceImpl) getService();
			
			Integer pageNum = args.getPageNum() == null ? 1 : args.getPageNum();
			Integer pageSize = args.getPageSize() == null ? 10 : args.getPageSize();
			pageNum = (pageNum - 1) * pageSize;
			//获取债权列表
			List<BizReceiptTransferFrontModel> list = service.findTransferList(
					args.getType(), args.isDesc(),  pageNum, pageSize);
			
			Integer pageCount = service.findTransferListByApiCount();

			map.put("list", list);
			map.put("pageCount", pageCount);

			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * Description：<br> 
	 * 债权转让购买详情
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	@ResponseBody
	public String info(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		try {
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			if(!StringUtils.hasLength(args.getTransferId())){
				throw new NullArgumentException("债权转让id为null");
			}
			if(StringUtils.hasLength(xtoken)){
				CustomerVo tcustomer = getCustomer(xtoken);
				if(tcustomer!=null){
					CustomerAccount ca = customerAccountService.getByCusterId(tcustomer.getPid());
					map.put("availableAmount", ca==null ? BigDecimal.ZERO : ca.getAvailableAmount());
				}
			}
			// 查询转让详情
			IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
			BizReceiptTransferModel vo = bizReceiptTransferService.getByPid(args.getTransferId());
			if(null != vo){
				vo.setCreateUserName(RepalceUtil.replaceCustomerNameToStar(vo.getCreateUserName()));
			}
			
			map.put("transferModel", vo!=null?ConvertObjectUtil.convertObject(vo, BizReceiptTransferApiVo.class):null );
			// 失效时间
			if(vo.getExpireTime()!=null){
				map.put("expireDate",DateUtil.format(vo.getExpireTime()).getTime());
			}else{
				map.put("expireDate",System.currentTimeMillis());
			}
			// 系统当前时间
			map.put("sysDate",System.currentTimeMillis());
			map.put("qixiDate",DateUtil.format(vo.getInterestData(),"yyyy-MM-dd"));
			
			// 获取对应的收款计划
			BizReceiptTransfer bizReceiptTransfer = bizReceiptTransferService.getBizReceiptTransferById(args.getTransferId());
			List<BizReceiptPlan> list = new ArrayList<BizReceiptPlan>();
			if(bizReceiptTransfer!=null){
					
				if(StringUtils.hasLength(bizReceiptTransfer.getParentTransferId())){
					// 否则是债权再次转让
					list = bizReceiptPlanService.findListByTransfer(bizReceiptTransfer.getParentTransferId(),Constant.INVEST_SRC_TYPE_2);
				}else{
					// 如果父id为null，表示原标转让
					list = bizReceiptPlanService.findListByTransfer(bizReceiptTransfer.getPid(),Constant.INVEST_SRC_TYPE_1);
				}
				
			}
			// 获取应收总本金
			BigDecimal benjin = BigDecimal.ZERO;
			// 获取应收总利息
			BigDecimal lixi = BigDecimal.ZERO;
			// 获取应收总本息
			BigDecimal benxi = BigDecimal.ZERO;
			for(BizReceiptPlan rp : list){
				BigDecimal capital = rp.getCapital() == null ? BigDecimal.ZERO : rp.getCapital();
				BigDecimal interest = rp.getInterest() == null ? BigDecimal.ZERO : rp.getInterest();
				benjin = benjin.add(capital);
				lixi = lixi.add(interest);
			}
			benxi = benjin.add(lixi);
			
			map.put("receiptPlanList", ConvertObjectUtil.batchConvertObject(list, BizReceiptPlanVo.class));
			map.put("benjin", benjin);
			map.put("lixi", lixi);
			map.put("benxi", benxi);
			map.put("qixian", list.size());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);

		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 购买
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @param tradePwd
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	@ResponseBody
	public String buy(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerVo tcustomer = getCustomer(xtoken);
			String userid = tcustomer.getPid();
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(!StringUtils.hasLength(args.getTransferId())){
				throw new NullArgumentException("债权转让id为null");
			}
			if(!StringUtils.hasLength(userid)){
				throw new NullArgumentException("客户id为null");
			}
			IBizReceiptTransferService service = (BizReceiptTransferServiceImpl) getService();
			// 购买
			service.buyTransfer(args.getTransferId(), userid,Constant.INVEST_TYPE_2);
			
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("购买成功");
			
		}catch(RuntimeException e){
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}catch(FrameworkException e){
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}catch (Exception e) {
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"操作失败",false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 个人中心列表
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @param tradePwd
	 * @param userId
	 * 1.持有用 2.转让中 3.已转让
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/centerList", method = RequestMethod.POST)
	@ResponseBody
	public String transferList(HttpServletRequest request, HttpServletResponse response)
					throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null ){
				throw new NullArgumentException("参数对象为null");
			}
			if(!StringUtils.hasLength(args.getTransferType())){
				// 默认　持有中
				args.setTransferType("1");
			}
			if(!StringUtils.hasLength(userid)){
				throw new NullArgumentException("用户id为null");
			}
			
			Integer pageNum = args.getPageNum() == null ? 0 : args.getPageNum() - 1;
			Integer pageSize = args.getPageSize() == null ? 10 : args.getPageSize();
			IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
			
			List<BizReceiptTransferCenterModel> vos = bizReceiptTransferService
					.findTrasferCenterVos(null, userid, pageNum,
							pageSize, args.getTransferType());
			
			@SuppressWarnings("unchecked")
			List<BizReceiptTransferCenterVo> list = (List<BizReceiptTransferCenterVo>) ConvertObjectUtil.batchConvertObject(vos, BizReceiptTransferCenterVo.class);
			
			Integer total = bizReceiptTransferService
					.findTrasferCenterVosCount(null,userid, args.getTransferType());
			
			total = (total == null ? 0 : total);
			
			map.put("list", list);
			map.put("pageCount", total);

			jsonObject.setResult(map);
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
		}catch (Exception e) {
			
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
	 * 持有中债权详情
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @param tradePwd
	 * @param userId
	 * 
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/holdInfo", method = RequestMethod.POST)
	@ResponseBody
	public String holdInfo(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerVo tcustomer = getCustomer(xtoken);
			String userid = tcustomer.getPid();
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null ){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getTransferId())){
				throw new NullArgumentException("债权id为null");
			}
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}

			IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
			BizReceiptTransferModel btc = bizReceiptTransferService.getByPid(args.getTransferId());
			
			map.put("model", btc);
			
			jsonObject.setResult(map);
			jsonObject.setStatus(true);
			jsonObject.setCode("200");
			jsonObject.setMessage("操作成功");
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
