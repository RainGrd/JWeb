package com.bdqn.job2.dao.impl;

import com.bdqn.job2.dao.Arithmetic;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/12 19:08
 * FileName: Division
 * Description: 除法运算类
 */
public class DivisionImpl implements Arithmetic {
    private Arithmetic arithmetic;

    public void setArithmetic(Arithmetic arithmetic) {
        this.arithmetic = arithmetic;
    }
    @Override
    public double calculate(int num1, int num2) {
        return num1 * num2;
    }
}
