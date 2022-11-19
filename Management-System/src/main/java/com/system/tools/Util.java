package com.system.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.entity.User;
import com.system.service.impl.TeacherImpl;
import com.system.service.impl.UserImpl;

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
    private static TeacherImpl teacherImpl = null;
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
     * 方法描述：请求转发
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
     * @description: 返回解析JSON的对象
     * @author: RainGrd
     * @date: 2022/5/10 20:27:37
     * @param valueType 各种类的class对象
     * @param request 请求对象
     * @return <T>
     **/
    public static <T> T userParsing(Class <T> valueType, HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String readLine = reader.readLine();
        return mapper.readValue(readLine, valueType);
    }

    /**
     * @param user 用户对象
     * @description: 将用户存放在cookie里面
     * @author: RainGrd
     * @date: 2022/5/11 14:14:57
     **/
    public static void addCookie(User user, HttpServletResponse response) {
        String username = user.getEmail().replace("\"", "").trim();
        Cookie cUserName = new Cookie("username", username);
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
     **/
    public static void deleteCookie(String name,HttpServletResponse response) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * @param email 邮件地址
     * @return {boolean}
     * @description: 检测用户是否存在
     * @author: RainGrd
     * @date: 2022/5/11 14:28:55st
     **/
    public static boolean userExist(String email) {
        userImpl = new UserImpl();
        if (userImpl.selectUser(email) != null)
            return true;
        else
            return false;
    }
    /*创建*/
}
