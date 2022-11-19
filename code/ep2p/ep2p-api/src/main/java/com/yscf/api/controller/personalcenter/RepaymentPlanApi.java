/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 还款Api接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter;

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
import com.yscf.api.vo.persionalcenter.RepaymentPlanInfoApiVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizRepaymentPlanServiceImpl;
import com.yscf.core.service.business.impl.RepaymentServiceImpl;

/**
 * Description：<br> 
 * 还款Api接口
 * @author  Jie.Zou
 * @date    2016年1月25日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/repaymentPlanApi")
public class RepaymentPlanApi extends EscfBaseApi {

	@Resource
	private BizRepaymentPlanServiceImpl bizRepaymentPlanService;
	
	@Resource
	private RepaymentServiceImpl repaymentService;
	
	@Resource
	private BizBorrowServiceImpl bizBorrowService;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public RepaymentPlanApi(BizRepaymentPlanServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizRepaymentPlan.class;
	}
	
	@RequestMapping(value = "/repaymentPlanInfo", method = RequestMethod.POST)
	@ResponseBody
	public String repaymentPlanInfo(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			RepaymentPlanInfoApiVo info = (RepaymentPlanInfoApiVo)ApiUtil.convertObjectByBody(request, RepaymentPlanInfoApiVo.class);
			if(null==info){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败，参数对象为NULL",false);
				return ApiUtil.getEncryptStr(jsonObject);
			}else{
				info.setCustomerId(tcustomer.getPid());
			}
			if(!StringUtils.hasText(info.getCustomerId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"用户未登陆",false);
			}else if(!StringUtils.hasText(info.getRepayId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"还款计划Id为空",false);
			}else{
				BizRepaymentPlanInfoVo repayInfoVo =  bizRepaymentPlanService.getRepaymentInfo(info.getRepayId());
				map.put("repayInfoVo", repayInfoVo);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
			}
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
	 * 已还款-还款详情-已还清
	 * @author  Jie.Zou
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/repaidPlanInfo", method = RequestMethod.POST)
	@ResponseBody
	public String repaidPlanInfo(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			RepaymentPlanInfoApiVo info = (RepaymentPlanInfoApiVo)ApiUtil.convertObjectByBody(request, RepaymentPlanInfoApiVo.class);
			if(null==info){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败，参数对象为NULL",false);
				return ApiUtil.getEncryptStr(jsonObject);
			}else{
				info.setCustomerId(tcustomer.getPid());
			}
			if(!StringUtils.hasText(info.getCustomerId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"用户未登陆",false);
			}else if(!StringUtils.hasText(info.getBorrowId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"借款Id为空",false);
			}else{
				BizRepaymentPlanInfoVo repayInfoVo = repaymentService.getRepaidInfoVoByBid(info.getBorrowId());
				map.put("repayInfoVo", repayInfoVo);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
			}
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
	 * 还款-待还款详情–当期还款
	 * @author  Jie.Zou
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/currentRepayPlanInfo", method = RequestMethod.POST)
	@ResponseBody
	public String currentRepayPlanInfo(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			RepaymentPlanInfoApiVo info = (RepaymentPlanInfoApiVo)ApiUtil.convertObjectByBody(request, RepaymentPlanInfoApiVo.class);
			if(null==info){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败，参数对象为NULL",false);
				return ApiUtil.getEncryptStr(jsonObject);
			}else{
				info.setCustomerId(tcustomer.getPid());
			}
			if(!StringUtils.hasText(info.getCustomerId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"用户未登陆",false);
			}else if(!StringUtils.hasText(info.getBorrowId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"借款Id为空",false);
			}else{
				List<BizRepaymentPlanInfoVo> list = repaymentService.getOverdueRepayments(info.getBorrowId());
				if(list.size()>0){
					map.put("repayments", list);
					map.put("repayInfoVo", repaymentService.getOverdueRepayBorrow(info.getBorrowId()));
					map.put("title", "逾期还款");
				}else{
					BizRepaymentPlan plan = repaymentService.selectRepayByTime(info.getBorrowId());
					if(null!=plan){
						map.put("repayInfoVo", repaymentService.getRepaymentInfoVo(plan.getPid()));
					}
					map.put("title", "当期还款");
				}
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
			}
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
	 * 确认还款
	 * @author  Jie.Zou
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/confirmRepayPlan", method = RequestMethod.POST)
	@ResponseBody
	public String confirmRepayPlan(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("repayResult", false);
		JsonObject jsonObject = new JsonObject();
		try {
			RepaymentPlanInfoApiVo info = (RepaymentPlanInfoApiVo)ApiUtil.convertObjectByBody(request, RepaymentPlanInfoApiVo.class);
			if(null==info){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败，参数对象为NULL",false);
				return ApiUtil.getEncryptStr(jsonObject);
			}else{
				info.setCustomerId(tcustomer.getPid());
			}
			if(!StringUtils.hasText(info.getCustomerId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"用户未登陆",false);
			}else if(!StringUtils.hasText(info.getBorrowId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"借款Id为空",false);
			}else{
				//还款成功信息
				BizRepaymentPlanInfoVo planVo = new BizRepaymentPlanInfoVo();
				//得到借款信息
				BizBorrow bizBorrow = bizBorrowService.getBizBorrowById(info.getBorrowId());
				if(null!=bizBorrow){
					map.put("borrowCode", bizBorrow.getBorrowCode());
				}else{
					map.put("borrowCode", "");
				}
				//得到逾期还款计划
				List<BizRepaymentPlan> list = repaymentService.selectOverdueRepayment(info.getBorrowId());
				boolean lastRepay = false; 
				if(list.size()>0){
					for (BizRepaymentPlan bizRepaymentPlan : list) {
						if(bizRepaymentPlan.getReceiptPalnStatus().equals(Constant.BIZ_REPLAN_STATUS_2)){
							bizRepaymentPlanService.executeRepayment(bizRepaymentPlan,"app手动立即还款");
						}else if(bizRepaymentPlan.getReceiptPalnStatus().equals(Constant.BIZ_REPLAN_STATUS_3)){
							bizRepaymentPlanService.executeRepaymentToSystem(bizRepaymentPlan,"app手动立即还款");
						}
						planVo.setCapital(planVo.getCapital().add(bizRepaymentPlan.getCapital()==null ? BigDecimal.ZERO : bizRepaymentPlan.getCapital()));
						planVo.setInterest(planVo.getInterest().add(bizRepaymentPlan.getInterest()==null ? BigDecimal.ZERO : bizRepaymentPlan.getInterest()));
						planVo.setManagementCost(planVo.getManagementCost().add(bizRepaymentPlan.getManagementCost()==null ? BigDecimal.ZERO : bizRepaymentPlan.getManagementCost()));
						planVo.setLatefee(planVo.getLatefee().add(bizRepaymentPlan.getLatefee()==null ? BigDecimal.ZERO : bizRepaymentPlan.getLatefee()));
						planVo.setRepaidTime(DateUtil.format(DateUtil.getSystemDate()));
						if(bizBorrow.getBorDeadline().equals(bizRepaymentPlan.getPlanindex())){
							lastRepay = true;
						}
					}
					map.put("repayResult", true);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"还款成功",true);
				}else{
					//如果没有逾期还款计划，则得到当期还款计划
					BizRepaymentPlan plan = repaymentService.selectRepayByTime(info.getBorrowId());
					if(null!=plan){
						//如果当期已还款则不处理
						if(plan.getReceiptPalnStatus().equals(Constant.BIZ_REPLAN_STATUS_1)){
							bizRepaymentPlanService.executeRepayment(plan,"app手动立即还款");
							planVo.setCapital(planVo.getCapital().add(plan.getCapital()==null ? BigDecimal.ZERO : plan.getCapital()));
							planVo.setInterest(planVo.getInterest().add(plan.getInterest()==null ? BigDecimal.ZERO : plan.getInterest()));
							planVo.setManagementCost(planVo.getManagementCost().add(plan.getManagementCost()==null ? BigDecimal.ZERO : plan.getManagementCost()));
							planVo.setLatefee(planVo.getLatefee().add(plan.getLatefee()==null ? BigDecimal.ZERO : plan.getLatefee()));
							planVo.setRepaidTime(DateUtil.format(DateUtil.getSystemDate()));
							if(bizBorrow.getBorDeadline().equals(plan.getPlanindex())){
								lastRepay = true;
							}
							map.put("repayResult", true);
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"还款成功",true);
						}else{
							map.put("repayResult", false);
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"还款失败，没有需要还款的还款计划",false);
						}
					}else{
						map.put("repayResult", false);
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"还款失败，没有需要还款的还款计划",false);
					}
				}
				map.put("repayPlan", planVo);
				map.put("lastRepay", lastRepay);
				jsonObject.setResult(map);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


