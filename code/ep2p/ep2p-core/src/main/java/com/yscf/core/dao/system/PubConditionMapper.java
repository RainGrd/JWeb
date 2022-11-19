package com.yscf.core.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.PubCondition;

/**
 * 
 * @ClassName : PubConditionMapper
 * @Description : 条件信息表Mapper接口
 * @Author : Qing.Cai
 * @Date : 2015年9月22日 下午2:48:06
 */
public interface PubConditionMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(PubCondition record);

	int insertSelective(PubCondition record);

	PubCondition selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(PubCondition record);

	int updateByPrimaryKey(PubCondition record);

	/**
	 * 
	 * @Description : 查询所有有效的条件信息
	 * @return 有效的条件信息list
	 * @Author : Qing.Cai
	 * @Date : 2015年9月22日 下午3:10:20
	 */
	public List<PubCondition> selectPubConditionBySatusEffective();

	/**
	 * 
	 * @Description : 查询条件信息,带分业
	 * @param pubCondition
	 *            条件信息对象
	 * @param info
	 *            分页对象
	 * @return 条件信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:40:02
	 */
	public PageList<PubCondition> selectAllPage(@Param("pubCondition") PubCondition pubCondition, PageInfo info);

	/**
	 * 
	 * @Description : 批量删除条件信息
	 * @param pids
	 *            条件信息ID数组(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午11:22:20
	 */
	public void deleteBatch(String[] pids);

	/**
	 * 
	 * @Description : 根据活动ID查询条件信息
	 * @param activityId
	 *            活动ID
	 * @return 条件信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午4:33:30
	 */
	public List<PubCondition> selectAllByActivityId(@Param("activityId")String activityId);

	/**
	 * 
	 * Description：<br> 
	 * 查询借款条件集合
	 * @author  JunJie.Liu
	 * @date    2016年2月25日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<PubCondition> findListByBorrowId(@Param("borrowId") String borrowId);
}