/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典页面控制数据交互类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月7日     Yu.Zhang		Initial Version.
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.system.SysDistionary;
import com.yscf.core.service.system.impl.SysDistionaryServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * Description：<br> 
 * 数据字典controll
 * @author  Yu.Zhang
 * @date    2015年9月7日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysDistionaryController")
public class SysDistionaryController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysDistionaryController.class);
	
	@Autowired
	public SysDistionaryController(SysDistionaryServiceImpl service) {
		super(service);
	}


	@Override
	public Class<?> getModel() {
		return SysDistionary.class;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到数据字典新增页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(String pid ,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/dictionary/distionary_add");
		modelView.addObject("pid", pid);
		return modelView;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	@Override
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysDistionaryServiceImpl service  = (SysDistionaryServiceImpl) getService();
			SysDistionary sysDistionary = (SysDistionary) getEntity(request);
			// 不为空修改，为空新增
			if(null!=sysDistionary && null!=sysDistionary.getPid() && !"".equals(sysDistionary.getPid().trim())){
				sysDistionary.setLastUpdateTime(DateUtil.getToday());
				sysDistionary.setLastUpdateUser(getUserId());
				service.updateByPrimaryKeySelective(sysDistionary);
			}else{
				sysDistionary.setCreateTime(DateUtil.getToday());
				sysDistionary.setCreateUser(getUserId());
				sysDistionary.setStatus(Constant.ACTIVATE);
				service.insert(sysDistionary);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到数据字典页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("/system/dictionary/distionary_list");
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 数据字典查询,带分页
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/distionaryList")
	@ResponseBody
	public ModelAndView distionaryList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			// 查询条件设置
			SysDistionary sysDistionary = (SysDistionary) getEntity(request);
			if(null == sysDistionary){
				sysDistionary = new SysDistionary();
			}
			// 只查询有效数据
			sysDistionary.setStatus(Constant.ACTIVATE);
			SysDistionaryServiceImpl service  = (SysDistionaryServiceImpl) getService();
			PageList<SysDistionary> pageList = service.selectAllPage(sysDistionary,info);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
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
	 * 
	 * Description：<br> 
	 * 数据字典批量删除
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteBtach")
	@ResponseBody
	public ModelAndView deleteBtach(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			SysDistionary sysDistionary = new SysDistionary() ;
			sysDistionary.setPid(request.getParameter("pid"));
			sysDistionary.setStatus(Constant.DISABLE);
			SysDistionaryServiceImpl service  = (SysDistionaryServiceImpl) getService();
		    service.updateStatusBatch(sysDistionary);
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
	 * 
	 * Description：<br> 
	 * 数据字典编码验证
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateCode")
	@ResponseBody
	public ModelAndView validateCode(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			SysDistionary sysDistionary = (SysDistionary) getEntity(request);
			sysDistionary.setStatus(Constant.ACTIVATE);
			SysDistionaryServiceImpl service  = (SysDistionaryServiceImpl) getService();
			if(!service.validateCode(sysDistionary)){
				modelView.addObject("result", "fail");
			}else{
				modelView.addObject("result", "suc");
			}
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
	 * 
	 * Description：<br> 
	 * 根据PID获取数据
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			SysDistionary sysDictionary = (SysDistionary) getEntity(request);
			sysDictionary = (SysDistionary) getService().selectByPrimaryKey(sysDictionary.getPid());
			modelView.addObject("result", sysDictionary);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取数据字典失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
}


