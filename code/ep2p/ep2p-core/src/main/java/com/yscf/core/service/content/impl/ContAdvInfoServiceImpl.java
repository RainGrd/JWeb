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
 * 2015年9月24日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.content.adv.ContAdvInfoMapper;
import com.yscf.core.model.content.adv.ContAdvInfo;
import com.yscf.core.service.content.IContAdvInfoService;

/**
 * Description：<br>
 * 广告信息 服务实现
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@Service("contAdvInfoServiceImpl")
public class ContAdvInfoServiceImpl extends BaseService<BaseEntity, String>
		implements IContAdvInfoService {

	@Autowired
	public ContAdvInfoServiceImpl(ContAdvInfoMapper dao) {
		super(dao);
	}

	@Override
	public PageList<ContAdvInfo> selectContentByParameter(ContAdvInfo column,
			PageInfo info) {
		ContAdvInfoMapper mapper = (ContAdvInfoMapper) getDao();

		return mapper.selectContentByParameter(column, info);
	}

	@Override
	public void batchUpdateByPids(ContAdvInfo params) {
		ContAdvInfoMapper mapper = (ContAdvInfoMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", params.getStatus());
		map.put("pid", params.getPid().split(","));
		mapper.batchUpdateByPids(map);
	}

}
