package com.yscf.core.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustExperienceWater;

/**
 * 
 * @ClassName : CustExperienceWaterMapper
 * @Description : 客户经验历史纪录数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年12月29日 下午9:43:29
 */
public interface CustExperienceWaterMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustExperienceWater record);

	int insertSelective(CustExperienceWater record);

	CustExperienceWater selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustExperienceWater record);

	int updateByPrimaryKey(CustExperienceWater record);

	/**
	 * Description：根据pid查询客户经验明细,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustExperienceWater> selectExperienceDetailsById(@Param("custExperienceWater") CustExperienceWater custExperienceWater, PageInfo info);

	/**
	 * Description：客户经验列表条件查询
	 * 
	 * @author heng.wang
	 * @date 2015年10月08日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustExperienceWater> selectAllPage(@Param("custExperienceWater") CustExperienceWater custExperienceWater, PageInfo info);

	/**
	 * 
	 * @Description : 批量新增经验流水记录信息
	 * @param list
	 *            经验流水list集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午9:33:05
	 */
	public void insertBatch(List<CustExperienceWater> list);
	
	/**
	 * 
	 * @Description : 批量新增经验流水记录信息
	 * @param list
	 *            经验流水list集合
	 * @Author : wangheng
	 * @Date : 2016年3月7日 下午9:33:05
	 */
	public void batchUpdateCustExperienceWater(List<CustExperienceWater> list);
}