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
 * 2015年11月3日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.adapter;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.security.domain.ContextUser;
import com.yscf.core.model.system.SysButton;
import com.yscf.core.service.system.impl.SysButtonServiceImpl;
import com.yscf.system.constort.Constants;

/**
 * Description：<br> 
 * 按钮权限拦截
 * @author  JingYu.Dai
 * @date    2015年11月3日
 * @version v1.0.0
 */
public class ButtonPermissionsAdapter extends HandlerInterceptorAdapter {
	private Logger logger = LoggerFactory
			.getLogger(ButtonPermissionsAdapter.class);

	@Resource(name="sysButtonServiceImpl")
	SysButtonServiceImpl sysButtonService;
	
	
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
    	String menuId = request.getParameter("menuId");
    	if(null != menuId && !"".equals(menuId)){
    		Subject subject = SecurityUtils.getSubject();
    		ContextUser user = (ContextUser) subject.getSession().getAttribute(Constants.CONTEXT_USER);
    		List<SysButton> list = null;
    		try {
    			list = sysButtonService.getButtonsByMenuId(menuId,user.getUserId());
    		} catch (FrameworkException e) {
    			if (logger.isInfoEnabled()) {
    				e.printStackTrace();
    			}
    		}
    		request.setAttribute("buttons", list);
    	}
    	return true;
    }   

}


