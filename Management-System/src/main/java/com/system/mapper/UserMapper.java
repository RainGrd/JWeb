package com.system.mapper;

import com.system.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public interface UserMapper {

    /**
     * 方法描述：判断emailName和密码来返回User对象
     *
     * @param email 邮箱名
     * @param password 密码
     * @return User
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    @Select("select * from tb_user where email=#{email} and password=#{password}")
    User selectUser(@Param("email") String email, @Param("password") String password);

    /**
     * 方法描述：根据邮箱名查询用户
     *
     * @param email 用户名
     * @return User
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 14:40:14 2022/4/23
     **/
    @Select("select * from tb_user where email=#{email}")
    User selectUserName(String email);

    /**
     * 方法描述：查询所有用户
     *
     * @param
     * @return
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    @Select("select * from tb_user")
    List<User> selectAll();

    /**
     * 方法描述：
     *
     * @param user 用户实例
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 14:36:52 2022/4/23
     **/
    @Insert("insert into tb_user values (null,#{username},#{password},#{phone},#{email},#{status})")
    void addUser(User user);
}
