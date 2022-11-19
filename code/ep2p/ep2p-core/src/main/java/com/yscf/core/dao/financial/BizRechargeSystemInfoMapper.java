package com.yscf.core.dao.financial;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizRechargeSystemInfo;

/**
 * Description：充值渠道数据层接口
 * @author  JingYu.Dai
 * @date    2015年9月30日
 * @version v1.0.0
 */
@MapperScan("bizRechargeSystemInfoMapper")
public interface BizRechargeSystemInfoMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizRechargeSystemInfo record);

    int insertSelective(BizRechargeSystemInfo record);

    BizRechargeSystemInfo selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizRechargeSystemInfo record);

    int updateByPrimaryKey(BizRechargeSystemInfo record);
    
    /**
     * Description：查询所有充值渠道
     * @author  JingYu.Dai
     * @date    2015年9月30日
     * @version v1.0.0
     * @return List<BizRechargeSystemInfo>
     */
    List<BizRechargeSystemInfo> selectAll();
}