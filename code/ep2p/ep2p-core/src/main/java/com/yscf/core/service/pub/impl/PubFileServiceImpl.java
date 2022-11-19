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
 * 2015年9月29日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.pub.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.pub.PubFileMapper;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.pub.IPubFileService;

/**
 * Description：<br> 
 * 文件service 
 * @author  Yu.Zhang
 * @date    2015年9月29日
 * @version v1.0.0
 */
@Service("pubFileServiceImpl")
public class PubFileServiceImpl extends BaseService<BaseEntity, String> implements IPubFileService {

	@Autowired
	public PubFileServiceImpl(PubFileMapper dao) {
		super(dao);
	}

	@Override
	public PubFile getById(String pid) {
		PubFileMapper dao = (PubFileMapper) getDao();
		return dao.selectByPrimaryKey(pid);
	}

}


