package com.bdqn.web;
/**
 * @BelongsProject: JWeb
 * @BelongsPackage: ${PACKAGE_NAME}
 * @Author: RainGrd
 * @CreateTime: 2022-05-09  09:40
 * @Description: TODO
 * @Version: 1.0
 */

import com.bdqn.entity.Person;
import com.bdqn.utils.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet("/introspectorServlet")
public class IntrospectorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> m = request.getParameterMap();
        /*BeanUtils beanUtils=new BeanUtils();*/
//        System.out.println(m);
        //创建封装属性的目标对象person
        Person p = new Person();
        BeanUtil.filling(m,p);
        try {
            //调用方法
//            BeanUtils.populate(p,m);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().println(p);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    /*重写工具类方法*/
    private void setProperty(Object obj, Map<String, String[]> m) throws Exception {
        // 将请求参数中 map的key 与传入对象属性名称 比较，如果一致，将参数的值赋值给对象属性
        //使用内省类获取BeanInfo类的对象
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        //获取所有的属性描述器
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        //遍历数组取出每一个属性描述器
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            //获取Person类中的属性
            String property_name = descriptor.getName();
            //判断属性在map中是否存在对应值
            if(m.containsKey(property_name)){
                //包含
                //获取对应的value值
                String value = m.get(property_name)[0];
                /*
                     Method getWriteMethod() 获得应该用于写入属性值的方法。
                 */
                Method setter = descriptor.getWriteMethod();
                //将value 写到属性中
                setter.invoke(obj, value);
            }
        }
    }
}
