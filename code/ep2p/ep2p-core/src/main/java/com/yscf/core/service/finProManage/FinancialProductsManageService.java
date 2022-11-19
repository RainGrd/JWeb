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
package com.yscf.core.service.finProManage;

import java.util.List;

import com.yscf.core.model.business.BizBorrowInfoVO;

/**
 * Description：<br>
 * TODO
 * 
 * @author shiliang.feng
 * @date 2015年11月13日
 * @version v1.0.0
 */
public interface FinancialProductsManageService {

	/**
	 * Description：<br>
	 * 查询理财产品
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param borrow
	 * @param pageIndex
	 * @param pageSize
	 * @param sortType
	 * @param sortModel
	 * @return
	 */
	public List<BizBorrowInfoVO> selectPublishedFinData(BizBorrowInfoVO borrow,
			Integer pageIndex, Integer pageSize, String sortType,
			String sortModel);

	/**
	 * 
	 * Description：<br>
	 * 查询总条数
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月20日
	 * @version v1.0.0
	 * @param borrow
	 *            查询条件
	 * @return
	 */
	public Integer selectFinProdCountPage(BizBorrowInfoVO borrow);
}
