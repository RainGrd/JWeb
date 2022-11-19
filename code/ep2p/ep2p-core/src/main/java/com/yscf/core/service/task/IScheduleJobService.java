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

import java.util.List;

import com.yscf.core.model.task.ScheduleJob;

/**
 * Description：<br> 
 * 定时任务业务接口类
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
public interface IScheduleJobService {
	
	 int insertSelective(ScheduleJob record);
	 
	 int updateByPrimaryKeySelective(ScheduleJob record);
	 
	 ScheduleJob selectByPrimaryKey(String pid);
	 
	 int updateJobStaus(String pid,String jobStatus);
	 
	 List<ScheduleJob> selectAll();
}


