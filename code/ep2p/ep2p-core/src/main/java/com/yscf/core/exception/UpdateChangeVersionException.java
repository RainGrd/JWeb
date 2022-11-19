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
 * 2015年12月16日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.exception;

/**
 * Description：<br> 
 * 更新记录失败，版本号已经改变异常
 * @author  JunJie.Liu
 * @date    2015年12月16日
 * @version v1.0.0
 */
public class UpdateChangeVersionException extends RuntimeException{

	private static final long serialVersionUID = -8367595468064364570L;

	public UpdateChangeVersionException() {
		super();
	}

	public UpdateChangeVersionException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateChangeVersionException(String message) {
		super(message);
	}

	public UpdateChangeVersionException(Throwable cause) {
		super(cause);
	}
	

}


