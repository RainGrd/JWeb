package com.yscf.core.dao.content.adv;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;

/**
 * 
 * Description：<br>
 * 广告内容管理DAO
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@MapperScan("contAdvContentMapper")
public interface ContAdvContentMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(ContAdvContent record);

	public int insertSelective(ContAdvContent record);

	public ContAdvContent selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(ContAdvContent record);

	public int updateByPrimaryKey(ContAdvContent record);

	/**
	 * 
	 * Description：<br>
	 * 根据广告栏目 查询 广告内容
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param column
	 * @param info
	 * @return
	 */
	public PageList<ColumnContent> selectAdvContentByParameter(
			@Param("map") ContAdvContent column, PageInfo info);

	public void batchUpdateByPids(@Param("map") Map<String, Object> map);

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
	public List<ContAdvContent> selectAdvContentByWebTag(String avdCode);
}