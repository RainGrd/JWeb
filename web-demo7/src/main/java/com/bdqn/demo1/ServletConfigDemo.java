package com.bdqn.demo1;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletConfigDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*读取web.xml*/
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        /*获取servletconfig对象*/
        ServletConfig servletConfig = this.getServletConfig();
        /*获取config的参数值*/
        String encoding = servletConfig.getInitParameter("encoding");
        out.println("encoding="+encoding);
        out.println("<br/>");
        /*获取ServletContext的额采纳数*/
        out.println("----------------");
        out.println("<br/>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
