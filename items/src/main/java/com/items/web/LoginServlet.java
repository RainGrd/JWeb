package com.items.web;
/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.items.entity.User;
import com.items.service.impl.UserImpl;
import com.items.tools.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserImpl userImpl=new UserImpl();
    ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*返回User对象*/
        User userJson= Util.parsing(request);
        System.out.println(userJson);
        /*通过反射将JSON对象转换为java对象*/
        loginJudge(userJson, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /**
     * @description: 校验数据
     * @author: RainGrd
     * @date: 2022/5/10 14:15:04
     * @param: null
     * @return: null
     **/
    public void loginJudge(User userData,HttpServletResponse response) throws IOException {
        /*接收的用户名和密码*/
        String emailName = userData.getEmail();
        String password = userData.getPassword();
        /*校验数据*/
        boolean flag = userImpl.userJudge(emailName, password);
        if (flag) {
            /*添加到cookie里面*/
            Util.addDataCookie(userData, response);
            HashMap<String, Object> map = new HashMap<>();
            /*成功标识*/
            map.put("success", 1);
            /*传过去的状态*/
            map.put("status", 0);
            String json = mapper.writeValueAsString(map);
            /*返回成功！*/
            response.getWriter().write(json);
        } else {
            /*登录失败*/
            response.getWriter().write("error");
        }
    }
}
