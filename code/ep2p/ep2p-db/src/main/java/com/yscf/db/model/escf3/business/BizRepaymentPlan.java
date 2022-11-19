package com.yscf.db.model.escf3.business;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br>
 * 还款计划
 * 
 * @author Lin Xu
 * @date 2015年10月22日
 * @version v1.0.0
 */
public class BizRepaymentPlan extends BaseEntity {

	private static final long serialVersionUID = 891858141677874172L;
	// 还款计划ID
	private String pid;
	// 借款ID
	private String borrowId;
	// 客户ID
	private String customerId;
	// 本金
	private BigDecimal capital;
	// 实际逾期时间
	private Date expireactualTime;
	// 到期时间
	private String expireTime;
	// 逾期罚息
	private BigDecimal latefee;
	// 提前还款罚息
	private BigDecimal prepaymentFee;
	// 罚息更新时间
	private Date latefeecalculateTime;
	// 垫付金额
	private BigDecimal payamount;
	// 垫付日期
	private Date payamountTime;
	// 还款期数
	private String planindex;
	// 已还款金额
	private BigDecimal repaidamount;
	// 实际还款时间
	private String repaidTime;
	// 利息
	private BigDecimal interest;
	// 计息方式
	private String interestType;
	// 还款状态
	private String receiptPalnStatus;
	// 还款状态名称
	private String receiptPalnStatusName;
	// 状态
	private String status;
	// 创建人
	private String createUser;
	// 创建时间
	private Date createTime;
	// 最后修改人
	private String lastUpdateUser;
	// 最后修改时间
	private Date lastUpdateTime;
	// 备注
	private String repPlaDesc;
	/**
	 * 剩余本金
	 */
	private BigDecimal remainingCapital;

	/**
	 * 管理费
	 */
	private BigDecimal managementCost;
	// 借款编号
	private String borrowCode;
	// 借款名称
	private String borrowName;
	// 借款期限
	private Integer borDeadline;
	// 当期还款金额
	private BigDecimal borTotalAmount;

	public BigDecimal getBorTotalAmount() {
		return borTotalAmount;
	}

	public void setBorTotalAmount(BigDecimal borTotalAmount) {
		this.borTotalAmount = borTotalAmount;
	}

	public BigDecimal getPrepaymentFee() {
		return prepaymentFee;
	}

	public void setPrepaymentFee(BigDecimal prepaymentFee) {
		this.prepaymentFee = prepaymentFee;
	}

	/**
	 * 
	 * Description：<br>
	 * 获取待还总金额，包含本金，罚息，利息，管理费
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月24日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getTotalAmount() {
		// 本金 + 罚息 + 利息 + 管理费
		this.capital = this.capital == null ? BigDecimal.ZERO : this.capital;
		this.interest = this.interest == null ? BigDecimal.ZERO : this.interest;
		this.latefee = this.latefee == null ? BigDecimal.ZERO : this.latefee;
		this.managementCost = this.managementCost == null ? BigDecimal.ZERO : this.managementCost;

		return this.capital.add(this.interest).add(this.latefee).add(this.managementCost);
	}

	public String getReceiptPalnStatusName() {
		return receiptPalnStatusName;
	}

	public void setReceiptPalnStatusName(String receiptPalnStatusName) {
		this.receiptPalnStatusName = receiptPalnStatusName;
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

	public Integer getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(Integer borDeadline) {
		this.borDeadline = borDeadline;
	}

	public BigDecimal getRemainingCapital() {
		return remainingCapital;
	}

	public void setRemainingCapital(BigDecimal remainingCapital) {
		this.remainingCapital = remainingCapital;
	}

	public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId == null ? null : borrowId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Date getExpireactualTime() {
		return expireactualTime;
	}

	public void setExpireactualTime(Date expireactualTime) {
		this.expireactualTime = expireactualTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public BigDecimal getLatefee() {
		return latefee;
	}

	public void setLatefee(BigDecimal latefee) {
		this.latefee = latefee;
	}

	public Date getLatefeecalculateTime() {
		return latefeecalculateTime;
	}

	public void setLatefeecalculateTime(Date latefeecalculateTime) {
		this.latefeecalculateTime = latefeecalculateTime;
	}

	public BigDecimal getPayamount() {
		return payamount;
	}

	public void setPayamount(BigDecimal payamount) {
		this.payamount = payamount;
	}

	public Date getPayamountTime() {
		return payamountTime;
	}

	public void setPayamountTime(Date payamountTime) {
		this.payamountTime = payamountTime;
	}

	public String getPlanindex() {
		return planindex;
	}

	public void setPlanindex(String planindex) {
		this.planindex = planindex == null ? null : planindex.trim();
	}

	public BigDecimal getRepaidamount() {
		return repaidamount;
	}

	public void setRepaidamount(BigDecimal repaidamount) {
		this.repaidamount = repaidamount;
	}

	public String getRepaidTime() {
		return repaidTime;
	}

	public void setRepaidTime(String repaidTime) {
		this.repaidTime = repaidTime;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType == null ? null : interestType.trim();
	}

	public String getReceiptPalnStatus() {
		return receiptPalnStatus;
	}

	public void setReceiptPalnStatus(String receiptPalnStatus) {
		this.receiptPalnStatus = receiptPalnStatus == null ? null : receiptPalnStatus.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRepPlaDesc() {
		return repPlaDesc;
	}

	public void setRepPlaDesc(String repPlaDesc) {
		this.repPlaDesc = repPlaDesc == null ? null : repPlaDesc.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		BizRepaymentPlan other = (BizRepaymentPlan) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital())) && (this.getExpireactualTime() == null ? other.getExpireactualTime() == null : this.getExpireactualTime().equals(other.getExpireactualTime())) && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
				&& (this.getLatefee() == null ? other.getLatefee() == null : this.getLatefee().equals(other.getLatefee())) && (this.getLatefeecalculateTime() == null ? other.getLatefeecalculateTime() == null : this.getLatefeecalculateTime().equals(other.getLatefeecalculateTime())) && (this.getPayamount() == null ? other.getPayamount() == null : this.getPayamount().equals(other.getPayamount())) && (this.getPayamountTime() == null ? other.getPayamountTime() == null : this.getPayamountTime().equals(other.getPayamountTime())) && (this.getPlanindex() == null ? other.getPlanindex() == null : this.getPlanindex().equals(other.getPlanindex())) && (this.getRepaidamount() == null ? other.getRepaidamount() == null : this.getRepaidamount().equals(other.getRepaidamount()))
				&& (this.getRepaidTime() == null ? other.getRepaidTime() == null : this.getRepaidTime().equals(other.getRepaidTime())) && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest())) && (this.getInterestType() == null ? other.getInterestType() == null : this.getInterestType().equals(other.getInterestType())) && (this.getReceiptPalnStatus() == null ? other.getReceiptPalnStatus() == null : this.getReceiptPalnStatus().equals(other.getReceiptPalnStatus())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getRepPlaDesc() == null ? other.getRepPlaDesc() == null : this.getRepPlaDesc().equals(other.getRepPlaDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
		result = prime * result + ((getExpireactualTime() == null) ? 0 : getExpireactualTime().hashCode());
		result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
		result = prime * result + ((getLatefee() == null) ? 0 : getLatefee().hashCode());
		result = prime * result + ((getLatefeecalculateTime() == null) ? 0 : getLatefeecalculateTime().hashCode());
		result = prime * result + ((getPayamount() == null) ? 0 : getPayamount().hashCode());
		result = prime * result + ((getPayamountTime() == null) ? 0 : getPayamountTime().hashCode());
		result = prime * result + ((getPlanindex() == null) ? 0 : getPlanindex().hashCode());
		result = prime * result + ((getRepaidamount() == null) ? 0 : getRepaidamount().hashCode());
		result = prime * result + ((getRepaidTime() == null) ? 0 : getRepaidTime().hashCode());
		result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
		result = prime * result + ((getInterestType() == null) ? 0 : getInterestType().hashCode());
		result = prime * result + ((getReceiptPalnStatus() == null) ? 0 : getReceiptPalnStatus().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getRepPlaDesc() == null) ? 0 : getRepPlaDesc().hashCode());
		return result;
	}
}