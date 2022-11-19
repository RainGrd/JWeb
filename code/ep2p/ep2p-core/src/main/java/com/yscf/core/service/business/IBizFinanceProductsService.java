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
 * 2015年10月19日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import com.yscf.core.model.business.BizFinanceProducts;

/**
 * Description：<br>
 * TODO 理财产品介绍 服务接口
 * 
 * @author fengshiliang
 * @date 2015年10月19日
 * @version v1.0.0
 */
public interface IBizFinanceProductsService {

	/**
	 * 
	 * Description：<br>
	 * TODO 保存理财产品介绍实体
	 * 
	 * @author fengshiliang
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param products
	 * @throws Exception
	 */
	void save(BizFinanceProducts products) throws Exception;

	/**
	 * 
	 * Description：<br>
	 * TODO 根据借款信息id查询单个 理财产品介绍实体
	 * 
	 * @author fengshiliang
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param BorrowId
	 * @return
	 */
	BizFinanceProducts getBizFinanceProductsByBorrowId(String BorrowId);
}
