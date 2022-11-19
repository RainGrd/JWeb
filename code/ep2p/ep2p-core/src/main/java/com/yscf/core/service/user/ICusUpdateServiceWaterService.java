package com.yscf.core.service.user;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CusUpdateServiceWater;

public interface ICusUpdateServiceWaterService {
	/**
	 * Description：根据客户pid插入客服历史记录
	 * @author  heng.wang
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int insertCustWater(CusTomer cusTomer);
	/**
	 * Description：根据客户id查询客户历史详情
	 * @author  heng.wang
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusUpdateServiceWater> cusHistoryDetailedById(String pid,PageInfo info);
}
