package com.system.service.impl;

import com.system.mapper.TeacherMapper;
import com.system.service.ITeacherService;
import com.system.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.system.service.impl
 * @Author: RainGrd
 * @CreateTime: 2022-05-16  16:43
 * @Description: TODO
 * @Version: 1.0
 */
public class TeacherImpl implements ITeacherService {
    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    /**
     * @description: 查询所有用户
     * @author: RainGrd
     * @date: 2022/5/16 17:15:57
     * @param: null
     * @return: null
     **/
    @Override
    public List<Object> selectTeacherList() {
        /*创建SqlSession工厂*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取TeacherMapper*/
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        /*调用方法*/
        return mapper.selectTeacherAll();
    }
}
