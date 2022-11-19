/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标处理方法类--方法处理接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月18日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.autobidding;

/**
 * Description：<br> 
 * 自动投标处理方法类--方法处理接口
 * @author  Lin Xu
 * @date    2015年12月18日
 * @version v1.0.0
 */
public interface IAutoBiddingProcessService {
	
	/**
	 * Description：<br> 
	 * 通过投标的id进行自动投标
	 * @author  Lin Xu
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param bspid
	 */
	public void updateProcessAutoBidding(String bspid);
	
}


