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
 * 2015年9月10日     JingYu.Dai		Initial Version.
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysRoleMenuButtonRel;
import com.yscf.core.service.system.impl.SysRoleMenuButtonRelServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：角色controll
 * 
 * @author JingYu.Dai
 * @date 2015年9月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysRoleMenuButtonRelController")
public class SysRoleMenuButtonRelController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(SysRoleMenuButtonRelController.class);

	@Autowired
	public SysRoleMenuButtonRelController(SysRoleMenuButtonRelServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysRoleMenuButtonRel.class;
	}

	/**
	 * Description：查询角色首页列表
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping("/queryPermission")
	@ResponseBody
	public ModelAndView queryPermission(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		String roleId = request.getParameter("roleId");
		SysRoleMenuButtonRelServiceImpl serviceImpl = (SysRoleMenuButtonRelServiceImpl) getService();
		try {
			List<SysRoleMenuButtonRel> list = serviceImpl.selectByRoleId(roleId);
			MessageBuilder.processSuccess(view, list, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}

	/**
	 * Description：权限分配
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/permissionAssign")
	@ResponseBody
	public ModelAndView permissionAssign(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRoleMenuButtonRelServiceImpl serviceImpl = (SysRoleMenuButtonRelServiceImpl) getService();
		try {
			String menuIds = request.getParameter("menuIds");
			String menuButtonIds = request.getParameter("menuButtonIds");
			String roleId = request.getParameter("roleId");
			int count = serviceImpl.insertPermissionAssign(menuIds, roleId,menuButtonIds);
			String message = "权限分配失败！";
			if (count > 0) {
				message = "权限分配成功！";
			}
			MessageBuilder.processSuccess(view, message, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
}
