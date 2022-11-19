package com.bdqn.web.cookie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/ServletDemo1")
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*创建cookie对象*/
        Cookie cookie1 = new Cookie("name", "zhangsan");
        /*发送cookie*/
        response.addCookie(cookie1);
        /*设置Cookie的存活时间：1周 604800秒*/
        cookie1.setMaxAge(604800);
        /*获取cookie对象*/
        Cookie[] cookies = request.getCookies();
        /*非空判断*/
        if(cookies != null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(cookie1.getName())){
                    System.out.println(cookie.getName() + ":" + cookie.getValue());
                    break;
                }
            }
        }else{
            Cookie cookie2 = new Cookie("age", "14");
            /*发送cookie*/
            response.addCookie(cookie2);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
