/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 后台报表统计信息任务信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import org.apache.log4j.Logger;

import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.report.impl.ReportCountQuartzServiceImpl;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * Description：<br> 
 * 后台报表统计信息任务信息
 * @author  Lin Xu
 * @date    2015年12月24日
 * @version v1.0.0
 */
public class ReportCountJobImpl implements IScheduleJobTask{
	
	Logger logger = Logger.getLogger(ReportCountJobImpl.class);

	@Override
	public void execute(ScheduleJob scheduleJob) {
		logger.info(scheduleJob.getJobName() + "已经启动");
		ReportCountQuartzServiceImpl reportcountquartzserviceimpl = (ReportCountQuartzServiceImpl) EscfApplicationContext.getBean("reportCountQuartzServiceImpl");
		reportcountquartzserviceimpl.cumulativeInvestReportService();
		reportcountquartzserviceimpl.alsoPrincipalReportService();
		reportcountquartzserviceimpl.userBenefitReportService();
		reportcountquartzserviceimpl.grantProfitReportService();
		logger.info(scheduleJob.getJobName() + "结束");
	}

}


