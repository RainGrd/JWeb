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
 * 2015年9月11日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.system.SysMenuButtRelMapper;
import com.yscf.core.dao.system.SysMenuMapper;
import com.yscf.core.model.system.SysMenu;
import com.yscf.core.model.system.SysMenuButtRel;
import com.yscf.core.service.system.ISysMenuService;

/**
 * Description：系统菜单service管理实现类
 * 
 * @author JingYu.Dai
 * @date 2015年9月11日
 * @version v1.0.0
 */
@Service("sysMenuService")
public class SysMenuServiceImpl extends BaseService<BaseEntity, String>
		implements ISysMenuService {

	@Autowired
	public SysMenuServiceImpl(SysMenuMapper dao) {
		super(dao);
	}
	
	@Resource(name="sysMenuButtRelMapper")
	SysMenuButtRelMapper sysMenuButtRelMapper;
	
	@Override
	public List<SysMenu> selectSelective(SysMenu sysMenu,String userId) throws FrameworkException {
		SysMenuMapper dao = (SysMenuMapper) getDao();
		Map<String , Object> map = new HashMap<String,Object>();
		String menuLevel = sysMenu.getMenuLevel()+"";
		menuLevel = ""==menuLevel?null:menuLevel;
		map.put("pid", sysMenu.getPid());
		map.put("parentAuthId", sysMenu.getParentAuthId());
		map.put("menuLevel", menuLevel);
		map.put("userId", userId);
		List<SysMenu> menus = dao.selectSelective(map);
		if(sysMenu != null && sysMenu.getParentAuthId() != null){
			List<SysMenu> menuList = new ArrayList<SysMenu>(); 
			recursionMenu(menuList,sysMenu.getParentAuthId(),userId);
			menus = menuList;
		}
		return menus;
	}
	
	/**
	 * Description：菜单递归查询
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param menus 菜单列表
	 * @param ppid pid
	 * @return
	 */
	private List<SysMenu> recursionMenu(List<SysMenu> menus, String ppid,String userId){
		SysMenuMapper dao = (SysMenuMapper) getDao();
		Map<String , Object> map = new HashMap<String,Object>();
		SysMenu sysMenu = new SysMenu();
		sysMenu.setParentAuthId(ppid);
		map.put("parentAuthId", ppid);
		map.put("userId", userId);
		List<SysMenu> menuList = dao.selectSelective(map);
		 for (SysMenu menu : menuList) {  
	           String parentId = menu.getParentAuthId();
	           if (parentId.equals(sysMenu.getParentAuthId())) {
	        	   menus.add(menu);
	           }
	           recursionMenu(menus, menu.getPid(),userId);  
	       }  
		return menus;
	}
	

	@Override
	public SysMenu getSysMenuByPid(String pid) throws FrameworkException {
		SysMenuMapper dao = (SysMenuMapper) getDao();
		SysMenu menu = dao.selectByPrimaryKey(pid);
		return menu;
	}

	@Override
	public int insertOrUpdateSelective(SysMenu menu) throws FrameworkException {
		SysMenuMapper dao = (SysMenuMapper) getDao();
		if(menu != null && menu.getPid() != null){
			//修改菜单
			return dao.updateByPrimaryKeySelective(menu);
		}else{
			//获取菜单主键
			menu.setPid(menu.getPK());
			String menuCodeStr = dao.selectMaxMenuCode();
			Integer menuCode ;
			//菜单表为空的时候会出现此情况
			if(menuCodeStr == null){
				menuCode = new Integer(10000);
			}else{
				menuCode = new Integer(menuCodeStr);
				menuCode++;
			}
			menu.setMenuCode(menuCode);
			//新增菜单
			return dao.insertSelective(menu);
		}
	}

	@Override
	public int deleteButtonAssigne(String menuId, String buttonIds)
			throws FrameworkException {
		int result = 0;
		if(null != buttonIds){
			String[] ids = buttonIds.split(",");
			List<String > buttonlist = Arrays.asList(ids);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("buttonIds", buttonlist);
			map.put("menuId", menuId);
			result = sysMenuButtRelMapper.deleteButtonAssign(map);
		}
		return result;
	}

	@Override
	public int insertButtonAssigne(String menuId, String buttonIds)
			throws FrameworkException {
		int result = 0;
		if(null != buttonIds){
			sysMenuButtRelMapper.deleteByMenuId(menuId);
			String[] ids = buttonIds.split(",");
			List<String > buttonlist = Arrays.asList(ids);
			List<SysMenuButtRel> buttRels = new ArrayList<SysMenuButtRel>();
			for (String buttonId : buttonlist) {
				SysMenuButtRel menuButtRel = new SysMenuButtRel();
				menuButtRel.setButtonId(buttonId);
				menuButtRel.setMenuId(menuId);
				menuButtRel.setPid(menuButtRel.getPK());
				buttRels.add(menuButtRel);
			}
			result = sysMenuButtRelMapper.insertSelectiveBatch(buttRels);
		}
		return result;
	}

	@Override
	public List<SysMenu> selectPermissionSelect() throws FrameworkException {
		SysMenuMapper dao = (SysMenuMapper) getDao();
		return dao.selectPermission();
	}

	@Override
	public List<SysMenu> queryAllMenu() throws FrameworkException {
		SysMenuMapper dao = (SysMenuMapper) getDao();
		return dao.queryAllMenu();
	}

}
