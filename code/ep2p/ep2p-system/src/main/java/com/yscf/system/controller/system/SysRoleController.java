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

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysRole;
import com.yscf.core.service.system.impl.SysRoleServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：角色controll
 * 
 * @author JingYu.Dai
 * @date 2015年9月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysRoleController")
public class SysRoleController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	public SysRoleController(SysRoleServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysRole.class;
	}

	/**
	 * Description：跳转到角色管理首页
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping("/openRoleIndex")
	public ModelAndView openRoleIndex() {
		return new ModelAndView("/system/role/index");
	}

	/**
	 * Description：打开角色 新增 修改页面
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/openAddOrUpdate")
	public ModelAndView openAddOrUpdate(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("pid", request.getParameter("pid"));
		return new ModelAndView("/system/role/add_or_update");
	}

	/**
	 * Description：打开用户配置页面
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/openUserAssign")
	public ModelAndView openUserAssign(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String roleName = java.net.URLDecoder.decode(
					request.getParameter("roleName"), "UTF-8");
			request.setAttribute("roleName", roleName);
			request.setAttribute("roleCode", request.getParameter("roleCode"));
			request.setAttribute("roleId", request.getParameter("roleId"));
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("/system/role/user_assign");
	}

	/**
	 * Description：打开权限配置页面
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/openPermissionAssign")
	public ModelAndView openPermissionAssign(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String roleName = java.net.URLDecoder.decode(
					request.getParameter("roleName"), "UTF-8");
			request.setAttribute("roleName", roleName);
			request.setAttribute("roleCode", request.getParameter("roleCode"));
			request.setAttribute("roleId", request.getParameter("roleId"));
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("/system/role/permission_assign");
	}

	/**
	 * Description：新增修改角色
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping("/addOrUpdate")
	@ResponseBody
	public ModelAndView addOrUpdate(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRole role = (SysRole) getEntity(request);
		String pid = role.getPid();
		try {
			int count = ((SysRoleServiceImpl) getService()).addOrUpdate(role);
			if (count > 0) {
				String message = "新增成功！";
				if (pid != null && !pid.equals("")) {
					message = "修改成功！";
				}
				MessageBuilder.processSuccess(view, message, HttpMessage.SUCCESS_CODE, request);
			} else {
				String message = "新增失败！";
				if (pid != null && !pid.equals("")) {
					message = "修改失败！";
				}
				MessageBuilder.processSuccess(view, message, HttpMessage.ERROR_CODE, request);
			}
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
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
	@RequestMapping("/roleIndexList")
	@ResponseBody
	public ModelAndView roleIndexList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		SysRole role = (SysRole) getEntity(request);
		PageInfo info = getPageInfo(request);
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			if (null == role) {
				role = new SysRole();
			}
			PageList<SysRole> list = serviceImpl
					.selectSelectivePage(role, info);
			view.addObject("rows", list);
			view.addObject("total", list.getPaginator().getTotalCount());
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
	 * Description：查询所有角色 下拉框用
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/roleAddOrUpdateCombx")
	@ResponseBody
	public ModelAndView roleAddOrUpdateCombx(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			List<SysRole> list = serviceImpl.selectSelective(new SysRole());
			List<SysRole> roles = new ArrayList<SysRole>();
			SysRole role = new SysRole();
			role.setRoleCode(0);
			role.setRoleName("--请选择--");
			roles.add(role);
			roles.addAll(list);
			MessageBuilder.processSuccess(view, roles, HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}

	/**
	 * Description：验证角色名称是否存在
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateName")
	@ResponseBody
	public ModelAndView validateName(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			SysRole role = (SysRole) getEntity(request);
			List<SysRole> list = serviceImpl.selectSelective(role);
			if (list != null && list.size() > 0) {
				view.addObject("isExist", "true");
			} else {
				view.addObject("isExist", "false");
			}
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
	 * Description：有选择的查询 数据列表
	 * @author JingYu.Dai
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getRoleByPid")
	@ResponseBody
	public ModelAndView getRoleByPid(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			String pid = request.getParameter("pid");
			List<SysRole> list = null;
			if (pid != null) {
				SysRole role = new SysRole();
				role.setPid(pid);
				list = serviceImpl.selectSelective(role);
			}
			MessageBuilder.processSuccess(view, list.get(0), HttpMessage.SUCCESS_CODE, request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}

	/**
	 * Description：批量删除
	 * @author JingYu.Dai
	 * @date 2015年9月18日
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
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			String pIds = request.getParameter("pIds");
			int count = serviceImpl.deleteBtach(pIds);
			String message = "删除失败！";
			if (count > 0) {
				message = "删除成功！";
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

	/**
	 * Description：用户分配
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/userAssign")
	@ResponseBody
	public ModelAndView userAssign(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			String userIds = request.getParameter("userIds");
			String roleId = request.getParameter("roleId");
			int count = serviceImpl.insertUserAssign(userIds, roleId);
			String message = "用户分配失败！";
			if (count > 0) {
				message = "用户分配成功！";
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

	/**
	 * Description：取消用户分配
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/cancelUserAssign")
	@ResponseBody
	public ModelAndView cancelUserAssign(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		SysRoleServiceImpl serviceImpl = (SysRoleServiceImpl) getService();
		try {
			String userIds = request.getParameter("userIds");
			String roleId = request.getParameter("roleId");
			int count = serviceImpl.deleteUserAssign(userIds, roleId);
			String message = "用户分配失败！";
			if (count > 0) {
				message = "用户分配成功！";
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
