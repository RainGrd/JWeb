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
package com.yscf.core.service.calculation;

import java.math.BigDecimal;
import java.util.List;

import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;

/**
 * Description：<br> 
 * 借款试算接口
 * @author  Yu.Zhang
 * @date    2015年11月11日
 * @version v1.0.0
 */
public interface ICalculation {
	
	/**
	 * 
	 * Description：<br> 
	 * 执行还款试算
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception 
	 */
	public List<BizRepaymentPlan> execRepaymentCalc() throws Exception;
	
	
	/**
	 * 
	 * Description：<br> 
	 * 执行收款试算
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception 
	 */
	public List<BizReceiptPlan> execReceivablesCalc() throws Exception;
	
	/**
	 * 
	 * Description：<br> 
	 * 获取投资奖励金额 投资奖励=本金*投资奖励比例
	 * @author  Yu.Zhang
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getInvestmentReward();
	
	/**
	 * 
	 * Description：<br> 
	 * 获取应收/应还 利息
	 * @author  Yu.Zhang
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getSumInterest();

	
	/**
	 * 
	 * Description：<br> 
	 * 执行还款，针对新手标体验标，按日为期限的试算
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception 
	 */
	public List<BizRepaymentPlan> execRepaymentCalcByDate() throws Exception;
	
	/**
	 * 
	 * Description：<br> 
	 * 执行收款试算，针对新手标体验标，按日为期限的试算
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @throws Exception 
	 */
	public List<BizReceiptPlan> execReceivablesCalcByDate() throws Exception;
	
	/**
	 * 
	 * Description：<br> 
	 * 通过投标金额得到投标奖励
	 * @author  Jie.Zou
	 * @date    2016年2月3日
	 * @version v1.0.0
	 * @param investmentAmount
	 * @return
	 */
	public BigDecimal getInvestmentReward(BigDecimal investmentAmount);
}


