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
 * 2015年11月12日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.calculation.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.yscf.common.util.ArithmeticUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.util.AmountUtil;

/**
 * Description：<br> 
 * 按月分期还款(等本等息) 计算
 * @author  Yu.Zhang
 * @date    2015年11月12日
 * @version v1.0.0
 */
public class AverageInterestCalc extends AbstractCalculation implements
		ICalculation {
	
	public AverageInterestCalc(CalculationDto dto){
		super.calculationDto = dto;
	}

	
	@Override
	public List<BizRepaymentPlan> execRepaymentCalc() throws Exception {
		// 检查执行
		execCheck();
		
		BizRepaymentPlan bizRepaymentPlan = null;
		List<BizRepaymentPlan> resultList = new ArrayList<BizRepaymentPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		int day = EscfDateUtil.getDay(repaymentDate);
		
		BigDecimal remainingCapital = calculationDto.getRepaymentAmt(); // 借款金额
		
		// 在生成还款计划和收款计划时，由于四舍五入的原因在平滩到每一期时本金和加息收益会变少，因此再最后一期须补齐所缺数额
		BigDecimal remainingCapital_ = remainingCapital;
		
		// 计算每月应还本金 : 本金/还款期限
		BigDecimal monthAmt = ArithmeticUtil.div(calculationDto.getRepaymentAmt(),new BigDecimal(calculationDto.getRepaymentDeadline()),4);
		
		// 计算每月应还利息 ：本金*年化率/12
		BigDecimal monthInterest = super.getMonthInterest(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate());
		
		// 计算管理费
		BigDecimal managementCost = super.getMonthInterest(calculationDto.getRepaymentAmt(),calculationDto.getManagementRate());
//		BigDecimal managementCost = super.getMonthInterest(monthAmt,calculationDto.getManagementRate());
		
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			bizRepaymentPlan = new BizRepaymentPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastMonth(repaymentDate,day);
			bizRepaymentPlan.setExpireTime(repaymentDate);
			
			// 设置还款期限
			bizRepaymentPlan.setPlanindex((i+1)+"");
			
			// 计算每个月应付管理费: (本金/12)*管理费率/12
			bizRepaymentPlan.setManagementCost(ArithmeticUtil.round(managementCost,2));
			
			// 计算每个月应还利息
			bizRepaymentPlan.setInterest(ArithmeticUtil.round(monthInterest, 2));
			
			remainingCapital_ = ArithmeticUtil.sub(remainingCapital_,ArithmeticUtil.round(monthAmt, 2));		
			
			// 设置剩余本金,应还本金
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				// 最后一期 剩余本金为0，应还本金为上期剩余本金
				bizRepaymentPlan.setRemainingCapital(BigDecimal.ZERO);
				bizRepaymentPlan.setCapital(ArithmeticUtil.round(monthAmt, 2).add(remainingCapital_));
				if(resultList.size() > 1){
					// 设置最后第二期的剩余本金为最后一期的应还本金
					resultList.get(i-1).setRemainingCapital(ArithmeticUtil.round(monthAmt, 2).add(remainingCapital_));
				}
			}else{
				
				// 设置每月应还本金
				bizRepaymentPlan.setCapital(ArithmeticUtil.round(monthAmt, 2));
				
				// 设置剩余本金
				remainingCapital = ArithmeticUtil.sub(remainingCapital,monthAmt);
				bizRepaymentPlan.setRemainingCapital(ArithmeticUtil.round(remainingCapital,2));
			}
			// 设置还款信息
			super.setRepaymentPlan(bizRepaymentPlan);
			resultList.add(bizRepaymentPlan);
		}
		
		return resultList;
	}

	@Override
	public List<BizReceiptPlan> execReceivablesCalc() throws Exception {
		// 检查执行
		execCheck();
		
		BizReceiptPlan bizReceiptPlan = null;
		List<BizReceiptPlan> resultList = new ArrayList<BizReceiptPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		int day = EscfDateUtil.getDay(repaymentDate);
		
		BigDecimal zero = new BigDecimal(0);
		
		BigDecimal remainingCapital = calculationDto.getRepaymentAmt(); // 借款金额
		
		// 在生成还款计划和收款计划时，由于四舍五入的原因在平滩到每一期时本金和加息收益会变少，因此再最后一期须补齐所缺数额
		BigDecimal remainingCapital_ = remainingCapital;
		
		// 计算每月应收本金 : 本金/借款款期限
		BigDecimal monthAmt = ArithmeticUtil.div(calculationDto.getRepaymentAmt(), new BigDecimal(calculationDto.getRepaymentDeadline()));
		
		// 计算每月应收利息 ：(本金/借款期限)*年化率/12
		BigDecimal netInterest = super.getMonthInterest(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate());
		
		BigDecimal netHike = zero ;												// 加息劵获得利息
		BigDecimal netHikeManager = zero ;										// 加息劵获得利息管理费
		BigDecimal interestManager = zero ;										// 利息管理费	
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			bizReceiptPlan = new BizReceiptPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastMonth(repaymentDate,day);
			bizReceiptPlan.setExpireTime(repaymentDate);
			
			// 设置还款期限
			bizReceiptPlan.setPlanIndex((i+1)+"");
			
			bizReceiptPlan.setNetInterest(ArithmeticUtil.round(netInterest, 2));
			
			// 计算每个月应收的利息管理费
			if(null!=calculationDto.getInterestRate()){
				interestManager = super.getMonthInterestManager(netInterest,calculationDto.getInterestRate());
			}
			// 设置利息管理费
			bizReceiptPlan.setManagementCost(ArithmeticUtil.round(interestManager,2));
			// 设置净收利息= 利息-利息管理费
			bizReceiptPlan.setReceivableInterest(ArithmeticUtil.round(ArithmeticUtil.sub(bizReceiptPlan.getNetInterest(),bizReceiptPlan.getManagementCost() ),2));
			// 判断是否使用加息劵，使用了的话就计算加息
			if(null!=calculationDto.getAddInterest() && 0 != calculationDto.getAddInterest().compareTo(new BigDecimal(0.0))){
				
				// 有传入总加息利息，直接平分
				if(null!=calculationDto.getSumAddInterest()){
					netHike = super.averageSumAddInterest(calculationDto.getRepaymentDeadline());
				}else{
					// 加息劵利息算法
					netHike = super.getMonthNetHike(calculationDto.getRepaymentAmt(),calculationDto.getAddInterest(),calculationDto.getRepaymentDeadline());
				}
				bizReceiptPlan.setNetHike(ArithmeticUtil.round(netHike,2));
				
				
				// 计算加息劵管理费
				if(null!=calculationDto.getAddInterestRate()){
					netHikeManager = super.getMonthInterestManager(netHike,calculationDto.getAddInterestRate());
				}
				// 加息利息管理费
				bizReceiptPlan.setIncreaseInterest(ArithmeticUtil.round(netHikeManager,2));
				// 加息净收利息=加息利息-加息利息管理费
				bizReceiptPlan.setReceivableHike(ArithmeticUtil.round(ArithmeticUtil.sub(bizReceiptPlan.getNetHike(), bizReceiptPlan.getIncreaseInterest()),2));
			}else{
				bizReceiptPlan.setReceivableHike(zero);
			}
			
			remainingCapital_ = ArithmeticUtil.sub(remainingCapital_,ArithmeticUtil.round(monthAmt,2));		
			
			// 利息 = 净收利息 + 净收加息劵利息
			bizReceiptPlan.setInterest(ArithmeticUtil.round(ArithmeticUtil.add(bizReceiptPlan.getReceivableInterest(), bizReceiptPlan.getReceivableHike()), 2));
			
			// 设置剩余本金,应还本金
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				// 最后一期 剩余本金为0，应还本金为上期剩余本金
				bizReceiptPlan.setRemainingCapital(BigDecimal.ZERO);
				bizReceiptPlan.setCapital(ArithmeticUtil.round(monthAmt, 2).add(remainingCapital_));
				// 设置最后第二期的剩余本金为最后一期的应还本金
				resultList.get(i-1).setRemainingCapital(ArithmeticUtil.round(monthAmt, 2).add(remainingCapital_));
			}else{
				// 设置应收本金
				bizReceiptPlan.setCapital(ArithmeticUtil.round(monthAmt,2));
			
				// 设置剩余本金
				remainingCapital = ArithmeticUtil.sub(remainingCapital,monthAmt);
				bizReceiptPlan.setRemainingCapital(remainingCapital);
			
			}
			// 设置收款信息
			super.setReceiptPlan(bizReceiptPlan);
			resultList.add(bizReceiptPlan);
		}
		
		return resultList;
	}
	
	@Override
	public List<BizRepaymentPlan> execRepaymentCalcByDate() throws Exception {
		// 检查执行
		execCheck();
		
		BizRepaymentPlan bizRepaymentPlan = null;
		List<BizRepaymentPlan> resultList = new ArrayList<BizRepaymentPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		BigDecimal capital = calculationDto.getRepaymentAmt(); // 投资金额
		BigDecimal manageFee = calculationDto.getManagementRate(); // 管理费率
		manageFee = manageFee == null ? BigDecimal.ZERO : manageFee;
		Integer deadline = calculationDto.getRepaymentDeadline();
		BigDecimal yearRate = calculationDto.getRepaymentRate();
		
		BigDecimal benXi = AmountUtil.getCapitalAndInterestByDate(deadline, capital,yearRate);
		BigDecimal liXi = AmountUtil.getInterestByDate(deadline, capital, yearRate);
		
		BigDecimal r = capital;
		BigDecimal in = liXi;
		
		for(int i=1;i<deadline+1;i++){
			
			bizRepaymentPlan = new BizRepaymentPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,1);
			bizRepaymentPlan.setExpireTime(repaymentDate);
			
			// 设置还款期限
			bizRepaymentPlan.setPlanindex(i+"");
			
			//利息
			BigDecimal ins = r.multiply(AmountUtil.getDateRate(yearRate)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//本金
			BigDecimal curCaptial = benXi.subtract(ins);
			
			if(i == deadline){
				// 最后一期
				bizRepaymentPlan.setInterest(in);
				BigDecimal managementCost = r.multiply(manageFee);
				bizRepaymentPlan.setManagementCost(ArithmeticUtil.round(managementCost,2));
				
				bizRepaymentPlan.setCapital(r);
				bizRepaymentPlan.setRemainingCapital(BigDecimal.ZERO);
				
			}else{
				bizRepaymentPlan.setInterest(ins);
				BigDecimal managementCost = curCaptial.multiply(manageFee);
				bizRepaymentPlan.setManagementCost(ArithmeticUtil.round(managementCost,2));
				
				r = r.subtract(curCaptial);
				bizRepaymentPlan.setCapital(curCaptial);
				bizRepaymentPlan.setRemainingCapital(r);
			}
			
			in = in.subtract(ins);
			
			
			resultList.add(bizRepaymentPlan);
		}
		
		return resultList;
	}

	/**
	 * 收款计划,新手标，按日计息
	 */
	@Override
	public List<BizReceiptPlan> execReceivablesCalcByDate() throws Exception {
		// 检查执行
		execCheck();
		
		BizReceiptPlan bizReceiptPlan = null;
		List<BizReceiptPlan> resultList = new ArrayList<BizReceiptPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		BigDecimal capital = calculationDto.getRepaymentAmt(); // 投资金额
		BigDecimal manageFee = calculationDto.getInterestRate(); // 管理费率
		manageFee = manageFee == null ? BigDecimal.ZERO : manageFee;
		Integer deadline = calculationDto.getRepaymentDeadline();
		BigDecimal yearRate = calculationDto.getRepaymentRate();
		
		BigDecimal benXi = AmountUtil.getCapitalAndInterestByDate(deadline, capital,yearRate);
		BigDecimal liXi = AmountUtil.getInterestByDate(deadline, capital, yearRate);
		
		BigDecimal r = capital;
		BigDecimal in = liXi;
		for(int i=1;i<deadline+1;i++){
			bizReceiptPlan = new BizReceiptPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,1);
			bizReceiptPlan.setExpireTime(repaymentDate);
			
			// 设置还款期限
			bizReceiptPlan.setPlanIndex(i+"");
			
			//利息
			BigDecimal ins = r.multiply(AmountUtil.getDateRate(yearRate)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//本金
			BigDecimal curCaptial = benXi.subtract(ins);
			
			if(i == deadline){
				// 最后一期
				bizReceiptPlan.setNetInterest(in);
				BigDecimal managementCost = in.multiply(manageFee);
				bizReceiptPlan.setManagementCost(ArithmeticUtil.round(managementCost,2));
				bizReceiptPlan.setReceivableInterest(ArithmeticUtil.round(in.subtract(managementCost),2));
				
				bizReceiptPlan.setCapital(r);
				bizReceiptPlan.setRemainingCapital(BigDecimal.ZERO);
				
			}else{
				bizReceiptPlan.setNetInterest(ins);
				BigDecimal managementCost = ins.multiply(manageFee);
				bizReceiptPlan.setManagementCost(ArithmeticUtil.round(managementCost,2));
				bizReceiptPlan.setReceivableInterest(ArithmeticUtil.round(ins.subtract(managementCost),2));
				
				r = r.subtract(curCaptial);
				bizReceiptPlan.setCapital(curCaptial);
				bizReceiptPlan.setRemainingCapital(r);
			}
			
			in = in.subtract(ins);
			
			
			// 判断是否使用加息劵，使用了的话就计算加息
//			if(null!=calculationDto.getAddInterestRate() &&  calculationDto.getAddInterestRate().compareTo(BigDecimal.ZERO) > 0){
//				// TODO 加息待做
//				
//			}
			bizReceiptPlan.setInterest(bizReceiptPlan.getNetInterest());
			
			resultList.add(bizReceiptPlan);
			
		}
		
		return resultList;
	}


}


