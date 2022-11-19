package com.itheima.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-14  16:53
 * @Description: 用于导出excel文件
 * @Version: 1.0
 */

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/exportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
    BrandService brandService = new BrandService();
    List<Brand> brands = brandService.selectAll();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        export(response);
        request.setAttribute("brands",brands);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    /**
     * @description: 用于导出Excel文件
     * @author: RainGrd
     * @date: 2022/5/14 16:55:16
     * @param: null
     * @return: null
     **/
    public void export(HttpServletResponse response) throws UnsupportedEncodingException {
        /*创建文件输入流*/
        ExcelWriter writer = ExcelUtil.getWriter();
        /*起表头*/
        writer.addHeaderAlias("id", "序号");
        writer.addHeaderAlias("brandName", "品牌名称");
        writer.addHeaderAlias("companyName", "企业名称");
        writer.addHeaderAlias("ordered", "排序字段");
        writer.addHeaderAlias("description", "描述信息");
        writer.addHeaderAlias("status", "状态");
        /*写入内容*/
        writer.write(brands, true);
//设置编码不然中文会乱码
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //filename=() 设置文件名
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("品牌表".getBytes("utf-8"), "ISO-8859-1") + ".xls");
        //把目标写入流
        ServletOutputStream out= null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭writer，释放内存
            writer.close();
        }
        IoUtil.close(out);
    }
}
