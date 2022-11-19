package com.yscf.core.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActActivity;

/**
 * 
 * @ClassName : ActActivityMapper
 * @Description : 活动信息数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 上午11:12:57
 */
public interface ActActivityMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(ActActivity record);

	public int insertSelective(ActActivity record);

	public ActActivity selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(ActActivity record);

	public int updateByPrimaryKey(ActActivity record);

	/**
	 * 
	 * @Description : 查询系统活动列表,带分页
	 * @param actActivity
	 *            系统活动对象
	 * @param info
	 *            分页对象
	 * @return 系统活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 下午3:44:33
	 */
	public PageList<ActActivity> selectAllPage(@Param("actActivity") ActActivity actActivity, PageInfo info);

	/**
	 * 
	 * @Description : 定时任务,查询当前时间前还没有开始的应该开始的活动
	 * @param startDate
	 *            当前时间
	 * @return 需要改变状态的活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月24日 下午4:15:32
	 */
	public List<ActActivity> selectActivityBeginTask(@Param("startDate") String startDate);

	/**
	 * 
	 * @Description : 定时任务,查询当前时间前还没有结束的应该结束的活动任务
	 * @param endDate
	 *            结束时间
	 * @return 需要改变状态的活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月26日 下午4:07:56
	 */
	public List<ActActivity> selectActivityEndTask(@Param("endDate") String endDate);

	/**
	 * 
	 * @Description : 根据活动类型和活动类型ID修改活动信息
	 * @param actActivity
	 *            活动对象
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午2:04:35
	 */
	public int updateByObjectIdAndActivityType(ActActivity actActivity);

	/**
	 * 
	 * @Description : 根据活动类型和活动类型那个ID查询出来活动ID
	 * @param objectId
	 *            活动对象信息的ID
	 * @param activityType
	 *            活动类型
	 * @return 活动ID
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午2:53:42
	 */
	public String selectByObjectIdAndActivityType(@Param("objectId") String objectId, @Param("activityType") String activityType);

	/**
	 * 
	 * Description：<br>
	 * 根据条件查询vip生日特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param actActivity
	 * @return
	 */
	public PageList<ActActivity> selectActActivityByParam(@Param("map") ActActivity actActivity, PageInfo info);

	public void deleteBatch(String[] str);

	/**
	 * 
	 * Description：<br>
	 * 根据活动pid和活动类型ID修改活动信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月15日
	 * @version v1.0.0
	 * @param actActivity
	 */
	public void updateByPidAndActivityType(ActActivity actActivity);

	/**
	 * 
	 * @Description : 批量修改活动的状态
	 * @param status
	 *            状态(-1 已删除 1 未开始 2 进行中 3过期 )
	 * @param pidArr
	 *            某一活动的ID数组
	 * @param activityType
	 *            活动类型
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 下午4:43:26
	 */
	public void updateStatusBatch(@Param("status") String status, @Param("pidArr") String[] pidArr, @Param("activityType") String activityType);

	/**
	 * 
	 * @Description : 根据状态查询当前活动的进行数
	 * @param status
	 *            活动状态
	 * @return 当前状态下的活动数量
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午4:28:48
	 */
	public int selectCountByStatus(@Param("status") String status);

	/**
	 * 
	 * @Description : 根据活动类型and活动类型ID查询活动ID
	 * @param activityType
	 *            活动类型
	 * @param objectId
	 *            活动类型ID
	 * @return 活动ID
	 * @Author : Qing.Cai
	 * @Date : 2015年11月26日 上午11:29:12
	 */
	public String selectActivityId(@Param("activityType") String activityType, @Param("objectId") String objectId);

	/**
	 * 
	 * @Description : 查询所有正在进行中的活动
	 * @return 进行中的活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午3:54:45
	 */
	public List<ActActivity> selectAllUnderwayActivity();

	/**
	 * Description：<br> 
	 * 根据objectId 活动	
	 * @author  shiliang.feng
	 * @date    2016年3月8日
	 * @version v1.0.0
	 * @param objectId
	 * @return
	 */
	public ActActivity selectActivityByObjectId(@Param("objectId")String objectId);
	/**
	 * Description：<br> 
	 * 根据activityType 查询有效的活动
	 * @author  shiliang.feng
	 * @date    2016年3月8日
	 * @version v1.0.0
	 * @param activityType
	 * @return
	 */
	public List<ActActivity> selectValidActByActType(@Param("activityType")String activityType);

}