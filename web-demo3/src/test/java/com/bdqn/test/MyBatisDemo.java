package com.bdqn.test;

import com.bdqn.mapper.BrandMapper;
import com.bdqn.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：测试用例
 */
public class MyBatisDemo {
    @Test
    public void testSelectAll() {
        /*获取SqlSessionFactory对象*/
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            /*2.获取SqlSession对象，用于执行SQL对象*/
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /*3.Mapper代理开发执行sql语句*/
            BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
            /*查询所有数据*/
            System.out.println(mapper.selectAll());
            /*关闭连接*/
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAById() {
        /*接收参数*/
        int id = 1;
        /*获取SqlSessionFactory对象*/
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            /*2.获取SqlSession对象，用于执行SQL对象*/
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /*3.Mapper代理开发执行sql语句*/
            BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
            /*查询所有数据*/
            System.out.println(mapper.SelectById(id));
            /*关闭连接*/
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectByCondition() {
        /*接收参数*/
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        /*处理参数*/
        companyName = "%" + companyName + "%";
        brandName= "%" + brandName + "%";
        /*封装参数*/
        Brand brand=new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        /*进入集合*/
        Map map = new HashMap();
        map.put("status",status);
        map.put("companyName",companyName);
        map.put("brandName",brandName);
        /*获取SqlSessionFactory对象*/
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            /*2.获取SqlSession对象，用于执行SQL对象*/
            SqlSession sqlSession = sqlSessionFactory.openSession();
            /*3.Mapper代理开发执行sql语句*/
            BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
            System.out.println(mapper.selectByCondition(brand));
            /*查询所有数据*/
            System.out.println(mapper.selectByCondition(map));
            /*关闭连接*/
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
