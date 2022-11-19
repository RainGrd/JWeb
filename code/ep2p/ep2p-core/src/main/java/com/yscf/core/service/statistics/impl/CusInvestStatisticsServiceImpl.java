/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.statistics.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.statistics.CusInvestStatisticsMapper;
import com.yscf.core.model.statistics.CusInvestStatistics;
import com.yscf.core.model.statistics.CusInvestStatisticsAge;
import com.yscf.core.model.statistics.CusInvestStatisticsDto;
import com.yscf.core.model.system.SysIpPvHis;
import com.yscf.core.service.statistics.ICusInvestStatisticsService;

/**
 * Description：<br>
 * 客户借款统计报表
 * 
 * @author JingYu.Dai
 * @date 2015年11月5日
 * @version v1.0.0
 */
@Service("cusInvestStatisticsServiceImpl")
public class CusInvestStatisticsServiceImpl extends BaseService<BaseEntity, String> implements ICusInvestStatisticsService {

	private Logger logger = LoggerFactory.getLogger(CusInvestStatisticsServiceImpl.class);

	@Autowired
	public CusInvestStatisticsServiceImpl(CusInvestStatisticsMapper dao) {
		super(dao);
	}

	@Override
	public List<CusInvestStatistics> selectInvCusStatisticsGroupProvince(Map<String, String> map) {
		CusInvestStatisticsMapper dao = (CusInvestStatisticsMapper) getDao();
		return dao.selectInvCusStatisticsGroupProvince(map);
	}

	@Override
	public List<CusInvestStatistics> selectInvCusStatisticsGroupCity(Map<String, String> map) {
		CusInvestStatisticsMapper dao = (CusInvestStatisticsMapper) getDao();
		return dao.selectInvCusStatisticsGroupCity(map);
	}

	/**
	 * 
	 * @Description : 投资人年龄/性别分布情况
	 * @param type
	 *            1 年龄 2 性别
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月6日 下午4:25:41
	 */
	@Override
	public List<CusInvestStatisticsDto> selectInvestmentStatisticsByCondition(Integer type) {
		CusInvestStatisticsMapper mapper = (CusInvestStatisticsMapper) getDao();
		List<CusInvestStatisticsDto> list = new ArrayList<CusInvestStatisticsDto>();
		try {
			// 判断哪一种统计方式
			if (type == 1) {
				// 如果是按照年龄统计,先获取统计对象,再解析统计对象生成同统计列表
				CusInvestStatisticsAge cisa = mapper.selectInvestmentStatisticsByAge();

				CusInvestStatisticsDto cd = new CusInvestStatisticsDto();
				cd.setName("18-24岁");
				cd.setY(cisa.getAgeValue1());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("25-30岁");
				cd.setY(cisa.getAgeValue2());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("31-35岁");
				cd.setY(cisa.getAgeValue3());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("36-40岁");
				cd.setY(cisa.getAgeValue4());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("41-45岁");
				cd.setY(cisa.getAgeValue5());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("46-50岁");
				cd.setY(cisa.getAgeValue6());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("51-60");
				cd.setY(cisa.getAgeValue7());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("61岁以上");
				cd.setY(cisa.getAgeValue8());
				list.add(cd);
				cd = new CusInvestStatisticsDto();
				cd.setName("其他");
				cd.setY(cisa.getAgeValue9());
				list.add(cd);
			} else if (type == 2) {
				list = mapper.selectInvestmentStatisticsBySex();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("投资人年龄/性别分布情况:" + e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 使用终端统计
	 * @param sysIpPvHis
	 *            统计条件
	 * @return 统计列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月11日 下午3:57:35
	 */
	@Override
	public List<CusInvestStatisticsDto> selectSysIpPvHisStatistics(SysIpPvHis sysIpPvHis) {
		CusInvestStatisticsMapper mapper = (CusInvestStatisticsMapper) getDao();
		List<CusInvestStatisticsDto> list = new ArrayList<CusInvestStatisticsDto>();
		try {
			list = mapper.selectSysIpPvHisStatistics(sysIpPvHis);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("投资人使用终端统计情况:" + e.getMessage());
			}
		}
		return list;
	}

}
