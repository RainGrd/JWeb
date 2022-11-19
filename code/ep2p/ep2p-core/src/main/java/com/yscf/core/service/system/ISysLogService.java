/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户日志管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysLog;

/**
 * Description：<br> 
 * 日志管理服务接口
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
public interface ISysLogService {
	/**
	 * Description：新增系统用户日志
	 * @author  Allen
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(SysLog record) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * 新增系统日志   线程
	 * @author  JingYu.Dai
	 * @date    2016年1月14日
	 * @version v1.0.0
	 * @param sysLog 日志对象
	 * @throws FrameworkException
	 */
	public void addLog(SysLog sysLog) throws FrameworkException;
	
	/**
	 * Description：查询用户日志列表
	 * @author  Allen
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param sysLog 系统用户日志
	 * @return List<SysLog>
	 * @throws FrameworkException
	 */
	List<SysLog> selectAll(SysLog sysLog, PageInfo pageInfo);
	/**
	 * Description：批量删除用户日志列表
	 * @author  heng.wang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param String pids 系统用户日志
	 * @return int
	 * @throws FrameworkException
	 */
	public int deleteBtach(String pids);
	/**
	 * Description：查询用户日志列表，带分页功能
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param SysLog sysLog 系统用户日志
	 * @return PageList<SysLog>
	 * @throws FrameworkException
	 */
	public PageList<SysLog> selectAllPage(SysLog sysLog, PageInfo info) ;
		
}


