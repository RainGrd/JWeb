package com.book.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-10  11:14
 * @Description: TODO
 * @Version: 1.0
 */

import com.book.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/cartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        List<Book> cart = null;
        boolean flag = true;
        /*获取session的数据*/
        HttpSession session = request.getSession();
        if (isSessionNull(session)) {
            cart = (List) session.getAttribute("cart");
            flag = isCartNull(cart);
        }
        if (!flag) {
            out.write("对不起！你还没有购买任何商品！<br/>");
        } else {
            out.write("您购买的图书有:<br/>");
            for (Book book : cart) {
                out.write(book.getName() + "<br/>");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * @description: 用于判断session是否为空
     * @author: RainGrd
     * @date: 2022/5/10 11:28:11
     * @param: null
     * @return: null
     **/
    public boolean isSessionNull(HttpSession session) {
        return session == null ? false : true;
    }

    /**
     * @description: 判断购物车是否为空
     * @author: RainGrd
     * @date: 2022/5/10 11:34:01
     * @param: null
     * @return: null
     **/
    public boolean isCartNull(List<Book> carts) {

        return carts == null ? false : true;
    }
}
