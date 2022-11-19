package com.yscf.core.service.user.impl;

import java.awt.FontFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustWitStatusWaterMapper;
import com.yscf.core.model.user.CustWitStatusWater;
import com.yscf.core.service.user.ICustWitStatusWaterService;
/**
 * Description：<br> 
 * 客户提现状态管理服务实现
 * @author  heng.wang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Service("custWitStatusWaterServiceImpl")
public class CustWitStatusWaterServiceImpl extends BaseService<BaseEntity, String>
			implements ICustWitStatusWaterService{
	@Autowired
	public CustWitStatusWaterServiceImpl(CustWitStatusWaterMapper dao) {
		super(dao);
	}

	@Override
	public int insert(CustWitStatusWater custWitStatusWater)
			throws FontFormatException {
		int result=0;
		if(null !=custWitStatusWater){
			CustWitStatusWaterMapper mappers = (CustWitStatusWaterMapper) getDao();
			result = mappers.insertSelective(custWitStatusWater);
		}
		return result;
	}

	@Override
	public PageList<CustWitStatusWater> selectCusStatusChangeHistoryById(
			String pid, PageInfo info) {
		CustWitStatusWaterMapper mapper = (CustWitStatusWaterMapper) getDao();
		CustWitStatusWater custWitStatusWater = new CustWitStatusWater();
		custWitStatusWater.setCustomerId(pid);
		
		return mapper.selectCusStatusChangeHistoryById(custWitStatusWater,info); 
	}
	
}
