package com.yscf.core.util;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.dao.system.SysLogMapper;
import com.yscf.core.model.system.SysLog;
import com.yscf.core.service.system.ISysLogService;


public class LogRecordThread implements Runnable{

	private SysLog sysLog;
	
	private ISysLogService sysLogServiceImpl;
	
	private SysLogMapper logMapper;
	
	public LogRecordThread(SysLog log,ISysLogService sysLogServiceImpl){
		this.sysLog = log;
		this.sysLogServiceImpl = sysLogServiceImpl;
	}
	
	public LogRecordThread(SysLog log,SysLogMapper logMapper){
		this.sysLog = log;
		this.logMapper = logMapper;
	}
	
	@Override
	public void run() {
		try {
			if(null != logMapper){
				logMapper.insert(sysLog);
			}else{
				sysLogServiceImpl.insert(this.sysLog);
			}
		} catch (FrameworkException e) {
				e.printStackTrace();
		}
	}

}
