package com.bdqn.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author hengwang
 * @date 2021/11/11 18:32
 */
@Configuration   //让一个类变成配置类
@ConditionalOnClass({SFCar.class}) //条件注解，是Configuration的扩展注解
@EnableConfigurationProperties({SFCarProperties.class})//为类中的属性赋值
//当配置文件的特定配置项为指定的指时，创建所修饰类的实例
@ConditionalOnProperty(prefix = "city.name",value = "耒阳",matchIfMissing =true)
public class SFCarAutoConfigration {

    @Resource
    private SFCarProperties sfcarProperties;

    @Bean
    @ConditionalOnMissingBean({SFCar.class})
    public SFCar searcher(){
        SFCar sfcar =  new SFCar();
        sfcar.setCityName(sfcarProperties.getCityName());
        return sfcar;
    }

}
