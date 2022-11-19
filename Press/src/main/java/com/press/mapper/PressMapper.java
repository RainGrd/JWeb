package com.press.mapper;

import com.press.entity.Press;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.press.mapper
 * @Author: RainGrd
 * @CreateTime: 2022-05-20  05:51
 * @Description: TODO
 * @Version: 1.0
 */
public interface PressMapper {
    /**
     * @description: 分页条件查询
     * @author: RainGrd
     * @date: 2022/5/20 6:00:52
     * @param: null
     * @return: null
     **/
    List<Press> selectByPressAll(@Param("begin") int begin, @Param("size") int size, @Param("press") Press press);
    /**
     * @description: 分布查询第一步，
     * @author: RainGrd
     * @date: 2022/5/26 16:52:44
     * @param: null
     * @return: null
     **/

    /**
     * @description: 初始化数据
     * @author: RainGrd
     * @date: 2022/5/21 13:47:11
     * @param: null
     * @return: null
     **/
    List<Press> selectInitPressAll(@Param("begin") int begin, @Param("size") int size);
    /**
     * @description: 根据Id查询新闻对象
     * @author: RainGrd
     * @date: 2022/5/23 15:34:46
     * @param: null
     * @return: null
     **/
    @Select("select * from press.t_press where press_id=#{pressId}")
    @ResultMap("pressResultMap")
    Press selectByPressId(int pressId);
    /**
     * @description: 查询总数
     * @author: RainGrd
     * @date: 2022/5/20 12:46:14
     * @param: null
     * @return: null
     **/
    int selectByTotalCount(Press press);

//    @Select("select count(*) from t_press limit ")
//    @ResultMap("pressResultMap")
//    int selectInitTotalCount();

    /**
     * @description: 查询全部
     * @author: RainGrd
     * @date: 2022/5/21 7:14:30
     * @param: null
     * @return: null
     **/
    @Select("select * from press.t_press where press_del_status=1")
    @ResultMap("pressResultMap")
    List<Press> selectPressAll();
    /**
     * @description: 批量删除
     * @author: RainGrd
     * @date: 2022/5/21 16:56:08
     * @param: null
     * @return: null
     **/
    void deleteAll(@Param("idList") int[] idList);
    /**
     * @description: 编辑新闻
     * @author: RainGrd
     * @date: 2022/5/22 15:54:31
     * @param: null
     * @return: null
     **/
    void update(Press press);
    /**
     * @description: 删除新闻
     * @author: RainGrd
     * @date: 2022/5/22 15:54:38
     * @param: null
     * @return: null
     **/
    void deletePress(@Param("id") int id);
    /**
     * @description: 发布新闻
     * @author: RainGrd
     * @date: 2022/5/22 16:24:54
     * @param: null
     * @return: null
     **/
    @ResultMap("pressResultMap")
    void addPress(Press press);
}
