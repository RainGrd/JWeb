package com.yscf.core.dao.financial;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizAccountRecharge;

/**
 * Description：<br>
 * 资金充值数据访问层接口
 * @author  JingYu.Dai
 * @date    2015年9月28日
 * @version v1.0.0
 */
@MapperScan("bizAccountRechargeMapper")
public interface BizAccountRechargeMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizAccountRecharge record);

    int insertSelective(BizAccountRecharge record);

    BizAccountRecharge selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizAccountRecharge record);

    int updateByPrimaryKey(BizAccountRecharge record);
    
    /**
     * Description：根据客户ID查询客户可用余额资金
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param clientId
     * @return String 可用余额资金
     */
    String getAvailableAmountByClientId(String clientId);
    
    /**
     * Description：根据客户ID修改客户资金充值数据
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param recharge
     * @return int 受影响的行数
     */
    int updateByCustomerId(BizAccountRecharge recharge);
    
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
    BizAccountRecharge getRechargeBySerialNumber(Integer serialNumber);
    
    /**
     * 
     * Description：<br> 
     * 通过客户ID得到充值资金
     * @author  Jie.Zou
     * @date    2015年12月16日
     * @version v1.0.0
     * @param customerId
     * @return
     */
    BizAccountRecharge selectAccountRechargeByCustomerId(String customerId);
}