package com.bdqn.job2.factory.impl;

import com.bdqn.job2.dao.Arithmetic;
import com.bdqn.job2.dao.impl.AddImpl;
import com.bdqn.job2.factory.ArithmeticFactoryInterface;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:36i
 * FileName: AddFactory
 * Description: 加法工厂类
 */
public class AddFactory implements ArithmeticFactoryInterface {
    @Override
    public Arithmetic getInstance() {
        return new AddImpl();
    }
}
