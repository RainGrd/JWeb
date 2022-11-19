package com.yscf.core.service.user.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustDueinWaterMapper;
import com.yscf.core.model.user.CustDueinWater;
import com.yscf.core.service.user.ICustDueinWaterService;

/**
 * Description：<br> 
 * 客户总共待收管理服务实现
 * @author  heng.wang
 * @date    2015年10月14日
 * @version v1.0.0
 */
@Service("custDueinWaterServiceImpl")
public class CustDueinWaterServiceImpl extends BaseService<BaseEntity, String>
implements ICustDueinWaterService {
	
	@Autowired
	public CustDueinWaterServiceImpl(CustDueinWaterMapper dao) {
		super(dao);
	}

	@Override
	public PageList<CustDueinWater> selectCusTotalStayDetailsById(String pid,
			PageInfo info) {
		CustDueinWaterMapper mapper = (CustDueinWaterMapper) getDao();
		CustDueinWater custDueinWater = new CustDueinWater();
		custDueinWater.setCustomerId(pid);
		return mapper.selectCusTotalStayDetailsById(custDueinWater,info);
	}
	
	@Override
	public PageList<CustDueinWater> selectCollectInterestDetailsById(String pid,
			PageInfo info) {
		CustDueinWaterMapper mapper = (CustDueinWaterMapper) getDao();
		CustDueinWater custDueinWater = new CustDueinWater();
		custDueinWater.setCustomerId(pid);
		return mapper.selectCollectInterestDetailsById(custDueinWater,info);
	}

	@Override
	public PageList<CustDueinWater> selectAllPage(CustDueinWater custDueinWater,
			PageInfo pageInfo) {
		CustDueinWaterMapper mapper = (CustDueinWaterMapper) getDao();
		PageList<CustDueinWater>  totalDaiShou = mapper.selectAllPage(custDueinWater, pageInfo);
		Map<String,BigDecimal> map = mapper.selectAllPageSum(custDueinWater);
		CustDueinWater custDueinWater2 = new CustDueinWater();
		if(map!=null){
		custDueinWater2.setDueinTotalValue(map.get("dueinTotalValue"));
		custDueinWater2.setCorpus(map.get("corpus"));
		custDueinWater2.setAccrual(map.get("accrual"));
		totalDaiShou.add(custDueinWater2);
		}
 		return totalDaiShou;
	}

}
