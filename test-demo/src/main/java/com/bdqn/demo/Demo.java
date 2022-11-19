package com.bdqn.demo;

import com.bdqn.util.DBUtil;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.demo
 * @Author: RainGrd
 * @CreateTime: 2022-06-22  08:28
 * @Description: TODO
 * @Version: 1.0
 */
public class Demo {
    public static void main(String[] args) {

        DBUtil dbUtil = DBUtil.getInterface();
        System.out.println(dbUtil);


    }
}
