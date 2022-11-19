/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体验标的VO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月5日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.myinvest;

/**
 * Description：<br> 
 * 体验标的VO
 * @author  Jie.Zou
 * @date    2016年1月5日
 * @version v1.0.0
 */
public class ExperienceBorrowApiVo {
	
	//用户Id
	private String userId;
	//借款Id
	private String borrowId;
	// 金额
	private String amount;
	// 交易密码
	private String tradePwd;  
	//体验金卷
	private String[] epces;
	//交易密码key
	private String tranPKey;
	
	public String getUserId() {
		return userId;
	}
	public String getTranPKey() {
		return tranPKey;
	}
	public void setTranPKey(String tranPKey) {
		this.tranPKey = tranPKey;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTradePwd() {
		return tradePwd;
	}
	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}
	public String[] getEpces() {
		return epces;
	}
	public void setEpces(String[] epces) {
		this.epces = epces;
	}  
}


