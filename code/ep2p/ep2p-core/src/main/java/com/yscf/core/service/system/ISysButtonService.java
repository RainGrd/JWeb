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
 * 2015年9月22日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysButton;

/**
 * Description：系统 按钮管理
 * @author  JingYu.Dai
 * @date    2015年9月22日
 * @version v1.0.0
 */
public interface ISysButtonService {

	/**
	 * Description：分页查询 按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param sysButton 按钮对象
	 * @param info 分页对象
	 * @return PageList<SysButton> 
	 */
	PageList<SysButton> selectSelectivePage(SysButton sysButton, PageInfo info) throws FrameworkException;
	
	/**
	 * Description：菜单按钮配置 查询可选按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param info 分页对象
	 * @param menuId 菜单ID
	 * @return PageList<SysButton>
	 * @throws FrameworkException
	 */
	PageList<SysButton> selectChoosableButtonPage(String menuId,PageInfo info)throws FrameworkException;
	
	/**
	 * Description：菜单按钮配置 查询衣已选按钮 
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param info 分页对象
	 * @param menuId 菜单ID
	 * @return PageList<SysButton>
	 * @throws FrameworkException
	 */
	PageList<SysButton> selectSelectedButtonPage(String menuId,PageInfo info)throws FrameworkException;
	
	/**
	 * Description：新增修改按钮
	 * @author  JingYu.Dai
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @param button 按钮对象
	 * @return int 受影响的行数
	 * @throws FrameworkException
	 */
	int addOrUpdate(SysButton button) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * 根据菜单ID 查询按钮集合
	 * @author  JingYu.Dai
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param menuId 菜单ID
	 * @param userId 用户ID
	 * @return List<SysButton>
	 */
	List<SysButton> getButtonsByMenuId(String menuId,String userId) throws FrameworkException;
}


