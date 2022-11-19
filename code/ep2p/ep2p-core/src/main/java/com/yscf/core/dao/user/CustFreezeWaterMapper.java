package com.yscf.core.dao.user;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustFreezeWater;

@MapperScan("custFreezeWaterMapper")
public interface CustFreezeWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);
	int insert(CustFreezeWater record);
	int insertSelective(CustFreezeWater record);
	CustFreezeWater selectByPrimaryKey(String pid);
	int updateByPrimaryKeySelective(CustFreezeWater record);
	int updateByPrimaryKey(CustFreezeWater record);
	/**
   	 * Description：根据pid查询客户冻结金额明细,带分页功能的
   	 * @author  heng.wang
   	 * @date    2015年10月9日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
   	public PageList<CustFreezeWater> selectFreezeAmountDetailsById(@Param("custFreezeWater") CustFreezeWater custFreezeWater, PageInfo info);
       /**
   	 * Description：客户积分列表条件查询
   	 * @author  heng.wang
   	 * @date    2015年10月08日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
       public PageList<CustFreezeWater> selectAllPage(@Param("custFreezeWater") CustFreezeWater custFreezeWater, PageInfo info);
       /**
      	 * Description：统计冻结金额
      	 * @author  heng.wang
      	 * @date    2015年10月26日
      	 * @version v1.0.0
      	 * @param userName 系统客户
      	 * @return boolean
      	 * @throws FrameworkException
      	 */
       Map<String,BigDecimal> selectAllPageSum(@Param("custFreezeWater") CustFreezeWater custFreezeWater);
}