package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Description：<br>
 * 收款计划
 * 
 * @author JunJie.Liu
 * @date 2015年11月11日
 * @version v1.0.0
 */
public class BizReceiptPlanModel {

	// 收款计划id
	private String pid;
	// 还款计划id
	private String repPlaPid;
	// 借款id
	private String borrowId;
	// 客户id
	private String customerId;
	// 到期时间
	private Date expireTime;
	// 编号
	private String borrowCode;
	// 借款名称
	private String borrowName;
	// 借款类型
	private String borrowType;
	// 期次
	private String planIndex;
	// 期限
	private String borDeadline;
	// 本息
	private BigDecimal principalAndInterest;
	// 本金
	private BigDecimal capital;
	// 利息
	private BigDecimal interest;
	// 加息
	private BigDecimal netHike;
	// 罚息
	private BigDecimal lateFee;
	// 逾期天数
	private Integer lateFeeDate;
	// 剩余回款天数
	private Integer returnedDate;
	// 状态
	private String status;
	//回款状态名称
	private String receiptStatusName;
	//回款状态
	private String receiptStatus;
	// 备注
	private String recPlaDesc;

	/* 查询条件 */
	// 待换开始时间（包含）
	private String startExpireTime;
	// 待换结束时间（包含）
	private String endExpireTime;

	// 转让金额
	private BigDecimal successAmount;

	// 年利率
	private BigDecimal yearRate;

	// 服务费率
	private BigDecimal serviceFeeRate;
	
	// 转让净收
	private BigDecimal netReceiptAmount;
	
	// 是否债权转让
	private String isEquitableAssignment;
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRepPlaPid() {
		return repPlaPid;
	}

	public void setRepPlaPid(String repPlaPid) {
		this.repPlaPid = repPlaPid;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
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

	public String getPlanIndex() {
		return planIndex;
	}

	public void setPlanIndex(String planIndex) {
		this.planIndex = planIndex;
	}

	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	public BigDecimal getPrincipalAndInterest() {
		return principalAndInterest;
	}

	public void setPrincipalAndInterest(BigDecimal principalAndInterest) {
		this.principalAndInterest = principalAndInterest;
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

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public Integer getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Integer returnedDate) {
		this.returnedDate = returnedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecPlaDesc() {
		return recPlaDesc;
	}

	public void setRecPlaDesc(String recPlaDesc) {
		this.recPlaDesc = recPlaDesc;
	}

	public String getStartExpireTime() {
		return startExpireTime;
	}

	public void setStartExpireTime(String startExpireTime) {
		this.startExpireTime = startExpireTime;
	}

	public String getEndExpireTime() {
		return endExpireTime;
	}

	public void setEndExpireTime(String endExpireTime) {
		this.endExpireTime = endExpireTime;
	}

	public BigDecimal getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getServiceFeeRate() {
		return serviceFeeRate;
	}

	public void setServiceFeeRate(BigDecimal serviceFeeRate) {
		this.serviceFeeRate = serviceFeeRate;
	}

	public String getReceiptStatusName() {
		return receiptStatusName;
	}

	public void setReceiptStatusName(String receiptStatusName) {
		this.receiptStatusName = receiptStatusName;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public BigDecimal getNetReceiptAmount() {
		return netReceiptAmount;
	}

	public void setNetReceiptAmount(BigDecimal netReceiptAmount) {
		this.netReceiptAmount = netReceiptAmount;
	}

	public String getIsEquitableAssignment() {
		return isEquitableAssignment;
	}

	public void setIsEquitableAssignment(String isEquitableAssignment) {
		this.isEquitableAssignment = isEquitableAssignment;
	}

	public Integer getLateFeeDate() {
		return lateFeeDate;
	}

	public void setLateFeeDate(Integer lateFeeDate) {
		this.lateFeeDate = lateFeeDate;
	}

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public BigDecimal getNetHike() {
		return netHike;
	}

	public void setNetHike(BigDecimal netHike) {
		this.netHike = netHike;
	}
	
	

	
	
}