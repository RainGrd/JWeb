package com.bdqn.service;

import com.bdqn.Vo.BrandVo;
import com.bdqn.entity.Brand;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.service
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  10:07
 * @Description: TODO
 * @Version: 1.0
 */
public interface BrandService {
    /**
     * @description: 查询全部
     * @author: RainGrd
     * @date: 2022/5/28 11:14:08
     * @param: null
     * @return: null
     **/
    BrandVo<Brand> selectBrandAll(int currentPage, int pageSize,Brand brand);
    /**
     * @description: 删除
     * @author: RainGrd
     * @date: 2022/5/28 16:50:27
     * @param: null
     * @return: null
     **/
    boolean deleteBrand(int id);
}
