/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借款后台管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrowAfter;
import com.yscf.core.model.comm.ExportObjectModel;

/**
 * Description：<br> 
 * 借款后台管理
 * @author  Lin Xu
 * @date    2015年10月13日
 * @version v1.0.0
 */
public interface IBorrowAfterManageService {
	
	/**
	 * Description：<br> 
	 * 通过条件查询所有的信息数据
	 * @author  Lin Xu
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param bbaft
	 * @return
	 */
	public PageList<BizBorrowAfter> selectBorrowAfterAll(BizBorrowAfter bba,PageInfo pageinfo);
	
	/**
	 * Description：<br> 
	 * 通过导入的条件进行查询数据信息
	 * @author  Lin Xu
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param bba
	 * @param expm
	 * @return
	 */
	public List<BizBorrowAfter> selectBorrowAfter(BizBorrowAfter bba,ExportObjectModel expm);
	
	
	/**
	 * Description：<br> 
	 * 统计下月待还款的记录数
	 * @author  Lin Xu
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	public Integer selectTotalReplayNumbers();
	
}


