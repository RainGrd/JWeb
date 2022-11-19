/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.system.ISysParamsService;

/**
 * Description： <br>
 * 系统参数管理 服务实现类
 * 
 * @author fengshiliang
 * @date 2015年9月10日
 * @version v1.0.0
 */
@Service("sysParamsService")
public class SysParamsServiceImpl extends BaseService<BaseEntity, String>
		implements ISysParamsService {
	@Autowired
	public SysParamsServiceImpl(SysParamsMapper dao) {
		super(dao);
	}

	@Override
	public PageList<SysParams> searchParamsByKeyOrVal(SysParams params,
			PageInfo info) throws FrameworkException {
		SysParamsMapper paramsMapper = (SysParamsMapper) getDao();

		return paramsMapper.searchParamsByKeyOrVal(params, info);
	}

	@Override
	public boolean saveOrUpdateParams(SysParams params)
			throws FrameworkException {
		super.saveOrUpdateObject(params);
		return true;
	}

	@Override
	public boolean batchDeletePara(SysParams params) throws FrameworkException {
		if (params != null) {
			SysParamsMapper paramsMapper = (SysParamsMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", params.getStatus());
			map.put("pid", params.getPid().split(","));
			return paramsMapper.batchDeletePara(map);
		}
		return false;

	}

	@Override
	public SysParams getParamsByPid(String pid) {
		SysParamsMapper paramsMapper = (SysParamsMapper) getDao();
		return paramsMapper.getParamsByPid(pid);
	}

	@Override
	public void updateParamByPid(SysParams params) {
		SysParamsMapper paramsMapper = (SysParamsMapper) getDao();
		paramsMapper.updateParamByPid(params);
	}

	@Override
	public SysParams getParamsByParamKey(String paramKey) {
		SysParamsMapper paramsMapper = (SysParamsMapper) getDao();
		return paramsMapper.getParamsByParamKey(paramKey);
	}

	@Override
	public List<SysParams> searchParamsByKey(String paramKey) {

		SysParamsMapper paramsMapper = (SysParamsMapper) getDao();

		return paramsMapper.searchParamsByKey(paramKey);
	}

}
