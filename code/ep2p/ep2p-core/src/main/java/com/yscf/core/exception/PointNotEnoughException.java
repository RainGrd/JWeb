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
 * @ClassName : PointNotEnoughException
 * @Description : 积分不够异常
 * @Author : Qing.Cai
 * @Date : 2016年1月18日 下午3:22:32
 */
public class PointNotEnoughException extends Exception {

	private static final long serialVersionUID = 1L;

	public PointNotEnoughException() {
		super();
	}

	public PointNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public PointNotEnoughException(String message) {
		super(message);
	}

	public PointNotEnoughException(Throwable cause) {
		super(cause);
	}

}
