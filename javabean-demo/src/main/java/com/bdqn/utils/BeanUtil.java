package com.bdqn.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.utils
 * @Author: RainGrd
 * @CreateTime: 2022-05-31  08:20
 * @Description: TODO
 * @Version: 1.0
 */
public class BeanUtil {
    public static void filling(Map<String, ?> map, Object obj) {
        /*非空判断*/
        if (map != null && obj != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                /*传递键和值*/
                setAttributes(obj, entry.getKey(), entry.getValue());
            }
        }
    }

    @SneakyThrows
    public static void setAttributes(Object bean, String key, Object parameter) {
        Class<?> aClass = bean.getClass();
        if(key.contains("[]")) key.substring(0,key.length()-2);
        /*比较字段*/
        Field field = aClass.getDeclaredField(key);
        /*暴力破解*/
        field.setAccessible(true);
        /*获取被映射的类的字段类型*/
        String typeName = field.getGenericType().getTypeName();
        /*判断*/
        /*String[] value= (String[]) parameter;
        System.out.println(value);*/

        /*
        * 根据字段类型进行赋值操作
        * 判断值是否多个还是一个
        *
        * */
        if ("java.lang.String".equals(typeName)) {

        }else {
            System.out.println(field);
            field.set(bean,parameter);
        }
    }
    /**
     * @description: 创建
     * @author: RainGrd
     * @date: 2022/5/31 11:28:41
     * @param: null
     * @return: null
     **/
}
