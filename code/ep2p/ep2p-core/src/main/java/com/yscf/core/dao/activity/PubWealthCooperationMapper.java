package com.yscf.core.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubWealthCooperation;

public interface PubWealthCooperationMapper extends
		IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(PubWealthCooperation record);

	public int insertSelective(PubWealthCooperation record);

	public PubWealthCooperation selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(PubWealthCooperation record);

	public int updateByPrimaryKey(PubWealthCooperation record);

	/**
	 * 
	 * @Description : 查询体验金信息列表,带分页
	 * @param pubExpGold
	 *            体验金信息对象
	 * @param info
	 *            分页对象
	 * @return 体验金信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:16:13
	 */
	public PageList<PubWealthCooperation> selectAllPage(
			@Param("map") PubWealthCooperation pubWealthCoop, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除体验金活动
	 * @param pids
	 *            体验金ID数组
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:58:20
	 */
	public void deleteBatch(String[] pids);
	/**
	 * Description：<br> 
	 * 修改财富合伙人 状态
	 * @author  shiliang.feng
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param pid 活动id
	 * @param status 活动状态
	 */
	public void updateActiivtyStatus(@Param("pid")String pid,@Param("status") String status);

	/**
	 * Description：<br> 
	 * 是否有 进行中的活动
	 * @author  shiliang.feng
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @return
	 */
	public List<PubWealthCooperation> selectActivateWealthCoop();
}