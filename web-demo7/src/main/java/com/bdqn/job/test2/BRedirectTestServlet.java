package com.bdqn.job.test2; /**
 * @author: ${user}
 * @date: ${date}
 * @description:
 * @version: 1.0
 * 类描述：
 */

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bRedirectTestServlet")
public class BRedirectTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            System.out.println("稍等");
            Thread.sleep(5000);
            /*稍等*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*重定向后的数据*/
        String username = request.getParameter("username");
        System.out.println("用户名："+username);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
