package com.press.service;

import com.press.entity.User;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：用户操作类
 */
public interface IUserService {
    /**
     * @description: 根据用户和密码查看是否存在此对象
     * @author: RainGrd
     * @date: 2022/5/19 9:53:58
     * @param username 用户对象
     * @param password 用户密码
     * @return user
     **/
    User selectUser(String username, String password);
    /**
     * @description: 根据id查询用户
     * @author: RainGrd
     * @date: 2022/5/19 16:13:13
     * @param: null
     * @return: null
     **/
    User selectUserById(int id);
    /**
     * @description: 根据id修改密码
     * @author: RainGrd
     * @date: 2022/5/23 14:38:17
     * @param: null
     * @return: null
     **/
    void updatePassword(User user);
    /**
     * @description: 添加用户
     * @author: RainGrd
     * @date: 2022/5/24 16:27:44
     * @param: null
     * @return: null
     **/
    void addUser(User user);
}
