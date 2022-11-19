package com.yscf.core.service.user;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustLargessVipWater;


public interface ICustLargessVipWaterService {
	/**
	 * Description：保存到赠送vip客户流水表
	 * @author  heng.wang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(CustLargessVipWater custLargessVipWater);
	/**
	 * Description：根据客户id查询赠送vip历史详情
	 * @author  heng.wang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustLargessVipWater> getVipHistoryDetailedById(String pid,PageInfo info);
}
