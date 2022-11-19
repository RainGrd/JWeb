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
 * 2015年12月29日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.myinvest.BizBorrowInfoVo;
import com.yscf.api.vo.myinvest.TransferApiArgsVo;
import com.yscf.api.vo.persionalcenter.ReceiptPlanArgsVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.ptp.financial.BizDetailModel;
import com.yscf.core.model.ptp.investment.InvestmentInfoModel;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizReceiptPlanServiceImpl;
import com.yscf.core.service.business.impl.BizReceiptTransferServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;

/**
 * Description：<br> 
 * 待收API
 * @author  JunJie.Liu
 * @date    2015年12月29日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/receiptPlanApi")
public class ReceiptPlanApi extends EscfBaseApi {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public ReceiptPlanApi(BizReceiptPlanServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		
		return BizReceiptPlan.class;
	}
	
	@Resource
	private CusTomerServiceImpl customerService;

	@Resource
	private SysVipinfoServiceImpl sysVipinfoService;
	
	@Resource
	private BizBorrowServiceImpl bizBorrowService;
	
	@Resource
	private BizBorrowDetailServiceImpl bizBorrowDetailService;
	
	@Resource
	private BizReceiptPlanServiceImpl bizReceiptPlanService;
	
	@Resource
	private SysParamsServiceImpl sysParamsService;
	
	@Resource
	private BizReceiptTransferServiceImpl bizReceiptTransferService;
	
	@Resource
	private UserCenterServiceImpl userCenterService;
	/**
	 * 
	 * Description：<br> 
	 * 待收详情
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
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			ReceiptPlanArgsVo args = (ReceiptPlanArgsVo) ApiUtil.convertObjectByBody(request, ReceiptPlanArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getReceiptPlanId())){
				throw new NullArgumentException("待收详情id为null");
			}
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}
			IBizReceiptPlanService service = (BizReceiptPlanServiceImpl) getService();
			
			BizReceiptPlan br = service.getBizReceiptPlanById(args.getReceiptPlanId());

			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			BizBorrow borrow = bizBorrowService.getBizBorrowById(br.getBorrowId());
			map.put("borrowName", borrow.getBorrowName());
			if (Constant.INVEST_SRC_TYPE_2.equals(br.getPlayType()) && StringUtils.hasLength(br.getTransferId())) {
				// 表示再次债权转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), br.getBorrowId(), Constant.INVEST_SRC_TYPE_2,
						br.getTransferId());
				BizReceiptTransfer brt = bizReceiptTransferService.getBizReceiptTransferById(br.getTransferId());
				map.put("borrowCode", brt.getTransferCode());
				
			} else {
				
				// 表示原标的转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), br.getBorrowId(),Constant.INVEST_SRC_TYPE_1
						,null);
				map.put("borrowCode", borrow.getBorrowCode());

			}
			
			int deadline = brpList.size();
			
			BigDecimal capital = br.getCapital() == null ? BigDecimal.ZERO : br.getCapital();
			BigDecimal netInterest = br.getNetInterest() == null ? BigDecimal.ZERO : br.getNetInterest();
			BigDecimal netHike = br.getNetHike() == null ? BigDecimal.ZERO : br.getNetHike();
			BigDecimal lateFee = br.getLateFee() == null ? BigDecimal.ZERO : br.getLateFee();
			
			BigDecimal totalAmount = capital.add(netInterest).add(netHike).add(lateFee);
		
			map.put("borrowId",br.getBorrowId());
			map.put("transferId",br.getTransferId());
			map.put("playType",br.getPlayType());
			map.put("planIndex",br.getPlanIndex());
			map.put("expireTime",br.getRDate());
			map.put("deadline",deadline);
			map.put("capital",capital);
			map.put("netInterest",netInterest);
			map.put("netHike",netHike);
			map.put("lateFee",lateFee);
			map.put("totalAmount",totalAmount);
			// 已收字段
			if(Integer.parseInt(br.getReceiptStatus())>3){
				map.put("expireTime",DateUtil.format(br.getRepaidTime(),"yyyy-MM-dd"));
				map.put("totalAmount",totalAmount);
			}
		
			
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);

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
	 * 项目详情
	 * @author  JunJie.Liu
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param transferId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/borrowInfo", method = RequestMethod.POST)
	@ResponseBody
	public String borrowInfo(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			ReceiptPlanArgsVo args = (ReceiptPlanArgsVo) ApiUtil.convertObjectByBody(request, ReceiptPlanArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("标的id为null");
			}
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}
			
			BizBorrow borrow = bizBorrowService.getBizBorrowById(args.getBorrowId());
			
			BizBorrowInfoVo vo = ConvertObjectUtil.convertClass(borrow, BizBorrowInfoVo.class);
			
			BizDetailModel bdm = bizBorrowDetailService.getBizDetailModelByUser(args.getBorrowId(),args.getUserId());
			
			BigDecimal surplyAmount = bizReceiptPlanService.getSurpAmount(args.getBorrowId(),args.getUserId());
			BigDecimal alreadyAmount = bizReceiptPlanService.getAlreadyAmount(args.getBorrowId(),args.getUserId());
			
			BigDecimal benXi = bizReceiptPlanService.sumCapitalAndInterest(args.getBorrowId(),args.getUserId());
			
			BigDecimal jiaXi = bizReceiptPlanService.sumNetHike(args.getBorrowId(),args.getUserId());
			
			map.put("borrow", vo);
			map.put("bdm",bdm);
			map.put("surplayAmount",surplyAmount == null ? BigDecimal.ZERO : surplyAmount);
			map.put("alreadyAmount",alreadyAmount == null ? BigDecimal.ZERO : alreadyAmount);
			map.put("benXi",benXi == null ? BigDecimal.ZERO : benXi);
			map.put("jiaXi",jiaXi == null ? BigDecimal.ZERO : jiaXi);
			
			jsonObject.setResult(map);
			
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
			
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
	 * 债权转让详情
	 * @author  JunJie.Liu
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/transferInfo", method = RequestMethod.POST)
	@ResponseBody
	public String transferInfo(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			if (StringUtils.hasLength(args.getTransferId())) {
				// 表示再次债权转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), args.getBorrowId(), Constant.INVEST_SRC_TYPE_2,
						args.getTransferId());
			} else {
				// 表示原标的转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), args.getBorrowId(),Constant.INVEST_SRC_TYPE_1
						,null);

			}

			// 投资本金
			BigDecimal investCapital = BigDecimal.ZERO;
			// 已收本息
			BigDecimal alreadyBenxi = BigDecimal.ZERO;
			// 剩余本金
			BigDecimal surperCapital = BigDecimal.ZERO;
			// 当期全部利息
			BigDecimal currentAllInterest = BigDecimal.ZERO;
			// 当期至今利息
			BigDecimal currentInterest = BigDecimal.ZERO;
			// 剩余期次
			int surperDead = 0;
			// 总期次
			int totalDead = 0;
			// 当期待收时间
			String currentExpireTime = "";
			// 上一期待收时间
			String lastExpireTime = "";

			if (brpList != null) {
				totalDead = brpList.size();
				for (int i = 0; i < brpList.size(); i++) {
					BizReceiptPlan brp = brpList.get(i);
					BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO
							: brp.getCapital();
					BigDecimal netInterest = brp.getNetInterest() == null ? BigDecimal.ZERO
							: brp.getNetInterest();

					investCapital = investCapital.add(capital);
					if (Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(brp.getReceiptStatus())) {
						// 待收中
						surperCapital = surperCapital.add(capital);

						if (surperDead == 0) {
							// 第一次循环到待收中表示当期
							currentAllInterest = netInterest;

							currentExpireTime = brp.getExpireTime();

							// 上一期待收时间
							if (i - 1 > -1) {
								BizReceiptPlan brp2 = brpList.get(i - 1);
								lastExpireTime = brp2.getExpireTime();
							}

						}
						surperDead++;
					} else {

						alreadyBenxi = alreadyBenxi.add(capital).add(netInterest);

					}
				}
			}

			// 求出上一期待收时间与当前时间的日期差
			Date lastDate = DateUtil.format(lastExpireTime);
			Date currentDate = DateUtil.format(currentExpireTime);

			Calendar aCalendar = Calendar.getInstance();

			aCalendar.setTime(lastDate);

			int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

			aCalendar.setTime(new Date());

			int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

			int b = day2 - day1;
			
			if(b > 0 && System.currentTimeMillis() < currentDate.getTime()){
				currentInterest = currentAllInterest.multiply(new BigDecimal(b)).divide(new BigDecimal(30), 2, BigDecimal.ROUND_DOWN);
			}
			
			map.put("investCapital", investCapital);
			map.put("alreadyBenxi", alreadyBenxi);
			map.put("surperCapital", surperCapital);
			map.put("currentAllInterest", currentAllInterest);
			map.put("currentInterest", currentInterest);
			map.put("surperDead", surperDead);
			map.put("totalDead", totalDead);
			map.put("currentExpireTime",DateUtil.format(currentDate, "yyyy-MM-dd"));

			// 查询转让手续费率
			SysParams sysParams = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.BIZ_TRANSFER_FEE);
			map.put("bizTransferFee", sysParams.getParamValue());
			// 查询百分比最小值
			SysParams sysParamsTransfer = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.TRANSFER_CAPITAL_PERCENTER);
			BigDecimal transferPercenter = null;
			try{
				transferPercenter = new BigDecimal(sysParamsTransfer.getParamValue());
			}catch(Exception e){
				//  默认值
				transferPercenter = new BigDecimal(0.97);
			}
			
			BigDecimal minValue = surperCapital.multiply(transferPercenter);
			BigDecimal maxValue = surperCapital.add(currentInterest);
			
			map.put("minValue", minValue);
			map.put("maxValue", maxValue);
			
			map.put("transferId", args.getTransferId());
			
			if (StringUtils.hasLength(args.getTransferId())) {
				// 表示再次债权转让
				BizReceiptTransfer brt = bizReceiptTransferService.getBizReceiptTransferById(args.getTransferId());
				map.put("borrowCode", brt.getTransferCode());
			} else {
				BizBorrow borrow = bizBorrowService.getBizBorrowById(args.getBorrowId());
				// 表示原标的转让
				map.put("borrowCode", borrow.getBorrowCode());
			}
			
			//获取新手标对象，以及理财产品介绍
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);

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
	 * 债权转让确认
	 * @author  JunJie.Liu
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/transferOk", method = RequestMethod.POST)
	@ResponseBody
	public String transferOk(HttpServletRequest request, HttpServletResponse response)
					throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("标的id为null");
			}
			if (!StringUtils.hasLength(args.getAmount())) {
				throw new NullArgumentException("转让金额为null");
			}
				
			BigDecimal transferAmount = new BigDecimal(args.getAmount());
			if (transferAmount==null || transferAmount.compareTo(BigDecimal.ZERO) < 1) {
				throw new NullArgumentException("转让金额为null或者为0");
			}

			// 重新计算一次，防止跨天转让时，数据出现差异
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			if (StringUtils.hasLength(args.getTransferId())) {
				// 表示再次债权转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), args.getBorrowId(), Constant.INVEST_SRC_TYPE_2,
						args.getTransferId());
			} else {
				// 表示原标的转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), args.getBorrowId(),Constant.INVEST_SRC_TYPE_1
						,null);

			}

			// 投资本金
			BigDecimal investCapital = BigDecimal.ZERO;
			// 已收本息
			BigDecimal alreadyBenxi = BigDecimal.ZERO;
			// 剩余本金
			BigDecimal surperCapital = BigDecimal.ZERO;
			// 当期全部利息
			BigDecimal currentAllInterest = BigDecimal.ZERO;
			// 当期至今利息
			BigDecimal currentInterest = BigDecimal.ZERO;
			// 剩余期次
			int surperDead = 0;
			// 总期次
			@SuppressWarnings("unused")
			int totalDead = 0;
			// 已结清的数量
			int jieqing = 0;
			// 逾期
			int yuqi = 0;
			// 当期待收时间
			String currentExpireTime = "";
			// 上一期待收时间
			String lastExpireTime = "";

			if (brpList != null) {
				totalDead = brpList.size();
				for (int i = 0; i < brpList.size(); i++) {
					BizReceiptPlan brp = brpList.get(i);
					BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO
							: brp.getCapital();
					BigDecimal netInterest = brp.getNetInterest() == null ? BigDecimal.ZERO
							: brp.getNetInterest();

					investCapital = investCapital.add(capital);
					if (Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(brp.getReceiptStatus())) {
						// 待收中
						surperCapital = surperCapital.add(capital);

						if (surperDead == 0) {
							// 第一次循环到待收中表示当期
							currentAllInterest = netInterest;

							currentExpireTime = brp.getExpireTime();

							// 上一期待收时间
							if (i - 1 > -1) {
								BizReceiptPlan brp2 = brpList.get(i - 1);
								lastExpireTime = brp2.getExpireTime();
							}

						}
						surperDead++;
					} else {

						alreadyBenxi = alreadyBenxi.add(capital).add(netInterest);
						
						if(Constant.BIZ_RECEIPTPLAN_STATUS_6.equals(brp.getReceiptStatus())){
							jieqing ++;
						}else if(Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(brp.getReceiptStatus())){
							yuqi++;
						}
					}
				}
			}

			// 求出上一期待收时间与当前时间的日期差
			Date lastDate = DateUtil.format(lastExpireTime);
			Date currentDate = DateUtil.format(currentExpireTime);

			Calendar aCalendar = Calendar.getInstance();

			aCalendar.setTime(lastDate);

			int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

			aCalendar.setTime(new Date());

			int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

			int b = day2 - day1;
			
			if(b > 0 && System.currentTimeMillis() < currentDate.getTime()){
				currentInterest = currentAllInterest.multiply(new BigDecimal(b)).divide(new BigDecimal(30), 2, BigDecimal.ROUND_DOWN);
			}
			
			// 查询转让手续费率
			SysParams sysParams = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.BIZ_TRANSFER_FEE);
			
			// 查询百分比最小值
			SysParams sysParamsTransfer = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.TRANSFER_CAPITAL_PERCENTER);
			BigDecimal transferPercenter = null;
			try{
				transferPercenter = new BigDecimal(sysParamsTransfer.getParamValue());
			}catch(Exception e){
				//  默认值
				transferPercenter = new BigDecimal(0.97);
			}
			
			BigDecimal minValue = surperCapital.multiply(transferPercenter);
			BigDecimal maxValue = surperCapital.add(currentInterest);
			
			if(transferAmount.compareTo(minValue) < 0 || transferAmount.compareTo(maxValue) > 0){
				throw new NullArgumentException("转让价格不在转让范围内");
			}
			
			BizBorrow borrow = bizBorrowService.getBizBorrowById(args.getBorrowId());
			
			// 校验用户是否可以转让
			// 1.是否允许债权转让
			if(!"1".equals(borrow.getIsEquitableAssignment())){
				throw new RuntimeException("标的不允许债权转让");
			}
			// 2.用户是vip
			if(!customerService.isVip(args.getUserId())){
				throw new RuntimeException("用户不是vip");
			}
			// 3.还款一次以上
			if(jieqing == 0){
				throw new RuntimeException("没有产生一次还款");
			}
			// 4.待收时间前三天
			aCalendar.setTime(currentDate);
			int day3 = aCalendar.get(Calendar.DAY_OF_YEAR);
			if(day3 - day2 < 3){
				throw new RuntimeException("待收时间不是前三天");
			}
			// 5.原项目的没有处于逾期状态 
			if(yuqi>0){
				throw new RuntimeException("标的逾期，不可转让");
			}
			
			
			// 进行转让
			BizReceiptTransfer brt = new BizReceiptTransfer();
			brt.setBorrowId(args.getBorrowId());
			brt.setCreateUser(args.getUserId());
			brt.setParentTransferId(args.getTransferId());
			brt.setCreateTime(new Date());
			aCalendar.setTime(new Date());
			aCalendar.add(Calendar.DATE, 1);
			brt.setExpireTime(aCalendar.getTime());
			BigDecimal fee = null;
			try{
				fee = new BigDecimal(sysParams.getParamValue());
			}catch(Exception e){
				fee = new BigDecimal(0.004);
			}
			brt.setFee(transferAmount.multiply(fee));
			brt.setInterestData(lastDate);
			brt.setInterestToday(currentInterest);
			brt.setPid(brt.getPK());
			brt.setProfitRate(currentAllInterest.multiply(new BigDecimal(12)).divide(surperCapital, 4, BigDecimal.ROUND_DOWN));
			brt.setProjectValue(surperCapital.add(currentAllInterest));
			brt.setResidualPrincipal(surperCapital);
			brt.setStatus(Constant.BIZ_TRANSFER_STATUS_1);
			brt.setSuccessAmount(transferAmount);
			brt.setTimeRemaining(surperDead);
			
			
			String transferCode = bizReceiptPlanService
					.updateTransfer(args.getBorrowId(),args.getTransferId(),args.getUserId(),transferAmount,brt);
			
			
			map.put("transferCode", transferCode);
			
		} catch (Exception e) {
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
	 * 转让--->债权转让详情
	 * @author  JunJie.Liu
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/transferClaimInfo", method = RequestMethod.POST)
	@ResponseBody
	public String transferClaimInfo(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("标id为null");
			}
			if(!StringUtils.hasLength(args.getTransferId())){
				throw new NullArgumentException("债权转让id为null");
			}
			
			// 查询债权
			BizReceiptTransfer brt = bizReceiptTransferService.getBizReceiptTransferById(args.getTransferId());
			
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			if (StringUtils.hasLength(brt.getParentTransferId())) {
				// 表示再次债权转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), args.getBorrowId(), Constant.INVEST_SRC_TYPE_2,
						brt.getParentTransferId());
			} else {
				// 表示原标的转让
				brpList = bizReceiptPlanService.findList(args.getUserId(), args.getBorrowId(),Constant.INVEST_SRC_TYPE_1
						,null);

			}

			// 投资本金
			BigDecimal investCapital = BigDecimal.ZERO;
			// 已收本息
			BigDecimal alreadyBenxi = BigDecimal.ZERO;
			// 剩余本金
			BigDecimal surperCapital = BigDecimal.ZERO;
			// 剩余期次
			int surperDead = 0;
			// 总期次
			int totalDead = 0;

			if (brpList != null) {
				totalDead = brpList.size();
				for (int i = 0; i < brpList.size(); i++) {
					BizReceiptPlan brp = brpList.get(i);
					BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO
							: brp.getCapital();
					BigDecimal netInterest = brp.getNetInterest() == null ? BigDecimal.ZERO
							: brp.getNetInterest();

					investCapital = investCapital.add(capital);
					if (Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(brp.getReceiptStatus())) {
						// 待收中
						surperCapital = surperCapital.add(capital);
						surperDead++;
					} else {

						alreadyBenxi = alreadyBenxi.add(capital).add(netInterest);

					}
				}
			}
			BizBorrow borrow = bizBorrowService.getBizBorrowById(args.getBorrowId());
			
			map.put("borrowId", borrow.getPid());
			map.put("borrowCode", borrow.getBorrowCode());
			map.put("investCapital", investCapital);
			map.put("alreadyBenxi", alreadyBenxi);
			map.put("surperCapital", surperCapital);
			map.put("alreadyDead", totalDead-surperDead);
			map.put("totalDead", totalDead);
			
			map.put("transferCode", brt.getTransferCode());
			map.put("transferAmount", brt.getSuccessAmount());
			map.put("transferFee", brt.getFee());
			map.put("transferId", brt.getPid());

			
			//获取新手标对象，以及理财产品介绍
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);

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
	 * 已完结--->项目明细
	 * @author  JunJie.Liu
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/transferProjectInfo", method = RequestMethod.POST)
	@ResponseBody
	public String transferProjectInfo(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			TransferApiArgsVo args = (TransferApiArgsVo) ApiUtil.convertObjectByBody(request, TransferApiArgsVo.class);
			if(args == null){
				throw new NullArgumentException("参数对象为null");
			}
			args.setUserId(userid);
			if(!StringUtils.hasLength(args.getUserId())){
				throw new NullArgumentException("用户id为null");
			}
			if(!StringUtils.hasLength(args.getBorrowId())){
				throw new NullArgumentException("标id为null");
			}
			if(!StringUtils.hasLength(args.getTransferId())){
				throw new NullArgumentException("债权转让id为null");
			}
			BizBorrow borrow = bizBorrowService.getBizBorrowById(args.getBorrowId());
			
			
			// 投资信息
			InvestmentInfoModel investModel = userCenterService.selectInvestmentInfoByBorrowId(userid,args.getBorrowId());
			map.put("investmentAmount",investModel.getInvestmentAmount());
			map.put("interest",investModel.getInterest());
			map.put("investmentReward",investModel.getInvestmentReward());
			map.put("hike",investModel.getHike());
			map.put("investmentTime",investModel.getInvestmentTime());
			
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			BizReceiptTransfer brt = null;
			BigDecimal totalAmount = BigDecimal.ZERO;
			if (StringUtils.hasLength(args.getTransferId())) {
				// 表示债权转让过
				brpList = bizReceiptPlanService.findList(userid, args.getBorrowId(), Constant.INVEST_SRC_TYPE_2,
						args.getTransferId());
				
				brt = bizReceiptTransferService.getBizReceiptTransferById(args.getTransferId());
				// 转让信息
				map.put("brt",brt);
			} else {
				// 表示原标
				brpList = bizReceiptPlanService.findList(userid, args.getBorrowId(),Constant.INVEST_SRC_TYPE_1
						,null);
				
			}
			// 已转让期数
			int transferDeadline = 0;
			// 提前还款期数
			int repamentDeadline = 0;
			
			for (int i = 0; i < brpList.size(); i++) {
				BizReceiptPlan bizReceiptPlan = brpList.get(i);
				BigDecimal augend = bizReceiptPlan.getReceiptAmount();
				totalAmount = totalAmount.add(augend);
				if(Constant.BIZ_RECEIPTPLAN_STATUS_4.equals(bizReceiptPlan.getReceiptStatus())){
					transferDeadline++;
				}else if(Constant.BIZ_RECEIPTPLAN_STATUS_7.equals(bizReceiptPlan.getReceiptStatus())){
					repamentDeadline++;
				}else{
					
				}
			}
			// 收益信息，已收金额
			map.put("totalAmount",totalAmount);
			// 总期次
			map.put("deadline", brpList.size());
			// 已收期次
			map.put("receivedDeadline", brpList.size() - transferDeadline - repamentDeadline);
			// 已转让期次
			map.put("transferDeadline",transferDeadline);
			// 提前还款期次
			map.put("repamentDeadline",repamentDeadline);
			
			// 项目信息
			map.put("borrowId", borrow.getPid());
			map.put("borrowCode", borrow.getBorrowCode());
			map.put("borrowMoney", borrow.getBorrowMoney());
			map.put("borrowRate", borrow.getBorrowRate());
			map.put("deadLine", borrow.getDeadline());
			map.put("repaymentTypeName", borrow.getRepaymentTypeName());
			map.put("borrowName",borrow.getBorrowName());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
			
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	

}


