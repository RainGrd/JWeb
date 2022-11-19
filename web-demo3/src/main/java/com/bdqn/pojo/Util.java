package com.bdqn.pojo;

import com.bdqn.mapper.UserMapper;
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
 * 类描述：
 */
public class Util {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        /**/
        try {
            /*1.加载mybatis的核心配置文件 获取SQLSessionFactory对象*/
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 方法描述：返回SqlSessionFactory对象
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 15:39:34 2022/4/21
     * @param 
     * @return
     **/
    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }
    /**
     * 方法描述：
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 15:43:21 2022/4/21
     * @param 
     * @return 
     **/
}
