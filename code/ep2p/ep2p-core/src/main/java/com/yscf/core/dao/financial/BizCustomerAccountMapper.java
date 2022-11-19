package com.yscf.core.dao.financial;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizCustomerAccount;

/**
 * 
 * Description：<br> 
 * 客户账户信息数据交互类
 * @author  Yu.Zhang
 * @date    2015年12月5日
 * @version v1.0.0
 */
@MapperScan("bizCustomerAccountMapper")
public interface BizCustomerAccountMapper  extends IBaseDao<BaseEntity, String>  {
	
    int deleteByPrimaryKey(String pid);

    int insert(BizCustomerAccount record);

    int insertSelective(BizCustomerAccount record);

    BizCustomerAccount selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizCustomerAccount record);

    int updateByPrimaryKey(BizCustomerAccount record);
}