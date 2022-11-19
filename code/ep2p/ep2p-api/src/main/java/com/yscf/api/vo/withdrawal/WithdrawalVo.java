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
 * 2016年1月4日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.withdrawal;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 客户提现VO
 * @author  JingYu.Dai
 * @date    2016年1月4日
 * @version v1.0.0
 */
public class WithdrawalVo {

	/**
	 * 短信验证码
	 */
	private String verifyCode;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 银行卡ID
	 */
	private String bankId;
	/**
	 * 提现金额
	 */
	private BigDecimal money;
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	
}


