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
 * 2015年12月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.activity.IActActivityService;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * 
 * @ClassName : SystemActivityTask
 * @Description : 后台活动定时任务
 * @Author : Qing.Cai
 * @Date : 2015年12月24日 上午11:12:58
 */
public class SystemActivityTask implements IScheduleJobTask {
	
	private Logger logger = LoggerFactory.getLogger(SystemActivityTask.class);

	@Override
	public void execute(ScheduleJob scheduleJob) {
		try {
			// 获取具体的业务操作类
			IActActivityService iActActivityService = (IActActivityService) EscfApplicationContext.getBean("actActivityServiceImpl");
			// 开始活动的定时任务查询
			iActActivityService.activityBeginTask();
			// 结束活动的定时任务查询
			iActActivityService.activityEndTask();
		} catch (FrameworkException e) {
			logger.info("活动定时任务处理异常:",e);
		}
	}

}
