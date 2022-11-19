/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 消息记录服务接口
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
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.sms.SmsRecordsModelMapper;
import com.yscf.core.model.sms.SmsRecordsModel;
import com.yscf.core.service.sms.ISmsRecordsService;

/**
 * Description：<br> 
 * 消息记录服务
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@Service("smsRecordsService")
public class SmsRecordsServiceImpl extends BaseService<BaseEntity, String> implements 
	ISmsRecordsService {

	@Autowired
	public SmsRecordsServiceImpl(SmsRecordsModelMapper dao) {
		super(dao);
	}

	
	public Integer selectTodayCountByMobiles(String mobiles) {
		SmsRecordsModelMapper mapper = (SmsRecordsModelMapper) getDao();
		SmsRecordsModel model = new SmsRecordsModel();
		model.setCreateTime(DateUtil.format(DateUtil.getToday(), "yyyy-MM-dd"));
		model.setPhoneNo(mobiles);
		return mapper.selectTodayCountByMobiles(model);
	}

}


