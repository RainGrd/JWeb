/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.yscf.core.model.system.SysDistionary;


/**
 * Description：<br> 
 * 数据字典业务类服务接口
 * @author  Simon.Hoo
 * @date    2015年9月6日
 * @version v1.0.0
 */
public interface ISysDistionaryService {
	
	/**
	 * 
	 * Description：<br> 
	 * 分页查询
	 * @author  Yu.Zhang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param sysDistionary
	 * @param info
	 * @return
	 */
	public List<SysDistionary> selectAllPage(SysDistionary sysDistionary, PageInfo info);

	/**
	 * 
	 * Description：<br> 
	 * 数据字典数据查询
	 * @author  Yu.Zhang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param dis
	 * @return
	 */
	public List<SysDistionary> selectAll(SysDistionary dis);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新状态
	 * @author  Yu.Zhang
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param map status,pids 
	 * @return 成功更新数据条数
	 */
	public int updateStatusBatch(SysDistionary dis);
	
	/**
	 * 
	 * Description：<br> 
	 * 动态更新数据
	 * @author  Yu.Zhang
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @param sysDistionary
	 * @return
	 */
	public int updateByPrimaryKeySelective(SysDistionary sysDistionary);
}


