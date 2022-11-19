/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 协议内容服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.AgreementContent;

/**
 * Description：<br> 
 * 协议内容服务层
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
public interface IAgreementContextService {

	/**
	 * Description：<br> 
	 * 通过查询的条件进行查询协议内容信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param agctemp
	 * @param pageinfo
	 * @return
	 */
	public PageList<AgreementContent> selectByPrimaryObj(AgreementContent agccont, PageInfo pageinfo);
	
	/**
	 * Description：<br> 
	 * 批量修改对象信息
	 * @author  Lin Xu
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param agccont
	 */
	public void updateBatchObj(List<AgreementContent> agccont);
	
	/**
	 * Description：<br> 
	 * 通过协议模板内容的id查询对应的模板信息
	 * @author  Lin Xu
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param atempid
	 * @return
	 */
	public PageList<AgreementContent> selectByPrimaryObjAll(String atempid);

	public AgreementContent getById(String borrowAgreementId);
	
}


