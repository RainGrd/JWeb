package com.yscf.core.dao.user;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustAuthenticationHis;

/**
 * 
 * @ClassName : custAuthenticationHisMapper
 * @Description : 客户实名认证历史数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月21日 上午10:03:15
 */
public interface CustAuthenticationHisMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustAuthenticationHis record);

	int insertSelective(CustAuthenticationHis record);

	CustAuthenticationHis selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustAuthenticationHis record);

	int updateByPrimaryKey(CustAuthenticationHis record);

	/**
	 * 
	 * @Description : 查询认证历史记录
	 * @param custAuthenticationHis
	 *            认证历史对象
	 * @param pageInfo
	 *            分页对象
	 * @return 用户的认证历史记录
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 上午11:06:19
	 */
	PageList<CustAuthenticationHis> selectAllPage(@Param("custAuthenticationHis")CustAuthenticationHis custAuthenticationHis, PageInfo pageInfo);
}