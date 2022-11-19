/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * API上传文件对象信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.system;

import java.io.InputStream;

import com.achievo.framework.vo.BaseVo;

/**
 * Description：<br> 
 * API上传文件对象信息
 * @author  Lin Xu
 * @date    2016年1月25日
 * @version v1.0.0
 */
public class ApiFileObject extends BaseVo {
	private static final long serialVersionUID = -4618520493115417638L;
	//文件属性名
	private String propname;
	//文件名
	private String filename;
	//文件类型
	private String filetype;
	//文件对象流
	private InputStream fileObject;
	//保存绝对路径
	private String absolutePath;
	//保存相对路径
	private String relativepath;
	//类型是否匹配
	private Boolean istypeRight = true;
	
	public String getPropname() {
		return propname;
	}
	public void setPropname(String propname) {
		this.propname = propname;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public InputStream getFileObject() {
		return fileObject;
	}
	public void setFileObject(InputStream fileObject) {
		this.fileObject = fileObject;
	}
	public String getAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getRelativepath() {
		return relativepath;
	}
	public void setRelativepath(String relativepath) {
		this.relativepath = relativepath;
	}
	public Boolean getIstypeRight() {
		return istypeRight;
	}
	public void setIstypeRight(Boolean istypeRight) {
		this.istypeRight = istypeRight;
	}
	
}


