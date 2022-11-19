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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.task.ScheduleJobMapper;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.task.IScheduleJobService;

/**
 * Description：<br> 
 * 定时任务业务数据实现类
 * @author  Yu.Zhang
 * @date    2015年12月22日
 * @version v1.0.0
 */
@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends BaseService<BaseEntity, String>  implements IScheduleJobService {

	@Autowired
	public ScheduleJobServiceImpl(ScheduleJobMapper dao) {
		super(dao);
	}

	public int insertSelective(ScheduleJob record) {
		record.setPid(record.getPK());
		record.setStatus(Constant.ACTIVATE);
		record.setJobStatus(Constant.JOB_STATUS_1);
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		return mapper.insertSelective(record);
	}

	@Override
	public ScheduleJob selectByPrimaryKey(String pid) {
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		return mapper.selectByPrimaryKey(pid);
	}

	public int updateByPrimaryKeySelective(ScheduleJob record) {
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ScheduleJob> selectAll() {
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		return mapper.selectAll();
	}

	public PageList<ScheduleJob> selectAllPage(ScheduleJob scheduleJob,PageInfo info) {
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		return mapper.selectAllPage(scheduleJob,info);
	}

	public ScheduleJob selectByJobName(ScheduleJob scheduleJob) {
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		return mapper.selectByJobName(scheduleJob);
	}

	@Override
	public int updateJobStaus(String pid,String jobStatus) {
		ScheduleJobMapper mapper = (ScheduleJobMapper) getDao();
		ScheduleJob job = new ScheduleJob();
		job.setPid(pid);
		job.setJobStatus(jobStatus);
		job.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
		return mapper.updateByPrimaryKeySelective(job);
	}

}


