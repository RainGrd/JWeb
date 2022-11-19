/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月11日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.system.SysMenu;

/**
 * Description：系统菜单service管理接口
 * @author  JingYu.Dai
 * @date    2015年9月11日
 * @version v1.0.0
 */
public interface ISysMenuService {
	
	/**
	 * Description：查询数据  有选择性的
	 * @author  JingYu.Dai
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param sysMenu
	 * @return List<SysMenu>
	 * @throws FrameworkException
	 */
	List<SysMenu> selectSelective(SysMenu sysMenu,String userId) throws FrameworkException;
	
	/**
	 * Description：级联查询  权限查询
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @return
	 * @throws FrameworkException
	 */
	List<SysMenu> selectPermissionSelect() throws FrameworkException;
	
	/**
	 * Description：查询系统菜单 、 根据PK
	 * @author  JingYu.Dai
	 * @date    2015年9月13日
	 * @version v1.0.0
	 * @param pid 主键
	 * @return SysMenu
	 * @throws FrameworkException
	 */
	SysMenu getSysMenuByPid(String pid) throws FrameworkException;
	
	/**
	 * Description：有选择的新增 或 修改
	 * @author  JingYu.Dai
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param menu 菜单DTO
	 * @return int 受影响的行数
	 * @throws FrameworkException
	 */
	int insertOrUpdateSelective(SysMenu menu) throws FrameworkException;
	
	/**
	 * Description：删除菜单下面的按钮列表 
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param menuId 菜单ID
	 * @param buttonIds 按钮id集合字符串，多个已（,）英文逗号分割
	 * @return int 受影响的行数
	 * @throws FrameworkException
	 */
	int deleteButtonAssigne(String menuId,String buttonIds) throws FrameworkException;
	/**
	 * Description：删除菜单下面的按钮列表 
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param menuId 菜单ID
	 * @param buttonIds 按钮id集合字符串，多个已（,）英文逗号分割
	 * @return int 受影响的行数
	 * @throws FrameworkException
	 */
	int insertButtonAssigne(String menuId,String buttonIds) throws FrameworkException;
	
	/**
	 * Description： 查询所有数据
	 * @author  JingYu.Dai
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @return  List<SysMenu>
	 * @throws FrameworkException
	 */
	List<SysMenu> queryAllMenu() throws FrameworkException;
}


