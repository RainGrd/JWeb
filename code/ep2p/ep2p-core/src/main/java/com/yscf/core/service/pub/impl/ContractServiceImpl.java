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
 * 2015年9月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.pub.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.pub.ContractMapper;
import com.yscf.core.model.pub.Contract;
import com.yscf.core.service.pub.IContractService;

/**
 * Description：<br> 
 * 借款审批业务处理类
 * @author  Yu.Zhang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Service("contractService")
public class ContractServiceImpl  extends BaseService<BaseEntity, String>  implements IContractService {
	
	@Autowired
	public ContractServiceImpl(	ContractMapper dao) {
		super(dao);
	}

	@Override
	public Contract getByCustIdAndBusinessId(String userId, String borrowId,
			String type) {
		ContractMapper dao = (ContractMapper) getDao();
		return dao.getByCustIdAndBusinessId(userId,borrowId,type);
	}

	@Override
	public int insertSelective(Contract contract) {
		ContractMapper dao = (ContractMapper) getDao();
		return dao.insertSelective(contract);
	}

}


