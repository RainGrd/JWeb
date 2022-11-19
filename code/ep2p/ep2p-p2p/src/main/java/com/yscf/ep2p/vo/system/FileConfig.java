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
 * 2015年9月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.vo.system;

/**
 * Description：<br> 
 * 文件上传配置信息
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
public class FileConfig {
	
	/**
	 * 文件上传下载根目录
	 */
	private String fileRoot;
	
	/**
	 * file.type
	 */
	private String fileRootIs;
	
	/**
	 * 允许上传文件最大值
	 */
	private Integer maxFileSize;
	
	/**
	 * 允许最大请求数
	 */
	private Integer maxRequestSize;
	
	/**
	 * 允许上传的文件类型
	 */
	private String fileType;
	
	/**
	 * Excel导出的临时文件夹，发布时一定要进行配置
	 */
	private String excleBasePath;
	
	

	public Integer getMaxRequestSize() {
		return maxRequestSize;
	}

	public void setMaxRequestSize(Integer maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	public String getFileRoot() {
		return fileRoot;
	}

	public void setFileRoot(String fileRoot) {
		this.fileRoot = fileRoot;
	}

	public String getFileRootIs() {
		return fileRootIs;
	}

	public void setFileRootIs(String fileRootIs) {
		this.fileRootIs = fileRootIs;
	}

	public Integer getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(Integer maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public String []  getFileType() {
		return fileType.split(",");
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getExcleBasePath() {
		return excleBasePath;
	}

	public void setExcleBasePath(String excleBasePath) {
		this.excleBasePath = excleBasePath;
	}
}


