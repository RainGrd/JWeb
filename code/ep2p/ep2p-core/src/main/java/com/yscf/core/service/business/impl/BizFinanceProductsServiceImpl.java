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
 * 2015年10月19日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.business.BizFinanceProductsMapper;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.service.business.IBizFinanceProductsService;

/**
 * Description：<br>
 * 理财产品介绍 服务实现
 * 
 * @author fengshiliang
 * @date 2015年10月19日
 * @version v1.0.0
 */
@Service("bizFinanceProductsServiceImpl")
public class BizFinanceProductsServiceImpl extends
		BaseService<BaseEntity, String> implements IBizFinanceProductsService {
	@Autowired
	public BizFinanceProductsServiceImpl(BizFinanceProductsMapper dao) {
		super(dao);
	}

	@Resource
	private BizFinanceProductsMapper mapper;

	@Override
	public void save(BizFinanceProducts products) throws Exception {
		mapper.insert(products);
	}

	@Override
	public BizFinanceProducts getBizFinanceProductsByBorrowId(String borrowId) {
		return mapper.getBizFinanceProductsByBorrowId(borrowId);
	}

}
