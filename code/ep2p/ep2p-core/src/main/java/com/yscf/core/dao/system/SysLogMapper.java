package com.yscf.core.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysLog;
/**
 * 
 * Description：系统日志数据访问层接口 
 * TODO
 * @author  Allen
 * @date    2015年9月15日
 * @version v1.0.0
 */
@MapperScan("sysLogMapper")
public interface SysLogMapper extends IBaseDao<BaseEntity, String> {
	/**
     * Description：根据日志主键删除一条系统日志数据
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int deleteByPrimaryKey(String pid);
    /**
	 * Description：批量删除日志列表
	 * @author  Allen
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param userName 系统日志
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int deleteBtach(List<?> list);
    /**
     * Description：新增一条系统日志数据
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int insert(SysLog record);
    /**
     * Description：根据对象的参数值有选择的新增（参数为空的不新增）
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return  int  受影响的行数
     */
    int insertSelective(SysLog record);
    /**
     * Description：根据日志主键查询系统日志数据
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return SysUser 
     */
    SysLog selectByPrimaryKey(String pid);
    /**
     * Description： 跟新系统日志 、条件日志主键
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int updateByPrimaryKey(SysLog record);
    /**
     * Description: 根据对象的参数值有选择的跟新（参数为空的不跟新） 、条件日志主键
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param record
     * @return int 受影响的行数
     */
    int updateByPrimaryKeySelective(SysLog record);
    /**
     * Description：根据对象的参数值有选择作为条件查询 记录条数
     * @author  Allen
     * @date    2015年9月8日
     * @version v1.0.0
     * @param sysUser 系统日志
     * @return int 记录条数
     */
    int selectSelectiveCount(SysLog sysLog);
    /**
	 * Description：查询日志列表,带分页功能的
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param sysLog 系统日志
	 * @return PageList<SysLog>
	 * @throws FrameworkException
	 */
	 PageList<SysLog> selectAllPage(@Param("sysLog") SysLog sysLog, PageInfo info);
    /**
	 * Description：查询日志列表
	 * @author  Allen
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param SysLog sysLog 系统日志
	 * @return List<SysLog>
	 * @throws FrameworkException
	 */
	List<SysLog> selectAll(SysLog sysLog);
}