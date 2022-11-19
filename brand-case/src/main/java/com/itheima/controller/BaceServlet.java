package com.itheima.controller;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-18  08:30
 * @Description: servlet代码优化
 * @Version: 1.0
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//@WebServlet("/brand/*")
public class BaceServlet extends HttpServlet {
    private static final long serialVersionUID = -6364313094088770977L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        /*获取请求路径*/
        String requestURI = req.getRequestURI();
        /*获取最后一段请求路径，然后执行方法*/
        int index=requestURI.lastIndexOf('/');
        String methodName = requestURI.substring(index+1);
        /*执行方法*/
        Class<? extends BaceServlet> cls = this.getClass();
        try {
            Method clsMethod = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            /*执行方法*/
            clsMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
