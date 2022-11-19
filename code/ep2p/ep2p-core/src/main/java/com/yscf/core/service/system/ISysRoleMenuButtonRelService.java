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
package com.yscf.core.service.system;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.system.SysRoleMenuButtonRel;


/**
 * Description：系统角色菜单按钮关系管理接口
 * @author  JingYu.Dai
 * @date    2015年9月10日
 * @version v1.0.0
 */
public interface ISysRoleMenuButtonRelService {
	
	 /**
     * Description：根据角色ID查询数据列表
     * @author  JingYu.Dai
     * @date    2015年9月23日
     * @version v1.0.0
     * @param roleId 角色ID
     * @return List<SysRoleMenuButtonRel>
     */ 
    List<SysRoleMenuButtonRel> selectByRoleId(String roleId)throws FrameworkException;
    
    /**
	 * Description：新增 权限配置
	 * @author  JingYu.Dai
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param menuIds
	 * @param roleId
	 * @return int 受影响的行数
	 * @throws FrameworkException
	 */
	int insertPermissionAssign(String menuIds,String roleId,String menuButtonIds) throws FrameworkException;
}


