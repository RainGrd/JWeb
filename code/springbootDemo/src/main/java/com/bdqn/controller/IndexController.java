package com.bdqn.controller;

import com.bdqn.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author hengwang
 * @date 2021/11/4 19:38
 */
@Controller
public class IndexController {

    @Resource
    private User user;

    @ResponseBody
    @RequestMapping("/toIndex")
    public String toIndex(){
        System.out.println("user:"+user);
        return "/test";
    }
}
