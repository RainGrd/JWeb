package com.yscf.core.model.activity;

import java.math.BigDecimal;

/**
 * 
 * @ClassName : ActRedpActDetail
 * @Description : 红包活动明细javaBean
 * @Author : Qing.Cai
 * @String : 2015年10月20日 上午10:30:11
 */
public class ActRedpActDetail extends ActDetailDto {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String activityId;

	private String redpacketId;

	private String smsId;

	private String customerId;

	private BigDecimal getAmount;

	private String getTime;

	private String useStatus;

	private String useTime;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;

	private String redpacketTypel;
	// 头像URL
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRedpacketTypel() {
		return redpacketTypel;
	}

	public void setRedpacketTypel(String redpacketTypel) {
		this.redpacketTypel = redpacketTypel;
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

	public String getRedpacketId() {
		return redpacketId;
	}

	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId == null ? null : redpacketId.trim();
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

	public BigDecimal getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(BigDecimal getAmount) {
		this.getAmount = getAmount;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
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
		ActRedpActDetail other = (ActRedpActDetail) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId())) && (this.getRedpacketId() == null ? other.getRedpacketId() == null : this.getRedpacketId().equals(other.getRedpacketId())) && (this.getSmsId() == null ? other.getSmsId() == null : this.getSmsId().equals(other.getSmsId())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getGetAmount() == null ? other.getGetAmount() == null : this.getGetAmount().equals(other.getGetAmount())) && (this.getGetTime() == null ? other.getGetTime() == null : this.getGetTime().equals(other.getGetTime()))
				&& (this.getUseStatus() == null ? other.getUseStatus() == null : this.getUseStatus().equals(other.getUseStatus())) && (this.getUseTime() == null ? other.getUseTime() == null : this.getUseTime().equals(other.getUseTime())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
		result = prime * result + ((getRedpacketId() == null) ? 0 : getRedpacketId().hashCode());
		result = prime * result + ((getSmsId() == null) ? 0 : getSmsId().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getGetAmount() == null) ? 0 : getGetAmount().hashCode());
		result = prime * result + ((getGetTime() == null) ? 0 : getGetTime().hashCode());
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