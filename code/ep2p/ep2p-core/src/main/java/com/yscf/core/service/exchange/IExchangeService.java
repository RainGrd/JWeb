package com.yscf.core.service.exchange;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.exception.PointNotEnoughException;

/**
 * 
 * @ClassName : IExchangeService
 * @Description : 兑换服务层接口
 * @Author : Qing.Cai
 * @Date : 2016年1月3日 下午6:29:15
 */
public interface IExchangeService {

	/**
	 * 
	 * @Description : 积分兑换话费
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @param phoneNo
	 *            兑换成功的手机号码
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:03:05
	 */
	public String exchangePhoneFees(String userId, String dictionaryContentId, String phoneNo) throws FrameworkException, PointNotEnoughException;

	/**
	 * 
	 * @Description : 积分兑换加息卷
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:03:25
	 */
	public void exchangeInterestTicket(String userId, String dictionaryContentId) throws FrameworkException, PointNotEnoughException;

	/**
	 * 
	 * @Description : 积分兑换VIP
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @return VIP到期时间
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月13日 下午2:26:45
	 */
	public String exchangeVIP(String userId, String dictionaryContentId) throws FrameworkException, PointNotEnoughException;

	/**
	 * 
	 * @Description : 积分兑换现金
	 * @param userId
	 *            客户ID
	 * @param dictionaryContentId
	 *            兑换的数据字典内容的ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:03:49
	 */
	public void exchangeCash(String userId, String dictionaryContentId) throws FrameworkException, PointNotEnoughException;
}
