package com.yscf.core.dao.content.adv;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.content.adv.ContAdvInfo;

/**
 * 
 * Description：<br>
 * 广告信息 管理DAO
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@MapperScan("contAdvInfoMapper")
public interface ContAdvInfoMapper extends IBaseDao<BaseEntity, String> {
	public int deleteByPrimaryKey(String pid);

	public int insert(ContAdvInfo record);

	public int insertSelective(ContAdvInfo record);

	public ContAdvInfo selectByPrimaryKey(String pid);

	public int updateByPrimaryKeySelective(ContAdvInfo record);

	public int updateByPrimaryKey(ContAdvInfo record);

	/**
	 * 
	 * Description：<br>
	 * 根据查询条件查询 广告信息
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param column
	 * @param info
	 * @return
	 */
	public PageList<ContAdvInfo> selectContentByParameter(
			@Param("map") ContAdvInfo column, PageInfo info);

	public boolean batchUpdateByPids(@Param("map") Map<String, Object> map);
}