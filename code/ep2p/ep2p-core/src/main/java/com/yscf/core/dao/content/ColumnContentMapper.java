package com.yscf.core.dao.content;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.ColumnContent;

/**
 * 
 * Description：<br>
 * 栏目内容管理
 * 
 * @author fengshiliang
 * @date 2015年9月18日
 * @version v1.0.0
 */
@MapperScan("columnContentMapper")
public interface ColumnContentMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(ColumnContent record);

	public int insertSelective(ColumnContent record);

	public ColumnContent selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(ColumnContent record);

	public int updateByPrimaryKeyWithBLOBs(ColumnContent record);

	public int updateByPrimaryKey(ColumnContent record);

	/**
	 * 
	 * Description：<br>
	 * 根据栏目查询栏目内容
	 * 
	 * @author fengshiliang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param column
	 * @param info
	 * @return
	 */
	public PageList<ColumnContent> selectColuContentByParameter(
			@Param("map") ColumnContent column, PageInfo info);

	public void batchUpdateByPids(@Param("map") Map<String, Object> map);

	/**
	 * Description：<br>
	 * 根据前台标示 查询状态有效的栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	public List<ColumnContent> selectAdvContentByWebTag(String webTag);

	/**
	 * Description：<br>
	 * 前台方法 根据父栏目id 查询子栏目信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月17日
	 * @version v1.0.0
	 * @param parentId
	 * @return
	 */
	public List<ColumnContent> selectColumnContentByParentId(
			@Param("parentId") String parentId,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * Description：<br>
	 * 查媒体最新动态
	 * 
	 * @author heng.wang
	 * @date 2015年12月21日
	 * @version v1.0.0
	 * @param parentId
	 * @return
	 */
	public List<ColumnContent> selectNewDynamic();

	/**
	 * 
	 * Description：<br>
	 * 根据二级栏目的webtag 查询二级栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月22日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	public ColumnContent selectContentMiddleByWebTag(String webTag);

	/**
	 * Description：<br>
	 * 批量修改 是否阅读状态
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	public Boolean batchUpdateIsReadingByPids(
			@Param("map") Map<String, Object> map);

	/**
	 * Description：<br>
	 * 主要是App接口方面 新手指引 系统公告
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param webTag
	 *            前台标示
	 * @param pageIndex
	 * @param pageSize
	 * @param isHome
	 *            是否在首页 为空则在是首页轮播广告（条件加上有效时间）
	 * @return
	 */
	public List<ColumnContent> selectColContentListByWebTag(
			@Param("webTag") String webTag,
			@Param("customerId") String customerId,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize, @Param("isHome") Integer isHome);

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