package com.yscf.core.dao.task;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.task.ScheduleJobLog;

/**
 * 
 * Description：<br> 
 * 定时任务日志数据交互类
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
@MapperScan("scheduleJobLogMapper")
public interface ScheduleJobLogMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(ScheduleJobLog record);

    int insertSelective(ScheduleJobLog record);

    ScheduleJobLog selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(ScheduleJobLog record);

    int updateByPrimaryKey(ScheduleJobLog record);

	PageList<ScheduleJobLog> selectAllPage(@Param("map") ScheduleJobLog jobLog, PageInfo info);
}