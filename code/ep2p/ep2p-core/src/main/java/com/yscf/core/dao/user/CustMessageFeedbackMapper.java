package com.yscf.core.dao.user;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.user.CustMessageFeedback;
/**
 * 
 * @ClassName : CustInterestTicketMapper
 * @Description : 意见反馈
 * @Author : heng.wang
 * @Date : 2016年1月8日 上午11:26:32
 */
public interface CustMessageFeedbackMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustMessageFeedback record);

    int insertSelective(CustMessageFeedback record);

    int updateByPrimaryKeySelective(CustMessageFeedback record);

    int updateByPrimaryKey(CustMessageFeedback record);
}