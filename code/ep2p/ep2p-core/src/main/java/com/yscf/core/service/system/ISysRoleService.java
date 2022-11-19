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
 * 2015年9月10日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysRole;


/**
 * Description：系统角色管理接口
 * @author  JingYu.Dai
 * @date    2015年9月10日
 * @version v1.0.0
 */
public interface ISysRoleService {
	
	/**
	 * Description：查询系统角色列表
	 * @author  JingYu.Dai
	 * @date    2015年9月10日
	 * @version v1.0.0
	 * @param userName 用户名称
	 * @return List<SysRole>
	 * @throws FrameworkException
	 */
	List<SysRole> querySysRoleByUserId(String userId) throws FrameworkException;
	
	/**
	 * Description：有选择的进行查询      根据角色对象role的属性进行查询 
	 * @author  JingYu.Dai
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @param role
	 * @return List<SysRole>
	 * @throws FrameworkException
	 */
	List<SysRole> selectSelective(SysRole role) throws FrameworkException;
	
	/**
	 * Description：新增或修改角色
	 * @author  JingYu.Dai
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @param role
	 * @return int
	 * @throws FrameworkException
	 */
	int addOrUpdate(SysRole role) throws FrameworkException;
	
	/**
	 * Description：有选择的进行查询 分页      根据角色对象role的属性进行查询 
	 * @author  JingYu.Dai
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @param role
	 * @return PageList<SysRole>
	 * @throws FrameworkException
	 */
	PageList<SysRole> selectSelectivePage(SysRole role,PageInfo info) throws FrameworkException;
	
	/**
	 * Description：批量删除角色 根据pid 集合
	 * @author  JingYu.Dai
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param pIds pid字符串 多个已  英文逗号（,）隔开
	 * @return int 受影响记录条数
	 * @throws FrameworkException
	 */
	int deleteBtach(String pIds)throws FrameworkException;
	
	/**
	 * Description：为角色  分配用户
	 * @author  JingYu.Dai
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param userIds 用户ID字符串 多个已  英文逗号（,）隔开
	 * @param roleId 角色Id
	 * @return int 受影响的行数
	 * @throws FrameworkException
	 */
	int insertUserAssign(String userIds,String roleId) throws FrameworkException;
	
	/**
	 * Description：删除 为角色分配的用户
	 * @author  JingYu.Dai
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param userIds 用户ID字符串 多个已  英文逗号（,）隔开
	 * @param roleId 角色Id
	 * @return int
	 * @throws FrameworkException
	 */
	int deleteUserAssign(String userIds,String roleId) throws FrameworkException;
	
}


