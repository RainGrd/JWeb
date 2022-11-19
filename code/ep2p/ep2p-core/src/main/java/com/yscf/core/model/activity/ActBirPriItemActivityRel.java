package com.yscf.core.model.activity;

import java.util.Date;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 生日特权项和活动关系表 实体类
 * 
 * @author fengshiliang
 * @date 2015年10月13日
 * @version v1.0.0
 */
public class ActBirPriItemActivityRel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5785999525886792966L;

	private String pid;

	private String activityId;

	private String objectId;

	private String priType;

	private String birPriItemDesc;

	private String status;

	private String createUser;

	private Date createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	private String version;

	/**
	 * 特权id
	 */
	private String privilegeId;
	/**
	 * 特权名字
	 */
	private String privilegeName;
	/**
	 * 特权类型
	 */
	private String privilegeType;

	/**
	 * 编号
	 */
	private String actCode;
	/**
	 * 名字
	 */
	private String actName;
	/**
	 * 标签
	 */
	private String actTag;
	/**
	 * 活动参与人数
	 */
	private String participantsNumber;
	private String startDate;
	private String endDate;

	/**
	 * 产于vip生日特权活动总人数
	 */
	private int getAmount;

	public int getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(int getAmount) {
		this.getAmount = getAmount;
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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId == null ? null : objectId.trim();
	}

	public String getPriType() {
		return priType;
	}

	public void setPriType(String priType) {
		this.priType = priType == null ? null : priType.trim();
	}

	public String getBirPriItemDesc() {
		return birPriItemDesc;
	}

	public void setBirPriItemDesc(String birPriItemDesc) {
		this.birPriItemDesc = birPriItemDesc == null ? null : birPriItemDesc
				.trim();
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
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeType() {
		return privilegeType;
	}

	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActTag() {
		return actTag;
	}

	public void setActTag(String actTag) {
		this.actTag = actTag;
	}

	public String getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(String participantsNumber) {
		this.participantsNumber = participantsNumber;
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
		ActBirPriItemActivityRel other = (ActBirPriItemActivityRel) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getActivityId() == null ? other.getActivityId() == null
						: this.getActivityId().equals(other.getActivityId()))
				&& (this.getObjectId() == null ? other.getObjectId() == null
						: this.getObjectId().equals(other.getObjectId()))
				&& (this.getPriType() == null ? other.getPriType() == null
						: this.getPriType().equals(other.getPriType()))
				&& (this.getBirPriItemDesc() == null ? other
						.getBirPriItemDesc() == null : this.getBirPriItemDesc()
						.equals(other.getBirPriItemDesc()))
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
				&& (this.getPrivilegeId() == null ? other.getPrivilegeId() == null
						: this.getPrivilegeId().equals(
								other.getLastUpdateTime()))
				&& (this.getPrivilegeName() == null ? other.getPrivilegeName() == null
						: this.getPrivilegeName().equals(
								other.getPrivilegeName()))
				&& (this.getPrivilegeType() == null ? other.getPrivilegeType() == null
						: this.getPrivilegeType().equals(
								other.getPrivilegeType()))
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
				+ ((getActivityId() == null) ? 0 : getActivityId().hashCode());
		result = prime * result
				+ ((getObjectId() == null) ? 0 : getObjectId().hashCode());
		result = prime * result
				+ ((getPriType() == null) ? 0 : getPriType().hashCode());
		result = prime
				* result
				+ ((getBirPriItemDesc() == null) ? 0 : getBirPriItemDesc()
						.hashCode());
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
				+ ((getVersion() == null) ? 0 : getVersion().hashCode());

		result = prime
				* result
				+ ((getPrivilegeId() == null) ? 0 : getPrivilegeId().hashCode());
		result = prime
				* result
				+ ((getPrivilegeType() == null) ? 0 : getPrivilegeType()
						.hashCode());
		result = prime
				* result
				+ ((getPrivilegeName() == null) ? 0 : getPrivilegeName()
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

}