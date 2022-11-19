package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysUser;

/**
 * Description：系统用户数据访问层接口
 * @author  JingYu.Dai
 * @date    2015年9月8日
 * @version v1.0.0
 */
@MapperScan("sysUserMapper")
public interface SysUserMapper extends IBaseDao<BaseEntity, String> {
	
	/**
     * Description：根据用户主键删除一条系统用户数据
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int deleteByPrimaryKey(String pid);

    /**
     * Description：新增一条系统用户数据
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int insert(SysUser record);

    /**
     * Description：根据对象的参数值有选择的新增（参数为空的不新增）
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return  int  受影响的行数
     */
    int insertSelective(SysUser record);
    /**
     * Description：验证用户名是否存在
     * @author  heng.wang
     * @date    2015年11月3日
     * @version v1.0.0
     * @param record
     * @return  int  受影响的行数
     */
    int validateUserAccount(String userAccount);
    
    /**
     * Description: 根据对象的参数值有选择的跟新（参数为空的不跟新） 、条件用户主键
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int updateByPrimaryKeySelective(SysUser record);

    /**
     * Description： 跟新系统用户 、条件用户主键
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int updateByPrimaryKey(SysUser record);

    /**
     * Description：根据用户主键查询系统用户数据
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return SysUser 
     */
    SysUser selectByPrimaryKey(String pid);
    
    /**
     * Description：根据对象的参数值有选择作为条件查询 记录条数
     * @author  JingYu.Dai
     * @date    2015年9月8日
     * @version v1.0.0
     * @param sysUser 系统用户
     * @return int 记录条数
     */
    int selectSelectiveCount(SysUser sysUser);
    
    /**
     * Description：根据对象的参数值有选择作为条件查询 用户数据
     * @author  JingYu.Dai
     * @date    2015年9月11日
     * @version v1.0.0
     * @param sysUser
     * @return List<SysUser>
     */
    List<SysUser> selectSelective(SysUser sysUser);
    
    /**
	 * Description：查询用户  、条件“密码”
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param password
	 * @return boolean
	 * @throws FrameworkException
	 */
	int getSysUserByPassword(String password);
	
	/**
	 * Description：查询用户  、条件“用户名”
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param userName 系统用户名
	 * @return boolean
	 * @throws FrameworkException
	 */
	int getSysUserByUserNameCount(String userName);
	/**
	 * Description：查询用户列表
	 * @author  heng.wang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<SysUser> selectAll(SysUser sysUser);
	
	/**
	 * Description：批量删除用户列表
	 * @author  heng.wang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int deleteBtach(List<String> list);
	
	/**
	 * Description：查询用户列表,带分页功能的
	 * @author  heng.wang
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<SysUser> selectAllPage(@Param("sysUser") SysUser sysUser, PageInfo info);
	
	/**
	 * Description：根据角色ID 查询匹配的用户列表
	 * @author  JingYu.Dai
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param map
	 * @param info
	 * @return PageList<SysUser>
	 */
	PageList<SysUser> queryUsersByRoleId(@Param("map") Map<String, String> map, PageInfo info);
	
	/**
	 * Description：根据角色ID 查询不匹配的用户列表
	 * @author  JingYu.Dai
	 * @date    2015年9月19日
	 * @version v1.0.0
	 * @param roleId
	 * @return List<SysUser>
	 * @throws FrameworkException
	 */
	PageList<SysUser> queryNoUsersByRoleId(@Param("map") Map<String, String> map, PageInfo info);
	
	/**
	 * Description：根据用户名获取用户对象 	不过滤禁用的用户
	 * @author  JingYu.Dai
	 * @date    2015年10月16日
	 * @version v1.0.0
	 * @param userName
	 * @return	SysUser
	 */
	List<SysUser> getSysUserByUserName(String userName);
	
	/**
	 * Description：根据用户账号获取用户对象 	不过滤禁用的用户
	 * @author  JingYu.Dai
	 * @date    2015年10月16日
	 * @version v1.0.0
	 * @param accountNo
	 * @return SysUser
	 */
	SysUser getSysUserByAccountNo(String accountNo);
	
	/**
	 * Description：<br> 
	 * 根据PID集合	查询用户列表
	 * @author  JingYu.Dai
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param pids
	 * @return List<SysUser>
	 */
	List<SysUser> getSysUserByPids(List<String> pids);
	
	/**
	 * Description：<br> 
	 * 根据角色编码 	获取该角色下的所有用户
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param roleCode 角色编码
	 * @return List<SysUser>
	 */
	List<SysUser> queryUserByRoleCode(Integer roleCode);
	
	/**
	 * Description：<br> 
	 * 批量更新密码更新密码
	 * @author  JingYu.Dai
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param users
	 * @return
	 */
	int passwordResetBtach(List<SysUser> users);
	/**
	 * Description：<br> 
	 * 根君pid更新密码更新密码
	 * @author heng.wang
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @param users
	 * @return
	 */
	int resetPassword(SysUser sysUser);
    
}