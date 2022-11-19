package com.yscf.core.dao.task;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.task.ScheduleJob;

/**
 * 
 * Description：<br> 
 * 定时任务数据交互类
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
@MapperScan("scheduleJobMapper")
public interface ScheduleJobMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(ScheduleJob record);

    int insertSelective(ScheduleJob record);

    ScheduleJob selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(ScheduleJob record);

    int updateByPrimaryKey(ScheduleJob record);

    /**
     * 
     * Description：<br> 
     * 获取所有有效数据
     * @author  Yu.Zhang
     * @date    2015年12月22日
     * @version v1.0.0
     * @return
     */
	List<ScheduleJob> selectAll();

	/**
	 * 
	 * Description：<br> 
	 * 查询所有数据，带分页
	 * @author  Yu.Zhang
	 * @date    2015年12月22日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @param info
	 * @return
	 */
	PageList<ScheduleJob> selectAllPage(@Param("map") ScheduleJob scheduleJob, PageInfo info);

	/**
	 * 
	 * Description：<br> 
	 * 根据任务名，组名查询
	 * @author  Yu.Zhang
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param scheduleJob
	 * @return
	 */
	ScheduleJob selectByJobName(ScheduleJob scheduleJob);
}