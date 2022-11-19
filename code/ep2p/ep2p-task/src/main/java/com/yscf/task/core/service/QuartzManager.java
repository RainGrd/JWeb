/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 定时任务管理类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.task.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.yscf.core.model.task.ScheduleJob;
import com.yscf.task.core.factory.JobSyncFactory;

/**
 * Description：<br> 
 * 定时任务管理类
 * @author  Lin Xu
 * @date    2015年11月13日
 * @version v1.0.0
 */
public class QuartzManager {
	
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;


	/**
	 * 
	 * Description：<br> 
	 * 创建定时任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @param scheduler
	 * @param jobName
	 * @param jobGroup
	 * @param cronExpression
	 * @param isSync
	 * @param param
	 * @throws SchedulerException 
	 */
	public void createScheduleJob(ScheduleJob scheduleJob) throws SchedulerException {
		//同步或异步
		Class<? extends Job> jobClass = JobSyncFactory.class;
		//构建job信息
		JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).build();
		//放入参数，运行时的方法可以获取
		jobDetail.getJobDataMap().put("JOB_PARAM_KEY", scheduleJob);
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		//按新的cronExpression表达式构建一个新的trigger
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).withSchedule(scheduleBuilder).build();
		
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 更新任务的时间表达式
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public void rescheduleJob(ScheduleJob scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
		//获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		//表达式调度构建器
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		//按新的cronExpression表达式重新构建trigger
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		//按新的trigger重新设置job执行
		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 暂停任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public void pauseJob(ScheduleJob scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 恢复任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public void resumeJob(ScheduleJob scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 删除任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public void deleteJob(ScheduleJob scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 立即运行任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public void triggerJob(ScheduleJob scheduleJob) throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取计划中的定时任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public List<ScheduleJob> getJobs() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		ScheduleJob job = null;
		for (JobKey jobKey : jobKeys) {
		    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		    for (Trigger trigger : triggers) {
		        job = new ScheduleJob();
		        job.setJobName(jobKey.getName());
		        job.setJobGroup(jobKey.getGroup());
		        job.setJobDesc("触发器:" + trigger.getKey());
		        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		        job.setJobStatus(triggerState.name());
		        if (trigger instanceof CronTrigger) {
		            CronTrigger cronTrigger = (CronTrigger) trigger;
		            String cronExpression = cronTrigger.getCronExpression();
		            job.setCronExpression(cronExpression);
		        }
		        jobList.add(job);
		    }
		}
		return jobList;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取运行中的定时任务
	 * @author  Yu.Zhang
	 * @date    2015年12月21日
	 * @version v1.0.0
	 * @throws SchedulerException 
	 */
	public List<ScheduleJob> getRuningJobs() throws SchedulerException{
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
		ScheduleJob job = null;
		for (JobKey jobKey : jobKeys) {
		    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		    for (Trigger trigger : triggers) {
		    	job = new ScheduleJob();
		        job.setJobName(jobKey.getName());
		        job.setJobGroup(jobKey.getGroup());
		        job.setJobDesc("触发器:" + trigger.getKey());
		        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
		        job.setJobStatus(triggerState.name());
		        if (trigger instanceof CronTrigger) {
		            CronTrigger cronTrigger = (CronTrigger) trigger;
		            String cronExpression = cronTrigger.getCronExpression();
		            job.setCronExpression(cronExpression);
		        }
		        jobList.add(job);
		    }
		}
		return jobList;
	}
}


