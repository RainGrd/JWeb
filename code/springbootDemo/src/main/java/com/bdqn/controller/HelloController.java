package com.bdqn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/12/21.
 */
@Controller
public class HelloController {
    @Resource
    private SayHello sayHello;
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return sayHello.sayHello();
    }
}
