package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;

/**
 * 
 * Description：<br>
 * 债权转让前端VO
 * 
 * @author JunJie.Liu
 * @date 2015年10月23日
 * @version v1.0.0
 */
public class BizReceiptTransferFrontModel {

	// 债权转让id
	private String transferId;
	// 债权转让编号
	private String transferCode;
	// 借款ID
	private String borrowId;
	// 借款编号
	private String borrowCode;
	// 借款名称（债权名称）
	private String borrowName;
	// 年化收益
	private BigDecimal yearRate;
	// 转让金额
	private BigDecimal successAmount;
	// 收益金额
	private BigDecimal projectValue;
	// 回款天数
	private Integer timeRemaining;
	// 转让状态
	private String transferStatus;

	/* 查询条件 */

	// 开始转让价格 （包含）
	private BigDecimal startSuccessAmount;
	// 结束转让价格（不包含）
	private BigDecimal endSuccessAmount;
	
	// 开始剩余期限 （包含）
	private String startTimeRemaining;
	// 结束结束剩余（不包含）
	private String endTimeRemaining;

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
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

	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public BigDecimal getProjectValue() {
		return projectValue;
	}

	public void setProjectValue(BigDecimal projectValue) {
		this.projectValue = projectValue;
	}

	public Integer getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(Integer timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public BigDecimal getStartSuccessAmount() {
		return startSuccessAmount;
	}

	public void setStartSuccessAmount(BigDecimal startSuccessAmount) {
		this.startSuccessAmount = startSuccessAmount;
	}

	public BigDecimal getEndSuccessAmount() {
		return endSuccessAmount;
	}

	public void setEndSuccessAmount(BigDecimal endSuccessAmount) {
		this.endSuccessAmount = endSuccessAmount;
	}

	public String getStartTimeRemaining() {
		return startTimeRemaining;
	}

	public void setStartTimeRemaining(String startTimeRemaining) {
		this.startTimeRemaining = startTimeRemaining;
	}

	public String getEndTimeRemaining() {
		return endTimeRemaining;
	}

	public void setEndTimeRemaining(String endTimeRemaining) {
		this.endTimeRemaining = endTimeRemaining;
	}


	

}