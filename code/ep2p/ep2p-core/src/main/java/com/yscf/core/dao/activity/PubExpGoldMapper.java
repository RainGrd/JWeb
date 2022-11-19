package com.yscf.core.dao.activity;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubExpGold;

/**
 * 
 * @ClassName : PubExpGoldMapper
 * @Description : 体验金信息数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午2:42:30
 */
public interface PubExpGoldMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(PubExpGold record);

	int insertSelective(PubExpGold record);

	PubExpGold selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(PubExpGold record);

	int updateByPrimaryKey(PubExpGold record);

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
	PageList<PubExpGold> selectAllPage(@Param("pubExpGold") PubExpGold pubExpGold, PageInfo info);

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
	 * 
	 * Description：<br>
	 * 根据活动id 查询体验金信息
	 * 
	 * @author fengshiliang
	 * @date 2015年11月10日
	 * @version v1.0.0
	 * @param activityId
	 * @return
	 */
	public PubExpGold selectByActivityId(String activityId);

	/**
	 * 
	 * @Description : 修改活动的状态
	 * @param pid
	 *            　活动ＩＤ
	 * @param stastus
	 *            　状态
	 * @Author : Qing.Cai
	 * @Date : 2015年12月26日 下午2:57:52
	 */
	public void updateActiivtyStatus(@Param("pid") String pid, @Param("status") String status);
}