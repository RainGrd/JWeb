package com.press.web;
/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        *//*返回User对象*//*
        User user = Util.userParsing(User.class, request);
        *//*判断user状态的是1还是2还是3*//*
        *//*1:管理员
        * 2：教师
        * 3.学生
        * *//*
        *//*通过反射将JSON对象转换为java对象*//*
        boolean loginJudge = loginJudge(user, response);
        if (loginJudge) {
            *//*返回成功！*//*
            response.getWriter().write("success");
        }else{
            response.getWriter().write("error");
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * @description: 校验数据
     * @author: RainGrd
     * @date: 2022/5/10 14:15:04
     * @param: null
     * @return: null
     **/
    /*public boolean loginJudge(User userData, HttpServletResponse response){
        *//*接收的用户名和密码*//*
        String emailName = userData.getEmail();
        String password = userData.getPassword();
        *//*校验数据*//*
        boolean flag = userImpl.userJudge(emailName, password);
        if (flag) {
            *//*添加到cookie里面*//*
            Util.addCookie(userData, response);
            return true;
        } else {
            *//*登录失败，清空cookie*//*
            Util.deleteCookie("username",response);
            Util.deleteCookie("password",response);
//            response.getWriter().write("error");
            return false;
        }
    }*/
}
