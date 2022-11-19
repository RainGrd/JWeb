package com.yscf.core.model.content;

import com.achievo.framework.entity.BaseEntity;

public class ColumnContent extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2264086137127139648L;

	private String pid;

	private String titleName;

	private String status;

	private String isLowerLevel;

	private String coluOrder;

	private String isCustomFile;

	private String url;

	private String parentId;

	private String coluId;

	private String createUser;

	private String createTime;

	private String dictContName;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;
	/**
	 * 栏目内容选择的标签
	 */
	private String tagIds;
	/**
	 * 标签的主键
	 */
	private String tagPids;

	private String content;

	private String lastBeginUpdateTime;

	private String lastEndUpdateTime;

	private String createBeginTime;

	private String createEndTime;
	/**
	 * 前台标示 主要是用在首页 媒体报道 最新动态
	 */
	private String webTag;
	/**
	 * 封面 路径
	 */
	private String imgUrl;

	private String dictId;
	// 是否改变了文件 （文件上传标示）
	private String isChangeFile;
	// 文件上传路径
	private String fileUrl;
	// 文件名
	private String fileName;
	// 文件类型
	private String fileType;

	private String isChangeFileImg;
	// 文件上传路径
	private String fileUrlImg;
	// 文件名
	private String fileNameImg;
	// 文件类型
	private String fileTypeImg;
	/**
	 * 是否阅读 1:未读 2:已读
	 */
	private String isReading;
	/**
	 * 开始时间
	 */
	private String beginTime;
	/**
	 * 结束时间
	 */
	private String endTime;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsReading() {
		return isReading;
	}

	public void setIsReading(String isReading) {
		this.isReading = isReading;
	}

	public String getIsChangeFileImg() {
		return isChangeFileImg;
	}

	public void setIsChangeFileImg(String isChangeFileImg) {
		this.isChangeFileImg = isChangeFileImg;
	}

	public String getFileUrlImg() {
		return fileUrlImg;
	}

	public void setFileUrlImg(String fileUrlImg) {
		this.fileUrlImg = fileUrlImg;
	}

	public String getFileNameImg() {
		return fileNameImg;
	}

	public void setFileNameImg(String fileNameImg) {
		this.fileNameImg = fileNameImg;
	}

	public String getFileTypeImg() {
		return fileTypeImg;
	}

	public void setFileTypeImg(String fileTypeImg) {
		this.fileTypeImg = fileTypeImg;
	}

	public String getWebTag() {
		return webTag;
	}

	public void setWebTag(String webTag) {
		this.webTag = webTag;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	// 文件pid
	private String filePid;

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePid() {
		return filePid;
	}

	public void setFilePid(String filePid) {
		this.filePid = filePid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
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

	public String getIsLowerLevel() {
		return isLowerLevel;
	}

	public void setIsLowerLevel(String isLowerLevel) {
		this.isLowerLevel = isLowerLevel == null ? null : isLowerLevel.trim();
	}

	public String getColuOrder() {
		return coluOrder;
	}

	public void setColuOrder(String coluOrder) {
		this.coluOrder = coluOrder;
	}

	public String getIsCustomFile() {
		return isCustomFile;
	}

	public void setIsCustomFile(String isCustomFile) {
		this.isCustomFile = isCustomFile == null ? null : isCustomFile.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId == null ? null : parentId.trim();
	}

	public String getColuId() {
		return coluId;
	}

	public void setColuId(String coluId) {
		this.coluId = coluId == null ? null : coluId.trim();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
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

	public String getTagIds() {
		return tagIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public String getTagPids() {
		return tagPids;
	}

	public void setTagPids(String tagPids) {
		this.tagPids = tagPids;
	}

	public String getDictContName() {
		return dictContName;
	}

	public void setDictContName(String dictContName) {
		this.dictContName = dictContName;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getIsChangeFile() {
		return isChangeFile;
	}

	public void setIsChangeFile(String isChangeFile) {
		this.isChangeFile = isChangeFile;
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
		ColumnContent other = (ColumnContent) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getTitleName() == null ? other.getTitleName() == null
						: this.getTitleName().equals(other.getTitleName()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getIsLowerLevel() == null ? other.getIsLowerLevel() == null
						: this.getIsLowerLevel()
								.equals(other.getIsLowerLevel()))
				&& (this.getColuOrder() == null ? other.getColuOrder() == null
						: this.getColuOrder().equals(other.getColuOrder()))
				&& (this.getIsCustomFile() == null ? other.getIsCustomFile() == null
						: this.getIsCustomFile()
								.equals(other.getIsCustomFile()))
				&& (this.getUrl() == null ? other.getUrl() == null : this
						.getUrl().equals(other.getUrl()))
				&& (this.getParentId() == null ? other.getParentId() == null
						: this.getParentId().equals(other.getParentId()))
				&& (this.getColuId() == null ? other.getColuId() == null : this
						.getColuId().equals(other.getColuId()))
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
				&& (this.getDictContName() == null ? other.getDictContName() == null
						: this.getDictContName()
								.equals(other.getDictContName()))

				&& (this.getTagIds() == null ? other.getTagIds() == null : this
						.getTagIds().equals(other.getTagIds()))
				&& (this.getTagPids() == null ? other.getTagPids() == null
						: this.getTagPids().equals(other.getTagPids()))

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
				&& (this.getDictId() == null ? other.getDictId() == null : this
						.getDictId().equals(other.getDictId()))
				&& (this.getIsChangeFile() == null ? other.getIsChangeFile() == null
						: this.getIsChangeFile()
								.equals(other.getIsChangeFile()))

				&& (this.getVersion() == null ? other.getVersion() == null
						: this.getVersion().equals(other.getVersion()))
				&& (this.getContent() == null ? other.getContent() == null
						: this.getContent().equals(other.getContent()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getTitleName() == null) ? 0 : getTitleName().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime
				* result
				+ ((getIsLowerLevel() == null) ? 0 : getIsLowerLevel()
						.hashCode());
		result = prime * result
				+ ((getColuOrder() == null) ? 0 : getColuOrder().hashCode());
		result = prime
				* result
				+ ((getIsCustomFile() == null) ? 0 : getIsCustomFile()
						.hashCode());
		result = prime * result
				+ ((getUrl() == null) ? 0 : getUrl().hashCode());
		result = prime * result
				+ ((getParentId() == null) ? 0 : getParentId().hashCode());
		result = prime * result
				+ ((getColuId() == null) ? 0 : getColuId().hashCode());
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
				+ ((getTagIds() == null) ? 0 : getTagIds().hashCode());
		result = prime * result
				+ ((getTagPids() == null) ? 0 : getTagPids().hashCode());
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
		result = prime
				* result
				+ ((getDictContName() == null) ? 0 : getDictContName()
						.hashCode());
		result = prime * result
				+ ((getDictId() == null) ? 0 : getDictId().hashCode());
		result = prime
				* result
				+ ((getIsChangeFile() == null) ? 0 : getIsChangeFile()
						.hashCode());

		result = prime * result
				+ ((getContent() == null) ? 0 : getContent().hashCode());
		return result;
	}
}