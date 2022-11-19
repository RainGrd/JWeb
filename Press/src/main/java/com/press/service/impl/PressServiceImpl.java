package com.press.service.impl;

import com.press.Vo.PressVO;
import com.press.entity.Press;
import com.press.mapper.PressMapper;
import com.press.service.IPressService;
import com.press.tools.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.press.service.impl
 * @Author: RainGrd
 * @CreateTime: 2022-05-20  13:05
 * @Description: TODO
 * @Version: 1.0
 */
public class PressServiceImpl implements IPressService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public PressVO<Press> selectPressVoCondition(int currentPage, int pageSize, Press press) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        String pressPerson = press.getUsername();
        String pressHead = press.getPressHead();
        String pressCreateDate = press.getPressCreateDate();
        /*处理条件*/
        if (pressHead != null && pressHead.length() > 0) {
            press.setPressHead("%" + pressHead + "%");
        }
        if (pressPerson != null && pressPerson.length() > 0) {
            press.setUsername("%" + pressPerson + "%");
        }
        if (pressCreateDate != null && pressCreateDate.length() > 0) {
            press.setPressCreateDate("%" + pressCreateDate + "%");
        }
        List<Press> presses = mapper.selectByPressAll(begin, size, press);
        int totalCount = mapper.selectByTotalCount(press);
        PressVO pressVO = new PressVO();
        pressVO.setCount(totalCount);
        pressVO.setMsg("success");
        pressVO.setCode(0);
        pressVO.setData(presses);
        sqlSession.close();
        return pressVO;
    }

    @Override
    public PressVO<Press> selectPressAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        /*处理条件*/
        List<Press> presses = mapper.selectPressAll();
        System.out.println(presses);
        PressVO pressVO = new PressVO();
        pressVO.setCount(presses.size());
        pressVO.setMsg("success");
        pressVO.setCode(0);
        pressVO.setData(presses);
        sqlSession.close();
        return pressVO;
    }

    @Override
    public PressVO<Press> selectInitPressAll(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Press> presses = mapper.selectInitPressAll(begin, size);
//        int totalCount = mapper.selectInitTotalCount();
//        System.out.println(totalCount);
        PressVO pressVO = new PressVO();
        pressVO.setCount(11);
        pressVO.setMsg("success");
        pressVO.setCode(0);
        pressVO.setData(presses);
        sqlSession.close();
        return pressVO;
    }

    @Override
    public void deletePressAll(int[] idList) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        mapper.deleteAll(idList);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void updatePress(Press press) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        /*修改*/
        mapper.update(press);
        /*提交事务*/
        sqlSession.commit();
        /*关闭工厂*/
        sqlSession.close();
    }
    /**
     * @description: 删除新闻
     * @author: RainGrd
     * @date: 2022/5/22 19:22:57
     * @param: null
     * @return: null
     **/
    @Override
    public void deletePress(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        /*删除*/
        mapper.deletePress(id);
        /*提交事务*/
        sqlSession.commit();
        /*关闭工厂*/
        sqlSession.close();
    }
    /**
     * @description: 发布新闻
     * @author: RainGrd
     * @date: 2022/5/22 19:22:51
     * @param: null
     * @return: null
     **/
    @Override
    public void addPress(Press press) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        mapper.addPress(press);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public Press selectPressById(int pressId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        PressMapper mapper = sqlSession.getMapper(PressMapper.class);
        return  mapper.selectByPressId(pressId);
    }
}
