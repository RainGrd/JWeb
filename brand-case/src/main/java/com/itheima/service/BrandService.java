package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.ResultMap;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.itheima.service
 * @Author: RainGrd
 * @CreateTime: 2022-05-17  20:38
 * @Description: TODO
 * @Version: 1.0
 */
public interface BrandService {
    /**
     * @description: 查询所有的方法
     * @author: RainGrd
     * @date: 2022/5/17 20:38:20
     * @param: null
     * @return: null
     **/
    @ResultMap("brandResultMap")
    List<Brand> selectAll();
    /**
     * @description: 添加
     * @author: RainGrd
     * @date: 2022/5/18 7:54:14
     * @param: null
     * @return: null
     **/
    @ResultMap("brandResultMap")
    void add(Brand brand);
    /**
     * @description: 批量删除
     * @author: RainGrd
     * @date: 2022/5/18 14:08:26
     * @param: null
     * @return: null
     **/
    void deleteAll(int[] idList);
    /**
     * @description: 分页查询
     * @author: RainGrd
     * @date: 2022/5/18 16:40:22
     * @param currentPage 当前页面
     * @param pageSize 总记录数
     * @return pageBean<Brand>
     **/
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     * @description: 分页查询
     * @author: RainGrd
     * @date: 2022/5/18 16:40:22
     * @param currentPage 当前页面
     * @param pageSize 总记录数
     * @return pageBean<Brand>
     **/
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);

    void update(Brand brand);
    void delete(int id);
}
