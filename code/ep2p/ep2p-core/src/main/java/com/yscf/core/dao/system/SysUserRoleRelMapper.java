package com.yscf.core.dao.system;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysUserRoleRel;

@MapperScan("sysUserRoleRelMapper")
public interface SysUserRoleRelMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

	int insert(SysUserRoleRel record);

	int insertSelective(SysUserRoleRel record);

	SysUserRoleRel selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysUserRoleRel record);

	int updateByPrimaryKey(SysUserRoleRel record);

    int deleteUserAssign(Map<String,Object> map);
}