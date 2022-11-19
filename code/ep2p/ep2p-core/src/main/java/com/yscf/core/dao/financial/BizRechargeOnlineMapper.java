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
import com.yscf.core.model.financial.BizRechargeOnline;

/**
 * Description：线上充值数据层接口 
 * @author  JingYu.Dai
 * @date    2015年9月29日
 * @version v1.0.0
 */
@MapperScan("bizRechargeOnlineMapper")
public interface BizRechargeOnlineMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizRechargeOnline record);

    int insertSelective(BizRechargeOnline record);

    BizRechargeOnline selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizRechargeOnline record);

    int updateByPrimaryKey(BizRechargeOnline record);
    
    /**
     * Description：查询线上充值列表数据  分页
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param record  线下充值类 对象（BizRechargeOffline）
     * @param info 分页 对象 （PageInfo）
     * @return PageList<BizRechargeOffline> 
     */
    PageList<BizRechargeOnline> selectOnlineRechargePage(@Param("map")Map<String,String> map , PageInfo info);
    
    /**
     * Description：根据查询条件查询 总金额
     * @author  JingYu.Dai
     * @date    2015年10月10日
     * @version v1.0.0
     * @param bizRechargeOffline
     * @return BigDecimal 总金额
     */
    BigDecimal selectSumAmountSelective(Map<String,String> map);
    
    /**
     * 
     * Description：<br> 
     * 线上充值客户列表导出
     * @author  JunJie.Liu
     * @date    2015年10月30日
     * @version v1.0.0
     * @param maps
     * @return
     */
	List<BizRechargeOnline> selectBizRechargeOnlineEom(
			HashMap<String, Object> maps);
	
	/**
     * 
     * Description：<br> 
     * 通过流水号得到充值记录
     * @author  Jie.Zou
     * @date    2015年11月23日
     * @version v1.0.0
     * @param serialNumber
     * @return
     */
    BizRechargeOnline getRechargeByRecOrderNo(Integer recOrderNo);
    
    /**
     * 
     * Description：<br> 
     * 根据充值状态得到充值数据集合，按照充值时间排序
     * @author  Jie.Zou
     * @date    2015年12月25日
     * @version v1.0.0
     * @param rechargeStatus
     * @return
     */
    List<BizRechargeOnline> selectBizRechargesByStatus(String rechargeStatus);
}