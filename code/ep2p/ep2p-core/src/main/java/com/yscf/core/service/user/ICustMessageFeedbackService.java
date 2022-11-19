package com.yscf.core.service.user;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.user.CustMessageFeedback;

/**
 * Description：<br> 
 * 意见反馈
 * @author heng.wang
 * @date    2016年1月15日
 * @version v1.0.0
 */
public interface ICustMessageFeedbackService {
	/**
	 * Description：新增客户
	 * 
	 * @author heng.wang
	 * @date 2016年1月16日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insertSelective(CustMessageFeedback custMessageFeedback) throws FrameworkException;
}
