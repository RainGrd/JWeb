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
 * 2015年12月24日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.send;

/**
 * Description：<br>
 * 发送短信VO
 * @author JingYu.Dai
 * @date 2015年12月24日
 * @version v1.0.0
 */
public class SendVerifySmsVo {

	/**
	 * 发送类型 1 及时短信 2 定时短信 3 语音信息
	 */
	private Integer sendType;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 验证码
	 */
	private String verifyCode;

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
