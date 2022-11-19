package com.yscf.core.dao.business;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizFinanceProducts;

/**
 * 
 * Description：<br>
 * 理财产品列表 Mapper
 * 
 * @author fengshiliang
 * @date 2015年10月19日
 * @version v1.0.0
 */
@MapperScan("bizFinanceProductsMapper")
public interface BizFinanceProductsMapper extends IBaseDao<BaseEntity, String> {
	public int insert(BizFinanceProducts record);

	public int insertSelective(BizFinanceProducts record);

	public BizFinanceProducts selectByPrimaryKey(String pid);

	public BizFinanceProducts getBizFinanceProductsByBorrowId(String borrowId);

	public int updateByPrimaryKeySelective(BizFinanceProducts record);
}