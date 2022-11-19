package com.bdqn.job1.dao.impl;

import com.bdqn.job1.dao.Arithmetic;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:06
 * FileName: Subtraction
 * Description: 减法实现类
 */
public class SubtractionImpl implements Arithmetic {
    private Arithmetic arithmetic;

    public void setArithmetic(Arithmetic arithmetic) {
        this.arithmetic = arithmetic;
    }
    @Override
    public double calculate(int num1, int num2) {
        return num1 - num2;
    }
}
