package com.bdqn;

import com.bdqn.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author hengwang
 * @date 2021/11/4 19:31
 */
//告诉springbooot 当前类就是程序的主入口
@SpringBootApplication
public class ApplicationMain {

    //main 程序的入口
    public static void main(String[] args) {
        //调用一个static静态方法run 才是springboot程序的主入口
        SpringApplication.run(ApplicationMain.class,args);
        System.out.println("hello springboot");
    }
}
