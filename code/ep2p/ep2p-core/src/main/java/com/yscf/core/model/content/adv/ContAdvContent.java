package com.yscf.core.model.content.adv;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * TODO 广告内容管理
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
public class ContAdvContent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5154868180744559228L;

	private String pid;

	private String advId;

	private String titleName;

	private String status;

	private String fileId;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;

	private String url;

	private String advOrder;

	private String lastBeginUpdateTime;

	private String lastEndUpdateTime;

	private String createBeginTime;

	private String createEndTime;

	private String fileUrl;
	// 文件名
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId == null ? null : advId.trim();
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName == null ? null : titleName.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId == null ? null : fileId.trim();
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getAdvOrder() {
		return advOrder;
	}

	public void setAdvOrder(String advOrder) {
		this.advOrder = advOrder;
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
		ContAdvContent other = (ContAdvContent) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getAdvId() == null ? other.getAdvId() == null : this
						.getAdvId().equals(other.getAdvId()))
				&& (this.getTitleName() == null ? other.getTitleName() == null
						: this.getTitleName().equals(other.getTitleName()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getFileId() == null ? other.getFileId() == null : this
						.getFileId().equals(other.getFileId()))
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
				&& (this.getUrl() == null ? other.getUrl() == null : this
						.getUrl().equals(other.getUrl()))
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
								other.getCreateEndTime()))
				&& (this.getFileUrl() == null ? other.getFileUrl() == null
						: this.getFileUrl().equals(other.getFileUrl()))
				&& (this.getAdvOrder() == null ? other.getAdvOrder() == null
						: this.getAdvOrder().equals(other.getAdvOrder()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getAdvId() == null) ? 0 : getAdvId().hashCode());
		result = prime * result
				+ ((getTitleName() == null) ? 0 : getTitleName().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getFileId() == null) ? 0 : getFileId().hashCode());
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
		result = prime * result
				+ ((getUrl() == null) ? 0 : getUrl().hashCode());
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
		result = prime * result
				+ ((getAdvOrder() == null) ? 0 : getAdvOrder().hashCode());
		result = prime * result
				+ ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());
		return result;
	}
}