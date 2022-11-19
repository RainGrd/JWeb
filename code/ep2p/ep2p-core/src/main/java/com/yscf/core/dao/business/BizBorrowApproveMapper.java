package com.yscf.core.dao.business;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizBorrowApprove;

@MapperScan("bizBorrowApproveMapper")
public interface BizBorrowApproveMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(String pid);

    int insert(BizBorrowApprove record);

    int insertSelective(BizBorrowApprove record);

    BizBorrowApprove selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizBorrowApprove record);

    int updateByPrimaryKey(BizBorrowApprove record);

	List<BizBorrowApprove> selectAll(BizBorrowApprove bizBorrowApprove);
}