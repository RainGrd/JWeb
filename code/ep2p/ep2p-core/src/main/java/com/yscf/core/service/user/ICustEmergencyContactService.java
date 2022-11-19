package com.yscf.core.service.user;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustEmergencyContact;

/**
 * Description：<br> 
 * 紧急联系人信息
 * @author  heng.wang
 * @date    2015年11月12日
 * @version v1.0.0
 */
public interface ICustEmergencyContactService {
	/**
	 * Description：根据客户id修改用户名
	 * @author  heng.wang
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return int
	 * @throws FrameworkException
	 */
	public int updateCusNameByCusPid(CustEmergencyContact contact);
	/**
	 * Description：根据客户id查询客户旧密码
	 * @author  heng.wang
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustEmergencyContact> selectOriginalByCusPid(String cusPid);
	
	/**
	 * Description：修改登录新密码
	 * @author  heng.wang
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return int
	 * @throws FrameworkException
	 */
	public int saveNewPassWord(CustEmergencyContact contact);
	/**
	 * Description：修改交易密码
	 * @author  heng.wang
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return int
	 * @throws FrameworkException
	 */
	public int saveNewTradePassWord(CustEmergencyContact contact);
	/**
	 * Description：根据客户账户查询登录系统时间
	 * @author  heng.wang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return int
	 * @throws FrameworkException
	 */
	public PageList<CustEmergencyContact> selectLoginTimeByAccount(String account);
	/**
	 * Description：修改紧急联系人信息
	 * @author  heng.wang
	 * @date    2015年12月8日
	 * @version v1.0.0
	 * @param userName 系统客服
	 * @return int
	 * @throws FrameworkException
	 */
	public int updateEmergencyContact(CustEmergencyContact contact);
}
