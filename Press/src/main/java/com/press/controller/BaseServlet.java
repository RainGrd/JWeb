package com.press.controller;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-19  08:56
 * @Description: TODO
 * @Version: 1.0
 */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    private static final long serialVersionUID = -2608048153562210950L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /**/
        /*获取请求路径*/
        String requestURI = request.getRequestURI();
        /*获取最后一段请求路径，然后执行方法*/
        int index=requestURI.lastIndexOf('/');
        String methodName = requestURI.substring(index+1);
        System.out.println(methodName);
        /*执行方法*/
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            Method clsMethod = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            /*执行方法*/
            clsMethod.invoke(this,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
