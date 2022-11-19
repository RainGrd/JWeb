package com.yscf.core.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustLargessVipWaterMapper;
import com.yscf.core.model.user.CustLargessVipWater;
import com.yscf.core.service.user.ICustLargessVipWaterService;
/**
 * Description：<br> 
 * 赠送vip服务实现
 * @author  heng.wang
 * @date    2015年9月22日
 * @version v1.0.0
 */
@Service("custLargessVipWaterServiceImpl")
public class CustLargessVipWaterServiceImpl extends BaseService<BaseEntity, String> 
		implements ICustLargessVipWaterService {
	
	@Autowired
	public CustLargessVipWaterServiceImpl(CustLargessVipWaterMapper dao) {
		super(dao);
	}

	@Override
	public int insert(CustLargessVipWater custLargessVipWater) {
		int result=0;
		if(null !=custLargessVipWater){
			CustLargessVipWaterMapper mappers = (CustLargessVipWaterMapper) getDao();
			result = mappers.insertSelective(custLargessVipWater);
		}
		return result;
	}

	@Override
	public PageList<CustLargessVipWater> getVipHistoryDetailedById(
			String pid, PageInfo info) {
		CustLargessVipWaterMapper mapper = (CustLargessVipWaterMapper) getDao();
		CustLargessVipWater custLargessVipWater = new CustLargessVipWater();
		custLargessVipWater.setCustomerId(pid);
		
		return mapper.getVipHistoryDetailedById(custLargessVipWater,info); 
	}

}
