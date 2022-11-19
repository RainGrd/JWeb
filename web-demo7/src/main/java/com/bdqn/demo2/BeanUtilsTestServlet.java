package com.bdqn.demo2; /**
 * @author: ${user}
 * @date: ${date}
 * @description:
 * @version: 1.0
 * 类描述：
 */

import com.bdqn.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/beanUtilsTestServlet")
public class BeanUtilsTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        User user = new User();
        /*使用BeanUtil工具类读取数据*/
        try {
            BeanUtils.populate(user,request.getParameterMap());
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
