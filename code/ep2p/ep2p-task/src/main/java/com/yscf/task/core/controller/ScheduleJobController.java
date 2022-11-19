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
 * 2015年12月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.task.core.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.constant.FrameworkConstants;
import com.achievo.framework.exception.ExceptionCode;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.BaseController;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.google.gson.Gson;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.model.task.ScheduleJobLog;
import com.yscf.core.service.task.impl.ScheduleJobServiceImpl;
import com.yscf.core.service.task.impl.ScheduleJobServiceLogImpl;
import com.yscf.task.core.service.QuartzManager;

/**
 * Description：<br> 
 * 定时任务controller
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/scheduleJobController")
public class ScheduleJobController  extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);
	
	@Resource(name = "scheduleJobServiceLog")
	private ScheduleJobServiceLogImpl scheduleJobServiceLog;
	
	@Autowired
	private QuartzManager quartzManager;

	@Autowired
	public ScheduleJobController(ScheduleJobServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ScheduleJob.class;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到借款客户列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toIndex")
	@ResponseBody
	public ModelAndView toIndex(HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("task/index");
	}
	
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到借款客户列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toTaskList")
	@ResponseBody
	public ModelAndView toTaskList(HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("task/task_list");
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到借款客户列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(String pid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView("task/task_add");
		view.addObject("pid", pid);
		return view;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 查询定时任务列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSearchLog")
	@ResponseBody
	public ModelAndView toSearchLog(String jobid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView("task/task_log");
		modelView.addObject("jobid", jobid);
		return  modelView;
	}
	
	
	/**
	 * 
	 * Description：<br>
	 * 查询定时任务列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectLogAllPage")
	@ResponseBody
	public ModelAndView selectLogAllPage(ScheduleJobLog jobLog,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			PageInfo info = getPageInfo(request);
			PageList<ScheduleJobLog> pageList = scheduleJobServiceLog.selectAllPage(jobLog, info);			
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("定时任务列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询定时任务列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(ScheduleJob scheduleJob,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			PageInfo info = getPageInfo(request);
			PageList<ScheduleJob> pageList = service.selectAllPage(scheduleJob, info);			
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("定时任务列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 根据ID查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectByPid")
	@ResponseBody
	public ModelAndView selectByPid(String pid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJob job = service.selectByPrimaryKey(pid);
		    modelView.addObject("result",job);
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("定时任务列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	
	/**
	 * 
	 * Description：<br>
	 * 根据任务名称查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectByJobName")
	@ResponseBody
	public ModelAndView selectByJobName(ScheduleJob scheduleJob,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJob job = service.selectByJobName(scheduleJob);
			if(null!=job){
				modelView.addObject("result",false);
			}else{
				modelView.addObject("result",true);
			}
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("定时任务列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 保存定时任务
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ModelAndView save(ScheduleJob scheduleJob,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJobLog log = getScheduleJobLog();
			// pid 不为空，则为新增
			if(null!=scheduleJob && null!=scheduleJob.getPid() && !"".equals(scheduleJob.getPid())){
				service.updateByPrimaryKeySelective(scheduleJob);
				quartzManager.rescheduleJob(scheduleJob);
				scheduleJob = service.selectByPrimaryKey(scheduleJob.getPid());
				log.setContent("更新表达式为："+scheduleJob.getCronExpression());
			}else{
				quartzManager.createScheduleJob(scheduleJob);
				service.insertSelective(scheduleJob);
				log.setJobid(scheduleJob.getPid());
				log.setContent("新建任务："+scheduleJob.getJobGroup()+"."+scheduleJob.getJobName());
			}
			scheduleJobServiceLog.insertSelective(log);
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("保存定时任务异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}

	/**
	 * 
	 * Description：<br> 
	 * 获取日志信息
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @return
	 */
	private ScheduleJobLog getScheduleJobLog() {
		ScheduleJobLog log = new ScheduleJobLog();
		log.setPid(log.getPK());
		log.setCreateTime(DateUtil.format(DateUtil.getToday()));
		return log;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 暂停任务
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/pauseJob")
	@ResponseBody
	public ModelAndView pauseJob(String pid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJob scheduleJob = service.selectByPrimaryKey(pid);
			if(null != scheduleJob){
				scheduleJob.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
				scheduleJob.setJobStatus(Constant.JOB_STATUS_3);
				service.updateByPrimaryKeySelective(scheduleJob);
				quartzManager.deleteJob(scheduleJob);
//				quartzManager.pauseJob(scheduleJob);
				ScheduleJobLog log = getScheduleJobLog();
				log.setJobid(pid);
				log.setContent("暂停任务");
				scheduleJobServiceLog.insertSelective(log);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("暂停任务异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 恢复任务
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/resumeJob")
	@ResponseBody
	public ModelAndView resumeJob(String pid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJob scheduleJob = service.selectByPrimaryKey(pid);
			if(null != scheduleJob){
				scheduleJob.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
				scheduleJob.setJobStatus(Constant.JOB_STATUS_1);
				service.updateByPrimaryKeySelective(scheduleJob);
				// 恢复定时任务
				quartzManager.createScheduleJob(scheduleJob);
//				quartzManager.resumeJob(scheduleJob);
				// 日志记录
				ScheduleJobLog log = getScheduleJobLog();
				log.setJobid(pid);
				log.setContent("恢复任务");
				scheduleJobServiceLog.insertSelective(log);
			    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("恢复任务异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 立即运行一次任务
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/triggerJob")
	@ResponseBody
	public ModelAndView triggerJob(String pid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJob scheduleJob = service.selectByPrimaryKey(pid);
			if(null != scheduleJob){
				// 调用定时任务
				quartzManager.triggerJob(scheduleJob);
				// 日志记录
				ScheduleJobLog log = getScheduleJobLog();
				log.setJobid(pid);
				log.setContent("手动运行任务");
				scheduleJobServiceLog.insertSelective(log);
			    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("立即运行一次任务异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 删除任务
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteJob")
	@ResponseBody
	public ModelAndView deleteJob(String pid,HttpServletRequest request,HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			ScheduleJobServiceImpl service =  (ScheduleJobServiceImpl) getService();
			ScheduleJob scheduleJob = service.selectByPrimaryKey(pid);
			if(null != scheduleJob){
				scheduleJob.setStatus(Constant.DELETE);
				service.updateByPrimaryKeySelective(scheduleJob);
				// 删除定时任务
				quartzManager.deleteJob(scheduleJob);
				// 日志记录
				ScheduleJobLog log = getScheduleJobLog();
				log.setJobid(pid);
				log.setContent("删除任务");
				scheduleJobServiceLog.insertSelective(log);
			    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("删除任务异常",e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return  modelView;
	}
	
	/**
	 * Description：<br> 
	 * 重写父类 getPageInfo 
	 * @author  Yu.Zhang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 */
	protected PageInfo getPageInfo(HttpServletRequest request) throws HttpRequestException {
	    String pageStr = request.getParameter(FrameworkConstants.PAGE);
	    if (pageStr == null) {
	    	throw new HttpRequestException(ExceptionCode.E_CORE_00006);
	    }
	    Gson gson = new Gson();
	    PageInfo page = new PageInfo();
	    boolean ext = false;
	    try {
	      if ((!StringUtils.isEmpty(pageStr)) && (Integer.parseInt(StringUtils.trimToEmpty(pageStr)) > 0))
	        ext = true;
	    }
	    catch (Throwable localThrowable) {
	    }
	    if (ext) {
	      String limit = request.getParameter(Constant.ROWS);
	      String start = request.getParameter(Constant.PAGE);
	      page.setLimit(StringUtils.isEmpty(limit) ? 15 : Integer.parseInt(limit));
	      page.setPage(StringUtils.isEmpty(start) ? 0 : Integer.parseInt(start));
	    } else {
	      page = (PageInfo)gson.fromJson(pageStr, PageInfo.class);
	    }
	    return page;
	}
}


