package com.yscf.core.dao.financial;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.CustomerAccountModel;

/**
 * Description：<br> 
 * 客户账户 数据访问层
 * @author  JingYu.Dai
 * @date    2015年12月26日
 * @version v1.0.0
 */
@MapperScan("customerAccountMapper")
public interface CustomerAccountMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(CustomerAccount record);

    int insertSelective(CustomerAccount record);

    CustomerAccount selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(CustomerAccount record);

    int updateByPrimaryKey(CustomerAccount record);
    public int deleteBtach(List<?> list);
    List<CustomerAccount> selectAll(CustomerAccount customerAccount);
    /**
     * 查询客户账户列表,带分页功能的
     * Description：<br> 
     * TODO
     * @author  Allen
     * @date    2015年9月30日
     * @version v1.0.0
     * @param parasMap
     * @param info
     * @return
     */
    PageList<CustomerAccount> selectAllPage(@Param("parasMap")HashMap<String,Object> parasMap ,PageInfo info);
    
    
    /**
	  * Description：根据查询条件查询 统计查询客户账户列表  总金额 
	  * @author  JingYu.Dai
	  * @date    2015年10月20日
	  * @version v1.0.0
	  * @param bizRechargeOffline
	  * @return Map<String,BigDecimal>
	  */
	 Map<String,BigDecimal> selectSumAmountSelective(Map<String ,Object> map);

	  /**
	   * 
	   * Description：<br> 
	   * 查询客户账户列表,带分页功能的
	   * @author  JunJie.Liu
	   * @date    2015年11月2日
	   * @version v1.0.0
	   * @param customerAccountVO
	   * @param info
	   * @return
	   */
	PageList<CustomerAccount> selectAllPage(
			@Param("parasMap")	CustomerAccountModel customerAccountVO, PageInfo info);

	 /**
	  * 
	  * Description：<br> 
	  * 根据查询条件查询 统计查询客户账户列表  总金额 
	  * @author  JunJie.Liu
	  * @date    2015年11月2日
	  * @version v1.0.0
	  * @param customerAccountVO
	  * @return
	  */
	Map<String, BigDecimal> selectSumAmountSelectiveByVo(
			@Param("parasMap")	CustomerAccountModel customerAccountVO);
	
	/**
	 * 
	 * Description：<br> 
	 * 导出客户账户列表
	 * @author  JunJie.Liu
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	public List<CustomerAccount> selectCustomerAccountEom(HashMap<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据用户id，查询其账户
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public CustomerAccount getByCusterId(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量修改客户账户可用余额	 
	 * @author  Jie.Zou
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	int updateCustAccountByUsersId(Map<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量修改客户账户可用体验金余额	 
	 * @author  Jie.Zou
	 * @date    2016年1月12日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	int updateCustExperienceAmountByUsersId(Map<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量修改账户可用余额根据（map）
	 * @author  Jie.Zou
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param maps
	 * @return
	 */
	int updateCustAccountByMaps(@Param("maps")Map<String, Object> maps);
}