package com.bdqn.filter;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-12  06:57
 * @Description: TODO
 * @Version: 1.0
 */

import com.bdqn.service.BookService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/imgHover.jsp")
public class ImgFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp= (HttpServletResponse) response;
        /*将查询过来的数据存放到域对象里*/
        BookService bookService = new BookService();
        HttpServletRequest req= (HttpServletRequest) request;
        req.setAttribute("books", bookService.selectAllBook());
        req.getRequestDispatcher("/imgHover.jsp").forward(req, resp);
        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}
