package com.bdqn.job1.factory;

import com.bdqn.job1.dao.Arithmetic;
import com.bdqn.job1.dao.impl.AddImpl;
import com.bdqn.job1.dao.impl.DivisionImpl;
import com.bdqn.job1.dao.impl.MultiplicationImpl;
import com.bdqn.job1.dao.impl.SubtractionImpl;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:09
 * FileName: ArithmeticFactory
 * Description: 算法工厂类
 */
public class ArithmeticFactory {

    public static Arithmetic getInstance(String key){
        switch (key){
            case "+":
                return new AddImpl();
            case "-":
                return new SubtractionImpl();
            case "*":
                return new MultiplicationImpl();
            case "/":
                return new DivisionImpl();
            default:
                throw new RuntimeException("无效的运算符:"+key);
        }
    }
}
