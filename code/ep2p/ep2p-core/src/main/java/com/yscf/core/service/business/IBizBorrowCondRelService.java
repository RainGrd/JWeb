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
 * 2015年10月20日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.util.List;

import com.yscf.core.model.business.BizBorrowCondRel;
import com.yscf.core.model.business.BizBorrowInfoVO;

/**
 * Description：<br>
 * 借款信息与条件关系表 服务接口
 * 
 * @author fengshiliang
 * @date 2015年10月20日
 * @version v1.0.0
 */
public interface IBizBorrowCondRelService {

	/**
	 * 
	 * Description：<br>
	 * 批量新增条件
	 * 
	 * @author fengshiliang
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param condIds
	 */
	public void batchAdd(BizBorrowInfoVO borrowInfoVO);

	/**
	 * 
	 * Description：<br>
	 * 修改status 为-1
	 * 
	 * @author fengshiliang
	 * @date 2015年10月20日
	 * @version v1.0.0
	 */
	public void deleteByBorrowId(String borrowId) throws Exception;

	/**
	 * 
	 * Description：<br>
	 * 根据标的id获取已参与条件
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param objectId
	 * @return
	 */
	public List<BizBorrowCondRel> selectNewStandardConRelByObjectId(
			String objectId);
}
