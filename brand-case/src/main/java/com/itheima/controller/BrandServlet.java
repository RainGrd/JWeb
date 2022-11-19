package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.itheima.controller
 * @Author: RainGrd
 * @CreateTime: 2022-05-18  08:47
 * @Description: TODO
 * @Version: 1.0
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaceServlet{

    private static final long serialVersionUID = 616176972841798685L;
    private BrandService brandService=new BrandServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    /**
     * @description: 查询所有方法
     * @author: RainGrd
     * @date: 2022/5/18 8:30:43
     * @param: null
     * @return: null
     **/
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        /*调用servlet查询*/
        List<Brand> brands = brandService.selectAll();
        String json = mapper.writeValueAsString(brands);
        response.getWriter().write(json);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String data = reader.readLine();
        Brand brand = mapper.readValue(data, Brand.class);
        brandService.add(brand);
        response.getWriter().write("addSuccess");
    }
    /**
     * @description: 批量删除
     * @author: RainGrd
     * @date: 2022/5/18 13:58:49
     * @param: null
     * @return: null
     **/
    public void deleteAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        String data = reader.readLine();
        int[] ints = mapper.readValue(data, int[].class);
        brandService.deleteAll(ints);
        response.getWriter().write("deleteSuccess");
    }
    /**
     * @description: 分页查询
     * @author: RainGrd
     * @date: 2022/5/18 13:58:49
     * @param: null
     * @return: null
     **/
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收当前页码和每页展示条数*/
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int pageSize = Integer.parseInt(_pageSize);
        int currentPage=Integer.parseInt(_currentPage);
        PageBean<Brand> brandPageBean = brandService.selectByPage(currentPage, pageSize);
        response.getWriter().write(mapper.writeValueAsString(brandPageBean));
    }
    /**
     * @description: 条件分页查询
     * @author: RainGrd
     * @date: 2022/5/18 13:58:49
     * @param: null
     * @return: null
     **/
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*接收当前页码和每页展示条数*/
        String _currentPage = request.getParameter("currentPage");
        System.out.println(_currentPage);
        String _pageSize = request.getParameter("pageSize");
        System.out.println(_pageSize);
        int pageSize = Integer.parseInt(_pageSize);
        int currentPage=Integer.parseInt(_currentPage);
        /*获取查询条件里面的brand对象*/
        BufferedReader reader = request.getReader();
        String data = reader.readLine();
        System.out.println(data);
        Brand brand = mapper.readValue(data, Brand.class);
        System.out.println(brand);
        PageBean<Brand> brandPageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);
        response.getWriter().write(mapper.writeValueAsString(brandPageBean));
    }
    /**
     * @description: 修改
     * @author: RainGrd
     * @date: 2022/5/18 13:58:49
     * @param: null
     * @return: null
     **/
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取查询条件里面的brand对象*/
        BufferedReader reader = request.getReader();
        String data = reader.readLine();
        Brand brand = mapper.readValue(data, Brand.class);
        brandService.update(brand);
        response.getWriter().write("updateSuccess");
    }
    /**
     * @description: 修改
     * @author: RainGrd
     * @date: 2022/5/18 13:58:49
     * @param: null
     * @return: null
     **/
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取查询条件里面的brand对象*/
        String _id = request.getParameter("id");
        int id=Integer.parseInt(_id);
        brandService.delete(id);
        response.getWriter().write("deleteSuccess");
    }
}
