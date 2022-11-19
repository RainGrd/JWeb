package com.bdqn.demo1;


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
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

@WebServlet("/servletConTextDemo")
public class ServletConTextDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        ServletContext servletContext = this.getServletContext();
        servletContext.setAttribute("data", "this servlet save data");
        Enumeration<String> parameterNames = servletContext.getInitParameterNames();
        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String nextElement = attributeNames.nextElement();
            if (nextElement.equals("data")) {
                out.println("<br/>" + servletContext.getAttribute(nextElement));
            }
        }
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String parameter = servletContext.getInitParameter(name);
            out.println(name + ":" + parameter);
            out.println("<br/>");
        }
        out.println(servletContext.getAttribute("data"));
        /*删除数据*/
        servletContext.removeAttribute("data");
        out.println("<br/>" + servletContext.getAttribute("data")+"<br/>");
        /*读取配置文件*/
        InputStream resourceAsStream = servletContext.getResourceAsStream("/WEB-INF/classes/itcast.properties");
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        Set<String> resourcePaths = servletContext.getResourcePaths("/WEB-INF/classes/itcast.properties");
        System.out.println(resourcePaths);
        out.println("Company=" + properties.getProperty("Company") + "<br/>");
        out.println("Address=" + properties.getProperty("Address") + "<br/>");
        System.out.println(properties.get("Company"));
        System.out.println(properties.getOrDefault("Company", ""));
        System.out.println(properties.getOrDefault("Address", ""));
        Iterator<String> iterator = resourcePaths.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
