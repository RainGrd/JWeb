package com.yscf.core.service.user;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustExperienceWater;

/**
 * Description：<br>
 * 客户经验明细服务接口
 * 
 * @author heng.wang
 * @date 2015年10月9日
 * @version v1.0.0
 */
public interface ICustExperienceService {
	/**
	 * Description：根据客户id查询客户经验明细
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustExperienceWater> selectExperienceDetailsById(String pid, PageInfo info);

	/**
	 * Description：查询客户经验明细列表
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CustExperienceWater> selectAllPage(CustExperienceWater custExperienceWater, PageInfo pageInfo);

	/**
	 * 
	 * @Description : 计算投资经验值
	 * @param investmentAmount
	 *            投资总额
	 * @param projectNum
	 *            项目总期数
	 * @param userId
	 *            用户ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 下午5:05:30
	 */
	public void updateUserExperience(BigDecimal investmentAmount, Integer projectNum, String userId) throws FrameworkException;
}
