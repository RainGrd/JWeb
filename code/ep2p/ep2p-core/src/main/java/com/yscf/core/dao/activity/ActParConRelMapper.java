package com.yscf.core.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.activity.ActParConRel;

/**
 * 
 * @ClassName : ActParConRelMapper
 * @Description : 活动参与条件关系表Mapper接口
 * @Author : Qing.Cai
 * @Date : 2015年9月22日 下午2:48:28
 */
public interface ActParConRelMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(ActParConRel record);

	int insertSelective(ActParConRel record);

	ActParConRel selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(ActParConRel record);

	int updateByPrimaryKey(ActParConRel record);

	/**
	 * 
	 * @Description : 根据活动ID查询活动已有条件信息
	 * @param objectId
	 *            某个类型活动ID
	 * @param activityType
	 *            活动类型
	 * @return 已选条件列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:53:51
	 */
	List<ActParConRel> selectActParConRelByObjectId(@Param("objectId")String objectId,@Param("activityType")String activityType);
	

	/**
	 * 
	 * @Description : 根绝活动类型和活动类型ID删除活动条件信息
	 * @param objectId
	 *            活动类型ID
	 * @param activityType
	 *            活动类型
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午2:36:45
	 */
	int deleteByObjectIdAndActivityType(@Param("objectId")String objectId,@Param("activityType")String activityType);
}