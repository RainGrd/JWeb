package com.book.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-10  10:08
 * @Description: TODO
 * @Version: 1.0
 */

import com.book.entity.Book;
import com.book.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/listBookServlet")
public class ListBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        /*获取数据库里面的数据*/
        BookService bookService = new BookService();
        List<Book> books = bookService.selectBookAll();
        for (Book book : books) {
            String url="http://localhost:5050/web-demo7/purchaseServlet?id="+book.getId();
            out.write(book.getId()+"."+book.getName()+"<a href='"+url+"'>点击购买</a><br/>");
            System.out.println(book.getId()+"."+book.getName()+"<a href='"+url+"'>点击购买</a>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
