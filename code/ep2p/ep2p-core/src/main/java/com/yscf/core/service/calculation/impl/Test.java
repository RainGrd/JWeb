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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.yscf.common.util.ArithmeticUtil;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class Test {
	public static void main(String[] args) {
/*		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		//获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        System.out.println(ca.get(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:"+last);*/
		
		
		CalculationDto dto = new CalculationDto();
		dto.setRepaymentType(1);
		dto.setInterestDate("2015-12-15");
		dto.setManagementRate(new BigDecimal(1.2));
		dto.setRepaymentAmt(new BigDecimal(10000.0));
		dto.setRepaymentRate(new BigDecimal(12.0));
		dto.setInterestRate(new BigDecimal(10.0));
		dto.setAddInterest(new BigDecimal(10.0));
		dto.setAddInterestRate(new BigDecimal(0.2));
		dto.setRepaymentDeadline(24);
		dto.setInterestType("1");
//		dto.setRewardRate(1.0);
		ICalculation cal = CalculationFactory.getCalculation(dto);
		
		System.out.println("所得利息："+cal.getSumInterest());
		System.out.println("投资奖励："+cal.getInvestmentReward());
		try {
//			List<BizRepaymentPlan> list = cal.execRepaymentCalc();
			List<BizReceiptPlan> list = cal.execReceivablesCalc();
//			for(BizRepaymentPlan plan : list){
			for(BizReceiptPlan plan : list){
				System.out.println("rece_hike:"+plan.getReceivableHike()+",net_hike:"+plan.getNetHike()+",increa:"+plan.getIncreaseInterest());
				System.out.println("还款期限："+plan.getPlanIndex()+",收款日期："+plan.getExpireTime()+",应收本息，"+ArithmeticUtil.add(plan.getCapital(),plan.getInterest())+",应收本金:"+plan.getCapital()+",利息:"+plan.getInterest()+",应收利息："+plan.getNetInterest()+",应收净利息："+plan.getReceivableInterest()+",应收利息管理费："+plan.getManagementCost()+",应收劵利息"+plan.getNetHike()+",应收净加息劵利息"+plan.getReceivableHike()+",应收加息劵利息管理费"+plan.getIncreaseInterest()+",剩余本金："+plan.getRemainingCapital());
//				System.out.println("还款期限："+plan.getPlanindex()+",收款日期："+plan.getExpireTime()+",应收本息，"+ArithmeticUtil.add(plan.getCapital(),plan.getInterest())+",应收本金:"+plan.getCapital()+",利息:"+plan.getInterest()+",应收利息管理费："+plan.getManagementCost()+",应收劵利息"+",剩余本金："+plan.getRemainingCapital());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


