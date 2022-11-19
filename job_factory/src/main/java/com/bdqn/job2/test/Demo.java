package com.bdqn.job2.test;

import com.bdqn.job2.dao.Arithmetic;
import com.bdqn.job2.dao.impl.AddImpl;
import com.bdqn.job2.factory.ArithmeticFactoryInterface;
import com.bdqn.job2.factory.impl.AddFactory;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:14
 * FileName: Demo
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        ArithmeticFactoryInterface factoryInterface=new AddFactory();
        Arithmetic instance = factoryInterface.getInstance();
        AddImpl add = new AddImpl();
        add.setArithmetic(instance);
        System.out.println(instance.calculate(1 , 2));
    }
}
