package com.itheima.web;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/addBrandServlet")
public class AddBrandServlet extends HttpServlet {
    private BrandService brandService=new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*接收数据*/
        Map<String, String[]> brandMap = request.getParameterMap();
        Brand brand=new Brand();
        try {
            BeanUtils.populate(brand,brandMap);
            brand.setStatus(1);
            /*设置*/
            System.out.println(brand);
            /*添加品牌*/
            brandService.addBrand(brand);
            /*重定向页面*/
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

}
