package com.yscf.api.controller.send;

/**
 * Description：<br> 
 * 短信 
 * @author  JingYu.Dai
 * @date    2016年1月4日
 * @version v1.0.0
 */
public class MobileVerifyUtil {
	public static final String SESSION_BIND_SMS_CODE = "BIND_SMS_CODE"; //绑定手机的会话key
	public static final String SESSION_REG_SMS_CODE = "REG_SMS_CODE"; //注册发送验证码的会话key
	public static final String SESSION_FORGET_CODE = "FORGET_CODE"; //忘记密码发送验证码的会话key
	/**
	 * 提现发送验证码的会话key
	 */
	public static final String SESSION_WITHDRAWAL_CODE = "WITHDRAWAL_CODE";
	

}
