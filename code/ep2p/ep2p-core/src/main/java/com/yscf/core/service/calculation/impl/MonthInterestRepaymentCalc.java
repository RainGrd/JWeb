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
 * 2015年11月11日     Yu.Zhang		Initial Version.
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

/**
 * Description：<br> 
 * 按月付息到期还本计算
 * @author  Yu.Zhang
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class MonthInterestRepaymentCalc extends AbstractCalculation implements ICalculation{
	
	public MonthInterestRepaymentCalc(CalculationDto dto){
		super.calculationDto = dto;
	}

	/**
	 * 
	 * Description：<br> 
		 按月付息，到期还本即为先息后本。根据计息方式计算出计息开始日，根据投资金额和年利率计算出每期（每月）的月利息，最后根据计息开始日期、月利息、借款期限生成还计划。<br>
		 满标计息时，计息开始日期为满标处理日。<br>
		 投资人，先按月回利息，到期一次性回全部本金。<br>
		
		 示例：借款人<br>
		 客户借款金额：120000<br>
		 借款期限：12个月<br>
		 借款利率：年化率12%(月1%，1分)，<br>
		 管理费率：1.6%<br>
		 每月利息：本金*年化率/12=14400<br>
		 每月管理费：本金*管理费率/12=14400<br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception 
	 */
	@Override
	public List<BizRepaymentPlan> execRepaymentCalc() throws Exception {
		// 检查执行
		execCheck();
		
		BizRepaymentPlan repaymentPlan = null;
		List<BizRepaymentPlan> resultList = new ArrayList<BizRepaymentPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		int day = EscfDateUtil.getDay(repaymentDate);
		
		BigDecimal zero = new BigDecimal(0);
		
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			repaymentPlan = new BizRepaymentPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastMonth(repaymentDate,day);
			repaymentPlan.setExpireTime(repaymentDate);
			repaymentPlan.setPlanindex((i+1)+"");
			
			// 计算每个月应收利息
			repaymentPlan.setInterest(ArithmeticUtil.round(super.getMonthInterest(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate()), 4));
			// 计算每个月应收管理费
			repaymentPlan.setManagementCost(ArithmeticUtil.round(super.getMonthInterest(calculationDto.getRepaymentAmt(),calculationDto.getManagementRate()), 4));
			
			//按月付息，到期还本剩余本金 如果不是最后一期就是借款金额，最后一期剩余本金则本金为0
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				repaymentPlan.setRemainingCapital(zero);
				repaymentPlan.setCapital(ArithmeticUtil.round(calculationDto.getRepaymentAmt(), 4));
			}else{
				repaymentPlan.setRemainingCapital(ArithmeticUtil.round(calculationDto.getRepaymentAmt(), 4));
				repaymentPlan.setCapital(zero);
			}
			// 设置还款信息
			super.setRepaymentPlan(repaymentPlan);
			resultList.add(repaymentPlan);
		}
		
		return resultList;
	}

	/**
	 * 
	 * Description：<br> 
		 按月付息，到期还本即为先息后本。根据计息方式计算出计息开始日，根据投资金额和年利率计算出每期（每月）的月利息，最后根据计息开始日期、月利息、借款期限生成还计划。<br>
		 满标计息时，计息开始日期为满标处理日。<br>
		 投资人，先按月回利息，到期一次性回全部本金。<br>
		
		 示例：借款人<br>
		 客户借款金额：120000<br>
		 借款期限：12个月<br>
		 借款利率：年化率12%(月1%，1分)，<br>
		 管理费率：1.6%<br>
		 每月利息：本金*年化率/12=14400<br>
		 每月管理费：本金*管理费率/12=14400<br>
		 
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception 
	 */
	@Override
	public List<BizReceiptPlan> execReceivablesCalc() throws Exception {
		// 检查执行
		execCheck();
		
		BizReceiptPlan bizReceiptPlan = null;
		List<BizReceiptPlan> resultList = new ArrayList<BizReceiptPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		int day = EscfDateUtil.getDay(repaymentDate);
		BigDecimal zero = new BigDecimal(0.0);
		BigDecimal netHike = zero ;												// 加息劵获得利息
		BigDecimal netHikeManager = zero ;										// 加息劵获得利息管理费
		BigDecimal interestManager = zero ;										// 利息管理费	
		BigDecimal netInterest  = zero ;										// 应收利息
		
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			bizReceiptPlan = new BizReceiptPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastMonth(repaymentDate,day);
			bizReceiptPlan.setExpireTime(repaymentDate);
			
			bizReceiptPlan.setPlanIndex((i+1)+"");
			
			// 计算每个月应收利息
			netInterest = super.getMonthInterest(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate());
			bizReceiptPlan.setNetInterest(ArithmeticUtil.round(netInterest, 2));
			
			// 计算每个月应收的利息管理费
			if(null!=calculationDto.getInterestRate()){
				interestManager = super.getMonthInterestManager(netInterest,calculationDto.getInterestRate());
			}
			// 设置利息管理费
			bizReceiptPlan.setManagementCost(ArithmeticUtil.round(interestManager,2));
			// 设置净收利息= 利息-利息管理费
			bizReceiptPlan.setReceivableInterest(ArithmeticUtil.round(ArithmeticUtil.sub(bizReceiptPlan.getNetInterest(),bizReceiptPlan.getManagementCost()),2));
			// 判断是否使用加息劵，使用了的话就计算加息
			if(null!=calculationDto.getAddInterest() && 0 != calculationDto.getAddInterest().compareTo(new BigDecimal(0.0))){
				// 加息劵利息
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
				bizReceiptPlan.setReceivableHike(ArithmeticUtil.round(ArithmeticUtil.sub(bizReceiptPlan.getNetHike(),bizReceiptPlan.getIncreaseInterest()),2));
			}else{
				bizReceiptPlan.setReceivableHike(zero);
			}
			
			// 利息 = 净收利息 + 净收加息劵利息
			bizReceiptPlan.setInterest(ArithmeticUtil.round(ArithmeticUtil.add(bizReceiptPlan.getReceivableInterest(), bizReceiptPlan.getReceivableHike()), 2));
			
			// 计算剩余本金 到期还本付息剩余本金 如果不是最后一期就是借款金额，最后一期则本金为0  最后一期 应还利息、应还管理费为所有月份的总和
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				bizReceiptPlan.setRemainingCapital(zero);
				bizReceiptPlan.setCapital(calculationDto.getRepaymentAmt());
			}else{
				bizReceiptPlan.setRemainingCapital(calculationDto.getRepaymentAmt());
				bizReceiptPlan.setCapital(zero);
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
		
		BizRepaymentPlan repaymentPlan = null;
		List<BizRepaymentPlan> resultList = new ArrayList<BizRepaymentPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		
		BigDecimal zero = BigDecimal.ZERO;
		
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			repaymentPlan = new BizRepaymentPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,1);
			repaymentPlan.setExpireTime(repaymentDate);
			repaymentPlan.setPlanindex((i+1)+"");
			
			// 计算每个月应收利息
			repaymentPlan.setInterest(super.getMonthInterestDay(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate()));
			// 计算每个月应收管理费
			repaymentPlan.setManagementCost(super.getMonthInterestDay(calculationDto.getRepaymentAmt(),calculationDto.getManagementRate()));
			
			//按月付息，到期还本剩余本金 如果不是最后一期就是借款金额，最后一期剩余本金则本金为0
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				repaymentPlan.setRemainingCapital(zero);
				repaymentPlan.setCapital(calculationDto.getRepaymentAmt());
			}else{
				repaymentPlan.setRemainingCapital(calculationDto.getRepaymentAmt());
				repaymentPlan.setCapital(zero);
			}
			
			resultList.add(repaymentPlan);
		}
		
		return resultList;
	}

	@Override
	public List<BizReceiptPlan> execReceivablesCalcByDate() throws Exception {
		// 检查执行
		execCheck();
		
		BizReceiptPlan bizReceiptPlan = null;
		List<BizReceiptPlan> resultList = new ArrayList<BizReceiptPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		BigDecimal manageFee = calculationDto.getInterestRate() == null ? BigDecimal.ZERO : calculationDto.getInterestRate();
		
		BigDecimal zero = BigDecimal.ZERO;
		BigDecimal netInterest = null;	// 应还利息
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			bizReceiptPlan = new BizReceiptPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,1);
			bizReceiptPlan.setExpireTime(repaymentDate);
			
			bizReceiptPlan.setPlanIndex((i+1)+"");
			
			// 计算应收利息
			netInterest = super.getMonthInterestDay(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate());
			bizReceiptPlan.setNetInterest(ArithmeticUtil.round(netInterest, 2));
			
			// 计算应收的利息管理费
			bizReceiptPlan.setManagementCost(ArithmeticUtil.round(manageFee.multiply(netInterest),2));
			
			// 利息 = 应收利息 
			bizReceiptPlan.setInterest(ArithmeticUtil.round(netInterest,2));
			
			// 计算剩余本金 到期还本付息剩余本金 如果不是最后一期就是借款金额，最后一期则本金为0  最后一期 应还利息、应还管理费为所有月份的总和
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				bizReceiptPlan.setRemainingCapital(zero);
				bizReceiptPlan.setCapital(calculationDto.getRepaymentAmt());
			}else{
				bizReceiptPlan.setRemainingCapital(calculationDto.getRepaymentAmt());
				bizReceiptPlan.setCapital(zero);
			}
			
			resultList.add(bizReceiptPlan);
		}
		
		return resultList;
	}

}


