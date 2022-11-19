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
 * @Description : 加息劵不够异常(已经领取完)
 * @Author : Qing.Cai
 * @Date : 2016年3月7日 下午6:47:00
 */
public class InterestNotEnoughException extends Exception {

	private static final long serialVersionUID = 1L;

	public InterestNotEnoughException() {
		super();
	}

	public InterestNotEnoughException(String message, Throwable cause) {
		super(message, cause);
	}

	public InterestNotEnoughException(String message) {
		super(message);
	}

	public InterestNotEnoughException(Throwable cause) {
		super(cause);
	}

}
