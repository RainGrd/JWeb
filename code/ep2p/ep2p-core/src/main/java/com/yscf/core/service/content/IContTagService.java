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
 * 2015年9月18日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import java.util.List;

import com.yscf.core.model.content.ContTag;

/**
 * Description：<br>
 * 
 * @author fengshiliang
 * @date 2015年9月18日
 * @version v1.0.0
 */
public interface IContTagService {
	/**
	 * 
	 * Description：<br>
	 * 已选择的标签
	 * 
	 * @author fengshiliang
	 * @date 2015年9月21日
	 * @param contentId
	 *            栏目id
	 * @version v1.0.0
	 * @return
	 */
	public List<ContTag> selectedTag(String contentId);

	/**
	 * 
	 * Description：<br>
	 * 每次修改的时候先删除原来栏目内容绑定的标签
	 * 
	 * @author fengshiliang
	 * @date 2015年9月23日
	 * @version v1.0.0
	 * @param contentId
	 */
	public void deleteByColuContId(String contentId);
}
