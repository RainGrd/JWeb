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
 * 2015年12月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task;

import com.yscf.core.model.task.ScheduleJobLog;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
public interface IScheduleJobServiceLog {
	
	int insertSelective(ScheduleJobLog record);
	
	int updateByPrimaryKeySelective(ScheduleJobLog record);
	
}


