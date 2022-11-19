package com.yscf.core.service.user;

import java.awt.FontFormatException;

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustWitStatusWater;

public interface ICustWitStatusWaterService {
	/**
	 * Description：保存客户提现状态流水表
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(CustWitStatusWater custWitStatusWater) throws FontFormatException;
	/**
	 * Description：根据客户id查询客户提现状态历史详情
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustWitStatusWater> selectCusStatusChangeHistoryById(String pid,PageInfo info);
}
