package com.yscf.api.vo.myinvest;

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
public class BizReceiptTransferCenterVo {

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
	// 本息
	private BigDecimal amount;
	// 转让金额
	private BigDecimal successAmount;
	// 转让状态
	private String transferStatus;
	// 收款计划状态
	private String receiptPlanStatus;
	// 收款计划状态名称
	private String receiptStatusName;

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

	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getReceiptPlanStatus() {
		return receiptPlanStatus;
	}

	public void setReceiptPlanStatus(String receiptPlanStatus) {
		this.receiptPlanStatus = receiptPlanStatus;
	}

	public String getReceiptStatusName() {
		return receiptStatusName;
	}

	public void setReceiptStatusName(String receiptStatusName) {
		this.receiptStatusName = receiptStatusName;
	}

}