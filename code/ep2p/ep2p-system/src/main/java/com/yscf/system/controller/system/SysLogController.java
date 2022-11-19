/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 日志管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysLog;
import com.yscf.core.service.system.impl.SysLogServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 日志管理
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysLogController")
public class SysLogController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(SysLogController.class);

	@Autowired
	public SysLogController(SysLogServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysLog.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到日志新增页面
	 * @author Allen
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/addLog");
		modelView.addObject("pid", request.getParameter("pid"));
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据ID查询
	 * @author Allen
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysLog sysLog = (SysLog) getEntity(request);
			sysLog = (SysLog) getService().selectByPrimaryKey(
					sysLog.getPid());
			modelView.addObject("result", sysLog);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取系统日志失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到日志查询页面
	 * @author Allen
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("/system/logList");
	}

	/**
	 * Description：日志管理查询列表
	 * 
	 * @author Allen
	 * @date 2015年9月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/logList")
	@ResponseBody
	public ModelAndView logList(HttpServletRequest request,
			HttpServletResponse response, int page, int rows,SysLog syslog)
			throws HttpRequestException {
		SysLogServiceImpl service = (SysLogServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
		/*	BaseEntity entity = getEntity(request);
			SysLog sysLog = (SysLog) entity;
			if (null == sysLog) {
				sysLog = new SysLog();
			}*/
			PageList<SysLog> list = service.selectAllPage(syslog, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * Description：删除日志管理列表
	 * 
	 * @author Allen
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */

	@RequestMapping(value = "/deleteBtach")
	@ResponseBody
	public ModelAndView deleteBtach(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String pids = request.getParameter("pid");
			SysLogServiceImpl service = (SysLogServiceImpl) getService();
			// 根据主键批量删除
			service.deleteBtach(pids);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
}
