package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.itheima.service.impl
 * @Author: RainGrd
 * @CreateTime: 2022-05-17  20:39
 * @Description: TODO
 * @Version: 1.0
 */
public class BrandServiceImpl implements BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    /**
     * @description: 添加数据
     * @author: RainGrd
     * @date: 2022/5/18 7:55:56
     * @param: null
     * @return: null
     **/
    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteAll(int[] idlist) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteAll(idlist);
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * @description: 分页查询
     * @author: RainGrd
     * @date: 2022/5/18 16:44:25
     * @param: null
     * @return: null
     **/
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        List<Brand> brands = mapper.selectByPage(begin, size);
        int totalCount = mapper.selectTotalCount();
        PageBean pageBean = new PageBean();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        /*处理brand条件*/
        String brandName = brand.getBrandName();
        String companyName = brand.getCompanyName();
        if(brandName != null && brandName.length() > 0){
            brand.setBrandName("%"+brandName+"%");
        }
        if(companyName != null && companyName.length() > 0){
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> brands = mapper.selectPageAndCondition(begin, size,brand);
        int totalCount = mapper.selectTotalCountByCondition(brand);
        System.out.println(totalCount);
        PageBean pageBean = new PageBean();
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);
        sqlSession.close();
        return pageBean;
    }

    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void delete(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.delete(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
