package com.yscf.core.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CusUpdateServiceWaterMapper;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CusUpdateServiceWater;
import com.yscf.core.service.user.ICusUpdateServiceWaterService;
/**
 * Description：<br> 
 * 客户更换客服流水表实现
 * @author  heng.wang
 * @date    2015年9月14日
 * @version v1.0.0
 */
@Service("cusUpdateServiceWaterServiceImpl")
public class CusUpdateServiceWaterServiceImpl extends BaseService<BaseEntity, String> 
		implements ICusUpdateServiceWaterService{
	
	@Autowired
	public CusUpdateServiceWaterServiceImpl(CusUpdateServiceWaterMapper dao) {
		super(dao);
	}
	
	

	@Override
	public int insertCustWater(CusTomer cusTomer) {
		int result=0;
		CusUpdateServiceWaterMapper mapper = (CusUpdateServiceWaterMapper) getDao();
		if(null !=cusTomer){
			String[] cids = cusTomer.getPid().split(",");
			String[] ouids = cusTomer.getOldcusServiceId().split(",");
			String newCustServiceId = cusTomer.getCusServicePid();
			for (int i = 0; i < ouids.length; i++) {
				CusUpdateServiceWater us = new CusUpdateServiceWater();
				us.setPid(us.getPK());
				us.setNewCustServiceId(newCustServiceId);
				us.setOldCustServiceId(ouids[i]);
				us.setCustomerId(cids[i]);
				us.setCreateTime(cusTomer.getCreateTime());
				us.setCreateUser(cusTomer.getCreateUser());
				result += mapper.insertSelective(us);
			}
		}
		return result;
	}

	@Override
	public PageList<CusUpdateServiceWater> cusHistoryDetailedById(String pid,PageInfo info) {
			CusUpdateServiceWaterMapper mapper = (CusUpdateServiceWaterMapper) getDao();
			CusUpdateServiceWater csUpdateServiceWater = new CusUpdateServiceWater();
			csUpdateServiceWater.setPid(pid);
			return mapper.cusHistoryDetailedById(csUpdateServiceWater,info); 
	}
}
