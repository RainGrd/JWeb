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
 * 2015年9月30日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.financial.BizRechargeSystemInfoMapper;
import com.yscf.core.model.financial.BizRechargeSystemInfo;
import com.yscf.core.service.financial.IBizRechargeSystemInfoService;

/**
 * Description：充值渠道业务实现类
 * @author  JingYu.Dai
 * @date    2015年9月30日
 * @version v1.0.0
 */
@Service("bizRechargeSystemInfoService")
public class BizRechargeSystemInfoServiceImpl extends BaseService<BaseEntity, String> implements
		IBizRechargeSystemInfoService {

	@Autowired
	public BizRechargeSystemInfoServiceImpl(BizRechargeSystemInfoMapper dao) {
		super(dao);
	}
	
	@Override
	public List<BizRechargeSystemInfo> selectAll() throws FrameworkException {
		BizRechargeSystemInfoMapper dao = (BizRechargeSystemInfoMapper) getDao();
		return dao.selectAll();
	}
}


