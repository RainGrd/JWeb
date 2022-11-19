package com.yscf.core.model.activity;

import java.math.BigDecimal;

/**
 * 
 * @ClassName : ActExpActDetail
 * @Description : 赠送体验金活动明细javaBean
 * @Author : Qing.Cai
 * @String : 2015年10月19日 上午10:35:20
 */
public class ActExpActDetail extends ActDetailDto {

	private static final long serialVersionUID = 2110009450300883417L;

	private String pid;

	private String activityId;

	private String expId;

	private String smsId;

	private String customerId;

	private String getTime;

	private String expireTime;

	private String useStatus;

	private String useTime;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;
	private BigDecimal expAmount;// 体验金金额
	private String expTime;// 过期时间
	private String happenBeginTime;// 开始时间
	private String happenEndTime;// 结束时间
	private String isExpired;// 是否过期
	private BigDecimal userExpAmount;// 已使用金额
	private Integer validity;// 有效期

	/**
	 * 过期天数
	 */
	private String expDays;

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public BigDecimal getUserExpAmount() {
		return userExpAmount;
	}

	public void setUserExpAmount(BigDecimal userExpAmount) {
		this.userExpAmount = userExpAmount;
	}

	public String getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
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

	public BigDecimal getExpAmount() {
		return expAmount;
	}

	public void setExpAmount(BigDecimal expAmount) {
		this.expAmount = expAmount;
	}

	public String getExpTime() {
		return expTime;
	}

	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId == null ? null : activityId.trim();
	}

	public String getExpId() {
		return expId;
	}

	public void setExpId(String expId) {
		this.expId = expId == null ? null : expId.trim();
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId == null ? null : smsId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus == null ? null : useStatus.trim();
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getExpDays() {
		return expDays;
	}

	public void setExpDays(String expDays) {
		this.expDays = expDays;
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
		ActExpActDetail other = (ActExpActDetail) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId())) && (this.getExpId() == null ? other.getExpId() == null : this.getExpId().equals(other.getExpId())) && (this.getSmsId() == null ? other.getSmsId() == null : this.getSmsId().equals(other.getSmsId())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getGetTime() == null ? other.getGetTime() == null : this.getGetTime().equals(other.getGetTime())) && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
				&& (this.getUseStatus() == null ? other.getUseStatus() == null : this.getUseStatus().equals(other.getUseStatus())) && (this.getUseTime() == null ? other.getUseTime() == null : this.getUseTime().equals(other.getUseTime())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
		result = prime * result + ((getExpId() == null) ? 0 : getExpId().hashCode());
		result = prime * result + ((getSmsId() == null) ? 0 : getSmsId().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getGetTime() == null) ? 0 : getGetTime().hashCode());
		result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
		result = prime * result + ((getUseStatus() == null) ? 0 : getUseStatus().hashCode());
		result = prime * result + ((getUseTime() == null) ? 0 : getUseTime().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}