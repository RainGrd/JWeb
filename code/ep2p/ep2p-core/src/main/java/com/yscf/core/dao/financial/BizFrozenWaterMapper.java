package com.yscf.core.dao.financial;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizFrozenWater;

/**
 * Description：<br> 
 * 冻结资金流水表 数据访问层接口
 * @author  JingYu.Dai
 * @date    2016年1月3日
 * @version v1.0.0
 */
@MapperScan("bizFrozenWaterMapper")
public interface BizFrozenWaterMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizFrozenWater record);

    int insertSelective(BizFrozenWater record);

    BizFrozenWater selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizFrozenWater record);

    int updateByPrimaryKey(BizFrozenWater record);
    
    /**
     * 
     * Description：<br> 
     * 根据用户id，借款id获取用户投资冻结明细
     * @author  JunJie.Liu
     * @date    2015年12月17日
     * @version v1.0.0
     * @param userId
     * @param borrowId
     * @param investType 
     * @return
     */
	List<BizFrozenWater> findList(@Param("userId") String userId,@Param("fkey")  String fkey, @Param("fundType") String fundType);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量删除冻结流水
	 * @author  JunJie.Liu
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param userId
	 * @param fkey
	 * @param fundType
	 */
	void updateByDelete(@Param("userId") String userId,@Param("fkey")  String fkey, @Param("fundType") String fundType);

}