package com.itheima.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-12  11:31
 * @Description: TODO
 * @Version: 1.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleterAllServlet")
public class DeleteAllServlet extends HttpServlet {
    private BrandService brandService = new BrandService();
    private List<Brand> brands = new ArrayList<>();
    ObjectMapper mapper= new ObjectMapper();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        Brand brand=new Brand();
//        try {
//            BeanUtils.populate(brand,parameterMap);
//            System.out.println(parameterMap);
//            Integer deleteId=brand.getId();
//            deleteBrand(deleteId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String[] idLists = request.getParameter("idList").split(",");
        for (int i = 0; i < idLists.length; i++) {
            deleteBrand(Integer.parseInt(idLists[i]));
        }
        /*重定向*/
        response.sendRedirect("/brand-demo/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /**
     * @description: 删除方法,根据Id修改状态值并查询数据
     * @author: RainGrd
     * @date: 2022/5/12 14:36:23
     * @param: null
     * @return: null
     **/
    public void deleteBrand(Integer deleteId){
        brandService.updateStatus(deleteId);
    }
}
