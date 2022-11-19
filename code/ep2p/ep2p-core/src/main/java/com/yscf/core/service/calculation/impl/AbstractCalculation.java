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

import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.service.calculation.ICalculation;

/**
 * Description：<br> 
 * 还款计划表计算父类
 * @author  Yu.Zhang
 * @date    2015年11月11日
 * @version v1.0.0
 */
public abstract class AbstractCalculation implements ICalculation{
	
	/**
	 * 试算DTO	
	 */
	protected CalculationDto calculationDto ;
	
	/**
	 * 
	 * Description：<br> 
	 * 计算前，执行检查
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception
	 */
	protected void execCheck() throws Exception{
		// 1 判断计算对象是否为空
		if(null == calculationDto){
			throw new Exception("计算对象为空，请检查！");
		}
		
		// 2 判断投资金额是否为0
		if(null == calculationDto.getRepaymentAmt() || calculationDto.getRepaymentAmt().compareTo(new BigDecimal("0")) == 0 ){
			throw new Exception("计算投资金额为0，请检查！");
		}
		
		// 3 判断投资利率是否为0
		if(null == calculationDto.getRepaymentRate() || calculationDto.getRepaymentRate().compareTo(new BigDecimal("0")) == 0 ){
			throw new Exception("计算投资金额利率为0，请检查！");
		}
		
		// 4 判断投资期限是否为0
		if(null == calculationDto.getRepaymentDeadline() || calculationDto.getRepaymentDeadline() == 0 ){
			throw new Exception("计算投资投资期为0，请检查！");
		}
	}
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取应付 利息or管理费<br><br>
	 * 
	 * 应付 利息or管理费 计算方式： 本金*年化率or管理费率/12 * 还款期限 <br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 每个月应付 利息or管理费
	 */
	protected BigDecimal getSumInterest(BigDecimal capital,BigDecimal rate,Integer deadlin){
		
		// 月利息
		BigDecimal monthInterest = getMonthInterest(capital,rate);
		
		// 总利息 ：  月利息*还款期限
		return ArithmeticUtil.round(ArithmeticUtil.mul(monthInterest,new BigDecimal(deadlin)),4);
//		return ArithmeticUtil.round(ArithmeticUtil.mul(monthInterest,deadlin),2);
		
	}
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取应收总利息管理费<br><br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 应收总利息管理费
	 */
	protected BigDecimal getSumInterestManager(BigDecimal capital,BigDecimal rate,Integer deadlin){
		
		// 月利息管理费
		BigDecimal monthInterest = getMonthInterestManager(capital,rate);
		
		// 总利息 ：  月利息管理费*还款期限
		return ArithmeticUtil.round(ArithmeticUtil.mul(monthInterest,new BigDecimal(deadlin)),4);
	}
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取应付 利息or管理费<br><br>
	 * 
	 *  应付 利息or管理费 计算方式： 本金*年化率or管理费率/12 * 还款期限 <br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 每个月应付 利息or管理费
	 */
	protected BigDecimal getSumInterestDay(BigDecimal capital,BigDecimal rate,Integer deadlin){
		
		// 日利息
		BigDecimal monthInterest = getMonthInterestDay(capital,rate);
		
		// 总利息 ：  日利息*还款期限
		return ArithmeticUtil.mul(monthInterest,new BigDecimal(deadlin));
		
	}
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取应收加息劵利息<br><br>
	 * 
	 * 应收加息劵利息计算方式： 本金*加息劵率  <br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 应收/应付 利息or管理费
	 */
	public static BigDecimal getNetHike(BigDecimal capital,BigDecimal rate){
		
		BigDecimal yearRate = ArithmeticUtil.div(rate,new BigDecimal(100));
		
		return ArithmeticUtil.mul(capital,yearRate);
	}
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取每个月应收加息劵利息<br><br>
	 * 
	 * 每个月应收收加息劵利息计算方式： 本金*加息劵率/还款期限 <br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 获取每个月应收加息劵利息
	 */
	protected BigDecimal getMonthNetHike(BigDecimal capital,BigDecimal rate,Integer deadlin){
		
		BigDecimal yearRate = ArithmeticUtil.div(rate,new BigDecimal(100));
		
		return ArithmeticUtil.div(ArithmeticUtil.mul(capital,yearRate),new BigDecimal(deadlin),4);
	}
	
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取每个月应收/应付 利息<br><br>
	 * 
	 * 每个月应收/应付 利息计算方式： 本金*年化率/12 <br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 应收/应付 利息
	 */
	protected BigDecimal getMonthInterest(BigDecimal capital,BigDecimal rate){
		// 年化率 ：  年化率/100
		BigDecimal yearRate = ArithmeticUtil.div(rate,new BigDecimal(100));
		
		// 月利息 ：  本金*年化率/12
		return ArithmeticUtil.div(ArithmeticUtil.mul(capital,yearRate),new BigDecimal(12));
		
	}
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取每个月应收 利息管理费<br><br>
	 * 
	 * 每个月应收 利息管理费 计算方式： 每个月应收利息*利息管理费率 <br>
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 应收 利息or管理费
	 */
	protected BigDecimal getMonthInterestManager(BigDecimal capital,BigDecimal rate){
		// 年化率 ：  年化率/100
		BigDecimal yearRate = ArithmeticUtil.div(rate,new BigDecimal(100));
		
		// 月利息 ：  本金*年化率/12
		return ArithmeticUtil.mul(capital,yearRate);
		
	}
	
	/**
	 * 
	 * Description：<br> <br>
	 * 
	 * 获取每天应收/应付 利息or管理费<br><br>
	 * 
	 * 每天应收/应付 利息or管理费 计算方式： 本金*年化率or管理费率/12 <br>
	 * 
	 * @author  JunJie.Liu
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @return BigDecimal 应收/应付 利息or管理费
	 */
	public static BigDecimal getMonthInterestDay(BigDecimal capital,BigDecimal rate){
		// 日利息 ：  本金*年化率/360
		return ArithmeticUtil.div(ArithmeticUtil.mul(capital,rate),new BigDecimal(360));
		
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 设置还款信息
	 * @author  Yu.Zhang
	 * @date    2016年2月16日
	 * @version v1.0.0
	 * @param bizRepaymentPlan
	 */
	protected void setRepaymentPlan(BizRepaymentPlan bizRepaymentPlan){
		bizRepaymentPlan.setBorrowId(calculationDto.getBorrowId());
		bizRepaymentPlan.setInterestType(calculationDto.getInterestType());
		bizRepaymentPlan.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_1);
		bizRepaymentPlan.setCreateUser(calculationDto.getCreateUser());
		bizRepaymentPlan.setCreateTime(DateUtil.getSystemDate());
		bizRepaymentPlan.setStatus(Constant.ACTIVATE);
		bizRepaymentPlan.setCustomerId(calculationDto.getCustomerId());
		
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 设置收款款信息
	 * @author  Yu.Zhang
	 * @date    2016年2月16日
	 * @version v1.0.0
	 * @param bizRepaymentPlan
	 */
	protected void setReceiptPlan(BizReceiptPlan bizReceiptPlan){
		bizReceiptPlan.setBorrowId(calculationDto.getBorrowId());
		bizReceiptPlan.setCreateUser(calculationDto.getCreateUser());
		bizReceiptPlan.setInterestType(calculationDto.getInterestType());
		bizReceiptPlan.setCustomerId(calculationDto.getCustomerId());
		bizReceiptPlan.setCreateTime(DateUtil.getSystemDate());
		bizReceiptPlan.setStatus(Constant.ACTIVATE);
		bizReceiptPlan.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_1);
		
	}

	@Override
	public BigDecimal getInvestmentReward() {
		if(null!=calculationDto.getRewardRate()){
			return  ArithmeticUtil.round(ArithmeticUtil.div(ArithmeticUtil.mul(calculationDto.getRepaymentAmt(),calculationDto.getRewardRate()),new BigDecimal(100)),2);
		}
		return new BigDecimal(0.0);
	}


	@Override
	public BigDecimal getSumInterest() {
		// 获取应收利息
		BigDecimal netInterest = this.getSumInterest(calculationDto.getRepaymentAmt(), calculationDto.getRepaymentRate(), calculationDto.getRepaymentDeadline());
		BigDecimal netHike = new BigDecimal(0.0);
		// 判断是否使用加息劵，使用了的话就计算加息
		if(null!=calculationDto.getAddInterest() && 0 != calculationDto.getAddInterest().compareTo(new BigDecimal(0.0))){
			// 加息劵利息算法与利息算法一致
			netHike = this.getSumInterest(calculationDto.getRepaymentAmt(),calculationDto.getAddInterest(),calculationDto.getRepaymentDeadline());
		}
		return ArithmeticUtil.round(ArithmeticUtil.add(netInterest,netHike),2);
	}

	@Override
	public BigDecimal getInvestmentReward(BigDecimal investmentAmount) {
		if(null!=calculationDto.getRewardRate()){
			return  ArithmeticUtil.round(ArithmeticUtil.div(ArithmeticUtil.mul(investmentAmount,calculationDto.getRewardRate()),new BigDecimal(100)),2);
		}
		return new BigDecimal(0.0);
	}
	
	
	
	/**
	 * 
	 * Description：<br> 
	 * 平均分配 总加息收益
	 * @author  Yu.Zhang
	 * @date    2016年3月11日
	 * @version v1.0.0
	 * @param deline
	 * @return
	 */
	public BigDecimal averageSumAddInterest(Integer deline){
		return calculationDto.getSumAddInterest().divide(new BigDecimal(deline)).setScale(4);
	}
	
}


