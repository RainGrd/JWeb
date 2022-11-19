package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : CustExperienceWater
 * @Description : 经验历史纪录javaBean
 * @Author : Qing.Cai
 * @Date : 2015年12月29日 下午9:44:00
 */
public class CustExperienceWater extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3367736274881634094L;

	private String pid;

	private Integer expValue;

	private String expGetType;

	private String expType;

	private Integer availableExp;

	private String customerId;

	private String happenTime;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String expWatDesc;

	private String happenBeginTime;
	private String happenEndTime;

	private String version;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHappenBeginTime() {
		return happenBeginTime;
	}

	public void setHappenBeginTime(String happenBeginTime) {
		this.happenBeginTime = happenBeginTime;
	}

	public String getHappenEndTime() {
		return happenEndTime;
	}

	public void setHappenEndTime(String happenEndTime) {
		this.happenEndTime = happenEndTime;
	}

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public Integer getExpValue() {
		return expValue;
	}

	public void setExpValue(Integer expValue) {
		this.expValue = expValue;
	}

	public String getExpGetType() {
		return expGetType;
	}

	public void setExpGetType(String expGetType) {
		this.expGetType = expGetType == null ? null : expGetType.trim();
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType == null ? null : expType.trim();
	}

	public Integer getAvailableExp() {
		return availableExp;
	}

	public void setAvailableExp(Integer availableExp) {
		this.availableExp = availableExp;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
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
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getExpWatDesc() {
		return expWatDesc;
	}

	public void setExpWatDesc(String expWatDesc) {
		this.expWatDesc = expWatDesc == null ? null : expWatDesc.trim();
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
		CustExperienceWater other = (CustExperienceWater) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getExpValue() == null ? other.getExpValue() == null : this.getExpValue().equals(other.getExpValue())) && (this.getExpGetType() == null ? other.getExpGetType() == null : this.getExpGetType().equals(other.getExpGetType())) && (this.getExpType() == null ? other.getExpType() == null : this.getExpType().equals(other.getExpType())) && (this.getAvailableExp() == null ? other.getAvailableExp() == null : this.getAvailableExp().equals(other.getAvailableExp())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getHappenTime() == null ? other.getHappenTime() == null : this.getHappenTime().equals(other.getHappenTime())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
				&& (this.getExpWatDesc() == null ? other.getExpWatDesc() == null : this.getExpWatDesc().equals(other.getExpWatDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getExpValue() == null) ? 0 : getExpValue().hashCode());
		result = prime * result + ((getExpGetType() == null) ? 0 : getExpGetType().hashCode());
		result = prime * result + ((getExpType() == null) ? 0 : getExpType().hashCode());
		result = prime * result + ((getAvailableExp() == null) ? 0 : getAvailableExp().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getHappenTime() == null) ? 0 : getHappenTime().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getExpWatDesc() == null) ? 0 : getExpWatDesc().hashCode());
		return result;
	}
}