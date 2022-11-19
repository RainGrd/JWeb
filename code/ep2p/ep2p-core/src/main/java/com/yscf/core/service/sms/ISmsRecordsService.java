/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 消息记录服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.sms;

/**
 * Description：<br> 
 * 消息记录服务接口
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
public interface ISmsRecordsService {

	/**
	 * 
	 * Description：<br> 
	 * 根据手机号码统计当前发送了多少条手机信息
	 * @author  Yu.Zhang
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param mobiles
	 * @return
	 */
	public Integer selectTodayCountByMobiles(String mobiles);
	
	
	
}


