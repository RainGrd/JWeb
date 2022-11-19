package com.yscf.core.service.user.impl;

import java.awt.FontFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustTransferStatusWaterMapper;
import com.yscf.core.model.user.CustTransferStatusWater;
import com.yscf.core.service.user.ICustTransferStatusWaterService;

/**
 * Description：<br> 
 * 客户债权转让状态管理服务实现
 * @author  heng.wang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Service("custTransferStatusWaterServiceImpl")
public class CustTransferStatusWaterServiceImpl extends BaseService<BaseEntity, String> 
implements ICustTransferStatusWaterService {
	
	@Autowired
	public CustTransferStatusWaterServiceImpl(CustTransferStatusWaterMapper dao) {
		super(dao);
	}

	@Override
	public int insert(CustTransferStatusWater custTransferStatusWater)
			throws FontFormatException {
		int result=0;
		if(null !=custTransferStatusWater){
			CustTransferStatusWaterMapper mappers = (CustTransferStatusWaterMapper) getDao();
			result = mappers.insertSelective(custTransferStatusWater);
		}
		return result;
	}

	@Override
	public PageList<CustTransferStatusWater> selectCusCredRightStatusHistoryById(
			String pid, PageInfo info) {
		CustTransferStatusWaterMapper mapper = (CustTransferStatusWaterMapper) getDao();
		CustTransferStatusWater custTransferStatusWater = new CustTransferStatusWater();
		custTransferStatusWater.setCustomerId(pid);
		
		return mapper.selectCusCredRightStatusHistoryById(custTransferStatusWater,info); 
	}

}
