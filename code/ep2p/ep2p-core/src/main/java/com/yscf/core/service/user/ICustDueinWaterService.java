package com.yscf.core.service.user;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustDueinWater;

/**
 * Description：<br> 
 * 客户总共代收明细服务接口
 * @author  heng.wang
 * @date    2015年10月14日
 * @version v1.0.0
 */
public interface ICustDueinWaterService {
	/**
	 * Description：根据客户id查询总共待收明细
	 * @author  heng.wang
	 * @date    2015年10月14日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustDueinWater> selectCusTotalStayDetailsById(String pid,PageInfo info);
	/**
	 * Description：查询客户总共待收明细列表
	 * @author  heng.wang
	 * @date    2015年10月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustDueinWater> selectAllPage(CustDueinWater custDueinWater, PageInfo pageInfo);
	
	
	/**
	 * Description：根据客户id查询总共待收明细
	 * @author  heng.wang
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustDueinWater> selectCollectInterestDetailsById(String pid,PageInfo info);
}
