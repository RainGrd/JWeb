/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.activity.ActActivityMapper;
import com.yscf.core.dao.activity.ActBirPriItemActivityRelMapper;
import com.yscf.core.dao.activity.ActExpActDetailMapper;
import com.yscf.core.dao.activity.ActInvAwaActDetailMapper;
import com.yscf.core.dao.activity.ActInvWealthCoopDetailMapper;
import com.yscf.core.dao.activity.ActParConRelMapper;
import com.yscf.core.dao.activity.ActRedpActDetailMapper;
import com.yscf.core.dao.activity.ActVipActDetailMapper;
import com.yscf.core.dao.activity.PubExpGoldMapper;
import com.yscf.core.dao.activity.PubInvestAwardMapper;
import com.yscf.core.dao.activity.PubRedpacketMapper;
import com.yscf.core.dao.activity.PubVipinfoMapper;
import com.yscf.core.dao.activity.PubWealthCooperationMapper;
import com.yscf.core.dao.business.BizBorrowDetailMapper;
import com.yscf.core.dao.system.PubConditionMapper;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.dao.system.SysSmsTemplatesMapper;
import com.yscf.core.dao.system.SysVipinfoMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustExperienceWaterMapper;
import com.yscf.core.dao.user.CustLargessVipWaterMapper;
import com.yscf.core.dao.user.CustPoinWaterMapper;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.activity.ActInvAwaActDetail;
import com.yscf.core.model.activity.ActInvWealthCoopDetail;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.ActRedpActDetail;
import com.yscf.core.model.activity.ActVipActDetail;
import com.yscf.core.model.activity.PubExpGold;
import com.yscf.core.model.activity.PubInvestAward;
import com.yscf.core.model.activity.PubRedpacket;
import com.yscf.core.model.activity.PubVipinfo;
import com.yscf.core.model.activity.PubWealthCooperation;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.system.PubCondition;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustExperienceWater;
import com.yscf.core.model.user.CustLargessVipWater;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.service.activity.IActActivityService;
import com.yscf.core.service.activity.IPubActivityAreaService;
import com.yscf.core.service.business.impl.BizFundtallyServiceImpl;
import com.yscf.core.service.financial.impl.BizAccountCommonServiceImpl;
import com.yscf.core.service.financial.impl.BizAccountExperienceServiceImpl;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;
import com.yscf.core.service.user.ICusTomerService;

/**
 * 
 * @ClassName : ActActivityServiceImpl
 * @Description : 活动管理业务商用接口
 * @Author : Qing.Cai
 * @Date : 2015年9月22日 下午3:26:47
 */
@Service("actActivityServiceImpl")
public class ActActivityServiceImpl extends BaseService<BaseEntity, String>
		implements IActActivityService {

	private Logger logger = LoggerFactory
			.getLogger(ActActivityServiceImpl.class);

	@Autowired
	public ActActivityServiceImpl(PubExpGoldMapper dao) {
		super(dao);
	}

	/** 活动专区接口 */
	@Autowired
	public IPubActivityAreaService pubActivityAreaServiceImpl;

	/** 生日特权项和活动关系表Mapper接口 */
	@Autowired
	public ActBirPriItemActivityRelMapper activityRelMapper;

	/** 活动信息表Mapper接口 */
	@Autowired
	public ActActivityMapper actActivityMapper;

	/** 活动参与条件关系表Mapper接口 */
	@Autowired
	public ActParConRelMapper actParConRelMapper;

	/** 条件信息Mapper接口 */
	@Autowired
	public PubConditionMapper pubConditionMapper;

	/** 编号生成规则服务接口 */
	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	/** 赠送体验金Mapper接口 */
	@Autowired
	public PubExpGoldMapper pubExpGoldMapper;

	/** 投资奖励Mapper接口 */
	@Autowired
	public PubInvestAwardMapper pubInvestAwardMapper;

	/** 红包Mapper接口 */
	@Autowired
	public PubRedpacketMapper pubRedpacketMapper;

	/** 赠送VIPMapper接口 */
	@Autowired
	public PubVipinfoMapper pubVipinfoMapper;

	/** 财富合伙人Mapper接口 */
	@Autowired
	public PubWealthCooperationMapper cooperationMapper;

	/** 借款投资人Mapper接口 */
	@Autowired
	public BizBorrowDetailMapper bizBorrowDetailMapper;

	/** 赠送VIP详细Maper接口 */
	@Autowired
	public ActVipActDetailMapper actVipActDetailMapper;

	/** 送红包详细Maper接口 */
	@Autowired
	public ActRedpActDetailMapper actRedpActDetailMapper;

	/** 赠送体验金详细Maper接口 */
	@Autowired
	public ActExpActDetailMapper actExpActDetailMapper;

	/** 投资奖励详细Maper接口 */
	@Autowired
	public ActInvAwaActDetailMapper actInvAwaActDetailMapper;
	/** 财富合伙人详细 接口 */
	@Autowired
	public ActInvWealthCoopDetailMapper actDetailMapper;

	/** 客户信息Maper接口 */
	@Autowired
	public CusTomerMapper cusTomerMapper;

	/** 客户信息Service接口 */
	@Autowired
	public ICusTomerService cusTomerServiceImpl;

	/** 系统VIP信息Mapper接口 */
	@Autowired
	public SysVipinfoMapper sysVipinfoMapper;

	/** 客户积分历史流水Mapper接口 */
	@Autowired
	public CustPoinWaterMapper custPoinWaterMapper;

	/** 客户经验历史流水Mapper接口 */
	@Autowired
	public CustExperienceWaterMapper custExperienceWaterMapper;

	/** 赠送客户VIP历史流水Mapper接口 */
	@Autowired
	public CustLargessVipWaterMapper custLargessVipWaterMapper;

	/** 系统参数Mapper接口 */
	@Autowired
	public SysParamsMapper sysParamsMapper;

	/** 普通资金处理实现类 */
	@Autowired
	public BizAccountCommonServiceImpl bizAccountCommonServiceImpl;

	/** 体验金实现类 */
	@Autowired
	public BizAccountExperienceServiceImpl bizAccountExperienceServiceImpl;

	/** 普通资金处理实现类 */
	@Autowired
	public BizFundtallyServiceImpl bizFundtallyServiceImpl;

	/** 发送短信服务类 */
	@Autowired
	public SmsServiceImpl smsServiceImpl;

	/** 生日特权项和活动关系表 服务实现 */
	@Autowired
	public ActBirPriItemActivityRelServiceImpl birPriItemActivityRelServiceImpl;
	/** 短信模板Maper接口 */
	@Autowired
	public SysSmsTemplatesMapper sysSmsTemplatesMapper;

	/**
	 * 
	 * @Description : 查询系统活动列表,带分页
	 * @param actActivity
	 *            系统活动对象
	 * @param info
	 *            分页对象
	 * @return 系统活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 下午3:42:06
	 */
	@Override
	public PageList<ActActivity> selectAllPage(ActActivity actActivity,
			PageInfo info) {
		// 修改适用开始时间格式-begin
		if (null != actActivity.getBeginApplyStartData()
				&& !"".equals(actActivity.getBeginApplyStartData())) {
			actActivity.setBeginApplyStartData(EscfDateUtil.formatterDate(
					actActivity.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != actActivity.getEndApplyStartData()
				&& !"".equals(actActivity.getEndApplyStartData())) {
			actActivity.setEndApplyStartData(EscfDateUtil.formatterDate(
					actActivity.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != actActivity.getBeginApplyFinishData()
				&& !"".equals(actActivity.getBeginApplyFinishData())) {
			actActivity.setBeginApplyFinishData(EscfDateUtil.formatterDate(
					actActivity.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != actActivity.getEndApplyFinishData()
				&& !"".equals(actActivity.getEndApplyFinishData())) {
			actActivity.setEndApplyFinishData(EscfDateUtil.formatterDate(
					actActivity.getEndApplyFinishData(), 2));
		}
		return actActivityMapper.selectAllPage(actActivity, info);
	}

	/**
	 * 
	 * @Description : 根据活动ID查询活动已有条件信息
	 * @param objectId
	 *            某个类型活动ID
	 * @param activityType
	 *            活动类型
	 * @return 已选条件列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:54:21
	 */
	@Override
	public List<ActParConRel> selectActParConRelByObjectId(String objectId,
			String activityType) {
		List<ActParConRel> list = new ArrayList<ActParConRel>();
		list = actParConRelMapper.selectActParConRelByObjectId(objectId,
				activityType);
		return list;
	}

	/**
	 * 
	 * @Description : 编辑活动
	 * @param actActivity
	 *            活动对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月3日 下午7:01:36
	 */
	@Override
	public void saveOrUpdatePir(ActActivity actActivity)
			throws FrameworkException {
		try {
			// 判断适用开始时间是否填写
			if (StringUtil.isBlank(actActivity.getStartDate())) {
				// 如果没有填写就默认设置时间
				actActivity.setStartDate(DateUtil.format(new Date(),
						"yyyy-MM-dd") + " 00:00:00");
			}
			// 判断适用结束时间是否填写
			if (StringUtil.isBlank(actActivity.getEndDate())) {
				// 如果没有填写就默认设置时间
				actActivity.setEndDate("2099-12-31 23:59:59");
			}
			// 不为空修改，为空新增
			if (!StringUtil.isBlank(actActivity.getPid())
					&& !"-1".equals(actActivity.getPid())) {
				actActivity.setLastUpdateTime(DateUtil.getToday().toString());// 最后一次修改时间
				actActivity.setActivityType("7");// 设置活动类型为vip生日特权
				// 修改活动信息
				actActivityMapper.updateByPidAndActivityType(actActivity);
				// 先删除原条件信息
				actParConRelMapper.deleteByObjectIdAndActivityType(
						actActivity.getPid(), "7");
				// 先删除原特权信息
				activityRelMapper.deleteByObjectIdAndActivityType(actActivity
						.getPid());

				// 判断是否选取了条件信息
				if (!StringUtil.isBlank(actActivity.getCondIds())) {
					// 解析出来选择的条件信息
					String[] conIds = actActivity.getCondIds().split(",");
					// 循环添加
					for (int i = 0; i < conIds.length; i++) {
						// 创建活动参与条件关系对象
						ActParConRel actParConRel = new ActParConRel();
						// 赋值
						actParConRel.setPid(actParConRel.getPK());// 设置PID
						actParConRel.setActivityId(actActivity.getPid());// 赋值活动ID

						actParConRel.setCondId(conIds[i]);// 设置条件ID
						actParConRel.setStatus("1");// 设置状态为1:表示正常
						actParConRel.setCreateUser(actActivity.getCreateUser());// 设置创建人
						actParConRel.setCreateTime(DateUtil.getToday()
								.toString());// 设置创建时间
						// 新增活动参与条件信息
						actParConRelMapper.insertSelective(actParConRel);
					}
				}

				// 判断是否选取了特权信息
				if (!StringUtil.isBlank(actActivity.getBirPriIds())) {
					// 解析出来选择的特权信息
					String[] birPriIds = actActivity.getBirPriIds().split(",");
					String[] birPriValues = actActivity.getBirPriValues()
							.split(",");
					String[] birPriTypes = actActivity.getBirPriTypes().split(
							",");
					// 循环添加
					for (int i = 0; i < birPriIds.length; i++) {
						// 创建活动参与条件关系对象
						ActBirPriItemActivityRel actParConRel = new ActBirPriItemActivityRel();

						// 赋值
						actParConRel.setPid(actParConRel.getPK());// 设置PID
						actParConRel.setActivityId(actActivity.getPid());// 赋值活动ID
						actParConRel.setPriType(birPriTypes[i]);// 设置特权类型ID
						actParConRel.setBirPriItemDesc(birPriValues[i]);// 设置特权类型名字
						actParConRel.setObjectId(birPriIds[i]);// 设置条件ID
						actParConRel.setStatus("1");// 设置状态为1:表示正常
						actParConRel.setCreateUser(actActivity.getCreateUser());// 设置创建人
						actParConRel.setCreateTime(DateUtil.getToday());// 设置创建时间

						// 新增活动参与条件关系对象
						activityRelMapper.insert(actParConRel);

					}
				}

			} else {

				actActivity.setPid(actActivity.getPK());// 设置id
				actActivity.setObjectId(actActivity.getPid());
				actActivity.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
				actActivity.setActivityType("7");// 设置活动类型为体验金
				actActivity.setActCode(sysCreateCodeRuleService.generatNoRule(
						"TQ", "7", 4, actActivity.getCreateUser()));
				// 新增活动信息
				actActivityMapper.insertSelective(actActivity);
				// 判断是否选取了条件信息
				if (!StringUtil.isBlank(actActivity.getCondIds())) {
					// 解析出来选择的条件信息
					String[] conIds = actActivity.getCondIds().split(",");
					// 循环添加
					for (int i = 0; i < conIds.length; i++) {
						// 创建活动参与条件关系对象
						ActParConRel actParConRel = new ActParConRel();
						// 赋值
						actParConRel.setPid(actParConRel.getPK());// 设置PID
						actParConRel.setActivityId(actActivity.getPid());// 赋值活动ID

						actParConRel.setCondId(conIds[i]);// 设置条件ID
						actParConRel.setStatus("1");// 设置状态为1:表示正常
						actParConRel.setCreateUser(actActivity.getCreateUser());// 设置创建人
						actParConRel.setCreateTime(DateUtil.getToday()
								.toString());// 设置创建时间
						// 新增活动参与条件信息
						actParConRelMapper.insertSelective(actParConRel);
					}
				}
				// 判断是否选取了特权信息
				if (!StringUtil.isBlank(actActivity.getBirPriIds())) {
					// 解析出来选择的特权信息
					String[] birPriIds = actActivity.getBirPriIds().split(",");
					String[] birPriValues = actActivity.getBirPriValues()
							.split(",");
					String[] birPriTypes = actActivity.getBirPriTypes().split(
							",");
					// 循环添加
					for (int i = 0; i < birPriIds.length; i++) {
						// 创建活动参与条件关系对象
						ActBirPriItemActivityRel actParConRel = new ActBirPriItemActivityRel();

						// 赋值
						actParConRel.setPid(actParConRel.getPK());// 设置PID
						actParConRel.setActivityId(actActivity.getPid());// 赋值活动ID
						actParConRel.setPriType(birPriTypes[i]);// 设置特权类型ID
						actParConRel.setBirPriItemDesc(birPriValues[i]);// 设置特权类型名字
						actParConRel.setObjectId(birPriIds[i]);// 设置特权ID
						actParConRel.setStatus("1");// 设置状态为1:表示正常
						actParConRel.setCreateUser(actActivity.getCreateUser());// 设置创建人
						actParConRel.setCreateTime(DateUtil.getToday());// 设置创建时间

						// 新增活动参与条件关系对象
						activityRelMapper.insert(actParConRel);

					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("编辑活动 :" + e.getMessage());
			}
		}

	}

	@Override
	public PageList<ActActivity> selectActActivityByParam(
			ActActivity actActivity, PageInfo info) {
		return actActivityMapper.selectActActivityByParam(actActivity, info);
	}

	@Override
	public void deleteBatch(String pids) throws FrameworkException {
		if (null != pids && !pids.equals("")) {
			String[] str = pids.split(",");
			actActivityMapper.deleteBatch(str);
		}
	}

	@Override
	public ActActivity selectByPrimaryKey(String pid) {
		return actActivityMapper.selectByPrimaryKey(pid);

	}

	@Override
	public List<ActBirPriItemActivityRel> selectBirPriByObjectId(String objectId) {
		return activityRelMapper.selectBirPriByObjectId(objectId);
	}

	/**
	 * 
	 * @Description : 根据状态查询当前活动的进行数
	 * @param status
	 *            活动状态( -1 已删除 1 未发布 2 进行中 3过期)
	 * @return 当前状态下的活动数量
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午4:30:52
	 */
	@Override
	public int selectCountByStatus(String status) {
		return actActivityMapper.selectCountByStatus(status);
	}

	/**
	 * 
	 * @Description : 活动开始定时任务,修改活动状态
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月24日 上午11:37:58
	 */
	@Override
	public void activityBeginTask() throws FrameworkException {
		try {
			// 获取当前时间
			String date = DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss");
			// 获取所有没有启动的活动
			List<ActActivity> list = actActivityMapper
					.selectActivityBeginTask(date);
			// 循环修改活动的状态
			for (int i = 0; i < list.size(); i++) {
				// 获取对象
				ActActivity aa = list.get(i);
				// 判断类型,调用不同的接口
				if (aa.getActivityType().equals("1")) {
					// 如果是赠送VIP
					pubVipinfoMapper
							.updateActiivtyStatus(aa.getObjectId(), "1");
				} else if (aa.getActivityType().equals("2")) {
					// 如果是红包活动,还需要判断是抢红包,还是送红包
					if (aa.getActCode().indexOf("QHB") != -1) {
						// 抢红包活动,还需要修改活动专区的状态
						pubActivityAreaServiceImpl.updateActivityStatus(
								aa.getObjectId(), 1, "1");
					}
					// 再统一修改红包活动的状态
					pubRedpacketMapper.updateActiivtyStatus(aa.getObjectId(),
							"1");
				} else if (aa.getActivityType().equals("3")) {
					// 如果是赠送体验金
					pubExpGoldMapper
							.updateActiivtyStatus(aa.getObjectId(), "1");
				} else if (aa.getActivityType().equals("4")) {
					// 如果是投资奖励,还需要判断是否是加息劵
					PubInvestAward pubInvestAward = pubInvestAwardMapper
							.selectByPrimaryKey(aa.getObjectId());
					if (pubInvestAward.getInvestAwardType().equals("3")) {
						// 如果是加息卷,还需要修改活动专区里面的状态
						pubActivityAreaServiceImpl.updateActivityStatus(
								aa.getObjectId(), 2, "1");
					}
					// 根据PID获取投资奖励活动对象
					pubInvestAwardMapper.updateActiivtyStatus(aa.getObjectId(),
							"1");
				} else if (aa.getActivityType().equals("8")) {
					// 如果是财富合伙人
					cooperationMapper.updateActiivtyStatus(aa.getObjectId(),
							"1");
				} else if (aa.getActivityType().equals("7")) {
					// 如果是vip 生日特权 因为vip生日特权活动存在活动主表里 不需要改变其他的状态
				}
				String[] pid = { aa.getObjectId() };
				// 统一修改活动信息表的活动状态
				actActivityMapper.updateStatusBatch("2", pid,
						aa.getActivityType());
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("活动开始定时任务,修改活动状态:" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @Description : 活动结束定时任务,修改活动状态
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月26日 下午4:04:13
	 */
	@Override
	public void activityEndTask() throws FrameworkException {
		try {
			// 获取当前时间
			String date = DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss");
			// 获取所有没有启动的活动
			List<ActActivity> list = actActivityMapper
					.selectActivityEndTask(date);
			// 循环修改活动的状态
			for (int i = 0; i < list.size(); i++) {
				// 获取对象
				ActActivity aa = list.get(i);
				// 判断类型,调用不同的接口
				if (aa.getActivityType().equals("1")) {
					// 如果是赠送VIP
					pubVipinfoMapper
							.updateActiivtyStatus(aa.getObjectId(), "2");
				} else if (aa.getActivityType().equals("2")) {
					// 如果是红包活动,还需要判断是抢红包,还是送红包
					if (aa.getActCode().indexOf("QHB") != -1) {
						// 抢红包活动,还需要修改活动专区的状态
						pubActivityAreaServiceImpl.updateActivityStatus(
								aa.getObjectId(), 1, "2");
					}
					// 再统一修改红包活动的状态
					pubRedpacketMapper.updateActiivtyStatus(aa.getObjectId(),
							"2");
				} else if (aa.getActivityType().equals("3")) {
					// 如果是赠送体验金
					pubExpGoldMapper
							.updateActiivtyStatus(aa.getObjectId(), "2");
				} else if (aa.getActivityType().equals("4")) {
					// 如果是投资奖励,还需要判断是否是加息劵
					PubInvestAward pubInvestAward = pubInvestAwardMapper
							.selectByPrimaryKey(aa.getObjectId());
					if (pubInvestAward.getInvestAwardType().equals("3")) {
						// 如果是加息卷,还需要修改活动专区里面的状态
						pubActivityAreaServiceImpl.updateActivityStatus(
								aa.getObjectId(), 2, "2");
					}
					// 根据PID获取投资奖励活动对象
					pubInvestAwardMapper.updateActiivtyStatus(aa.getObjectId(),
							"2");
				} else if (aa.getActivityType().equals("8")) {
					// 如果是财富合伙人
					cooperationMapper.updateActiivtyStatus(aa.getObjectId(),
							"3");
				} else if (aa.getActivityType().equals("7")) {
					// 如果是vip 生日特权 因为vip生日特权活动存在活动主表里 不需要改变其他的状态
				}
				String[] pid = { aa.getObjectId() };
				// 统一修改活动信息表的活动状态
				actActivityMapper.updateStatusBatch("3", pid,
						aa.getActivityType());
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("活动结束定时任务,修改活动状态:" + e.getMessage());
			}
		}

	}

	/**
	 * 
	 * @Description : 活动赠送
	 * @param borrowId
	 *            借款ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午3:50:38
	 */
	@Override
	public void activityGive(String borrowId) throws FrameworkException {
		try {
			// 查询所有正在进行中的活动
			List<ActActivity> list = actActivityMapper
					.selectAllUnderwayActivity();
			// 判断是否有活动正在进行中
			if (list.size() > 0) {
				// 循环解析list
				for (int i = 0; i < list.size(); i++) {
					// 需要活动奖励的客户集合
					List<BizBorrowDetail> listBorrowDetail = null;
					// 获得对象
					ActActivity actActivity = list.get(i);
					// 根据活动ID查询活动参与条件
					List<PubCondition> listCondition = pubConditionMapper
							.selectAllByActivityId(actActivity.getPid());
					// 判断是否有条件
					if (listCondition.size() > 0) {
						// 把所有的条件值拼接
						String conditionValue = "";
						// 如果有条件,就拼接
						for (int j = 0; j < listCondition.size(); j++) {
							// 拼接
							conditionValue += listCondition.get(j)
									.getCondValue();
						}
						// 根据条件查询满足的用户
						listBorrowDetail = bizBorrowDetailMapper
								.selectBorrowDetailByConditionAndBorrowId(
										borrowId, conditionValue);
					} else {
						// 如果不需要条件,就把当前活动的奖品每个投资客户都赠送一次
						listBorrowDetail = bizBorrowDetailMapper
								.selectBorrowDetailByConditionAndBorrowId(
										borrowId, null);
					}
					// 判断满足的客户数是否大于1
					if (null != listBorrowDetail && listBorrowDetail.size() > 0) {
						// 调用赠送各种礼品的接口
						if (actActivity.getActivityType().equals(
								Constant.ACTIVITY_TYPE_1)) {
							// 如果是赠送VIP
							sysVipActivityId(actActivity, listBorrowDetail);
						} else if (actActivity.getActivityType().equals(
								Constant.ACTIVITY_TYPE_2)) {
							redpacketActivityId(actActivity, listBorrowDetail);
						} else if (actActivity.getActivityType().equals(
								Constant.ACTIVITY_TYPE_3)) {
							// 如果是体验金
							expGoldByActivityId(actActivity, listBorrowDetail);
						} else if (actActivity.getActivityType().equals(
								Constant.ACTIVITY_TYPE_4)) {
							// 如果是投资奖励,还需要判断是否是加息劵
							actInvAwaByActivityId(actActivity, listBorrowDetail);
						} else if (actActivity.getActivityType().equals(
								Constant.ACTIVITY_TYPE_8)) {
							// 如果是财富合伙人奖励
							pubWealthCoopDetail(actActivity, listBorrowDetail,
									borrowId);
						}
					}
				}
			} else {
				logger.info("没有活动正在进行,记录。");
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("活动赠送:" + e.getMessage());
			}
		}

	}

	/**
	 * Description：<br>
	 * 参与财富合伙人 满标时计算 财富合伙人明细加一 根据财富合伙人奖励值 给邀请人奖励
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月10日
	 * @version v1.0.0
	 * @param actActivity
	 * @param listBorrowDetail
	 */
	private void pubWealthCoopDetail(ActActivity actActivity,
			List<BizBorrowDetail> listBorrowDetail, String borrowId)
			throws FrameworkException {
		// 获取当前时间
		String dateTime = DateUtil.getToday().toString();
		// 获取财富合伙人信息对象
		PubWealthCooperation pubWealthCoop = cooperationMapper
				.selectByPrimaryKey(actActivity.getObjectId());
		// 创建需要修改财富合伙人的用户ID集合
		List<String> listCusTomerPid = new ArrayList<String>();
		// 创建一个用户集合
		List<CusTomer> listCusTomer = new ArrayList<CusTomer>();
		// 1.创建list对象集
		List<ActInvWealthCoopDetail> listCusTomerDetail = new PageList<ActInvWealthCoopDetail>();
		// 2.循环生成财富合伙人详情对象,并添加到list里面
		for (int j = 0; j < listBorrowDetail.size(); j++) {
			// 创建用户对象
			CusTomer ct = cusTomerMapper.selectByPrimaryKey(listBorrowDetail
					.get(j).getCustomerId());
			//如果推荐人id 为空进行下一个
			if(StringUtil.isBlank(ct.getRefereeUser())){
				continue;
			}
			// 创建对象
			ActInvWealthCoopDetail aead = new ActInvWealthCoopDetail();

			aead.setPid(aead.getPK());// 设置PID
			aead.setActivityId(actActivity.getPid());// 设置活动ID
			aead.setWealthCoopId(actActivity.getObjectId());// 设置邀请有奖活动信息ID
			if ("2".equals(pubWealthCoop.getInvestAwardType())) {
				// 百分比奖励
				// 本标的 本客户投资总额 * 奖励比例 = 奖励金额
				BigDecimal investmentAmountTotal = bizBorrowDetailMapper
						.sunInvestAmount(borrowId, listBorrowDetail.get(j)
								.getCustomerId());
				aead.setBonusAmount(pubWealthCoop.getWealthCoopValue()
						.multiply(investmentAmountTotal));
			} else {
				// 固定金额奖励
				aead.setBonusAmount(pubWealthCoop.getWealthCoopValue());// 奖励金额
			}
			aead.setSmsId(actActivity.getSmsId());// 设置短信ID
			aead.setCustomerId(ct.getRefereeUser());// 设置客户ID
			aead.setUseStatus(Constant.USE_STATUS_1);// 使用状态:已使用
			aead.setGetTime(dateTime);// 设置领取时间
			aead.setUseTime(dateTime);// 设置使用时间
			aead.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常:1
			aead.setCreateUser(actActivity.getCreateUser());// 设置创建人
			aead.setCreateTime(dateTime);// 设置创建时间
			// 添加到list列表
			listCusTomerDetail.add(aead);
			// 将客户ID添加到集合里面
			listCusTomerPid.add(ct.getRefereeUser());

			listCusTomer.add(ct);// 添加到客户集合
		}
		// 批量财富合伙人活动明细信息
		actDetailMapper.insertBatch(listCusTomerDetail);
		// 修改客户可用金额的备注
		String note = "【活动】" + pubWealthCoop.getWealthCoopName() + "赠送金额："
				+ pubWealthCoop.getWealthCoopValue() + "元";
		// 批量修改客户可用金额
		bizAccountCommonServiceImpl.updateAmountByCustIds(listCusTomerPid,
				pubWealthCoop.getWealthCoopValue(),
				Constant.CUST_FUND_BUSINESS_TYPE_1,
				TradeTypeConstant.RETURNS_TYPE_415, note,
				pubWealthCoop.getPid());

		// TODO:批量发送短信
		// 操作人
		String userId = "system";
		// 手机号码字符串(以,隔开)
		String phoneNoArr = "";
		// 循环所有用户信息,获取用户的手机号码,发送短信
		for (int k = 0; k < listCusTomer.size(); k++) {
			if (k == 0) {
				phoneNoArr = listCusTomer.get(k).getPhoneNo();
			} else {
				phoneNoArr += "," + listCusTomer.get(k).getPhoneNo();
			}
		}
		// 所属模块
		String model = "activity";
		// 短信模板ID
		String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
				actActivity.getSmsId()).getTempCode();
		// 短信模板替换Map
		Map<String, String> map = new HashMap<String, String>();
		// 赋值
		map.put("{活动名称}", actActivity.getActName());
		map.put("{奖励金额}", pubWealthCoop.getWealthCoopValue().toString());
		// 批量发送短信
		smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);
	}

	/**
	 * Description：<br>
	 * 活动类型是赠送VIP 判定赠送内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param actActivity
	 * @param listBorrowDetail
	 */
	private void sysVipActivityId(ActActivity actActivity,
			List<BizBorrowDetail> listBorrowDetail) throws FrameworkException {
		// 首先获取VIP1的对象
		SysVipinfo sysVipinfo = sysVipinfoMapper.getByLevel("1");
		// 获取当前时间
		String dateTime = DateUtil.getToday().toString();
		// 根据活动ID获取赠送VIP活动对象
		PubVipinfo pubVipinfo = pubVipinfoMapper.selectByPrimaryKey(actActivity
				.getObjectId());
		// 创建一个赠送VIP历史流水List
		List<CustLargessVipWater> listLargessVip = new ArrayList<CustLargessVipWater>();
		// 创建一个用户集合
		List<CusTomer> listCusTomer = new ArrayList<CusTomer>();
		// 创建一个赠送VIP列表
		List<ActVipActDetail> listVipDetail = new ArrayList<ActVipActDetail>();
		// 循环添加到list里面，批量新增
		for (int j = 0; j < listBorrowDetail.size(); j++) {
			// 创建赠送VIP信息对象
			ActVipActDetail avad = new ActVipActDetail();
			// 赋值
			avad.setPid(avad.getPK());// 设置主键
			avad.setActivityId(actActivity.getPid());// 设置活动ID
			avad.setVipId(actActivity.getObjectId());// 设置VIP活动信息ID
			avad.setSmsId(actActivity.getSmsId());// 设置短信ID
			avad.setCustomerId(listBorrowDetail.get(j).getCustomerId());// 设置客户ID
			avad.setUseStatus(Constant.USE_STATUS_1);// 使用状态,已使用
			avad.setUseTime(dateTime);// 使用时间,现在
			avad.setCreateUser(actActivity.getCreateUser());// 设置创建人
			avad.setCreateTime(dateTime);// 设置创建时间
			avad.setStatus(Constant.PUBLIC_STATUS);// 状态1,正常
			avad.setVersion(Constant.PUBLIC_VERSION);// 设置版本号
			// 添加到list
			listVipDetail.add(avad);
			// 创建用户对象
			CusTomer ct = cusTomerMapper.selectByPrimaryKey(listBorrowDetail
					.get(j).getCustomerId());
			// 赋值
			ct.setVipTime(ct.getVipTime() + 100);// 设置VIP时长
			// 判断是否是VIP
			if (!ct.getIsVip().equals("1")) {
				// 如果不是VIP
				ct.setVipLevel(sysVipinfo.getPid());
			}
			listCusTomer.add(ct);// 添加到客户集合
			// 创建赠送VIP历史流水记录信息
			CustLargessVipWater custLargessVipWater = new CustLargessVipWater();
			// 赋值
			custLargessVipWater.setPid(custLargessVipWater.getPK());// 设置主键
			custLargessVipWater.setCustomerId(listBorrowDetail.get(j)
					.getCustomerId());// 设置客户ID
			custLargessVipWater.setVipinfoId(actActivity.getObjectId());// 设置VIP活动ID
			custLargessVipWater.setLargessValue(pubVipinfo.getValidTime()
					.toString());
			custLargessVipWater.setGetType(Constant.LARGESS_VIP_TYPE_3);// 获取类型
			custLargessVipWater.setStatus(Constant.PUBLIC_STATUS);// 状态为1,表示正常
			custLargessVipWater.setDistributionTime(dateTime);
			custLargessVipWater.setCreateUser(actActivity.getCreateUser());// 设置创建人
			custLargessVipWater.setCreateTime(dateTime);// 设置创建时间
			custLargessVipWater.setLarVipWatDesc("赠送VIP,时长为:"
					+ pubVipinfo.getValidTime() + "天");
			// 添加到赠送VIP历史流水List
			listLargessVip.add(custLargessVipWater);
		}
		// 批量新增
		actVipActDetailMapper.insertBatch(listVipDetail);
		// 批量修改客户的VIP状态和VIP时长
		cusTomerServiceImpl.batchUpdateVip(listCusTomer);
		// 批量新增客户赠送VIP流水历史纪录
		custLargessVipWaterMapper.insertBatch(listLargessVip);

		// TODO:批量发送短信
		// 操作人
		String userId = "system";
		// 手机号码字符串(以,隔开)
		String phoneNoArr = "";
		// 循环所有用户信息,获取用户的手机号码,发送短信
		for (int k = 0; k < listCusTomer.size(); k++) {
			if (k == 0) {
				phoneNoArr = listCusTomer.get(k).getPhoneNo();
			} else {
				phoneNoArr += "," + listCusTomer.get(k).getPhoneNo();
			}
		}
		// 所属模块
		String model = "activity";
		// 短信模板ID
		String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
				actActivity.getSmsId()).getTempCode();
		// 短信模板替换Map
		Map<String, String> map = new HashMap<String, String>();
		// 赋值
		map.put("{活动名称}", actActivity.getActName());
		map.put("{VIP时长}", pubVipinfo.getValidTime() / 30 + "");
		// 批量发送短信
		smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);

	}

	/**
	 * Description：<br>
	 * 活动类型是红包 判定赠送内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param actActivity
	 * @param listBorrowDetail
	 */
	private void redpacketActivityId(ActActivity actActivity,
			List<BizBorrowDetail> listBorrowDetail) {
		// 获取当前时间
		String dateTime = DateUtil.getToday().toString();
		// 获取红包对象
		PubRedpacket pubRedpacket = pubRedpacketMapper
				.selectByPrimaryKey(actActivity.getObjectId());
		// 创建一个用户集合
		List<CusTomer> listCusTomer = new ArrayList<CusTomer>();
		// 如果是红包活动,还需要判断是抢红包,还是送红包
		if (actActivity.getActCode().indexOf("SHB") != -1) {
			// 如果是送红包
			// 创建需要修改可用金额的用户ID集合
			List<String> listCusTomerPid = new ArrayList<String>();
			// 1.创建list对象集
			List<ActRedpActDetail> listRedpDetail = new PageList<ActRedpActDetail>();
			// 2.循环生成红包详情对象,并添加到list里面
			for (int j = 0; j < listBorrowDetail.size(); j++) {
				// 创建对象
				ActRedpActDetail arad = new ActRedpActDetail();
				arad.setPid(arad.getPK());// 设置PID
				arad.setActivityId(actActivity.getPid());// 设置活动ID
				arad.setRedpacketId(actActivity.getObjectId());// 设置红包活动信息ID
				arad.setSmsId(actActivity.getSmsId());// 设置短信ID
				arad.setCustomerId(listBorrowDetail.get(j).getCustomerId());// 设置客户ID
				arad.setGetAmount(pubRedpacket.getRepAmount());// 设置领取金额
				arad.setGetTime(dateTime);// 设置领取时间
				arad.setUseTime(dateTime);// 设置使用时间
				arad.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常:1
				arad.setUseStatus(Constant.USE_STATUS_1);// 设置使用状态为已使用：1
				arad.setCreateUser(actActivity.getCreateUser());// 设置创建人
				arad.setCreateTime(dateTime);// 设置创建时间
				// 添加到list列表
				listRedpDetail.add(arad);
				// 将客户ID添加到集合里面
				listCusTomerPid.add(listBorrowDetail.get(j).getCustomerId());
				// 创建用户对象
				CusTomer ct = cusTomerMapper
						.selectByPrimaryKey(listBorrowDetail.get(j)
								.getCustomerId());
				listCusTomer.add(ct);// 添加到客户集合
			}
			// 批量新增红包信息
			actRedpActDetailMapper.insertBatchSong(listRedpDetail);
			// 修改客户体验金的备注
			String note = "【活动】" + pubRedpacket.getRepName() + "赠送红包："
					+ pubRedpacket.getRepAmount() + "元";
			// 修改系统资金
			bizFundtallyServiceImpl.insertBizFundtallyByUserIds(
					listCusTomerPid, pubRedpacket.getRepAmount(),
					Constant.CUST_FUND_BUSINESS_TYPE_2,
					TradeTypeConstant.SYSTEM_TRADE_TYPE_1032,
					DateUtil.format(dateTime), note);
			// 批量修改客户可用金额
			bizAccountCommonServiceImpl.updateAmountByCustIds(listCusTomerPid,
					pubRedpacket.getRepAmount(),
					Constant.CUST_FUND_BUSINESS_TYPE_1,
					TradeTypeConstant.RETURNS_TYPE_415, note,
					pubRedpacket.getPid());

			// TODO:批量发送短信
			// 操作人
			String userId = "system";
			// 手机号码字符串(以,隔开)
			String phoneNoArr = "";
			// 循环所有用户信息,获取用户的手机号码,发送短信
			for (int k = 0; k < listCusTomer.size(); k++) {
				if (k == 0) {
					phoneNoArr = listCusTomer.get(k).getPhoneNo();
				} else {
					phoneNoArr += "," + listCusTomer.get(k).getPhoneNo();
				}
			}
			// 所属模块
			String model = "activity";
			// 短信模板ID
			String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
					actActivity.getSmsId()).getTempCode();
			// 短信模板替换Map
			Map<String, String> map = new HashMap<String, String>();
			// 赋值
			map.put("{活动名称}", actActivity.getActName());
			map.put("{红包金额}", pubRedpacket.getRepAmount().toString());
			// 批量发送短信
			smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);
		}
	}

	/**
	 * Description：<br>
	 * 活动类型是 体验金判定赠送内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param actActivity
	 * @param listBorrowDetail
	 */
	private void expGoldByActivityId(ActActivity actActivity,
			List<BizBorrowDetail> listBorrowDetail) {
		// 获取当前时间
		String dateTime = DateUtil.getToday().toString();
		// 获取体验金信息对象
		PubExpGold pubExpGold = pubExpGoldMapper.selectByPrimaryKey(actActivity
				.getObjectId());
		// 创建需要修改体验金的用户ID集合
		List<String> listCusTomerPid = new ArrayList<String>();
		// 创建一个用户集合
		List<CusTomer> listCusTomer = new ArrayList<CusTomer>();
		// 1.创建list对象集
		List<ActExpActDetail> listExpDetail = new PageList<ActExpActDetail>();
		// 2.循环生成体验金详情对象,并添加到list里面
		for (int j = 0; j < listBorrowDetail.size(); j++) {
			// 创建对象
			ActExpActDetail aead = new ActExpActDetail();
			aead.setPid(aead.getPK());// 设置PID
			aead.setActivityId(actActivity.getPid());// 设置活动ID
			aead.setExpId(actActivity.getObjectId());// 设置体验金活动信息ID
			aead.setSmsId(actActivity.getSmsId());// 设置短信ID
			aead.setCustomerId(listBorrowDetail.get(j).getCustomerId());// 设置客户ID
			aead.setUseStatus(Constant.USE_STATUS_1);// 使用状态:已使用
			aead.setGetTime(dateTime);// 设置领取时间
			aead.setUseTime(dateTime);// 设置使用时间
			aead.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常:1
			aead.setCreateUser(actActivity.getCreateUser());// 设置创建人
			aead.setCreateTime(dateTime);// 设置创建时间
			// 添加到list列表
			listExpDetail.add(aead);
			// 将客户ID添加到集合里面
			listCusTomerPid.add(listBorrowDetail.get(j).getCustomerId());
			// 创建用户对象
			CusTomer ct = cusTomerMapper.selectByPrimaryKey(listBorrowDetail
					.get(j).getCustomerId());
			listCusTomer.add(ct);// 添加到客户集合
		}
		// 批量新增体验金信息
		actExpActDetailMapper.insertBatch(listExpDetail);
		// 修改客户体验金的备注
		String note = "【活动】" + pubExpGold.getExpName() + "赠送体验金："
				+ pubExpGold.getExpAmount() + "元";
		// 批量修改客户体验金
		bizAccountExperienceServiceImpl.updateAmountByCustIds(listCusTomerPid,
				pubExpGold.getExpAmount(), Constant.CUST_FUND_BUSINESS_TYPE_1,
				TradeTypeConstant.SYSTEM_TRADE_TYPE_1024, note, null);

		// TODO:批量发送短信
		// 操作人
		String userId = "system";
		// 手机号码字符串(以,隔开)
		String phoneNoArr = "";
		// 循环所有用户信息,获取用户的手机号码,发送短信
		for (int k = 0; k < listCusTomer.size(); k++) {
			if (k == 0) {
				phoneNoArr = listCusTomer.get(k).getPhoneNo();
			} else {
				phoneNoArr += "," + listCusTomer.get(k).getPhoneNo();
			}
		}
		// 所属模块
		String model = "activity";
		// 短信模板ID
		String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
				actActivity.getSmsId()).getTempCode();
		// 短信模板替换Map
		Map<String, String> map = new HashMap<String, String>();
		// 赋值
		map.put("{活动名称}", actActivity.getActName());
		map.put("{体验金金额}", pubExpGold.getExpAmount().toString());
		// 批量发送短信
		smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);

	}

	/**
	 * Description：<br>
	 * 活动类型是 投资奖励判定赠送内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param actActivity
	 * @param listBorrowDetail
	 * @throws FrameworkException
	 */
	private void actInvAwaByActivityId(ActActivity actActivity,
			List<BizBorrowDetail> listBorrowDetail) throws FrameworkException {
		// 如果是投资奖励,还需要判断是否是加息劵
		PubInvestAward pubInvestAward = pubInvestAwardMapper
				.selectByPrimaryKey(actActivity.getObjectId());
		// 获取当前时间
		String dateTime = DateUtil.getToday().toString();
		if (!pubInvestAward.getInvestAwardType().equals(
				Constant.INVEST_AWARD_TYPE_3)) {
			// 如果是积分or经验
			// 创建积分历史纪录集合
			List<CustPoinWater> listPoin = new ArrayList<CustPoinWater>();
			// 创建经验历史纪录集合
			List<CustExperienceWater> listExperience = new ArrayList<CustExperienceWater>();
			// 创建需要修改用户积分集合
			List<CusTomer> listCusTomer = new ArrayList<CusTomer>();
			// 1.创建list对象集
			List<ActInvAwaActDetail> listInvAwaDetail = new PageList<ActInvAwaActDetail>();
			// 2.循环生成红包详情对象,并添加到list里面
			for (int j = 0; j < listBorrowDetail.size(); j++) {
				// 创建对象
				ActInvAwaActDetail aiad = new ActInvAwaActDetail();
				aiad.setPid(aiad.getPK());// 设置PID
				aiad.setActivityId(actActivity.getPid());// 设置活动ID
				aiad.setInvestAwardId(actActivity.getObjectId());// 设置投资奖励活动信息ID
				aiad.setSmsId(actActivity.getSmsId());// 设置短信ID
				aiad.setCustomerId(listBorrowDetail.get(j).getCustomerId());// 设置客户ID
				aiad.setUseStatus(Constant.USE_STATUS_1);// 使用状态:已使用
				aiad.setGetTime(dateTime);// 设置领取时间
				aiad.setUseTime(dateTime);// 设置使用时间
				aiad.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常:1
				aiad.setCreateUser(actActivity.getCreateUser());// 设置创建人
				aiad.setCreateTime(dateTime);// 设置创建时间
				// 添加到list列表
				listInvAwaDetail.add(aiad);
				// 创建用户对象
				CusTomer ct = cusTomerMapper
						.selectByPrimaryKey(listBorrowDetail.get(j)
								.getCustomerId());
				// 判断是积分还是经验
				if (pubInvestAward.getInvestAwardType().equals(
						Constant.INVEST_AWARD_TYPE_1)) {
					// 如果是积分
					CustPoinWater custPoin = new CustPoinWater();
					// 赋值
					custPoin.setPid(custPoin.getPK());// 设置主键
					custPoin.setPointValue(Integer.parseInt(pubInvestAward
							.getInvestAwardValue().toString()));// 设置积分值
					custPoin.setPointGetType("");// 设置积分获取类型
					custPoin.setPointType(Constant.POINT_TYPE_1);// 设置积分类型(1
																	// 赠送、2兑换)
					custPoin.setAvailablePoint(ct.getAvailablePoint());// 设置当前积分值
					custPoin.setCustomerId(listBorrowDetail.get(j)
							.getCustomerId());// 设置客户ID
					custPoin.setStatus(Constant.PUBLIC_STATUS);// 设置状态为1
					custPoin.setHappenTime(dateTime);// 设置发生时间
					custPoin.setCreateUser(listBorrowDetail.get(j)
							.getCustomerId());// 设置创建人ID
					custPoin.setCreateTime(dateTime);// 设置创建时间
					custPoin.setPotWatDesc("获取积分："
							+ pubInvestAward.getInvestAwardValue());// 设置备注
					// 添加到积分历史流水list集合
					listPoin.add(custPoin);
					// 添加用户集合,作为批量修改用户积分
					CusTomer c = new CusTomer();
					c.setPid(listBorrowDetail.get(j).getCustomerId());// 设置PID
					c.setAvailablePoint(custPoin.getPointValue()
							+ ct.getAvailablePoint());// 设置新积分
					// 添加到用户修改积分list集合
					listCusTomer.add(c);
				} else if (pubInvestAward.getInvestAwardType().equals(
						Constant.INVEST_AWARD_TYPE_2)) {
					// 如果是经验
					CustExperienceWater custExperience = new CustExperienceWater();
					custExperience.setPid(custExperience.getPK());// 设置主键
					custExperience.setCustomerId(listBorrowDetail.get(j)
							.getCustomerId());// 设置客户ID
					custExperience.setExpValue(Integer.parseInt(pubInvestAward
							.getInvestAwardValue().toString()));// 设置经验值
					custExperience.setExpGetType(Constant.EXP_GET_TYPE_3);// 设置经验获取类型
					custExperience.setAvailableExp(ct.getEmpiricalValue());// 设置当前累计经验
					custExperience.setStatus(Constant.PUBLIC_STATUS);// 设置状态
					custExperience.setHappenTime(dateTime);// 设置发生时间
					custExperience.setCreateUser(listBorrowDetail.get(j)
							.getCustomerId());// 设置创建人ID
					custExperience.setCreateTime(dateTime);// 设置创建时间
					custExperience.setExpWatDesc("获得经验："
							+ pubInvestAward.getInvestAwardValue());// 设置备注
					// 添加到积分流水历史list集合
					listExperience.add(custExperience);
					// 添加用户集合,作为批量修改用户经验
					CusTomer c = new CusTomer();
					c.setPid(listBorrowDetail.get(j).getCustomerId());// 设置PID
					c.setAvailablePoint(custExperience.getExpValue()
							+ ct.getEmpiricalValue());// 设置经验值
					// 添加到用户修改经验list集合
					listCusTomer.add(c);
				}
			}
			// 批量新增投资奖励人到投资奖励详情表里面
			actInvAwaActDetailMapper.insertBatch(listInvAwaDetail);
			// 判断
			if (pubInvestAward.getInvestAwardType().equals(
					Constant.INVEST_AWARD_TYPE_1)) {
				// 如果是积分
				cusTomerServiceImpl.batchUpdatePoin(listCusTomer);
				// 批量新增积分流水历史纪录
				custPoinWaterMapper.insertBatch(listPoin);

				// TODO:批量发送短信
				// 操作人
				String userId = "system";
				// 手机号码字符串(以,隔开)
				String phoneNoArr = "";
				// 循环所有用户信息,获取用户的手机号码,发送短信
				for (int k = 0; k < listCusTomer.size(); k++) {
					if (k == 0) {
						phoneNoArr = listCusTomer.get(k).getPhoneNo();
					} else {
						phoneNoArr += "," + listCusTomer.get(k).getPhoneNo();
					}
				}
				// 所属模块
				String model = "activity";
				// 短信模板ID
				String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
						actActivity.getSmsId()).getTempCode();
				// 短信模板替换Map
				Map<String, String> map = new HashMap<String, String>();
				// 赋值
				map.put("{活动名称}", actActivity.getActName());
				map.put("{积分值}", pubInvestAward.getInvestAwardValue()
						.toString());
				// 批量发送短信
				smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);

			} else if (pubInvestAward.getInvestAwardType().equals(
					Constant.INVEST_AWARD_TYPE_2)) {
				// 如果是经验
				cusTomerServiceImpl.batchUpdateExperience(listCusTomer);
				// 批量新增经验流水历史纪录
				custExperienceWaterMapper.insertBatch(listExperience);

				// TODO:批量发送短信
				// 操作人
				String userId = "system";
				// 手机号码字符串(以,隔开)
				String phoneNoArr = "";
				// 循环所有用户信息,获取用户的手机号码,发送短信
				for (int k = 0; k < listCusTomer.size(); k++) {
					if (k == 0) {
						phoneNoArr = listCusTomer.get(k).getPhoneNo();
					} else {
						phoneNoArr += "," + listCusTomer.get(k).getPhoneNo();
					}
				}
				// 所属模块
				String model = "activity";
				// 短信模板ID
				String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
						actActivity.getSmsId()).getTempCode();
				// 短信模板替换Map
				Map<String, String> map = new HashMap<String, String>();
				// 赋值
				map.put("{活动名称}", actActivity.getActName());
				map.put("{经验值}", pubInvestAward.getInvestAwardValue()
						.toString());
				// 批量发送短信
				smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);

			}
		}
	}

	/**
	 * Description：<br>
	 * 根据活动id 判定vip生日特权活动 应该赠送的内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @throws FrameworkException
	 */
	@Override
	public void birPriRelByActivityId() throws FrameworkException {
		System.out.println("vip生日特权定时任务开始");
		/**
		 * 活动类型是生日特权，根据特权id 找到特权和活动管理表数据找到特权类型，根据特权类型再判断是什么生日特权的活动
		 */
		// 查询没有过期的VIP生日特权活动
		List<ActActivity> activityList = actActivityMapper
				.selectValidActByActType("7");

		// 没有有效的生日特权活动 不执行定时任务
		if (activityList.size() <= 0) {
			System.out.println("没有进行中的vip生日特权活动，任务结束");
			return;
		}
		String conditionValue = "";
		// VIP生日特权设置的条件 和 VIP特权设置的参与条件
		for (int j = 0; j < activityList.size(); j++) {
			ActActivity actActivity = activityList.get(j);
			conditionValue += conditionValue(actActivity);
			// 先根据 活动关联建立的id 找到活动和特权关系
			List<ActBirPriItemActivityRel> birPriRelList = birPriItemActivityRelServiceImpl
					.selectBirPriRelByActivityId(actActivity.getObjectId());
			// vip生日特权没有选择赠送活动 则为数据异常
			if (birPriRelList == null && birPriRelList.size() <= 0)
				return;

			for (int index = 0; index < birPriRelList.size(); index++) {
				ActBirPriItemActivityRel rel = birPriRelList.get(index);
				// 根据关系表objectId 找到活动主表的信息
				ActActivity activity = actActivityMapper
						.selectActivityByObjectId(rel.getObjectId());
				activity = actActivityMapper.selectByPrimaryKey(activity
						.getPid());
				conditionValue += conditionValue(activity);
				// 满足 VIP生日特权设置的条件 和 VIP特权设置的参与条件 通过的客户集合
				List<CusTomer> passCust = selectPassCustByCondition(conditionValue);
				if (passCust == null || passCust.size() <= 0) {
					System.out.println("没有满足的客户，vip生日特权定时任务结束");
					logger.info("没有满足的客户，vip生日特权定时任务结束");
					return;
				}
				List<BizBorrowDetail> listBorrowDetail = new ArrayList<BizBorrowDetail>();
				for (CusTomer cusTomer : passCust) {
					BizBorrowDetail detail = new BizBorrowDetail();
					detail.setCustomerId(cusTomer.getPid());
					listBorrowDetail.add(detail);
				}
				Integer reltype = new Integer(rel.getPriType());
				switch (reltype) {
				// 根据特权类型判定赠送内容
				case 1:
					// 投资奖励 t_pub_invest_award
					actInvAwaByActivityId(activity, listBorrowDetail);
					break;
				case 2:
					// 红包奖励 t_pub_redpacket
					redpacketActivityId(activity, listBorrowDetail);
					break;
				case 3:
					// 体验金 t_pub_exp_gold
					expGoldByActivityId(activity, listBorrowDetail);
					break;
				case 4:
					// 投资奖励 t_pub_invest_award
					actInvAwaByActivityId(activity, listBorrowDetail);
					break;
				case 5:
					// 投资奖励 t_pub_invest_award
					actInvAwaByActivityId(activity, listBorrowDetail);
					break;
				default:
					break;
				}
			}

		}

	}

	/**
	 * Description：<br>
	 * 根据活动生成活动设置的条件
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param actActivity
	 * @return
	 */
	public String conditionValue(ActActivity actActivity) {
		String conditionValue = "";
		// 根据活动ID查询活动参与条件
		List<PubCondition> listCondition = pubConditionMapper
				.selectAllByActivityId(actActivity.getPid());
		// 判断是否有条件
		if (listCondition.size() > 0) {
			// 把所有的条件值拼接
			// 如果有条件,就拼接
			for (int j = 0; j < listCondition.size(); j++) {
				// 拼接
				conditionValue += listCondition.get(j).getCondValue();
				if (j < listCondition.size() - 1) {
					conditionValue += " union all ";
				}

			}
		}
		return conditionValue;
	}

	/**
	 * Description：<br>
	 * 满足VIP生日特权活动的客户
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param conditionValue
	 *            活动设置的条件
	 * @return
	 */
	public List<CusTomer> selectPassCustByCondition(String conditionValue) {
		// 符合条件的客户
		List<CusTomer> passCustList = new ArrayList<CusTomer>();
		// 查询当天生日的 客户集合
		List<CusTomer> custList = cusTomerServiceImpl
				.selectCustomerByBirthdayToDay();
		// 如果没有当天生日的 客户 不执行生日特权活动
		if (custList.size() <= 0)
			return null;
		// 条件为空 返回今天生日的客户
		if (StringUtil.isBlank(conditionValue)) {
			return custList;
		}
		// 根据条件查询满足的用户
		List<CusTomer> custs = cusTomerServiceImpl
				.selectCustByCondition(conditionValue);
		
		for (CusTomer cust : custList) {
			for (CusTomer cusTomer : custs) {
				// 客户既是当天生日 并且 符合活动设置的条件 才是通过的客户
				if (cust.getPid().equals(cusTomer.getPid())) {
					passCustList.add(cust);
				}
			}
		}
		return passCustList;
	}

	/**
	 * 
	 * @Description : 注册or认证送红包
	 * @param customerId
	 *            客户ID
	 * @param redType
	 *            类型（注册：Constant.REGISTER_GIVERED,认证：Constant.AUTHENTICA_GIVERED
	 *            ）
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 下午2:32:08
	 */
	@Override
	public void giveRedByType(String customerId, String redType)
			throws FrameworkException {
		try {
			// 获取红包活动ID
			String redpacketId = sysParamsMapper.getParamsByParamKey(
					Constant.REGISTER_GIVERED).getParamValue();
			// 根据活动ID判断当前客户是否参加过此次活动
			int num = actRedpActDetailMapper.checkUserIsAttend(redpacketId,
					customerId);
			// 判断
			if (num == 0) {
				// 获取当前时间
				String dateTime = DateUtil.getToday().toString();
				// 根据红包ID查询红包对象
				PubRedpacket pubRedpacket = pubRedpacketMapper
						.selectByPrimaryKey(redpacketId);
				// 根据主键查询红包对象,先查询出红包ID
				ActActivity actActivity = actActivityMapper
						.selectByPrimaryKey(actActivityMapper.selectActivityId(
								"2", redpacketId));
				// 如果没有参加,就添加
				// 创建对象
				ActRedpActDetail arad = new ActRedpActDetail();
				arad.setPid(arad.getPK());// 设置PID
				arad.setActivityId(actActivity.getPid());// 设置活动ID
				arad.setRedpacketId(redpacketId);// 设置红包活动信息ID
				arad.setSmsId(actActivity.getSmsId());// 设置短信ID
				arad.setCustomerId(customerId);// 设置客户ID
				arad.setGetAmount(pubRedpacket.getRepAmount());// 设置领取金额
				arad.setGetTime(dateTime);// 设置领取时间
				arad.setUseTime(dateTime);// 设置使用时间
				arad.setStatus(Constant.PUBLIC_STATUS);// 设置状态为正常:1
				arad.setUseStatus(Constant.USE_STATUS_1);// 设置使用状态为已使用：1
				arad.setCreateUser(actActivity.getCreateUser());// 设置创建人
				arad.setCreateTime(dateTime);// 设置创建时间
				// 新增
				actRedpActDetailMapper.insertSelective(arad);

				// 修改客户体验金的备注
				String note = "【注册送红包】红包金额：" + pubRedpacket.getRepAmount()
						+ "元";
				// 客户ID集合
				List<String> listCusTomerPid = new ArrayList<String>();
				listCusTomerPid.add(customerId);
				// 修改系统资金
				bizFundtallyServiceImpl.insertBizFundtallyByUserIds(
						listCusTomerPid, pubRedpacket.getRepAmount(),
						Constant.CUST_FUND_BUSINESS_TYPE_2,
						TradeTypeConstant.SYSTEM_TRADE_TYPE_1032,
						DateUtil.format(dateTime), note);
				// 批量修改客户可用金额
				bizAccountCommonServiceImpl.updateAmountByCustIds(
						listCusTomerPid, pubRedpacket.getRepAmount(),
						Constant.CUST_FUND_BUSINESS_TYPE_1,
						TradeTypeConstant.RETURNS_TYPE_415, note,
						pubRedpacket.getPid());
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("注册or认证送红包:" + e.getMessage());
			}
			e.printStackTrace();
		}

	}

}
