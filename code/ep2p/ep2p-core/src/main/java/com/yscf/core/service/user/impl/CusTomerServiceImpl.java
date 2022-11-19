package com.yscf.core.service.user.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemMessageConstant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.EncodedUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.common.util.InvitationCodeUtil;
import com.yscf.core.dao.activity.ActExpActDetailMapper;
import com.yscf.core.dao.content.ColumnContentMapper;
import com.yscf.core.dao.financial.BizAccountCommonMapper;
import com.yscf.core.dao.financial.BizAccountExperienceMapper;
import com.yscf.core.dao.financial.BizAccountRechargeMapper;
import com.yscf.core.dao.financial.BizCustomerAccountMapper;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.dao.system.SysUserMapper;
import com.yscf.core.dao.system.SysVipinfoMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustInterestTicketMapper;
import com.yscf.core.dao.user.CustLargessVipWaterMapper;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.financial.BizAccountCommon;
import com.yscf.core.model.financial.BizAccountExperience;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.BizCustomerAccount;
import com.yscf.core.model.sms.CustMessRecord;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysUser;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustExperienceWater;
import com.yscf.core.model.user.CustInterestTicket;
import com.yscf.core.model.user.CustLargessVipWater;
import com.yscf.core.service.activity.impl.ActActivityServiceImpl;
import com.yscf.core.service.sms.impl.CustMessRecordServiceImpl;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.ucenter.BBSApi;
import com.yscf.core.util.HReportUtil;
import com.yscf.core.util.YTDDto;
import com.yscf.core.util.YTDListDto;

/**
 * Description：<br>
 * 客户管理服务实现
 * 
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
@Service("cusTomerService")
public class CusTomerServiceImpl extends BaseService<BaseEntity, String> implements ICusTomerService {
	Logger logger = Logger.getLogger(CusTomerServiceImpl.class);

	@Resource(name = "bizCustomerAccountMapper")
	BizCustomerAccountMapper bizCustomerAccountMapper;

	@Resource(name = "columnContentMapper")
	ColumnContentMapper columnContentMapper;

	@Resource(name = "bizAccountCommonMapper")
	BizAccountCommonMapper bizAccountCommonMapper;

	@Resource(name = "bizAccountRechargeMapper")
	BizAccountRechargeMapper bizAccountRechargeMapper;
	
	@Resource(name = "sysUserMapper")
	SysUserMapper sysUserMapper ;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;

	/** 系统VIP信息Mapper接口 */
	@Autowired
	public SysVipinfoMapper sysVipinfoMapper;

	@Resource
	public SysParamsMapper sysParamsMapper;

	/** 我的加息劵Mapper */
	@Resource
	public CustInterestTicketMapper custInterestTicketMapper;

	/** 体验金明细Mapper */
	@Resource
	public ActExpActDetailMapper actExpActDetailMapper;

	@Resource
	private com.yscf.core.dao.user.CustExperienceWaterMapper custExperienceWaterMapper;
	
	@Resource(name = "actActivityServiceImpl")
	private ActActivityServiceImpl actActivityServiceImpl;
	
	@Resource(name = "bizAccountExperienceMapper")
	BizAccountExperienceMapper bizAccountExperienceMapper;
	
	@Resource(name = "custLargessVipWaterMapper")
	CustLargessVipWaterMapper custLargessVipWaterMapper;
	
	//读取系统参数
	@Resource(name="sysParamsService")
	ISysParamsService sysParamsServiceImpl;
	
	//客户资料明细
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;
	
	@Resource(name = "custMessRecordService")
	private CustMessRecordServiceImpl CustMessRecordServiceImpl;

	@Autowired
	public CusTomerServiceImpl(CusTomerMapper dao) {
		super(dao);
	}

	@Override
	public int insert(CusTomer cusTomer) throws FrameworkException {
		// 取出密码，先保存手机号码信息
		String password = cusTomer.getPassword();
		cusTomer.setPid(cusTomer.getPK());
		cusTomer.setStatus(Constant.ACTIVATE);
		cusTomer.setPassword(null);
		cusTomer.setIsVip("0");
		cusTomer.setIsBlacklist("1");
		cusTomer.setIsForbidTransfer("1");
		cusTomer.setIsAttestation("3");
		cusTomer.setIsForbidWithdraw("1");
		cusTomer.setIsFreeze("1");
		
		// 用户VIP级别默认为VIp 0 
		SysVipinfo info = sysVipinfoMapper.getByLevel("0");
		if(null != info){
			cusTomer.setVipId(info.getPid());
		}
		// 查询最后推介码
		List<CusTomer> list = this.selectLastReferralCode();

		if (null != list && list.size() > 0) {
			// 生成推介码
			cusTomer.setReferralCode(InvitationCodeUtil.createStatusCode(list.get(0).getReferralCode()));
		} else {
			// 生成推介码
			cusTomer.setReferralCode("A0000");
		}
		// 查询出最大加密因子，并且加1后最为当前数据的加密因子存放到数据库中
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		Integer index = mapper.selectMaxIndex();
		if (null == index) {
			cusTomer.setIndex(1);
		} else {
			cusTomer.setIndex(index + 1);
		}
		int result = mapper.insertSelective(cusTomer);
		cusTomer.setPassword(password);

		// 加密密码，并且保存到数据库中
		resetPwd(cusTomer,"yes");

		// 往用户账户表中插入数据
		BizCustomerAccount bizCustomerAccount = new BizCustomerAccount();
		bizCustomerAccount.setPid(bizCustomerAccount.getPK());
		bizCustomerAccount.setCustomerId(cusTomer.getPid());
		bizCustomerAccount.setStatus(Constant.ACTIVATE);
		bizCustomerAccount.setCreateTime(cusTomer.getCreateTime());
		bizCustomerAccount.setCreateUser(cusTomer.getPid());
		bizCustomerAccountMapper.insertSelective(bizCustomerAccount);

		// 往普通资金表中插入数据
		BizAccountCommon bizAccountCommon = new BizAccountCommon();
		bizAccountCommon.setPid(bizAccountCommon.getPK());
		bizAccountCommon.setCustomerId(cusTomer.getPid());
		bizAccountCommon.setStatus(Constant.ACTIVATE);

		bizAccountCommonMapper.insertSelective(bizAccountCommon);

		// 往充值资金表中插入数据
		BizAccountRecharge bizAccountRecharge = new BizAccountRecharge();
		bizAccountRecharge.setPid(bizAccountRecharge.getPK());
		bizAccountRecharge.setCustomerId(cusTomer.getPid());
		bizAccountRecharge.setStatus(Constant.ACTIVATE);
		bizAccountRechargeMapper.insertSelective(bizAccountRecharge);
		
		// 往体验金资金类别表插入数据
		BizAccountExperience bizAccountExperience = new BizAccountExperience();
		bizAccountExperience.setCustomerId(cusTomer.getPid());
		bizAccountExperience.setPid(bizAccountExperience.getPK());
		bizAccountExperience.setStatus(Constant.ACTIVATE);
		bizAccountExperienceMapper.insert(bizAccountExperience);
		
		// 调用注册送红包活动接口
		actActivityServiceImpl.giveRedByType(cusTomer.getPid(), Constant.REGISTER_GIVERED);
		
		
		// 调用系统消息接口
		CustMessRecord record = new CustMessRecord();
		record.setPid(record.getPK());
		record.setCustomerId(cusTomer.getPid());
		record.setSendModel(Constant.CUTOMER);
		record.setIsRead(0);
		record.setMsgType(Constant.CUST_MESS_RECORD_1);
		record.setStatus(Constant.ACTIVATE);
		record.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
		record.setSendContent(SystemMessageConstant.REGISTER_MESSAGE);
		CustMessRecordServiceImpl.insertSelective(record);
		return result;
	}

	public void resetPwd(CusTomer cusTomer,String isRegister) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		// 保存成功后，取出加密引子与密码加密
		CusTomer temp = mapper.selectByPrimaryKey(cusTomer.getPid());
		String password = EncodedUtil.getEncodedPassword(cusTomer.getPassword(), temp.getIndex().toString());
		cusTomer.setPassword(password);

		// 客户名不为空的情况下。
		if (null != cusTomer.getCustomerName()) {
			try {
				// 同步更新论坛密码
				BBSApi.modifyPwd(cusTomer.getCustomerName(), temp.getPassword(), password);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("修改密码同步至论坛失败" + e.getMessage());
				}
			}
		}
		// 将密码更新到数据库中
		mapper.updatePwd(cusTomer);
		
		// 非注册操作
		if(null !=isRegister){
			// 修改密码成功后，发送系统消息
			CustMessRecord record = new CustMessRecord();
			record.setPid(record.getPK());
			record.setCustomerId(cusTomer.getPid());
			record.setSendModel(Constant.CUTOMER);
			record.setIsRead(0);
			record.setMsgType(Constant.CUST_MESS_RECORD_1);
			record.setStatus(Constant.ACTIVATE);
			record.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
			record.setSendContent(SystemMessageConstant.UPDATE_PWD_MESSAGE.replace("${time}", DateUtil.format(DateUtil.getSystemDate())));
			CustMessRecordServiceImpl.insertSelective(record);
		}
	}

	@Override
	public List<CusTomer> selectAll(CusTomer cusTomer, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBtach(String pids) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PageList<CusTomer> selectPage(CusTomer cusTomer, PageInfo info) {
		// 修改适用开始时间格式-begin
		if (null != cusTomer.getHappenBeginTime() && !"".equals(cusTomer.getHappenBeginTime())) {
			cusTomer.setHappenBeginTime(EscfDateUtil.formatterDate(cusTomer.getHappenBeginTime(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != cusTomer.getHappenEndTime() && !"".equals(cusTomer.getHappenEndTime())) {
			cusTomer.setHappenEndTime(EscfDateUtil.formatterDate(cusTomer.getHappenEndTime(), 2));
		}
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectAllPage(cusTomer, info);
	}

	@Override
	public PageList<CusTomer> selectAllPage(CusTomer cusTomer, PageInfo info) {
		// 修改适用开始时间格式-begin
		if (null != cusTomer.getHappenBeginTime() && !"".equals(cusTomer.getHappenBeginTime())) {
			cusTomer.setHappenBeginTime(EscfDateUtil.formatterDate(cusTomer.getHappenBeginTime(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != cusTomer.getHappenEndTime() && !"".equals(cusTomer.getHappenEndTime())) {
			cusTomer.setHappenEndTime(EscfDateUtil.formatterDate(cusTomer.getHappenEndTime(), 2));
		}
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		String vipPid = cusTomer.getVipPID();
		PageList<CusTomer> list = null;
		if (vipPid != null && !"".equals(vipPid)) {
			String arr[] = vipPid.split(",");
			list = mapper.selectAllPageByVipId(cusTomer, info, arr);
			Map<String, BigDecimal> map = mapper.selectAllPageByVipISum(cusTomer, arr);
			CusTomer cusTomer1 = new CusTomer();
			if (map != null && map.size() > 0) {
				cusTomer1.setTotalStay(map.get("totalStay"));
				cusTomer1.setAvailableBalance(map.get("availableBalance"));
				list.add(cusTomer1);
			}
		} else {
			String cusInv = cusTomer.getCusInv();
			String cusBor = cusTomer.getCusBor();
			if ((cusInv != "" && cusInv != null) && (cusBor != "" && cusBor != null)) {
				cusTomer.setCusBorAndCusInv("3");
			} else {
				cusTomer.setCusBorAndCusInv("");
			}
			list = mapper.selectAllPage(cusTomer, info);
			Map<String, BigDecimal> map = mapper.selectAllPageSum(cusTomer);
			CusTomer cusTomer1 = new CusTomer();
			if (null != map && map.size() > 0) {
				if (list != null && list.size() > 0) {
					cusTomer1.setTotalStay(map.get("totalStay"));
					cusTomer1.setAvailableBalance(map.get("availableBalance"));
					list.add(cusTomer1);
				}
			}
		}
		return list;
	}

	@Override
	public int updatCusIdByPrimaryKey(CusTomer cusTomer) {
		int result = 0;
		if (null != cusTomer && null != cusTomer.getPid()) {
			CusTomerMapper mapper = (CusTomerMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusmoterServiceUser", cusTomer.getCusServicePid());
			map.put("idItem", cusTomer.getPid().split(","));
			result = mapper.updatCusIdByPrimaryKey(map);
		}
		return result;
	}

	@Override
	public PageList<CusTomer> selectCusByPid(String pid) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		if (pid != null) {
			//
			map.put("pid", pid.split(","));
		}
		return mapper.selectCusByPid(map);
	}

	@Override
	public PageList<CusTomer> selectCusHistoryDetailedById(String pid) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectCusHistoryDetailedById(pid);
	}

	public int updateByPrimaryKeySelective(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.updateByPrimaryKeySelective(cusTomer);
	}

	@SuppressWarnings("static-access")
	@Override
	public Map<String, Object> selectNearlySevenNewCustomers() {
		Map<String, Object> map = new HashMap<String, Object>(); // 近七天 查询日期条件集合

		Date date = new Date();// 获取当前时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		String[] days = new String[7]; // 近七天 天 集合

		days[0] = DateUtil.format(date, "dd") + "日";
		map.put("day1", DateUtil.format(date, "yyyy-MM-dd"));
		for (int i = 1; i < 7; i++) {
			calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
			date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
			map.put("day" + (i + 1), DateUtil.format(date, "yyyy-MM-dd"));
			days[i] = DateUtil.format(date, "dd") + "日";
		}
		// 查询近七日新增客户数据
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		List<CusTomer> list = mapper.selectNearlySevenNewCustomers(map);
		map.clear();

		List<Integer> dayCount = new ArrayList<Integer>();
		for (CusTomer cusTomer : list) {
			dayCount.add(cusTomer.getCount());
		}

		map.put("days", days);
		map.put("result", dayCount.toArray());
		return map;
	}

	@Override
	public PageList<CusTomer> selectHongbaoDetailsById(String pid, PageInfo info) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer cusTomer = new CusTomer();
		cusTomer.setPid(pid);
		PageList<CusTomer> hongbao = mapper.selectHongbaoDetailsById(cusTomer, info);
		Map<String, BigDecimal> map = mapper.selectHongbaoDetailsByIdSum(cusTomer);
		CusTomer cusTomer2 = new CusTomer();
		cusTomer2.setPaychecksAmount(map.get("getAmount"));
		hongbao.add(cusTomer2);
		return hongbao;
	}

	@Override
	public List<CusTomer> selectCustomerServiceById(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectCustomerServiceById(userId);
	}

	/**
	 * 
	 * @Description : 查看所有提交实名认证的客户列表
	 * @param cusTomer
	 *            客户对象
	 * @param info
	 *            分页对象
	 * @return 提交实名认证的客户列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月21日 下午4:18:53
	 */
	@Override
	public PageList<CusTomer> selectAuthenticationByCondition(CusTomer cusTomer, PageInfo info) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		// 修改注册开始时间格式-begin
		if (null != cusTomer.getHappenBeginTime() && !"".equals(cusTomer.getHappenBeginTime())) {
			cusTomer.setHappenBeginTime(EscfDateUtil.formatterDate(cusTomer.getHappenBeginTime(), 1));
		}
		// 修改注册开始时间格式-end
		if (null != cusTomer.getHappenEndTime() && !"".equals(cusTomer.getHappenEndTime())) {
			cusTomer.setHappenEndTime(EscfDateUtil.formatterDate(cusTomer.getHappenEndTime(), 2));
		}
		PageList<CusTomer> list = new PageList<CusTomer>();
		list = mapper.selectAuthenticationByCondition(cusTomer, info);
		return list;
	}

	@Override
	public List<CusTomer> selectVipLevel() {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectVipLevel();
	}

	@Override
	public List<CusTomer> selectCustomer(CusTomer cusTomer, ExportObjectModel expm) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cusTomer", cusTomer);
		map.put("expm", expm);
		List<CusTomer> elist = mapper.selectCustomer(map);
		if (null == elist) {
			elist = new ArrayList<CusTomer>(0);
		} else {
			// 统计总数
			CusTomer cusTomer1 = new CusTomer();
			cusTomer1.setTotal("总计");
			HashMap<String, BigDecimal> remap = mapper.selectSumCustomer(map);
			if (null != remap) {
				cusTomer1.setTotalStay(remap.get("totalStay"));
				cusTomer1.setAvailableBalance(remap.get("availableBalance"));
				// 添加到集合中
				elist.add(cusTomer1);
			}
		}
		return elist;
	}

	@Override
	public CusTomer selectCusByCustomerName(String customerName) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectCusByCustomerName(customerName);
	}

	/**
	 * Description：<br>
	 * 统计用户（客户）总数集合 （今日新增用户数、今日实名认证人数、总用户数、实名认证总人数）
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月3日
	 * @version v1.0.0
	 * @return
	 * @throws HttpRequestException
	 */
	@Override
	public Map<String, Integer> selectUserSum() throws FrameworkException {
		CusTomerMapper dao = (CusTomerMapper) getDao();
		Map<String, String> map = new HashMap<String, String>();
		map.put("registrationTime", DateUtil.format(new Date(), "yyyy-MM-dd"));
		// 今日新增用户数
		Integer todayAddSum = dao.selectUserSum(map);
		map.put("isAttestation", "1");
		// 今日实名认证人数
		Integer todayCertificationSum = dao.selectTodayCertification();
		map.remove("registrationTime");
		// 实名认证总人数
		Integer certificationSum = dao.selectUserSum(map);
		map.remove("isAttestation");
		// 总用户数
		Integer userSum = dao.selectUserSum(map);

		Map<String, Integer> sumMap = new HashMap<String, Integer>();
		sumMap.put("todayAddSum", null == todayAddSum ? 0 : todayAddSum);
		sumMap.put("todayCertificationSum", null == todayCertificationSum ? 0 : todayCertificationSum);
		sumMap.put("certificationSum", null == certificationSum ? 0 : certificationSum);
		sumMap.put("userSum", null == userSum ? 0 : userSum);
		return sumMap;
	}

	@Override
	public Map<String, Object> userStatistical(String type, String param) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(type)) {
			try {
				// type(1：按年统计、2：按月统计)
				if ("1".equals(type)) {
					return monthlyStatisticsCustomer(param);
				} else if ("2".equals(type)) {
					// 获取按年月统计客户数
					return monthDayStatisticsCustomer(param);
				}
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.info(e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return remap;
	}

	/**
	 * Description：<br>
	 * 获取按年月统计客户数
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param year
	 *            年份
	 * @param userCount
	 *            按时间统计用户总数
	 * @param attestationCount
	 *            按时间统计实名认证总数
	 * @return
	 */
	private Map<String, Object> monthlyStatisticsCustomer(String year) {
		CusTomerMapper dao = (CusTomerMapper) getDao();
		Map<String, Object> resMap = new HashMap<String, Object>();
		// 按时间统计用户总数
		Integer userCount = 0;
		// 按时间统计实名认证总数
		Integer attestationCount = 0;
		// 查询条件
		HashMap<String, String> querymap = new HashMap<String, String>();
		// 获取月份（1-12）
		int[] yearm = HReportUtil.comMonth2();
		// 设置已实名制认证客户按月统计数组长度 （12个月）
		int[] certificationSum = new int[yearm.length];
		// 设置用户（总客户）长度按月统计数组长度 （12个月）
		int[] userSum = new int[yearm.length];
		// 设置条件（年份）
		querymap.put("date", year);
		// 获取按年月统计客户数
		List<YTDDto> userList = dao.monthlyStatisticsCustomer(querymap);
		// 设置条件（实名制认证的客户）
		querymap.put("isAttestation", "1");
		// 获取按年月统计客户数
		List<YTDDto> ertificationList = dao.monthlyStatisticsCustomer(querymap);
		// 封装数据信息
		for (int i = 0; i < ertificationList.size(); i++) {
			// 实名制客户统计对象
			YTDDto de = ertificationList.get(i);
			if (null != de) {
				for (int j = 0; j < yearm.length; j++) {
					if (yearm[j] == de.getMonthD()) {
						// 赋值 每个月的实名制客户统计数
						certificationSum[j] = de.getCountD();
						attestationCount += de.getCountD();
						break;
					}
				}
			}
		}
		for (int i = 0; i < userList.size(); i++) {
			// 客户统计对象
			YTDDto du = userList.get(i);
			if (null != du) {
				for (int j = 0; j < yearm.length; j++) {
					// 比对月份
					if (yearm[j] == du.getMonthD()) {
						// 赋值 每个月的客户统计数
						userSum[j] = du.getCountD();
						userCount += du.getCountD();
						break;
					}

				}
			}
		}
		List<YTDListDto> dtos = new ArrayList<YTDListDto>();
		YTDListDto user = new YTDListDto(Constant.ADD_USER_COUNT_TITLE, userSum);
		YTDListDto certification = new YTDListDto(Constant.USER_CERTIFICATION_COUNT_TITLE, certificationSum);
		dtos.add(user);
		dtos.add(certification);
		resMap.put("categories", HReportUtil.comMonth());
		resMap.put("userCount", userCount);
		resMap.put("attestationCount", attestationCount);
		resMap.put("datas", dtos);
		return resMap;
	}

	/**
	 * Description：<br>
	 * 按月日 统计用户（客户）报表
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param monthly
	 *            年月
	 * @param userCount
	 *            按时间统计用户总数
	 * @param attestationCount
	 *            按时间统计实名认证总数
	 * @return List<YTDListDto>
	 */
	private Map<String, Object> monthDayStatisticsCustomer(String monthly) {
		CusTomerMapper dao = (CusTomerMapper) getDao();
		Map<String, Object> resMap = new HashMap<String, Object>();
		// 查询条件
		HashMap<String, String> querymap = new HashMap<String, String>();
		// 按时间统计用户总数
		Integer userCount = 0;
		// 按时间统计实名认证总数
		Integer attestationCount = 0;
		// 设置横坐标
		// 获取指定月数的天数的数组
		int[] yearmd = HReportUtil.comDay2(monthly);
		// 设置已实名制认证客户按月统计数组长度
		int[] certificationSum = new int[yearmd.length];
		// 设置用户（总客户）长度按月统计数组长度
		int[] userSum = new int[yearmd.length];
		// 设置条件（年份）
		querymap.put("date", monthly);
		// 获取按年月统计客户数
		List<YTDDto> userList = dao.monthDayStatisticsCustomer(querymap);
		// 设置条件（实名制认证的客户）
		querymap.put("isAttestation", "1");
		// 获取按年月统计客户数
		List<YTDDto> ertificationList = dao.monthDayStatisticsCustomer(querymap);
		// 封装数据信息
		for (int i = 0; i < ertificationList.size(); i++) {
			// 实名制客户统计对象
			YTDDto de = ertificationList.get(i);
			if (null != de) {
				for (int j = 0; j < yearmd.length; j++) {
					if (yearmd[j] == de.getDayD()) {
						// 赋值 每个月的实名制客户统计数
						certificationSum[j] = de.getCountD();
						attestationCount += de.getCountD();
						break;
					}
				}
			}
		}
		for (int i = 0; i < userList.size(); i++) {
			// 客户统计对象
			YTDDto du = userList.get(i);
			if (null != du) {
				for (int j = 0; j < yearmd.length; j++) {
					// 比对月份
					if (yearmd[j] == du.getDayD()) {
						// 赋值 每个月的客户统计数
						userSum[j] = du.getCountD();
						userCount += du.getCountD();
						break;
					}
				}
			}
		}
		List<YTDListDto> dtos = new ArrayList<YTDListDto>();
		YTDListDto user = new YTDListDto(Constant.ADD_USER_COUNT_TITLE, userSum);
		YTDListDto certification = new YTDListDto(Constant.USER_CERTIFICATION_COUNT_TITLE, certificationSum);
		dtos.add(user);
		dtos.add(certification);
		// 设置横坐标 按天
		resMap.put("categories", HReportUtil.comDay(monthly));
		resMap.put("userCount", userCount);
		resMap.put("attestationCount", attestationCount);
		resMap.put("datas", dtos);
		return resMap;
	}

	/**
	 * 
	 * @Description : 查询提交实名认证人数
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午4:53:22
	 */
	@Override
	public int selectAuthenticationCount() {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectAuthenticationCount();
	}

	@Override
	public boolean validatePhoneNo(CusTomer customer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		Integer count = mapper.validatePhoneNo(customer);
		if (null != count && count > 0) {
			return false;
		}
		return true;
	}

	@Override
	public CusTomer selectByLoginName(String loginName) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer cusTomer = new CusTomer();
		cusTomer.setSname(loginName);
		return mapper.selectByLoginName(cusTomer);
	}

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
	public void clearLoginError(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		mapper.clearLoginError(cusTomer);
	}

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
	public void updateLoingError(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		mapper.updateLoingError(cusTomer);
	}

	@Override
	public List<CusTomer> selectPersonData(String customerId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectPersonData(customerId);
	}

	public CusTomer selectByPrimaryKey(String pid) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectByPrimaryKey(pid);
	}

	@Override
	public int selectIndexByPid(String custmerPid) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectIndexByPid(custmerPid);
	}

	@Override
	public int vailidateTradePassword(String pwd, String customerId) throws TradePwdIsBlankException , TradePwdFreezeException {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		String tradePwd = mapper.selectTradePwdByUserId(customerId);
		Integer count = 0;
		if (tradePwd == null) {
			// 用户交易密码未设置
			throw new TradePwdIsBlankException(Constant.USER_TRADE_PWD_BANK);
		}
		// 获取用户交易冻结时间
		Date tradeFreezeTime = mapper.selectTradeFreezeTimeByUserId(customerId);
		// 如果冻结则抛出冻结异常
		if (tradeFreezeTime != null && System.currentTimeMillis() <= tradeFreezeTime.getTime()) {
			// 冻结中，交易冻结中
			throw new TradePwdFreezeException("交易冻结中");

		}
		// 否则判断是否正确

		Integer index = mapper.selectIndexByPid(customerId);

		String tradPassword = EncodedUtil.getEncodedPassword(pwd, index.toString());

		// 正确返回-1
		if (tradePwd.equals(tradPassword)) {
			// 重置用户错误次数为0，累计截止时间为null
			mapper.resetTradepwd(customerId);
			return -1;
		} else {

			// 获取用户累计错误截止时间
			Date tradeErrorTime = mapper.selectTradeErrorTimeByUserId(customerId);

			if (tradeErrorTime != null && System.currentTimeMillis() < tradeErrorTime.getTime()) {
				// 错误返回错误次数，如果大于限制次数，则冻结，抛出冻结异常
				count = mapper.selectTradeCountByUserId(customerId);
				count = count == null ? 0 : count;
				count++;

				// 获取交易密码限制次数
				SysParams sp = sysParamsMapper.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n = null;
				try {
					n = Integer.parseInt(sp.getParamValue());
				} catch (Exception e) {

				}
				n = n == null ? 4 : n;

				// 获取交易密码限制分钟
				SysParams s = sysParamsMapper.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
				Integer m = null;
				try {
					m = Integer.parseInt(s.getParamValue());
				} catch (Exception e) {

				}
				m = m == null ? 60 : m;

				if (count >= n) {
					// 冻结用户
					Calendar c = Calendar.getInstance();
					c.add(Calendar.MINUTE, m);
					mapper.freezeTrade(c.getTime(), customerId);
					throw new TradePwdFreezeException("错误次数过多，交易被冻结");
				} else {
					mapper.addTradePwdCount(customerId);
				}

			} else {
				// 产生新的截止时间，并且设置错误次数为1次
				SysParams sp = sysParamsMapper.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROT_TIME);
				Integer n = null;
				try {
					n = Integer.parseInt(sp.getParamValue());
				} catch (Exception e) {

				}
				n = n == null ? 60 : n;
				Calendar c = Calendar.getInstance();
				c.add(Calendar.MINUTE, n);
				mapper.addTradePwdErrorTime(c.getTime(), customerId);
				count = 1;
			}

		}
		return count;
	}

	@Override
	public List<CusTomer> selectTradPwd(String customerId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectTradPwd(customerId);
	}

	@Override
	public int updateNewTradingPwd(String customerId) {
		return 0;
	}

	@Override
	public List<CusTomer> selectReferralCodeByCustd(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectReferralCodeByCustd(userId);
	}

	@Override
	public List<CusTomer> selectLastReferralCode() {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectLastReferralCode();
	}

	@Override
	public CusTomer selectByReferralCode(String referralCode) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer cusTomer = new CusTomer();
		cusTomer.setReferralCode(referralCode);

		List<CusTomer> list = mapper.selectByReferralCode(referralCode);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<CusTomer> selectMySharePartner(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectMySharePartner(userId);
	}

	@Override
	public int updateCusNameByCusPid(CusTomer cusTomer) {
		int result = 0;
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		if (!"".equals(cusTomer) && null != cusTomer) {
			result = mapper.updateCusNameByCusPid(cusTomer);
		}
		return result;
	}

	@Override
	public int updateHomeAddressByCusPid(CusTomer cusTomer) {
		int result = 0;
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		if (!"".equals(cusTomer) && null != cusTomer) {
			result = mapper.updateHomeAddressByCusPid(cusTomer);
		}
		return result;
	}

	@Override
	public List<CusTomer> getUserInfoByPid(String customerId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.getUserInfoByPid(customerId);
	}

	@Override
	public int updateCustVipTimeByCustId(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.updateCustVipTimeByCustId(cusTomer);
	}

	@Override
	public CusTomer selectByUserName(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectByUserName(cusTomer);
	}

	@Override
	public void resetTradPwd(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		// 保存成功后，取出加密引子与密码加密
		Integer index = mapper.selectIndexByPid(cusTomer.getPid());
		String tradPassword = EncodedUtil.getEncodedPassword(cusTomer.getPassword(), index.toString());
		cusTomer.setTradePassword(tradPassword);
		// 将密码更新到数据库中
		mapper.updateTradPwd(cusTomer);

	}

	@Override
	public List<ColumnContent> selectNewDynamic() {
		return columnContentMapper.selectNewDynamic();
	}

	@Override
	public CusTomer getByUserId(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.getBuyUserId(userId);
	}

	@Override
	public boolean isVip(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return "1".equals(mapper.getVip(userId));
	}

	/**
	 * 
	 * @Description : 批量修改客户VIP信息
	 * @param list
	 *            客户集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月29日 下午4:55:06
	 */
	@Override
	public void batchUpdateVip(List<CusTomer> list) throws FrameworkException {
		try {
			CusTomerMapper mapper = (CusTomerMapper) getDao();
			mapper.batchUpdateVip(list);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("批量修改客户VIP信息:" + e.getMessage());
			}
		}

	}

	@Override
	public void batchUpdatePoin(List<CusTomer> list) throws FrameworkException {
		// TODO Auto-generated method stub

	}

	@Override
	public void batchUpdateExperience(List<CusTomer> list) throws FrameworkException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CusTomer> selectAllRelateUsers(BizBorrow bizBorrow, List<BizBorrowDetail> bids) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		List<String> customerIds = new ArrayList<String>();
		for (BizBorrowDetail bid : bids) {
			customerIds.add(bid.getCustomerId());
		}
		customerIds.add(bizBorrow.getCustomerId());
		return mapper.selectAllRelateUsers(customerIds);
	}

	@Override
	public int validateOriginalPassWord(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.validateOriginalPassWord(cusTomer);
	}

	@Override
	public int updateLoginPassWord(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.updateLoginPassWord(cusTomer);
	}

	@Override
	public int updateTradPassWord(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.updateTradPassWord(cusTomer);
	}

	@Override
	public int validataTradPassWord(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.validataTradPassWord(cusTomer);
	}

	@Override
	public List<CusTomer> selectReferralCode(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectReferralCode(userId);
	}

	@Override
	public List<CusTomer> selectMyReferralList(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectMyReferralList(userId);
	}

	@Override
	public int setTradPassWord(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.updateTradPassWord(cusTomer);
	}

	public CusTomer getIsBlacklist(String loginName) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer cusTomer = new CusTomer();
		cusTomer.setSname(loginName);
		return mapper.getIsBlacklist(cusTomer);
	}

	public CusTomer getIsFreeze(String loginName) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer cusTomer = new CusTomer();
		cusTomer.setSname(loginName);
		return mapper.getIsFreeze(cusTomer);
	}

	@Override
	public int bindPhone(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.bindPhone(cusTomer);
	}

	@Override
	public Date getTradeFreezeTime(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectTradeFreezeTimeByUserId(userId);
	}

	@Override
	public int batchUpdateVipLeavel(List<CusTomer> list, String createUserId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer tempCustomer = null;
		List<CusTomer> updateList = new ArrayList<CusTomer>(); // 需要更新的客户集合
		String time = DateUtil.format(DateUtil.getToday());
		SysVipinfo sysVipInfo = null;
		for (CusTomer customer : list) {

			// 获取用户当前经验值VIP级别ID

			tempCustomer = new CusTomer();
			tempCustomer.setPid(customer.getPid());
			tempCustomer.setEmpiricalValue(customer.getEmpiricalValue());
			tempCustomer.setLastUpdateTime(time);
			if(StringUtil.isNotEmpty(createUserId))
				tempCustomer.setLastUpdateUser(createUserId);

			sysVipInfo = sysVipinfoMapper.selectVipInfoByEmpiricalValue(tempCustomer.getEmpiricalValue());

			tempCustomer.setVipId(sysVipInfo.getPid());
			tempCustomer.setVipLevel(sysVipInfo.getVipName());
			updateList.add(tempCustomer);
		}
		// 批量更新客户VIP信息
		mapper.batchUpdate(updateList);
		return 0;
	}

	@Override
	public int updateCustName(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		
		// 客户名不为空的情况下。
		if (null != cusTomer.getCustomerName()) {
			try {
				CusTomer temp = mapper.selectByPrimaryKey(cusTomer.getPid());
				// 同步注册论坛
				BBSApi.reg(cusTomer.getCustomerName(), temp.getPassword(), temp.getEmail());
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("设置用户名同步注册论坛失败" + e.getMessage());
				}
			}
		}
		return mapper.updateCustName(cusTomer);
	}

	@Override
	public int selectVipTime(String userId, int month) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		// 获取客户对象
		CusTomer cusTomer = mapper.getBuyUserId(userId);
		Integer vipTime = cusTomer.getVipTime();
		if(vipTime ==null || vipTime.equals("") || vipTime==0){
			vipTime=0;
			cusTomer.setVipTime(vipTime);
		}
		// 计算出当前时间加上几个月后的天数是多少天
		GregorianCalendar gcd = new GregorianCalendar();
		// 获取当前时间
		gcd.setTime(new Date());
		// 添加原来的日期
		gcd.add(Calendar.DATE, cusTomer.getVipTime());
		// 再添加购买的月份
		gcd.add(Calendar.MONTH, month);
		// 获取购买后的日期
		Date dt = gcd.getTime();
		// 计算购买后的日期距离现在多少天
		long s1 = dt.getTime();// 将时间转为毫秒
		long s2 = System.currentTimeMillis();// 得到当前的毫秒
		// 获取距离时长
		int day = (int) ((s1 - s2) / 1000 / 60 / 60 / 24);
		return day;
	}

	@Override
	public int updateVipTime(CusTomer cusTomer) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		cusTomer = cusDistribution(cusTomer);
		CusTomer cusTomer2 = cusTomerServiceImpl.selectByPrimaryKey(cusTomer.getPid());
		//获取购买后的天数
		Integer vt1 = cusTomer.getVipTime();
		//获取没购买之前的天数
		Integer vt2 = cusTomer2.getVipTime();
		Integer largessValue = vt1-vt2;
		CustLargessVipWater custLargessVipWater = new CustLargessVipWater();
		// 赋值
		custLargessVipWater.setPid(custLargessVipWater.getPK());// 设置主键
		custLargessVipWater.setCustomerId(cusTomer.getPid());// 设置客户ID
		custLargessVipWater.setVipinfoId("");// 设置VIP活动ID
		custLargessVipWater.setLargessValue(largessValue.toString());
		custLargessVipWater.setGetType(Constant.LARGESS_VIP_TYPE_1);// 获取类型
		custLargessVipWater.setStatus(Constant.PUBLIC_STATUS);// 状态为1,表示正常
		custLargessVipWater.setDistributionTime(DateUtil.format(new Date()));
		custLargessVipWater.setCreateUser(cusTomer.getCreateUser());// 设置创建人
		custLargessVipWater.setCreateTime(DateUtil.format(new Date()));// 设置创建时间
		custLargessVipWater.setLarVipWatDesc("购买VIP,时长为:" + cusTomer.getVipTime().toString() + "天");
		custLargessVipWaterMapper.insert(custLargessVipWater);
		return mapper.updateVipTime(cusTomer);
	}
	
	/**
	 * Description：<br> 
	 * 为客户分配客服
	 * @author  JingYu.Dai
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @throws MemcachedException 
	 * @throws InterruptedException 
	 * @throws TimeoutException 
	 */
	public  CusTomer cusDistribution(CusTomer customer) {
		//获取当前客户的客服ID
		String userId = customer.getCustomerServiceUser();
		//客服ID等于空 为客户分配客服
		if(userId == null){
			//从 memcached 内存中获取客服队列
			Queue<SysUser> queue;
			try {
				queue = memcachedClient.get(Constant.CUS_DISTRIBUTION_QUEUE);
				//队列为空  说明他在内存中已过期 重新加载新对列
				if(null == queue){
					//获取客服列表
					List<SysUser> users = sysUserMapper.queryUserByRoleCode(Constant.SYS_USER_ROLE_CODE);
					//创建对列
					queue = new LinkedList<SysUser>();
					for (SysUser u : users) {
						//add()和remove()方法在失败的时候会抛出异常(不推荐)
						queue.offer(u);
					}
					//重新放入memcached内存中等待下一次轮询
					memcachedClient.set(Constant.CUS_DISTRIBUTION_QUEUE, 0, queue);
					//设置第一位客服数
					customer.setCustomerServiceUser(users.get(0).getPid());
				}else{
					SysUser u1 = queue.poll();
					customer.setCustomerServiceUser(u1.getPid());
					//判断客服 客户数是否小于Constant.SERVICE_USER_COUNT(1000千)小于继续排队 
					if(u1.getServiceUserCount() < Constant.SERVICE_USER_COUNT){
						queue.offer(u1);
					}
				}
				//重新放入memcached内存中等待下一次轮询
				memcachedClient.set(Constant.CUS_DISTRIBUTION_QUEUE, 0, queue);
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (MemcachedException e) {
				e.printStackTrace();
			}
			return customer;
		}else{
			return customer;
		}
	}

	/**
	 * 
	 * @Description : 查询客户的卡劵
	 * @param customerId
	 *            客户ID
	 * @return 我的卡劵
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 上午10:40:20
	 */
	@Override
	public List<CustInterestTicket> selectUserCardVolume(String customerId) {
		List<CustInterestTicket> list = new ArrayList<CustInterestTicket>();
		try {
			list = custInterestTicketMapper.selectUserCardVolume(customerId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询客户的卡劵:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Description : API--查询我的卡劵-已失效or已过期
	 * @param customerId
	 *            客户ID
	 * @return 我的过期or失效卡劵
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 下午3:58:41
	 */
	@Override
	public List<CustInterestTicket> selectUserCardVolumeHasExpired(String customerId) {
		List<CustInterestTicket> list = new ArrayList<CustInterestTicket>();
		try {
			list = custInterestTicketMapper.selectUserCardVolumeHasExpired(customerId);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的卡劵-已失效or已过期:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Description : API--查询客户的体验金列表-可用
	 * @param customerId
	 *            客户ID
	 * @return 我的体验金劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午6:46:43
	 */
	@Override
	public List<ActExpActDetail> selectCustTomerExperienceGold(String customerId) {
		List<ActExpActDetail> list = null;
		try {
			list = actExpActDetailMapper.selectCustTomerExperienceGoldAPI(customerId);
			// 如果为空,就创建一个空对象
			if (null == list || list.size() == 0) {
				list = new ArrayList<ActExpActDetail>();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("API--查询客户的体验金列表-可用:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

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
	 * @Date : 2016年1月28日 上午11:24:03
	 */
	@Override
	public List<CustInterestTicket> selectUserInterestTicketAPI(String customerId, Integer pageIndex, Integer pageSize) {
		List<CustInterestTicket> list = null;
		try {
			// 创建查询条件Map
			Map<String, Object> map = new HashMap<String, Object>();
			// 赋值查询条件
			map.put("customerId", customerId);
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = custInterestTicketMapper.selectUserInterestTicketAPI(map);
			// 判断
			if(null == list || list.size() == 0){
				list = new ArrayList<CustInterestTicket>();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("API--查询客户的加息劵列表-未使用:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String selectIsAttestationById(String custId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectIsAttestationById(custId);
	}

	@Override
	public void updateIsVipByCustId(String custId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		mapper.updateIsVipByCustId(custId);
		
	}

	@Override
	public boolean isOverdue(String userId) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		Integer i = mapper.isOverdue(userId);
		if(i!=null && i==0){
			return false;
		}else{
			return true;
		}
	}
	
	public void  updateEmpiricalValueToDay() {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		List<CustExperienceWater> custExperienceWaters = new ArrayList<CustExperienceWater>();
		List<CusTomer> cusTomerList = new ArrayList<CusTomer>();
		List<CusTomer> list = new ArrayList<CusTomer>();
		List<CusTomer> list1 = new ArrayList<CusTomer>();
		CusTomer customer1 = null;
		Integer empiricalValueDay=0;
		// 1 查询系统参数配置的每天需要叠加的经验值  
		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("EMPIRICAL_VALUE_TODAY");
		String paramValue =sysParams.getParamValue();
		empiricalValueDay = empiricalValueDay.valueOf(paramValue);
		// 2 查询未禁用，非黑名单，status =1的VIP的客户数据
		cusTomerList = mapper.selectCustomerInfo();
		// 3 循环叠加经验值，并且记录客户经验值流水表数据对象
		CustExperienceWater water = null;
		for(CusTomer customer: cusTomerList){
			customer1 = new CusTomer();
			//设置经验值
			customer1.setEmpiricalValue(customer.getEmpiricalValue()+empiricalValueDay);
			customer1.setPid(customer.getPid());
			customer1.setVipLevel(customer.getVipLevel());
			list1.add(customer1);
			water = new CustExperienceWater();
			water.setPid(water.getPK());
			water.setCustomerId(customer.getPid());
			water.setExpValue(empiricalValueDay);
			water.setExpGetType("1");
			water.setStatus("1");
			water.setAvailableExp(customer.getEmpiricalValue()+empiricalValueDay);
			water.setCreateTime(DateUtil.format(new Date()));
			water.setExpWatDesc("新增经验流水");
			custExperienceWaters.add(water);
		}
		// 4 批量更新客户表数据
		mapper.batchUpdateCusTomerEmpiricalValue(list1);
		// 5 批量插入客户经验流水表数据
		custExperienceWaterMapper.batchUpdateCustExperienceWater(custExperienceWaters);
		// 6 批量更新客户VIP等级
		cusTomerServiceImpl.batchUpdateVipLeavel(list1, customer1.getPid());
	} 
	public List<CusTomer> selectCustByCondition(String conditionValue) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectCustByCondition(conditionValue);
	}
	
	@Override
	public List<CusTomer> selectCustomerByBirthdayToDay() {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		return mapper.selectCustomerByBirthdayToDay();
	} 
	@Override
	public void reduceVipTimeByDay() {		
		CusTomerMapper mapper = (CusTomerMapper) getDao();
	    //把vip客户的vipTime -1
		mapper.updateViptimeByIsVip();
	}

	@Override
	public void updateCustIsVip() {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		List<CusTomer>  list = new ArrayList<CusTomer>();
		List<CusTomer>  list1 = new ArrayList<CusTomer>();
		CusTomer cusTomer1=null;
		//查viptime = 0的客户  
		list = mapper.selectVipInfo();
		for(CusTomer cusTomer : list){
			cusTomer1 = new CusTomer();
			cusTomer1.setPid(cusTomer.getPid());
			list1.add(cusTomer1);
		} 
		mapper.updateCustIsVip(list1);
	}

	public Integer updateMobileDeviceMachineCode(String mobileDeviceMachineCode,
			String pid) {
		CusTomerMapper mapper = (CusTomerMapper) getDao();
		CusTomer customer = new CusTomer();
		customer.setMobileDeviceMachineCode(mobileDeviceMachineCode);
		customer.setPid(pid);
		return mapper.updateByPrimaryKeySelective(customer);
	}

	@Override
	public void prohibitOrStartOperation() {
		
		
	} 
}
