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
 * @ClassName : CodeIsExistenceException
 * @Description : 编号存在异常
 * @Author : Qing.Cai
 * @Date : 2016年2月26日 下午3:55:48
 */
public class CodeIsExistenceException extends Exception {

	private static final long serialVersionUID = 1L;

	public CodeIsExistenceException() {
		super();
	}

	public CodeIsExistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CodeIsExistenceException(String message) {
		super(message);
	}

	public CodeIsExistenceException(Throwable cause) {
		super(cause);
	}

}
