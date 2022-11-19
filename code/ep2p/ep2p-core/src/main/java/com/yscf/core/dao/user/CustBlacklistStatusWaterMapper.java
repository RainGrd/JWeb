package com.yscf.core.dao.user;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustBlacklistStatusWater;

public interface CustBlacklistStatusWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustBlacklistStatusWater record);

    int insertSelective(CustBlacklistStatusWater record);

    CustBlacklistStatusWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustBlacklistStatusWater record);

    int updateByPrimaryKey(CustBlacklistStatusWater record);
    /**
   	 * Description：根据客户pid来查黑名单客户状态历史详情
   	 * @author  heng.wang
   	 * @date    2015年9月24日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustBlacklistStatusWater> selectBlicklistCusStatusHistoryById(@Param("custBlacklistStatusWater")CustBlacklistStatusWater custBlacklistStatusWater, PageInfo info);
}