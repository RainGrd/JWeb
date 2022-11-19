package com.bdqn.requst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
@WebServlet("/demo1")
public class Demo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req);
        /*获取请求的资源路径*/
        System.out.println(req.getRequestURI());
        /*获取请求的统一资源定位符（绝对路径）*/
        System.out.println(req.getRequestURL());
        /*获取客户端的ip地址*/
        System.out.println(req.getRemoteHost());
        /*获取请求的方式*/
        System.out.println(req.getMethod());
        /*获取请求头*/
        System.out.println(req.getHeader("User-Agent"));
        /*获取请求参数*/
        System.out.println(req.getQueryString());
        /*获取虚拟目录*/
        System.out.println(req.getContextPath());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取请求体*/
        /*ServletInputStream inputStream = req.getInputStream();
        byte[] bytes = new byte[1024];
        int len=0;
        while ((len=inputStream.read(bytes))!=-1){
            System.out.println(len);
        }*/
        BufferedReader reader = req.getReader();
        String line;
        /*StringBuffer buffer=null;
        line=reader.readLine();
        buffer=new StringBuffer(line);
        for (int i = 0; i < buffer.length(); i++) {
            System.out.print(buffer.charAt(i));
        }*/
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
    }
}
