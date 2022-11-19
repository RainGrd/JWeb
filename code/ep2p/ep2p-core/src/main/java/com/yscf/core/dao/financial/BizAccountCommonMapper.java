package com.yscf.core.dao.financial;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizAccountCommon;

/**
 * Description：<br> 
 * 普通资金 数据访问层
 * @author  JingYu.Dai
 * @date    2015年12月25日
 * @version v1.0.0
 */
@MapperScan("bizAccountCommonMapper")
public interface BizAccountCommonMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizAccountCommon record);

    int insertSelective(BizAccountCommon record);

    BizAccountCommon selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizAccountCommon record);

    int updateByPrimaryKey(BizAccountCommon record);

	BizAccountCommon selectAccountCommonByCustomerId(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过客户ID结合批量修改充值资金可用余额
	 * @author  Jie.Zou
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param userid
	 * @param amount
	 * @return
	 */
	int updateAmountByCustIds(Map<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量修改普通资金可用余额根据Map
	 * @author  Jie.Zou
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param maps
	 * @return
	 */
	int updateAmountByMap(@Param("maps")Map<String, Object> maps);
}