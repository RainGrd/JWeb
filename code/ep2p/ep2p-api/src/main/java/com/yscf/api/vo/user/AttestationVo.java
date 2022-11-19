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
 * 
 * @ClassName : AttestationVo
 * @Description : 实名认证VO
 * @Author : Qing.Cai
 * @Date : 2016年1月23日 下午5:04:47
 */
public class AttestationVo {

	/** 真实姓名 */
	private String sname;
	/** 身份证号码 */
	private String identificationNo;
	/** 身份证正面图片URL */
	private String identificationBackImage;
	/** 身份证反面图片URL */
	private String identificationFrontImage;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getIdentificationBackImage() {
		return identificationBackImage;
	}

	public void setIdentificationBackImage(String identificationBackImage) {
		this.identificationBackImage = identificationBackImage;
	}

	public String getIdentificationFrontImage() {
		return identificationFrontImage;
	}

	public void setIdentificationFrontImage(String identificationFrontImage) {
		this.identificationFrontImage = identificationFrontImage;
	}

}
