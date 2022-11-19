package com.bdqn.util;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.util
 * @Author: RainGrd
 * @CreateTime: 2022-06-22  08:40
 * @Description: TODO
 * @Version: 1.0
 */
public class DBUtil {
    private DBUtil() {

    }
    public static final DBUtil dbUtil=new DBUtil();

    public static final DBUtil getInterface(){
        return dbUtil;
    }

    public  void getConn(){}

}
