package com.yscf.core.dao.financial.recharge;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.recharge.PayConfig;

/**
 * 
 * Description：<br> 
 * 支付接口的数据访问层
 * @author  Jie.Zou
 * @date    2015年11月18日
 * @version v1.0.0
 */
@MapperScan("payConfigMapper")
public interface PayConfigMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(PayConfig record);

    int insertSelective(PayConfig record);

    PayConfig selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(PayConfig record);

    int updateByPrimaryKeyWithBLOBs(PayConfig record);

    int updateByPrimaryKey(PayConfig record);
    
    /**
     * 
     * Description：<br> 
     * 查询所有有效的支付接口
     * @author  Jie.Zou
     * @date    2015年11月18日
     * @version v1.0.0
     * @param config
     * @return
     */
    List<PayConfig> selectAll();
}