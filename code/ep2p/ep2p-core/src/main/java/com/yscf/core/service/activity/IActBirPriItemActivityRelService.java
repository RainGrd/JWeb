/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 *  
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月13日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;

/**
 * Description：<br>
 * 生日特权项和活动关系表 服务接口
 * 
 * @author fengshiliang
 * @date 2015年10月13日
 * @version v1.0.0
 */
public interface IActBirPriItemActivityRelService {

	/**
	 * 
	 * Description：<br>
	 * 查询 t_pub_jiaxi_ticket 、t_pub_invest_award 、t_pub_redpacket t_pub_exp_gold
	 * 、t_pub_jingyan_ticket 、t_pub_point_ticket 表内特权是vip特权生日 并且当前时间还在适用时间内的特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @return
	 */
	public List<ActBirPriItemActivityRel> selectPrivilege();

	/**
	 * 
	 * Description：<br>
	 * VIP生日特权活动查询
	 * 
	 * @author fengshiliang
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param info
	 * @return
	 */
	public PageList<ActBirPriItemActivityRel> selectBirPriItem(
			ActBirPriItemActivityRel activityRel, PageInfo info);

	/**
	 * Description：<br>
	 * 根据活动id 找到，特权和活动关联实体
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param activityId
	 * @return
	 */
	public List<ActBirPriItemActivityRel> selectBirPriRelByActivityId(
			String activityId);
}
