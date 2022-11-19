package com.bdqn.requst;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
@WebServlet("/demo2")
public class Demo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*获取所有参数的Map集合*/
        Map<String, String[]> map = req.getParameterMap();
        for (String key : map.keySet()) {
            /*输出格式：username:zhangsan*/
            System.out.print(key+":");
            /*获取值*/
            String[] values = map.get(key);
            for (String value : values) {
                System.out.print(value+" ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        /*根据Key获取获取参数值*/
        String[] hobbies = req.getParameterValues("hobby");
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);

    }
}
