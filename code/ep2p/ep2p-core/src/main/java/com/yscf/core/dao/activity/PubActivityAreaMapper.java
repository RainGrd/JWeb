package com.yscf.core.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubActivityArea;

/**
 * 
 * @ClassName : PubActivityAreaMapper
 * @Description : 活动专区展示数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年12月12日 下午3:02:59
 */
public interface PubActivityAreaMapper extends IBaseDao<BaseEntity, String> {
	int insert(PubActivityArea record);

	PubActivityArea selectByPrimaryKey(String pid);

	int insertSelective(PubActivityArea record);

	/**
	 * 
	 * @Description : 前台_查询初始化的活动专区显示
	 * @param pageSize
	 *            初始显示数量
	 * @return 活动专区初始显示列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午5:57:11
	 */
	public List<PubActivityArea> selectShowActicityArea(Integer pageSize);

	/**
	 * 
	 * @Description : 前台_查询加载的活动专区信息
	 * @param pageIndex
	 *            第几次加载
	 * @param pageSize
	 *            每次加载的数量
	 * @return 活动专区加载列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午5:57:57
	 */
	public List<PubActivityArea> selectLoadActicityArea(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

	/**
	 * 
	 * @Description : 修改活动专区
	 * @param pubActivityArea
	 *            活动专区对象
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月18日 下午3:01:48
	 */
	public int updatePubActivityArea(PubActivityArea pubActivityArea);

	/**
	 * 
	 * @Description : 修改活动导致活动专区信息改变
	 * @param pubActivityArea
	 *            活动专区对象
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 下午2:52:09
	 */
	public int updateActiviayByArea(PubActivityArea pubActivityArea);

	/**
	 * 
	 * @Description : 后台_分页查询活动专区列表
	 * @param pubActivityArea
	 *            活动专区对象
	 * @param info
	 *            分页对象
	 * @return 活动专区列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月16日 上午11:13:39
	 */
	public PageList<PubActivityArea> selectAllPage(@Param("pubActivityArea") PubActivityArea pubActivityArea, PageInfo info);

	/**
	 * 
	 * @Description : 根据活动类型and活动ID修改活动状态
	 * @param map
	 *            条件Map
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午10:45:02
	 */
	public void updateActivityStatus(Map<String, Object> map);

	/**
	 * 
	 * @Description : 修改是否展示
	 * @param pubActivityArea
	 *            活动专区对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午2:38:50
	 */
	public void updateIsShows(PubActivityArea pubActivityArea);

	/**
	 * 
	 * @Description : 删除活动专区数据
	 * @param pubActivityArea
	 *            活动专区对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午3:01:24
	 */
	public void deletePubActivityArea(PubActivityArea pubActivityArea);

	/**
	 * 
	 * @Description : 修改是否启用
	 * @param pubActivityArea
	 *            活动专区对象
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 上午10:42:56
	 */
	public void updateIsDisable(PubActivityArea pubActivityArea);

	/**
	 * 
	 * @Description : 检查当前编号是否存在
	 * @param activityCode
	 *            活动编号
	 * @return 数量
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 下午3:47:35
	 */
	public int checkCodeIsExistence(@Param("activityCode") String activityCode);

	/**
	 * 
	 * @Description : 查询当前最新的活动(根据类型)
	 * @param activityType
	 *            活动类型(1.抢红包 2.加息劵 99.其他)
	 * @return 最新的活动对象
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 上午10:17:50
	 */
	public PubActivityArea selectPubActivityByType(@Param("activityType") Integer activityType);

}