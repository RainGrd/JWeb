package com.yscf.core.service.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.task.IScheduleJobTask;
import com.yscf.core.service.user.ICusTomerService;
/**
 * Description：<br> 
 * 更新客户表的vip时长
 * @author  wangheng
 * @date    2016年3月5日
 * @version v1.0.0
 */
public class UpdateVipTimeTask implements IScheduleJobTask{

	private Logger logger = LoggerFactory.getLogger(UpdateVipTimeTask.class);
	@Override
	public void execute(ScheduleJob scheduleJob) {
		
		logger.info("执行每天减去vip时长定时器开始");
		
		ICusTomerService service  = (ICusTomerService)EscfApplicationContext.getBean("cusTomerService");
		
		try {
			service.reduceVipTimeByDay();
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error("执行每天减去vip时长定时器异常"+e.getMessage());
			}
		}
		
		logger.info("执行每天减去vip时长定时器结束");
		
	}

}
