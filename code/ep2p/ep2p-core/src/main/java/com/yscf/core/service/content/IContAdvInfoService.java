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
 * 2015年9月24日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.adv.ContAdvInfo;

/**
 * Description：<br>
 * TODO 广告信息服务service接口
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
public interface IContAdvInfoService {

	/**
	 * 
	 * Description：<br>
	 * TODO 根据查询条件 查询
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param column
	 * @param info
	 * @return
	 */
	PageList<ContAdvInfo> selectContentByParameter(ContAdvInfo column,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * TODO 批量禁用和启动
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param params
	 */
	void batchUpdateByPids(ContAdvInfo params);

}
