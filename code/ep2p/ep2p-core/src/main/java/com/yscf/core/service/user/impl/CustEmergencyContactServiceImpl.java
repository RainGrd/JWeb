package com.yscf.core.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustEmergencyContactMapper;
import com.yscf.core.model.user.CustEmergencyContact;
import com.yscf.core.service.user.ICustEmergencyContactService;

/**
 * Description：<br> 
 * 安全中心--紧急联系人服务实现
 * @author  heng.wang
 * @date    2015年11月12日
 * @version v1.0.0
 */
@Service("custEmergencyContactService")
public class CustEmergencyContactServiceImpl  extends BaseService<BaseEntity, String>
implements ICustEmergencyContactService{

	@Autowired
	public CustEmergencyContactServiceImpl(CustEmergencyContactMapper dao) {
		super(dao);
	}
    
	@Override
	public int updateCusNameByCusPid(CustEmergencyContact contact) {
		int result = 0;
		if (null != contact && null != contact.getCustomerId()) {
			CustEmergencyContactMapper mapper = (CustEmergencyContactMapper) getDao();
			result = mapper.updateCusNameByCusPid(contact);
		}
		return result;
	}

	@Override
	public PageList<CustEmergencyContact> selectOriginalByCusPid(String custPid) {
		CustEmergencyContactMapper mapper = (CustEmergencyContactMapper) getDao();
		return mapper.selectOriginalByCusPid(custPid);
	}

	@Override
	public int saveNewPassWord(CustEmergencyContact custEmergencyContact) {
		CustEmergencyContactMapper mapper = (CustEmergencyContactMapper) getDao();
		return mapper.saveNewPassWord(custEmergencyContact);
	}

	@Override
	public int saveNewTradePassWord(CustEmergencyContact contact) {
		CustEmergencyContactMapper mapper = (CustEmergencyContactMapper) getDao();
		return mapper.saveNewTradePassWord(contact);
	}

	@Override
	public PageList<CustEmergencyContact> selectLoginTimeByAccount(String account) {
		CustEmergencyContactMapper mapper = (CustEmergencyContactMapper) getDao();
		return mapper.selectLoginTimeByAccount(account);
	}

	@Override
	public int updateEmergencyContact(CustEmergencyContact contact) {
		CustEmergencyContactMapper mapper = (CustEmergencyContactMapper) getDao();
		return mapper.updateEmergencyContact(contact);
	}
}
