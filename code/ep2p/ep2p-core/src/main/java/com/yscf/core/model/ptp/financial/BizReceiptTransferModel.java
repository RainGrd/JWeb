package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.yscf.common.util.ArithmeticUtil;
/**
 * 
 * Description：<br>
 * 债权转让VO
 * 
 * @author JunJie.Liu
 * @date 2015年10月23日
 * @version v1.0.0
 */
public class BizReceiptTransferModel {

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
	// 发布开始时间
	private String releaseStartTime;
	// 发布结束时间
	private String releaseEndTime;
	// 成交金额
	private BigDecimal successAmount;
	// 转让人Id
	private String createUser;
	// 转让人名称
	private String createUserName;
	// 转让人客户名
	private String createUserSName;
	// 转让人手机号码
	private String createUserMobile;
	// 购买人ID
	private String customerId;
	// 购买人名称
	private String customerName;
	// 购买人客户名
	private String customerSName;
	// 购买人手机号码
	private String customerMobile;
	// 成交时间
	private String successTime;
	// 成交时间开始
	private String successStartTime;
	// 成交时间结束
	private String successEndTime;
	// 申请时间开始
	private String applyStartTime;
	// 申请时间结束
	private String applyEndTime;
	// 申请时间
	private String applyTime;
	// 失效时间
	private String expireTime;
	// 状态
	private String status;
	// 状态名
	private String statusName;
	// 创建时间
	private String createTime;
	// 备注
	private String recTraDesc;
	// 期限
	private String deadline;
	
	// 起息日
	private Date interestData;
	
	// 还款方式
	private String repayType;
	
	// 还款方式名称
	private String repayTypeName;
	
	// 借款类型
	private String borrowType;
	
	// 借款类型名称
	private String borrowTypeName;
	
	// 组织机构
	private String org;
	
	
	
	// 导出字段显示【只做显示】
	@SuppressWarnings("unused")
	private String issue;
	// 导出字段显示【只做显示】
	@SuppressWarnings("unused")
	private String yearRate;
	// 导出字段显示【只做显示】
	@SuppressWarnings("unused")
	private String des;
	
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
	public String getReleaseStartTime() {
		return releaseStartTime;
	}
	public void setReleaseStartTime(String releaseStartTime) {
		this.releaseStartTime = releaseStartTime;
	}
	public String getReleaseEndTime() {
		return releaseEndTime;
	}
	public void setReleaseEndTime(String releaseEndTime) {
		this.releaseEndTime = releaseEndTime;
	}
	public BigDecimal getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getCreateUserSName() {
		return createUserSName;
	}
	public void setCreateUserSName(String createUserSName) {
		this.createUserSName = createUserSName;
	}
	public String getCreateUserMobile() {
		return createUserMobile;
	}
	public void setCreateUserMobile(String createUserMobile) {
		this.createUserMobile = createUserMobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSName() {
		return customerSName;
	}
	public void setCustomerSName(String customerSName) {
		this.customerSName = customerSName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}
	public String getSuccessStartTime() {
		return successStartTime;
	}
	public void setSuccessStartTime(String successStartTime) {
		this.successStartTime = successStartTime;
	}
	public String getSuccessEndTime() {
		return successEndTime;
	}
	public void setSuccessEndTime(String successEndTime) {
		this.successEndTime = successEndTime;
	}
	public String getApplyStartTime() {
		return applyStartTime;
	}
	public void setApplyStartTime(String applyStartTime) {
		this.applyStartTime = applyStartTime;
	}
	public String getApplyEndTime() {
		return applyEndTime;
	}
	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
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
	public String getRecTraDesc() {
		return recTraDesc;
	}
	public void setRecTraDesc(String recTraDesc) {
		this.recTraDesc = recTraDesc;
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
		if(org == null){
			this.org = "";
		}
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	public String getBorrowTypeName() {
		return borrowTypeName;
	}
	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}
	public String getIssue() {
		if(this.timeRemaining!=null && StringUtils.hasLength(this.deadline)){
		
			return this.timeRemaining + "/" + this.deadline;
		}
		return "";
	}

	public String getYearRate() {
		if(this.profitRate!=null){
			return ArithmeticUtil.round(this.profitRate.multiply(new BigDecimal(100)),2) + "%";
		}
		return "";
	}

	public String getDes() {
		if(!StringUtils.hasLength(this.transferCode)){
			return "";
		}
		
		this.projectValue = this.projectValue == null ? BigDecimal.ZERO : this.projectValue;
		this.residualPrincipal = this.residualPrincipal == null ? BigDecimal.ZERO : this.residualPrincipal;
		this.interestToday = this.interestToday == null ? BigDecimal.ZERO : this.interestToday;
		this.fee = this.fee == null ? BigDecimal.ZERO : this.fee;
		String pv = ArithmeticUtil.round(this.projectValue,2).toString();
		String rp = ArithmeticUtil.round(this.residualPrincipal,2).toString();
		String it = ArithmeticUtil.round(this.interestToday,2).toString();
		String fee = ArithmeticUtil.round(this.fee,2).toString();
		
		return "项目总价"+pv+"元（剩余本金"+rp+"元，当期至今利息"+it+"元），转让手续费"+fee+"元";
	}
	
	
	
	
}