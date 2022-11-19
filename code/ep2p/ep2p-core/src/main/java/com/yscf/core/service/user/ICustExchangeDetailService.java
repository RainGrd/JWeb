package com.yscf.core.service.user;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.CustExchangeDetail;

/**
 * @ClassName : ICustExchangeDetailService
 * @Description : 积分兑换明细业务接口
 * @Author : Qing.Cai
 * @Date : 2016年1月14日 上午10:17:23
 */
public interface ICustExchangeDetailService {

	/**
	 * 
	 * @Description : 查询所有兑换明细
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param info
	 *            分页对象
	 * @return 兑换明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午10:21:16
	 */
	public PageList<CustExchangeDetail> selectAllPage(CustExchangeDetail custExchangeDetail, PageInfo info);

	/**
	 * 
	 * @Description : 积分兑换统计查询
	 * @param custExchangeDetail
	 *            积分明细对象
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 下午7:55:03
	 */
	public List<CustExchangeDetail> selectAllStatistics(CustExchangeDetail custExchangeDetail);

	/**
	 * 
	 * @Description : 导出查询兑换明细
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param eom
	 *            导出对象
	 * @return 需要导出的兑换明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午10:46:42
	 */
	public List<CustExchangeDetail> selectAllPageExport(CustExchangeDetail custExchangeDetail, ExportObjectModel eom);

	/**
	 * 
	 * @Description : 导出查询兑换统计
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param eom
	 *            导出对象
	 * @return 兑换统计集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月18日 上午10:17:25
	 */
	public List<CustExchangeDetail> selectAllStatisticsExport(CustExchangeDetail custExchangeDetail, ExportObjectModel eom);
}
