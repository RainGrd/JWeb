package com.system.service;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.system.service
 * @Author: RainGrd
 * @CreateTime: 2022-05-16  16:42
 * @Description: TODO
 * @Version: 1.0
 */
public interface ITeacherService {
    /**
     * @description: 用于展示查询的所有数据
     * @author: RainGrd
     * @date: 2022/5/16 16:50:29
     * @param: null
     * @return: null
     **/
    List<Object> selectTeacherList();
}
