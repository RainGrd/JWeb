package com.yscf.core.service.user.impl;

import java.awt.FontFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustStatusWaterMapper;
import com.yscf.core.model.user.CustStatusWater;
import com.yscf.core.service.user.ICustStatusWaterService;

/**
 * Description：<br> 
 * 客户状态管理服务实现
 * @author  heng.wang
 * @date    2015年9月23日
 * @version v1.0.0
 */
@Service("custStatusWaterServiceImpl")
public class CustStatusWaterServiceImpl extends BaseService<BaseEntity, String> 
	implements ICustStatusWaterService{
	
	@Autowired
	public CustStatusWaterServiceImpl(CustStatusWaterMapper dao) {
		super(dao);
	}

	@Override
	public int insert(CustStatusWater custStatusWater)
			throws FontFormatException {
		int result=0;
		if(null !=custStatusWater){
			CustStatusWaterMapper mappers = (CustStatusWaterMapper) getDao();
			result = mappers.insertSelective(custStatusWater);
		}
		return result;
	}

	@Override
	public PageList<CustStatusWater> cusStatusChangeHistoryById(String pid,
			PageInfo info) {
		CustStatusWaterMapper mapper = (CustStatusWaterMapper) getDao();
		CustStatusWater custStatusWater = new CustStatusWater();
		custStatusWater.setCustomerId(pid);
		return mapper.cusStatusChangeHistoryById(custStatusWater,info); 
	}

}
