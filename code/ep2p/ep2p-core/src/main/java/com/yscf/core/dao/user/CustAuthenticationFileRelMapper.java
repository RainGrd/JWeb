package com.yscf.core.dao.user;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.user.CustAuthenticationFileRel;

/**
 * 
 * @ClassName : CustAuthenticationFileRelMapper
 * @Description : 实名认证图片与文件数据层接口
 * @Author : Qing.Cai
 * @Date : 2016年1月25日 下午5:03:28
 */
public interface CustAuthenticationFileRelMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustAuthenticationFileRel record);

	int insertSelective(CustAuthenticationFileRel record);

	CustAuthenticationFileRel selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustAuthenticationFileRel record);

	int updateByPrimaryKey(CustAuthenticationFileRel record);
}