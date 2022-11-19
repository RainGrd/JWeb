package com.bdqn.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-17  14:30
 * @Description: TODO
 * @Version: 1.0
 */

import com.alibaba.fastjson.JSONObject;
import com.bdqn.entity.Book;
import com.bdqn.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@WebServlet("/imgHoverServlet")
public class ImgHoverServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Book book = new Book();
        try {
            BeanUtils.populate(book,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String bookName = book.getBookName();
        BookService service = new BookService();
        List<Book> books = service.selectBook(bookName);
        if (books != null) {
            response.getWriter().write(mapper.writeValueAsString(books));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public JSONObject receivePost(HttpServletRequest request) throws UnsupportedEncodingException, IOException {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

        String line = null;
        StringBuilder sb = new StringBuilder();

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        // 将json字符串转化为json对象
        JSONObject json =JSONObject.parseObject(sb.toString());
        return json;
    }
}
