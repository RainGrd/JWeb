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
 * 2015年9月18日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.content.ContTagMapper;
import com.yscf.core.model.content.ContTag;
import com.yscf.core.service.content.IContTagService;

/**
 * Description：<br>
 * 
 * @author fengshiliang
 * @date 2015年9月18日
 * @version v1.0.0
 */
@Service("contTagService")
public class ContTagServiceImpl extends BaseService<BaseEntity, String>
		implements IContTagService {
	@Autowired
	public ContTagServiceImpl(ContTagMapper dao) {
		super(dao);
	}

	@Override
	public List<ContTag> selectedTag(String contentId) {
		ContTagMapper mapper = (ContTagMapper) getDao();
		List<ContTag> list = mapper.selectedTag(contentId);
		return list;
	}

	@Override
	public void deleteByColuContId(String contentId) {
		ContTagMapper mapper = (ContTagMapper) getDao();
		mapper.deleteByColuContId(contentId);
	}

}
