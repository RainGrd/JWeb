package com.yscf.core.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysRole;

@MapperScan("sysRoleMapper")
public interface SysRoleMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(String pid);

	int insert(SysRole record);

	int insertSelective(SysRole record);

	SysRole selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysRole record);

	int updateByPrimaryKey(SysRole record);

	/**
	 * Description：根据用户ID 询角色数据
	 * @author  JingYu.Dai
	 * @date    2015年9月10日
	 * @version v1.0.0
	 * @param userId 
	 * @return  List<SysRole>
	 */
	List<SysRole> querySysRoleByUserId(String userId);
	
	/**
	 * Description：有选择的进行查询      根据角色对象role的属性进行查询 
	 * @author  JingYu.Dai
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @param role
	 * @return List<SysRole>
	 */
	List<SysRole> selectSelective(SysRole role);
	
	/**
	 * Description：获取最大角色编码
	 * @author  JingYu.Dai
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @return String
	 */
	String selectMaxRoleCode();
	
	/**
	 * Description：分页查询
	 * @author  JingYu.Dai
	 * @date    2015年9月17日
	 * @version v1.0.0
	 * @param role
	 * @param info
	 * @return PageList<SysRole>
	 */
	PageList<SysRole> selectSelectivePage(@Param("map")SysRole role,PageInfo info);
	
	/**
	* Description：批量删除角色 根据pid 集合
	 * @author  JingYu.Dai
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param list pid 集合
	 * @return int 受影响记录条数
	 */
	int deleteBtach(List<String> list);
	
}