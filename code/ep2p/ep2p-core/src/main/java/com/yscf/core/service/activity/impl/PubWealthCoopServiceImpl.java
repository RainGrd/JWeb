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
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.activity.ActActivityMapper;
import com.yscf.core.dao.activity.ActInvAwaActDetailMapper;
import com.yscf.core.dao.activity.ActInvWealthCoopDetailMapper;
import com.yscf.core.dao.activity.ActParConRelMapper;
import com.yscf.core.dao.activity.PubWealthCooperationMapper;
import com.yscf.core.dao.business.BizBorrowDetailMapper;
import com.yscf.core.dao.system.PubConditionMapper;
import com.yscf.core.dao.system.SysSmsTemplatesMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActInvAwaActDetail;
import com.yscf.core.model.activity.ActInvWealthCoopDetail;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.model.activity.PubWealthCooperation;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.system.PubCondition;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.activity.IPubWealthCoopService;
import com.yscf.core.service.financial.impl.BizAccountCommonServiceImpl;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;

/**
 * 
 * @ClassName : PubWealthCoopServiceImpl
 * @Description : 财富合伙人业务服务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午4:20:26
 */
@Service("PubWealthCoopServiceImpl")
public class PubWealthCoopServiceImpl extends BaseService<BaseEntity, String>
		implements IPubWealthCoopService {

	private Logger logger = LoggerFactory
			.getLogger(PubWealthCoopServiceImpl.class);

	@Autowired
	public PubWealthCoopServiceImpl(PubWealthCooperationMapper dao) {
		super(dao);
	}

	@Autowired
	public ActActivityMapper actActivityMapper;

	@Autowired
	public ActParConRelMapper actParConRelMapper;

	@Autowired
	public SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	@Autowired
	public PubWealthCooperationMapper pubWealthCooperationMapper;

	@Autowired
	public ActInvWealthCoopDetailMapper actDetailMapper;
	@Autowired
	public BizAccountCommonServiceImpl bizAccountCommonService;
	@Autowired
	public BizBorrowDetailMapper bizBorrowDetailMapper;

	@Autowired
	public CusTomerMapper cusTomerMapper;
	/** 条件信息Mapper接口 */
	@Autowired
	public PubConditionMapper pubConditionMapper;
	/** 短信模板Maper接口 */
	@Autowired
	public SysSmsTemplatesMapper sysSmsTemplatesMapper;
	/** 发送短信服务类 */
	@Autowired
	public SmsServiceImpl smsServiceImpl;
	/**
	 * 
	 * @Description : 查询财富合伙人信息列表,带分页
	 * @param PubWealthCoop
	 *            财富合伙人信息对象
	 * @param info
	 *            分页对象
	 * @return 财富合伙人信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:22:04
	 */
	@Override
	public PageList<PubWealthCooperation> selectAllPage(
			PubWealthCooperation pubWealthCoop, PageInfo info) {
		// 修改适用开始时间格式-begin
		if (null != pubWealthCoop.getBeginApplyStartData()
				&& !"".equals(pubWealthCoop.getBeginApplyStartData())) {
			pubWealthCoop.setBeginApplyStartData(EscfDateUtil.formatterDate(
					pubWealthCoop.getBeginApplyStartData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubWealthCoop.getEndApplyStartData()
				&& !"".equals(pubWealthCoop.getEndApplyStartData())) {
			pubWealthCoop.setEndApplyStartData(EscfDateUtil.formatterDate(
					pubWealthCoop.getEndApplyStartData(), 2));
		}
		// 修改适用开始时间格式-begin
		if (null != pubWealthCoop.getBeginApplyFinishData()
				&& !"".equals(pubWealthCoop.getBeginApplyFinishData())) {
			pubWealthCoop.setBeginApplyFinishData(EscfDateUtil.formatterDate(
					pubWealthCoop.getBeginApplyFinishData(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != pubWealthCoop.getEndApplyFinishData()
				&& !"".equals(pubWealthCoop.getEndApplyFinishData())) {
			pubWealthCoop.setEndApplyFinishData(EscfDateUtil.formatterDate(
					pubWealthCoop.getEndApplyFinishData(), 2));
		}
		return pubWealthCooperationMapper.selectAllPage(pubWealthCoop, info);
	}

	/**
	 * 
	 * @Description : 编辑财富合伙人活动
	 * @param pubWealthCoop
	 *            财富合伙人信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午2:03:21
	 */
	@Override
	public void PubWealthCoopEdit(PubWealthCooperation pubWealthCoop)
			throws FrameworkException {

		// 判断适用开始时间是否填写
		if (null == pubWealthCoop.getStartTime()
				|| pubWealthCoop.getStartTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubWealthCoop.setStartTime(DateUtil
					.format(new Date(), "yyyy-MM-dd") + " 00:00:00");
		}
		// 判断适用结束时间是否填写
		if (null == pubWealthCoop.getEndTime()
				|| pubWealthCoop.getEndTime().trim().equals("")) {
			// 如果没有填写就默认设置时间
			pubWealthCoop.setEndTime("2099-12-31 23:59:59");
		}
		// 不为空修改，为空新增
		if (!pubWealthCoop.getPid().trim().equals("")
				&& !pubWealthCoop.getPid().trim().equals("-1")) {
			pubWealthCoop.setLastCreateTime(DateUtil.getToday().toString());// 设置修改时间
			pubWealthCoop.setLastCreateUser(pubWealthCoop.getCreateUser());// 设置修改人
			// 修改财富合伙人信息
			pubWealthCooperationMapper
					.updateByPrimaryKeySelective(pubWealthCoop);
			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 赋值
			actActivity.setActTag(pubWealthCoop.getActTag());// 设置活动标签
			actActivity.setActName(pubWealthCoop.getWealthCoopName());// 设置活动名称
			actActivity.setStartDate(pubWealthCoop.getStartTime());// 设置活动开始时间
			actActivity.setEndDate(pubWealthCoop.getEndTime());// 设置活动结束时间
			actActivity.setObjectId(pubWealthCoop.getPid());// 设置财富合伙人ID
			actActivity.setActivityType("8");// 设置活动类型为财富合伙人
			actActivity.setIsSmsWarn(pubWealthCoop.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubWealthCoop.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubWealthCoop.getInvAwaDesc());// 设置活动描述
			actActivity.setStatus(pubWealthCoop.getStatus());// 设置活动状态
			actActivity.setLastUpdateTime(DateUtil.getToday().toString());// 设置最后修改时间
			actActivity.setLastUpdateUser(pubWealthCoop.getCreateUser());// 设置最后修改人ID
			// 修改活动信息
			actActivityMapper.updateByObjectIdAndActivityType(actActivity);
			// 先删除原条件信息
			actParConRelMapper.deleteByObjectIdAndActivityType(
					pubWealthCoop.getPid(), "3");
			// 判断是否选取了条件信息
			if (null != pubWealthCoop.getCondIds()
					&& !pubWealthCoop.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubWealthCoop.getCondIds().split(",");
				// 查询出来活动ID
				String activityId = actActivityMapper
						.selectByObjectIdAndActivityType(
								pubWealthCoop.getPid(), "3");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubWealthCoop.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}

		} else {
			String pubWealthCoopId = pubWealthCoop.getPK();// 获取财富合伙人活动ID
			pubWealthCoop.setPid(pubWealthCoopId);// 设置PID
			pubWealthCoop.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			pubWealthCoop.setCreateUser(pubWealthCoop.getCreateUser());// 设置创建人
			pubWealthCoop.setActCode(sysCreateCodeRuleService.generatNoRule(
					"TJ", "7", 4, pubWealthCoop.getCreateUser()));
			// 新增财富合伙人信息
			pubWealthCooperationMapper.insertSelective(pubWealthCoop);

			// 创建活动信息对象
			ActActivity actActivity = new ActActivity();
			// 获取活动ID
			String activityId = actActivity.getPK();
			// 赋值
			actActivity.setPid(activityId);// 设置PID
			actActivity.setObjectId(pubWealthCoopId);// 设置财富合伙人ID

			actActivity.setActTag(pubWealthCoop.getActTag());// 设置活动标签
			actActivity.setActName(pubWealthCoop.getWealthCoopName());// 设置活动名称
			actActivity.setStartDate(pubWealthCoop.getStartTime());// 设置活动开始时间
			actActivity.setEndDate(pubWealthCoop.getEndTime());// 设置活动结束时间
			actActivity.setActivityType("8");// 设置活动类型为财富合伙人
			actActivity.setIsSmsWarn(pubWealthCoop.getIsSmsWarn());// 设置是否短信提醒
			actActivity.setSmsId(pubWealthCoop.getSmsId());// 设置短信ID
			actActivity.setActDesc(pubWealthCoop.getInvAwaDesc());// 设置活动描述
			actActivity.setStatus(pubWealthCoop.getStatus());// 设置活动状态
			actActivity.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
			actActivity.setCreateUser(pubWealthCoop.getCreateUser());// 设置创建人
			// 新增活动
			actActivityMapper.insertSelective(actActivity);
			// 判断是否选取了条件信息
			if (null != pubWealthCoop.getCondIds()
					&& !pubWealthCoop.getCondIds().equals("")) {
				// 解析出来选择的条件信息
				String[] conIds = pubWealthCoop.getCondIds().split(",");
				// 循环添加
				for (int i = 0; i < conIds.length; i++) {
					// 创建活动参与条件关系对象
					ActParConRel actParConRel = new ActParConRel();
					// 赋值
					actParConRel.setPid(actParConRel.getPK());// 设置PID
					actParConRel.setActivityId(activityId);// 赋值活动ID
					actParConRel.setCondId(conIds[i]);// 设置条件ID
					actParConRel.setStatus("1");// 设置状态为1:表示正常
					actParConRel.setCreateUser(pubWealthCoop.getCreateUser());// 设置创建人
					actParConRel.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
					// 新增活动参与条件信息
					actParConRelMapper.insertSelective(actParConRel);
				}
			}
		}

	}

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param pid
	 *            主键ID
	 * @return 财富合伙人活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午3:11:54
	 */
	@Override
	public PubWealthCooperation getPubWealthCoopByPid(String pid) {
		PubWealthCooperation PubWealthCoop = new PubWealthCooperation();
		PubWealthCoop = pubWealthCooperationMapper.selectByPrimaryKey(pid);
		return PubWealthCoop;
	}

	/**
	 * 
	 * @Description : 批量删除财富合伙人活动
	 * @param pids
	 *            财富合伙人ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:53:20
	 */
	@Override
	public void deleteBatch(String pids) {
		try {
			if (null != pids && !pids.equals("")) {
				String[] str = pids.split(",");
				// 删除财富合伙人活动
				pubWealthCooperationMapper.deleteBatch(str);
				// 删除活动信息
				actActivityMapper.updateStatusBatch("-1", str, "8");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("批量删除财富合伙人活动:" + e.getMessage());
			}
		}
	}

	@Override
	public void pubWealthCoopDetail(String borrowId) throws FrameworkException {
		// 有没有 有效的财富合伙人活动
		List<PubWealthCooperation> activatelist = pubWealthCooperationMapper
				.selectActivateWealthCoop();
		if (activatelist == null || activatelist.size() <= 0) {
			System.out.println("没有进行中的财富合伙人活动！");
			logger.info("没有进行中的财富合伙人活动！");
			return;
		}
		// 正在满标的标的的投资用户
		List<BizBorrowDetail> borrowDetailCustList = bizBorrowDetailMapper
				.selectBorrowDetailsCustByBorrowId(borrowId);
		if (borrowDetailCustList == null || borrowDetailCustList.size() <= 0) {
			System.out.println("标的投资人数为空！");
			logger.info("标的投资人数为空！");
			return;
		}
		// 投资用户
		List<CusTomer> custList = new ArrayList<CusTomer>();
		// 满足条件的用户
		List<CusTomer> passCustList = new ArrayList<CusTomer>();
		for (BizBorrowDetail borrowDetail : borrowDetailCustList) {
			CusTomer cust = new CusTomer();
			cust.setPid(borrowDetail.getCustomerId());
			custList.add(cust);
		}
		// 可能有多个激活的财富合伙人活动
		for (PubWealthCooperation activate : activatelist) {
			// 投资客户是否满足条件 1是通过现有客户推荐过来的 2满足活动设置的条件
			// 根据关系表objectId 找到活动主表的信息
			ActActivity activityMain = actActivityMapper
					.selectActivityByObjectId(activate.getPid());
			activityMain = actActivityMapper.selectByPrimaryKey(activityMain
					.getPid());
			String conditionValue = conditionValue(activityMain.getPid());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("custList", custList);
			params.put("conditionValue", conditionValue);

			passCustList = cusTomerMapper.passCustByCondition(params);
			if (passCustList.size() > 0) {
				for (CusTomer cusTomer : passCustList) {
					// 更新财富合伙人参与表
					updateActInvWealthCoopDetail(cusTomer,activityMain,activate);
					// 更新可用邀请人的客户可用余额表
					BigDecimal money = activate.getWealthCoopValue();
					bizAccountCommonService
							.modifyAvailableAmountWithTallyCreateNoTran(
									cusTomer.getRefereeUser(),
									Constant.CUST_FUND_BUSINESS_TYPE_1,
									TradeTypeConstant.RETURNS_TYPE_405, money,
									"推荐有奖", DateUtil.getSystemDate());
				}

			}

		}
	}

	/**
	 * Description：<br> 
	 * 更新财富合伙人参与表
	 * @author  shiliang.feng
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param cusTomer
	 * @param activate
	 */
	public void updateActInvWealthCoopDetail(CusTomer cusTomer,ActActivity activate,PubWealthCooperation cooperation){
		ActInvWealthCoopDetail actDetail = new ActInvWealthCoopDetail();
		actDetail.setPid(actDetail.getPK());
		actDetail.setActivityId(activate.getPid());// 活动id
		actDetail.setCustomerId(cusTomer.getRefereeUser());// 推荐人信息
		actDetail.setSmsId(activate.getSmsId());// 短信模块
		actDetail.setGetTime(DateUtil.getToday().toString());// 获得时间
		actDetailMapper.insertSelective(actDetail);
		
		// 操作人
		String userId = "system";
		// 手机号码字符串(以,隔开)
		String phoneNoArr = cusTomer.getPhoneNo();
		 
		// 所属模块
		String model = "activity";
		// 短信模板ID
		String tempKey = sysSmsTemplatesMapper.selectByPrimaryKey(
				activate.getSmsId()).getTempCode();
		// 短信模板替换Map
		Map<String, String> map = new HashMap<String, String>();
		// 赋值
		map.put("{活动名称}", activate.getActName());
		map.put("{奖励金额}", cooperation.getWealthCoopValue().toString());
		// 批量发送短信
		smsServiceImpl.sendSms(phoneNoArr, model, tempKey, map, userId);
	}
	/**
	 * Description：<br>
	 * 根据活动生成活动设置的条件
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param activityId
	 *            活动id
	 * @return
	 */
	public String conditionValue(String activityId) {
		String conditionValue = "";
		// 根据活动ID查询活动参与条件
		List<PubCondition> listCondition = pubConditionMapper
				.selectAllByActivityId(activityId);
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
}
