package com.bdqn.utils;

import lombok.SneakyThrows;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.utils
 * @Author: RainGrd
 * @CreateTime: 2022-06-06  10:07
 * @Description: 数据库工具类
 * @Version: 1.0
 */
public class DBUtil {
    /**
     * 驱动类名
     **/
    private static String driver = null;
    /**
     * 驱动地址
     **/
    private static String url = null;
    /**
     * 用户名
     **/
    private static String username = null;
    /**
     * 密码
     **/
    private static String password = null;

    /**
     * 数据库连接
     */
    static Connection conn = null;
    /**
     * 加载连接
     */
    static PreparedStatement ps = null;
    /**
     * 结果集
     */
    static ResultSet rs = null;

    /**
     * 读取配置文件
     */
    static {
        Properties properties = new Properties();
        try {
            properties.load(DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*加载配置内容*/
        driver = properties.getProperty("jdbc.driver");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");
    }

    /**
     * 载入数据库驱动
     */
    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author RainGrd
     * @description 获取数据库链接。需要单独创建一个方法通过返回值返回链接对象 链接每次用完之后就会关闭
     * mysql连接url地址:jdbc:mysql://主机名:端口号/数据库名","用户名","登录密码
     * @date 19:55:23 2022/4/7
     **/
    public static void getConnection() throws Exception {
        /*返回成功之后的连接*/
        conn = DriverManager.getConnection(url, username, password);
    }

    /**
     * @author RainGrd
     * @description //TODO
     * @date 10:50:46 2022/6/6
     **/
    @SneakyThrows
    public static void close() {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * 获取预编译对象
     *
     * @param sql 要执行的SQL语句
     */
    @SneakyThrows
    public static void getResult(String sql) {
        getConnection();
        try {
            ps = conn.prepareStatement(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args  查询条件
     * @param clazz 实体类反射对象
     * @param sql   SQL语句
     * @author RainGrd
     * @description // 查询方法
     * @date 15:55:46 2022/6/6
     **/
    public static <E> List<E> queryList(Class<E> clazz, String sql, Object... args) {
        getResult(sql);
        List<E> list = new ArrayList<>();
        try {
            String[] dataColumns = splicingColumnName(args);
            String[] fields = fields(dataColumns);
            list = filling(dataColumns, fields, clazz, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }

    /**
     * @param args  查询条件
     * @param clazz 实体类反射对象
     * @param sql   SQL语句
     * @author RainGrd
     * @description 查询单个
     * @date 15:55:46 2022/6/6
     **/
    public static <T> T queryOne(Class<T> clazz, String sql, Object... args) {
        getResult(sql);
        T entity = null;
        try {
            String[] dataColumns = splicingColumnName(args);
            if (clazz.getName().equals("int") || clazz.getName().equals("long") || clazz.getName().equals("java.lang.Integer") || clazz.getName().equals("java.lang.Long")) {
                rs.next();
                return (T) Integer.valueOf(rs.getInt(1));
            }
            String[] fields = fields(dataColumns);
            entity = filling(dataColumns, fields, clazz, false).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return entity;
    }

    /**
     * @param args 参数
     * @throws SQLException sql异常
     * @descriptio 填充问号的参数
     */
    public static void populate(Object[] args) throws SQLException {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
            ps.setObject(i + 1, args[i]);
        }
    }

    /**
     * @param args 参数列表
     * @return java.lang.String
     * @description 获取列名
     */
    public static String[] splicingColumnName(Object[] args) throws SQLException {
        /*将条件设置到预编译对象里*/
        populate(args);
        /*查询全部*/
        rs = ps.executeQuery();
        /*等到查询出来的总数据*/
        ResultSetMetaData metaData = rs.getMetaData();
        /*获取查询出来的总列数*/
        int columnCount = metaData.getColumnCount();
        String[] arr = new String[columnCount];
        for (int i = 0; i < arr.length; i++) {
            /*获取数据库里面的列名*/
            arr[i] = metaData.getColumnLabel(i + 1);
        }
        return arr;
    }

    /**
     * @param args 从数据库里面查询出来的列名
     * @return java.lang.String
     * @description 处理数据库查询出来的列名
     */
    public static String[] fields(String[] args) {
        String[] arr = new String[args.length];
        for (int i = 0; i < arr.length; i++) {
            /*判断查询数据库字段的是否存在“_”*/
            if (args[i].contains("_")) {
                String[] split = args[i].split("_");
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < split.length; j++) {
                    /*将分割后的第一个字符串拼接进去*/
                    if (j == 0) {
                        sb.append(split[j]);
                    } else {
                        /*将下划线的第一个字符转换为大写*/
                        char[] chars = split[j].toCharArray();
                        chars[0] -= 32;
                        sb.append(chars);
                    }
                }
                arr[i] = sb.toString();
            } else {
                arr[i] = args[i];
            }
        }
        return arr;
    }

    /**
     * @param dataColumn 未处理的数据列
     * @param javaColumn 处理过后的数据列
     * @param aClass     实体类对象
     * @param <E>        被映射的类
     * @return java.util.List
     * @author RainGrd
     */
    public static <E> List<E> filling(String[] dataColumn, String[] javaColumn, Class<E> aClass, boolean flag) throws Exception {
        List<E> list = new ArrayList<>();
        E e;
        while (rs.next()) {
            /*flag为flase的话为查询单个*/
            if (!flag) {
                if (rs.getRow() > 1) {
                    throw new Exception("查询应该是一条，但是查出多条结果");
                }
            }
            /*通过反射类获取对象*/
            e = aClass.newInstance();
            for (int i = 0; i < dataColumn.length; i++) {
                /*查询符合条件的字段*/
                Field declaredField = aClass.getDeclaredField(javaColumn[i]);
                /*暴力破解*/
                declaredField.setAccessible(true);
                /*进行字段赋值*/
                declaredField.set(e, rs.getObject(dataColumn[i]));
            }
            /*添加被反射的对象*/
            list.add(e);
        }
        return list;
    }

    /**
     * 用于增删改
     *
     * @param sql  要执行sql语句
     * @param args 参数
     * @return int 参数
     */
    public static int update(String sql, Object... args) {
        getResult(sql);
        int updateCount = 0;
        try {
            populate(args);
            updateCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return updateCount;
    }


    public static <T> PageUtil<T> pageList(String sql, Class<T> clazz, Object... args) {
        /*1.将SQL转换为大写*/
        String sqlToUp = sql.toUpperCase();
        StringBuilder sb = new StringBuilder("SElECT COUNT(*) ");
        /*拼接form*/
        sb.append(sql.substring(sqlToUp.indexOf("FROM")));
        /*2.去掉limit后面的SQL语句*/
        sb.replace(sb.indexOf("LIMIT"), sb.length(), "");
        /*3.去掉分页参数*/
        Object[] objects = Arrays.copyOf(args, args.length - 2);
        Integer count = queryOne(int.class, sb.toString(), objects);
        /*4.获取传递过来的分页参数*/
        int pageSize = (int) args[args.length - 1];
        int pageNumber = (int) args[args.length - 2];
        /*处理参数*/
        pageNumber = pageNumber == 0 ? 0 : pageNumber - 1;
        args[args.length - 2] = pageNumber * pageSize;
        return new PageUtil<T>(queryList(clazz, sql, args), count,pageSize,pageNumber+1);
    }
}
