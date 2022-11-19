package com.yscf.db.model.escf3.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;
import com.yscf.db.model.escf3.user.CusTomer;

/**
 * Description：线上充值Bean  对应表：t_biz_recharge_online
 * @author  JingYu.Dai
 * @date    2015年9月29日
 * @version v1.0.0
 */
public class BizRechargeOnline extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6539167304130440891L;

	private String pid;

	private String customerId; // 客户ID

	private BigDecimal amount; // 充值金额

	private String bankId; // 银行ID

	private String recStatus; // 充值状态

	private String recTime; // 充值时间

	private String recOnlDesc; // 充值描述

	private CusTomer customer; // 客户对象

	private String recTimeBegin; // 充值开始时间 用于条件充值时间（recTime）的区间查询 （当做VO用）

	private String recTimeEnd; // 充值结束始时间 用于条件充值时间（recTime）的区间查询 （当做VO用）

	private String payConfigId; //支付平台ID

	private String payName; //支付平台名称
	
	private Integer recOrderNo;	//订单号

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId == null ? null : bankId.trim();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus == null ? null : recStatus.trim();
	}

	public String getRecTime() {
		return recTime;
	}

	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}

	public String getPayConfigId() {
		return payConfigId;
	}

	public void setPayConfigId(String payConfigId) {
		this.payConfigId = payConfigId == null ? null : payConfigId.trim();
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRecOnlDesc() {
		return recOnlDesc;
	}

	public void setRecOnlDesc(String recOnlDesc) {
		this.recOnlDesc = recOnlDesc == null ? null : recOnlDesc.trim();
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
		BizRechargeOnline other = (BizRechargeOnline) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getCustomerId() == null ? other.getCustomerId() == null
						: this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getBankId() == null ? other.getBankId() == null : this
						.getBankId().equals(other.getBankId()))
				&& (this.getAmount() == null ? other.getAmount() == null : this
						.getAmount().equals(other.getAmount()))
				&& (this.getRecStatus() == null ? other.getRecStatus() == null
						: this.getRecStatus().equals(other.getRecStatus()))
				&& (this.getRecTime() == null ? other.getRecTime() == null
						: this.getRecTime().equals(other.getRecTime()))
				&& (this.getPayConfigId() == null ? other.getPayConfigId() == null
						: this.getPayConfigId().equals(other.getPayConfigId()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other
						.getLastUpdateUser() == null : this.getLastUpdateUser()
						.equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other
						.getLastUpdateTime() == null : this.getLastUpdateTime()
						.equals(other.getLastUpdateTime()))
				&& (this.getRecOnlDesc() == null ? other.getRecOnlDesc() == null
						: this.getRecOnlDesc().equals(other.getRecOnlDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result
				+ ((getBankId() == null) ? 0 : getBankId().hashCode());
		result = prime * result
				+ ((getAmount() == null) ? 0 : getAmount().hashCode());
		result = prime * result
				+ ((getRecStatus() == null) ? 0 : getRecStatus().hashCode());
		result = prime * result
				+ ((getRecTime() == null) ? 0 : getRecTime().hashCode());
		result = prime
				* result
				+ ((getPayConfigId() == null) ? 0 : getPayConfigId().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime
				* result
				+ ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser()
						.hashCode());
		result = prime
				* result
				+ ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime()
						.hashCode());
		result = prime * result
				+ ((getRecOnlDesc() == null) ? 0 : getRecOnlDesc().hashCode());
		return result;
	}

	public String getRecTimeBegin() {
		return recTimeBegin;
	}

	public void setRecTimeBegin(String recTimeBegin) {
		this.recTimeBegin = recTimeBegin;
	}

	public String getRecTimeEnd() {
		return recTimeEnd;
	}

	public void setRecTimeEnd(String recTimeEnd) {
		this.recTimeEnd = recTimeEnd;
	}

	public CusTomer getCustomer() {
		return customer;
	}

	public void setCustomer(CusTomer customer) {
		this.customer = customer;
	}

	public Integer getRecOrderNo() {
		return recOrderNo;
	}

	public void setRecOrderNo(Integer recOrderNo) {
		this.recOrderNo = recOrderNo;
	}

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}
	
}