package com.bdqn.job.test1;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@WebServlet("/carServlet")
public class CarServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*获取城市名*/
        String address = request.getParameter("address").trim();
        /*读取配置文件*/
        ServletContext servletContext = this.getServletContext();
        InputStream resourceAsStream = servletContext.getResourceAsStream("/WEB-INF/classes/car.properties");
        Properties properties = new Properties();
//        properties.load(resourceAsStream);
        properties.load(new InputStreamReader(resourceAsStream,"UTF-8"));
        String property = properties.getProperty("address");
        /*进行比较*/
        String[] split = property.split(",");
        for (int i = 0; i < split.length; i++) {
            if (address.equals(split[i])) {
                response.getWriter().println("已成功打到滴滴顺风车");
            }else{
                response.getWriter().println("由于滴滴平台发生过杀人事件，根据据国家监管机构责另滴滴平台整改,<br/>暂时只能开放：北京,上海,深圳三个地方的滴滴业务。<br/>其他城市暂不开放，开放时间见相关部门的通知，");
                break;
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
