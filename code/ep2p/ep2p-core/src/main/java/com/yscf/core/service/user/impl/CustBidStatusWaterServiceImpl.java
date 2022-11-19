package com.yscf.core.service.user.impl;

import java.awt.FontFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustBidStatusWaterMapper;
import com.yscf.core.model.user.CustBidStatusWater;
import com.yscf.core.service.user.ICustBidStatusWaterService;
/**
 * Description：<br> 
 * 客户投标状态管理服务实现
 * @author  heng.wang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Service("custBidStatusWaterServiceImpl")
public class CustBidStatusWaterServiceImpl extends BaseService<BaseEntity, String>
	implements ICustBidStatusWaterService {
	
	@Autowired
	public CustBidStatusWaterServiceImpl(CustBidStatusWaterMapper dao) {
		super(dao);
	}

	@Override
	public int insert(CustBidStatusWater  custBidStatusWater)
			throws FontFormatException {
		int result=0;
		if(null !=custBidStatusWater){
			CustBidStatusWaterMapper mappers = (CustBidStatusWaterMapper) getDao();
			result = mappers.insertSelective(custBidStatusWater);
		}
		return result;
	}

	@Override
	public PageList<CustBidStatusWater> selectBidCusStatusHistoryById(
			String pid, PageInfo info) {
		CustBidStatusWaterMapper mapper = (CustBidStatusWaterMapper) getDao();
		CustBidStatusWater custBidStatusWater = new CustBidStatusWater();
		custBidStatusWater.setCustomerId(pid);
		
		return mapper.selectBidCusStatusHistoryById(custBidStatusWater,info);
	}
}
