package com.yscf.core.dao.user;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustDueinWater;

public interface CustDueinWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustDueinWater record);

    int insertSelective(CustDueinWater record);

    CustDueinWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustDueinWater record);

    int updateByPrimaryKey(CustDueinWater record);
    /**
   	 * Description：客户待收总额明细查询
   	 * @author  heng.wang
   	 * @date    2015年10月15日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustDueinWater> selectCusTotalStayDetailsById(@Param("custDueinWater") CustDueinWater custDueinWater, PageInfo info);
    
    /**
   	 * Description：客户待收利息明细查询
   	 * @author  heng.wang
   	 * @date    2015年10月15日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CustDueinWater> selectCollectInterestDetailsById(@Param("custDueinWater") CustDueinWater custDueinWater, PageInfo info);
    /**
	 * Description：客户积分列表条件查询
	 * @author  heng.wang
	 * @date    2015年10月08日
	 * @version v1.0.0
	 * @param userName 系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
    public PageList<CustDueinWater> selectAllPage(@Param("custDueinWater") CustDueinWater custDueinWater, PageInfo info);
    /**
   	 * Description：统计总共待收
   	 * @author  heng.wang
   	 * @date    2015年10月26日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    Map<String,BigDecimal> selectAllPageSum(@Param("custDueinWater") CustDueinWater custDueinWater);
}