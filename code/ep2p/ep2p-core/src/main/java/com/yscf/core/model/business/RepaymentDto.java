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
 * 2016年1月25日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.business;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 还款DTO类
 * @author  Yu.Zhang
 * @date    2016年1月25日
 * @version v1.0.0
 */
public class RepaymentDto {

	/**
	 * 借款ID
	 */
	private String borrowId;
	
	/**
	 * 借款编号
	 */
	private String borrowCode;
	
	/**
	 * 借款名称
	 */
	private String borrowName;
	
	/**
	 * 借款金额
	 */
	private BigDecimal borrowMoney;
	
	/**
	 * 借款时间
	 */
	private String borrowTime;
	
	/**
	 * 还款方式
	 */
	private String repaymentType;
	
	/**
	 * 还款方式名称
	 */
	private String repaymentTypeName;
	
	/**
	 * 借款期限
	 */
	private String borDeadline;
	
	/**
	 * 借款利率
	 */
	private BigDecimal borrowRate;
	
	/**
	 * 管理费率
	 */
	private BigDecimal manageExpenseRate;
	
	/**
	 * 本金
	 */
	private BigDecimal capital;
	
	/**
	 * 利息
	 */
	private BigDecimal interest;
	
	
	/**
	 * 管理费
	 */
	private BigDecimal managementCost;
	
	/**
	 * 逾期罚息
	 */
	private BigDecimal latefee;
	
	/**
	 * 提前还款罚息
	 */
	private BigDecimal prepaymentFee;
	
	/**
	 * 还款金额
	 */
	private BigDecimal paymentAmount;
	
	/**
	 * 提前还款少支付利息
	 */
	private BigDecimal prepaymentInterest;
	
	/**
	 * 提前还款少支付管理费
	 */
	private BigDecimal prepaymentManage;
	
	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	public BigDecimal getPrepaymentManage() {
		return prepaymentManage;
	}

	public void setPrepaymentManage(BigDecimal prepaymentManage) {
		this.prepaymentManage = prepaymentManage;
	}

	public BigDecimal getBorrowRate() {
		return borrowRate;
	}

	public void setBorrowRate(BigDecimal borrowRate) {
		this.borrowRate = borrowRate;
	}

	public BigDecimal getManageExpenseRate() {
		return manageExpenseRate;
	}

	public void setManageExpenseRate(BigDecimal manageExpenseRate) {
		this.manageExpenseRate = manageExpenseRate;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowCode() {
		return borrowCode;
	}

	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(BigDecimal borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getLatefee() {
		return latefee;
	}

	public void setLatefee(BigDecimal latefee) {
		this.latefee = latefee;
	}

	public BigDecimal getPrepaymentFee() {
		return prepaymentFee;
	}

	public void setPrepaymentFee(BigDecimal prepaymentFee) {
		this.prepaymentFee = prepaymentFee;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public BigDecimal getPrepaymentInterest() {
		return prepaymentInterest;
	}

	public void setPrepaymentInterest(BigDecimal prepaymentInterest) {
		this.prepaymentInterest = prepaymentInterest;
	}

	@Override
	public String toString() {
		return "RepaymentDto [borrowId=" + borrowId + ", borrowCode="
				+ borrowCode + ", borrowName=" + borrowName + ", borrowMoney="
				+ borrowMoney + ", borrowTime=" + borrowTime
				+ ", repaymentType=" + repaymentType + ", repaymentTypeName="
				+ repaymentTypeName + ", borrowRate=" + borrowRate
				+ ", manageExpenseRate=" + manageExpenseRate + ", capital="
				+ capital + ", interest=" + interest + ", managementCost="
				+ managementCost + ", latefee=" + latefee + ", prepaymentFee="
				+ prepaymentFee + ", paymentAmount=" + paymentAmount
				+ ", prepaymentInterest=" + prepaymentInterest
				+ ", prepaymentManage=" + prepaymentManage + "]";
	}
	
}


