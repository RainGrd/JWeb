package com.bdqn.dao.impl;

import com.bdqn.dao.BrandDao;
import com.bdqn.entity.Brand;
import com.bdqn.utils.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.dao.impl
 * @Author: RainGrd
 * @CreateTime: 2022-05-28  15:11
 * @Description: TODO
 * @Version: 1.0
 */
public class BrandDaoImpl implements BrandDao {
    StringBuilder sql = new StringBuilder();
    @Override
    public List<Brand> selectBrandAll(int limit, int pageSize, Brand brand) {
        List<Brand> brandList =null;
        List<Object> list=new ArrayList<>();
        sql.append("select * from items.tb_brand where ");
        /*进行SQL语句处理*/
        if (pageSize != 0) {
            sql.append(" del_status=1 ");
        }
        if (brand.getBrandName() != null && !"".equals(brand.getBrandName())) {
            sql.append(" and brand_name like CONCAT('%',?,'%'");
            list.add(brand.getBrandName());
        }
        if (brand.getCompanyName() != null &&  !"".equals(brand.getCompanyName())) {
            sql.append(" and company_name like CONCAT('%',?,'%')");
            list.add(brand.getCompanyName());

        }
        if (brand.getStatus() != null) {
            sql.append(" and status=? ");
            list.add(brand.getStatus());
        }
        brandList=DBUtil.queryList(Brand.class, sql.toString(),list);
        /*sql = "select brand_name brandName,company_name " +
                "companyName,description " +
                "description,ordered" +
                " ordered,status " +
                "status,id id from items.tb_brand where " + str + "  limit " + limit + "," + pageSize;*/

/*        Brand brand1 = DBUtil.queryOne(Brand.class, sql.toString());
        System.out.println(brand1);*/
//        System.out.println(maps);
//        List<Map<String, Object>> maps = Util.executeQuery(sql);
        /*System.out.println(maps.size()== 0);
        if (maps == null || maps.size() == 0) {
            *//*重新调用自己*//*
            brand.setBrandName("");
            brand.setCompanyName("");
            brand.setStatus(null);
            return selectBrandAll(limit,pageSize,brand);
        }*/
        /*遍历数据*/
        /*Iterator<Map<String, Object>> iterator = maps.iterator();
        List<Brand> brandList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Brand _brand = new Brand();
            _brand.setBrandName((String) map.get("brandName"));
            _brand.setCompanyName((String) map.get("companyName"));
            _brand.setDescription((String) map.get("description"));
            _brand.setOrdered((Integer) map.get("ordered"));
            _brand.setStatus((Integer) map.get("status"));
            _brand.setId((Integer) map.get("id"));
            brandList.add(_brand);
        }*/
        return brandList;
    }

    @Override
    public Integer selectTotalCount(Brand brand) {
        /*String str = " del_status=1";
        if (brand.getBrandName() != null && brand.getBrandName() != "") {
            brand.setBrandName("%" + brand.getBrandName() + "%");
            str += " and brand_name like '" + brand.getBrandName() + "'";
        }
        if (brand.getCompanyName() != null && brand.getCompanyName() != "") {
            brand.setCompanyName("%" + brand.getCompanyName() + "%");
            str += " and company_name like '" + brand.getCompanyName() + "'";
        }
        if (brand.getStatus() != null) {
            str += " and status= " + "'" + brand.getStatus() + "'";
        }
        if (str != null && str != "") {
            str += " and del_status=1";
        }
        sql = "select count(*) from items.tb_brand where " + str + " ";
        System.out.println(sql);
        List<Map<String, Object>> maps = Util.executeQuery(sql);
        Number totalCount = null;
        for (Map<String, Object> map : maps) {
            totalCount = (Number) map.get("count(*)");
        }
        return Integer.parseInt(String.valueOf(totalCount));*/
        return null;
    }

    @Override
    public boolean delete(int id) {
       /* sql = "update items.tb_brand set del_status=0 where id=" + id;
        boolean flag = Util.executeUpdate(sql);
        return flag;*/
        return false;
    }
}
