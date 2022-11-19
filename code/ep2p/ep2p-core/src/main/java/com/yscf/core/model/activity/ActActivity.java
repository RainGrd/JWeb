package com.yscf.core.model.activity;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : ActActivity
 * @Description : 活动信息表JavaBean
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 上午11:05:49
 */
public class ActActivity extends BaseEntity {

	private static final long serialVersionUID = -2895566596953570L;

	private String pid;

	private String actCode;

	private String actTag;

	private String actName;

	private String startDate;

	private String endDate;

	private String objectId;

	private String activityType;

	private String smsId;

	private String isSmsWarn;

	private String isBirPrivilege;

	private String actDesc;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;
	/**
	 * 已选择的条件
	 */
	private String condIds;

	/**
	 * 已选择的特权
	 */
	private String birPriIds;

	private String birPriValues;

	private String birPriTypes;

	private String lastBeginUpdateTime;

	private String lastEndUpdateTime;

	private String createBeginTime;

	private String createEndTime;

	// 短信id
	private String smsCode;
	// 短信内容
	private String smsContent;
	private String getTime;

	// 适用开始时间-begin
	private String beginApplyStartData;
	// 适用开始时间-end
	private String endApplyStartData;
	// 适用结束时间-begin
	private String beginApplyFinishData;
	// 适用结束时间-end
	private String endApplyFinishData;
	// 活动参与人数
	private Integer participantsNumber;
	// 有效期
	private String expNumber;
	// 需要传给活动专区的活动状态
	private Integer activityAreaType;
	// 是否禁用 1.启用 2.禁用
	private Integer isDisable;

	public Integer getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Integer isDisable) {
		this.isDisable = isDisable;
	}

	public Integer getActivityAreaType() {
		return activityAreaType;
	}

	public void setActivityAreaType(Integer activityAreaType) {
		this.activityAreaType = activityAreaType;
	}

	public String getExpNumber() {
		return expNumber;
	}

	public void setExpNumber(String expNumber) {
		this.expNumber = expNumber;
	}

	public Integer getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(Integer participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

	public String getBeginApplyStartData() {
		return beginApplyStartData;
	}

	public void setBeginApplyStartData(String beginApplyStartData) {
		this.beginApplyStartData = beginApplyStartData;
	}

	public String getEndApplyStartData() {
		return endApplyStartData;
	}

	public void setEndApplyStartData(String endApplyStartData) {
		this.endApplyStartData = endApplyStartData;
	}

	public String getBeginApplyFinishData() {
		return beginApplyFinishData;
	}

	public void setBeginApplyFinishData(String beginApplyFinishData) {
		this.beginApplyFinishData = beginApplyFinishData;
	}

	public String getEndApplyFinishData() {
		return endApplyFinishData;
	}

	public void setEndApplyFinishData(String endApplyFinishData) {
		this.endApplyFinishData = endApplyFinishData;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode == null ? null : actCode.trim();
	}

	public String getActTag() {
		return actTag;
	}

	public void setActTag(String actTag) {
		this.actTag = actTag == null ? null : actTag.trim();
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName == null ? null : actName.trim();
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId == null ? null : objectId.trim();
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType == null ? null : activityType.trim();
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId == null ? null : smsId.trim();
	}

	public String getIsSmsWarn() {
		return isSmsWarn;
	}

	public void setIsSmsWarn(String isSmsWarn) {
		this.isSmsWarn = isSmsWarn == null ? null : isSmsWarn.trim();
	}

	public String getIsBirPrivilege() {
		return isBirPrivilege;
	}

	public void setIsBirPrivilege(String isBirPrivilege) {
		this.isBirPrivilege = isBirPrivilege == null ? null : isBirPrivilege.trim();
	}

	public String getActDesc() {
		return actDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc == null ? null : actDesc.trim();
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

	public String getCondIds() {
		return condIds;
	}

	public void setCondIds(String condIds) {
		this.condIds = condIds;
	}

	public String getBirPriIds() {
		return birPriIds;
	}

	public void setBirPriIds(String birPriIds) {
		this.birPriIds = birPriIds;
	}

	public String getBirPriValues() {
		return birPriValues;
	}

	public void setBirPriValues(String birPriValues) {
		this.birPriValues = birPriValues;
	}

	public String getBirPriTypes() {
		return birPriTypes;
	}

	public void setBirPriTypes(String birPriTypes) {
		this.birPriTypes = birPriTypes;
	}

	public String getLastBeginUpdateTime() {
		return lastBeginUpdateTime;
	}

	public void setLastBeginUpdateTime(String lastBeginUpdateTime) {
		this.lastBeginUpdateTime = lastBeginUpdateTime;
	}

	public String getLastEndUpdateTime() {
		return lastEndUpdateTime;
	}

	public void setLastEndUpdateTime(String lastEndUpdateTime) {
		this.lastEndUpdateTime = lastEndUpdateTime;
	}

	public String getCreateBeginTime() {
		return createBeginTime;
	}

	public void setCreateBeginTime(String createBeginTime) {
		this.createBeginTime = createBeginTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
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
		ActActivity other = (ActActivity) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getActCode() == null ? other.getActCode() == null : this.getActCode().equals(other.getActCode())) && (this.getActTag() == null ? other.getActTag() == null : this.getActTag().equals(other.getActTag())) && (this.getActName() == null ? other.getActName() == null : this.getActName().equals(other.getActName())) && (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate())) && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate())) && (this.getObjectId() == null ? other.getObjectId() == null : this.getObjectId().equals(other.getObjectId()))
				&& (this.getActivityType() == null ? other.getActivityType() == null : this.getActivityType().equals(other.getActivityType())) && (this.getSmsId() == null ? other.getSmsId() == null : this.getSmsId().equals(other.getSmsId())) && (this.getIsSmsWarn() == null ? other.getIsSmsWarn() == null : this.getIsSmsWarn().equals(other.getIsSmsWarn())) && (this.getIsBirPrivilege() == null ? other.getIsBirPrivilege() == null : this.getIsBirPrivilege().equals(other.getIsBirPrivilege())) && (this.getActDesc() == null ? other.getActDesc() == null : this.getActDesc().equals(other.getActDesc())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))

				&& (this.getBirPriIds() == null ? other.getBirPriIds() == null : this.getBirPriIds().equals(other.getBirPriIds())) && (this.getBirPriValues() == null ? other.getBirPriValues() == null : this.getBirPriValues().equals(other.getBirPriValues())) && (this.getBirPriTypes() == null ? other.getBirPriTypes() == null : this.getBirPriTypes().equals(other.getBirPriTypes())) && (this.getCondIds() == null ? other.getCondIds() == null : this.getCondIds().equals(other.getCondIds())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion())) && (this.getSmsCode() == null ? other.getSmsCode() == null : this.getSmsCode().equals(other.getSmsCode()))
				&& (this.getSmsContent() == null ? other.getSmsContent() == null : this.getSmsContent().equals(other.getSmsContent())) && (this.getLastBeginUpdateTime() == null ? other.getLastBeginUpdateTime() == null : this.getLastBeginUpdateTime().equals(other.getLastBeginUpdateTime())) && (this.getLastEndUpdateTime() == null ? other.getLastEndUpdateTime() == null : this.getLastEndUpdateTime().equals(other.getLastEndUpdateTime())) && (this.getCreateBeginTime() == null ? other.getCreateBeginTime() == null : this.getCreateBeginTime().equals(other.getCreateBeginTime())) && (this.getCreateEndTime() == null ? other.getCreateEndTime() == null : this.getCreateEndTime().equals(other.getCreateEndTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getActCode() == null) ? 0 : getActCode().hashCode());
		result = prime * result + ((getActTag() == null) ? 0 : getActTag().hashCode());
		result = prime * result + ((getActName() == null) ? 0 : getActName().hashCode());
		result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
		result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
		result = prime * result + ((getObjectId() == null) ? 0 : getObjectId().hashCode());
		result = prime * result + ((getActivityType() == null) ? 0 : getActivityType().hashCode());
		result = prime * result + ((getSmsId() == null) ? 0 : getSmsId().hashCode());
		result = prime * result + ((getIsSmsWarn() == null) ? 0 : getIsSmsWarn().hashCode());
		result = prime * result + ((getIsBirPrivilege() == null) ? 0 : getIsBirPrivilege().hashCode());
		result = prime * result + ((getActDesc() == null) ? 0 : getActDesc().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());

		result = prime * result + ((getCondIds() == null) ? 0 : getCondIds().hashCode());
		result = prime * result + ((getBirPriIds() == null) ? 0 : getBirPriIds().hashCode());
		result = prime * result + ((getBirPriValues() == null) ? 0 : getBirPriValues().hashCode());
		result = prime * result + ((getSmsCode() == null) ? 0 : getSmsCode().hashCode());
		result = prime * result + ((getSmsContent() == null) ? 0 : getSmsContent().hashCode());
		result = prime * result + ((getBirPriTypes() == null) ? 0 : getBirPriTypes().hashCode());
		result = prime * result + ((getLastBeginUpdateTime() == null) ? 0 : getLastBeginUpdateTime().hashCode());
		result = prime * result + ((getLastEndUpdateTime() == null) ? 0 : getLastEndUpdateTime().hashCode());
		result = prime * result + ((getCreateBeginTime() == null) ? 0 : getCreateBeginTime().hashCode());
		result = prime * result + ((getCreateEndTime() == null) ? 0 : getCreateEndTime().hashCode());
		return result;
	}
}