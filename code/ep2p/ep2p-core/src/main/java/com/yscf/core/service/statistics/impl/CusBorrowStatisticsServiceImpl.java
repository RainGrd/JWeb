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

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.statistics.CusBorrowStatisticsMapper;
import com.yscf.core.model.statistics.CusBorrowStatistics;
import com.yscf.core.service.statistics.ICusBorrowStatisticsService;

/**
 * Description：<br> 
 * 客户借款统计报表
 * @author  JingYu.Dai
 * @date    2015年11月5日
 * @version v1.0.0
 */
@Service("cusBorrowStatisticsServiceImpl")
public class CusBorrowStatisticsServiceImpl extends BaseService<BaseEntity, String> implements ICusBorrowStatisticsService{

	@Autowired
	public CusBorrowStatisticsServiceImpl(CusBorrowStatisticsMapper dao) {
		super(dao);
	}

	@Override
	public List<CusBorrowStatistics> selectBorCusStatisticsGroupProvince(
			Map<String, String> map) {
		CusBorrowStatisticsMapper dao = (CusBorrowStatisticsMapper) getDao();
		return dao.selectBorCusStatisticsGroupProvince(map);
	}

	@Override
	public List<CusBorrowStatistics> selectBorCusStatisticsGroupCity(
			Map<String, String> map) {
		CusBorrowStatisticsMapper dao = (CusBorrowStatisticsMapper) getDao();
		return dao.selectBorCusStatisticsGroupCity(map);
	}

}


