package com.yscf.core.model.content;

import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

public class SysNoticeReadRecord extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3897348933627361717L;

	private String pid;

	/**
	 * 客户id
	 */
	private String customerId;

	/**
	 * 栏目内容id
	 */
	private String columnContentId;
	/**
	 * 阅读时间
	 */
	private Date readTime;

	private String extfield1;

	private String extfield2;

	private String extfield3;

	private Date createTime;

	private String createUser;

	private Date lastUpdateTime;

	private String lastUpdateUser;

	private String status;

	private String version;

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

	public String getColumnContentId() {
		return columnContentId;
	}

	public void setColumnContentId(String columnContentId) {
		this.columnContentId = columnContentId == null ? null : columnContentId
				.trim();
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
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
		SysNoticeReadRecord other = (SysNoticeReadRecord) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getCustomerId() == null ? other.getCustomerId() == null
						: this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getColumnContentId() == null ? other
						.getColumnContentId() == null : this
						.getColumnContentId()
						.equals(other.getColumnContentId()))
				&& (this.getReadTime() == null ? other.getReadTime() == null
						: this.getReadTime().equals(other.getReadTime()))
				&& (this.getExtfield1() == null ? other.getExtfield1() == null
						: this.getExtfield1().equals(other.getExtfield1()))
				&& (this.getExtfield2() == null ? other.getExtfield2() == null
						: this.getExtfield2().equals(other.getExtfield2()))
				&& (this.getExtfield3() == null ? other.getExtfield3() == null
						: this.getExtfield3().equals(other.getExtfield3()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getLastUpdateTime() == null ? other
						.getLastUpdateTime() == null : this.getLastUpdateTime()
						.equals(other.getLastUpdateTime()))
				&& (this.getLastUpdateUser() == null ? other
						.getLastUpdateUser() == null : this.getLastUpdateUser()
						.equals(other.getLastUpdateUser()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
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
		result = prime
				* result
				+ ((getColumnContentId() == null) ? 0 : getColumnContentId()
						.hashCode());
		result = prime * result
				+ ((getReadTime() == null) ? 0 : getReadTime().hashCode());
		result = prime * result
				+ ((getExtfield1() == null) ? 0 : getExtfield1().hashCode());
		result = prime * result
				+ ((getExtfield2() == null) ? 0 : getExtfield2().hashCode());
		result = prime * result
				+ ((getExtfield3() == null) ? 0 : getExtfield3().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result
				+ ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime
				* result
				+ ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime()
						.hashCode());
		result = prime
				* result
				+ ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser()
						.hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}