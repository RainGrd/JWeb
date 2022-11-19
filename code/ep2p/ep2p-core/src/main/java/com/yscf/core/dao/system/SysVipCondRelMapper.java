package com.yscf.core.dao.system;

import java.util.List;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.system.SysVipCondRel;

/**
 * 
 * @ClassName : SysVipCondRelMapper
 * @Description : VIP与条件关系表数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月29日 下午3:43:31
 */
public interface SysVipCondRelMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(SysVipCondRel record);

	int insertSelective(SysVipCondRel record);

	SysVipCondRel selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysVipCondRel record);

	int updateByPrimaryKey(SysVipCondRel record);

	/**
	 * 
	 * @Description : 根据vipId查询条件数据
	 * @param vipId
	 *            VIP主键
	 * @return 条件List
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午4:50:08
	 */
	List<SysVipCondRel> seleCondRelByVipId(String vipId);

	/**
	 * 
	 * @Description : 根据vipId删除当前VIP下面的所有条件
	 * @param vipId
	 *            VIP主键
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午5:16:43
	 */
	void deleteByVipId(String vipId);

	/**
	 * 
	 * @Description : 批量新增
	 * @param list
	 *            VIP与条件信息List
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午5:30:56
	 */
	void insertSelectiveBatch(List<SysVipCondRel> list);
}