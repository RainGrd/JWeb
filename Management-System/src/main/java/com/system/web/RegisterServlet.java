package com.system.web;
/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.entity.User;
import com.system.service.impl.UserImpl;
import com.system.tools.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static UserImpl userImpl = null;
    ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        User user = Util.userParsing(User.class,request);
        int i = registerJudge(user);
        PrintWriter writer = response.getWriter();
        /*判断返回过来的值是0,1,2*/
        if(i==0){
            /*设置注册账号的默认登录状态值*/
            user.setStatus("0");
            userImpl = new UserImpl();
            /*添加用户*/
            userImpl.addUser(user);
            /*将用户对象存放在cookie里面*/
            Util.addCookie(user, response);
            writer.write("success");
        }else if (i == 1) {
            writer.write("email_register");
        }else if (i == 2) {
            writer.write("phone_register");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /**
     * @param jsonUser 用户对象
     * @description: 注册所用的方法
     * @author: RainGrd
     * @date: 2022/5/10 20:46:43
     **/

    public int registerJudge(User jsonUser) throws IOException {
        String phone = jsonUser.getPhone();
        String email = jsonUser.getEmail();
        /*if ("".equals(checkCode) || checkCode == null) {
            Util.showInfo("register_msg", "注册失败！验证码不能为空!", "/register.html", request, response);
            return;
        }
        /*忽略大小写进行比对 错误的话直接返回注册页面*/
        /*
        if (!checkCodeGen.equalsIgnoreCase(checkCode)) {
            Util.showInfo("register_msg", "验证码输入错误！", "/register.html", request, response);
            return;
        }*/
        /*校验数据*/
        if (Util.userExist(email)) {
            return 1;
        }
        if (Util.judgePhone(phone)) {
            return 2;
        }
        /*成功标识*/
        return 0;
    }


}
