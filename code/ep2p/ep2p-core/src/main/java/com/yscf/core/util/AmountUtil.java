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
 * 2015年12月31日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

import java.math.BigDecimal;

import freemarker.template.utility.NullArgumentException;

/**
 * Description：<br> 
 * 金额计算工具类
 * @author  JunJie.Liu
 * @date    2015年12月31日
 * @version v1.0.0
 */
public class AmountUtil {

	public static final int yearDate = 360;
	
	/**
	 * 
	 * Description：<br> 
	 * 根据年利率获取到日利率
	 * @author  JunJie.Liu
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param yearRate
	 * @return
	 */
	public static BigDecimal getDateRate(BigDecimal yearRate){
		if(yearRate == null){
			return BigDecimal.ZERO;
		}
		return yearRate.divide(new BigDecimal(AmountUtil.yearDate),10,BigDecimal.ROUND_DOWN);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取等额本息的还款总额
	 * @author  JunJie.Liu
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param deadline
	 * @param capital
	 * @param yearRate
	 * @return
	 */
	public static BigDecimal getTotalCapitalAndInterestByDate(Integer deadline,BigDecimal capital,BigDecimal yearRate){
		if(deadline == null){
			throw new NullArgumentException("期次为空");
		}
		if(deadline < 1){
			throw new NullArgumentException("期次小于1");
		}
		if(capital == null){
			throw new NullArgumentException("本金为空");
		}
		if(yearRate == null){
			throw new NullArgumentException("年利率为空");
		}
		
		BigDecimal r = BigDecimal.ZERO;
		// 日利率
		BigDecimal dr = AmountUtil.getDateRate(yearRate);
		// 本金 * 日利率
		r = capital.multiply(dr);
		// 1 + 日利率
		BigDecimal y = BigDecimal.ONE.add(dr);
		// (1+日利率)期限次方
		double d2 = Math.pow(y.doubleValue(), deadline);
		double d = d2 - 1;
		r = r.multiply(new BigDecimal(d2)).divide(new BigDecimal(d),2,BigDecimal.ROUND_HALF_DOWN).multiply(new BigDecimal(deadline));
		return r;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取等额本息的本息
	 * @author  JunJie.Liu
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param deadline
	 * @param capital
	 * @param yearRate
	 * @return
	 */
	public static BigDecimal getCapitalAndInterestByDate(Integer deadline,BigDecimal capital,BigDecimal yearRate){
		if(deadline == null){
			throw new NullArgumentException("期次为空");
		}
		if(deadline < 1){
			throw new NullArgumentException("期次小于1");
		}
		if(capital == null){
			throw new NullArgumentException("本金为空");
		}
		if(yearRate == null){
			throw new NullArgumentException("年利率为空");
		}
		
		BigDecimal r = BigDecimal.ZERO;
		// 日利率
		BigDecimal dr = AmountUtil.getDateRate(yearRate);
		// 本金 * 日利率
		r = capital.multiply(dr);
		// 1 + 日利率
		BigDecimal y = BigDecimal.ONE.add(dr);
		// (1+日利率)期限次方
		double d2 = Math.pow(y.doubleValue(), deadline);

		double d = d2 - 1;
		r = r.multiply(new BigDecimal(d2)).divide(new BigDecimal(d),2,BigDecimal.ROUND_HALF_DOWN);
		return r;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取等额本息当期的利息
	 * @author  JunJie.Liu
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param deadline
	 * @param capital
	 * @param yearRate
	 * @return
	 */
	public static BigDecimal getInterestByDate(Integer deadline,BigDecimal capital,BigDecimal yearRate){
		if(deadline == null){
			throw new NullArgumentException("期次为空");
		}
		if(deadline < 1){
			throw new NullArgumentException("期次小于1");
		}
		if(capital == null){
			throw new NullArgumentException("本金为空");
		}
		if(yearRate == null){
			throw new NullArgumentException("年利率为空");
		}
		
		BigDecimal r = BigDecimal.ZERO;
		// 日利率
		BigDecimal dr = AmountUtil.getDateRate(yearRate);
		// 期次 * 本金 * 日利率
		r = new BigDecimal(deadline).multiply(capital).multiply(dr);
		// 1 + 日利率
		BigDecimal y = BigDecimal.ONE.add(dr);
		// (1+日利率)期限次方
		double d2 = Math.pow(y.doubleValue(), deadline);
		double d = d2 - 1;
		
		
		r = r.multiply(new BigDecimal(d2)).divide(new BigDecimal(d),2,BigDecimal.ROUND_HALF_DOWN).subtract(capital);
		
		return r;
	}
	
	public static void main(String[] args) {
		int deadline = 3;
		BigDecimal capital = new BigDecimal(1000);
		BigDecimal yearRate = new BigDecimal(0.12);
		
		BigDecimal benXi = AmountUtil.getCapitalAndInterestByDate(deadline, capital,yearRate);
		BigDecimal liXi = AmountUtil.getInterestByDate(deadline, capital, new BigDecimal(0.12));
		
		System.out.println("月利率："+AmountUtil.getDateRate(yearRate));
		System.out.println("本息："+benXi);
		System.out.println("利息："+liXi);
		System.out.println("还款总额："+AmountUtil.getTotalCapitalAndInterestByDate(deadline, capital, yearRate));
	
		
		BigDecimal r = capital;
		BigDecimal in = liXi;
		for(int i=1;i<deadline+1;i++){
			//利息
			BigDecimal ins = r.multiply(AmountUtil.getDateRate(yearRate)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//本金
			BigDecimal curCaptial = benXi.subtract(ins);
			
			
			
			if(i == deadline){
				System.out.print("第"+i+"个月的利息："+in+"\t");
				System.out.println("第"+i+"个月的本金："+r);
			}else{
				System.out.print("第"+i+"个月的利息："+ins+"\t");
				r = r.subtract(benXi.subtract(ins));
				System.out.println("第"+i+"个月的本金："+curCaptial);
			}
			in = in.subtract(ins);
			
		}
	}
	
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取一次付息的利息
	 * @author  JunJie.Liu
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param amount
	 * @param yearRate
	 * @param deadline
	 * @return
	 */
	public static BigDecimal getAccrual(BigDecimal capital,BigDecimal yearRate,Integer deadline){
		if(deadline == null){
			throw new NullArgumentException("期次为空");
		}
		if(deadline < 1){
			throw new NullArgumentException("期次小于1");
		}
		if(capital == null){
			throw new NullArgumentException("本金为空");
		}
		if(yearRate == null){
			throw new NullArgumentException("年利率为空");
		}
		return capital.multiply(getDateRate(yearRate)).multiply(new BigDecimal(deadline));
	}
	
	
}


