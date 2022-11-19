package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * Description：<br>
 * 债权转让VO
 * 
 * @author JunJie.Liu
 * @date 2015年10月23日
 * @version v1.0.0
 */
public class BizReceiptTransferApiVo {

	private String pid;
	
    private String transferCode;

    private BigDecimal residualPrincipal;

    private Integer timeRemaining;

    private BigDecimal projectValue;

    private BigDecimal fee;

    private BigDecimal interestToday;
    
	// 借款ID
	private String borrowId;
	// 借款编号
	private String borrowCode;
	// 借款名称
	private String borrowName;
	// 年化收益
	private BigDecimal profitRate;
	// 成交金额
	private BigDecimal successAmount;
	// 转让人名称
	private String createUserName;
	// 成交时间
	private String successTime;
	// 状态
	private String status;
	// 状态名
	private String statusName;
	// 创建时间
	private String createTime;
	// 期限
	private String deadline;
	
	// 起息日
	private Date interestData;
	
	// 还款方式
	private String repayType;
	
	// 还款方式名称
	private String repayTypeName;
	
	// 组织机构
	private String org;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public BigDecimal getResidualPrincipal() {
		return residualPrincipal;
	}

	public void setResidualPrincipal(BigDecimal residualPrincipal) {
		this.residualPrincipal = residualPrincipal;
	}

	public Integer getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(Integer timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public BigDecimal getProjectValue() {
		return projectValue;
	}

	public void setProjectValue(BigDecimal projectValue) {
		this.projectValue = projectValue;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getInterestToday() {
		return interestToday;
	}

	public void setInterestToday(BigDecimal interestToday) {
		this.interestToday = interestToday;
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

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public BigDecimal getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public Date getInterestData() {
		return interestData;
	}

	public void setInterestData(Date interestData) {
		this.interestData = interestData;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getRepayTypeName() {
		return repayTypeName;
	}

	public void setRepayTypeName(String repayTypeName) {
		this.repayTypeName = repayTypeName;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}
	
}