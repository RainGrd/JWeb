package com.yscf.db.model.escf3.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

public class BizFrozenWater extends BaseEntity {
	private static final long serialVersionUID = 6544606457341931162L;

	private String pid;

	private String customerId;

	private String frozenType;

	private String fkey;

	private BigDecimal rechargeAmount;

	private BigDecimal experienceAmount;

	private BigDecimal commonAmount;

	private String status;

	private String createTime;

	private String createUser;
	
	private String lastUpdateUser;
	
	private String lastUpdateTime;

	private String lastUpStringTime;

	private String lastUpStringUser;

	private String version;

	
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

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

	public String getFrozenType() {
		return frozenType;
	}

	public void setFrozenType(String frozenType) {
		this.frozenType = frozenType == null ? null : frozenType.trim();
	}

	public String getFkey() {
		return fkey;
	}

	public void setFkey(String fkey) {
		this.fkey = fkey == null ? null : fkey.trim();
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public BigDecimal getExperienceAmount() {
		return experienceAmount;
	}

	public void setExperienceAmount(BigDecimal experienceAmount) {
		this.experienceAmount = experienceAmount;
	}

	public BigDecimal getCommonAmount() {
		return commonAmount;
	}

	public void setCommonAmount(BigDecimal commonAmount) {
		this.commonAmount = commonAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getLastUpStringTime() {
		return lastUpStringTime;
	}

	public void setLastUpStringTime(String lastUpStringTime) {
		this.lastUpStringTime = lastUpStringTime;
	}

	public String getLastUpStringUser() {
		return lastUpStringUser;
	}

	public void setLastUpStringUser(String lastUpStringUser) {
		this.lastUpStringUser = lastUpStringUser == null ? null : lastUpStringUser
				.trim();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
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
		BizFrozenWater other = (BizFrozenWater) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getCustomerId() == null ? other.getCustomerId() == null
						: this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getFrozenType() == null ? other.getFrozenType() == null
						: this.getFrozenType().equals(other.getFrozenType()))
				&& (this.getFkey() == null ? other.getFkey() == null : this
						.getFkey().equals(other.getFkey()))
				&& (this.getRechargeAmount() == null ? other
						.getRechargeAmount() == null : this.getRechargeAmount()
						.equals(other.getRechargeAmount()))
				&& (this.getExperienceAmount() == null ? other
						.getExperienceAmount() == null : this
						.getExperienceAmount().equals(
								other.getExperienceAmount()))
				&& (this.getCommonAmount() == null ? other.getCommonAmount() == null
						: this.getCommonAmount()
								.equals(other.getCommonAmount()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getLastUpStringTime() == null ? other
						.getLastUpStringTime() == null : this.getLastUpStringTime()
						.equals(other.getLastUpStringTime()))
				&& (this.getLastUpStringUser() == null ? other
						.getLastUpStringUser() == null : this.getLastUpStringUser()
						.equals(other.getLastUpStringUser()))
				&& (this.getVersion() == null ? other.getVersion() == null
						: this.getVersion().equals(other.getVersion()));
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
				+ ((getFrozenType() == null) ? 0 : getFrozenType().hashCode());
		result = prime * result
				+ ((getFkey() == null) ? 0 : getFkey().hashCode());
		result = prime
				* result
				+ ((getRechargeAmount() == null) ? 0 : getRechargeAmount()
						.hashCode());
		result = prime
				* result
				+ ((getExperienceAmount() == null) ? 0 : getExperienceAmount()
						.hashCode());
		result = prime
				* result
				+ ((getCommonAmount() == null) ? 0 : getCommonAmount()
						.hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result
				+ ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime
				* result
				+ ((getLastUpStringTime() == null) ? 0 : getLastUpStringTime()
						.hashCode());
		result = prime
				* result
				+ ((getLastUpStringUser() == null) ? 0 : getLastUpStringUser()
						.hashCode());
		result = prime * result
				+ ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}