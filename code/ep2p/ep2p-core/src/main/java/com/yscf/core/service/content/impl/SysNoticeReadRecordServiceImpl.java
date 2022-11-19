/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月19日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.content.SysNoticeReadRecordMapper;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.SysNoticeReadRecord;
import com.yscf.core.service.content.ISysNoticeReadRecordService;

/**
 * Description：<br>
 * 系统公告 阅读记录表
 * 
 * @author shiliang.feng
 * @date 2016年1月19日
 * @version v1.0.0
 */
@Service("noticeReadRecordServiceImpl")
public class SysNoticeReadRecordServiceImpl extends
		BaseService<BaseEntity, String> implements ISysNoticeReadRecordService {

	@Autowired
	public SysNoticeReadRecordServiceImpl(SysNoticeReadRecordMapper dao) {
		super(dao);
	}

	@Resource
	private SysNoticeReadRecordMapper noticeReadRecordMapper;

	@Resource
	private ColumnContentServiceImpl columnContentServiceImpl;

	@Override
	public int insertSelective(SysNoticeReadRecord noticeReadRecord) {
		return noticeReadRecordMapper.insertSelective(noticeReadRecord);
	}

	@Override
	public Boolean readAllMessage(String userid) {
		// 用户阅读过的系统消息
		List<SysNoticeReadRecord> sysNoticeList = noticeReadRecordMapper
				.selectByCustomerId(userid);
		// 所有已上架的系统消息
		List<ColumnContent> contentList = columnContentServiceImpl
				.selectColContentByWebTagSpecial("P2P_SYS_NOTICE", null, null);
		StringBuffer contentIds = new StringBuffer();
		for (ColumnContent columnContent : contentList) {
			contentIds.append(columnContent + ",");
		}
		System.out.println("所有已上架的系统消息>>>>>>" + contentIds.toString());

		List<SysNoticeReadRecord> batchInsert = new ArrayList<SysNoticeReadRecord>();
		// 用户没有阅读过系统公告
		if (sysNoticeList.size() <= 0) {
			for (ColumnContent columnContent : contentList) {
				SysNoticeReadRecord sysNotice = new SysNoticeReadRecord();
				sysNotice.setPid(sysNotice.getPK());
				sysNotice.setCustomerId(userid);
				sysNotice.setColumnContentId(columnContent.getPid());
				sysNotice.setCreateUser(userid);
				sysNotice.setCreateTime(DateUtil.getToday());
				sysNotice.setStatus("1");
				batchInsert.add(sysNotice);
			}
		} else {
			// 如果用户阅读的系统公告数量 = 已上架的系统消息 判定为用户已经全部阅读了系统消息
			if (sysNoticeList.size() == contentList.size()) {
				return true;
			}
			List<String> readed = new ArrayList<String>();
			for (SysNoticeReadRecord sysNoticeReadRecord : sysNoticeList) {
				readed.add(sysNoticeReadRecord.getColumnContentId());
			}
			for (ColumnContent columnContent : contentList) {
				if (readed.contains(columnContent.getPid())) {
					continue;
				}
				SysNoticeReadRecord sysNotice = new SysNoticeReadRecord();
				sysNotice.setPid(sysNotice.getPK());
				sysNotice.setCustomerId(userid);
				sysNotice.setColumnContentId(columnContent.getPid());
				sysNotice.setCreateUser(userid);
				sysNotice.setCreateTime(DateUtil.getToday());
				sysNotice.setStatus("1");
				batchInsert.add(sysNotice);
			}
		}
		if (batchInsert.size() <= 0) {
			return true;
		}
		noticeReadRecordMapper.batchInsert(batchInsert);
		return true;
	}
}
