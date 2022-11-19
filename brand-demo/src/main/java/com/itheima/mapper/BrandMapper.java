package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from tb_brand where status=1")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * @param brand Brand对象
     * @description: 添加
     * @author: RainGrd
     * @date: 2022/5/13 10:16:41
     **/
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Select("select * from tb_brand where id in (#{id}) and status=1")
    @ResultMap("brandResultMap")
    Brand selectById(int id);

    /**
     * @param brandName 查询条件
     * @return List<Brand>
     * @description: 根据品牌名称查询
     * @author: RainGrd
     * @date: 2022/5/12 8:18:03
     **/
    List<Brand> selectByBrandName(String brandName);
    List<Brand> selectByBrandName(Brand brand);

    /**
     * 修改
     *
     * @param brand
     */
    @Update("update tb_brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description} where id=#{id}")
    void update(Brand brand);

    /**
     * @description: 根据id来修改状态
     * @author: RainGrd
     * @date: 2022/5/12 15:06:47
     * @param: null
     * @return: null
     **/
    @Update("update tb_brand set status=0 where id=#{id}")
    void delete(int id);
}
