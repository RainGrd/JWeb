package com.press.service.impl;

import com.press.entity.User;
import com.press.mapper.UserMapper;
import com.press.service.IUserService;
import com.press.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：用户操作类
 */
public class UserServiceImpl implements IUserService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public User selectUser(String username, String password) {
        /*创建工厂*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*关闭连接*/
        return mapper.selectUser(username, password);
    }

    @Override
    public User selectUserById(int id) {
        /*创建工厂*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        /*关闭连接*/
        return mapper.selectUserById(id);
    }

    @Override
    public void updatePassword(User user) {
        /*创建工厂*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updatePassword(user);
        sqlSession.commit();
        sqlSession.close();
    }
    /**
     * @description: 添加用户
     * @author: RainGrd
     * @date: 2022/5/24 16:26:08
     * @param: null
     * @return: null
     **/
    @Override
    public void addUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
    }
}
