package com.yscf.core.dao.user;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CusBirWarnHis;

/**
 * 
 * @ClassName : CusBirWarnHisMapper
 * @Description : VIP生日提醒历史数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月24日 下午2:38:06
 */
public interface CusBirWarnHisMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CusBirWarnHis record);

	int insertSelective(CusBirWarnHis record);

	CusBirWarnHis selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CusBirWarnHis record);

	int updateByPrimaryKey(CusBirWarnHis record);

	/**
	 * 
	 * @Description : VIP生日提醒列表,带分页
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @param pageInfo
	 *            分页对象
	 * @return 今年VIP生日提醒列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午3:50:02
	 */
	PageList<CusBirWarnHis> selectAllPage(@Param("cusBirWarnHis") CusBirWarnHis cusBirWarnHis, PageInfo pageInfo);

	/**
	 * 
	 * @Description : 导出查询
	 * @param map
	 *            条件
	 * @return 今年VIP生日提醒列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午11:42:47
	 */
	List<CusBirWarnHis> selectAllPageDetailExport(HashMap<String, Object> map);

	/**
	 * 
	 * @Description : 查询今天VIP生日提醒数
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午5:08:52
	 */
	int selectVipBirCount();

}