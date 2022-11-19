package com.bdqn.controller;

/**
 * @author hengwang
 * @date 2021/11/11 18:22
 */
public class SFCar {
    private int code;

    private String cityName;

    public String tip(){
        if(cityName.equals("北京") || cityName.equals("深圳") || cityName.equals("上海")){
            return "你所在的城市"+cityName+"已开通顺风车业务！";
        }
        return "你所在的城市"+cityName+"暂未开通顺风车业务！";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
