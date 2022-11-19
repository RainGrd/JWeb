package com.items.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.items.entity.User;
import com.items.service.impl.UserImpl;
import com.items.types.UserTime;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public class Util {
    private static UserImpl userImpl = null;
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 方法描述：生成随机用户名的方法
     *
     * @param
     * @return
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    public static String[] createUserName() {


        return new String[]{""};
    }

    /**
     * 方法描述：用于传输注册成功和失败的信息
     *
     * @param info 要传输的信息
     * @param path 传输文件的路径
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    public static void showInfo(String infoName, String info, String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(infoName, info);
        request.getRequestDispatcher(path).forward(request, response);
    }

    /**
     * 方法描述：判断手机号码是否存在
     *
     * @param
     * @return
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     **/
    public static boolean judgePhone(String phone) {
        userImpl = new UserImpl();
        boolean flag = false;
        List<User> users = userImpl.selectUserAll();
        for (User user : users) {
            if (phone.equals(user.getPhone())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @description: 返回解析的用户对象
     * @author: RainGrd
     * @date: 2022/5/10 20:27:37
     * @param: null
     * @return: null
     **/
    public static User parsing(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        return mapper.readValue(readLine, User.class);
    }



    /**
     * @param user 用户对象
     * @description: 将用户存放在cookie里面
     * @author: RainGrd
     * @date: 2022/5/11 14:14:57
     **/
    public static void addDataCookie(User user, HttpServletResponse response) {
        String username = user.getEmail().replace("\"", "").trim();
        Cookie cUserName = new Cookie("username", username);
        Cookie cPassWord = new Cookie("password", user.getPassword());
        /*添加到response对象里面*/
        response.addCookie(cUserName);
        response.addCookie(cPassWord);

    }

    /**
     * @param cookies cookie数组
     * @description:
     * @author: RainGrd
     * @date: 2022/5/11 15:51:19
     **/
    public static void CookieTime(Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(UserTime.SURVIVAL_TIME);
        }
    }


    /**
     * @param email 邮件地址
     * @return {boolean}
     * @description: 检测用户是否存在
     * @author: RainGrd
     * @date: 2022/5/11 14:28:55st
     **/
    public static boolean userExist(String email) {
        if (userImpl.selectUser(email) != null)
            return true;
        else
            return false;
    }
}
