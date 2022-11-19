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
 * 2015年12月21日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.task.core.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.task.IScheduleJobService;
import com.yscf.task.core.controller.ScheduleJobController;

/**
 * Description：<br> 
 * 定时任务服务启动加载类
 * @author  Yu.Zhang
 * @date    2015年12月21日
 * @version v1.0.0
 */
public class QuartzInit {
	
	private Logger logger = LoggerFactory.getLogger(QuartzInit.class);
	@Autowired
	private IScheduleJobService scheduleJobService;
	
	@Autowired
	private QuartzManager quartzManager;

	public void init(){
		//获取任务信息数据
		List<ScheduleJob> jobList = scheduleJobService.selectAll();
		if(null!=jobList && jobList.size()>0){
			for (ScheduleJob job : jobList) {
				// 将数据库里查询出来的数据加载到Quartz中
				try{
					quartzManager.createScheduleJob(job);
				}catch(Exception e){
					if(logger.isDebugEnabled()){
						logger.debug("定时任务名： "+job.getJobName()+",组名："+job.getJobGroup() +" 加载到定时任务失败/n"+e.getMessage());
					}
					e.printStackTrace();
				}
			}
		}
	}

}


