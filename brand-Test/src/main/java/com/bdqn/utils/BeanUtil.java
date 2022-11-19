package com.bdqn.utils;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<String> list = pojoList(bean);
        /**/
        if (key.contains("[]")) key= key.substring(0, key.length() - 2);
        if(list.contains(key)){
            /*比较字段*/
            Field field = aClass.getDeclaredField(key);
            /*暴力破解*/
            field.setAccessible(true);
            if("java.lang.String".equals(parameter.getClass().getTypeName())){
                String value = (String) parameter;
                setAttribute(bean,field,value);
            }else{
                String[] value = (String[]) parameter;
                if(value.length==1){
                    setAttribute(bean,field,value[0]);
                }else{
                    setAttribute(bean,field,value);
                }
            }
        }

    }
    /**
     * @description: 根据被映射的类的字段进行
     * @author: RainGrd
     * @date: 2022/5/31 11:28:41
     * @param: null
     * @return: null
     **/
    public static List<String> pojoList(Object bean){
        List<String> list=new ArrayList<>();
        /*
        * 将bean的字段分别存放到集合里面
        * */
        /*根据反射获取对象*/
        Class<?> beanClass = bean.getClass();
        /*根据对象获取字段名并存放到字段*/
        for (Field field : beanClass.getDeclaredFields()){
            list.add(field.getName());
        }
        return list;
    }
    /**
     * @description: 针对多个参数方法
     * @author: RainGrd
     * @date: 2022/5/31 15:56:00
     * @param: null
     * @return: null
     **/
    @SneakyThrows
    public static void setAttribute(Object bean, Field field, String[] value){
        String typeName = field.getGenericType().getTypeName();
        if("java.lang.String".equals(typeName)){
            field.set(bean, value);
        }else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName) || "int[]".equals(typeName)){
            field.set(bean, Arrays.stream(value).mapToInt(Integer::parseInt).toArray());
        }else if ("java.lang.Long".equals(typeName) || "long".equals(typeName) || "long[]".equals(typeName)){



        }
        /*根据创过来的参数数*/
    }
    /**
     * @description: 针对一个参数方法
     * @author: RainGrd
     * @date: 2022/5/31 15:56:00
     * @param: null
     * @return: null
     **/
    @SneakyThrows
    public static void setAttribute(Object bean, Field field, String value){
        String typeName = field.getGenericType().getTypeName();
        if("java.lang.String".equals(typeName)){
            field.set(bean,value);
        }else if ("java.lang.Integer".equals(typeName) || "int".equals(typeName) || "int[]".equals(typeName)){
            field.set(bean, Integer.parseInt(value));
        }else {

        }
    }
    /**
     * @description: 根据
     * @author: RainGrd
     * @date: 2022/5/31 16:25:28
     * @param: null
     * @return: null
     **/
    public static void populate(Object bean,Map<String,Class<? extends Object>>map){
        if(bean==null || map==null){
            return;
        }
        /*判断非空*/
        for (Map.Entry<String, Class<?>> entry : map.entrySet()) {
            if(entry.getKey()==null){
                return;
            }
            setProperty(bean,entry.getKey(),entry.getValue());
        }

    }
    public static void setProperty(Object bean,String key,Object value){

        Class<?> aClass = bean.getClass();



        Field[] fields = aClass.getDeclaredFields();
        /*暴力破解*/



        /*
         * 根据被映射的实体类的每个字段的set方法来进行赋值
         * */

    }



}
