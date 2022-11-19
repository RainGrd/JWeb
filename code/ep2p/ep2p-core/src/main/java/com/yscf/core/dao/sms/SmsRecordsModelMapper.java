package com.yscf.core.dao.sms;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.sms.SmsRecordsModel;

/**
 * Description：<br> 
 * 发送消息记录信息Mapper
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@MapperScan("smsRecordsModelMapper")
public interface SmsRecordsModelMapper extends IBaseDao<BaseEntity, String> {
    
	int insert(SmsRecordsModel record);

    int insertSelective(SmsRecordsModel record);

	Integer selectTodayCountByMobiles(SmsRecordsModel record);
    
}