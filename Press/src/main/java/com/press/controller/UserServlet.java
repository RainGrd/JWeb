package com.press.controller;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-19  08:58
 * @Description: TODO
 * @Version: 1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.press.entity.User;
import com.press.service.IUserService;
import com.press.service.impl.UserServiceImpl;
import com.press.tools.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private IUserService userService = new UserServiceImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private static final long serialVersionUID = -6345432477751137578L;

    /**
     * @description: 登录方法
     * @author: RainGrd
     * @date: 2022/5/19 8:59:10
     * @param: null
     * @return: null
     **/
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = Util.parsing(User.class, request);
        /*获取*/
        /*
         * 根据user对象查询，
         * */
        User selectUser = userService.selectUser(user.getUsername(), user.getPassword());
        /*获取对象*/
        if (selectUser != null) {
            Util.addCookie(selectUser, response);
            /*将用户名和密码存放到cookie里面*/
            /*请求转发*/
            response.getWriter().write(mapper.writeValueAsString(selectUser));
            request.getSession().setAttribute("user",selectUser);
        } else {
            /*没有此对象清空cookie*/
            Util.deleteCookie("username", response);
            Util.deleteCookie("password", response);
            response.getWriter().write("error");
        }
    }

    /**
     * @description: 登录成功后进入到管理页面的方法
     * @author: RainGrd
     * @date: 2022/5/19 15:24:56
     * @param: null
     * @return: null
     **/
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
    /**
     * @description: 注册方法
     * @author: RainGrd
     * @date: 2022/5/19 15:17:40
     * @param: null
     * @return: null
     **/
    public void registered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = Util.parsing(User.class, request);
        Util.deleteCookie("username", response);
        Util.deleteCookie("password", response);
        Util.addCookie(user,response);
        userService.addUser(user);
        response.getWriter().write("registeredSuccess");
    }
    /**
     * @description: 修改密码
     * @author: RainGrd
     * @date: 2022/5/24 9:05:48
     * @param: null
     * @return: null
     **/
    public void updataPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = Util.parsing(User.class, request);
        User userSession = (User) request.getSession().getAttribute("user");
        userSession.setPassword(user.getPassword());
        userService.updatePassword(userSession);
        response.getWriter().write("changePwdSuccess");
    }
    /**
     * @description: 退出登录
     * @author: RainGrd
     * @date: 2022/5/24 20:28:40
     * @param: null
     * @return: null
     **/
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
//        request.getRequestDispatcher("/login.jsp").forward(request,response);
        response.sendRedirect("/login.jsp");
    }
}
