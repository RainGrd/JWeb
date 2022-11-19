package com.bdqn.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：响应字符数据
 */
@WebServlet("/resp3")
public class Demo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("resp3...");
        /*响应数据*/
        resp.setContentType("text/html;charset=utf-8");
        /*1.获取数据*/
        PrintWriter writer = resp.getWriter();

        writer.write("username");
        /*设置浏览解析HTML文本，并设置文本编码为UTF-8*/
//        resp.setHeader("content-type","text/html");
        writer.write("你好");
        writer.write("<h1>username</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
