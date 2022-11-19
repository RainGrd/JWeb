package com.yscf.core.dao.content;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.content.ColumnContentFileRel;

/**
 * 
 * Description：<br>
 * 栏目内容文件关系表
 * 
 * @author fengshiliang
 * @date 2015年11月5日
 * @version v1.0.0
 */
public interface ColumnContentFileRelMapper extends
		IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(ColumnContentFileRel record);

	int insertSelective(ColumnContentFileRel record);

	ColumnContentFileRel selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(ColumnContentFileRel record);

	int updateByPrimaryKey(ColumnContentFileRel record);

	int deleteByContentId(String pid);
}