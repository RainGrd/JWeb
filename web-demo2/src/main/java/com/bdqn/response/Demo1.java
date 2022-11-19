package com.bdqn.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
@WebServlet("/resp1")
public class Demo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("resp1...");
        /*1.设置响应状态码*/
//        resp.setStatus(302);
        /*2.设置响应头 location 虚拟目录*/

//        resp.setHeader("location","/web-demo2/resp2");
        /*动态获取虚拟目录 需要pom.xml配置*/
        String path = req.getContextPath();
        resp.sendRedirect(path+"/resp2");

//        resp.sendRedirect("https://www.baidu.com");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
