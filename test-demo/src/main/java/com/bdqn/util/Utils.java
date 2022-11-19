package com.bdqn.util;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.util
 * @Author: RainGrd
 * @CreateTime: 2022-06-22  08:48
 * @Description: TODO
 * @Version: 1.0
 */
public class Utils {
    private Utils(){

    }
    public static  Utils UTILS=null;


    public synchronized static  final Utils getInterface(){
        if (UTILS == null) {
            UTILS = new Utils();
        }
        return UTILS;
    }
}
