package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author RainGrd
 */
public interface BrandMapper {
    /**
     * @description: 查询所有
     * @author: RainGrd
     * @date: 2022/5/17 20:37:43
     * @param: null
     * @return: null
     **/
    @Select("select * from tb_brand where del_status=1")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * @description: 添加品牌
     * @author: RainGrd
     * @date: 2022/5/18 7:57:04
     * @param: null
     * @return: null
     **/
    void add(Brand brand);

    /**
     * @description: 根据传过来的ID进行批量删除
     * @author: RainGrd
     * @date: 2022/5/18 14:01:03
     * @param: null
     * @return: null
     **/

    void deleteAll(@Param("idList") int[] idList);

    /**
     * @param begin 开始索引
     * @param size    查询的条目数
     * @description: 分页查询
     * @author: RainGrd
     * @date: 2022/5/18 16:35:57
     * @return: null
     **/
    @Select("select * from tb_brand where del_status=1 limit #{begin},#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    /**
     * @description: 返回查询的条数
     * @author: RainGrd
     * @date: 2022/5/18 16:39:37
     * @param: null
     * @return: null
     **/
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    /**
     * @description: 返回查询的条数
     * @author: RainGrd
     * @date: 2022/5/18 16:39:37
     * @param: null
     * @return: null
     **/
    int selectTotalCountByCondition(Brand brand);

    /**
     * @description: 分页条件查询
     * @author: RainGrd
     * @date: 2022/5/18 17:59:30
     * @param: null
     * @return: null
     **/
    List<Brand> selectPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand") Brand brand);
    /**
     * @description: 根据品牌查询id
     * @author: RainGrd
     * @date: 2022/5/19 7:14:10
     * @param: null
     * @return: null
     **/

    /**
     * @description: 修改品牌
     * @author: RainGrd
     * @date: 2022/5/18 20:25:25
     * @param: null
     * @return: null
     **/

    void update(Brand brand);
    /**
     * @description: 删除
     * @author: RainGrd
     * @date: 2022/5/19 8:15:04
     * @param: null
     * @return: null
     **/
    void delete(@Param("id") int id);
}
