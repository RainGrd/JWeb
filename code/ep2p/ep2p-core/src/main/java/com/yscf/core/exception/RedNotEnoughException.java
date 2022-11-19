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
 * @ClassName : RedNotEnoughException
 * @Description : 红包不够异常(已经领取完)
 * @Author : Qing.Cai
 * @Date : 2016年3月7日 下午6:46:00
 */
public class RedNotEnoughException extends Exception {

	private static final long serialVersionUID = 1L;

	public RedNotEnoughException() {
		super();
	}

	public RedNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public RedNotEnoughException(String message) {
		super(message);
	}

	public RedNotEnoughException(Throwable cause) {
		super(cause);
	}

}
