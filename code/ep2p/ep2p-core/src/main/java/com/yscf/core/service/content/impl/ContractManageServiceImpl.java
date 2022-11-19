/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 内容管理-合同管理服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.content.BizBorrowAgreementMapper;
import com.yscf.core.model.content.BizBorrowAgreement;
import com.yscf.core.service.content.IContractManageService;

/**
 * Description：<br> 
 * 内容管理-合同管理服务层
 * @author  Lin Xu
 * @date    2015年9月21日
 * @version v1.0.0
 */
@Service("contractManageServiceImpl")
public class ContractManageServiceImpl extends BaseService<BaseEntity, String> implements IContractManageService {
	
	@Autowired
	public ContractManageServiceImpl(BizBorrowAgreementMapper dao) {
		super(dao);
	}

	/**
	 * 分页查询借款合同数据信息
	 */
	@Override
	public PageList<BizBorrowAgreement> selectByPrimaryObj(
			BizBorrowAgreement bba, PageInfo info) {
		BizBorrowAgreementMapper mapper = (BizBorrowAgreementMapper) getDao();
		
		return mapper.selectByPrimaryObj(bba, info);
	}

	/**
	 * 通过条件修改数据
	 */
	@Override
	public int updateByPrimaryKeySelective(BizBorrowAgreement record) {
		BizBorrowAgreementMapper mapper = (BizBorrowAgreementMapper) getDao();
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public BizBorrowAgreement getById(String borrowAgreementId) {
		BizBorrowAgreementMapper mapper = (BizBorrowAgreementMapper) getDao();
		return mapper.selectByPrimaryKey(borrowAgreementId);
	}

	@Override
	public BizBorrowAgreement getByName(String paramValue) {
		
		BizBorrowAgreementMapper mapper = (BizBorrowAgreementMapper) getDao();
		return mapper.getByName(paramValue);
	}

}


