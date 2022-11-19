package com.yscf.core.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustInterestTicket;

/**
 * Description：<br>
 * 客户管理服务接口
 * 
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
public interface ICusTomerService {

	/**
	 * Description：新增客户
	 * 
	 * @author heng.wang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(CusTomer cusTomer) throws FrameworkException;

	/**
	 * Description：查询客户列表
	 * 
	 * @author heng.wang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CusTomer> selectAll(CusTomer cusTomer, PageInfo pageInfo);

	/**
	 * Description：批量删除客户列表
	 * 
	 * @author heng.wang
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int deleteBtach(String pids);

	/**
	 * Description：查询客户列表，带分页功能
	 * 
	 * @author heng.wang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectAllPage(CusTomer usTomer, PageInfo info);

	/**
	 * Description：根据客户pid来更新客服id
	 * 
	 * @author heng.wang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updatCusIdByPrimaryKey(CusTomer cusTomer);

	/**
	 * Description：根据客户id查询列表
	 * 
	 * @author heng.wang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectCusByPid(String pid);

	/**
	 * Description：根据客户id查询客户详情
	 * 
	 * @author heng.wang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectCusHistoryDetailedById(String pid);

	/**
	 * 
	 * Description：<br>
	 * 获取近七日新增客户数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @return
	 */
	public Map<String, Object> selectNearlySevenNewCustomers();

	/**
	 * Description：根据客户id查询客户投资奖励红包详情
	 * 
	 * @author heng.wang
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectHongbaoDetailsById(String pid, PageInfo info);

	/**
	 * Description：查询客户id查客服列表
	 * 
	 * @author heng.wang
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CusTomer> selectCustomerServiceById(String userId);

	/**
	 * Description：查询vip等级
	 * 
	 * @author heng.wang
	 * @date 2015年10月28日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CusTomer> selectVipLevel();

	/**
	 * 
	 * @Description : 查看所有提交实名认证的客户列表
	 * @param cusTomer
	 *            客户对象
	 * @param info
	 *            分页对象
	 * @return 提交实名认证的客户列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月21日 下午4:18:17
	 */
	PageList<CusTomer> selectAuthenticationByCondition(CusTomer cusTomer, PageInfo info);

	/**
	 * 
	 * @Description : 客户导出
	 * @param cusTomer
	 *            客户对象
	 * @return
	 * @Author :heng.wang
	 * @Date : 2015年10月29日
	 */
	List<CusTomer> selectCustomer(CusTomer cusTomer, ExportObjectModel expvo);

	/**
	 * 
	 * Description：<br>
	 * 根据用户名，查询用户
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月30日
	 * @version v1.0.0
	 * @param paramValue
	 * @return
	 */
	public CusTomer selectCusByCustomerName(String paramValue);

	/**
	 * Description：<br>
	 * 统计用户（客户）总数集合 （今日新增用户数、今日实名认证人数、总用户数、实名认证总人数）
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @return Map<String,BigDecimal>
	 * @throws FrameworkException
	 */
	Map<String, Integer> selectUserSum() throws FrameworkException;

	/**
	 * Description：<br>
	 * 系统用户（客户）统计报表
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param type
	 *            (类型1：年、2月份)
	 * @param param
	 * @return
	 */
	Map<String, Object> userStatistical(String type, String param);

	/**
	 * 
	 * @Description : 查询提交实名认证人数
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午4:56:17
	 */
	int selectAuthenticationCount();

	/**
	 * 
	 * Description：<br>
	 * 验证手机号码是否存在
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月17日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	boolean validatePhoneNo(CusTomer customer);

	/**
	 * 
	 * Description：<br>
	 * 根据登录名查询客户信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月19日
	 * @version v1.0.0
	 * @param loginName
	 * @return
	 */
	CusTomer selectByLoginName(String loginName);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id查个人资料
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	List<CusTomer> selectPersonData(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id查加密因子
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月29日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	int selectIndexByPid(String custmerPid);

	/**
	 * 
	 * Description：<br>
	 * 校验用户交易密码,返回错误次数 ，如果为-1，则表示交易密码正确
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月30日
	 * @version v1.0.0
	 * @param pwd
	 * @param customerId
	 * @return
	 */
	int vailidateTradePassword(String pwd, String customerId) throws TradePwdIsBlankException, TradePwdFreezeException;

	/**
	 * 
	 * Description：<br>
	 * 根据条件查询客户信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月1日
	 * @version v1.0.0
	 * @param cusTomer
	 * @param info
	 * @return
	 */
	public PageList<CusTomer> selectPage(CusTomer cusTomer, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 根据客户ID查交易密码
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月1日
	 * @version v1.0.0
	 * @param cusTomer
	 * @param info
	 * @return
	 */
	List<CusTomer> selectTradPwd(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 根据客户ID保存交易密码
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param cusTomer
	 * @param info
	 * @return
	 */
	public int updateNewTradingPwd(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 更新密码
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public void resetPwd(CusTomer cusTomer,String isRegister) ;

	/**
	 * 
	 * Description：<br>
	 * 更新交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public void resetTradPwd(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id查推荐码
	 * 
	 * @author heng.wang
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	List<CusTomer> selectReferralCodeByCustd(String userId);

	/**
	 * 
	 * Description：<br>
	 * 按创建时间查最后注册用户的推荐码
	 * 
	 * @author heng.wang
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	List<CusTomer> selectLastReferralCode();

	/**
	 * 
	 * Description：<br>
	 * 根据推介码查询用户信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月5日
	 * @version v1.0.0
	 * @param referralCode
	 * @return
	 */
	CusTomer selectByReferralCode(String referralCode);

	/**
	 * 
	 * Description：<br>
	 * 查我分享过的合伙人
	 * 
	 * @author heng.wang
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param referralCode
	 * @return
	 */
	List<CusTomer> selectMySharePartner(String userId);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id修改客户名字
	 * 
	 * @author heng.wang
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param CusTomer
	 * @return
	 */
	public int updateCusNameByCusPid(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id修改客户联系地址
	 * 
	 * @author heng.wang
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param CusTomer
	 * @return
	 */
	public int updateHomeAddressByCusPid(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id查客户信息
	 * 
	 * @author heng.wang
	 * @date 2015年12月12日
	 * @version v1.0.0
	 * @param CusTomer
	 * @return
	 */
	public List<CusTomer> getUserInfoByPid(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id更新vip时长
	 * 
	 * @author heng.wang
	 * @date 2015年12月14日
	 * @version v1.0.0
	 * @param CusTomer
	 * @return
	 */
	public int updateCustVipTimeByCustId(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据pid 查询用户
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月12日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public CusTomer selectByPrimaryKey(String userId);

	/**
	 * 
	 * Description：<br>
	 * 根据客户名查看客户是否存在
	 * 
	 * @author heng.wang
	 * @date 2015年12月18日
	 * @version v1.0.0
	 * @param CusTomer
	 * @return
	 */
	public CusTomer selectByUserName(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 查询媒体动态
	 * 
	 * @author heng.wang
	 * @date 2015年12月21日
	 * @version v1.0.0
	 * @param CusTomer
	 * @return
	 */
	public List<ColumnContent> selectNewDynamic();

	/**
	 * 
	 * Description：<br>
	 * 根据用户id获取用户
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public CusTomer getByUserId(String userId);

	/**
	 * 
	 * Description：<br>
	 * 获取用户isVip字段，判断用户是否vip
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public boolean isVip(String userId);

	/**
	 * 
	 * @Description : 批量修改客户VIP信息
	 * @param list
	 *            客户集合
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午4:55:18
	 */
	public void batchUpdateVip(List<CusTomer> list) throws FrameworkException;

	/**
	 * 
	 * @Description : 批量修改客户积分信息
	 * @param list
	 *            客户集合
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午9:10:45
	 */
	public void batchUpdatePoin(List<CusTomer> list) throws FrameworkException;

	/**
	 * 
	 * @Description : 批量修改客户经验信息
	 * @param list
	 *            客户集合
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午9:11:08
	 */
	public void batchUpdateExperience(List<CusTomer> list) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 查询借款人和该借款所有的投标人
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @return
	 */
	public List<CusTomer> selectAllRelateUsers(BizBorrow bizBorrow, List<BizBorrowDetail> bids);

	/**
	 * 
	 * Description：<br>
	 * api:校验原密码是否正确
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public int validateOriginalPassWord(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api:修改登录密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public int updateLoginPassWord(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api:设置交易密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public int updateTradPassWord(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api:校验交易密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public int validataTradPassWord(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api:查我的推荐码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public List<CusTomer> selectReferralCode(String userId);

	/**
	 * 
	 * Description：<br>
	 * api:查我推荐的客户列表
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public List<CusTomer> selectMyReferralList(String myReferralCode);

	/**
	 * 
	 * Description：<br>
	 * api:设置交易密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月7日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public int setTradPassWord(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api:绑定手机号码
	 * 
	 * @author heng.wang
	 * @date 2016年1月14日
	 * @version v1.0.0
	 * @param customer
	 * @return
	 */
	public int bindPhone(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 获取用户交易冻结时间
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月14日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public Date getTradeFreezeTime(String userId);

	/**
	 * 
	 * Description：<br>
	 * 修改客户名称
	 * 
	 * @author heng.wang
	 * @date 2016年1月18日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public int updateCustName(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api查vip时长
	 * 
	 * @author heng.wang
	 * @date 2016年1月21日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public int selectVipTime(String userId, int month);

	/**
	 * 
	 * Description：<br>
	 * api更新购买vip时长后的viptime
	 * 
	 * @author heng.wang
	 * @date 2016年1月21日
	 * @version v1.0.0
	 * @param cusTomer
	 * @return
	 */
	public int updateVipTime(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 批量更新客户VIP等级
	 * 
	 * @author Yu.Zhang
	 * @date 2016年1月12日
	 * @version v1.0.0
	 * @param list
	 *            客户集合
	 * @param Type
	 *            经验值类型
	 * @param createUserId
	 *            创建人
	 * @return
	 */
	public int batchUpdateVipLeavel(List<CusTomer> list, String createUserId);

	/**
	 * 
	 * @Description : API--查询客户的卡劵
	 * @param customerId
	 *            客户ID
	 * @return 我的卡劵
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 上午10:39:47
	 */
	public List<CustInterestTicket> selectUserCardVolume(String customerId);

	/**
	 * 
	 * @Description : API--查询我的卡劵-已失效or已过期
	 * @param customerId
	 *            客户ID
	 * @return 我的过期or失效卡劵
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 下午3:58:04
	 */
	public List<CustInterestTicket> selectUserCardVolumeHasExpired(String customerId);

	/**
	 * 
	 * @Description : API--查询客户的体验金列表-可用
	 * @param customerId
	 *            客户ID
	 * @return 我的体验金劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午6:43:39
	 */
	public List<ActExpActDetail> selectCustTomerExperienceGold(String customerId);

	/**
	 * 
	 * @Description : API--查询客户的加息劵列表-未使用
	 * @param customerId
	 *            客户ID
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 加息劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月28日 上午11:22:57
	 */
	public List<CustInterestTicket> selectUserInterestTicketAPI(String customerId, Integer pageIndex, Integer pageSize);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过客户Id得到实名认证情况
	 * @author  Jie.Zou
	 * @date    2016年2月2日
	 * @version v1.0.0
	 * @param custId
	 * @return
	 */
	public String selectIsAttestationById(String custId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID更新是否vip
	 * @author  wangheng
	 * @date    2016年2月2日
	 * @version v1.0.0
	 * @param custId
	 * @return
	 */
	public void updateIsVipByCustId(String custId);
	
	/**
	 * 
	 * Description：<br> 
	 * 判断用户是否逾期,true:逾期   false:未逾期
	 * @author  JunJie.Liu
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public boolean isOverdue(String userId);
	
	
	/**
	 * 
	 * Description：<br> 
	 * 每天批量更新未禁用，非黑名单，是VIP的客户的经验值
	 * @author  Yu.Zhang
	 * @date    2016年3月6日
	 * @version v1.0.0
	 */
	public void  updateEmpiricalValueToDay();
	
	/**
	 * 
	 * Description：<br> 
	 * 为客户分配客服
	 * @author  wangheng
	 * @date    2016年3月7日
	 * @version v1.0.0
	 */
	public CusTomer cusDistribution(CusTomer customer); 

	/**
	 * Description：<br> 
	 * 活动满足条件的客户
	 * @author  shiliang.feng
	 * @date    2016年3月8日
	 * @version v1.0.0
	 * @param conditionValue
	 * @return
	 */
	public List<CusTomer> selectCustByCondition(String conditionValue);
	/**
	 * Description：<br> 
	 * 当天生日的客户
	 * @author  shiliang.feng
	 * @date    2016年3月8日
	 * @version v1.0.0
	 * @return
	 */
	public List<CusTomer> selectCustomerByBirthdayToDay();
	
	/**
	 * 
	 * Description：<br> 
	 * 每天减去Vip时长
	 * @author  wangheng
	 * @date    2016年3月7日
	 * @version v1.0.0
	 */
	public void reduceVipTimeByDay();
	
	/**
	 * 
	 * Description：<br> 
	 * 更新客户是否是vip
	 * @author  wangheng
	 * @date    2016年3月7日
	 * @version v1.0.0
	 */
	public void updateCustIsVip();
	
	/**
	 * 
	 * Description：<br> 
	 * 更新移动设备编号
	 * @author  Yu.Zhang
	 * @date    2016年5月13日
	 * @version v1.0.0
	 * @param mobileDeviceMachineCode
	 * @param pid
	 */
	public Integer updateMobileDeviceMachineCode(String mobileDeviceMachineCode,String pid);
	
	/**
	 * 
	 * Description：<br> 
	 * 客户禁止或启用的定时任务
	 * @author wangheng
	 * @date    2016年3月11日
	 * @version v1.0.0
	 */
	public void prohibitOrStartOperation();
}
