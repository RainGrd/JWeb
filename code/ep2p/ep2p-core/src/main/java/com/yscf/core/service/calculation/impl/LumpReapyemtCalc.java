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
import com.yscf.core.util.AmountUtil;

/**
 * Description：<br> 
 * 到期一次性还款计算 (到期还本付息) 
 * @author  Yu.Zhang
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class LumpReapyemtCalc extends AbstractCalculation implements ICalculation{
	
	public LumpReapyemtCalc(CalculationDto dto){
		super.calculationDto = dto;
	}

	/**
	 * 
	 * Description：<br> 
	 * 到期一次性还款 (到期还本付息) 执行试算<br>
	 * 到期还一次性还本息即为到期还一次性还本息。根据计息方式计算出计息开始日，根据借款金额和年利率计算出每期（每月）的月利息，最后根据计息开始日期、月利息、借款期限生成还计划。<br>
	 * 示例：<br>
		客户借款金额：120000<br>
		借款期限：12个月<br>
		借款利率：年化率12%(月1%，1分)， <br>
		管理费率 :12%<br>
		月应还利息：本金*年化率/12=14400<br>
		月应付管理费：本金*管理费率/12=14400<br>
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
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			repaymentPlan = new BizRepaymentPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastMonth(repaymentDate,day);
			repaymentPlan.setExpireTime(repaymentDate);
			
			// 设置还款期数
			repaymentPlan.setPlanindex((i+1)+"");
			
			// 计算剩余本金 到期还本付息剩余本金 如果不是最后一期就是借款金额，最后一期则本金为0  最后一期 应还利息、应还管理费为所有月份的总和
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				repaymentPlan.setInterest(super.getSumInterest(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate(),calculationDto.getRepaymentDeadline()));
				repaymentPlan.setManagementCost(super.getSumInterestManager(calculationDto.getRepaymentAmt(),calculationDto.getManagementRate(),calculationDto.getRepaymentDeadline()));
				repaymentPlan.setRemainingCapital(BigDecimal.ZERO);
				repaymentPlan.setCapital(calculationDto.getRepaymentAmt());
			}else{
				repaymentPlan.setRemainingCapital(calculationDto.getRepaymentAmt());
				repaymentPlan.setCapital(BigDecimal.ZERO);
				repaymentPlan.setManagementCost(BigDecimal.ZERO);
				repaymentPlan.setInterest(BigDecimal.ZERO);
			}
			// 设置还款信息
		    super.setRepaymentPlan(repaymentPlan);
			resultList.add(repaymentPlan);
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
		BigDecimal netInterest = null;	// 应还利息
		BigDecimal netHike = zero ;		
		BigDecimal interestManager = zero ;	
		BigDecimal netHikeManager = zero ;	
		for(int i = 0 ;i < calculationDto.getRepaymentDeadline() ; i ++ ){
			bizReceiptPlan = new BizReceiptPlan();
			// 计算收款日期
			repaymentDate = EscfDateUtil.getDateOfLastMonth(repaymentDate,day);
			bizReceiptPlan.setExpireTime(repaymentDate);
			
			// 设置还款期数
			bizReceiptPlan.setPlanIndex((i+1)+"");
			
			// 判断是否使用加息劵，使用了的话就计算加息
			if(null!=calculationDto.getAddInterest() && 0 != calculationDto.getAddInterest().compareTo(new BigDecimal(0.0))){
				// 加息劵利息
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
			
			// 计算剩余本金 到期还本付息剩余本金 如果不是最后一期就是借款金额，最后一期则本金为0  最后一期 应还利息、应还管理费为所有月份的总和
			if(i == (calculationDto.getRepaymentDeadline()-1)){
				// 计算应收利息
				netInterest = super.getSumInterest(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate(),calculationDto.getRepaymentDeadline());
				bizReceiptPlan.setNetInterest(ArithmeticUtil.round(netInterest, 2));
				
				// 计算应收利息管理费
				if(null!=calculationDto.getInterestRate()){
					interestManager = super.getSumInterest(netInterest,calculationDto.getInterestRate(),calculationDto.getRepaymentDeadline());
				}
				// 设置利息管理费
				bizReceiptPlan.setManagementCost(ArithmeticUtil.round(interestManager,2));
				// 设置净收利息= 利息-利息管理费
				bizReceiptPlan.setReceivableInterest(ArithmeticUtil.round(ArithmeticUtil.sub(bizReceiptPlan.getNetInterest(), bizReceiptPlan.getManagementCost()),2));

				// 利息 = 净收利息 + 净收加息劵利息
				bizReceiptPlan.setInterest(ArithmeticUtil.round(ArithmeticUtil.add(bizReceiptPlan.getReceivableInterest(), bizReceiptPlan.getReceivableHike()), 2));
				
				bizReceiptPlan.setRemainingCapital(zero);
				bizReceiptPlan.setCapital(calculationDto.getRepaymentAmt());
			}else{
				bizReceiptPlan.setRemainingCapital(calculationDto.getRepaymentAmt());
				bizReceiptPlan.setCapital(zero);
				bizReceiptPlan.setManagementCost(zero);
				bizReceiptPlan.setInterest(zero);
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
		
		BizRepaymentPlan repaymentPlan = new BizRepaymentPlan();;
		List<BizRepaymentPlan> resultList = new ArrayList<BizRepaymentPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		BigDecimal zero = BigDecimal.ZERO;
		BigDecimal netInterest = AmountUtil.getAccrual(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate(),calculationDto.getRepaymentDeadline());	// 应还利息
		BigDecimal manageFee = calculationDto.getInterestRate() == null ? BigDecimal.ZERO : calculationDto.getInterestRate();
		// 计算收款日期
		repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,calculationDto.getRepaymentDeadline());
		
		// 设置还款信息
		repaymentPlan.setPlanindex(calculationDto.getRepaymentDeadline()+"");
		repaymentPlan.setExpireTime(repaymentDate);
		repaymentPlan.setInterest(ArithmeticUtil.round(netInterest,2));
		repaymentPlan.setManagementCost(ArithmeticUtil.round(calculationDto.getRepaymentAmt().multiply(manageFee).multiply(new BigDecimal(calculationDto.getRepaymentDeadline())),2));
		repaymentPlan.setRemainingCapital(zero);
		repaymentPlan.setCapital(calculationDto.getRepaymentAmt());
		
		resultList.add(repaymentPlan);
		
		return resultList;
	}

	@Override
	public List<BizReceiptPlan> execReceivablesCalcByDate() throws Exception {
		// 检查执行
		execCheck();
		
		BizReceiptPlan bizReceiptPlan = new BizReceiptPlan();
		List<BizReceiptPlan> resultList = new ArrayList<BizReceiptPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		BigDecimal zero = BigDecimal.ZERO;
		BigDecimal manageFee = calculationDto.getManagementRate() == null ? zero : calculationDto.getManagementRate();
		BigDecimal netInterest = zero;	// 应还利息
		// 计算收款日期
		repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,calculationDto.getRepaymentDeadline());
		// 设置还款期数
		netInterest =  AmountUtil.getAccrual(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate(),calculationDto.getRepaymentDeadline()); 
		// 计算剩余本金 到期还本付息剩余本金 如果不是最后一期就是借款金额，最后一期则本金为0  最后一期 应还利息、应还管理费为所有月份的总和
		bizReceiptPlan.setPlanIndex(calculationDto.getRepaymentDeadline()+"");
		bizReceiptPlan.setExpireTime(repaymentDate);
		// 计算应收利息
		bizReceiptPlan.setNetInterest(ArithmeticUtil.round(netInterest, 2));
		
		// 计算应收的利息管理费
		bizReceiptPlan.setManagementCost(ArithmeticUtil.round(netInterest.multiply(manageFee),2));
		
		// 利息 = 应收利息 
		bizReceiptPlan.setInterest(ArithmeticUtil.round(netInterest, 2));
		bizReceiptPlan.setRemainingCapital(zero);
		bizReceiptPlan.setCapital(calculationDto.getRepaymentAmt());
		
		resultList.add(bizReceiptPlan);
			
		return resultList;
	}

}


