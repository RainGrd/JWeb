package com.bdqn.mapper;

import com.bdqn.entity.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.mapper
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  10:06
 * @Description: TODO
 * @Version: 1.0
 */
public interface BrandMapper {
    List<Brand> selectBrandList(@Param("begin") int begin, @Param("size") int size);
    /**
     * @description: 查询总数
     * @author: RainGrd
     * @date: 2022/5/28 11:35:33
     * @param: null
     * @return: null
     **/
    Integer selectBrandTotalCount();
    /**
     * @description: 删除
     * @author: RainGrd
     * @date: 2022/5/28 15:11:05
     * @param: null
     * @return: null
     **/
}
