package com.bdqn.mapper;

import com.bdqn.pojo.Brand;
import com.bdqn.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public interface BrandMapper {
    /**
     * 方法描述：查询所有数据
     *
     * @param
     * @return
     * @author 段荣贵
     * @description //TODO RainGrd
     * @date 11:29:38 2022/4/21
     **/
    List<Brand> selectAll();

    /**
     * 方法描述：查看详情
     *
     * @param
     * @return
     * @author 段荣贵
     * @description //TODO RainGrd
     * @date 14:39:39 2022/4/21
     **/
    Brand SelectById(int id);

    /**
     * 方法描述：多条件查询
     *
     * @param status 状态
     * @param brandName 品牌名称
     * @param companyName 企业名称
     * @return List<Brand>
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 15:03:59 2022/4/21
     *
     * 参数接收：
     * 1. 散装参数：如果方法中多个参数，需要使用@Param("SQL参数占位符名称")，给指定的字段赋值
     * 2. 对象参数：
     * 3. map集合：
     *
     **/
    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName );
    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map map);
}
