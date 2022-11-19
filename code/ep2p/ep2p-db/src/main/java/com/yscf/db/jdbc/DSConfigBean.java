/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月18日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.db.jdbc;

/**
 * Description：<br> 
 * 配置文件Bean类 
 * @author  JingYu.Dai
 * @date    2016年1月18日
 * @version v1.0.0
 */
public class DSConfigBean {
	
	private String type = ""; // 数据库类型
	private String name = ""; // 连接池名字
	private String driver = ""; // 数据库驱动
	private String url = ""; // 数据库url
	private String username = ""; // 用户名
	private String password = ""; // 密码
	private int maxconn = 0; // 最大连接数

	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return the maxconn
	 */
	public int getMaxconn() {
		return maxconn;
	}

	/**
	 * @param maxconn
	 *            the maxconn to set
	 */
	public void setMaxconn(int maxconn) {
		this.maxconn = maxconn;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
