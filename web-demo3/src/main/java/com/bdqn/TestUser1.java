package com.bdqn;

import com.bdqn.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：快速入门代码
 */
public class TestUser1 {
    public static void main(String[] args) {





        /*1.加载mybatis的核心配置文件 获取SQLSessionFactory对象*/
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            /*2.获取SqlSession对象，用于执行SQL对象*/
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /*3.执行sql语句*/
            List<User> userList = sqlSession.selectList("test.selectAll");
            System.out.println(userList);
            /*Mapper代理开发*/
            /*关闭连接*/
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
