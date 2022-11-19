/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户登录Vo
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月19日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.vo.system;

/**
 * Description：<br> 
 * 用户登录Vo
 * @author  Yu.Zhang
 * @date    2015年11月19日
 * @version v1.0.0
 */
public class LoginVo {
	/**
	 * 用户登录账号
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 错误次数
	 */
	private Integer errorCount;
	
	/**
	 * 剩余次数
	 */
	private Integer surplusCount;
	
	/**
	 * 是否记住用户名 1 记住，0不需要记住
	 */
	private String isSaveCookie;
	
	public String getIsSaveCookie() {
		return isSaveCookie;
	}

	public void setIsSaveCookie(String isSaveCookie) {
		this.isSaveCookie = isSaveCookie;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getSurplusCount() {
		return surplusCount;
	}

	public void setSurplusCount(Integer surplusCount) {
		this.surplusCount = surplusCount;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}


