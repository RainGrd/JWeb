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
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysUser;

/**
 * Description：<br> 
 * 用户管理服务接口
 * @author  JingYu.Dai
 * @date    2015年10月29日
 * @version v1.0.0
 */
public interface ISysUserService {
	/**
	 * Description：新增系统用户
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(SysUser record) throws FrameworkException;
	/**
	 * Description：验证用户是否已经存在
	 * @author  heng.wang
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int validateUserAccount(String userAccount) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param sysUser
	 * @return boolean
	 * @throws FrameworkException
	 */
	public boolean login(SysUser sysUser) throws FrameworkException;
	
	/**
	 * Description：验证密码
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param password 系统用户密码
	 * @return boolean
	 * @throws FrameworkException
	 */
	public boolean passwordVerify(String password) throws FrameworkException;
	
	/**
	 * Description：验证用户名
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param userName 系统用户名
	 * @return boolean
	 * @throws FrameworkException
	 */
	public boolean userNameVerify(String userName) throws FrameworkException;
	
	/**
	 * Description：根据用户名查询用户对象
	 * @author  JingYu.Dai
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param userName
	 * @return
	 * @throws FrameworkException
	 */
	public SysUser getSysUserByUserName(String userName) throws FrameworkException;
	
	/**
	 * Description：查询用户列表
	 * @author  heng.wang
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<SysUser> selectAll(SysUser sysUser, PageInfo pageInfo);
	/**
	 * Description：批量删除用户列表
	 * @author  heng.wang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int deleteBtach(String pids);
	/**
	 * Description：查询用户列表，带分页功能
	 * @author  heng.wang
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<SysUser> selectAllPage(SysUser sysUser, PageInfo info) ;
	/**
	 * Description：修改用户列表
	 * @author  heng.wang
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updateByPrimaryKeySelective(SysUser sysUser);
	
	/**
	 * Description：根据角色ID 查询匹配的用户列表
	 * @author  JingYu.Dai
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param roleId 角色ID
	 * @param queryStr 查询字符串
	 * @param info 分页对象
	 * @return PageList<SysUser>
	 * @throws FrameworkException
	 */
	PageList<SysUser> queryUsersByRoleId(String roleId,String queryStr,PageInfo info) throws FrameworkException;
	
	/**
	 * Description：根据角色ID 查询不匹配的用户列表
	 * @author  JingYu.Dai
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param roleId 角色ID
	 * @param queryStr 查询字符串
	 * @param info 分页对象
	 * @return PageList<SysUser>
	 * @throws FrameworkException
	 */
	PageList<SysUser> queryNoUsersByRoleId(String roleId,String queryStr,PageInfo info) throws FrameworkException;
	
	/**
	 * 
	 * Description：根据用户账号查询用户对象
	 * @author  JingYu.Dai
	 * @date    2015年10月16日
	 * @version v1.0.0
	 * @param accountNo
	 * @return
	 */
	SysUser getSysUserByAccountNo(String accountNo) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * 根据用户PID 重置密码
	 * @author  JingYu.Dai
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param pids 用户pid字符串用逗号分割
	 * @return boolean 是否成功
	 * @throws FrameworkException
	 */
	boolean passwordResetBtach(String pids) throws FrameworkException;
	/**
	 * Description：<br> 
	 * 根据用户PID 重置密码
	 * @author  heng.wang
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @param pids 用户pid字符串用逗号分割
	 * @return 
	 * @throws FrameworkException
	 */
	public int resetPassword(SysUser sysUser)throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * 根据角色编码 	获取该角色下的所有用户
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param roleCode 角色编码
	 * @return List<SysUser>
	 * @throws FrameworkException
	 */
	List<SysUser> queryUserByRoleCode(Integer roleCode) throws FrameworkException;
}


