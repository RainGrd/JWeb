package com.book.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-10  10:07
 * @Description: TODO
 * @Version: 1.0
 */

import com.book.entity.Book;
import com.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/purchaseServlet")
public class PurchaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        System.out.println(id);
        if (id == null) {
            /*返回原页面*/
            response.sendRedirect("http://localhost:5050/web-demo7/listBookServlet");
            return;
        }
        BookService bookService = new BookService();
        Book book = bookService.selectBook(id);
        HttpSession session = request.getSession();
        List<Book> cart = (List)session.getAttribute("cart");
        if(cart == null){
            cart=new ArrayList<>();
            session.setAttribute("cart",cart);
        }
        cart.add(book);
        /*创建Cookie存放Session的标识号*/
        Cookie jsessionid = new Cookie("JSESSIONID", session.getId());
        jsessionid.setMaxAge(60*60*24);
        response.addCookie(jsessionid);
        response.sendRedirect("http://localhost:5050/web-demo7/cartServlet");
        System.out.println(book);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
