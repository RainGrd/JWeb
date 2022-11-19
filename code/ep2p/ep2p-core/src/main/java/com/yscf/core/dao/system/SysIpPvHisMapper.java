package com.yscf.core.dao.system;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysIpPvHis;

/**
 * 
 * @ClassName : SysIpPvHisMapper
 * @Description : IP访问历史查看记录数据层
 * @Author : Qing.Cai
 * @Date : 2015年11月11日 下午3:11:11
 */
public interface SysIpPvHisMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(Integer id);

	int insert(SysIpPvHis record);

	int insertSelective(SysIpPvHis record);

	SysIpPvHis selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SysIpPvHis record);

	int updateByPrimaryKey(SysIpPvHis record);
}