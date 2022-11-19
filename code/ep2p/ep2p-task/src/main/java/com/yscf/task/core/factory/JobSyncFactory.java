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
package com.yscf.task.core.factory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.model.task.ScheduleJobLog;
import com.yscf.core.service.task.IScheduleJobService;
import com.yscf.core.service.task.IScheduleJobServiceLog;
import com.yscf.core.service.task.impl.ScheduleJobServiceLogImpl;



/**
 * Description：<br> 
 * 定时任务工厂类
 * @author  Yu.Zhang
 * @date    2015年12月21日
 * @version v1.0.0
 */
@DisallowConcurrentExecution
public class JobSyncFactory implements Job {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("任务成功运行");
        ScheduleJob scheduleJob = (ScheduleJob)context.getMergedJobDataMap().get("JOB_PARAM_KEY");
        IScheduleJobServiceLog logService = null;
        IScheduleJobService jobService = null;
        String content = null;
        boolean execStatus = false;
        try {
        	logService =  (IScheduleJobServiceLog) EscfApplicationContext.getBean("scheduleJobServiceLog"); 
        	jobService  = (IScheduleJobService) EscfApplicationContext.getBean("scheduleJobService"); 
        	
        	content = "执行触发器";
        	// 记录触发日志
        	insertLog(content,scheduleJob, logService);
        	// 更新任务状态为处理中
        	jobService.updateJobStaus(scheduleJob.getPid(), Constant.JOB_STATUS_2);
        	
        	// 任务调度
			Class clazz = Class.forName(scheduleJob.getClassName());
			Method method = clazz.getMethod("execute",ScheduleJob.class);
			method.invoke(clazz.newInstance(),scheduleJob);
			
			// 记录触发日志
			content = "执行完成";
			insertLog(content,scheduleJob, logService);
			// 更新任务状态为处理完成
        	jobService.updateJobStaus(scheduleJob.getPid(), Constant.JOB_STATUS_4);
		} catch (ClassNotFoundException e) {
			// 记录触发日志
			content = "执行异常 ClassNotFoundException ： "+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		} catch (InstantiationException e) {
			// 记录触发日志
			content = "执行异常 ：InstantiationException ："+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			content = "执行异常 ：IllegalAccessException ："+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			content = "执行异常 ：IllegalArgumentException ："+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			content = "执行异常 ：InvocationTargetException ："+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		} catch (SecurityException e) {
			content = "执行异常 ：SecurityException ："+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			content = "执行异常 ：NoSuchMethodException ："+e.getMessage();
			execStatus = true;
			e.printStackTrace();
		}
        
        // 是否出现异常，true为出现异常，记录日志
        if(execStatus){
        	// 记录触发日志
        	insertLog(content,scheduleJob, logService);
        	// 更新任务状态为异常
        	jobService.updateJobStaus(scheduleJob.getPid(), Constant.JOB_STATUS_5);
        }
        System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
	}

	/**
	 * 
	 * Description：<br> 
	 * 记录操作日志
	 * @author  Yu.Zhang
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @param logService
	 */
	private void insertLog(String content,ScheduleJob scheduleJob,
			IScheduleJobServiceLog logService) {
		ScheduleJobLog log = new ScheduleJobLog();
		log.setPid(log.getPK());
		log.setJobid(scheduleJob.getPid());
		log.setContent(content);
		log.setCreateTime(DateUtil.format(DateUtil.getToday()));
		logService.insertSelective(log);
	}

}


