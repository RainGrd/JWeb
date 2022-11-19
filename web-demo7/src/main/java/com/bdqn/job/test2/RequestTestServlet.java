package com.bdqn.job.test2; /**
 * @author: ${user}
 * @date: ${date}
 * @description:
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/requestTestServlet")
public class RequestTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        /*测试request的方法*/
        String uri = request.getRequestURI();
        String method = request.getMethod();
        StringBuffer url = request.getRequestURL();
        String host = request.getRemoteHost();
        String path = request.getContextPath();
        String remoteAddr = request.getRemoteAddr();
        String localAddr = request.getLocalAddr();
        String localName = request.getLocalName();
        int localPort = request.getLocalPort();
        Enumeration<String> names = request.getHeaderNames();
        out.write("主机地址:" + host + "<br/>");
        out.write("虚拟目录:" + path + "<br/>");
        out.write("请求方式:" + method + "<br/>");
        out.write("客户端的IP地址:" + remoteAddr + "<br/>");
        out.write("地址栏:" + url.toString() + "<br/>");
        out.write("URL:" + uri + "<br/>");
        out.write("WEB服务器的IP地址:" + localAddr + "<br/>");
        out.write("WEB服务器的主机名:" + localName + "<br/>");
        out.write("客户机所使用的网络端口号:" + localPort + "<br/>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", "drg");
        this.doGet(request, response);
    }
}
