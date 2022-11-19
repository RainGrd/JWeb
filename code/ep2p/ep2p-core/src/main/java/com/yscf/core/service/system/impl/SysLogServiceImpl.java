/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.google.common.collect.Lists;
import com.yscf.core.dao.system.SysLogMapper;
import com.yscf.core.model.system.SysLog;
import com.yscf.core.service.system.ISysLogService;
import com.yscf.core.util.LogRecordThread;

/**
 * Description：<br> 
 * 系统日志管理服务实现
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
@Service("sysLogService")
public class SysLogServiceImpl extends BaseService<BaseEntity, String> implements
		ISysLogService {
	
	public static ExecutorService pool = Executors.newFixedThreadPool(5);

	@Autowired
	public SysLogServiceImpl(SysLogMapper dao) {
		super(dao);
	}

	@Override
	public int insert(SysLog log) throws FrameworkException {
		SysLogMapper dao = (SysLogMapper) getDao();
		return dao.insert(log);
	}
	
	@Override
	public void addLog(SysLog log) throws FrameworkException {
		SysLogMapper dao = (SysLogMapper) getDao();
		LogRecordThread thread = new LogRecordThread(log,dao);
		pool.submit(thread);
	}

	@Override
	public List<SysLog> selectAll(SysLog sysLog, PageInfo pageInfo) {
		SysLogMapper mapper = (SysLogMapper) getDao();
		return mapper.selectAll(sysLog);
	}
	@Override
	public PageList<SysLog> selectAllPage(SysLog sysLog, PageInfo info) {
		SysLogMapper mapper = (SysLogMapper) getDao();
		
		return mapper.selectAllPage(sysLog,info);
	}
	
	@Override
	public int deleteByPrimaryKey(String pids){
		SysLogMapper mapper = (SysLogMapper) getDao();
		String [] arr = pids.split(",");
		List<String> list = Lists.newArrayList();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
		return mapper.deleteByPrimaryKey(pids);
		
	}

	@Override
	public int deleteBtach(String pids) {
		SysLogMapper mapper = (SysLogMapper) getDao();
		String [] arr = pids.split(",");
		List<String> list = Lists.newArrayList();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
		return mapper.deleteBtach(list);
	}
}


