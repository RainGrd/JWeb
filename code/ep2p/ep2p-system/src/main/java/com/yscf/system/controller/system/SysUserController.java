/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
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
import com.achievo.framework.exception.IncorrectCaptchaException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.security.shiro.CaptchaUsernamePasswordToken;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysUser;
import com.yscf.core.service.system.impl.SysUserServiceImpl;
import com.yscf.core.ucenter.BBSApi;
import com.yscf.core.ucenter.XMLHelper;
import com.yscf.system.constort.Constants;
import com.yscf.system.constort.LogConstants;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.util.MD5;

/**
 * Description：<br>
 * 用户管理
 * @author Simon.Hoo
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysUserController")
public class SysUserController extends  EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	public SysUserController(SysUserServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysUser.class;
	}
	/**
	 * 
	 * Description：<br> 
	 * 跳转到用户新增页面
	 * @author  heng.wang
	 * @date    2015年9月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/addUser");
		SysUserServiceImpl sysUserServiceImpl = (SysUserServiceImpl)getService();
		String password =sysUserServiceImpl.genRandomPassword(8);
		modelView.addObject("pid", request.getParameter("pid"));
		modelView.addObject("password", password);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据ID查询
	 * @author  heng.wang
	 * @date    2015年9月11日
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
			SysUser sysUser = (SysUser) getEntity(request);
			sysUser = (SysUser) getService().selectByPrimaryKey(sysUser.getPid());
			modelView.addObject("result", sysUser);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取系统用户失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * Description：登陆跳转
	 * @author  JingYu.Dai
	 * @date    2015年9月10日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping(value="/login")
	public ModelAndView login(){
		return new ModelAndView("/index");
	}
	
	@RequestMapping(value = "/create")
	@ResponseBody
	@Override
	public ModelAndView create(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView("/system/userManagerList");
		try {
			BaseEntity entity = getEntity(request);
			SysUserServiceImpl sysUserServiceImpl = (SysUserServiceImpl)getService();
			SysUser sysUser = (SysUser) entity;
			sysUser.setCreateUser(getContextUser().getUserName());
			String password =sysUserServiceImpl.genRandomPassword(8);
			sysUser.setPassword(password);
			//获取当前登录人
			String pwdMD5 = MD5.encryptionMD5(sysUser.getPassword());
			sysUser.setPassword(pwdMD5);
			String pid = sysUser.getPid();
			if(null!=pid && !"".equals(pid)){
				sysUserServiceImpl.updateByPrimaryKeySelective(sysUser);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}else{
				sysUser.setCreateTime(DateUtil.format(DateUtil.getToday()));
				sysUser.setCreateTime(DateUtil.format(new Date()));
				getService().insert(entity);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
			
			syncBBS(sysUser.getStatus(),sysUser.getAccountNo(),sysUser.getPassword());
			
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：验证用户是否存在 
	 * @author  heng.wang
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/validateUserAccount")
	@ResponseBody
	public ModelAndView validateUserAccount(String accountNo,String pid,HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		SysUserServiceImpl service = (SysUserServiceImpl) getService();
		int count=0;
		try {
			count = service.validateUserAccount(accountNo);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		//判断是新增还是修改，如果pid是空的，说明是新增，否则修改
		if(count > 0 && pid.equals("")){
			MessageBuilder.processSuccess(modelView, null, HttpMessage.ERROR_MSG, request);
			modelView.addObject("count","1");//账户已存在
		}else{
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			modelView.addObject("count","2");
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 将用户信息同步至论坛
	 * @author  Yu.Zhang
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param status
	 * @param userName
	 * @param pwd
	 */
	private void syncBBS(String status,String userName,String pwd){
		// 禁用用户时，删除论坛数据
		if(com.yscf.common.Constant.Constant.DELETE.equals(status) || com.yscf.common.Constant.Constant.DISABLE.equals(status)){
			String result = BBSApi.getUserInfo(userName);
			LinkedList<String> rs = XMLHelper.uc_unserialize(result);
			if (rs.size() > 0) {
				result = BBSApi.deleteUser(rs.get(0));
				System.out.println("删除论坛用户 "+result);
			}
		}
		
		// 启用时，添加用户数据
		if(com.yscf.common.Constant.Constant.ACTIVATE.equals(status)){
			BBSApi.reg(userName, pwd, "11609924@qq.com");
		}
	}

	/**
	 * Description：用户登录 
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value="/doLogin")
	@ResponseBody
	public ModelAndView doLogin(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		SysUserServiceImpl service = (SysUserServiceImpl) getService();
		BaseEntity entity = getEntity(request);
		SysUser sysUser = (SysUser) entity;
		String pwdMD5 = MD5.encryptionMD5(sysUser.getPassword());
		sysUser.setPassword(pwdMD5);
		try {
			if(!service.userNameVerify(sysUser.getName())){
				MessageBuilder.processSuccess(modelView, "用户名错误！", HttpMessage.ERROR_CODE, request);
			}else if(!service.passwordVerify(sysUser.getPassword())){
				MessageBuilder.processSuccess(modelView, "密码错误！", HttpMessage.ERROR_CODE, request);
			}else{
				modelView.addObject("sysUser", sysUser);
				MessageBuilder.processSuccess(modelView, "登录成功！", HttpMessage.SUCCESS_CODE, request);
			}
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
	 * 跳转到用户查询页面
	 * @author  heng.wang
	 * @date    2015年9月10日
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
		ModelAndView modelAndView = new ModelAndView("/system/userManagerList");
		SysUserServiceImpl service = (SysUserServiceImpl) getService();
		String password =service.genRandomPassword(8);
		modelAndView.addObject("password", password);
		return modelAndView;
	}
	/**
	 * Description：用户管理查询列表 
	 * @author  heng.wang
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/queryUserList")
	@ResponseBody
	public ModelAndView queryUserList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		SysUserServiceImpl service  = (SysUserServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
	   try {
			PageInfo info = getPageInfo(request);
			BaseEntity entity = getEntity(request);
			SysUser sysUser = (SysUser) entity;
			if(null == sysUser){
				sysUser = new SysUser();
			}
			PageList<SysUser> list = service.selectAllPage(sysUser,info);
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
	 * Description：删除用户管理列表 
	 * @author  heng.wang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteBtach")
	@ResponseBody
	public ModelAndView deleteBtach(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String pid = request.getParameter("pid");
			SysUserServiceImpl service  = (SysUserServiceImpl) getService();
			// 同步论坛删除
			syncDeleteBBS(pid, service);
			//根据主键批量删除
		    service.deleteBtach(pid);
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
	 * 用户删除，同步论坛删除
	 * @author  Yu.Zhang
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param pid
	 * @param service
	 * @throws FrameworkException
	 */
	private void syncDeleteBBS(String pid, SysUserServiceImpl service)
			throws FrameworkException {
		String [] pids = pid.split(",");
		SysUser user = null;
		for(String id : pids){
			try{
				user = (SysUser) service.selectByPrimaryKey(id);
				syncBBS(com.yscf.common.Constant.Constant.DELETE, user.getAccountNo(), "");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Description：可选用户 		用于角色管理中的用户分配
	 * @author  JingYu.Dai
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/choosableUser")
	@ResponseBody
	public ModelAndView choosableUser(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		try {
			//角色ID
			String roleId = request.getParameter("roleId");
			//查询的字符串 可以是（用户账号 、用户名、用户电话号码）
			String queryStr = request.getParameter("queryStr");
			if("".equals(queryStr)){
				queryStr = null;
			}
			PageInfo info = getPageInfo(request);
			SysUserServiceImpl service  = (SysUserServiceImpl) getService();
			//根据角色ID 查询用户列表
			PageList<SysUser> pageList = service.queryNoUsersByRoleId(roleId,queryStr,info);
			view.addObject("rows",pageList);
			view.addObject("total", pageList.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	/**
	 * Description：已选用户 		用于角色管理中的用户分配
	 * @author  JingYu.Dai
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectedUser")
	@ResponseBody
	public ModelAndView selectedUser(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		try {
			//角色ID
			String roleId = request.getParameter("roleId");
			//查询的字符串 可以是（用户账号 、用户名、用户电话号码）
			String queryStr = request.getParameter("queryStr");
			if("".equals(queryStr)){
				queryStr = null;
			}
			PageInfo info = getPageInfo(request);
			SysUserServiceImpl service  = (SysUserServiceImpl) getService();
			//根据角色ID 查询用户列表
			PageList<SysUser> pageList = service.queryUsersByRoleId(roleId,queryStr,info);
			view.addObject("rows",pageList);
			view.addObject("total", pageList.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	
	/**
	 * Description：检查用户登录
	 * @author  JingYu.Dai
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/checkUserLogin")
	@ResponseBody
	public ModelAndView checkUserLogin(HttpServletRequest request, HttpServletResponse response) {
		//用户名
		String userName = request.getParameter("userName");
		//密码
		String password = request.getParameter("password");
		//String captcha = request.getParameter("captcha");
		ModelAndView view = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken();
		String pwdMD5 = MD5.encryptionMD5(password);
		token.setUsername(userName);
		token.setPassword(pwdMD5.toCharArray());
		//token.setCaptcha(captcha);
		token.setRememberMe(true);
		try {
			subject.login(token);
			MessageBuilder.processSuccess(view, null ,HttpMessage.SUCCESS_CODE, request);
			addLog(request,LogConstants.LOGIN,null);
			String result = BBSApi.login(userName, password);
			view.addObject("discuzLogin", result);
//			return new ModelAndView("redirect:/sysUserController/login.shtml");
		} catch (UnknownSessionException use) {
			subject = new Subject.Builder().buildSubject();
			subject.login(token);
			logger.error(Constants.UNKNOWN_SESSION_EXCEPTION);
			MessageBuilder.processSuccess(view, Constants.UNKNOWN_SESSION_EXCEPTION, HttpMessage.ERROR_CODE, request);
		} catch (UnknownAccountException ex) {
			logger.error(Constants.UNKNOWN_ACCOUNT_EXCEPTION);
			MessageBuilder.processSuccess(view, Constants.UNKNOWN_ACCOUNT_EXCEPTION, HttpMessage.ERROR_CODE, request);
		} catch (IncorrectCredentialsException ice) {
			ice.printStackTrace();
			MessageBuilder.processSuccess(view, Constants.INCORRECT_CREDENTIALS_EXCEPTION, HttpMessage.ERROR_CODE, request);
		} catch (LockedAccountException lae) {
			MessageBuilder.processSuccess(view, Constants.LOCKED_ACCOUNT_EXCEPTION, HttpMessage.ERROR_CODE, request);
		} catch (IncorrectCaptchaException e) {
			MessageBuilder.processSuccess(view, Constants.INCORRECT_CAPTCHA_EXCEPTION, HttpMessage.ERROR_CODE, request);
		} catch (AuthenticationException ae) {
			MessageBuilder.processSuccess(view, ae.getMessage(), HttpMessage.ERROR_CODE, request);
		} catch (Exception e) {
			MessageBuilder.processSuccess(view, Constants.UNKNOWN_EXCEPTION, HttpMessage.ERROR_CODE, request);
		}
		return view;
	}
	
	/**
	 * 
	 * Description：
	 * 论坛退出
	 * @author  Yu.Zhang
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bbsLogout")
	public void bbsLogout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try{
			String result = BBSApi.logout();
			tojson.put("result",result);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
			logger.info(getUserId()+"论坛退出成功！");
		}catch(Exception e){
			logger.error(getUserId()+"论坛退出失败！");
			tojson.put("message", HttpMessage.ERROR_CODE);
		}
		outputJson(tojson, response);
	}
	
	/**
	 * Description：<br> 
	 * 用户登出系统
	 * @author  JingYu.Dai
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @return String
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		//recordLog(BusinessModule.MODUEL_SYSTEM, SysLogTypeConstant.LOG_TYPE_LOGOUT, "登出系统");
		addLog(request,LogConstants.LOGOUT,null);
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return redirect("/");
	}
	
	
	/**
	 * Description：<br> 
	 * 批量重置密码
	 * @author  JingYu.Dai
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/passwordResetBtach")
	@ResponseBody
	public ModelAndView passwordResetBtach(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String pids = request.getParameter("pids");
			SysUserServiceImpl service  = (SysUserServiceImpl) getService();
			//根据主键批量删除
		    boolean falg = service.passwordResetBtach(pids);
		    MessageBuilder.processSuccess(modelView, falg, HttpMessage.SUCCESS_MSG, request);
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
	 * Description：<br> 
	 * 重置密码
	 * @author  JingYu.Dai
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/resetPassword")
	@ResponseBody
	public ModelAndView resetPassword(String pids,String passWord,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysUserServiceImpl service  = (SysUserServiceImpl) getService();
			SysUser sysUser = new SysUser();
			sysUser.setPid(pids);
			String pwdMD5 = MD5.encryptionMD5(passWord);
			sysUser.setPassword(pwdMD5);
			//根据主键批量删除
		    service.resetPassword(sysUser);
		    MessageBuilder.processSuccess(modelView,null, HttpMessage.SUCCESS_MSG, request);
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
	 * Description：<br> 
	 * 打开修改密码页面
	 * @author  JingYu.Dai
	 * @date    2015年12月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/openChangePassword")
	public ModelAndView openChangePassword(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/change_password");
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 修改密码
	 * @author  JingYu.Dai
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param oldPassword
	 * @param password
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/changePassword")
	@ResponseBody
	public ModelAndView changePassword(String oldPassword,String password ,HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ContextUser user = getContextUser();
		SysUserServiceImpl userImpl = (SysUserServiceImpl) getService();
		SysUser sysUser;
		try {
			sysUser = userImpl.getSysUserByAccountNo(user.getUserName());
			String pwdMD5 = MD5.encryptionMD5(oldPassword);
			boolean falg = false;
			if(sysUser.getPassword().equals(pwdMD5)){
				falg = true;
				String newPasswordMD5 = MD5.encryptionMD5(password);
				sysUser.setPassword(newPasswordMD5);
				userImpl.updateByPrimaryKeySelective(sysUser);
				MessageBuilder.processSuccess(modelView, falg, HttpMessage.SUCCESS_MSG, request);
			}else{
				MessageBuilder.processSuccess(modelView, falg, HttpMessage.SUCCESS_MSG, request);
			}
		} catch (FrameworkException e) {
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
}
