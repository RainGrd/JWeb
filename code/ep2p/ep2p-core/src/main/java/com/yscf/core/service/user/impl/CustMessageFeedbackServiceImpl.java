package com.yscf.core.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.user.CustMessageFeedbackMapper;
import com.yscf.core.model.user.CustMessageFeedback;
import com.yscf.core.service.user.ICustMessageFeedbackService;
@Service("custMessageFeedbackService")
public class CustMessageFeedbackServiceImpl extends BaseService<BaseEntity, String> 
				implements ICustMessageFeedbackService{

	@Autowired
	public CustMessageFeedbackServiceImpl(CustMessageFeedbackMapper dao) {
		super(dao);
	}

	@Override
	public int insertSelective(CustMessageFeedback custMessageFeedback)
			throws FrameworkException {
		CustMessageFeedbackMapper custMessageFeedbackMapper=(CustMessageFeedbackMapper)getDao();
		return custMessageFeedbackMapper.insertSelective(custMessageFeedback);
	}

}
