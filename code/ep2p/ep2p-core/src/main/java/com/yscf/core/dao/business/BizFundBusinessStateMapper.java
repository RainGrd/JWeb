package com.yscf.core.dao.business;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizFundBusinessState;

/**
 * 
 * Description：<br> 
 * 资金交易状况表
 * @author  Yu.Zhang
 * @date    2015年10月13日
 * @version v1.0.0
 */
@MapperScan("bizFundBusinessStateMapper")
public interface BizFundBusinessStateMapper extends IBaseDao<BaseEntity, String> {
    int insert(BizFundBusinessState record);

    int insertSelective(BizFundBusinessState record);
    
    public List< BizFundBusinessState > selectAll(BizFundBusinessState bizFundBusinessState);

	void syncFundBusinessState(BizFundBusinessState bizFundBusinessState); 
}