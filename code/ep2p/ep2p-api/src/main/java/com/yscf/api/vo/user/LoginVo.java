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
 * 2016年1月4日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.user;

/**
 * Description：<br> 
 * 用户登录，注册VO
 * @author  Yu.Zhang
 * @date    2016年1月4日
 * @version v1.0.0
 */
public class LoginVo {

	/**
	 * 登陆名
	 */
	private String loginName;
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 是否不需要验证密码登陆 yes 需要验证 no 不需要验证
	 */
	private String loginFlag;
	
	/**
	 * 手机号码
	 */
	private String phoneNo;
	
	/**
	 * 推介码
	 */
	private String referralCode;
	
	/**
	 * 手机验证码
	 */
	private String phoneCode;
	
	/**
	 * 移动设备的机器代码
	 */
	private String mobileDeviceMachineCode;
	
	public String getMobileDeviceMachineCode() {
		return mobileDeviceMachineCode;
	}

	public void setMobileDeviceMachineCode(String mobileDeviceMachineCode) {
		this.mobileDeviceMachineCode = mobileDeviceMachineCode;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(String loginFlag) {
		this.loginFlag = loginFlag;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

}


