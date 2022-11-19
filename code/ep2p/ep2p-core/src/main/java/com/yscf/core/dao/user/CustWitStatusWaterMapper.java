package com.yscf.core.dao.user;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustWitStatusWater;

public interface CustWitStatusWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustWitStatusWater record);

    int insertSelective(CustWitStatusWater record);

    CustWitStatusWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustWitStatusWater record);

    int updateByPrimaryKey(CustWitStatusWater record);
    /**
   	 * Description：根据客户pid来查客户提现状态历史详情
   	 * @author  heng.wang
   	 * @date    2015年9月24日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustWitStatusWater> selectCusStatusChangeHistoryById(@Param("custWitStatusWater")CustWitStatusWater custWitStatusWater, PageInfo info);
}