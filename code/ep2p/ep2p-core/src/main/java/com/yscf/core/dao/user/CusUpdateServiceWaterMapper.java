package com.yscf.core.dao.user;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CusUpdateServiceWater;
/**
 * Description：更换客服数据访问层接口
 * @author  heng.wang
 * @date    2015年9月17日
 * @version v1.0.0
 */
@MapperScan("cusUpdateServiceWaterMapper")
public interface CusUpdateServiceWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CusUpdateServiceWater record);

    int insertSelective(CusUpdateServiceWater record);

    CusUpdateServiceWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CusUpdateServiceWater record);

    int updateByPrimaryKey(CusUpdateServiceWater record);
    
    /**
   	 * Description：根据pid来插入客服历史详细
   	 * @author  heng.wang
   	 * @date    2015年9月17日
   	 * @version v1.0.0
   	 * @param userName 系统客服
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public int insertCustWater(@Param("map")Map<String, Object> map);
    /**
   	 * Description：根据客户pid来查客户历史详情
   	 * @author  heng.wang
   	 * @date    2015年9月21日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
    public PageList<CusUpdateServiceWater> cusHistoryDetailedById(@Param("cusUpdateServiceWater")CusUpdateServiceWater cusUpdateServiceWater, PageInfo info);
}