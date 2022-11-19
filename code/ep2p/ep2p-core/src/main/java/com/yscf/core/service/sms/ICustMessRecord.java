package com.yscf.core.service.sms;

import java.util.HashMap;
import java.util.List;

import com.yscf.core.model.sms.CustMessRecord;
/**
 * Description：<br> 
 * 客户消息记录接口
 * @author  heng.wang
 * @date    2015年12月09日
 * @version v1.0.0
 */
public interface ICustMessRecord {
	/**
	 * Description：<br> 
	 * 插入传入的参数信息
	 * @author  heng.wang
	 * @date    2015年12月9日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int insertSelective(CustMessRecord record);
	/**
	 * Description：<br> 
	 * 查询消息中心列表数据
	 * @author  heng.wang
	 * @date    2015年12月9日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<CustMessRecord> selectMsgList(CustMessRecord custMessRecord,Integer pageIndex,Integer pageSize); 
	/**
	 * Description：<br> 
	 * 查询消息中心列表数据总数
	 * @author  heng.wang
	 * @date    2015年12月9日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int selectTotalMsgList(CustMessRecord custMessRecord);
	/**
	 * Description：<br> 
	 * 批量标记为已读
	 * @author  heng.wang
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int batchUpdateMarkReadByPid(String pid);
	
	/**
	 * Description：<br> 
	 * api：查询消息中心列表
	 * @author  heng.wang
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<CustMessRecord> selectMsgListApi(CustMessRecord custMessRecord);
	
	/**
	 * Description：<br> 
	 * api：查询未读消息数量
	 * @author  heng.wang
	 * @date    2016年1月16日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int selectUnreadNumber(String userId);
	
	/**
	 * Description：<br> 
	 * api：标记选择消息为已读
	 * @author  heng.wang
	 * @date    2016年1月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int signMessageRead(String userId,String str[]);
	
	/**
	 * Description：<br> 
	 * api：标记消息为全部已读
	 * @author  heng.wang
	 * @date    2016年2月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int signMessageAllRead(String userId);
}
