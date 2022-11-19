package com.bdqn.job.test1;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ServletContext servletContext = this.getServletContext();
        /*接收次数*/
        Integer counts = (Integer) servletContext.getAttribute("counts");
        if (counts == null) {
            counts = 1;
        } else {
            counts += 1;
        }
        /*设置次数*/
        servletContext.setAttribute("counts", counts);
        /*输出到页面*/
        out.println("<head><meta charset=\"UTF-8\"><title>滴滴顺风车打车平台</title></head>");
        out.println("<body><h2>本页面被访问次数为<span style=" + "font-size:30px;color:red;>" + counts + "</span>次</h2></body>");
        System.out.println(counts);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
