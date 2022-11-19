package com.itheima.web;


/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {
    private BrandService brandService=new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收数据*/
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        /*转换数据为java数据*/
        Brand brand = JSON.parseObject(params, Brand.class);
        brand.setStatus(1);
        System.out.println(brand);
        /*调用servlet添加*/
        brandService.addBrand(brand);
        /*3.响应成功的标识*/
        response.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
