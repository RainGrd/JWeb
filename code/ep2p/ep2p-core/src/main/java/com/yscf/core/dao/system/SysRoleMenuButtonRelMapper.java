package com.yscf.core.dao.system;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysRoleMenuButtonRel;

@MapperScan("sysRoleMenuButtonRelMapper")
public interface SysRoleMenuButtonRelMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(SysRoleMenuButtonRel record);

    int insertSelective(SysRoleMenuButtonRel record);

    SysRoleMenuButtonRel selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(SysRoleMenuButtonRel record);

    int updateByPrimaryKey(SysRoleMenuButtonRel record);
    
    /**
     * Description：根据角色ID删除所有数据 
     * @author  JingYu.Dai
     * @date    2015年9月23日
     * @version v1.0.0
     * @param roleId 角色ID
     * @return int
     */
    int deleteByRoleId(String roleId);
    
    /**
     * Description：根据角色ID查询数据列表
     * @author  JingYu.Dai
     * @date    2015年9月23日
     * @version v1.0.0
     * @param roleId 角色ID
     * @return List<SysRoleMenuButtonRel>
     */ 
    List<SysRoleMenuButtonRel> selectByRoleId(String roleId);
    
}