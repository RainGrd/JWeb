package com.yscf.core.service.activity;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActInvWealthCoopDetail;
import com.yscf.core.model.comm.ExportObjectModel;

/**
 * Description：<br>
 * 客户积分页签明细服务接口
 * 
 * @author heng.wang
 * @date 2015年10月9日
 * @version v1.0.0
 */
public interface IActInvWealthCoopDetailService {
	/**
	 * 
	 * Description：<br>
	 * 获得 财富合伙人活动
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param ActInvWealthCoopDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvWealthCoopDetail> selectAllPageByCondition(
			ActInvWealthCoopDetail coopDetail, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 获得 财富合伙人活动 明细
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actExpActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvWealthCoopDetail> selectAllPageDetail(
			ActInvWealthCoopDetail coopDetail, PageInfo info); 
	
	/**
	 * 
	 * Description：<br> 
	 * 导出记录，获取赠送财富明细
	 * @author  JunJie.Liu
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param actInvWealthCoopDetail
	 * @param eom
	 * @return
	 */
	public List<ActInvWealthCoopDetail> selectActInvWealthCoopDetailVOEom(
			ActInvWealthCoopDetail actInvWealthCoopDetail, ExportObjectModel eom);

}
