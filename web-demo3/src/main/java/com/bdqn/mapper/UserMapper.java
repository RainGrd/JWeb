package com.bdqn.mapper;

import com.bdqn.pojo.User;

import java.util.List;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public interface UserMapper {
    List<User> selectAll();
}
