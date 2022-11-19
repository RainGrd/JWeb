package com.yscf.core.dao.statistics;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.statistics.CusInvestStatistics;
import com.yscf.core.model.statistics.CusInvestStatisticsAge;
import com.yscf.core.model.statistics.CusInvestStatisticsDto;
import com.yscf.core.model.system.SysIpPvHis;

/**
 * Description：<br>
 * 客户投资统计报表
 * 
 * @author JingYu.Dai
 * @date 2015年11月5日
 * @version v1.0.0
 */
@MapperScan("cusInvestStatisticsMapper")
public interface CusInvestStatisticsMapper extends IBaseDao<BaseEntity, String> {

	/**
	 * Description：<br>
	 * 投资人区域分布统计 按省份统计
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param map
	 *            {key:orderByType 、value:1、2 }排序方式 1：按金额排序、2：按人数排序
	 * @return List<CusBorrowStatistics>
	 */
	List<CusInvestStatistics> selectInvCusStatisticsGroupProvince(Map<String, String> map);

	/**
	 * Description：<br>
	 * 投资人区域分布统计 按市统计
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param map
	 *            {key:orderByType 、value:1、2 | key:provincee 、value:省份 }排序方式
	 *            1：按金额排序、2：按人数排序
	 * @return List<CusBorrowStatistics>
	 */
	List<CusInvestStatistics> selectInvCusStatisticsGroupCity(Map<String, String> map);

	/**
	 * 
	 * @Description : 投资人按性别统计
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月6日 下午4:14:59
	 */
	List<CusInvestStatisticsDto> selectInvestmentStatisticsBySex();

	/**
	 * 
	 * @Description : 投资人按年龄统计
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月6日 下午4:15:34
	 */
	CusInvestStatisticsAge selectInvestmentStatisticsByAge();

	/**
	 * 
	 * @Description : 使用终端统计
	 * @param sysIpPvHis
	 *            统计条件
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月11日 下午3:56:02
	 */
	List<CusInvestStatisticsDto> selectSysIpPvHisStatistics(SysIpPvHis sysIpPvHis);

}