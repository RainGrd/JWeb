package com.bdqn.test;

import com.bdqn.entity.Person;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.test
 * @Author: RainGrd
 * @CreateTime: 2022-05-09  09:03
 * @Description: TODO
 * @Version: 1.0
 */
public class IntrospectorDemo3 {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        person.setAge(18);
        person.setName("阿贵");
        PropertyDescriptor pd = new PropertyDescriptor("name",person.getClass());

    }
}
