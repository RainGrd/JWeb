/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户信息拦截器（判断是否登录可以访问的页面）
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月19日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.vo.system.CustomerVo;

/**
 * Description：<br> 
 * 用户信息拦截器（判断是否登录可以访问的页面）
 * @author  Lin Xu
 * @date    2015年11月19日
 * @version v1.0.0
 */
public class UserInterceptor implements HandlerInterceptor {
	Logger logger = Logger.getLogger(UserInterceptor.class);
	//不需要进行拦截的二级菜单
	private static final String[] EXCLUDE_MAPPING = {"/login/","/index/","/help/","/api/","/forum/",
		"/resources/","/theme/","/sms/","/sysParam/","/activityArea/","/login/userController/upateEmail",
		"/login/userController/openUpdateEmail"};

	//获取Memcache客户端信息
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	/**
	 * 第一步：进行拦截处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String url = request.getRequestURL().toString();
        logger.info("拦截地址》》》：" + url);
        for (String s : EXCLUDE_MAPPING) {
            if (url.contains(s)) {
                flag = true;
                break;
            }
        }
        
        //1.获取访问的session的id
		String sessionId = request.getSession().getId();
		//2.获取登录的人员信息
    	CustomerVo user = (CustomerVo) memcachedClient.get(sessionId);
    	//获取当前session的值如果为空，就设置信息数据
    	CustomerVo thisVo = (CustomerVo) request.getSession().getAttribute(PtpConstants.CONSUMER);
    	if(null != user){
    		memcachedClient.replace(sessionId, 1800, user);
    	}
    	if(null == thisVo && null != user){
    		request.getSession().setAttribute(PtpConstants.CONSUMER, user);
    	}
    	if(null == user){
    		request.getSession().removeAttribute(PtpConstants.CONSUMER);
    	}
    	
        //看是否是需要校验有登录
        if (!flag) {
            //CustomerVo user = (CustomerVo) request.getSession().getAttribute(PtpConstants.CONSUMER);
            if (user != null) {
            	flag = true;
            }
        }
        //跳转登录页
        if(!flag){
        	response.sendRedirect("../../login/loginController/toLogin.shtml");
        }
        return flag;
	}

	/**
	 * 第二步：在第一步基础上进行拦截处理
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
	

	/**
	 * 最后一步：拦截后处理方法
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
}


