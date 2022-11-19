/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 协议内容模板管理服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.AgreementContTemp;

/**
 * Description：<br> 
 * 协议内容模板管理服务层
 * @author  Lin Xu
 * @date    2015年9月21日
 * @version v1.0.0
 */
public interface IAgreementTempManageService {
	
	/**
	 * Description：<br> 
	 * 通过查询的条件进行查询协议模板信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param agctemp
	 * @param pageinfo
	 * @return
	 */
	public PageList<AgreementContTemp> selectByPrimaryObj(AgreementContTemp agctemp, PageInfo pageinfo);
	
	/**
	 * Description：<br> 
	 * 通过一定的条件去查询数据进行更新数据
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(AgreementContTemp record);
	
	/**
	 * Description：<br> 
	 * 查询所有的协议模板信息
	 * @author  Lin Xu
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public List<AgreementContTemp> selectByAllObj(AgreementContTemp record);
	
}


