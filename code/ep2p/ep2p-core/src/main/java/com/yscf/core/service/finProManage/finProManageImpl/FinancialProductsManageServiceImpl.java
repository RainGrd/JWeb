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
 * 2015年11月13日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.finProManage.finProManageImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.business.BizBorrowCondRelMapper;
import com.yscf.core.dao.business.BizBorrowInfoVOMapper;
import com.yscf.core.dao.business.BizFinanceProductsMapper;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.service.finProManage.FinancialProductsManageService;
import com.yscf.core.service.system.ISysCreateCodeRuleService;

/**
 * Description：<br>
 * 
 * @author shiliang.feng
 * @date 2015年11月13日
 * @version v1.0.0
 */
@Service("financialProductsManageServiceImpl")
public class FinancialProductsManageServiceImpl extends
		BaseService<BaseEntity, String> implements
		FinancialProductsManageService {

	@Resource(name = "bizFinanceProductsMapper")
	private BizFinanceProductsMapper bizFinanceProductsMapper;

	@Resource(name = "bizBorrowCondRelMapper")
	private BizBorrowCondRelMapper borrowCondRelMapper;

	@Resource(name = "sysCreateCodeRuleService")
	private ISysCreateCodeRuleService sysCreateCodeRuleService;

	@Resource(name = "bizBorrowInfoVOMapper")
	private BizBorrowInfoVOMapper infoVOMapper;

	@Autowired
	public FinancialProductsManageServiceImpl(BizBorrowInfoVOMapper dao) {
		super(dao);
	}

	@Override
	public Integer selectFinProdCountPage(BizBorrowInfoVO borrow) {
		return infoVOMapper.selectFinProdCountPage(borrow);
	}

	@Override
	public List<BizBorrowInfoVO> selectPublishedFinData(BizBorrowInfoVO borrow,
			Integer pageIndex, Integer pageSize, String sortType,
			String sortModel) {
		return infoVOMapper.selectPublishedFinData(borrow, pageIndex, pageSize,
				sortType, sortModel);
	}
}
