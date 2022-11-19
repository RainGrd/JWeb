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
import com.itheima.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
    private BrandService brandService = new BrandService();
    private List<Brand> brands = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String condition = request.getParameter("selectBrand");
        brands=selectCondition(condition);
        request.setAttribute("brands",brands);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /**
     * @description: 判断查询条件
     * @author: RainGrd
     * @date: 2022/5/12 8:21:49
     * @param: null
     * @return: null
     **/
    public List<Brand> selectCondition(String condition){
        if (!"".equals(condition) && condition!=null) {
            /*判断是否是数字*/
            System.out.println(Util.isNumber(condition));
            if(Util.isNumber(condition)){
                int selectCondition = Integer.parseInt(condition);
                /*根据id进行查询*/
                brands=brandService.selectById(selectCondition);
                return Util.brandIsNull(brands);
            }else{
                /*模糊查询*/
                brands = brandService.selectBrandName(condition);
                return Util.brandIsNull(brands);
            }
        }else {
            return brandService.selectAll();
        }
    }
}
