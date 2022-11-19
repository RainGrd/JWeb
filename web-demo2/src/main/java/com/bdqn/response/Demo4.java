package com.bdqn.response;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：响应字节数据
 */
@WebServlet("/resp4")
public class Demo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("resp4...");
        /*1.读取文件*/
        FileInputStream inputStream = new FileInputStream("D:\\lenovo\\Pictures\\wallhaven-k7v9yq.png");
        /*2.获取字节输出流*/
        ServletOutputStream outputStream = resp.getOutputStream();
        /*3.完成流的copy*//*
        byte[] bytes = new byte[1024];
        int len=0;
        while ((len=inputStream.read(bytes))!=-1) {
            outputStream.write(bytes,0,len);
        }*/
        /*使用工具类插件完成*/
        IOUtils.copy(inputStream, outputStream);
        outputStream.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
