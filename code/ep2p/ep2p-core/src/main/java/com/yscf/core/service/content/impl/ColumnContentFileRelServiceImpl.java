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
 * 2015年11月5日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.content.ColumnContentFileRelMapper;
import com.yscf.core.service.content.IColumnContentFileRelService;

/**
 * Description：<br>
 * 栏目内容文件关系表
 * 
 * @author fengshiliang
 * @date 2015年11月5日
 * @version v1.0.0
 */
@Service("columnContentFileRelServiceImpl")
public class ColumnContentFileRelServiceImpl extends
		BaseService<BaseEntity, String> implements IColumnContentFileRelService {
	@Autowired
	public ColumnContentFileRelServiceImpl(ColumnContentFileRelMapper dao) {
		super(dao);
	}

}
