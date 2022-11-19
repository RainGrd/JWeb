package com.bdqn.test;

import com.bdqn.entity.Person;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.test
 * @Author: RainGrd
 * @CreateTime: 2022-05-09  09:03
 * @Description: TODO
 * @Version: 1.0
 */
public class IntrospectorDemo2 {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        PropertyDescriptor pd = new PropertyDescriptor("name", person.getClass());
        Method setter = pd.getWriteMethod();
        Method getter = pd.getReadMethod();
        System.out.println(getter.getName());
        System.out.println(getter.getModifiers());
        setter.invoke(person,"小明");
        pd = new PropertyDescriptor("age", person.getClass());
        Method setterAge = pd.getWriteMethod();
        Method getterGet = pd.getReadMethod();
        /*判断什么类型的实参*/
        Class<?> propertyType = pd.getPropertyType();
        System.out.println(propertyType);
        if (propertyType.equals(int.class)) {
            setterAge.invoke(person,15);
        }
        System.out.println(person);
    }
}
