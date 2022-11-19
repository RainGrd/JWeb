package com.bdqn.job2.factory.impl;

import com.bdqn.job2.dao.Arithmetic;
import com.bdqn.job2.dao.impl.MultiplicationImpl;
import com.bdqn.job2.factory.ArithmeticFactoryInterface;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:39
 * FileName: MultiplicationFactory
 * Description:
 */
public class MultiplicationFactory implements ArithmeticFactoryInterface {
    @Override
    public Arithmetic getInstance() {
        return new MultiplicationImpl();
    }
}
