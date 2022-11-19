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
import com.yscf.core.model.financial.BizRechargeOffline;

/**
 * Description：线下充值数据层接口
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@MapperScan("bizRechargeOfflineMapper")
public interface BizRechargeOfflineMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizRechargeOffline record);

    int insertSelective(BizRechargeOffline record);

    BizRechargeOffline selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizRechargeOffline record);

    int updateByPrimaryKey(BizRechargeOffline record);
    
    /**
     * Description：查询总记录条数  条件充值状态
     * @author  JingYu.Dai
     * @date    2015年10月9日
     * @version v1.0.0
     * @param recStatus 充值状态
     * @return	int
     */
    int getTotalCountByRecStatus(String recStatus);
    
    /**
     * Description：查询线下充值列表数据  分页
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param record  线下充值类 对象（BizRechargeOffline）
     * @param info 分页 对象 （PageInfo）
     * @return PageList<BizRechargeOffline> 
     */
    PageList<BizRechargeOffline> selectRechargeOfflinePage(@Param("map")Map<String,String> map , PageInfo info);
    
    /**
     * Description：根据查询条件查询 总金额
     * @author  JingYu.Dai
     * @date    2015年10月10日
     * @version v1.0.0
     * @param bizRechargeOffline
     * @return BigDecimal 总金额
     */
    BigDecimal selectSumAmountSelective(Map<String ,String> map);

	/**
	 * 
	 * Description：<br> 
	 * 导出线下充值客户列表
	 * @author  JunJie.Liu
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param bizRechargeOffline
	 * @param eom
	 * @return
	 */
	List<BizRechargeOffline> selectBizRechargeOfflineEom(
			HashMap<String, Object> maps);
    
}