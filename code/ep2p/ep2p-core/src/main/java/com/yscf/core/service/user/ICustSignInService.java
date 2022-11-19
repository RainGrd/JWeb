package com.yscf.core.service.user;

import java.util.List;

import com.yscf.core.model.user.CustSignIn;

/**
 * 
 * @ClassName : ICustSignInService
 * @Description : 签到信息业务层接口
 * @Author : Qing.Cai
 * @Date : 2015年12月7日 下午3:34:49
 */
public interface ICustSignInService {

	/**
	 * 
	 * @Description : 查询签到达人榜(签到次数前五名)
	 * @return 签到集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午3:56:21
	 */
	public List<CustSignIn> selectSignInDarenCharts();

	/**
	 * 
	 * @Description : 查询客户签到总数
	 * @param customerId
	 *            客户ID
	 * @return 签到总天数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午4:38:07
	 */
	public int selectUserSignInSum(String customerId);

	/**
	 * 
	 * @Description : 新增签到信息
	 * @param custSignIn
	 *            签到信息
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午3:55:48
	 */
	public int custSignInEdit(CustSignIn custSignIn);

	/**
	 * 
	 * @Description : 判断是否签到
	 * @param customerId
	 *            用户ID
	 * @return 0：未签到 其他：已签到
	 * @Author : Qing.Cai
	 * @Date : 2016年1月19日 上午10:39:34
	 */
	public int checkWhetheSignIn(String customerId);

}
