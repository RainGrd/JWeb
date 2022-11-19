/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户日志管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.Map;

import com.yscf.core.model.system.SysIpPvCount;


/**
 * Description：<br> 
 * Ip pv 统计服务接口
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
public interface ISysIpPvCountService {
	
	/**
	 * 
	 * Description：<br> 
	 * ip pv 执行统计
	 * @author  Yu.Zhang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param record
	 */
	public void executeIpPvCount(SysIpPvCount record);
	
	
	public Map<String, Object> selectNearlySevenData();
	
}


