package com.bdqn.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hengwang
 * @date 2021/11/11 18:29
 */
@ConfigurationProperties(prefix = "ciyt.name")
public class SFCarProperties {

    private String cityName="";

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
