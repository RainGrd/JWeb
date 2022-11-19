package com.yscf.core.service.user.impl;

import java.awt.FontFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustBlacklistStatusWaterMapper;
import com.yscf.core.model.user.CustBlacklistStatusWater;
import com.yscf.core.service.user.ICustBlacklistStatusWaterService;

/**
 * Description：<br> 
 * 黑名单客户状态管理服务实现
 * @author  heng.wang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Service("custBlacklistStatusWaterServiceImpl")
public class CustBlacklistStatusWaterServiceImpl extends BaseService<BaseEntity, String>
		implements ICustBlacklistStatusWaterService {
	
	@Autowired
	public CustBlacklistStatusWaterServiceImpl(CustBlacklistStatusWaterMapper dao) {
		super(dao);
	}

	@Override	
	public int insert(CustBlacklistStatusWater custBlacklistStatusWater)
			throws FontFormatException {
		int result=0;
		if(null !=custBlacklistStatusWater){
			CustBlacklistStatusWaterMapper mappers = (CustBlacklistStatusWaterMapper) getDao();
			result = mappers.insertSelective(custBlacklistStatusWater);
		}
		return result;
	}

	@Override
	public PageList<CustBlacklistStatusWater> selectBlicklistCusStatusHistoryById(
			String pid, PageInfo info) {
		CustBlacklistStatusWaterMapper mapper = (CustBlacklistStatusWaterMapper) getDao();
		CustBlacklistStatusWater custBlacklistStatusWater = new CustBlacklistStatusWater();
		custBlacklistStatusWater.setCustomerId(pid);
		
		return mapper.selectBlicklistCusStatusHistoryById(custBlacklistStatusWater,info); 
	}

}
