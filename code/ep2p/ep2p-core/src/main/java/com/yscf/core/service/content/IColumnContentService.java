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
 * 2015年9月18日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.content;

import java.util.List;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.pub.PubFile;

/**
 * Description：<br>
 * 
 * 
 * @author shiliang.feng
 * @date 2015年9月18日
 * @version v1.0.0
 */
public interface IColumnContentService {

	/**
	 * 
	 * Description：<br>
	 * 根据栏目查询lan
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param column
	 * @param info
	 * @return
	 */
	PageList<ColumnContent> selectColuContentByParameter(ColumnContent column,
			PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 批量更新栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param params
	 */
	void batchUpdateByPids(ColumnContent params);

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
	void updateByPrimaryKey(PubFile pubFileImg, PubFile pubFile,
			ColumnContent content);

	/**
	 * 
	 * Description：<br>
	 * 保存广告内容和附件
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月22日
	 * @version v1.0.0
	 * @param pubFileImg
	 *            上传的封面
	 * @param pubFile
	 *            上传的文件
	 * @param content
	 */
	void saveFileInfo(PubFile pubFileImg, PubFile pubFile, ColumnContent content);

	/**
	 * 
	 * Description：<br>
	 * 根据前台标示 查询状态有效的栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	public List<ColumnContent> selectColContentByWebTag(String webTag);

	/**
	 * 
	 * Description：<br>
	 * 前台方法 根据父栏目id 查询子栏目信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月17日
	 * @version v1.0.0
	 * @param parentId
	 * @return
	 */
	public List<ColumnContent> selectColumnContentByParentId(String parentId,
			Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * Description：<br>
	 * 根据前台标示 查询状态有效的栏目内容 主要是用于媒体报道和最新动态
	 * 
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param webTag
	 *            前台标示
	 * @param pageIndex
	 * @param pageSize
	 * @return webTag对应的二级栏目 以及二级栏目的三级内容
	 */
	public List<ColumnContent> selectColContentByWebTagSpecial(String webTag,
			Integer pageIndex, Integer pageSize);

	/**
	 * Description：<br>
	 * 批量已读 批量未读 pIds已逗号分隔
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param pids
	 * @param isReading
	 *            1 为未读 2为已读
	 * @return
	 */
	public Boolean batchUpdateIsReadingByPids(String isReading, String pIds);

	/**
	 * Description：<br>
	 * 主要是App接口方面 新手指引 系统公告
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	public List<ColumnContent> selectColContentListByWebTag(String webTag,
			String userId, Integer pageIndex, Integer pageSize, Integer isHome);

	/**
	 * Description：<br>
	 * 根据webTag 查询对应的栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月2日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	public ColumnContent selectContentByWebTag(String webTag);
}
