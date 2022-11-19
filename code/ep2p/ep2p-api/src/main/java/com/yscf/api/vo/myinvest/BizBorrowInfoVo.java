package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;

/**
 * 
 * Description：<br>
 * 标的详情VO
 * 
 * @author JunJie.Liu
 * @date 2016年1月4日
 * @version v1.0.0
 */
public class BizBorrowInfoVo {

	private String pid;

	/**
	 * 借款编号
	 */
	private String borrowCode;

	/**
	 * 借款名称
	 */
	private String borrowName;

	/**
	 * 借款利率
	 */
	private BigDecimal borrowRate;

	/**
	 * 借款金额
	 */
	private BigDecimal borrowMoney;

	/**
	 * 借款时间
	 */
	// private String borrowTime;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 借款类型
	 */
	private String borrowType;

	/**
	 * 借款合同ID
	 */
	private String borAgrId;

	/**
	 * 借款期限
	 */
	private String borDeadline;

	// 扩展字段
	private String repaymentTypeName;

	// 计息类型名称
	private String accrualTypeName;

	// 借款类型名称
	private String borrowTypeName;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public BigDecimal getBorrowRate() {
		return borrowRate;
	}

	public void setBorrowRate(BigDecimal borrowRate) {
		this.borrowRate = borrowRate;
	}

	public BigDecimal getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(BigDecimal borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public String getBorAgrId() {
		return borAgrId;
	}

	public void setBorAgrId(String borAgrId) {
		this.borAgrId = borAgrId;
	}

	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public String getAccrualTypeName() {
		return accrualTypeName;
	}

	public void setAccrualTypeName(String accrualTypeName) {
		this.accrualTypeName = accrualTypeName;
	}

	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

}