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
import com.yscf.core.model.financial.BizWithdraw;
/**
 * 
 * Description：<br> 
 * @author  Allen
 * @date    2015年9月17日
 * @version v1.0.0
 */
@MapperScan("bizWithdrawMapper")
public interface BizWithdrawMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizWithdraw record);

    int insertSelective(BizWithdraw record);
    
    /**
	 * Description：批量删除日志列表
	 * @author  Allen
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param userName 系统日志
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int deleteBtach(List<?> list);

    BizWithdraw selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizWithdraw record);

    int updateByPrimaryKey(BizWithdraw record);
    
    /**
     * Description：根据对象的参数值有选择作为条件查询 记录条数
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param BizWithdraw 系统体现
     * @return int 记录条数
     */
    int selectSelectiveCount(BizWithdraw bizWithdraw);
    /**
	 * Description：查询提现管理（通用）,条件查询,带分页功能的
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param BizWithdraw 系统体现
	 * @return PageList<BizWithdraw>
	 * @throws FrameworkException
	 */
	 PageList<BizWithdraw> selectSelectivePage(@Param("parasMap")HashMap<String,Object> parasMap ,PageInfo info);
	 
	 /**
	  * Description：根据查询条件查询 统计提现金额  总金额 
	  * @author  JingYu.Dai
	  * @date    2015年10月20日
	  * @version v1.0.0
	  * @param bizRechargeOffline
	  * @return BigDecimal 总金额
	  */
	 Map<String,BigDecimal> selectSumAmountSelective(Map<String ,Object> map);
	 /**
	  * 查询确认转账列表，带分页功能的
	  * Description：<br> 
	  * @author  Allen
	  * @date    2015年9月23日
	  * @version v1.0.0
	  * @param parasMap
	  * @param info
	  * @return
	  */
	 //PageList<BizWithdraw> selectTransferServiceAllPage(@Param("parasMap")HashMap<String,Object> parasMap ,PageInfo info);
	 
	 /**
	  * Description：根据查询条件查询 统计提现金额  总金额 
	  * @author  JingYu.Dai
	  * @date    2015年10月20日
	  * @version v1.0.0
	  * @param bizRechargeOffline
	  * @return BigDecimal 总金额
	  */
	 //Map<String,BigDecimal> selectAlreadyTransferSumAmountSelective(Map<String ,Object> map);
	 /**
	  * 查询确认已转账列表，带分页功能的
	  * Description：<br> 
	  * @author  Allen
	  * @date    2015年9月23日
	  * @version v1.0.0
	  * @param parasMap
	  * @param info
	  * @return
	  */
	 //PageList<BizWithdraw> selectAlreadyTransferServiceAllPage(@Param("parasMap")HashMap<String,Object> parasMap ,PageInfo info);
	 
    /**
	 * Description：查询体现列表
	 * @author  Allen
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param BizWithdraw BizWithdraw 系统体现
	 * @return List<BizWithdraw>
	 * @throws FrameworkException
	 */
	List<BizWithdraw> selectAll(BizWithdraw bizWithdraw);
	
	/**
	 * 批量审核体现申请
	 * Description：<br> 
	 * @author  Allen
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	int batchAudit(@Param("map") Map<String,Object> map);
	int batchAuditTransferService(@Param("map") Map<String,Object> map);
	
	/**
	 * Description：查询总记录条数  根据审核状态audit_status
	 * @author  JingYu.Dai
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param auditStatus 审核状态
	 * @return int
	 */
	int getTotalCountByAuditStatus(String auditStatus);

	/**
	 * 
	 * Description：<br> 
	 * 导出提现管理excel
	 * @author  JunJie.Liu
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param bizWithdrawVO
	 * @param eom
	 * @return
	 */
	List<BizWithdraw> selectBizWithdrawVOEom(HashMap<String, Object> map);
	
}