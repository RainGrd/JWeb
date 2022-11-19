/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.system.SysRoleMenuButtonRelMapper;
import com.yscf.core.model.system.SysRoleMenuButtonRel;
import com.yscf.core.service.system.ISysRoleMenuButtonRelService;

/**
 * Description：系统角色管理实现类
 * @author  JingYu.Dai
 * @date    2015年9月10日
 * @version v1.0.0
 */
 @Service("sysRoleMenuButtonRelServiceImpl")
public class SysRoleMenuButtonRelServiceImpl extends BaseService<BaseEntity, String> implements ISysRoleMenuButtonRelService{

	@Autowired
	public SysRoleMenuButtonRelServiceImpl(SysRoleMenuButtonRelMapper dao) {
		super(dao);
	}

	@Override
	public List<SysRoleMenuButtonRel> selectByRoleId(String roleId) throws FrameworkException {
		SysRoleMenuButtonRelMapper dao = (SysRoleMenuButtonRelMapper) getDao();
		return dao.selectByRoleId(roleId);
	}

	@Override
	public int insertPermissionAssign(String menuIds, String roleId ,String menuButtonIds)
			throws FrameworkException {
		SysRoleMenuButtonRelMapper sysRoleMenuButtonRelMapper = (SysRoleMenuButtonRelMapper) getDao();
		//根据角色ID删除所有数据 关联数据
		sysRoleMenuButtonRelMapper.deleteByRoleId(roleId);
		int count = 0;
		//拆分菜单ID
		String[] ids = menuIds.split(",");
		List<String > menuIdList = Arrays.asList(ids);
		SysRoleMenuButtonRel sysRoleMenuButtonRel = null;
		for (String menuId : menuIdList) {
			sysRoleMenuButtonRel = new SysRoleMenuButtonRel();
			sysRoleMenuButtonRel.setRoleId(roleId);
			sysRoleMenuButtonRel.setMenuId(menuId);
			sysRoleMenuButtonRel.setPid(sysRoleMenuButtonRel.getPK());
			count+=sysRoleMenuButtonRelMapper.insert(sysRoleMenuButtonRel);
			sysRoleMenuButtonRel = null;
		}
		//拆分菜单按钮中间表ID
		String[] mbIds = menuButtonIds.split(",");
		List<String > mbIdList = Arrays.asList(mbIds);
		for (String mbId : mbIdList) {
			sysRoleMenuButtonRel = new SysRoleMenuButtonRel();
			sysRoleMenuButtonRel.setRoleId(roleId);
			sysRoleMenuButtonRel.setMenuButtonRelId(mbId);
			sysRoleMenuButtonRel.setPid(sysRoleMenuButtonRel.getPK());
			count+=sysRoleMenuButtonRelMapper.insert(sysRoleMenuButtonRel);
			sysRoleMenuButtonRel = null;
		}
		return count;
	}
}

