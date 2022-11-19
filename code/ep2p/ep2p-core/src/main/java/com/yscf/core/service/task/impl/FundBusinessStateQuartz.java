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
 * 2015年12月25日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.achievo.framework.util.DateUtil;
import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.business.BizFundBusinessState;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.business.IBizFundBusinessStateService;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * Description：<br> 
 * 资金交易状况表定时任务
 * @author  Yu.Zhang
 * @date    2015年12月25日
 * @version v1.0.0
 */
public class FundBusinessStateQuartz  implements IScheduleJobTask {
	private Logger logger = LoggerFactory.getLogger(FundBusinessStateQuartz.class);

	@Override
	public void execute(ScheduleJob scheduleJob) {
		logger.info(scheduleJob.getJobName()+" 定时调度任务开始"+DateUtil.format(DateUtil.getToday()));
		try{
			IBizFundBusinessStateService businessStateService = (IBizFundBusinessStateService) EscfApplicationContext.getBean("bizFundBusinessStateServiceImpl");
			
			String startTime = DateUtil.format(DateUtil.getToday(),"yyyy-MM-dd")+" 00:00:00";
			String endTime = DateUtil.format(DateUtil.getToday(),"yyyy-MM-dd")+" 23:59:59";
			
			BizFundBusinessState bizFundBusinessState = new BizFundBusinessState(); 
			bizFundBusinessState.setStartTime(startTime);
			bizFundBusinessState.setEndTime(endTime);
			
			businessStateService.executeSyncFundBusinessState(bizFundBusinessState);
		}catch(Exception e){
			logger.info("资金交易状况表 定时调度任务异常",e);
		}
		
		logger.info(scheduleJob.getJobName()+"定时调度任务结束"+DateUtil.format(DateUtil.getToday()));
	}

}


