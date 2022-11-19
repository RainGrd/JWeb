/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月7日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.common;

/**
 * Description：<br>
 * 新手指引 系统公告 对应后台栏目管理
 * 
 * @author shiliang.feng
 * @date 2016年1月7日
 * @version v1.0.0
 */
public class ColumnContentVo {

	private String pid;
	private String titleName;
	private String coluOrder;
	private String isCustomFile;
	private String url;
	private String webTag;
	private String isReading;
	private String content;
	private String createTime;
	private String filePid;
	private String fileName;
	private String fileUrl;

	private Integer isHome;// 是否在首页
	private Integer pageIndex;
	private Integer pageSize;

	public Integer getIsHome() {
		return isHome;
	}

	public void setIsHome(Integer isHome) {
		this.isHome = isHome;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getFilePid() {
		return filePid;
	}

	public void setFilePid(String filePid) {
		this.filePid = filePid;
	}

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
		this.pid = pid;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
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
		this.isCustomFile = isCustomFile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWebTag() {
		return webTag;
	}

	public void setWebTag(String webTag) {
		this.webTag = webTag;
	}

	public String getIsReading() {
		return isReading;
	}

	public void setIsReading(String isReading) {
		this.isReading = isReading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
