package com.yscf.core.dao.user;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustTransferStatusWater;

public interface CustTransferStatusWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustTransferStatusWater record);

    int insertSelective(CustTransferStatusWater record);

    CustTransferStatusWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustTransferStatusWater record);

    int updateByPrimaryKey(CustTransferStatusWater record);
    /**
   	 * Description：根据客户pid来查客户债权转让状态历史详情
   	 * @author  heng.wang
   	 * @date    2015年9月24日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustTransferStatusWater> selectCusCredRightStatusHistoryById(@Param("custTransferStatusWater")CustTransferStatusWater custTransferStatusWater, PageInfo info);
    
}