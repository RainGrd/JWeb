package com.yscf.core.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;

/**
 * 
 * Description：<br>
 * 生日特权项和活动关系表 dao
 * 
 * @author fengshiliang
 * @date 2015年10月13日
 * @version v1.0.0
 */
public interface ActBirPriItemActivityRelMapper extends
		IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(ActBirPriItemActivityRel record);

	public int insertSelective(ActBirPriItemActivityRel record);

	public ActBirPriItemActivityRel selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(ActBirPriItemActivityRel record);

	public int updateByPrimaryKey(ActBirPriItemActivityRel record);

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
	 * 根据活动id 和 活动类型 删除
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param pid
	 * @param string
	 */
	public void deleteByObjectIdAndActivityType(String activityId);

	/**
	 * 
	 * Description：<br>
	 * 根据活动ID查询活动已有特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月15日
	 * @version v1.0.0
	 * @param objectId
	 * @param activityType
	 * @return
	 */
	public List<ActBirPriItemActivityRel> selectBirPriByObjectId(String objectId);

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
			@Param("map") ActBirPriItemActivityRel activityRel, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 参与 vip生日特权活动总人数
	 * 
	 * @author fengshiliang
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param info
	 * @return
	 */
	public int selectTotalCondition();

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