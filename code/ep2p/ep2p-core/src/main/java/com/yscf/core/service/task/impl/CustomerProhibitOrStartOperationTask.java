package com.yscf.core.service.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.task.IScheduleJobTask;
import com.yscf.core.service.user.ICusTomerService;
/**
 * Description：<br> 
 * 客户禁止或启用操作的定时任务
 * @author  heng.wang
 * @date    2016年1月12日
 * @version v1.0.0
 */
public class CustomerProhibitOrStartOperationTask implements IScheduleJobTask{

	private Logger logger = LoggerFactory.getLogger(CustomerProhibitOrStartOperationTask.class);
	@Override
	public void execute(ScheduleJob scheduleJob) {
		try {
			// 获取具体的业务操作类
			ICusTomerService service = (ICusTomerService) EscfApplicationContext.getBean("cusTomerService");
			// 客户冻结操作
			service.prohibitOrStartOperation();
		} catch (Exception e) {
			logger.info("客户禁止或启用操作定时任务处理异常:",e);
		}
		
	}

}
