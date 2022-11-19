package com.itheima.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-12  19:39
 * @Description: TODO
 * @Version: 1.0
 */

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.util.Util;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    private  BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        Brand brand=new Brand();
        try {
            BeanUtils.populate(brand,parameterMap);
            updateBrand(brand);
            response.sendRedirect("/brand-demo/index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /**
     * @description:
     * @author: RainGrd
     * @date: 2022/5/12 19:44:20
     * @param: null
     * @return: null
     **/
    public List<Brand> updateBrand(Brand brand){
        brandService.update(brand);
        return Util.brandIsNull(brandService.selectAll());
    }
}
