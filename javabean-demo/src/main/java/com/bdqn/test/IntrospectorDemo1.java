package com.bdqn.test;

import com.bdqn.entity.Person;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
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
public class IntrospectorDemo1 {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        BeanInfo beanInfo = Introspector.getBeanInfo(person.getClass());
        PropertyDescriptor[] p = beanInfo.getPropertyDescriptors();
        System.out.println(p.length);
        for (PropertyDescriptor descriptor : p) {
            System.out.println(descriptor);
        }
        BeanInfo beanInfo1 = Introspector.getBeanInfo(person.getClass(),Object.class);
        PropertyDescriptor[] p1 = beanInfo1.getPropertyDescriptors();
        /*遍历规则：根据字母表顺序来*/
        for (PropertyDescriptor descriptor : p1) {
            String name = descriptor.getName();
            //获取实体类的get方法
            Method getter = descriptor.getReadMethod();
            //获取实体类的set方法
            Method setter = descriptor.getWriteMethod();
            if("name".equals(name)){
                setter.invoke(person,"段荣贵");
            }
            if ("age".equals(name)) {
                setter.invoke(person, 15);
            }
            System.out.println(getter.invoke(person));
        }
        PropertyDescriptor pd = new PropertyDescriptor("name", Person.class);
        Method getter = pd.getReadMethod();
        Method setter = pd.getWriteMethod();

    }
}
