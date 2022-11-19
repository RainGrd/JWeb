package com.yscf.core.model.content.adv;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 广告信息管理
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
public class ContAdvInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4282127304515844139L;

	private String pid;

	private String avdName;

	/**
	 * 在前台系统显示的标示
	 */
	private String avdCode;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;

	private String lastBeginUpdateTime;

	private String lastEndUpdateTime;

	private String createBeginTime;

	private String createEndTime;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getAvdName() {
		return avdName;
	}

	public void setAvdName(String avdName) {
		this.avdName = avdName == null ? null : avdName.trim();
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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
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

	public String getAvdCode() {
		return avdCode;
	}

	public void setAvdCode(String avdCode) {
		this.avdCode = avdCode;
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
		ContAdvInfo other = (ContAdvInfo) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getAvdName() == null ? other.getAvdName() == null
						: this.getAvdName().equals(other.getAvdName()))
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
				&& (this.getVersion() == null ? other.getVersion() == null
						: this.getVersion().equals(other.getVersion()))
				&& (this.getLastBeginUpdateTime() == null ? other
						.getLastBeginUpdateTime() == null : this
						.getLastBeginUpdateTime().equals(
								other.getLastBeginUpdateTime()))
				&& (this.getLastEndUpdateTime() == null ? other
						.getLastEndUpdateTime() == null : this
						.getLastEndUpdateTime().equals(
								other.getLastEndUpdateTime()))
				&& (this.getCreateBeginTime() == null ? other
						.getCreateBeginTime() == null : this
						.getCreateBeginTime()
						.equals(other.getCreateBeginTime()))
				&& (this.getCreateEndTime() == null ? other.getCreateEndTime() == null
						: this.getCreateEndTime().equals(
								other.getCreateEndTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getAvdName() == null) ? 0 : getAvdName().hashCode());
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
				+ ((getLastBeginUpdateTime() == null) ? 0
						: getLastBeginUpdateTime().hashCode());
		result = prime
				* result
				+ ((getLastEndUpdateTime() == null) ? 0
						: getLastEndUpdateTime().hashCode());
		result = prime
				* result
				+ ((getCreateBeginTime() == null) ? 0 : getCreateBeginTime()
						.hashCode());
		result = prime
				* result
				+ ((getCreateEndTime() == null) ? 0 : getCreateEndTime()
						.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

}