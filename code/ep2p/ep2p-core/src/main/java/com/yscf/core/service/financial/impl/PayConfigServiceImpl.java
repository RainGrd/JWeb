/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 支付接口业务逻辑接口实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月18日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.financial.recharge.PayConfigMapper;
import com.yscf.core.model.financial.recharge.PayConfig;
import com.yscf.core.service.financial.IPayConfigService;

/**
 * Description：<br> 
 * 支付接口业务逻辑接口实现
 * @author  Jie.Zou
 * @date    2015年11月18日
 * @version v1.0.0
 */
@Service("payConfigServiceImpl")
public class PayConfigServiceImpl extends BaseService<BaseEntity, String> implements IPayConfigService{

	
	@Autowired
	public PayConfigServiceImpl(PayConfigMapper dao) {
		super(dao);
	}

	@Override
	public List<PayConfig> selectAll() {
		PayConfigMapper mapper = (PayConfigMapper)getDao();
		return mapper.selectAll();
	}

	@Override
	public PayConfig selectPay(String payConfigId) {
		PayConfigMapper mapper = (PayConfigMapper)getDao();
		return mapper.selectByPrimaryKey(payConfigId);
	}

}


