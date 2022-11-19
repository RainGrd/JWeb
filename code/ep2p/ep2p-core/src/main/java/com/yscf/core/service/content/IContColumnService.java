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
 * 2015年9月16日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.ContColumn;

/**
 * Description：<br>
 * 栏目管理 服务service
 * 
 * @author fengshiliang
 * @date 2015年9月16日
 * @version v1.0.0
 */
public interface IContColumnService {

	/**
	 * 
	 * Description：<br>
	 * 根据条件查询 内容栏目 查询全部请new ContColumn
	 * 
	 * @author fengshiliang
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	PageList<ContColumn> selectContentByParameter(ContColumn column,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 批量修改栏目信息
	 * 
	 * @author fengshiliang
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param params
	 */
	boolean batchUpdateByPids(ContColumn params);
}
