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
 * 2015年9月11日     JingYu.Dai		Initial Version.
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

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysMenu;
import com.yscf.core.service.system.impl.SysMenuServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：菜单controll
 * @author JingYu.Dai
 * @date 2015年9月11日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysMenuController")
public class SysMenuController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(SysMenuController.class);

	@Autowired
	public SysMenuController(SysMenuServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysMenu.class;
	}

	@RequestMapping("/openMenuIndex")
	public ModelAndView openMenuIndex() {
		return new ModelAndView("/system/menu/index");
	}
	@RequestMapping("/openAddModule")
	public ModelAndView openAddModule(){
		return new ModelAndView("/system/menu/add_module");
	}
	
	@RequestMapping("/openAddChildNode")
	public ModelAndView openAddChildNode(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		BaseEntity entity = getEntity(request);
		SysMenu menu = (SysMenu) entity;
		request.setAttribute("parentAuthId", menu.getPid());
		request.setAttribute("parentNenuName",menu.getMenuName());
		return new ModelAndView("/system/menu/add_child_node");
	}
	
	@RequestMapping("/openButtonAssign")
	public ModelAndView openButtonAssign(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		request.setAttribute("menuId",request.getParameter("menuId"));
		return new ModelAndView("/system/menu/button_assign");
	}
	

	/**
	 * Description：新增菜单
	 * @author  JingYu.Dai
	 * @date    2015年9月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	@Override
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BaseEntity entity = getEntity(request);
			SysMenu menu = (SysMenu) entity;
			String pid = menu.getPid();
			int count = ((SysMenuServiceImpl) getService()).insertOrUpdateSelective(menu);
			if(count > 0){
				String message = "新增成功！";
				if(pid !=null && !pid.equals("")){
					message = "修改成功！";
				}
				MessageBuilder.processSuccess(modelView, message, HttpMessage.SUCCESS_CODE,
						request);
			}else{
				String message = "新增失败！";
				if(pid !=null && !pid.equals("")){
					message = "修改失败！";
				}
				MessageBuilder.processSuccess(modelView, message, HttpMessage.ERROR_CODE, request);
			}
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * Description：查询数据菜单数据
	 * @author  JingYu.Dai
	 * @date    2015年9月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectSelective")
	@ResponseBody
	public ModelAndView selectSelective(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		SysMenu sysMenu = (SysMenu) getEntity(request);
		String userId = getContextUser().getUserId();
		try {
			List<SysMenu> list = service.selectSelective(sysMenu,userId);
			MessageBuilder.processSuccess(modelAndView, list, HttpMessage.SUCCESS_CODE,
					request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
	
	
	/**
	 * Description：查询数据菜单数据
	 * @author  JingYu.Dai
	 * @date    2015年9月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/queryAllMenu")
	@ResponseBody
	public ModelAndView queryAllMenu(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		try {
			List<SysMenu> list = service.queryAllMenu();
			MessageBuilder.processSuccess(modelAndView, list, HttpMessage.SUCCESS_CODE,
					request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	/**
	 * Description：根据用户主键查询 菜单数据
	 * @author  JingYu.Dai
	 * @date    2015年9月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/getSysMenuByPK")
	@ResponseBody
	public ModelAndView getSysMenuByPK(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		SysMenu menu = (SysMenu) getEntity(request);
		try {
			menu  = service.getSysMenuByPid(menu.getPid());
			MessageBuilder.processSuccess(modelAndView, menu, HttpMessage.SUCCESS_CODE,
					request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	@RequestMapping(value="/delete")
	@ResponseBody
	@Override
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		SysMenu menu = (SysMenu) getEntity(request);
		try {
			int count = service.deleteByPrimaryKey(menu.getPid());
			if(count > 0){
				MessageBuilder.processSuccess(modelAndView, "删除成功！", HttpMessage.SUCCESS_CODE,
						request);
			}else{
				MessageBuilder.processSuccess(modelAndView, "删除失败！", HttpMessage.ERROR_CODE,
						request);
			}
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
	
	/**
	 * Description：取消按钮分配
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/cancelButtonAssign")
	@ResponseBody
	public ModelAndView cancelButtonAssign(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		String menuId = request.getParameter("menuId");
		String buttonIds = request.getParameter("buttonIds");
		try {
			int count = service.deleteButtonAssigne(menuId,buttonIds);
			if(count > 0){
				MessageBuilder.processSuccess(modelAndView, "按钮取消成功！", HttpMessage.SUCCESS_CODE,
						request);
			}else{
				MessageBuilder.processSuccess(modelAndView, "按钮取消失败！", HttpMessage.ERROR_CODE,
						request);
			}
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
	
	/**
	 * Description：按钮分配
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/buttonAssign")
	@ResponseBody
	public ModelAndView buttonAssign(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		String menuId = request.getParameter("menuId");
		String buttonIds = request.getParameter("buttonIds");
		try {
			int count = service.insertButtonAssigne(menuId,buttonIds);
			if(count > 0){
				MessageBuilder.processSuccess(modelAndView, "按钮配置成功！", HttpMessage.SUCCESS_CODE,
						request);
			}else{
				MessageBuilder.processSuccess(modelAndView, "按钮配置失败！", HttpMessage.ERROR_CODE,
						request);
			}
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
	
	/**
	 * Description：权限分配查询
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/queryPermissionSelect")
	@ResponseBody
	public ModelAndView queryPermissionSelect(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException{
		ModelAndView modelAndView = new ModelAndView();
		SysMenuServiceImpl service = (SysMenuServiceImpl) getService();
		try {
			List<SysMenu> list = service.selectPermissionSelect();
			MessageBuilder.processSuccess(modelAndView, list, HttpMessage.SUCCESS_CODE,
					request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
}
