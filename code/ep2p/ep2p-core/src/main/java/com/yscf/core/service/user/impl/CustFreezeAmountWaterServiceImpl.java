package com.yscf.core.service.user.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustFreezeWaterMapper;
import com.yscf.core.model.user.CustFreezeWater;
import com.yscf.core.service.user.ICustFreezeAmountWaterService;

/**
 * Description：<br> 
 * 客户冻结金额明细服务实现
 * @author  heng.wang
 * @date    2015年10月9日
 * @version v1.0.0
 */
@Service("custFreezeAmountWaterServiceImpl")
public class CustFreezeAmountWaterServiceImpl extends BaseService<BaseEntity, String> 
implements ICustFreezeAmountWaterService {

	@Autowired
	public CustFreezeAmountWaterServiceImpl(CustFreezeWaterMapper dao) {
		super(dao);
	}

	@Override
	public PageList<CustFreezeWater>  selectAllPage(CustFreezeWater custFreezeWater,
			PageInfo pageInfo) {
		CustFreezeWaterMapper mapper = (CustFreezeWaterMapper) getDao();
	    PageList<CustFreezeWater> dongjie = mapper.selectAllPage(custFreezeWater, pageInfo);
		Map<String,BigDecimal> map = mapper.selectAllPageSum(custFreezeWater);
		CustFreezeWater custFreezeWater1 = new CustFreezeWater();
		custFreezeWater1.setFreezeValue(map.get("freezeValue"));
		dongjie.add(custFreezeWater1);
 		return dongjie;
	}

	

}
