package com.yscf.core.dao.system;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.system.SysMenu;

@MapperScan("sysMenuMapper")
public interface SysMenuMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String pid);
    
    List<SysMenu> selectSelective(Map<String , Object> map);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    /**
     * Description：查询 权限
     * @author  JingYu.Dai
     * @date    2015年9月23日
     * @version v1.0.0
     * @return List<SysMenu>
     */
    List<SysMenu> selectPermission();
    
    /**
     * Description：查询全部
     * @author  JingYu.Dai
     * @date    2015年9月23日
     * @version v1.0.0
     * @return List<SysMenu>
     */
    List<SysMenu> queryAllMenu();

    /**
	 * Description：<br> 
	 * 查询菜单编码最大值
	 * @author  JingYu.Dai
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @return String
	 * @throws FrameworkException
	 */
	String selectMaxMenuCode();
}