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
 * 2015年12月24日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.task.IScheduleJobTask;
import com.yscf.core.service.user.ICusTomerService;

/**
 * Description：<br> 
 * VIP用户每天定时添加经验值定时器
 * @author  zhangyu
 * @date    2015年12月24日
 * @version v1.0.0
 */
public class VIPToDayQuartz implements IScheduleJobTask {
	
	private Logger logger = LoggerFactory.getLogger(VIPToDayQuartz.class);
	
	@Override
	public void execute(ScheduleJob scheduleJob) {
		
		logger.info("执行VIP用户每天定时添加经验值定时器开始");
		
		ICusTomerService service = (ICusTomerService)EscfApplicationContext.getBean("cusTomerService");
		try {
			service.updateEmpiricalValueToDay();
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.error("执行VIP用户每天定时添加经验值定时器异常"+e.getMessage());
			}
		}
		
		logger.info("执行VIP用户每天定时添加经验值定时器结束");
	}
}

