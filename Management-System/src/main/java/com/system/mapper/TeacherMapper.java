package com.system.mapper;

import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.system.mapper
 * @Author: RainGrd
 * @CreateTime: 2022-05-16  16:40
 * @Description: TODO
 * @Version: 1.0
 */
@Alias("teacher")
public interface TeacherMapper {
    /**
     * @description: 查询所有
     * @author: RainGrd
     * @date: 2022/5/16 16:43:18
     **/
    @Select("select * from tb_techerinfo where del_status = 1")
    @ResultMap("teacherResultMap")
    List<Object> selectTeacherAll();
}
