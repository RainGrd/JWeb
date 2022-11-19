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
 * 2015年9月22日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.core.dao.system.SysButtonMapper;
import com.yscf.core.model.system.SysButton;
import com.yscf.core.service.system.ISysButtonService;

/**
 * Description：系统 按钮管理
 * @author  JingYu.Dai
 * @date    2015年9月22日
 * @version v1.0.0
 */
@Service("sysButtonServiceImpl")
public class SysButtonServiceImpl extends BaseService<BaseEntity, String> implements ISysButtonService{

	@Autowired
	public SysButtonServiceImpl(SysButtonMapper dao) {
		super(dao);
	}

	@Override
	public PageList<SysButton> selectSelectivePage(SysButton sysButton, PageInfo info) throws FrameworkException{
		SysButtonMapper dao = (SysButtonMapper) getDao();
		
		return dao.selectSelectivePage(sysButton,info);
	}

	@Override
	public PageList<SysButton> selectChoosableButtonPage(String menuId,PageInfo info)
			throws FrameworkException {
		SysButtonMapper dao = (SysButtonMapper) getDao();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("menuId", menuId);
		return dao.selectChoosableButtonPage(map,info);
	}

	@Override
	public PageList<SysButton> selectSelectedButtonPage(String menuId,PageInfo info)
			throws FrameworkException {
		SysButtonMapper dao = (SysButtonMapper) getDao();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("menuId", menuId);
		return dao.selectSelectedButtonPage(map,info);
	}

	@Override
	public int addOrUpdate(SysButton button) throws FrameworkException {
		int count = 0;
		SysButtonMapper dao = (SysButtonMapper) getDao();
		if(button != null){
			//修改按钮
			if(button.getPid() != null && !"".equals(button.getPid())){
				count = dao.updateByPrimaryKey(button);
			}else{
				//取得按钮色主键
				button.setPid(button.getPK());
				button.setCreateTime(DateUtil.format(new Date()));
				String buttonCodeStr = dao.getMaxButtonCode();
				Integer roleCode ;
				//按钮表为空的时候会出现此情况
				if(buttonCodeStr == null){
					roleCode = new Integer(10000);
				}else{
					roleCode = new Integer(buttonCodeStr);
					roleCode++;
				}
				button.setButtonCode(roleCode.toString());
				//新增角色
				count = dao.insertSelective(button);
			}
		}
		return count;
	}

	@Override
	public List<SysButton> getButtonsByMenuId(String menuId,String userId) throws FrameworkException{
		SysButtonMapper dao = (SysButtonMapper) getDao();
		Map<String,String> map = new HashMap<String, String>();
		map.put("menuId", menuId);
		map.put("userId", userId);
		return dao.getButtonsByMenuId(map);
	}
	
}


