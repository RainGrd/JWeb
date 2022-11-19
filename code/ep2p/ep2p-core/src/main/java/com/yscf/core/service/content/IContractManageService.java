/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 内容管理-合同管理服务层接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.BizBorrowAgreement;

/**
 * Description：<br> 
 * 内容管理-合同管理服务层接口
 * @author  Lin Xu
 * @date    2015年9月21日
 * @version v1.0.0
 */
public interface IContractManageService {
	/**
	 * Description：<br> 
	 * 通过条件修改数据
	 * @author  Lin Xu
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(BizBorrowAgreement record);
	
	/**
	 * Description：<br> 
	 * 分页查询借款合同数据信息
	 * @author  Lin Xu
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param bba
	 * @param info
	 * @return
	 */
	public PageList<BizBorrowAgreement> selectByPrimaryObj(BizBorrowAgreement bba,PageInfo info);

	/**
	 * 
	 * Description：<br> 
	 * 根据合同id获取合同对象
	 * @author  JunJie.Liu
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param borrowAgreementId
	 * @return
	 */
	public BizBorrowAgreement getById(String borrowAgreementId);

	/**
	 * 
	 * Description：<br> 
	 * 根据名称，获取协议
	 * @author  JunJie.Liu
	 * @date    2016年2月23日
	 * @version v1.0.0
	 * @param paramValue
	 * @return
	 */
	public BizBorrowAgreement getByName(String paramValue);
}


