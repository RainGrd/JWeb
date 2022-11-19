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
 * 2015年9月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.util.List;

import com.yscf.core.model.business.BizBorrowFileRel;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
public interface IBizBorrowFileRelService {


	/**
	 * 
	 * Description：<br> 
	 * 数据查询
	 * @author  Yu.Zhang
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param borrow
	 * @return
	 */
	public List<BizBorrowFileRel> selectAll(BizBorrowFileRel borrow);

	/**
	 * 
	 * Description：<br> 
	 * 更新
	 * @author  Yu.Zhang
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param borrow
	 * @return 
	 */
	public int updateByPrimaryKeySelective(BizBorrowFileRel borrow);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款Id查询关联的上传文件信息
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param borrowId 借款ID
	 * @return
	 */
	public List<BizBorrowFileRel> selectByBorrowId(String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 新增
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param borrowFile
	 * @return
	 */
	public int insertSelective(BizBorrowFileRel borrowFile);
}


