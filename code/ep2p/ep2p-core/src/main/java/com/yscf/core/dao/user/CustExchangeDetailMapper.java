package com.yscf.core.dao.user;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustExchangeDetail;

/**
 * 
 * @ClassName : CustExchangeDetailMapper
 * @Description : 客户兑换明细数据层接口
 * @Author : Qing.Cai
 * @Date : 2016年1月13日 下午2:57:15
 */
public interface CustExchangeDetailMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustExchangeDetail record);

	int insertSelective(CustExchangeDetail record);

	CustExchangeDetail selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustExchangeDetail record);

	int updateByPrimaryKey(CustExchangeDetail record);

	/**
	 * 
	 * @Description : 查询所有兑换明细
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param info
	 *            分页对象
	 * @return 兑换明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月13日 下午3:36:33
	 */
	public PageList<CustExchangeDetail> selectAllPage(@Param("custExchangeDetail") CustExchangeDetail custExchangeDetail, PageInfo info);

	/**
	 * 
	 * @Description : 积分兑换统计查询
	 * @param custExchangeDetail
	 *            积分明细对象
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 下午7:53:03
	 */
	public List<CustExchangeDetail> selectAllStatistics(CustExchangeDetail custExchangeDetail);

	/**
	 * 
	 * @Description : 积分兑换统计查询总数
	 * @param custExchangeDetail
	 *            积分明细对象
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 下午7:54:30
	 */
	public Integer selectAllStatisticsCount(CustExchangeDetail custExchangeDetail);

	/**
	 * 
	 * @Description : 导出查询
	 * @param map
	 *            条件
	 * @return 兑换明细列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月13日 下午3:37:50
	 */
	public List<CustExchangeDetail> selectAllPageExport(HashMap<String, Object> map);

	/**
	 * 
	 * @Description : 导出查询
	 * @param map
	 *            条件
	 * @return 兑换统计列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月18日 上午10:32:40
	 */
	public List<CustExchangeDetail> selectAllStatisticsExport(HashMap<String, Object> map);
}