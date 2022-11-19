package com.yscf.core.service.user.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CusTomerDtoMapper;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.user.ICusTomerDtoService;

/**
 * Description：<br>
 * 客户管理服务实现
 * 
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
@Service("cusTomerDtoService")
public class CusTomerDtoServiceImpl extends BaseService<BaseEntity, String> implements ICusTomerDtoService {
	
	@Autowired
	public CusTomerDtoServiceImpl(CusTomerDtoMapper dao) {
		super(dao);
	}

	Logger logger = Logger.getLogger(CusTomerDtoServiceImpl.class);

	@Override
	public CustomerDto getCustomerDtoByPid(String pid) {
		CusTomerDtoMapper mapper = (CusTomerDtoMapper) getDao();
		return mapper.getCustomerDtoByPid(pid);
	}

}
