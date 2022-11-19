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
 * Description：<br> 
 * vip生日特权定时器
 * @author  shiliang.feng
 * @date    2016年3月8日
 * @version v1.0.0
 */
public class SystemActivityVIPBirPirTask implements IScheduleJobTask {
	
	private Logger logger = LoggerFactory.getLogger(SystemActivityVIPBirPirTask.class);

	@Override
	public void execute(ScheduleJob scheduleJob) {
		try {
			// 获取具体的业务操作类
			IActActivityService iActActivityService = (IActActivityService) EscfApplicationContext.getBean("actActivityServiceImpl");
			// 开始活动的定时任务查询
			iActActivityService.birPriRelByActivityId(); 
		} catch (FrameworkException e) {
			logger.info("活动定时任务处理异常:",e);
		}
	}

}
