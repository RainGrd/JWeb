package com.yscf.core.dao.user;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.util.YTDDto;

/**
 * Description：客户数据访问层接口
 * 
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
@MapperScan("cusTomerMapper")
public interface CusTomerMapper extends IBaseDao<BaseEntity, String> {
	/**
	 * Description：根据PID来删除
	 * 
	 * @author heng.wang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	int deleteByPrimaryKey(String pid);

	/**
	 * Description：新增客户
	 * 
	 * @author heng.wang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	int insert(CusTomer record);

	int insertSelective(CusTomer record);

	/**
	 * Description：根据PID查客户
	 * 
	 * @author heng.wang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	CusTomer selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CusTomer record);

	/**
	 * Description：根据PID来更新客户
	 * 
	 * @author heng.wang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	int updateByPrimaryKey(CusTomer record);

	/**
	 * 
	 * @Description : 查看所有提交实名认证的客户列表
	 * @param cusTomer
	 *            客户对象
	 * @param info
	 *            分页对象
	 * @return 提交实名认证的客户列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月21日 下午4:10:53
	 */
	PageList<CusTomer> selectAuthenticationByCondition(@Param("cusTomer") CusTomer cusTomer, PageInfo info);

	public PageList<CusTomer> selectAllPage(@Param("cusTomer") CusTomer cusTomer, PageInfo info);

	// 根据VIP级别来查
	public PageList<CusTomer> selectAllPageByVipId(@Param("cusTomer") CusTomer cusTomer, PageInfo info, @Param("statusArr") String[] statusArr);

	Map<String, BigDecimal> selectAllPageByVipISum(@Param("cusTomer") CusTomer cusTomer, @Param("statusArr") String[] statusArr);

	/**
	 * Description：根据pid来批量更新客服ID
	 * 
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public int updatCusIdByPrimaryKey(@Param("map") Map<String, Object> map);

	/**
	 * Description：根据客户pid来查客户
	 * 
	 * @author heng.wang
	 * @date 2015年9月17日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectCusByPid(@Param("map") Map<String, Object> map);

	public Map<String, BigDecimal> selectHongbaoDetailsByIdSum(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * Description：根据客户pid来查客户详情
	 * 
	 * @author heng.wang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
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
	public List<CusTomer> selectNearlySevenNewCustomers(@Param("map") Map<String, Object> map);

	/**
	 * Description：根据客户pid来查客户投资奖励红包详情
	 * 
	 * @author heng.wang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectHongbaoDetailsById(@Param("cusTomer") CusTomer cusTomer, PageInfo info);

	/**
	 * Description：根据客户pid来查客户银行卡账户详情
	 * 
	 * @author heng.wang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<CusTomer> selectBankInfoById(String pid);

	/**
	 * Description：根据客户pid查客服
	 * 
	 * @author heng.wang
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public List<CusTomer> selectCustomerServiceById(String userId);

	/**
	 * Description：查vip等级
	 * 
	 * @author heng.wang
	 * @date 2015年10月26日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public List<CusTomer> selectVipLevel();

	/**
	 * Description：客户导出
	 * 
	 * @author heng.wang
	 * @date 2015年10月29日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<CusTomer> selectCustomer(HashMap<String, Object> map);

	/**
	 * Description：统计客户导出
	 * 
	 * @author heng.wang
	 * @date 2015年10月29日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	HashMap<String, BigDecimal> selectSumCustomer(HashMap<String, Object> map);

	/**
	 * 
	 * Description：<br>
	 * 根据用户名，查询用户
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月30日
	 * @version v1.0.0
	 * @param customerName
	 */
	CusTomer selectCusByCustomerName(String customerName);

	/**
	 * 
	 * Description：<br>
	 * 统计客户数据
	 * 
	 * @author heng.wang
	 * @date 2015年10月30日
	 * @version v1.0.0
	 * @param customerName
	 */
	Map<String, BigDecimal> selectAllPageSum(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * @Description : 修改认证状态
	 * @param record
	 *            客户对象
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午9:10:04
	 */
	void updateCusTomerByPid(CusTomer record);

	/**
	 * Description：<br>
	 * 统计用户（客户）总数集合 （今日新增用户数、今日实名认证人数、总用户数、实名认证总人数）
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	Integer selectUserSum(Map<String, String> map);

	/**
	 * Description：<br>
	 * 按年月统计用户数
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param dto
	 *            年月日对象
	 * @return List<YTDDto>
	 */
	List<YTDDto> monthlyStatisticsCustomer(Map<String, String> map);

	/**
	 * Description：<br>
	 * 按月日 统计用户（客户）报表
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param map
	 * @return List<YTDDto>
	 */
	List<YTDDto> monthDayStatisticsCustomer(Map<String, String> map);

	/**
	 * Description：<br>
	 * 查询今日 实名制认证人数
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月10日
	 * @version v1.0.0
	 * @return Integer
	 */
	Integer selectTodayCertification();

	/**
	 * 
	 * @Description : 查询提交实名认证人数
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午4:52:17
	 */
	int selectAuthenticationCount();

	/**
	 * 
	 * Description：<br>
	 * 统计用户数
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月17日
	 * @version v1.0.0
	 * @param phoneNo
	 * @return
	 */
	Integer validatePhoneNo(CusTomer customer);

	/**
	 * 
	 * Description：<br>
	 * 修改客户密码
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月17日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	Integer updatePwd(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 修改客户交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	Integer updateTradPwd(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 获取旧数据ID，密码加密因子
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月17日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	Integer selectIndexByPid(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据登录名查询客户数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月19日
	 * @version v1.0.0
	 * @param cusTomer
	 * @return
	 */
	CusTomer selectByLoginName(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 清空登录错误信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月19日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	void clearLoginError(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 更新用户登录错误信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月19日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	void updateLoingError(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id查客户资料
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public List<CusTomer> selectPersonData(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 根据客户id查交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public List<CusTomer> selectTradPwd(String customerId);

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
	public List<CusTomer> selectReferralCodeByCustd(String userId);

	/**
	 * 
	 * Description：<br>
	 * 按创建时间查最后用户注册的邀请码
	 * 
	 * @author heng.wang
	 * @date 2015年12月4日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public List<CusTomer> selectLastReferralCode();

	/**
	 * 
	 * Description：<br>
	 * 根据推介码获取客户信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月5日
	 * @version v1.0.0
	 * @param referralCode
	 */
	List<CusTomer> selectByReferralCode(String referralCode);

	/**
	 * 
	 * Description：<br>
	 * 根据id查我分享过的合伙人
	 * 
	 * @author heng.wang
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param referralCode
	 */
	public List<CusTomer> selectMySharePartner(String userId);

	/**
	 * 
	 * Description：<br>
	 * 根据id修改客户名
	 * 
	 * @author heng.wang
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param
	 */
	public int updateCusNameByCusPid(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据id修改家庭地址
	 * 
	 * @author heng.wang
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param
	 */
	public int updateHomeAddressByCusPid(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 查询当前最大加密因子值
	 * 
	 * @author Yu.Zhang
	 * @date 2015年12月9日
	 * @version v1.0.0
	 * @return
	 */
	Integer selectMaxIndex();

	/**
	 * 
	 * Description：<br>
	 * 查用户信息
	 * 
	 * @author heng.wang
	 * @date 2015年12月12日
	 * @version v1.0.0
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
	 * @return
	 */
	public int updateCustVipTimeByCustId(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 根据用户id，获取其交易密码
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月14日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	public String selectTradePwdByUserId(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 查用户名是否存在
	 * 
	 * @author heng.wang
	 * @date 2015年12月18日
	 * @version v1.0.0
	 * @param cusTomer
	 * @return
	 */
	public CusTomer selectByUserName(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 查询借款人和该借款所有的投标人
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param customerIds
	 * @return
	 */
	public List<CusTomer> selectAllRelateUsers(List<String> customerIds);

	/**
	 * 
	 * Description：<br>
	 * 根据用户id,获取用户对象
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public CusTomer getBuyUserId(@Param("userId") String userId);

	public String getVip(String userId);

	/**
	 * Description：<br>
	 * 更新最后登录时间
	 * 
	 * @author heng.wang
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @MAP: userId ,createTime
	 * @return
	 */
	public int updateLastLoginTime(Map<String, Object> map);

	/**
	 * Description：<br>
	 * 更新最后交易时间
	 * 
	 * @author heng.wang
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @MAP: userId,createTime
	 * @return
	 */
	public int updateLastTransacTime(Map<String, Object> map);

	/**
	 * 
	 * @Description : 批量修改客户VIP信息
	 * @param list
	 *            客户集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午4:54:18
	 */
	public void batchUpdateVip(List<CusTomer> list);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新
	 * @author  Yu.Zhang
	 * @date    2016年1月22日
	 * @version v1.0.0
	 * @param list
	 */
	public void  batchUpdate(List<CusTomer> list);

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
	 * 获取用户积分
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	public Integer getSrcPointByUserId(@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 往用户增加积分
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param customerId
	 * @param pointValue
	 */
	public void updateAddSrcPointByUserId(@Param("customerId") String customerId, @Param("pointValue") Integer pointValue);

	/**
	 * 
	 * Description：<br>
	 * api：校验原密码是否正确
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param customerId
	 */
	public int validateOriginalPassWord(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api：修改原密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public int updateLoginPassWord(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api：设置交易密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public int updateTradPassWord(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api：校验交易密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public int validataTradPassWord(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * api：查我的推荐码
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public List<CusTomer> selectReferralCode(String userId);

	/**
	 * 
	 * Description：<br>
	 * api：查我推荐的客户列表
	 * 
	 * @author heng.wang
	 * @date 2016年1月2日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public List<CusTomer> selectMyReferralList(String userId);

	/**
	 * 
	 * Description：<br>
	 * api：设置交易密码
	 * 
	 * @author heng.wang
	 * @date 2016年1月7日
	 * @version v1.0.0
	 * @param cusTomer
	 */
	public int setTradPassWord(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 获取黑名单客户
	 * 
	 * @author Yu.Zhang
	 * @date 2016年1月11日
	 * @version v1.0.0
	 * @param cusTomer
	 * @return
	 */
	CusTomer getIsBlacklist(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 获取被禁用的用户
	 * 
	 * @author Yu.Zhang
	 * @date 2016年1月11日
	 * @version v1.0.0
	 * @param cusTomer
	 * @return
	 */
	CusTomer getIsFreeze(CusTomer cusTomer);

	/**
	 * 
	 * Description：<br>
	 * 获取用户交易冻结到期时间
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	Date selectTradeFreezeTimeByUserId(@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 将该用户的交易错误次数，重置为0
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param customerId
	 */
	void resetTradepwd(@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 获取用户交易错误次数
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	Integer selectTradeCountByUserId(@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 冻结用户交易，并修改其交易次数为0
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param time
	 * @param customerId
	 */
	void freezeTrade(@Param("time") Date time, @Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 增加用户交易错误次数
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param customerId
	 */
	void addTradePwdCount(@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 获取用户交易错误累计截止时间
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	Date selectTradeErrorTimeByUserId(@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 更新用户交易密码错误累计截止时间，并改成错误次数1次
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月13日
	 * @version v1.0.0
	 * @param time
	 * @param customerId
	 */
	void addTradePwdErrorTime(@Param("time") Date time, @Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * api 绑定手机号码
	 * 
	 * @author heng.wang
	 * @date 2016年1月14日
	 * @version v1.0.0
	 * @param time
	 * @param customerId
	 */
	public int bindPhone(@Param("cusTomer") CusTomer cusTomer);

	/**
	 * 
	 * @Description : 兑换修改客户信息
	 * @param cusTomer 客户对象
	 * @Author : Qing.Cai
	 * @Date : 2016年1月18日 下午2:17:11
	 */
	public void updateCustTomerByExchange(CusTomer cusTomer);
	
	/**
	 * 
	 * @Description : 修改客户名称
	 * @param cusTomer 客户对象
	 * @Author :heng.wang
	 * @Date : 2016年1月18日 下午2:17:11
	 */
	public int updateCustName(@Param("cusTomer")CusTomer cusTomer);
	
	/**
	 * 
	 * @Description : api修改vip时长
	 * @param cusTomer 客户对象
	 * @Author :heng.wang
	 * @Date : 2016年1月21日 下午2:17:11
	 */
	public int updateVipTime(@Param("cusTomer")CusTomer cusTomer);
	
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
	public String selectIsAttestationById(@Param("custId")String custId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户Id更新是否vip
	 * @author  wangheng
	 * @date    2016年2月2日
	 * @version v1.0.0
	 * @param custId
	 * @return
	 */
	public void updateIsVipByCustId(@Param("custId")String custId);
	
	/**
	 * 
	 * Description：<br> 
	 * 获取用户逾期记录数量
	 * @author  JunJie.Liu
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public Integer isOverdue(@Param("userId") String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询未禁用，非黑名单，status =1的VIP的客户数据
	 * @author wangheng
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public List<CusTomer> selectCustomerInfo(); 
	/**
	 * Description：<br> 
	 * 活动满足条件的客户
	 * @author  shiliang.feng
	 * @date    2016年3月8日
	 * @version v1.0.0
	 * @param conditionValue
	 * @return
	 */
	public List<CusTomer> selectCustByCondition(@Param("conditionValue")String conditionValue);
	
	/**
	 * Description：<br> 
	 * 当天生日的客户
	 * @author  shiliang.feng
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @return
	 */
	public List<CusTomer> selectCustomerByBirthdayToDay(); 
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新客户的经验值
	 * @author wangheng
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public void batchUpdateCusTomerEmpiricalValue(List<CusTomer> list);
	
	/**
	 * 
	 * Description：<br> 
	 * 把是vip的客户vip时长减1
	 * @author wangheng
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public int updateViptimeByIsVip();
	
	/**
	 * 
	 * Description：<br> 
	 * 查客户vip信息
	 * @author wangheng
	 * @date    2016年3月7日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public List<CusTomer> selectVipInfo();
	
	/**
	 * 
	 * Description：<br> 
	 * 把是客户更新是否为vip
	 * @author wangheng
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public int updateCustIsVip(List<CusTomer> list1);

	/**
	 * Description：<br> 
	 * 满足活动设置的条件 并用户在custList之内的客户
	 * @author  shiliang.feng
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param custList
	 * @param conditionValue
	 * @return
	 */
	public List<CusTomer> passCustByCondition(@Param("map")Map<String,Object> params); 
	
}