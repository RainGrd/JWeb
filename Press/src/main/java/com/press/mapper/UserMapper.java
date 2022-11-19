package com.press.mapper;

import com.press.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
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
     * @param username 用户名
     * @param password 密码
     * @return User
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    @Select("select * from press.t_user where user_name=#{username} and password=#{password}")
    @ResultMap("userResultMap")
    User selectUser(@Param("username") String username,@Param("password") String password);
    /**
     * @description: 根据用户名返回对象
     * @author: RainGrd
     * @date: 2022/5/23 14:36:02
     * @param: null
     * @return: null
     **/
    @Select("select * from press.t_user where user_name=#{username};")
    @ResultMap("userResultMap")
    User selectUserByUsername(@Param("username") String username);
    /**
     * 方法描述：根据id查询用户
     *
     * @param id 用户编号
     * @return User
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 14:40:14 2022/4/23
     **/
    @Select("select * from press.t_user where user_id=#{id}")
    User selectUserById(int id);
    /**
     * 方法描述：查询所有用户
     *
     * @param
     * @return
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    List<User> selectAll();

    /**
     *
     * @param user 用户实例
     * @author gui.rong.duan
     * @description //添加用户
     * @date 14:36:52 2022/4/23
     **/
    @Insert("insert into press.t_user values (null,#{username},#{password},2,1,#{email},#{phone});")
    void addUser(User user);
    /**
     * @description: 修改密码
     * @author: RainGrd
     * @date: 2022/5/23 19:55:34
     * @param: null
     * @return: null
     **/
    void updatePassword(User user);
}
