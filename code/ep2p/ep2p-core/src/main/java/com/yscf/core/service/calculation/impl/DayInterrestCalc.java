/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 按日计息，到期还本息的计算方式
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月4日     Jie.Zou		Initial Version.
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
 * 按日计息，到期还本息的计算方式
 * @author  Jie.Zou
 * @date    2016年1月4日
 * @version v1.0.0
 */
public class DayInterrestCalc extends AbstractCalculation implements
						ICalculation {

	public DayInterrestCalc(CalculationDto dto){
		super.calculationDto = dto;
	}
	
	@Override
	public List<BizRepaymentPlan> execRepaymentCalc() throws Exception {
		return null;
	}

	@Override
	public List<BizReceiptPlan> execReceivablesCalc() throws Exception {
		return null;
	}

	@Override
	public List<BizRepaymentPlan> execRepaymentCalcByDate() throws Exception {
		// 检查执行
		execCheck();
		
		BizRepaymentPlan repaymentPlan = null;
		List<BizRepaymentPlan> resultList = new ArrayList<BizRepaymentPlan>();
		// 获取计息日期
		String repaymentDate = calculationDto.getInterestDate();
		repaymentPlan = new BizRepaymentPlan();
		// 计算收款日期
		repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,1);
		repaymentPlan.setExpireTime(repaymentDate);
		repaymentPlan.setPlanindex("1");
		
		repaymentPlan.setInterest(ArithmeticUtil.round(super.getSumInterestDay(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate(),calculationDto.getRepaymentDeadline()), 2));
		repaymentPlan.setRemainingCapital(BigDecimal.ZERO);
		repaymentPlan.setCapital(calculationDto.getRepaymentAmt());
		resultList.add(repaymentPlan);
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
		BigDecimal zero = new BigDecimal(0.0);
		BigDecimal netInterest = null;	// 应还利息
		BigDecimal netHike = zero ;		
		
		bizReceiptPlan = new BizReceiptPlan();
		// 计算收款日期
		repaymentDate = EscfDateUtil.getDateOfLastDay(repaymentDate,1);
		bizReceiptPlan.setExpireTime(repaymentDate);
		
		// 设置还款期数
		bizReceiptPlan.setPlanIndex("1");
		
		// 计算应收利息
		netInterest = super.getSumInterestDay(calculationDto.getRepaymentAmt(),calculationDto.getRepaymentRate(),calculationDto.getRepaymentDeadline());
		bizReceiptPlan.setNetInterest(ArithmeticUtil.round(netInterest, 2));
		
		// 计算应收的利息管理费
		if(null!=calculationDto.getInterestRate()){
			bizReceiptPlan.setReceivableInterest(ArithmeticUtil.round(netInterest.subtract(super.getSumInterestDay(netInterest,calculationDto.getInterestRate(),calculationDto.getRepaymentDeadline())),2));
		}
		
		// 利息 = 应收利息 + 加息劵利息
		bizReceiptPlan.setInterest(ArithmeticUtil.round(ArithmeticUtil.add(netInterest, netHike), 2));
		bizReceiptPlan.setRemainingCapital(zero);
		bizReceiptPlan.setCapital(calculationDto.getRepaymentAmt());
		
		resultList.add(bizReceiptPlan);
		return resultList;
	}
}


