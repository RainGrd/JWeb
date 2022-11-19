package com.itheima.filter;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-12  06:57
 * @Description: TODO
 * @Version: 1.0
 */

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/index.jsp")
public class IndexFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse resp= (HttpServletResponse) response;
        resp.sendRedirect("/brand-demo/selectServlet");
//        chain.doFilter(request, response);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }


}
