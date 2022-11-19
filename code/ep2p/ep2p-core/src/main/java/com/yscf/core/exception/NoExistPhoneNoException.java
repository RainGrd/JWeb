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
 * 2015年12月14日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.exception;

/**
 * 
 * @ClassName : NoExistPhoneNoException
 * @Description : 手机号码不存在异常
 * @Author : Qing.Cai
 * @Date : 2016年2月25日 下午4:04:16
 */
public class NoExistPhoneNoException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoExistPhoneNoException() {
		super();
	}

	public NoExistPhoneNoException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoExistPhoneNoException(String message) {
		super(message);
	}

	public NoExistPhoneNoException(Throwable cause) {
		super(cause);
	}

}
