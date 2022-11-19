package com.yscf.core.service.user;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CustPoinWater;

/**
 * Description：<br>
 * 客户积分明细服务接口
 * 
 * @author heng.wang
 * @date 2015年9月29日
 * @version v1.0.0
 */
public interface ICustPoinWaterService {
	/**
	 * Description：根据客户id查询客户积分明细
	 * 
	 * @author heng.wang
	 * @date 2015年9月29日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CustPoinWater> selectAvailablePointDetailsById(String pid, PageInfo info);

	/**
	 * Description：查询客户积分明细列表
	 * 
	 * @author heng.wang
	 * @date 2015年10.8日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CustPoinWater> selectAllPage(CustPoinWater custPoinWater, PageInfo pageInfo);

	/**
	 * 
	 * @Description : 积分对外接口
	 * @param pointGetType
	 *            积分类型
	 * @param investmentMoney
	 *            金额(只有当类型为投资积分,金额才需要传入,其他类型金额为0)
	 * @param customerId
	 *            当前登录的客户ID
	 * @return 可获得的积分
	 * @Author : Qing.Cai
	 * @Date : 2015年12月1日 上午10:37:21
	 */
	Integer pointObtain(String pointGetType, BigDecimal investmentMoney, String customerId);

	/**
	 * 
	 * @Description : 获取预算积分
	 * @param investmentMoney
	 *            投资金额
	 * @param customerId
	 *            当前登录的客户ID
	 * @return 可获得的积分
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 下午4:47:31
	 */
	Integer pointObtainTemp(BigDecimal investmentMoney, String customerId);

	/**
	 * 
	 * @Description : 查询个人的可用积分and累计积分
	 * @param customerId
	 *            客户ID
	 * @return 积分对象
	 * @Author : Qing.Cai
	 * @Date : 2015年12月3日 下午5:57:31
	 */
	public CustPoinWater selectUserPoint(String customerId);

	/**
	 * 
	 * @Description : 查询个人积分明细
	 * @param custPoinWater
	 *            积分明细对象
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 个人积分明细
	 * @Author : Qing.Cai
	 * @Date : 2015年12月4日 上午10:21:51
	 */
	public List<CustPoinWater> selectUserPointDetail(CustPoinWater custPoinWater, Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 查询个人积分明细-API
	 * @param userId
	 *            用户ID
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 个人积分明细
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午10:21:50
	 */
	public List<CustPoinWater> selectUserPointDetailAPI(String userId, Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 查询个人积分明细的总记录数
	 * @param custPoinWater
	 *            积分明细对象
	 * @return 个人积分明细总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月4日 上午10:30:33
	 */
	public Integer selectUserPointDetailCount(CustPoinWater custPoinWater);
	
	/**
	 * 
	 * @Description : 添加银行卡后往客户积分流水表里插一条记录
	 * @param custPoinWater
	 * @Author : heng.wang
	 * @Date : 2016年2月16日 上午10:30:33
	 */
	public int insertCustPointWater(CustPoinWater custPoinWater);
}
