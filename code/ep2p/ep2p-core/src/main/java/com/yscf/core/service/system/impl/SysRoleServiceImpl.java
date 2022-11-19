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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.system.SysRoleMapper;
import com.yscf.core.dao.system.SysUserRoleRelMapper;
import com.yscf.core.model.system.SysRole;
import com.yscf.core.model.system.SysUserRoleRel;
import com.yscf.core.service.system.ISysRoleService;

/**
 * Description：系统角色管理实现类
 * @author  JingYu.Dai
 * @date    2015年9月10日
 * @version v1.0.0
 */
 @Service("sysRoleService")
public class SysRoleServiceImpl extends BaseService<BaseEntity, String> implements ISysRoleService{

	@Autowired
	public SysRoleServiceImpl(SysRoleMapper dao) {
		super(dao);
	}

	@Resource(name="sysUserRoleRelMapper")
	SysUserRoleRelMapper sysUserRoleRelMapper;
	
	@Override
	public List<SysRole> querySysRoleByUserId(String userId) {
		SysRoleMapper dao  = (SysRoleMapper) getDao();
		List<SysRole> roles = dao.querySysRoleByUserId(userId);
		return roles;
	}

	@Override
	public List<SysRole> selectSelective(SysRole role )  throws FrameworkException{
		SysRoleMapper dao = (SysRoleMapper) getDao();
		return dao.selectSelective(role);
	}

	@Override
	public int addOrUpdate(SysRole role) throws FrameworkException {
		int count = 0;
		SysRoleMapper dao = (SysRoleMapper) getDao();
		if(role != null){
			//修改角色
			if(role.getPid() != null && !"".equals(role.getPid())){
				count = dao.updateByPrimaryKeySelective(role);
			}else{
				//取得角色主键
				role.setPid(role.getPK());
				role.setCreateTime(DateUtil.format(new Date()));
				String roleCodeStr = dao.selectMaxRoleCode();
				Integer roleCode ;
				//角色表为空的时候会出现此情况
				if(roleCodeStr == null){
					roleCode = new Integer(10000);
				}else{
					roleCode = new Integer(roleCodeStr);
					roleCode++;
				}
				role.setRoleCode(roleCode);
				//新增角色
				count = dao.insertSelective(role);
			}
		}
		return count;
	}

	@Override
	public PageList<SysRole> selectSelectivePage(SysRole role,PageInfo info)
			throws FrameworkException {
		SysRoleMapper dao = (SysRoleMapper) getDao();
		
		return dao.selectSelectivePage(role,info);
	}

	@Override
	public int deleteBtach(String pIds) throws FrameworkException {
		int result = 0;
		if(null != pIds){
			SysRoleMapper mapper = (SysRoleMapper) getDao();
			String[] ids = pIds.split(",");
			List<String > list = Arrays.asList(ids);
			result = mapper.deleteBtach(list);
		}
		return result;
	}

	@Override
	public int insertUserAssign(String userIds, String roleId)
			throws FrameworkException {
		int result = 0;
		if(null != userIds){
			String[] ids = userIds.split(",");
			List<String > userlist = Arrays.asList(ids);
			for (String userId : userlist) {
				SysUserRoleRel sysUserRoleRel = new SysUserRoleRel();
				sysUserRoleRel.setUserId(userId);
				sysUserRoleRel.setRoleId(roleId);
				sysUserRoleRel.setPid(sysUserRoleRel.getPK());
				result += sysUserRoleRelMapper.insert(sysUserRoleRel);
				sysUserRoleRel = null;
			}
		}
		return result;
	}

	@Override
	public int deleteUserAssign(String userIds, String roleId)
			throws FrameworkException {
		int result = 0;
		if(null != userIds){
			String[] ids = userIds.split(",");
			List<String > userlist = Arrays.asList(ids);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userIds", userlist);
			map.put("roleId", roleId);
			result = sysUserRoleRelMapper.deleteUserAssign(map);
			map = null;
		}
		return result;
	}

}

