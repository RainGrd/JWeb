package com.yscf.core.dao.user;

import java.util.List;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.user.CustSignIn;

/**
 * 
 * @ClassName : CustSignInMapper
 * @Description : 签到信息数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年12月7日 下午3:29:11
 */
public interface CustSignInMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustSignIn record);

	int insertSelective(CustSignIn record);

	CustSignIn selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustSignIn record);

	int updateByPrimaryKey(CustSignIn record);

	/**
	 * 
	 * @Description : 查询签到达人榜(签到次数前五名)
	 * @return 签到集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午4:01:22
	 */
	public List<CustSignIn> selectSignInDarenCharts();

	/**
	 * 
	 * @Description : 判断是否签到
	 * @param customerId
	 *            用户ID
	 * @return 0：未签到 其他：已签到
	 * @Author : Qing.Cai
	 * @Date : 2015年12月9日 下午10:56:24
	 */
	public int checkWhetheSignIn(String customerId);

	/**
	 * 
	 * @Description : 查询客户签到总数
	 * @param customerId
	 *            客户ID
	 * @return 签到总天数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月7日 下午4:40:22
	 */
	public int selectUserSignInSum(String customerId);
}