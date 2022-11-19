package com.bdqn.web;

import com.bdqn.entity.User;
import com.bdqn.mapper.UserMapper;
import com.bdqn.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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
 * 类描述：登录
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*1.接收用户名密码并清除左右空格*/
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        /*进行校验*/
        /*非空判断*/
        /*2.调用Mybatis进行查询*/
        /*2.1 获取SqlSessionFactory对象*/
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        /*2.2 获取SqlSession对象,无法省略，用于指定工厂*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*2.3 获取Mapper对象*/
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*2.4 调用登录验证方法*/
        User user = mapper.LoginJudge(username, password);
        /*2.5 释放资源*/
        sqlSession.close();
        /*获取字符输出流 并设置字符编码*/
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        /*3.判断user是否为null*/
        if(user!=null){
            /*存储数据*/
            req.setAttribute("","");
        }else{
            writer.write("登录失败！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*调用get方法*/
        this.doGet(req,resp);
    }
}
