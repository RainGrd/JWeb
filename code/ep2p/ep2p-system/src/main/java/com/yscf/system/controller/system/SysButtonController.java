/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysButton;
import com.yscf.core.service.system.impl.SysButtonServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：按钮管理 控制器
 * 
 * @author JingYu.Dai
 * @date 2015年9月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping(value = "/sysButtonController")
public class SysButtonController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysRoleController.class);
	
	@Autowired
	public SysButtonController(SysButtonServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysButton.class;
	}

	/**
	 * Description：打开按钮管理首页
	 * @author JingYu.Dai
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/openButtonIndex")
	public ModelAndView openButtonIndex() {
		return new ModelAndView("/system/button/index");
	}
	/**
	 * Description：打开修改新增页面
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/openAddOrUpdate")
	public ModelAndView openAddOrUpdate(HttpServletRequest request , HttpServletResponse response){
		request.setAttribute("pid", request.getParameter("pid"));
		return new ModelAndView("/system/button/add_or_update");
	}
	/**
	 * Description：新增按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public ModelAndView addOrUpdate(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysButton button = (SysButton) getEntity(request);
		SysButtonServiceImpl serviceImpl = (SysButtonServiceImpl) getService();
		int count = 0;
		String menssage = null;
		try {
			count = serviceImpl.addOrUpdate(button);
			if(null != button && button.getPid() != null){
				menssage = (count>0?"修改成功！":"修改失败！");
			}else{
				menssage = (count>0?"新增成功！":"新增失败！");
			}
			MessageBuilder.processSuccess(view, menssage, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}

	/**
	 * Description： 按钮管理首页
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/buttonIndexList")
	public ModelAndView buttonIndexList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysButton sysButton = (SysButton) getEntity(request);
		sysButton = (sysButton == null ? new SysButton():sysButton);
		PageInfo info = getPageInfo(request);
		SysButtonServiceImpl serviceImpl = (SysButtonServiceImpl) getService();
		PageList<SysButton> list;
		try {
			list = serviceImpl.selectSelectivePage(sysButton,info);
			view.addObject("rows",list);
			view.addObject("total",list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	
	/**
	 * Description：菜单按钮配置 查询可选按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/choosableButton")
	public ModelAndView choosableButton(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		PageInfo info = getPageInfo(request);
		String menuId = request.getParameter("menuId");
		SysButtonServiceImpl serviceImpl = (SysButtonServiceImpl) getService();
		PageList<SysButton> list;
		try {
			list = serviceImpl.selectChoosableButtonPage(menuId,info);
			view.addObject("rows",list);
			view.addObject("total",list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	
	/**
	 * Description： 菜单按钮配置 查询衣已选按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/selectedButton")
	public ModelAndView selectedButton(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		PageInfo info = getPageInfo(request);
		String menuId = request.getParameter("menuId");
		SysButtonServiceImpl serviceImpl = (SysButtonServiceImpl) getService();
		PageList<SysButton> list;
		try {
			list = serviceImpl.selectSelectedButtonPage(menuId,info);
			view.addObject("rows",list);
			view.addObject("total",list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	
	/**
	 * Description：<br> 
	 * 根据菜单Id查询按钮集合
	 * @author  JingYu.Dai
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/getButtonsByMenuId")
	public ModelAndView getButtonsByMenuId(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		String menuId = request.getParameter("menuId");
		//String menuId = "FFFFFFFFE17C559D!4C53BF2FFE5D4503A4663B7277AC7C";
		ContextUser contextUser = getContextUser();
		
		SysButtonServiceImpl serviceImpl = (SysButtonServiceImpl) getService();
		List<SysButton> list = null;
		try {
			list = serviceImpl.getButtonsByMenuId(menuId,contextUser.getUserId());
			MessageBuilder.processSuccess(view, list, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		request.setAttribute("buttons", list);
		return view;
	}
	
}
