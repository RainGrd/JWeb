/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.system.SysDistionaryMapper;
import com.yscf.core.model.system.SysDistionary;
import com.yscf.core.service.system.ISysDistionaryService;

/**
 * Description：<br> 
 * 数据字典类业务实现
 * @author  Simon.Hoo
 * @date    2015年9月6日
 * @version v1.0.0
 */
@Service("sysDistionaryService")
public class SysDistionaryServiceImpl extends BaseService<BaseEntity, String> implements
		ISysDistionaryService {

	
	@Autowired
	public SysDistionaryServiceImpl(SysDistionaryMapper dao) {
		super(dao);
	}
	

	public PageList<SysDistionary> selectAllPage(SysDistionary sysDistionary, PageInfo info) {
		SysDistionaryMapper mapper = (SysDistionaryMapper) getDao();
		
		return mapper.selectAllPage(sysDistionary,info);
	}

	@Override
	public List<SysDistionary>selectAll(SysDistionary dis) {
		SysDistionaryMapper mapper = (SysDistionaryMapper) getDao();
		return mapper.selectAll(dis);
	}

	@Override
	public int updateStatusBatch(SysDistionary dis) {
		int result = 0;
		if(null != dis && null!=dis.getPid()){
			SysDistionaryMapper mapper = (SysDistionaryMapper) getDao();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", dis.getStatus());
			map.put("idItem", dis.getPid().split(","));
			result = mapper.updateStatusBatch(map);
		}
		return result;
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 验证数据字典code 是否存在
	 * @author  Yu.Zhang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param sysDistionary
	 * @return
	 */
	public boolean validateCode(SysDistionary sysDistionary) {
		SysDistionaryMapper mapper = (SysDistionaryMapper) getDao();
		List<SysDistionary> list = mapper.validateCode(sysDistionary);
		if(null == list || list.size() ==0){
			return true;
		}
		return false;
	}


	public int updateByPrimaryKeySelective(SysDistionary sysDistionary) {
		SysDistionaryMapper mapper = (SysDistionaryMapper) getDao();
		return mapper.updateByPrimaryKeySelective(sysDistionary);
	}

}


