package com.bdqn.job.test2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/requestParamServlet")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String city = request.getParameter("city");
        String[] languages = request.getParameterValues("language");
        for (String language : languages) {
            System.out.println(language);
        }
        response.getWriter().write("用户名："+username+"<br/>密码："+password+"<br/>性别："+sex+"<br/>城市："+city+"<br/>选择使用的语言:"+ Arrays.toString(languages));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
