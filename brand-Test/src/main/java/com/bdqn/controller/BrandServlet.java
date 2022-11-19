package com.bdqn.controller;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  11:19
 * @Description: TODO
 * @Version: 1.0
 */

import com.bdqn.Vo.BrandVo;
import com.bdqn.entity.Brand;
import com.bdqn.service.BrandService;
import com.bdqn.service.impl.BrandServiceImpl;
import com.bdqn.utils.BeanUtil;
import com.bdqn.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    BrandService brandService=new BrandServiceImpl();
    ObjectMapper mapper=new ObjectMapper();
    /**
     * @description: 查询全部
     * @author: RainGrd
     * @date: 2022/5/28 11:19:20
     * @param: null
     * @return: null
     **/
    @SneakyThrows
    public void selectBrandAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*Brand brand = Util.parsing(Brand.class, request);
        */
        Brand brand = new Brand();
        BeanUtil.filling(request.getParameterMap(),brand);
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//        BeanUtils.populate(brand,request.getParameterMap());
        System.out.println(brand);
        BrandVo<Brand> brandBrandVo = brandService.selectBrandAll(currentPage, pageSize,brand);
        response.getWriter().write(mapper.writeValueAsString(brandBrandVo));
        System.out.println(brandBrandVo);
//        System.out.println(brand);
//        response.getWriter().write(mapper.writeValueAsString(brand));
    }
    /**
     * @description: 删除
     * @author: RainGrd
     * @date: 2022/5/28 16:52:08
     * @param: null
     * @return: null
     **/
    public void deleteBrand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Brand brand = Util.parsing(Brand.class, request);
        System.out.println(brand);
        int id = brand.getId();
        boolean b = brandService.deleteBrand(id);
        if(b){
            response.getWriter().write("deleteSuccess");
        }
    }

}
