package com.yscf.core.dao.business;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizBorrowHis;

/**
 * 
 * Description：<br> 
 * 借款历史表数据交互类
 * @author  Yu.Zhang
 * @date    2015年9月21日
 * @version v1.0.0
 */
@MapperScan("bizBorrowHisMapper")
public interface BizBorrowHisMapper extends IBaseDao<BaseEntity, String> {
    int insert(BizBorrowHis record);

    int insertSelective(BizBorrowHis record);
}