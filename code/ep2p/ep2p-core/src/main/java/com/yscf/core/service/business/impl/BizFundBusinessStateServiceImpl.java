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
 * 2015年10月13日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.business.BizFundBusinessStateMapper;
import com.yscf.core.model.business.BizFundBusinessState;
import com.yscf.core.service.business.IBizFundBusinessStateService;

/**
 * Description：<br> 
 * 资金交易状况表
 * @author  Yu.Zhang
 * @date    2015年10月13日
 * @version v1.0.0
 */
@Service("bizFundBusinessStateServiceImpl")
public class BizFundBusinessStateServiceImpl extends BaseService<BaseEntity, String> implements
		IBizFundBusinessStateService {

	@Autowired
	public BizFundBusinessStateServiceImpl(BizFundBusinessStateMapper dao) {
		super(dao);
	}

	@Override
	public List<BizFundBusinessState> selectAll(BizFundBusinessState bizFundBusinessState) {
		BizFundBusinessStateMapper mapper = (BizFundBusinessStateMapper) getDao();
		return mapper.selectAll(bizFundBusinessState);
	}

	@Override
	public void executeSyncFundBusinessState(BizFundBusinessState bizFundBusinessState) {
		BizFundBusinessStateMapper mapper = (BizFundBusinessStateMapper) getDao();
		mapper.syncFundBusinessState(bizFundBusinessState);
	}

}


