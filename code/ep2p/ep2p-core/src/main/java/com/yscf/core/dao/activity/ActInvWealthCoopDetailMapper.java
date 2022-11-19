package com.yscf.core.dao.activity;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActInvWealthCoopDetail;

/**
 * 
 * Description：<br>
 * 财富合伙人 参与明细
 * 
 * @author fengshiliang
 * @date 2015年10月22日
 * @version v1.0.0
 */
@MapperScan("actInvWealthCoopDetailMapper")
public interface ActInvWealthCoopDetailMapper extends
		IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(ActInvWealthCoopDetail record);

	public int insertSelective(ActInvWealthCoopDetail record);

	public ActInvWealthCoopDetail selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(ActInvWealthCoopDetail record);

	public int updateByPrimaryKey(ActInvWealthCoopDetail record);

	/**
	 * 
	 * Description：<br>
	 * 查看 获得投资奖励活动的用户
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actInvAwaActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvWealthCoopDetail> selectAllPageByCondition(
			@Param("map") ActInvWealthCoopDetail coopDetail, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 查看 获得投资奖励活动的用户 明细
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actInvAwaActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvWealthCoopDetail> selectAllPageDetail(
			@Param("map") ActInvWealthCoopDetail coopDetail, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 获得 投资合伙人奖励 参与人数总和
	 * 
	 * @author fengshiliang
	 * @date 2015年10月27日
	 * @version v1.0.0
	 * @return
	 */
	public int selectTotalCondition();

	/**
	 * 
	 * Description：<br>
	 * 导出记录，获取赠送财富明细
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月29日
	 * @version v1.0.0
	 * @param actInvWealthCoopDetail
	 * @param eom
	 * @return
	 */
	public List<ActInvWealthCoopDetail> selectActInvWealthCoopDetailVOEom(
			HashMap<String, Object> maps);

	/**
	 * Description：<br> 
	 * 批量插入财富合伙人 产于者
	 * @author  shiliang.feng
	 * @date    2016年3月10日
	 * @version v1.0.0
	 * @param listCusTomerDetail
	 */
	public void insertBatch(List<ActInvWealthCoopDetail> listCusTomerDetail);
}