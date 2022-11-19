package com.press.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.press.entity.User;
import com.press.service.IUserService;
import com.press.service.impl.UserServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public class Util {
    private IUserService userServlet = new UserServiceImpl();
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * @description: 返回解析JSON的对象
     * @author: RainGrd
     * @date: 2022/5/10 20:27:37
     * @param valueType 各种类的class对象
     * @param request 请求对象
     * @return <T>
     */
    public static <T> T parsing(Class <T> valueType, HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        return mapper.readValue(readLine, valueType);
    }

    /**
     * @param user 用户对象
     * @description: 将用户存放在cookie里面
     * @author: RainGrd
     * @date: 2022/5/11 14:14:57
     */
    public static void addCookie(User user, HttpServletResponse response) {
        Cookie cUserName = new Cookie("username", user.getUsername());
        Cookie cPassWord = new Cookie("password", user.getPassword());
        /*添加到response对象里面*/
        response.addCookie(cUserName);
        response.addCookie(cPassWord);
    }
    /**
     * @description: 清空cookie
     * @author: RainGrd
     * @date: 2022/5/16 18:55:00
     * @param name cookie名
     * @param response 响应对象
     */
    public static void deleteCookie(String name,HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    /*利用*/
}
