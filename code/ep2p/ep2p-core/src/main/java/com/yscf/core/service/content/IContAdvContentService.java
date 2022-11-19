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
 * 2015年9月24日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.pub.PubFile;

/**
 * Description：<br>
 * 广告内容服务service接口
 * 
 * @author shiliang.feng
 * @date 2015年9月24日
 * @version v1.0.0
 */
public interface IContAdvContentService {

	/**
	 * 
	 * Description：<br>
	 * 根据广告栏目id 查询广告内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param column
	 * @param info
	 * @return
	 */
	PageList<ColumnContent> selectAdvContentByParameter(ContAdvContent column,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 批量禁用和启动
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param params
	 */
	void batchUpdateByPids(ContAdvContent params);

	/**
	 * 
	 * Description：<br>
	 * 保存广告内容和附件
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月10日
	 * @version v1.0.0
	 * @param pubFile
	 * @param content
	 */
	void saveFileInfo(PubFile pubFile, ContAdvContent content);

	/**
	 * 
	 * Description：<br>
	 * 修改广告内容 同时新增一条附件记录
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月10日
	 * @version v1.0.0
	 * @param pubFile
	 * @param content
	 */
	void updateByPrimaryKey(PubFile pubFile, ContAdvContent content);

	/**
	 * 
	 * Description：<br>
	 * 根据前台标示 查询状态有效的广告内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	public List<ContAdvContent> selectAdvContentByWebTag(String webTag);

}
