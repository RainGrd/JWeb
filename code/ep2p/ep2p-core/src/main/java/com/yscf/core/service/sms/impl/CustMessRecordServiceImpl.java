package com.yscf.core.service.sms.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.sms.CustMessRecordMapper;
import com.yscf.core.model.sms.CustMessRecord;
import com.yscf.core.service.sms.ICustMessRecord;

/**
 * Description：<br> 
 * 客户 系统消息
 * @author  heng.wang
 * @date    2015年12月09日
 * @version v1.0.0
 */
@Service("custMessRecordService")
public class CustMessRecordServiceImpl extends BaseService<BaseEntity, String> implements ICustMessRecord {

	@Autowired
	public CustMessRecordServiceImpl(CustMessRecordMapper dao) {
		super(dao);
	}
	
	@Override
	public int insertSelective(CustMessRecord record) {
		return ((CustMessRecordMapper) getDao()).insertSelective(record);
	}

	@Override
	public List<CustMessRecord> selectMsgList(CustMessRecord custMessRecord,Integer pageIndex,Integer pageSize) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		//格式化时间
//		String beginTime = EscfDateUtil.formatterDate(custMessRecord.getBeginTime(), 1);
		String beginTime = custMessRecord.getBeginTime();
		String endTime = EscfDateUtil.formatterDate(custMessRecord.getEndTime(),2);
		custMessRecord.setBeginTime(beginTime);
		custMessRecord.setEndTime(endTime);
		map.put("custMessRecord", custMessRecord);
		if("".equals(pageIndex) || pageIndex==null){
			pageIndex=0;
		}
		if("".equals(pageSize) || pageSize==null){
			pageSize=10;
		}
		map.put("pageIndex", pageIndex);
		map.put("pageSize", pageSize);
		return mapper.selectMsgList(map);
	}

	@Override
	public int selectTotalMsgList(CustMessRecord custMessRecord) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		//格式化时间
		String beginTime = EscfDateUtil.formatterDate(custMessRecord.getBeginTime(), 1);
		String endTime = EscfDateUtil.formatterDate(custMessRecord.getEndTime(),2);
		custMessRecord.setBeginTime(beginTime);
		custMessRecord.setEndTime(endTime);
		map.put("custMessRecord", custMessRecord);
		return mapper.selectTotalMsgList(map);
	}

	@Override
	public int batchUpdateMarkReadByPid(String pid) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idItem", pid.split(","));
		return mapper.batchUpdateMarkReadByPid(map);
	}

	@Override
	public List<CustMessRecord> selectMsgListApi(CustMessRecord custMessRecord) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		return mapper.selectMsgListApi(custMessRecord);
	}

	@Override
	public int selectUnreadNumber(String userId) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		return mapper.selectUnreadNumber(userId);
	}

	@Override
	public int signMessageRead(String userId, String[] str) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("idItem", str);
		return mapper.signMessageRead(map);
	}

	@Override
	public int signMessageAllRead(String userId) {
		CustMessRecordMapper mapper = (CustMessRecordMapper) getDao();
		return mapper.signMessageAllRead(userId);
	}

}
