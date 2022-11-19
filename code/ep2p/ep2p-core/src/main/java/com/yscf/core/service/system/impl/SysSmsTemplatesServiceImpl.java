/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 短信模板Service实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月14日     Jie.Zou		Initial Version.
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
import com.yscf.core.dao.system.SysSmsTemplatesMapper;
import com.yscf.core.model.system.SysSmsTemplates;
import com.yscf.core.service.system.ISysSmsTemplatesService;

/**
 * Description：<br> 
 * 短信模板Service实现
 * @author  Jie.Zou
 * @date    2015年9月14日
 * @version v1.0.0
 */
@Service("sysSmsTemplatesService")
public class SysSmsTemplatesServiceImpl extends BaseService<BaseEntity, String> 
			implements ISysSmsTemplatesService {
	
	@Autowired
	public SysSmsTemplatesServiceImpl(SysSmsTemplatesMapper dao) {
		super(dao);
	}

	@Override
	public PageList<SysSmsTemplates> selectAllPage(SysSmsTemplates smsTemplates,
			PageInfo info) {
		SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
		
		return mapper.selectAllPage(smsTemplates, info);
	}

	@Override
	public List<SysSmsTemplates> selectAll(SysSmsTemplates smsTemplates) {
		SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
		return mapper.selectAll(smsTemplates);
	}

	@Override
	public SysSmsTemplates getSmsTemplatesById(String pid) {
		SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
		return mapper.selectByPrimaryKey(pid);
	}

	@Override
	public void updateSmsTemplates(SysSmsTemplates smsTemplates) {
		SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
		mapper.updateByPrimaryKeySelective(smsTemplates);
	}

	@Override
	public int updateStatusBatch(SysSmsTemplates smsTemplates) {
		int result = 0;
		if(null != smsTemplates && null != smsTemplates.getPid()){
			SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", smsTemplates.getStatus());
			map.put("lastUpdateUser", smsTemplates.getLastUpdateUser());
			map.put("lastUpdateTime", smsTemplates.getLastUpdateTime());
			map.put("idItem", smsTemplates.getPid().split(","));
			result = mapper.updateStatusBatch(map);
		}
		return result;
	}

	@Override
	public SysSmsTemplates selectByTempCode(String tempKey) {
		SysSmsTemplates smsTemplates = new SysSmsTemplates();
		smsTemplates.setTempCode(tempKey);
		SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
		return mapper.selectByTempCode(smsTemplates);
	}

	@Override
	public int insert(SysSmsTemplates smsTemplates) {
		SysSmsTemplatesMapper mapper = (SysSmsTemplatesMapper)getDao();
		return mapper.insert(smsTemplates);
	}
	
}


