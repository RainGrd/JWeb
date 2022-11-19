package com.yscf.core.dao.financial;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizAccountAmountDetail;

/**
 * 
 * Description：<br> 
 * 资金类别变化明细数据访问层
 * @author  Jie.Zou
 * @date    2015年12月14日
 * @version v1.0.0
 */
@MapperScan("bizAccountAmountDetailMapper")
public interface BizAccountAmountDetailMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizAccountAmountDetail record);

    int insertSelective(BizAccountAmountDetail record);

    BizAccountAmountDetail selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizAccountAmountDetail record);

    int updateByPrimaryKey(BizAccountAmountDetail record);
    
    /**
     * 
     * Description：<br> 
     * 批量新增资金类别变化明细
     * @author  Jie.Zou
     * @date    2016年1月12日
     * @version v1.0.0
     * @param details
     * @return
     */
    int addDetails(Map<String, Object> details);
}