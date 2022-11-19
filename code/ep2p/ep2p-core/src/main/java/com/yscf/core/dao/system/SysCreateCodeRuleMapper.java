package com.yscf.core.dao.system;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.system.SysCreateCodeRule;

/**
 * 
 * @ClassName : SysCreateCodeRuleMapper
 * @Description : 编号生成规则数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年9月16日 下午5:46:04
 */
@MapperScan("sysCreateCodeRuleMapper")
public interface SysCreateCodeRuleMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(SysCreateCodeRule record);

	int insertSelective(SysCreateCodeRule record);

	SysCreateCodeRule selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(SysCreateCodeRule record);

	int updateByPrimaryKey(SysCreateCodeRule record);

	/**
	 * 
	 * @Description : 查询编号生成规则信息,带分页
	 * @param sysCreateCodeRule
	 *            编号生成规则对象
	 * @param info
	 *            分页对象
	 * @return 编号生成规则对象集合
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 上午10:40:46
	 */
	PageList<SysCreateCodeRule> selectAllPage(@Param("sysCreateCodeRule") SysCreateCodeRule sysCreateCodeRule, PageInfo info);

	/**
	 * 
	 * @Description : 查询序号
	 * @param sysCreateCodeRule
	 *            编号生成规则对象
	 * @return 序号
	 * @Author : Qing.Cai
	 * @Date : 2015年9月16日 上午10:54:16
	 */
	String selectOrderByPrefixAnDateTime(SysCreateCodeRule sysCreateCodeRule);

	/**
	 * 
	 * @Description : 查询序号(不带年月日)
	 * @param sysCreateCodeRule
	 *            编号生成规则对象
	 * @return 序号
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午4:14:46
	 */
	String selectOrderByPrefix(SysCreateCodeRule sysCreateCodeRule);
}