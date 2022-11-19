package com.bdqn.service.impl;

import com.bdqn.Vo.BrandVo;
import com.bdqn.dao.BrandDao;
import com.bdqn.dao.impl.BrandDaoImpl;
import com.bdqn.entity.Brand;
import com.bdqn.service.BrandService;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.service.impl
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  11:14
 * @Description: TODO
 * @Version: 1.0
 */
public class BrandServiceImpl implements BrandService {
    BrandDao brandDao=new BrandDaoImpl();
    @Override
    public BrandVo<Brand> selectBrandAll(int currentPage, int pageSize,Brand brand) {
        /*SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();*/
        int begin=(currentPage-1)*pageSize;
        int size=pageSize;
        /*BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Integer totalCount = mapper.selectBrandTotalCount();*/
        BrandVo<Brand> brandBrandVo = new BrandVo<>();
        brandBrandVo.setData(brandDao.selectBrandAll(begin,size,brand));
        brandBrandVo.setTotalCount(brandDao.selectTotalCount(brand));
        return brandBrandVo;
    }

    @Override
    public boolean deleteBrand(int id) {
        boolean delete = brandDao.delete(id);
        return delete;
    }
}
