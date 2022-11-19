/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 消息和人员关系服
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.sms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.sms.SmsRecordsCustomerMapper;
import com.yscf.core.model.sms.SmsRecordsCustomer;
import com.yscf.core.service.sms.ISmsRecordsCustomerService;

/**
 * Description：<br> 
 * 消息和人员关系服
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@Service("smsRecordsCustomerService")
public class SmsRecordsCustomerServiceImpl extends BaseService<BaseEntity, String> implements 
	ISmsRecordsCustomerService{

	@Autowired
	public SmsRecordsCustomerServiceImpl(SmsRecordsCustomerMapper dao) {
		super(dao);
	}

	@Override
	public int insertSelective(SmsRecordsCustomer record) {
		return ((SmsRecordsCustomerMapper) getDao()).insertSelective(record);
	}

	
}


