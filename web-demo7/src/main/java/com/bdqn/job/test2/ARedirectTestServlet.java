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

@WebServlet("/aRedirectTestServlet")
public class ARedirectTestServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");

        /*重定向*/
        response.sendRedirect("/wen-demo7/bRedirectTestServlet?username="+username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
