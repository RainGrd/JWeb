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
package com.yscf.system.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.security.shiro.CaptchaUsernamePasswordToken;
import com.yscf.core.model.system.SysMenu;
import com.yscf.core.model.system.SysRole;
import com.yscf.core.model.system.SysUser;
import com.yscf.core.service.system.impl.SysRoleServiceImpl;
import com.yscf.core.service.system.impl.SysUserServiceImpl;
import com.yscf.system.constort.Constants;

/**
 * Description：shiro实现
 * 
 * @author JingYu.Dai
 * @date 2015年9月10日
 * @version v1.0.0
 */
public class EscfShiroRealm extends AuthorizingRealm {

	@Resource(name = "sysRoleService")
	private SysRoleServiceImpl roleService;

	@Resource(name = "sysUserService")
	private SysUserServiceImpl userService;

	/**
	 * Description：
	 * 
	 * @see 为当前登录的用户授予角色和权限
	 * @see 经测试:本例中该方法的调用时机为需授权资源被访问时
	 * @see 经测试:并且每次访问需授权资源时都会执行该方法中的逻辑,这表明本例中默认并未启用AuthorizationCache
	 * @see 个人感觉若使用了Spring3
	 *      .1开始提供的ConcurrentMapCache支持,则可灵活决定是否启用AuthorizationCache
	 * @see 比如说这里从数据库获取权限信息时,先去访问Spring3.1提供的缓存,而不使用Shior提供的AuthorizationCache
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return AuthorizationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		List<String> roleList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();
		// 获取当前登录用户的角色
		ContextUser contextUser = (ContextUser) principals.fromRealm(getName())
				.iterator().next();
		String userId = contextUser.getUserId();
		// 从数据库中获取当前登录用户的角色列表信息
		List<SysRole> roles = roleService.querySysRoleByUserId(userId);
		for (SysRole role : roles) {
			roleList.add(role.getRoleName());
			// 实体类Role中包含有角色权限的实体类信息
			if (null != role.getSysMenus() && role.getSysMenus().size() > 0) {
				// 获取权限
				for (SysMenu menu : role.getSysMenus()) {
					if (!"".equals(menu.getMenuName())) {
						permissionList.add(menu.getMenuName());
					}
				}
			}
		}
		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addRoles(roleList);
		simpleAuthorInfo.addStringPermissions(permissionList);
		return simpleAuthorInfo;
	}

	/**
	 * Description： 验证当前登录的Subject
	 * 
	 * @see 经测试:本例中该方法的调用时机为LoginController.login()方法中执行Subject.login()时调用
	 * @author JingYu.Dai
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return AuthorizationInfo
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String accountNo = token.getUsername();
		if (accountNo != null && !"".equals(accountNo)) {
			try {
				SysUser sysuser = userService.getSysUserByAccountNo(accountNo);
				// 从数据库中取出user
				if (sysuser != null && sysuser.getPid() != null) {
					// 用户已被禁用
					if ("0".equals(sysuser.getStatus())) {
						throw new LockedAccountException();
					}
					Subject subject = SecurityUtils.getSubject();
					ContextUser contextUser = new ContextUser(sysuser.getPid(),
							sysuser.getAccountNo(), sysuser.getName(), null);
					subject.getSession().setAttribute(Constants.CONTEXT_USER,
							contextUser);
					List<Integer> roleList = new ArrayList<Integer>();
					List<Integer> permissionList = new ArrayList<Integer>();
					// 获取当前登录用户的角色
					String userId = contextUser.getUserId();
					// 从数据库中获取当前登录用户的角色列表信息
					List<SysRole> roles = roleService
							.querySysRoleByUserId(userId);
					for (SysRole role : roles) {
						roleList.add(role.getRoleCode());
						// 实体类Role中包含有角色权限的实体类信息
						if (null != role.getSysMenus()
								&& role.getSysMenus().size() > 0) {
							// 获取权限
							for (SysMenu menu : role.getSysMenus()) {
								permissionList.add(menu.getMenuCode());
							}
						}
					}
					subject.getSession().setAttribute(Constants.CONTEXT_USER_ROLE_LIST,
							roleList);
					subject.getSession().setAttribute(Constants.CONTEXT_USER_PERMISSION_LIST,
							permissionList);
					return new SimpleAuthenticationInfo(contextUser,
							sysuser.getPassword(), getName());
				}
			} catch (LockedAccountException e) {
				throw new LockedAccountException(e);
			} catch (Exception e) {
				throw new AuthenticationException(e);
			}
		}
		return null;
	}

}
