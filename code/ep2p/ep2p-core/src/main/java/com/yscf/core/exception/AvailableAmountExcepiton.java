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
 * Description：<br> 
 * 可用余额不足异常
 * @author  JunJie.Liu
 * @date    2015年12月14日
 * @version v1.0.0
 */
public class AvailableAmountExcepiton extends RuntimeException{

	private static final long serialVersionUID = -6355767200120553511L;

	public AvailableAmountExcepiton() {
		super();
	}

	public AvailableAmountExcepiton(String message, Throwable cause) {
		super(message, cause);
	}

	public AvailableAmountExcepiton(String message) {
		super(message);
	}

	public AvailableAmountExcepiton(Throwable cause) {
		super(cause);
	}
	
}


