package com.yscf.core.dao.statistics;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.statistics.CusBorrowStatistics;

/**
 * Description：<br> 
 * 客户借款统计报表
 * @author  JingYu.Dai
 * @date    2015年11月5日
 * @version v1.0.0
 */
@MapperScan("cusBorrowStatisticsMapper")
public interface CusBorrowStatisticsMapper extends IBaseDao<BaseEntity, String> {
	
	/**
	 * Description：<br> 
	 * 借款人区域分布统计	按省份统计
	 * @author  JingYu.Dai
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param map {key:orderByType 、value:1、2 }排序方式 1：按金额排序、2：按人数排序
	 * @return List<CusBorrowStatistics>
	 */
	List<CusBorrowStatistics> selectBorCusStatisticsGroupProvince(Map<String,String> map);
	
	/**
	 * Description：<br> 
	 * 借款人区域分布统计	按市统计
	 * @author  JingYu.Dai
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param map {key:orderByType 、value:1、2 | key:provincee 、value:省份 }排序方式 1：按金额排序、2：按人数排序
	 * @return List<CusBorrowStatistics>
	 */
	List<CusBorrowStatistics> selectBorCusStatisticsGroupCity(Map<String,String> map);
    
}