package com.bdqn.mapper;

import com.bdqn.entity.User;
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
     * 方法描述：登录方法
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 8:06:38 2022/4/22
     * @param userName 用户名
     * @param passWord 密码
     * @return User
     **/
    /*使用@Select注解进行SQL语句查询,*/
    @Select("select * from tb_user where username=#{userName} and password=#{passWord}")
    /*使用@Param注解进行字段赋值*/
    User LoginJudge(@Param("username")String userName,@Param("password") String passWord);
    /**
     * 方法描述：查询用户是否存在数据库
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 11:07:13 2022/4/22
     * @param 
     * @return 
     **/
    @Select("select * from tb_user where username=#{userName}")
    User selectUserName(String userName);
    /**
     * 方法描述：添加用户方法
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 11:21:03 2022/4/22
     * @param 
     * @return 
     **/
    @Insert("insert into tb_user(id,username,password,gender,addr) values (null,#{username},#{password},'男','湖南')")
    void addUser(User user);
    /**
     * 方法描述：查找所有用户
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 16:44:10 2022/4/22
     * @param 
     * @return 
     **/
    @Select("select * from tb_user")
    List<User> selectUsers();
}
