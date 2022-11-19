package com.items.filter;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：登录验证的过滤器
 */

import com.items.tools.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login.html")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /*判断session对象的User键是否存在*/
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        /*获取*/
        HttpServletResponse resp = (HttpServletResponse) response;
        if (user == null) {
            /*放行*/
            chain.doFilter(request, response);
        }else{
            /*登录失败，跳转到登录页面*/
//            chain.doFilter(request, response);
            Util.showInfo("login_msg","你尚未登录","/login.html",req,resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    @Override
    public void destroy() {
    }


}
