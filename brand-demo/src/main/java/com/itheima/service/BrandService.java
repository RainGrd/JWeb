package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RainGrd
 */
public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Brand> selectAll() {
        //调用BrandMapper.selectAll()

        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        List<Brand> brands = mapper.selectAll();

        sqlSession.close();

        return brands;
    }

    /**
     * 添加
     *
     * @param brand
     */
    public void addBrand(Brand brand) {

        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        mapper.add(brand);

        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }


    /**
     * 根据条件查询
     *
     * @return
     */
    public List<Brand> selectById(int id) {
        //调用BrandMapper.selectAll()

        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        Brand brand = mapper.selectById(id);
        List<Brand> brands=new ArrayList<>();
        brands.add(brand);
        sqlSession.close();

        return brands;
    }


    /**
     * 修改
     *
     * @param brand
     */
    public void update(Brand brand) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        mapper.update(brand);

        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    /**
     * @description: 根据id修改状态
     * @author: RainGrd
     * @date: 2022/5/12 15:09:13
     * @param: null
     * @return: null
     **/
    public void updateStatus(int id) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法
        mapper.delete(id);

        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    /**
     * @description: 根据品牌列表查询
     * @author: RainGrd
     * @date: 2022/5/12 8:56:30
     * @param: null
     * @return: null
     **/
    public List<Brand> selectBrandName(String brandName) {
        //2. 获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //3. 获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectByBrandName(brandName);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        return brands;
    }
}
