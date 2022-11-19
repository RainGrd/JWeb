/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 系统自动补单任务
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月25日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import org.apache.log4j.Logger;

import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.financial.IBizRechargeOnlineService;
import com.yscf.core.service.financial.impl.BizRechargeOnlineServiceImpl;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * Description：<br> 
 * 系统自动补单任务
 * @author  Jie.Zou
 * @date    2015年12月25日
 * @version v1.0.0
 */
public class PayRechargeTask implements IScheduleJobTask{

	Logger logger = Logger.getLogger(PayRechargeTask.class);
	
	@Override
	public void execute(ScheduleJob scheduleJob) {
		logger.info(scheduleJob.getJobName() + "已经启动");
		IBizRechargeOnlineService rechargeOnlineService = (BizRechargeOnlineServiceImpl)EscfApplicationContext.getBean("bizRechargeOnlineServiceImpl");
		rechargeOnlineService.doRechargeByTask();
		logger.info(scheduleJob.getJobName() + "已经结束");
	}
	
}


