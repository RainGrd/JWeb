package com.yscf.core.model.activity;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : PubActivityArea
 * @Description : 活动专区展示javaBean
 * @Author : Qing.Cai
 * @Date : 2015年12月12日 下午3:02:32
 */
public class PubActivityArea extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String activityId;

	private String activityName;

	private String activityCode;

	private String activityUrl;

	private String activityImage;

	private String fileId;

	private Integer isShows;

	private String activityStartDate;

	private String activityEndDate;

	private Integer activityType;

	private String activityStatus;

	private String extfield1;

	private String extfield2;

	private String extfield3;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String status;

	private String activityDesc;

	private String version;

	// 活动开始时间-begin
	private String beginStartData;
	// 活动开始时间-end
	private String endStartData;
	// 活动结束时间-begin
	private String beginFinishData;
	// 活动结束时间-end
	private String endFinishData;

	private Integer isDisable;// 是否禁用 1.启用 2.禁用

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName == null ? null : activityName.trim();
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getActivityImage() {
		return activityImage;
	}

	public void setActivityImage(String activityImage) {
		this.activityImage = activityImage;
	}

	public Integer getIsShows() {
		return isShows;
	}

	public void setIsShows(Integer isShows) {
		this.isShows = isShows;
	}

	public String getBeginStartData() {
		return beginStartData;
	}

	public void setBeginStartData(String beginStartData) {
		this.beginStartData = beginStartData;
	}

	public String getEndStartData() {
		return endStartData;
	}

	public void setEndStartData(String endStartData) {
		this.endStartData = endStartData;
	}

	public String getBeginFinishData() {
		return beginFinishData;
	}

	public void setBeginFinishData(String beginFinishData) {
		this.beginFinishData = beginFinishData;
	}

	public String getEndFinishData() {
		return endFinishData;
	}

	public void setEndFinishData(String endFinishData) {
		this.endFinishData = endFinishData;
	}

	public String getActivityStartDate() {
		return activityStartDate;
	}

	public void setActivityStartDate(String activityStartDate) {
		this.activityStartDate = activityStartDate;
	}

	public String getActivityEndDate() {
		return activityEndDate;
	}

	public void setActivityEndDate(String activityEndDate) {
		this.activityEndDate = activityEndDate;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus == null ? null : activityStatus.trim();
	}

	public String getExtfield1() {
		return extfield1;
	}

	public void setExtfield1(String extfield1) {
		this.extfield1 = extfield1 == null ? null : extfield1.trim();
	}

	public String getExtfield2() {
		return extfield2;
	}

	public void setExtfield2(String extfield2) {
		this.extfield2 = extfield2 == null ? null : extfield2.trim();
	}

	public String getExtfield3() {
		return extfield3;
	}

	public void setExtfield3(String extfield3) {
		this.extfield3 = extfield3 == null ? null : extfield3.trim();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getActivityDesc() {
		return activityDesc;
	}

	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
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
		PubActivityArea other = (PubActivityArea) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId())) && (this.getActivityName() == null ? other.getActivityName() == null : this.getActivityName().equals(other.getActivityName())) && (this.getActivityStartDate() == null ? other.getActivityStartDate() == null : this.getActivityStartDate().equals(other.getActivityStartDate())) && (this.getActivityEndDate() == null ? other.getActivityEndDate() == null : this.getActivityEndDate().equals(other.getActivityEndDate())) && (this.getActivityType() == null ? other.getActivityType() == null : this.getActivityType().equals(other.getActivityType()))
				&& (this.getActivityStatus() == null ? other.getActivityStatus() == null : this.getActivityStatus().equals(other.getActivityStatus())) && (this.getExtfield1() == null ? other.getExtfield1() == null : this.getExtfield1().equals(other.getExtfield1())) && (this.getExtfield2() == null ? other.getExtfield2() == null : this.getExtfield2().equals(other.getExtfield2())) && (this.getExtfield3() == null ? other.getExtfield3() == null : this.getExtfield3().equals(other.getExtfield3())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
		result = prime * result + ((getActivityName() == null) ? 0 : getActivityName().hashCode());
		result = prime * result + ((getActivityStartDate() == null) ? 0 : getActivityStartDate().hashCode());
		result = prime * result + ((getActivityEndDate() == null) ? 0 : getActivityEndDate().hashCode());
		result = prime * result + ((getActivityType() == null) ? 0 : getActivityType().hashCode());
		result = prime * result + ((getActivityStatus() == null) ? 0 : getActivityStatus().hashCode());
		result = prime * result + ((getExtfield1() == null) ? 0 : getExtfield1().hashCode());
		result = prime * result + ((getExtfield2() == null) ? 0 : getExtfield2().hashCode());
		result = prime * result + ((getExtfield3() == null) ? 0 : getExtfield3().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}