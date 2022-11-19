package com.bdqn.job1.test;

import com.bdqn.job1.dao.Arithmetic;
import com.bdqn.job1.dao.impl.AddImpl;
import com.bdqn.job1.factory.ArithmeticFactory;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:14
 * FileName: Demo
 * Description:
 */
public class Demo {
    public static void main(String[] args) {
        Arithmetic instance = ArithmeticFactory.getInstance("+");
        AddImpl add = new AddImpl();
        add.setArithmetic(instance);
        System.out.println(instance.calculate(1 , 2));
    }
}
