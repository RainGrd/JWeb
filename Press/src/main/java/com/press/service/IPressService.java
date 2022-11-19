package com.press.service;

import com.press.Vo.PressVO;
import com.press.entity.Press;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.press.service
 * @Author: RainGrd
 * @CreateTime: 2022-05-20  13:04
 * @Description: TODO
 * @Version: 1.0
 */
public interface IPressService {
    /**
     * @description: 条件分页查询
     * @author: RainGrd
     * @date: 2022/5/20 13:04:10
     * @param: null
     * @return: null
     **/
    PressVO<Press> selectPressVoCondition(int currentPage,int pageSize,Press press);
    PressVO<Press> selectPressAll();
    PressVO<Press> selectInitPressAll(int currentPage,int pageSize);
    /**
     * @description: 批量删除
     * @author: RainGrd
     * @date: 2022/5/21 17:00:43
     * @param: null
     * @return: null
     **/
    void deletePressAll(int[] idList);
    /**
     * @description: 修改
     * @author: RainGrd
     * @date: 2022/5/22 9:41:42
     * @param: null
     * @return: null
     **/
    void updatePress(Press press);
    /**
     * @description: 删除方法
     * @author: RainGrd
     * @date: 2022/5/22 15:57:45
     * @param: null
     * @return: null
     **/
    void deletePress(int id);
    /**
     * @description: 新增
     * @author: RainGrd
     * @date: 2022/5/22 19:22:24
     * @param: null
     * @return: null
     **/
    void addPress(Press press);
    /**
     * @description: 根据新闻编号查询新闻对象
     * @author: RainGrd
     * @date: 2022/5/23 15:20:40
     * @param: null
     * @return: null
     **/
    Press selectPressById(int pressId);
}
