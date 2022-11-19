package com.bdqn.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：
 */
public class Util {
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
    /*声明属性*/
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    /**
     * @author 段荣贵
     * @description //TODO 肖萧奈何桥
     * @date 9:40:41 2022/4/8
     * @param
     * @return
     * 方法描述：
     * 注册驱动，驱动只需要加载一次
     * 即可所以写在静态代码段中
     * （static）类的静态代码块，随着类的加载，只执行一次。
     **/
    static {
        try {
            /*加载驱动*/
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param
     * @return 方法描述：
     * 建立链接。需要单独创建一个方法
     * 通过返回值返回链接对象
     * 链接每次用完之后就会关闭
     * mysql连接url地址:jdbc:mysql://主机名:端口号/数据库名","用户名","登录密码
     * @author 段荣贵
     * @description //TODO 肖萧奈何桥
     * @date 19:55:23 2022/4/7
     **/
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String user = "root";
        String password = "drg1224605";
        try {
            /*返回连接*/
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*如果连接不成功，那么就返回null*/
        return null;
    }

    /**
     * @param sql,args 语句，对象数组
     * @return boolean
     * @author 段荣贵
     * @description //TODO 肖萧奈何桥
     * @date 19:55:16 2022/4/7
     * 方法描述：增删改的通用方法
     * Object ... 可变参数，多半用于重写
     **/
    public static boolean executeUpdate(String sql, Object... args) {
        try {
            ps = getConnection().prepareStatement(sql);
            /*遍历args的长度*/
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /*执行更新语句*/
            int i = ps.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /*关闭连接，释放资源*/
            close(conn, ps, null);
        }
        return false;
    }

    /**
     * @param sql,args 语句,对象数组
     * @return 方法描述：用于普通遍历查询
     * @author 段荣贵
     * @description //TODO 肖萧奈何桥
     * @date 20:13:36 2022/4/7
     **/
    public static List<Map<String, Object>> executeQuery(String sql, Object... args) {
        try {
            /*实例化对象，创建连接*/
            conn = getConnection();
            /*准备好的声明*/
            ps = conn.prepareStatement(sql);
            /*遍历args的长度,获取数据*/
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            /*执行查询*/
            rs = ps.executeQuery();
            /*存放数据到List中*/
            List<Map<String, Object>> list = new ArrayList<>();
            /*查询本次查询结果集有多少列*/
            int count = rs.getMetaData().getColumnCount();
            /*通过while循环遍历结果集存放数据*/
            while (rs.next()) {
                /*一行数据用一个Map接收*/
                Map<String, Object> map = new HashMap<>();
                /*通过for循环进行*/
                for (int i = 0; i < count; i++) {
                    /*获取每个列的名称*/
                    String name = rs.getMetaData().getColumnLabel(i + 1);
                    /*通过列名来获取这个列的值*/
                    map.put(name, rs.getObject(name));
                }
                /*将每一个的数据添加存放到List中*/
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*关闭连接*/
            close(conn, ps, rs);
        }
        return null;
    }

    /**
     * @param
     * @return 方法描述：消除链接，释放资源
     * @author 段荣贵
     * @description //TODO 肖萧奈何桥
     * @date 20:14:23 2022/4/7
     **/
    public static void close(Connection conn, PreparedStatement st, ResultSet rs) {
        try {
            /*判断连接是否为null*/
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
