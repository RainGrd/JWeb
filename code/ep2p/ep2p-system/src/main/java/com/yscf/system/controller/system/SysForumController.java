/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 系统论坛
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月2日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysUser;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysUserServiceImpl;
import com.yscf.core.ucenter.BBSApi;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 系统论坛管理页面控制类
 * @author  Yu.Zhang
 * @date    2015年12月2日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysForumController")
public class SysForumController  extends EscfBaseController {
	
	
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;

	@Autowired
	public SysForumController(SysUserServiceImpl service) {
		super(service);
	}

	/**
	 * 
	 * Description：<br> 
	 * 跳转到论坛管理页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toForumPage")
	@ResponseBody
	public ModelAndView toForumPage(String pid ,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/forum/forumManger");
		try {
			// 登录当前用户
			SysUserServiceImpl sysUserServiceImpl = (SysUserServiceImpl)getService();
			SysUser user = (SysUser) sysUserServiceImpl.selectByPrimaryKey(super.getUserId());
			String result = BBSApi.login(user.getAccountNo(), "123456");
			
			SysParams sysParams =  sysParamsService.getParamsByParamKey("FORUM_ADDRESS");
			request.setAttribute("forumAddress", sysParams.getParamValue());
			request.setAttribute("discuzLogin", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelView;
	}

	@Override
	public Class<?> getModel() {
		return SysUser.class;
	}
}


