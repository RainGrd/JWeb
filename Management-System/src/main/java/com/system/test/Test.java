package com.system.test;


import com.system.entity.User;
import com.system.service.impl.UserImpl;

import java.util.List;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public class Test {
    public static void main(String[] args) {
        UserImpl user = new UserImpl();
        List<User> users = user.selectUserAll();
    }
}
