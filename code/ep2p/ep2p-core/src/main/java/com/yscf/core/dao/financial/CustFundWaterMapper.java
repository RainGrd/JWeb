package com.yscf.core.dao.financial;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.financial.CustFundWater;

/**
 * Description：<br> 
 * 客户资金流水  数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年1月4日
 * @version v1.0.0
 */
@MapperScan("custFundWaterMapper")
public interface CustFundWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustFundWater record);

    int insertSelective(CustFundWater record);

    CustFundWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustFundWater record);

    int updateByPrimaryKey(CustFundWater record);
    
    List<CustFundWater> selectAll(CustFundWater custFundWater);
    /**
     * 按照条件查询列表数据待分页
     * Description：<br> 
     * TODO
     * @author  Allen
     * @date    2015年10月10日
     * @version v1.0.0
     * @param parasMap
     * @param info
     * @return
     */
    PageList<CustFundWater> selectAllPage(@Param("parasMap")HashMap<String,Object> parasMap ,PageInfo info);
    /**
     * 按照条件统计所有总计数据带分页
     * Description：<br> 
     * TODO
     * @author  Allen
     * @date    2015年10月10日
     * @version v1.0.0
     * @param parasMap
     * @param info
     * @return
     */
    List<CustFundWater> getTotalData(@Param("parasMap")HashMap<String,Object> parasMap);
    /**
   	 * Description：根据pid查询客户账户总额明细,带分页功能的
   	 * @author  heng.wang
   	 * @date    2015年10月9日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
   	public PageList<CustFundWater> selectAccountTotalDetailsById(@Param("custFundWater") CustFundWater custFundWater, PageInfo info);
       /**
   	 * Description：客户账户总额流水列表条件查询
   	 * @author  heng.wang
   	 * @date    2015年10月12日
   	 * @version v1.0.0
   	 * @param userName 系统客户
   	 * @return boolean
   	 * @throws FrameworkException
   	 */
       public PageList<CustFundWater> selectAllPages(@Param("custFundWater") CustFundWater custFundWater, PageInfo info);
       
       /**
      	 * Description：根据pid查询客户投资总额明细,带分页功能的
      	 * @author  heng.wang
      	 * @date    2015年10月9日
      	 * @version v1.0.0
      	 * @param userName 系统客户
      	 * @return boolean
      	 * @throws FrameworkException
      	 */
      	public PageList<CustFundWater> selectTouziDetailsById(@Param("custFundWater") CustFundWater custFundWater, PageInfo info);
        /**
      	 * Description：查资金流水类型带分页功能的
      	 * @author  heng.wang
      	 * @date    2015年10月21日
      	 * @version v1.0.0
      	 * @param userName 系统客户
      	 * @return boolean
      	 * @throws FrameworkException
      	 */
      	public PageList<CustFundWater> selectWaterType(@Param("custFundWater") CustFundWater custFundWater,PageInfo info);
      	 /**
      	 * Description：查资金流水详细带分页功能的
      	 * @author  heng.wang
      	 * @date    2015年10月21日
      	 * @version v1.0.0
      	 * @param userName 系统客户
      	 * @return boolean
      	 * @throws FrameworkException
      	 */
      	public PageList<CustFundWater> selectZiJinWater(@Param("custFundWater") CustFundWater custFundWater, PageInfo info);
      	 /**
      	 * Description：根据条件查资金流水详细带分页功能的
      	 * @author  heng.wang
      	 * @date    2015年10月21日
      	 * @version v1.0.0
      	 * @param userName 系统客户
      	 * @return boolean
      	 * @throws FrameworkException
      	 */
      	public PageList<CustFundWater> selectZiJinWaterAllPage(@Param("custFundWater")CustFundWater custFundWater, PageInfo info,@Param("statusArr") String[] statusArr);
      	
      	/**
      	 * 统计资金流水
      	 * @param custFundWater
      	 * @param statusArr
      	 * @return
      	 */
      	public Map<String,BigDecimal> selectZiJinWaterAllSum(@Param("custFundWater")CustFundWater custFundWater,@Param("statusArr") String[] statusArr);
      	/**
      	 * 统计投标奖励
      	 * @param custFundWater
      	 * @return
      	 */
      	public Map<String,BigDecimal> selectAllPagesSum(@Param("custFundWater")CustFundWater custFundWater);
    	/**
      	 * 统计资金流水
      	 * @param custFundWater
      	 * @return
      	 */
      	public Map<String,BigDecimal> selectZiJinWaterSum(@Param("custFundWater")CustFundWater custFundWater);
      	
      	/**
      	 * 
      	 * Description：<br> 
      	 * 导出客户账户明细
      	 * @author  JunJie.Liu
      	 * @date    2015年11月3日
      	 * @version v1.0.0
      	 * @param map
      	 * @return
      	 */
		List<CustFundWater> selectCustFundWaterEom(HashMap<String, Object> map);
		/**
      	 * 
      	 * Description：<br> 
      	 * 前台：查询流水
      	 * @author  heng.wang
      	 * @date    2015年11月3日
      	 * @version v1.0.0
      	 * @param map
      	 * @return
      	 */
		public List<CustFundWater> selectWater(@Param("map")Map<String, Object> map); 
		
		/**
      	 * 
      	 * Description：<br> 
      	 * api：查询流水
      	 * @author  heng.wang
      	 * @date    2015年12月30日
      	 * @version v1.0.0
      	 * @param userId
      	 * @return
      	 */
		public List<CustFundWater> selectCapitalFlow(String userId);
		
		/**
      	 * 
      	 * Description：<br> 
      	 * 统计资金流水
      	 * @author  heng.wang
      	 * @date    2016年1月6日
      	 * @version v1.0.0
      	 * @param userId
      	 * @return
      	 */
		Map<String, Object> sumZiJinWater(HashMap<String, Object> map);
		

		/**
		 * 
		 * Description：<br> 
		 * 批量新增资金流水
		 * @author  Jie.Zou
		 * @date    2016年1月11日
		 * @version v1.0.0
		 * @param map
		 * @return
		 */
		int addFundWaters(Map<String, Object> custFWs);
}