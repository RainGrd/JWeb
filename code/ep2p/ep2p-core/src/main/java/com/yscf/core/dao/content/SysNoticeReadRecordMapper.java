package com.yscf.core.dao.content;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.content.SysNoticeReadRecord;

/**
 * Description：<br>
 * 系统消息 阅读记录DAO
 * 
 * @author shiliang.feng
 * @date 2016年1月19日
 * @version v1.0.0
 */
@MapperScan("sysNoticeReadRecordMapper")
public interface SysNoticeReadRecordMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(SysNoticeReadRecord record);

	int insertSelective(SysNoticeReadRecord record);

	SysNoticeReadRecord selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysNoticeReadRecord record);

	int updateByPrimaryKey(SysNoticeReadRecord record);

	/**
	 * Description：<br>
	 * 用户阅读过的系统消息
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月20日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	public List<SysNoticeReadRecord> selectByCustomerId(String customerId);

	/**
	 * Description：<br>
	 * 批量插入
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月20日
	 * @version v1.0.0
	 * @param SysNoticeList
	 */
	public void batchInsert(List<SysNoticeReadRecord> SysNoticeList);
}