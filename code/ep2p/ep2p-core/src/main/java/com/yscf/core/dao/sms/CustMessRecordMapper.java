package com.yscf.core.dao.sms;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.sms.CustMessRecord;

public interface CustMessRecordMapper extends IBaseDao<BaseEntity, String> {
    int insert(CustMessRecord record);

    int insertSelective(CustMessRecord record);
    /**
     * Description：<br> 
     * 查询消息中心数据列表
     * @author  heng.wang
     * @date    2015年12月09日
     * @version v1.0.0
     */
    public List<CustMessRecord> selectMsgList(Map<String, Object> map);
    /**
     * Description：<br> 
     * 查询消息中心数据列表总数
     * @author  heng.wang
     * @date    2015年12月09日
     * @version v1.0.0
     */
    public int selectTotalMsgList(Map<String, Object> map);
    /**
     * Description：<br> 
     * 消息中心：根据id批量标记为已读状态
     * @author  heng.wang
     * @date    2015年12月10日
     * @version v1.0.0
     */
    public int batchUpdateMarkReadByPid(@Param("map") Map<String, Object> map);
    
    /**
     * Description：<br> 
     * 查询消息中心列表
     * @author  heng.wang
     * @date    2015年12月30日
     * @version v1.0.0
     */
    public List<CustMessRecord> selectMsgListApi(@Param("custMessRecord")CustMessRecord custMessRecord);
    
    /**
     * Description：<br> 
     * 查询是否有未读消息
     * @author  heng.wang
     * @date    2016年1月16日
     * @version v1.0.0
     */
    public int  selectUnreadNumber(String userId);
    
    /**
     * Description：<br> 
     * 标记消息单条为已读
     * @author  heng.wang
     * @date    2016年1月16日
     * @version v1.0.0
     */
    public int signMessageRead(@Param("map") Map<String, Object> map);
    
    /**
     * Description：<br> 
     * 标记消息为全部已读
     * @author  heng.wang
     * @date    2016年2月22日
     * @version v1.0.0
     */
    public int signMessageAllRead(String userId);
    
    
}