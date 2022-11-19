package com.yscf.db.model.escf3.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : CustPoinWater
 * @Description : 积分历史记录javaBean
 * @Author : Qing.Cai
 * @Date : 2015年12月1日 下午4:44:31
 */
public class CustPoinWater extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7083062863859177013L;

	private String pid;

	private Integer pointValue;

	private String pointGetType;

	private String pointType;

	private Integer availablePoint;

	private String customerId;

	private String status;

	private String happenTime;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String potWatDesc;
	private String happenBeginTime;
	private String happenEndTime;
	// 兑换使用：兑换类型
	private String exchangeType;

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public Integer getPointValue() {
		return pointValue;
	}

	public void setPointValue(Integer pointValue) {
		this.pointValue = pointValue;
	}

	public String getPointGetType() {
		return pointGetType;
	}

	public void setPointGetType(String pointGetType) {
		this.pointGetType = pointGetType;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public Integer getAvailablePoint() {
		return availablePoint;
	}

	public void setAvailablePoint(Integer availablePoint) {
		this.availablePoint = availablePoint;
	}

	public String getCustomerId() {
		return customerId;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
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

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
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

	public String getPotWatDesc() {
		return potWatDesc;
	}

	public void setPotWatDesc(String potWatDesc) {
		this.potWatDesc = potWatDesc == null ? null : potWatDesc.trim();
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
		CustPoinWater other = (CustPoinWater) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getPointValue() == null ? other.getPointValue() == null : this.getPointValue().equals(other.getPointValue())) && (this.getPointGetType() == null ? other.getPointGetType() == null : this.getPointGetType().equals(other.getPointGetType())) && (this.getPointType() == null ? other.getPointType() == null : this.getPointType().equals(other.getPointType())) && (this.getAvailablePoint() == null ? other.getAvailablePoint() == null : this.getAvailablePoint().equals(other.getAvailablePoint())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getHappenTime() == null ? other.getHappenTime() == null : this.getHappenTime().equals(other.getHappenTime())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
				&& (this.getPotWatDesc() == null ? other.getPotWatDesc() == null : this.getPotWatDesc().equals(other.getPotWatDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getPointValue() == null) ? 0 : getPointValue().hashCode());
		result = prime * result + ((getPointGetType() == null) ? 0 : getPointGetType().hashCode());
		result = prime * result + ((getPointType() == null) ? 0 : getPointType().hashCode());
		result = prime * result + ((getAvailablePoint() == null) ? 0 : getAvailablePoint().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getHappenTime() == null) ? 0 : getHappenTime().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getPotWatDesc() == null) ? 0 : getPotWatDesc().hashCode());
		return result;
	}
}