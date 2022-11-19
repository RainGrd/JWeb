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
package com.yscf.core.service.content;

import com.yscf.core.model.content.SysNoticeReadRecord;

/**
 * Description：<br>
 * 系统消息 阅读记录 服务
 * 
 * @author shiliang.feng
 * @date 2016年1月19日
 * @version v1.0.0
 */
public interface ISysNoticeReadRecordService {
	/**
	 * Description：<br>
	 * 单条已读
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月19日
	 * @version v1.0.0
	 * @param noticeReadRecord
	 * @return
	 */
	public int insertSelective(SysNoticeReadRecord noticeReadRecord);

	/**
	 * Description：<br>
	 * 批量已读 先查询表中未读的数据 在批量插入
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月19日
	 * @version v1.0.0
	 * @param userid
	 * @return
	 */
	public Boolean readAllMessage(String userid);
}
