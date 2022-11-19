/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.task.ScheduleJobLogMapper;
import com.yscf.core.model.task.ScheduleJobLog;
import com.yscf.core.service.task.IScheduleJobServiceLog;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
@Service("scheduleJobServiceLog")
public class ScheduleJobServiceLogImpl extends BaseService<BaseEntity, String> implements IScheduleJobServiceLog {

	@Autowired
	public ScheduleJobServiceLogImpl(ScheduleJobLogMapper dao) {
		super(dao);
	}

	public int insertSelective(ScheduleJobLog record) {
		record.setPid(record.getPK());
		ScheduleJobLogMapper mapper = (ScheduleJobLogMapper) getDao();
		return mapper.insertSelective(record);
	}

	@Override
	public ScheduleJobLog selectByPrimaryKey(String pid) {
		ScheduleJobLogMapper mapper = (ScheduleJobLogMapper) getDao();
		return mapper.selectByPrimaryKey(pid);
	}

	public int updateByPrimaryKeySelective(ScheduleJobLog record) {
		ScheduleJobLogMapper mapper = (ScheduleJobLogMapper) getDao();
		return mapper.updateByPrimaryKeySelective(record);
	}

	public PageList<ScheduleJobLog> selectAllPage(ScheduleJobLog jobLog,
			PageInfo info) {
		ScheduleJobLogMapper mapper = (ScheduleJobLogMapper) getDao();
		return mapper.selectAllPage(jobLog,info);
	}

}


