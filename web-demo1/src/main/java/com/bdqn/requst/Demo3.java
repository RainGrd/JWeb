package com.bdqn.requst; /**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/demo3")
public class Demo3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*POST解决请求参数中文乱码*/
//        request.setCharacterEncoding("UTF-8");/*设置字符输入流的编码*/
        /*获取username*/
        String username = request.getParameter("userName");
        System.out.println(username);
        /*tomcat7:获取GET，获取参数的方式解决中文乱码*/
        /*先转成字节*/
        /*byte[] bytes = username.getBytes(StandardCharsets.ISO_8859_1);
        username= new String(bytes, StandardCharsets.UTF_8);*/
        username=new String(username.getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        System.out.println(username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
