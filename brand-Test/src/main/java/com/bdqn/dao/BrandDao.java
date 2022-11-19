package com.bdqn.dao;

import com.bdqn.entity.Brand;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.press.dao
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  15:11
 * @Description: TODO
 * @Version: 1.0
 */
public interface BrandDao {
    /**
     * @description: 查询全部
     * @author: RainGrd
     * @date: 2022/5/28 15:15:39
     * @param: null
     * @return: null
     **/
    public List<Brand> selectBrandAll(int limit, int pageSize,Brand brand);
    /**
     * @description: 查询条数
     * @author: RainGrd
     * @date: 2022/5/28 15:34:42
     * @param: null
     * @return: null
     **/
    Integer selectTotalCount(Brand brand);
    /**
     * @description: 删除
     * @author: RainGrd
     * @date: 2022/5/28 16:42:40
     * @param: null
     * @return: null
     **/
    boolean delete(int id);
}
