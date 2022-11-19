package com.yscf.core.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubInvestAward;

/**
 * 
 * @ClassName : PubInvestAwardMapper
 * @Description : 投资奖励信息表数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月14日 上午11:06:18
 */
public interface PubInvestAwardMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(PubInvestAward record);

	int insertSelective(PubInvestAward record);

	PubInvestAward selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(PubInvestAward record);

	int updateByPrimaryKey(PubInvestAward record);

	/**
	 * 
	 * @Description : 查询投资奖励信息列表,带分页
	 * @param pubInvestAward
	 *            投资奖励信息对象
	 * @param info
	 *            分页对象
	 * @return 投资奖励信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月14日 上午11:12:13
	 */
	PageList<PubInvestAward> selectAllPage(@Param("pubInvestAward") PubInvestAward pubInvestAward, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除投资奖励活动
	 * @param pids
	 *            投资奖励ID数组
	 * @Author : Qing.Cai
	 * @Date : 2015年10月14日 上午11:10:51
	 */
	public void deleteBatch(String[] pids);

	/**
	 * 查询我的赠劵
	 * @param map
	 * @return
	 */
	public List<PubInvestAward> selectMyCoupon(@Param("map") Map<String, Object> map);

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
	
	/**
	 * 查询我的赠劵总记录数
	 * @param map
	 * @return
	 */
	public Integer selectMyCouponCount(String customerId);
	
	/**
	 * 查询已过期的赠劵
	 * @param map
	 * @return
	 */
	public List<PubInvestAward> selectMyCouponExpired(@Param("map") Map<String, Object> map);
	
	/**
	 * 查询我已过期赠劵总记录数
	 * @param map
	 * @return
	 */
	public Integer selectMyCouponExpiredCount(String customerId);
}