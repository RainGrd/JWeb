package com.yscf.core.dao.pub;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.pub.PubFile;

public interface PubFileMapper extends IBaseDao<BaseEntity, String> {
	
    int deleteByPrimaryKey(String pid);

    int insert(PubFile record);

    int insertSelective(PubFile record);

    PubFile selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(PubFile record);

    int updateByPrimaryKey(PubFile record);
}