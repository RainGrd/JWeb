package com.bdqn.test;


import com.alibaba.fastjson.JSON;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */

public class FastJsonDemo {
    public static void main(String[] args) {

        /*将java对象装换为JSON对象*/
        User user = new User();
        user.setId(1);
        user.setUsername("zhangsan");
        user.setPassword("123");
        String s = JSON.toJSONString(user);
        /*{"id":1,"password":"123","username":"zhangsan"}*/
        System.out.println(s.toString());
        /*将JSON对象装换为java对象*/
        User user1 = JSON.parseObject("{\"id\":1,\"password\":\"123\",\"username\":\"zhangsan\"}", User.class);
        System.out.println(user1);
    }
}
