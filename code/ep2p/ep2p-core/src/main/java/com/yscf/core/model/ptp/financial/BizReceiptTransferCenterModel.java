package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * Description：<br>
 * 债权转让前端个人中心VO
 * 
 * @author JunJie.Liu
 * @date 2015年10月23日
 * @version v1.0.0
 */
public class BizReceiptTransferCenterModel {

	// 债权转让id
	private String transferId;
	// 转让的收款计划ID
	private String receiptPlanId;
	// 借款ID
	private String borrowId;
	// 新债权客户id
	private String customerId;
	// 转让人id
	private String createUserId;
	// 待收时间
	private Date receiptTime;
	// 债权编号
	private String transferCode;
	// 借款编号
	private String borrowCode;
	// 借款类型名称
	private String borrowTypeName;
	// 借款名称（债权名称）
	private String borrowName;
	// 期次
	private String planIndex;
	// 总期次
	private String deadline;
	// 本息
	private BigDecimal amount;
	// 本金
	private BigDecimal capital;
	// 利息
	private BigDecimal interest;
	// 罚息
	private BigDecimal lateFee;
	// 利息
	private Integer lateFeeDate;
	// 年化收益
	private BigDecimal yearRate;
	// 转让金额
	private BigDecimal successAmount;
	// 在架时间
	private Integer returnedDate;
	// 转让时间
	private Date transferTime;
	// 转让状态
	private String transferStatus;
	// 收款计划状态
	private String receiptPlanStatus;
	// 收款计划状态名称
	private String receiptStatusName;
	// 转让人名称
	private String createUserName;
	
	// 失效时间
	private Date expireTime;
	
	// 债权转让净收金额
	private BigDecimal netReceiptAmount;
	/* 查询条件 */

	// 开始待收时间 （包含）
	private String startReceiptTime;
	// 结束待收时间（包含）
	private String endReceiptTime;

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getReceiptPlanId() {
		return receiptPlanId;
	}

	public void setReceiptPlanId(String receiptPlanId) {
		this.receiptPlanId = receiptPlanId;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public Date getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(Date receiptTime) {
		this.receiptTime = receiptTime;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
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

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	public BigDecimal getYearRate() {
		return yearRate;
	}

	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}

	public BigDecimal getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}

	public Integer getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Integer returnedDate) {
		this.returnedDate = returnedDate;
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getStartReceiptTime() {
		return startReceiptTime;
	}

	public void setStartReceiptTime(String startReceiptTime) {
		this.startReceiptTime = startReceiptTime;
	}

	public String getEndReceiptTime() {
		return endReceiptTime;
	}

	public void setEndReceiptTime(String endReceiptTime) {
		this.endReceiptTime = endReceiptTime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getReceiptPlanStatus() {
		return receiptPlanStatus;
	}

	public void setReceiptPlanStatus(String receiptPlanStatus) {
		this.receiptPlanStatus = receiptPlanStatus;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getReceiptStatusName() {
		return receiptStatusName;
	}

	public void setReceiptStatusName(String receiptStatusName) {
		this.receiptStatusName = receiptStatusName;
	}

	public BigDecimal getNetReceiptAmount() {
		return netReceiptAmount;
	}

	public void setNetReceiptAmount(BigDecimal netReceiptAmount) {
		this.netReceiptAmount = netReceiptAmount;
	}

	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public Integer getLateFeeDate() {
		return lateFeeDate;
	}

	public void setLateFeeDate(Integer lateFeeDate) {
		this.lateFeeDate = lateFeeDate;
	}
	
	
	
}