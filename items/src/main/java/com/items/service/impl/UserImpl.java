package com.items.service.impl;

import com.items.entity.User;
import com.items.mapper.UserMapper;
import com.items.service.IUserService;
import com.items.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：用户操作类
 */
public class UserImpl implements IUserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
    @Override
    public boolean userJudge(String emailName, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*查询指定用户*/
        User user = mapper.selectUser(emailName,password);
        /*判断用户是否存在*/
        if(user!=null) {
            /*释放资源*/
            sqlSession.close();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addUser(User user) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);
        /*提交事务*/
        sqlSession.commit();
        /*释放资源*/
        sqlSession.close();

    }
    /**
     * 方法描述：查询指定用户名的用户
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     * @param
     * @return
     **/
    public User selectUser(String emailName) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectUserName(emailName);
    }

    /**
     * 方法描述：显示所有用户
     * @author gui.rong.duan
     * @description //TODO RainGrd
     * @date 10:21:32 2022/4/24c
     * @param
     * @return
     **/
    public List<User> selectUserAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.selectAll();
    }
}
