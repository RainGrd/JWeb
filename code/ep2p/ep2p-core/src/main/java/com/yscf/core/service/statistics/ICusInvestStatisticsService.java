/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.statistics;

import java.util.List;
import java.util.Map;

import com.yscf.core.model.statistics.CusInvestStatistics;
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
public interface ICusInvestStatisticsService {

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
	 * @Description : 投资人年龄/性别分布情况
	 * @param type
	 *            1 年龄 2 性别
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月6日 下午4:21:51
	 */
	List<CusInvestStatisticsDto> selectInvestmentStatisticsByCondition(Integer type);
	
	/**
	 * 
	 * @Description : 使用终端统计
	 * @param sysIpPvHis
	 *            统计条件
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月11日 下午3:57:02
	 */
	List<CusInvestStatisticsDto> selectSysIpPvHisStatistics(SysIpPvHis sysIpPvHis);

}
