package com.yscf.core.dao.sms;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.sms.SmsRecordsCustomer;

/**
 * Description：<br> 
 * 消息和人员信息服务层
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@MapperScan("smsRecordsRustomerMapper")
public interface SmsRecordsCustomerMapper extends IBaseDao<BaseEntity, String> {
	
    int insert(SmsRecordsCustomer record);
    
    int insertSelective(SmsRecordsCustomer record);

	Integer selectTodayCountByMobiles(String mobiles);
    
}