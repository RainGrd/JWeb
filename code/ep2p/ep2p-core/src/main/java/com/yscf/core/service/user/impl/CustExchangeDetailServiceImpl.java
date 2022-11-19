package com.yscf.core.service.user.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.common.Constant.ExchangeConstant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.user.CustExchangeDetailMapper;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.CustExchangeDetail;
import com.yscf.core.service.user.ICustExchangeDetailService;

/**
 * 
 * @ClassName : CustExchangeDetailServiceImpl
 * @Description : 积分兑换明细商务接口
 * @Author : Qing.Cai
 * @Date : 2016年1月14日 上午10:18:56
 */
@Service("custExchangeDetailServiceImpl")
public class CustExchangeDetailServiceImpl extends BaseService<BaseEntity, String> implements ICustExchangeDetailService {

	private Logger logger = LoggerFactory.getLogger(CustExchangeDetailServiceImpl.class);

	@Autowired
	public CustExchangeDetailServiceImpl(CustExchangeDetailMapper dao) {
		super(dao);
	}

	/**
	 * 
	 * @Description : 查询所有兑换明细
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param info
	 *            分页对象
	 * @return 兑换明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午10:21:55
	 */
	@Override
	public PageList<CustExchangeDetail> selectAllPage(CustExchangeDetail custExchangeDetail, PageInfo info) {
		CustExchangeDetailMapper mapper = (CustExchangeDetailMapper) getDao();
		// 创建需要返回的list
		PageList<CustExchangeDetail> list = null;
		try {
			// 时间处理
			// 修改兑换发生时间格式-begin
			if (null != custExchangeDetail.getExchangeBeginTime() && !"".equals(custExchangeDetail.getExchangeBeginTime())) {
				custExchangeDetail.setExchangeBeginTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeBeginTime(), 1));
			}
			// 修改兑换发生时间格式-end
			if (null != custExchangeDetail.getExchangeEndTime() && !"".equals(custExchangeDetail.getExchangeEndTime())) {
				custExchangeDetail.setExchangeEndTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeEndTime(), 2));
			}
			list = mapper.selectAllPage(custExchangeDetail, info);
			// 总计
			if (null == list) {
				list = new PageList<CustExchangeDetail>();
			} else {
				// 总计积分
				Integer points = 0;
				for (CustExchangeDetail vo : list) {
					// 循环累加
					points += vo.getExchangePoint();
					// 判断日期是否有错误
					if (vo.getExchangeTime().indexOf(".") != -1) {
						// 格式化日期
						vo.setExchangeTime(vo.getExchangeTime().substring(0, vo.getExchangeTime().indexOf(".")));
					}
				}
				// 创建总计对象
				CustExchangeDetail c = new CustExchangeDetail();
				c.setExchangeRemark("总计");
				c.setExchangePoint(points);
				// 添加到list
				list.add(c);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询所有兑换明细：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 积分兑换统计查询
	 * @param custExchangeDetail
	 *            积分明细对象
	 * @return 积分兑换统计列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 下午7:56:56
	 */
	@Override
	public List<CustExchangeDetail> selectAllStatistics(CustExchangeDetail custExchangeDetail) {
		CustExchangeDetailMapper mapper = (CustExchangeDetailMapper) getDao();
		List<CustExchangeDetail> list = null;
		try {
			// 时间处理
			// 修改兑换发生时间格式-begin
			if (null != custExchangeDetail.getExchangeBeginTime() && !"".equals(custExchangeDetail.getExchangeBeginTime())) {
				custExchangeDetail.setExchangeBeginTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeBeginTime(), 1));
			}
			// 修改兑换发生时间格式-end
			if (null != custExchangeDetail.getExchangeEndTime() && !"".equals(custExchangeDetail.getExchangeEndTime())) {
				custExchangeDetail.setExchangeEndTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeEndTime(), 2));
			}
			// 统计数据
			list = mapper.selectAllStatistics(custExchangeDetail);
			// 获取统计总数
			Integer total = mapper.selectAllStatisticsCount(custExchangeDetail);
			// 总计
			if (null == list) {
				list = new ArrayList<CustExchangeDetail>();
			} else {
				// 循环修改总额
				for (CustExchangeDetail vo : list) {
					BigDecimal bd = new BigDecimal(Double.parseDouble((vo.getPensNumber() * 100.0 / total)+""));
					// 判断笔数
					vo.setPensNumberProportion((bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) + "%");
					// 判断类型是不是兑换话费or兑换现金
					if (vo.getExchangeType().equals(ExchangeConstant.EXCHANGE_DETAIL_TYPE_1)) {
						// 如果是兑换现金
						if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_TELEPHONE_FARE_KEY_1)) {
							// 如果是兑换20元话费的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_TELEPHONE_FARE_VALUES_1 * 1.0);
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_TELEPHONE_FARE_KEY_2)) {
							// 如果是兑换50元话费的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_TELEPHONE_FARE_VALUES_2 * 1.0);
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_TELEPHONE_FARE_KEY_3)) {
							// 如果是兑换100元话费的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_TELEPHONE_FARE_VALUES_3 * 1.0);
						}
					} else if (vo.getExchangeType().equals(ExchangeConstant.EXCHANGE_DETAIL_TYPE_4)) {
						// 如果是兑换现金
						if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_CASH_KEY_1)) {
							// 如果是兑换20元现金的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_CASH_VALUES_1 * 1.0);
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_CASH_KEY_2)) {
							// 如果是兑换50元现金的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_CASH_VALUES_2 * 1.0);
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_CASH_KEY_3)) {
							// 如果是兑换100元现金的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_CASH_VALUES_3 * 1.0);
						}
					} else {
						// 如果是兑换加息劵 或者 VIP 就不显示总额
						vo.setTotal(null);
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("积分兑换统计查询：", e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Description : 导出查询兑换明细
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param eom
	 *            导出对象
	 * @return 需要导出的兑换明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午10:47:44
	 */
	@Override
	public List<CustExchangeDetail> selectAllPageExport(CustExchangeDetail custExchangeDetail, ExportObjectModel eom) {
		CustExchangeDetailMapper mapper = (CustExchangeDetailMapper) getDao();
		List<CustExchangeDetail> list = null;
		try {
			// 时间处理
			// 修改兑换发生时间格式-begin
			if (null != custExchangeDetail.getExchangeBeginTime() && !"".equals(custExchangeDetail.getExchangeBeginTime())) {
				custExchangeDetail.setExchangeBeginTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeBeginTime(), 1));
			}
			// 修改兑换发生时间格式-end
			if (null != custExchangeDetail.getExchangeEndTime() && !"".equals(custExchangeDetail.getExchangeEndTime())) {
				custExchangeDetail.setExchangeEndTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeEndTime(), 2));
			}
			// 创建map条件,作为导出的查询
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("custExchangeDetail", custExchangeDetail);
			map.put("expm", eom);
			list = mapper.selectAllPageExport(map);
			// 判断是否为空
			if (null == list) {
				list = new ArrayList<CustExchangeDetail>();
			} else {
				// 总计积分
				Integer points = 0;
				for (CustExchangeDetail vo : list) {
					// 循环累加
					points += vo.getExchangePoint();
					// 判断日期是否有错误
					if (vo.getExchangeTime().indexOf(".") != -1) {
						// 格式化日期
						vo.setExchangeTime(vo.getExchangeTime().substring(0, vo.getExchangeTime().indexOf(".")));
					}
				}
				// 创建总计对象
				CustExchangeDetail c = new CustExchangeDetail();
				c.setExchangeRemark("总计");
				c.setExchangePoint(points);
				// 添加到list
				list.add(c);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("导出查询兑换明细：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 导出查询兑换统计
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @param eom
	 *            导出对象
	 * @return 兑换统计集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月18日 上午10:18:18
	 */
	@Override
	public List<CustExchangeDetail> selectAllStatisticsExport(CustExchangeDetail custExchangeDetail, ExportObjectModel eom) {
		CustExchangeDetailMapper mapper = (CustExchangeDetailMapper) getDao();
		List<CustExchangeDetail> list = null;
		try {
			// 时间处理
			// 修改兑换发生时间格式-begin
			if (null != custExchangeDetail.getExchangeBeginTime() && !"".equals(custExchangeDetail.getExchangeBeginTime())) {
				custExchangeDetail.setExchangeBeginTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeBeginTime(), 1));
			}
			// 修改兑换发生时间格式-end
			if (null != custExchangeDetail.getExchangeEndTime() && !"".equals(custExchangeDetail.getExchangeEndTime())) {
				custExchangeDetail.setExchangeEndTime(EscfDateUtil.formatterDate(custExchangeDetail.getExchangeEndTime(), 2));
			}
			// 创建map条件,作为导出的查询
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("custExchangeDetail", custExchangeDetail);
			map.put("expm", eom);
			list = mapper.selectAllStatisticsExport(map);
			// 获取统计总数
			Integer total = mapper.selectAllStatisticsCount(custExchangeDetail);
			// 判断是否为空
			if (null == list) {
				list = new ArrayList<CustExchangeDetail>();
			} else {
				// 循环修改总额
				for (CustExchangeDetail vo : list) {
					BigDecimal bd = new BigDecimal(Double.parseDouble((vo.getPensNumber() * 100.0 / total)+""));
					// 判断笔数
					vo.setPensNumberProportion((bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()) + "%");
					// 判断类型是不是兑换话费or兑换现金
					if (vo.getExchangeType().equals(ExchangeConstant.EXCHANGE_DETAIL_TYPE_1)) {
						// 如果是兑换现金
						if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_TELEPHONE_FARE_KEY_1)) {
							// 如果是兑换20元话费的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_TELEPHONE_FARE_VALUES_1 * 1.0);
							// 设置类别
							vo.setExchangeType("兑换话费-20元");
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_TELEPHONE_FARE_KEY_2)) {
							// 如果是兑换50元话费的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_TELEPHONE_FARE_VALUES_2 * 1.0);
							// 设置类别
							vo.setExchangeType("兑换话费-50元");
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_TELEPHONE_FARE_KEY_3)) {
							// 如果是兑换100元话费的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_TELEPHONE_FARE_VALUES_3 * 1.0);
							// 设置类别
							vo.setExchangeType("兑换话费-100元");
						}
					} else if (vo.getExchangeType().equals(ExchangeConstant.EXCHANGE_DETAIL_TYPE_4)) {
						// 如果是兑换现金
						if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_CASH_KEY_1)) {
							// 如果是兑换20元现金的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_CASH_VALUES_1 * 1.0);
							// 设置类别
							vo.setExchangeType("兑换现金-20元");
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_CASH_KEY_2)) {
							// 如果是兑换50元现金的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_CASH_VALUES_2 * 1.0);
							// 设置类别
							vo.setExchangeType("兑换现金-50元");
						} else if (vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_CASH_KEY_3)) {
							// 如果是兑换100元现金的话,计算出总额
							vo.setTotal(vo.getPensNumber() * ExchangeConstant.EXCHANGE_CASH_VALUES_3 * 1.0);
							// 设置类别
							vo.setExchangeType("兑换现金-100元");
						}
					} else if(vo.getExchangeType().equals(ExchangeConstant.EXCHANGE_DETAIL_TYPE_2)){
						// 如果是兑换加息劵就不显示总额
						vo.setTotal(null);
						// 设置类别
						if(vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_INTEREST_TICKET_KEY_1)){
							vo.setExchangeType("兑换加息劵-0.2%");
						}else if(vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_INTEREST_TICKET_KEY_2)){
							vo.setExchangeType("兑换加息劵-0.5%");
						}else if(vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_INTEREST_TICKET_KEY_3)){
							vo.setExchangeType("兑换加息劵-1.0%");
						}
					} else if(vo.getExchangeType().equals(ExchangeConstant.EXCHANGE_DETAIL_TYPE_3)){
						// 如果是兑换VIP就不显示总额
						vo.setTotal(null);
						// 设置类别
						if(vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_VIP_KEY_1)){
							vo.setExchangeType("兑换VIP-12月");
						}else if(vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_VIP_KEY_2)){
							vo.setExchangeType("兑换VIP-24月");
						}else if(vo.getExchangePoint().equals(ExchangeConstant.EXCHANGE_VIP_KEY_3)){
							vo.setExchangeType("兑换VIP-36月");
						}
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("导出查询兑换统计：", e.getMessage());
				e.printStackTrace();
			}
		}
		return list;
	}

}
