package com.yscf.core.dao.user;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustStatusWater;

public interface CustStatusWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustStatusWater record);

    int insertSelective(CustStatusWater record);

    CustStatusWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustStatusWater record);

    int updateByPrimaryKey(CustStatusWater record);
    /**
   	 * Description：根据客户pid来查客户状态历史详情
   	 * @author  heng.wang
   	 * @date    2015年9月24日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustStatusWater> cusStatusChangeHistoryById(@Param("custStatusWater")CustStatusWater custStatusWater, PageInfo info);
}