package com.items.service;

import com.items.entity.User;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：用户操作类
 */
public interface IUserService {

    /**
     * 方法描述：验证用户是否存在
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 15:21:54 2022/4/23
     * @param userName 用户名
     * @param password 密码
     * @return boolean
     **/
    boolean userJudge(String userName,String password);

    /**
     * 方法描述：如何注册
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 7:51:40 2022/4/24
     * @param 
     * @return 
     **/
    void addUser(User user);
}
