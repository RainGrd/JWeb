/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 线上充值VO类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.persionalcenter;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 线上充值VO类
 * @author  Jie.Zou
 * @date    2016年1月25日
 * @version v1.0.0
 */
public class RechargeOnlineVo {
	private String customerId;
	
	private BigDecimal amount;
	
	private String payconfigId;
	
	private String bankCard;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayconfigId() {
		return payconfigId;
	}

	public void setPayconfigId(String payconfigId) {
		this.payconfigId = payconfigId;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	
}


