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
 * 2016年1月25日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.business.BizRepaymentPlanInfoVoMapper;
import com.yscf.core.dao.business.BizRepaymentPlanMapper;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.exception.AvailableAmountExcepiton;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;
import com.yscf.core.model.business.RepaymentDto;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.IRepaymentService;
import com.yscf.core.service.financial.IBizAccountCommonService;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.system.ISysAccountService;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;

/**
 * Description：<br> 
 * 还款业务处理类
 * @author  Yu.Zhang
 * @date    2016年1月25日
 * @version v1.0.0
 */
@Service("repaymentService")
public class RepaymentServiceImpl extends BaseService<BaseEntity, String>  implements IRepaymentService {

	
	@Resource(name = "bizBorrowInfoVOService")
	private BizBorrowInfoVOServiceImpl bizBorrowInfoVOService;
	
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl bizBorrowService;
	
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
	
	@Resource(name = "bizReceiptPlanService")
	private BizReceiptPlanServiceImpl bizReceiptPlanService;
	
	@Resource
	private BizRepaymentPlanInfoVoMapper bizRepaymentPlanInfoVoMapper;
	
	@Resource(name = "bizRepaymentPlanMapper")
	private  BizRepaymentPlanMapper bizRepaymentPlanMapper;
	
	@Resource(name = "customerAccountService")
	CustomerAccountServiceImpl customerAccountService;
	
	@Resource(name = "bizAccountCommonService")
	IBizAccountCommonService bizAccountCommonService;                    
	
	@Resource(name = "bizRepaymentPlanService")
	private BizRepaymentPlanServiceImpl bizRepaymentPlanService;
	
	@Resource
	private IBizReceiptTransferService bizReceiptTransferService;
	
	@Resource
	private SysParamsMapper sysParamsMapper;
	
	@Resource
	private ISysAccountService sysAccountService;
	
	@Autowired
	public RepaymentServiceImpl(BizRepaymentPlanMapper dao) {
		super(dao);
	}
	
	

	@Override
	public void prepayment(String borrowId,String userId) throws FrameworkException {
		
		// 获取可用余额
		BigDecimal repaymentAmt = customerAccountService.getUserAvailableAmountExculdExperience(userId);
		// 验证可用余额是否足够
		RepaymentDto dto = this.getPrepaymentInfo(borrowId, DateUtil.format(DateUtil.getSystemDate()));
		if(repaymentAmt.doubleValue() - dto.getPaymentAmount().doubleValue() < 0){
			throw new AvailableAmountExcepiton("还款人余额不足");
		}
		
		
		// 还款计划表需要更新的数据
		List<BizRepaymentPlan> updateRepaymentList = new ArrayList<BizRepaymentPlan>();
		
		// 收息表需要更新的数据
		List<BizReceiptPlan> updateReceiptPlanList = new ArrayList<BizReceiptPlan>();
		
		// step.1 查询借款信息
		BizBorrow bizborrow = bizBorrowService.getBizBorrowById(borrowId);
		
		// step 2 设置 还款计划表、收款计划表 需要更新的数据、以及记录资金流水 
		setUpdateList(borrowId, bizborrow, updateRepaymentList,updateReceiptPlanList);
		
		// step 3 批量更新还款计划表
		if(updateRepaymentList.size() > 0){
			bizRepaymentPlanMapper.batchUpdateToPrepayment(updateRepaymentList);
		}
		
		// step 4 批量更新收款计划表
		if(updateReceiptPlanList.size() > 0 ){
			bizReceiptPlanService.batchUpdateToPrepayment(updateReceiptPlanList);
		}
		
		// step 5 设置借款状态为已结清
		bizborrow.setBorStatus(Constant.BORROW_STATUS_8);
		bizborrow.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
		bizBorrowService.updateByPrimaryKeySelective(bizborrow);
	}

	/**
	 * 
	 * Description：<br> 
	 * 设置 还款计划表、收款计划表、以及资金流水 需要更新的数据
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param borrowId 借款ID
	 * @param bizborrow	 借款信息
	 * @param updateRepaymentLis	需要更新的还款计划表数据t
	 * @param updateReceiptPlanList	  需要更新的回款计划表
	 * @param debitMap
	 * @throws FrameworkException 
	 */
	private void setUpdateList(String borrowId, BizBorrow bizborrow,
			List<BizRepaymentPlan> updateRepaymentList,
			List<BizReceiptPlan> updateReceiptPlanList) throws FrameworkException {
		BizRepaymentPlanMapper bamapper = (BizRepaymentPlanMapper) getDao();
		
		String sysDate = DateUtil.formatYmd(DateUtil.getSystemDate());
		// 当期期次到期日期
		String expireTime = null;
		
		// 根据借款ID获取还款计划表数据
		List<BizRepaymentPlan> repaymentList = bamapper.selectRepaymentsByBorrowId(borrowId);
		
		// 获取当前还款期次
		Integer planIndex = getRepaymentIndex(borrowId,sysDate);
		
		// 获取提前还款罚息率
		BigDecimal prepaymentFeeRate = getPrepaymentFeeRate();
		
		// 获取提前还款管理费占比
		
		BigDecimal debitInterest = BigDecimal.ZERO; // 扣款利息
		BigDecimal debitManagementCost = BigDecimal.ZERO; // 扣款管理费
		
		BigDecimal debitCapital = BigDecimal.ZERO; // 扣款本金
		BigDecimal debitPrepaymentFee = BigDecimal.ZERO; // 扣款提前还款罚息
		
		int day = 0 ; 
		int sumDay = 0 ;
		String replanStatus = null;
		for(BizRepaymentPlan plan: repaymentList){
			// step.4 判断当前期次之前是否有逾期数据,有未结清数据则统计本金，利息，逾期罚息，管理费
			if(Integer.parseInt(plan.getPlanindex()) < planIndex){
				// 未结清的数据
				if(!Constant.BIZ_REPLAN_STATUS_4.equals(plan.getReceiptPalnStatus())){
					// 逾期垫付还款，调用逾期垫付接口
					if(Constant.BIZ_REPLAN_STATUS_3.equals(plan.getReceiptPalnStatus())){
						bizRepaymentPlanService.executeRepaymentToSystem(plan, "");
					}
					
					// 逾期还款，调用逾期还款接口
					if(Constant.BIZ_REPLAN_STATUS_2.equals(plan.getReceiptPalnStatus())){
						bizRepaymentPlanService.executePayment(plan);
					}
				}
			}else if(Integer.parseInt(plan.getPlanindex()) == planIndex){
				
				// 叠加本金，剩余本金
				if(null!=plan.getCapital()){
					debitCapital =debitCapital.add(plan.getCapital());
				}
				// 本金叠加剩余本金
				if(null!=plan.getRemainingCapital()){
					debitCapital =debitCapital.add(plan.getRemainingCapital());
				}
				
				// 提前还款罚息  = 未还本金*提前还款罚息率
				if(null != prepaymentFeeRate){
					debitPrepaymentFee = calculatePrepaymentFee(prepaymentFeeRate, debitCapital);
					plan.setPrepaymentFee(debitPrepaymentFee);
				}
				
				//  ====================================判断借款，还款方式是不是到期一次性付清，是则需要计算之前期数的利息，管理费================================
				if("3".equals(bizborrow.getRepaymentType())){
					
					// 获取起息至今相隔天数
					try {
						day = EscfDateUtil.daysBetween(sysDate,DateUtil.formatYmd(bizborrow.getBorFullTime()));
						sumDay = EscfDateUtil.daysBetween(repaymentList.get(repaymentList.size()-1).getExpireTime(),DateUtil.formatYmd(bizborrow.getBorFullTime()));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					// 获取日利息
					debitInterest =this.getPreRepaymentInterest(repaymentList.get(repaymentList.size()).getInterest(),sumDay, day);
					
					// 获取日管理费
					debitManagementCost = this.getPreRepaymentInterest(repaymentList.get(repaymentList.size()).getManagementCost(),sumDay,day);
				}
				
				
				// step.5 当前期数，判断是否是还款日
				expireTime = DateUtil.formatYmd(DateUtil.format(plan.getExpireTime()));
				if(!expireTime.equals(sysDate)){
					
					// ==================== 提前还款日期不是还款日，以天计算当期利息，管理费============================= 
					// 获取日期之间相隔天数
					try {
						day = EscfDateUtil.daysBetween(sysDate,plan.getExpireTime());
						if(day < 0){
							day = EscfDateUtil.daysBetween(DateUtil.formatYmd(bizborrow.getBorFullTime()),sysDate);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					// ================================还款方式为到期一次性付清 不需要计算天利息，由前面代码计算==================================
					if(!"3".equals(bizborrow.getRepaymentType())){
						// 利息
						if(null!=bizborrow.getBorrowRate()){
							// 计算还款天利息 
							debitInterest =calculateDayInterest(plan.getInterest(),day);
							plan.setInterest(debitInterest);
						}
						
						// 管理费
						if(null!=bizborrow.getManageExpenseRate()){
							// 计算天管理费
							debitManagementCost = calculateDayInterest(plan.getManagementCost(),day); 
							plan.setManagementCost(debitManagementCost);
						}
					}
				
					// 还款状态为提前还款
					replanStatus = Constant.BIZ_REPLAN_STATUS_7;
				}else{
					// ==================== 提前还款日期是还款日 =============================
					day = 0 ;
					debitInterest = plan.getInterest() == null ? BigDecimal.ZERO : plan.getInterest();
					debitManagementCost = plan.getManagementCost() == null ? BigDecimal.ZERO : plan.getManagementCost();
					// 还款状态为已结清
					replanStatus = Constant.BIZ_REPLAN_STATUS_4;
				}
				
				// 扣除本金
				String note = "[提前还款] "+ bizborrow.getBorrowCode() + " " + bizborrow.getBorrowName();
				if (debitCapital.compareTo(BigDecimal.ZERO) > 0) {
					customerAccountService.updateDeductedAvailableAmount(debitCapital, plan.getCustomerId(), plan.getBorrowId(), TradeTypeConstant.OTHER_TYPE_510, note);
				}
				
				// 扣除利息
				if (plan.getInterest().compareTo(BigDecimal.ZERO) > 0) {
					customerAccountService.updateDeductedAvailableAmount(plan.getInterest(), plan.getCustomerId(), plan.getBorrowId(), TradeTypeConstant.OTHER_TYPE_512, note);
				}
				
				// 扣除管理费
				if(plan.getManagementCost().compareTo(BigDecimal.ZERO) > 0){
					customerAccountService.updateDeductedAvailableAmount(plan.getManagementCost(), plan.getCustomerId(), plan.getBorrowId(), TradeTypeConstant.OTHER_TYPE_504, note);
					sysAccountService.updateAddSystemAmount(plan.getManagementCost(), plan.getCustomerId(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1011, plan.getBorrowId(), note);
				}
				
				// 扣除提前还款罚息
				if(debitPrepaymentFee.compareTo(BigDecimal.ZERO) > 0){
					customerAccountService.updateDeductedAvailableAmount(debitPrepaymentFee, plan.getCustomerId(), plan.getBorrowId(), TradeTypeConstant.OTHER_TYPE_522, note);
					sysAccountService.updateAddSystemAmount(getPrepaymentFeeAmt(debitPrepaymentFee,"1"), plan.getCustomerId(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1035, plan.getBorrowId(), note);
				}
				
				
				// 回款计划表数据更新，回款人金额更新，回款人资金流水记录
				updateReceiptPlanListAdd(updateReceiptPlanList, plan,day,sumDay,prepaymentFeeRate,bizborrow);
				
				// 设置已还金额 本金+利息+管理费+提前还款罚息
				plan.setRepaidamount(debitCapital.add(debitPrepaymentFee).add(debitInterest).add(debitManagementCost));
				plan.setRemainingCapital(BigDecimal.ZERO);
				// 设置备注
				plan.setRepPlaDesc("提前还剩余本金，收取"+plan.getPrepaymentFee().doubleValue()+"提前还款罚息。");
				
				// 添加需要更新的还款计划数据
				updateRepaymentListAdd(updateRepaymentList, plan,replanStatus);
				
			}else{
//				plan.setInterest(BigDecimal.ZERO);
//				plan.setManagementCost(BigDecimal.ZERO);
				// 大于已提前还款的期次，已还金额设置为 0 
				plan.setRepaidamount(BigDecimal.ZERO);
				updateRepaymentListAdd(updateRepaymentList, plan,Constant.BIZ_REPLAN_STATUS_7);
			}
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 到期一次性付清，计算提前还款利息、管理费
	 * @author  Yu.Zhang
	 * @date    2016年3月10日
	 * @version v1.0.0
	 * @param money
	 * @param sumDay
	 * @param day
	 * @return
	 */
	private BigDecimal getPreRepaymentInterest(BigDecimal money ,Integer sumDay, Integer day){
		return money.divide(new BigDecimal(sumDay)).multiply(new BigDecimal(day)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
	}

	/**
	 * 根据借款ID获取当前应还款期次
	 * Description：<br> 
	 * TODO
	 * @author  Yu.Zhang
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	private Integer getRepaymentIndex(String borrowId,String sysDate) {
		BizRepaymentPlanMapper bamapper = (BizRepaymentPlanMapper) getDao();
		BizRepaymentPlan repaymentPlan = bamapper.getRepaymentPlanIndexByDate(borrowId,sysDate);
		if(null == repaymentPlan){
			return Integer.MAX_VALUE;
		}
		return Integer.parseInt(repaymentPlan.getPlanindex());
	}


	/**
	 * 
	 * Description：<br> 
	 * 计算提前还款罚息
	 *  公式： 本金 * 提前还款罚息率 
	 * @author  Yu.Zhang
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param prepaymentFeeRate
	 * @param capital
	 */
	private BigDecimal calculatePrepaymentFee(BigDecimal prepaymentFeeRate,BigDecimal capital) {
		return capital.multiply(prepaymentFeeRate).setScale(2,BigDecimal.ROUND_HALF_DOWN);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 *  根据类型，获取计算分成后应得提前还款罚息
	 * @author  Yu.Zhang
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param prepaymentFee 提前还款罚金
	 * @param type 1 还款人 2 回款人
	 */
	private BigDecimal getPrepaymentFeeAmt(BigDecimal prepaymentFee,String type) {
		
		// 根据系统参数，类型 来分成提前还款罚息
		SysParams sp = sysParamsMapper.getParamsByParamKey(Constant.PRE_REPAYMENT_RATE);
		if(sp==null){
			throw new NullArgumentException("Constant.PRE_REPAYMENT_RATE getParamsByParamKey(): sysparams is null");
		}
		// 系统参数配置分成率
		BigDecimal fee = new BigDecimal(sp.getParamValue());
		
		BigDecimal temp = prepaymentFee.multiply(fee).divide(new BigDecimal(100.0));
		if("2".equals(type)){
			return temp;
		}else{
			return ArithmeticUtil.sub(prepaymentFee, temp);
		}
	}

	/**
	 * 
	 * Description：<br> 
	 * 获取提前还款罚息率
	 * @author  Yu.Zhang
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @return
	 */
	private BigDecimal getPrepaymentFeeRate() {
		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("PREPAYMENT_FEE_RATE");
		if(null != sysParams){
			return  BigDecimal.valueOf(Double.parseDouble(sysParams.getParamValue()));
		}
		return BigDecimal.ZERO;
		
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 添加需要更新的收款计划数据
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param updateReceiptPlanList
	 * @param plan
	 * @param day
	 * @param receiptStatus
	 * @param prepaymentFeeRate
	 * @throws FrameworkException 
	 */
	private void updateReceiptPlanListAdd(List<BizReceiptPlan> updateReceiptPlanList, BizRepaymentPlan plan,
			int day,int sumDay,BigDecimal prepaymentFeeRate, BizBorrow bizborrow) throws FrameworkException {
		List<BizReceiptPlan> receiptList;
		receiptList = bizReceiptPlanService.findValidByRepaymentId(plan.getPid());

		BigDecimal interest = null;
		BigDecimal capital = null; 
		BigDecimal manageFee = null;
		BigDecimal prerepaymentFee = null;
		BigDecimal netHike = null;
		BigDecimal netManageFee = null;
		String userId = null;
		BizReceiptPlan bizReceiptPlan = null;
		for(BizReceiptPlan receipt : receiptList){
			userId = receipt.getCustomerId();
			
			// ====================非到期一次性付清还款方式 提前还款时间不是还款日 按天计算收益利息========================
			if(!"3".equals(bizborrow.getRepaymentType()) && day > 0){
				if(null != receipt.getNetHike()){
					receipt.setNetHike(calculateDayInterest(receipt.getNetHike(),day));
				}
				if(null != receipt.getReceivableHike()){
					receipt.setReceivableHike(calculateDayInterest(receipt.getReceivableHike(),day));
				}
				if(null != receipt.getIncreaseInterest()){
					receipt.setIncreaseInterest(calculateDayInterest(receipt.getIncreaseInterest(),day));
				}
				if(null != receipt.getNetInterest()){
					receipt.setNetInterest(calculateDayInterest(receipt.getNetInterest(),day));
				}
				if(null != receipt.getReceivableInterest()){
					receipt.setReceivableInterest(calculateDayInterest(receipt.getReceivableInterest(),day));
				}
			}else if ("3".equals(bizborrow.getRepaymentType())) {
				  // ==================== 到期一次性付清还款方式 =============================
				
				  // 根据借款ID，回款人ID，借款期限 获取最后一期数据
				bizReceiptPlan = bizReceiptPlanService.getLastDeline(receipt.getBorrowId(),receipt.getCustomerId(),bizborrow.getBorDeadline());
				if(null != bizReceiptPlan.getNetHike()){
					receipt.setNetHike(this.getPreRepaymentInterest(bizReceiptPlan.getNetHike(),sumDay, day));
				}
				if(null != bizReceiptPlan.getReceivableHike()){
					receipt.setReceivableHike(this.getPreRepaymentInterest(bizReceiptPlan.getReceivableHike(), sumDay , day));
				}
				if(null != bizReceiptPlan.getIncreaseInterest()){
					receipt.setIncreaseInterest(this.getPreRepaymentInterest(bizReceiptPlan.getIncreaseInterest(), sumDay , day));
				}
				if(null != bizReceiptPlan.getNetInterest()){
					receipt.setNetInterest(this.getPreRepaymentInterest(bizReceiptPlan.getNetInterest(), sumDay , day));
				}
				if(null != bizReceiptPlan.getReceivableInterest()){
					receipt.setReceivableInterest(this.getPreRepaymentInterest(bizReceiptPlan.getReceivableInterest(), sumDay , day));
				}
			}
			
			capital = receipt.getCapital().add(receipt.getRemainingCapital());
			
			// 计算提前还款罚息
			if(prepaymentFeeRate.intValue() > 0 ){
				receipt.setPrepaymentFee(calculatePrepaymentFee(prepaymentFeeRate,capital));
			}
			
			// 2.收款本金
			String note = bizborrow.getBorrowCode() + " " + bizborrow.getBorrowName() + " ," + receipt.getPlanIndex() + "/" + bizborrow.getDeadline();
			String managerNote = bizborrow.getBorrowCode() + " " + bizborrow.getBorrowName() ;
			if (capital.compareTo(BigDecimal.ZERO) > 0) {
				customerAccountService.updateAddAvailableAmount(capital, userId, receipt.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_407, note);
			}

			// 3.收款利息
			interest = receipt.getNetInterest() == null ? BigDecimal.ZERO : receipt.getNetInterest();
			if (interest.compareTo(BigDecimal.ZERO) > 0) {
				customerAccountService.updateAddAvailableAmount(interest, userId, receipt.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_409, note);
			}

			// 4.扣除管理费
			manageFee = receipt.getManagementCost() == null ? BigDecimal.ZERO : receipt.getManagementCost();
			if (manageFee.compareTo(BigDecimal.ZERO) > 0) {
				// 用户扣除
				customerAccountService.updateDeductedAvailableAmount(manageFee, userId, receipt.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, managerNote);
				// 系统增加
				customerAccountService.updateDeductedInterestManagementFee(manageFee, userId, receipt.getBorrowId(), null);
			}
			
			// ========================5.提前还款罚息 根据系统参数配置分成，用户收入==============================
			prerepaymentFee = receipt.getPrepaymentFee() == null ? BigDecimal.ZERO : receipt.getPrepaymentFee();
			if (prerepaymentFee.compareTo(BigDecimal.ZERO) > 0) {
				// 用户收入
				customerAccountService.updateAddAvailableAmount(getPrepaymentFeeAmt(prerepaymentFee,"2"), userId, receipt.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_427, note);
			}
			
			// 6.收款加息利息
			if(Constant.NET_HIKE_STATUS_0.equals(receipt.getNetHikeStatus())){
				netHike = receipt.getNetHike() == null ? BigDecimal.ZERO : receipt.getNetHike();
				if (netHike.compareTo(BigDecimal.ZERO) > 0) {
					// 用户增加
					customerAccountService.updateAddAvailableAmount(netHike, userId, receipt.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_401, note);
					// 从风险备用金扣除
					sysAccountService.updateSubSystemAmount(netHike, userId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1012, receipt.getBorrowId(), note);
		
					receipt.setNetHikeStatus(Constant.NET_HIKE_STATUS_1);
				}
				// 7.加息利息管理费
				netManageFee = receipt.getIncreaseInterest() == null ? BigDecimal.ZERO : receipt.getIncreaseInterest();
				if (netManageFee.compareTo(BigDecimal.ZERO) > 0) {
					customerAccountService.updateDeductedAvailableAmount(netManageFee, userId, receipt.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, managerNote);
					customerAccountService.updateDeductedInterestManagementFee(netManageFee, userId, receipt.getBorrowId(), "");
				}
			}
			
			
			if(Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(receipt.getReceiptStatus())){
				// 转让中，则将在架的债权，进行下架
				bizReceiptTransferService.soldOutByTransferId(receipt.getTransferId(),"债权转让时，发生了还款");
			}
			
			// 设置状态为提前还款
			receipt.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_7);
			// 已提前还款，设置剩余本金为0 
			receipt.setRemainingCapital(BigDecimal.ZERO);
			// 设置已收本金
			receipt.setRepaidCapital(capital);
			// 设置已收利息
			receipt.setRepaidInterest(receipt.getNetInterest());
			// 将提前还款罚息设置到罚息字段中国
			receipt.setLateFee(receipt.getPrepaymentFee());
			// 设置加息利息状态为已收
			receipt.setNetHikeStatus(Constant.NET_HIKE_STATUS_1);
			// 设置备注
			receipt.setRecPlaDesc("收取罚息：提前还款罚息 "+receipt.getPrepaymentFee());
		}
		updateReceiptPlanList.addAll(receiptList);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 计算天利息
	 * @author  Yu.Zhang
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param money
	 * @param day
	 * @return
	 */
	public static BigDecimal calculateDayInterest(BigDecimal money , Integer day){
		return money.divide(new BigDecimal(30),4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(day)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
	}
	
	public static void main(String[] args) {
		System.out.println(calculateDayInterest(new BigDecimal("2.7800"), 26));
	}

	/**
	 * 
	 * Description：<br> 
	 * 添加需要更新的还款计划数据
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param updateList
	 * @param plan
	 * @param status
	 */
	private void updateRepaymentListAdd(List<BizRepaymentPlan> updateList,
			BizRepaymentPlan plan,String status) {
		// 设置还款状态
		plan.setReceiptPalnStatus(status);
		
		// 设置最后更新时间
		plan.setLastUpdateTime(DateUtil.getToday());
		
		// 设置剩余本金为0 
		plan.setRemainingCapital(BigDecimal.ZERO);
		
		// 设置实际还款时间
		plan.setRepaidTime(DateUtil.format(DateUtil.getToday()));
		updateList.add(plan);
	}

	@Override
	public RepaymentDto getPrepaymentInfo(String borrowId,String repaymentDate) {
		
		RepaymentDto repaymentDto = new RepaymentDto();
		
		// step.1 查询借款信息
		BizBorrowInfoVO vo = bizBorrowInfoVOService.getBizBorrowById(borrowId);
		try {
			repaymentDto = ConvertObjectUtil.convertObject(vo, RepaymentDto.class);
			repaymentDto.setBorrowId(vo.getPid());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		BizRepaymentPlanMapper bamapper = (BizRepaymentPlanMapper) getDao();
		BigDecimal latefee = BigDecimal.valueOf(0);	// 逾期罚息
		BigDecimal capital = BigDecimal.valueOf(0); // 本金
		BigDecimal interest = BigDecimal.valueOf(0); // 利息
		BigDecimal dayInterest = BigDecimal.valueOf(0); // 天利息
		BigDecimal shouldInterest = BigDecimal.valueOf(0); // 应还利息
		BigDecimal managementCost = BigDecimal.valueOf(0); // 管理费
		BigDecimal shouldManagementCost = BigDecimal.valueOf(0); // 应还管理费
		BigDecimal dayManagementCost = BigDecimal.valueOf(0); // 天管理费
		BigDecimal prepaymentFee = BigDecimal.valueOf(0);	// 提前还款罚息
		boolean isAddCapital = true;
		// 
		repaymentDate = DateUtil.formatYmd(DateUtil.format(repaymentDate));
		// 当期期次到期日期
		String expireTime = null;
		
		// 获取提前还款罚息率
		BigDecimal prepaymentFeeRate = getPrepaymentFeeRate();
		
		// step.2 根据借款ID, 当前日期获取应还期次
		Integer planIndex = getRepaymentIndex(borrowId,repaymentDate);
		
		// step.3 根据借款ID获取还款计划列表
		List<BizRepaymentPlan> list = bamapper.selectRepaymentsByBorrowId(borrowId);
		for(BizRepaymentPlan plan: list){
			// step.4 判断当前期次之前是否有逾期数据,有未结清数据则统计本金，利息，逾期罚息，管理费
			if(Integer.parseInt(plan.getPlanindex()) < planIndex){
				if(!Constant.BIZ_REPLAN_STATUS_4.equals(plan.getReceiptPalnStatus())){
					// 计算逾期罚息
					if(null!=plan.getLatefee()){
						latefee =latefee.add(plan.getLatefee());
					}
					
					// 本金
					if(null!=plan.getCapital()){
						capital =capital.add(plan.getCapital());
					}
					
					// 利息
					if(null!=plan.getInterest()){
						interest =interest.add(plan.getInterest());
					}
					// 管理费
					if(null!=plan.getManagementCost()){
						managementCost =managementCost.add(plan.getManagementCost());
					}
				}
			}else if(Integer.parseInt(plan.getPlanindex()) == planIndex){
				
				// 判断当前期是否已结清
				if(Constant.BIZ_REPLAN_STATUS_4.equals(plan.getReceiptPalnStatus())){
					continue;
				}
				
				// 叠加本金，剩余本金
				if(null!=plan.getCapital()){
					capital =capital.add(plan.getCapital());
				}
				
				// 本金叠加剩余本金
				if(null!=plan.getRemainingCapital()){
					capital =capital.add(plan.getRemainingCapital());
				}
				
				isAddCapital = false;
				
				// step.5 当前期数，判断是否是还款日
				expireTime = DateUtil.formatYmd(DateUtil.format(plan.getExpireTime()));
				if(expireTime.equals(repaymentDate)){
					// 6 是还款日则直接叠加 利息，管理费
					// 利息
					if(null!=plan.getInterest()){
						interest =interest.add(plan.getInterest());
					}
					// 管理费
					if(null!=plan.getManagementCost()){
						managementCost =managementCost.add(plan.getManagementCost());
					}
				}else{
					
					// step.7 不是还款日，以天计算当期利息，管理费
					// 获取日期之间相隔天数
					int day = 0;
					try {
						day = EscfDateUtil.daysBetween(plan.getExpireTime(),repaymentDate);
						if(day < 0){
							day = EscfDateUtil.daysBetween(DateUtil.formatYmd(vo.getBorFullTime()),repaymentDate);
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
					
					// 利息
					if(null!=repaymentDto.getBorrowRate()){
						// 计算还款天利息 
						dayInterest = calculateDayInterest(plan.getInterest(),day);
					}
					
					interest =interest.add(dayInterest);
					
					// 管理费
					if(null!=repaymentDto.getManageExpenseRate()){
						dayManagementCost = calculateDayInterest(plan.getManagementCost(),day); 
					}
					
					managementCost =managementCost.add(dayManagementCost);
					// 记录应还利息,应还管理费
					shouldInterest = shouldInterest.add(plan.getInterest());
					shouldManagementCost = shouldManagementCost.add(plan.getManagementCost());
				}
			}else{
				
				// 判断当前期是否已结清
				if(Constant.BIZ_REPLAN_STATUS_4.equals(plan.getReceiptPalnStatus())){
					continue;
				}
				
				if(isAddCapital){
					// 叠加本金，剩余本金
					if(null!=plan.getCapital()){
						capital =capital.add(plan.getCapital());
					}
					
					// 本金叠加剩余本金
					if(null!=plan.getRemainingCapital()){
						capital =capital.add(plan.getRemainingCapital());
					}
				}
				
				// 记录应还利息,应还管理费
				if(null != plan.getInterest()){
					shouldInterest = shouldInterest.add(plan.getInterest());
				}
				if(null != plan.getManagementCost()){
					shouldManagementCost = shouldManagementCost.add(plan.getManagementCost());
				}
			}
		}
		
		// 计算提前还款罚息
		prepaymentFee =  calculatePrepaymentFee(prepaymentFeeRate, capital);
		
		//设置信息
		repaymentDto.setLatefee(latefee.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		repaymentDto.setCapital(capital.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		repaymentDto.setInterest(interest.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		repaymentDto.setPrepaymentFee(prepaymentFee.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		// 少支付利息
		if(dayInterest.doubleValue() > 0){
			repaymentDto.setPrepaymentInterest(shouldInterest.subtract(dayInterest).setScale(2,BigDecimal.ROUND_HALF_DOWN));
		}else{
			repaymentDto.setPrepaymentInterest(shouldInterest.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		}
		// 少支付管理费
		if(dayManagementCost.doubleValue() > 0){
			repaymentDto.setPrepaymentManage(shouldManagementCost.subtract(dayManagementCost).setScale(2,BigDecimal.ROUND_HALF_DOWN));
		}else{
			repaymentDto.setPrepaymentManage(shouldManagementCost.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		}
		repaymentDto.setManagementCost(managementCost.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		// 应还金额= 本金+利息+逾期罚息+管理费
		repaymentDto.setPaymentAmount(capital.add(interest).add(prepaymentFee).add(managementCost).add(latefee).setScale(2,BigDecimal.ROUND_HALF_DOWN));

		return repaymentDto;
	}

	@Override
	public BizRepaymentPlanInfoVo getRepaidInfoVoByBid(String borrowId) {
		return bizRepaymentPlanInfoVoMapper.getRepaidInfoVo(borrowId);
	}

	@Override
	public BizRepaymentPlanInfoVo getOverdueRepayBorrow(String borrowId) {
		return bizRepaymentPlanInfoVoMapper.getOverdueRepayBorrow(borrowId);
	}

	@Override
	public List<BizRepaymentPlanInfoVo> getOverdueRepayments(String borrowId) {
		return bizRepaymentPlanInfoVoMapper.getOverdueRepayment(borrowId);
	}

	@Override
	public BizRepaymentPlan selectRepayByTime(String borrowId) {
		BizRepaymentPlanMapper bamapper = (BizRepaymentPlanMapper) getDao();
		return bamapper.selectRepayByTime(borrowId);
	}

	@Override
	public List<BizRepaymentPlan> selectOverdueRepayment(String borrowId) {
		BizRepaymentPlanMapper bamapper = (BizRepaymentPlanMapper) getDao();
		return bamapper.selectOverdueRepayment(borrowId);
	}

	@Override
	public BizRepaymentPlanInfoVo getRepaymentInfoVo(String repayId) {
		return bizRepaymentPlanInfoVoMapper.getRepaymentInfoVo(repayId);
	}

	@Override
	public BizRepaymentPlanInfoVo getRepayInfoByTime(String borrowId) {
		return bizRepaymentPlanInfoVoMapper.getRepayInfoByTime(borrowId);
	}

}


