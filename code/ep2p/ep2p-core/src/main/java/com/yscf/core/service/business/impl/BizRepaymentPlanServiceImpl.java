/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 还款计划服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.business.BizRepaymentPlanInfoVoMapper;
import com.yscf.core.dao.business.BizRepaymentPlanMapper;
import com.yscf.core.exception.AvailableAmountExcepiton;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizOverdueRateVO;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.financial.IBizEnsureMoneyService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysAccountService;
import com.yscf.core.service.system.ISysParamsService;

import freemarker.template.utility.NullArgumentException;

/**
 * Description：<br>
 * 还款计划服务层
 * 
 * @author Lin Xu
 * @date 2015年10月22日
 * @version v1.0.0
 */
@Service("bizRepaymentPlanService")
public class BizRepaymentPlanServiceImpl extends BaseService<BaseEntity, String> implements IBizRepaymentPlanService {

	private Logger logger = LoggerFactory.getLogger(BizRepaymentPlanServiceImpl.class);

	@Autowired
	public BizRepaymentPlanServiceImpl(BizRepaymentPlanMapper dao) {
		super(dao);
	}

	@Resource
	private ICustomerAccountService customerAccountService;

	@Resource
	private IBizReceiptPlanService bizReceiptPlanService;

	@Resource
	private IBizReceiptTransferService bizReceiptTransferService;

	@Resource
	private IBizBorrowService bizBorrowService;

	@Resource
	private ISysAccountService sysAccountService;

	@Resource
	private IBizEnsureMoneyService bizEnsureMoneyService;

	@Resource
	private BizRepaymentPlanInfoVoMapper bizRepaymentPlanInfoVoMapper;
	
	@Resource
	private ISysParamsService sysParamsService;

	@Override
	public int updateByPrimaryKeySelective(BizRepaymentPlan record) {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		return brpm.updateByPrimaryKeySelective(record);
	}

	/**
	 * 
	 * Description：<br>
	 * 根据逾期天数获取逾期数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param startDay
	 * @param endDay
	 * @return
	 */
	private BizOverdueRateVO getBizOverdueRateVO(int startDay, int endDay) {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		BizOverdueRateVO vo = new BizOverdueRateVO();
		vo.setStartOverdueDay(startDay);
		vo.setEndOverdueDay(endDay);
		vo.setToDay(DateUtil.format(DateUtil.getToday()));
		BizOverdueRateVO result = brpm.selectOverdueRateVO(vo);
		if (null != result) {
			return result;
		}
		return new BizOverdueRateVO();
	}

	@Override
	public List<BizOverdueRateVO> selectOverdueRateVOList() {
		List<BizOverdueRateVO> resultList = new ArrayList<BizOverdueRateVO>();
		int sumOverdueNum = 0;
		BigDecimal sumOverdueAmt = new BigDecimal(0);
		// 获取 1~10天 逾期数据
		BizOverdueRateVO result = getBizOverdueRateVO(1, 10);

		result.setType("1~10");
		resultList.add(result);
		sumOverdueNum += result.getOverdueNum();
		sumOverdueAmt.add((null == result.getOverdueAmt() ? new BigDecimal(0) : result.getOverdueAmt()));

		// 获取 11~30天 逾期数据
		result = getBizOverdueRateVO(11, 30);
		result.setType("11~30");
		resultList.add(result);
		sumOverdueNum += result.getOverdueNum();
		sumOverdueAmt.add((null == result.getOverdueAmt() ? new BigDecimal(0) : result.getOverdueAmt()));

		// 获取 31~60天 逾期数据
		result = getBizOverdueRateVO(31, 60);
		result.setType("31~60");
		resultList.add(result);
		sumOverdueNum += result.getOverdueNum();
		sumOverdueAmt.add((null == result.getOverdueAmt() ? new BigDecimal(0) : result.getOverdueAmt()));

		// 获取 61~90天 逾期数据
		result = getBizOverdueRateVO(61, 90);
		result.setType("61~90");
		resultList.add(result);
		sumOverdueNum += result.getOverdueNum();
		sumOverdueAmt.add((null == result.getOverdueAmt() ? new BigDecimal(0) : result.getOverdueAmt()));

		// 获取 90天以上 逾期数据
		result = getBizOverdueRateVO(91, 0);
		result.setType("90");
		resultList.add(result);
		sumOverdueNum += result.getOverdueNum();
		sumOverdueAmt.add((null == result.getOverdueAmt() ? new BigDecimal(0) : result.getOverdueAmt()));

		// 计算金额占比 期数占比

		for (BizOverdueRateVO rateVo : resultList) {

			/**
			 * 可以通过BigDecimal的compareTo方法来进行比较。返回的结果是int类型，-1表示小于，0是等于，1是大于
			 */
			if (null != rateVo.getOverdueAmt() && rateVo.getOverdueAmt().compareTo(new BigDecimal("0")) > 1) {
				rateVo.setAmtProportion(ArithmeticUtil.mul(ArithmeticUtil.div(rateVo.getOverdueAmt(), sumOverdueAmt, 4), BigDecimal.valueOf(100)));
			} else {
				rateVo.setAmtProportion(BigDecimal.valueOf(0));
			}

			if (null != rateVo.getOverdueNum() && rateVo.getOverdueNum() > 0) {
				rateVo.setNumProportion(ArithmeticUtil.mul(ArithmeticUtil.div(new BigDecimal(rateVo.getOverdueNum()), new BigDecimal(sumOverdueNum), 4), BigDecimal.valueOf(100)));
			} else {
				rateVo.setNumProportion(BigDecimal.valueOf(0));
			}
		}
		// 把总计添加到list中
		result = new BizOverdueRateVO();
		result.setOverdueNum(sumOverdueNum);
		result.setOverdueAmt(sumOverdueAmt);
		resultList.add(result);

		return resultList;
	}

	@Override
	public Integer getSumReapymentPlanNum() {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		return brpm.getSumReapymentPlanNum();
	}

	@Override
	public Integer getSumOverdueNum() {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		BizOverdueRateVO vo = new BizOverdueRateVO();
		vo.setToDay(DateUtil.format(DateUtil.getToday()));
		return brpm.getSumOverdueNum(vo);
	}

	@Override
	public BigDecimal getHasRepaymentAmt() {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		BizOverdueRateVO vo = new BizOverdueRateVO();
		vo.setToDay(DateUtil.format(DateUtil.getToday()));
		return brpm.getHasRepaymentAmt(vo);
	}

	@Override
	public BigDecimal getPendingRepaymentAmt() {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		BizOverdueRateVO vo = new BizOverdueRateVO();
		vo.setToDay(DateUtil.format(DateUtil.getToday()));
		return brpm.getPendingRepaymentAmt(vo);
	}

	@Override
	public BigDecimal getOverdueNoRepaymentAmt() {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		BizOverdueRateVO vo = new BizOverdueRateVO();
		vo.setToDay(DateUtil.format(DateUtil.getToday()));
		return brpm.getOverdueNoRepaymentAmt(vo);
	}

	/**
	 * 
	 * @Description : :前台_我的借款_待还款
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 我的待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:56:35
	 */
	@Override
	public List<BizRepaymentPlan> selectPendingRepayment(BizRepaymentPlan bizRepaymentPlan, Integer pageIndex, Integer pageSize) {
		BizRepaymentPlanMapper mapper = (BizRepaymentPlanMapper) getDao();
		List<BizRepaymentPlan> list = new ArrayList<BizRepaymentPlan>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizRepaymentPlan.getCustomerId());
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = mapper.selectPendingRepayment(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_待还款：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_待还款的总数
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:18:58
	 */
	@Override
	public Integer selectPendingRepaymentCount(BizRepaymentPlan bizRepaymentPlan) {
		Integer count = 0;
		try {
			BizRepaymentPlanMapper mapper = (BizRepaymentPlanMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizRepaymentPlan.getCustomerId());
			// 获取总数
			count = mapper.selectPendingRepaymentCount(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_待还款的总数：", e.getMessage());
			}
		}
		return count;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_已还款
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 我的已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 下午2:56:45
	 */
	@Override
	public List<BizRepaymentPlan> selectRepayment(BizRepaymentPlan bizRepaymentPlan, Integer pageIndex, Integer pageSize) {
		BizRepaymentPlanMapper mapper = (BizRepaymentPlanMapper) getDao();
		List<BizRepaymentPlan> list = new ArrayList<BizRepaymentPlan>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizRepaymentPlan.getCustomerId());
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = mapper.selectRepayment(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_已还款：", e.getMessage());
			}
		}
		return list;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_已还款的总数
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:19:03
	 */
	@Override
	public Integer selectRepaymentCount(BizRepaymentPlan bizRepaymentPlan) {
		Integer count = 0;
		try {
			BizRepaymentPlanMapper mapper = (BizRepaymentPlanMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("customerId", bizRepaymentPlan.getCustomerId());
			// 获取总数
			count = mapper.selectRepaymentCount(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("前台_我的借款_已还款的总数：", e.getMessage());
			}
		}
		return count;
	}

	@Override
	public void createRepaymentPlan(BizBorrow borrow) throws Exception {
		if (null == borrow) {
			throw new RuntimeException("标的不存在");
		}
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();

		// 删除原有计划，产生新的计划
		brpm.deleteBatch(borrow.getPid(), borrow.getCustomerId());

		// 产生还款记录
		CalculationDto dto = new CalculationDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String receiptPlanStatus = null;
		if (borrow.getBorFullTime() != null) {
			dto.setInterestDate(sdf.format(borrow.getBorFullTime()));
			receiptPlanStatus = Constant.BIZ_REPLAN_STATUS_1;
		} else {
			dto.setInterestDate(sdf.format(new Date()));
		}
		// 获取管理费率
		dto.setManagementRate(borrow.getManageExpenseRate());
		dto.setRepaymentAmt(borrow.getBorrowMoney());
		dto.setRepaymentRate(borrow.getBorrowRate());
		Integer borDeadline = Integer.parseInt(borrow.getBorDeadline());
		dto.setRepaymentDeadline(borDeadline);
		dto.setRepaymentType(Integer.parseInt(borrow.getRepaymentType()));
		ICalculation cal = CalculationFactory.getCalculation(dto);

		List<BizRepaymentPlan> list = cal.execRepaymentCalcByDate();
		List<BizRepaymentPlan> rpList = new ArrayList<BizRepaymentPlan>();
		for (BizRepaymentPlan plan : list) {
			plan.setPid(plan.getPK());
			plan.setBorDeadline(borDeadline);
			plan.setBorrowCode(borrow.getBorrowCode());
			plan.setBorrowId(borrow.getPid());
			plan.setBorrowName(borrow.getBorrowName());
			plan.setCustomerId(borrow.getCustomerId());
			plan.setCreateUser(borrow.getCreateUser());
			plan.setInterestType(borrow.getAccrualType());

			plan.setStatus("1");
			plan.setReceiptPalnStatus(receiptPlanStatus);
			plan.setCreateTime(new Date());

			rpList.add(plan);
		}

		brpm.saveBatch(rpList);

	}

	@Override
	public List<BizRepaymentPlan> findRepaymentPlanByStatus(String bizReplanStatus, String time) {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();

		return brpm.findRepaymentPlanByStatus(bizReplanStatus, time);
	}

	@Override
	public List<BizRepaymentPlan> findRepaymentPlanByTime(String bizReplanStatus, String time) {

		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();

		return brpm.findRepaymentPlanByTime(bizReplanStatus, time);
	}

	@Override
	public void executeRepayment(BizRepaymentPlan brp,String des) throws FrameworkException {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		if (brp == null) {
			throw new NullArgumentException("BizRepaymentPlan is null");
		}
		// 获取借款
		BizBorrow borrow = bizBorrowService.getBizBorrowById(brp.getBorrowId());
		if (null == borrow) {
			throw new NullArgumentException("标的不存在");
		}
		// 1.获取待还总金额
		BigDecimal benXi = brp.getTotalAmount();
		String repUserId = brp.getCustomerId();

		// 2.获取借款人账户金额
		CustomerAccount ca = customerAccountService.getByCusterId(repUserId);
		BigDecimal repMoney = ca.getAvailableAmount() == null ? BigDecimal.ZERO : ca.getAvailableAmount();
		// 3.判断余额是否充足
		if(benXi.compareTo(BigDecimal.ZERO) != 0 ){
			if (benXi.compareTo(repMoney) > 0) {
				// 余额不足
				throw new AvailableAmountExcepiton("还款人余额不足");
	
			}
	
			// 还款人进行还款
			// 1.本金
			BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO : brp.getCapital();
			// 2.利息
			BigDecimal interest = brp.getInterest() == null ? BigDecimal.ZERO : brp.getInterest();
			// 3.管理费
			BigDecimal manageFee = brp.getManagementCost() == null ? BigDecimal.ZERO : brp.getManagementCost();
			// 4.罚息
			BigDecimal lateFee = brp.getLatefee() == null ? BigDecimal.ZERO : brp.getLatefee();
			if (lateFee.compareTo(BigDecimal.ZERO) > 0) {
				// 收取罚息
				String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
				
				customerAccountService.updateDeductedAvailableAmount(lateFee, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_514, note);
				sysAccountService.updateAddSystemAmount(lateFee, repUserId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1027, brp.getBorrowId(), null);
			}
	
			if (capital.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
				customerAccountService.updateDeductedAvailableAmount(capital, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_510, note);
			}
			if (interest.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
				customerAccountService.updateDeductedAvailableAmount(interest, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_512, note);
			}
	
			if (manageFee.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
				customerAccountService.updateDeductedAvailableAmount(manageFee, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_504, note);
				sysAccountService.updateAddSystemAmount(manageFee, repUserId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1011, brp.getBorrowId(), note);
			}
		}
		// 4.获取待收计划
		List<BizReceiptPlan> plans = bizReceiptPlanService.findListByRepaymentId(brp.getPid());

		// 5.进行回款
		if (plans != null && plans.size() > 0) {
			for (int i = 0; i < plans.size(); i++) {
				this.receipt(plans.get(i), borrow.getBorrowCode(), borrow.getBorrowName(), borrow.getBorDeadline());
			}
		}

		brp.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_4);
		brp.setLastUpdateTime(new Date());
		brp.setRepPlaDesc(des);
		brp.setRepaidTime(DateUtil.format(new Date()));
		brp.setRepaidamount(benXi);
		brpm.updateByPrimaryKey(brp);
		
		// 如果是最后一期还款，则需将标的状态改为已结清
		if(brp.getPlanindex().equals(borrow.getBorDeadline())){
			borrow.setBorStatus(Constant.BORROW_STATUS_8);
		}else{
			borrow.setBorStatus(Constant.BORROW_STATUS_5);
		}
		bizBorrowService.updateByPrimaryKeySelective(borrow);
		
		
		
	}
	


	private void receipt(BizReceiptPlan brp, String borrowCode, String borrowName, String borDeadLine) throws FrameworkException {
		// 回款
		// 2.如果状态不是1 2 3的，不进行回款
		String repaidStatus = brp.getReceiptStatus();
		if(!(Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(repaidStatus) ||  
				Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(repaidStatus) ||
				Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(repaidStatus))){
			return;
		}
		
		String userId = brp.getCustomerId();
		
		
		
		// 2.本金
		BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO : brp.getCapital();
		// 3.利息
		BigDecimal interest = brp.getNetInterest() == null ? BigDecimal.ZERO : brp.getNetInterest();
		// 4.罚息
		BigDecimal lateFee = brp.getLateFee() == null ? BigDecimal.ZERO : brp.getLateFee();
		// 5.管理费
		BigDecimal manageFee = brp.getManagementCost() == null ? BigDecimal.ZERO : brp.getManagementCost();
		boolean f = true;
		if(capital.add(interest).add(lateFee).add(manageFee).compareTo(BigDecimal.ZERO)!=0){
			
			if (capital.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
				customerAccountService.updateAddAvailableAmount(capital, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_407, note);
			}
	
		
			if (interest.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
				customerAccountService.updateAddAvailableAmount(interest, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_409, note);
			}
	
		
			if (lateFee.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
				String des = "罚息：" + lateFee.setScale(2, BigDecimal.ROUND_DOWN);
				customerAccountService.updateAddAvailableAmount(lateFee, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_411, note + des);
				// 扣除系统资金，垫付罚息的
				sysAccountService.updateSubSystemAmount(lateFee, brp.getCustomerId(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1026, brp.getBorrowId(), null);
	
			}

			if (manageFee.compareTo(BigDecimal.ZERO) > 0) {
				// 用户扣除
				String note = borrowCode + " " + borrowName;
				customerAccountService.updateDeductedAvailableAmount(manageFee, userId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, note);
				// 系统增加
				customerAccountService.updateDeductedInterestManagementFee(manageFee, userId, brp.getBorrowId(), null);
	
			}
		}else{
			f = false;
		}
		
		
		
		// 6.加息利息
		if(Constant.NET_HIKE_STATUS_0.equals(brp.getNetHikeStatus())){
			BigDecimal netHike = brp.getNetHike() == null ? BigDecimal.ZERO : brp.getNetHike();
			if (netHike.compareTo(BigDecimal.ZERO) > 0) {
				// 用户增加
				String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
				customerAccountService.updateAddAvailableAmount(netHike, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_401, note);
				// 从风险备用金扣除
				sysAccountService.updateSubSystemAmount(netHike, userId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1012, brp.getBorrowId(), note);
	
				brp.setNetHikeStatus(Constant.NET_HIKE_STATUS_1);
			}
			// 7.加息利息管理费
			BigDecimal netManageFee = brp.getIncreaseInterest() == null ? BigDecimal.ZERO : brp.getIncreaseInterest();
			if (netManageFee.compareTo(BigDecimal.ZERO) > 0) {
				String note = borrowCode + " " + borrowName;
				customerAccountService.updateDeductedAvailableAmount(netManageFee, userId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, note);
				customerAccountService.updateDeductedInterestManagementFee(netManageFee, userId, brp.getBorrowId(), "");
			}
		}

		brp.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_6);
		brp.setLastUpdateTime(new Date());
		brp.setRecPlaDesc("定时器还款结清");
		
		bizReceiptPlanService.updateByPrimaryKeySelective(brp);
		
		if(Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(repaidStatus)){
			// 转让中，则将在架的债权，进行下架
			if(f){
				bizReceiptTransferService.soldOutByTransferId(brp.getTransferId(),"债权转让时，发生了还款");
			}
		}
		
		
		

	}

	private boolean isReceipt(String status) {

		boolean result = false;

		if (Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(status)) {
			result = true;
		} else if (Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(status)) {
			result = true;
		}

		return result;
	}

	@Override
	public List<BizRepaymentPlan> selectRepaymentsByBorrowId(String borrowId) {
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		return brpm.selectRepaymentsByBorrowId(borrowId);
	}

	/**
	 * （已被系统逾期垫付的借款人）将其金额还给系统，并改为已结清，如果没有钱，更新其罚息
	 * 
	 * @throws FrameworkException
	 */
	@Override
	public void executeRepaymentToSystem(BizRepaymentPlan brp,String des) throws FrameworkException {
		logger.debug("借款人进行了垫付后还款给系统..........................");
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		if (brp == null) {
			throw new NullArgumentException("BizRepaymentPlan is null");
		}
		// 获取借款
		BizBorrow borrow = bizBorrowService.getBizBorrowById(brp.getBorrowId());
		if (null == borrow) {
			throw new NullArgumentException("标的不存在");
		}
		// 1.获取待还总金额
		BigDecimal benXi = brp.getTotalAmount();

		String repUserId = brp.getCustomerId();

		// 2.获取借款人账户金额
		CustomerAccount ca = customerAccountService.getByCusterId(repUserId);
		BigDecimal repMoney = ca.getAvailableAmount() == null ? BigDecimal.ZERO : ca.getAvailableAmount();
		// 3.判断余额是否充足
		if (benXi.compareTo(repMoney) > 0) {
			// 余额不足
			throw new AvailableAmountExcepiton("还款人余额不足");

		}

		// 扣除还款人的金额
		// 1.本金
		BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO : brp.getCapital();
		// 2.利息
		BigDecimal interest = brp.getInterest() == null ? BigDecimal.ZERO : brp.getInterest();
		// 3.管理费，给系统资金
		BigDecimal manageFee = brp.getManagementCost() == null ? BigDecimal.ZERO : brp.getManagementCost();
		// 4.罚息
		BigDecimal lateFee = brp.getLatefee() == null ? BigDecimal.ZERO : brp.getLatefee();

		if (capital.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
			customerAccountService.updateDeductedAvailableAmount(capital, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_510, note);
		}
		
		if (interest.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
			customerAccountService.updateDeductedAvailableAmount(interest, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_512, note);
		}
		if (lateFee.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
			customerAccountService.updateDeductedAvailableAmount(lateFee, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_514, note);
		}
		
		if(manageFee.compareTo(BigDecimal.ZERO) > 0){
			String note = borrow.getBorrowCode() + " " + borrow.getBorrowName();
			customerAccountService.updateDeductedAvailableAmount(manageFee, repUserId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_504, note);
			sysAccountService.updateAddSystemAmount(manageFee, repUserId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1011, brp.getBorrowId(), note);
		}
		
		int a = 0;
		int num = 1;
		try {
			//垫付时间 - 应还时间，再减去一天
			a = EscfDateUtil.daysBetween(DateUtil.format(brp.getExpireTime()), brp.getPayamountTime());
			a--;
			// 更新罚息时间 - 垫付时间，再加上一天
			num = EscfDateUtil.daysBetween( brp.getPayamountTime(),brp.getLatefeecalculateTime());
			num++;
		} catch (Exception e) {
			// 无效
		}
		int s = a + num;
		s = s == 0 ?1:s;
		BigDecimal sysAmount = lateFee.multiply(new BigDecimal(a / (s * 1.0)));
		BigDecimal beifuAmount = lateFee.subtract(sysAmount);

		// 逾期罚息，垫付日期到借款人还款日期的部分给备付金
		if (beifuAmount != null && beifuAmount.compareTo(BigDecimal.ZERO) > 0) {
			bizEnsureMoneyService.updateAddProvisions(beifuAmount, repUserId, TradeTypeConstant.RISK_TRADE_TYPE_2003, brp.getBorrowId(), null);
		}

		// 逾期罚息，应还日期到垫付日期的部分给系统资金
		if (sysAmount != null && sysAmount.compareTo(BigDecimal.ZERO) > 0) {
			sysAccountService.updateAddSystemAmount(sysAmount, repUserId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1027, brp.getBorrowId(), null);
		}

		// 本金，给备付金 VIP的利息，给备付金
		BigDecimal vipAmount = bizReceiptPlanService.sumVipAmount(Constant.BIZ_RECEIPTPLAN_STATUS_5, brp.getPid());
		vipAmount = vipAmount == null ? BigDecimal.ZERO : vipAmount;
		logger.debug("获取vip利息："+vipAmount);
		if (capital.add(vipAmount).compareTo(BigDecimal.ZERO) > 0) {
			// 获取VIP利息
			bizEnsureMoneyService.updateAddProvisions(capital.add(vipAmount), repUserId, TradeTypeConstant.RISK_TRADE_TYPE_2005, brp.getBorrowId(), null);
		}
		// 将vip的状态改成已结清
		bizReceiptPlanService.updateStatusByVip(Constant.BIZ_RECEIPTPLAN_STATUS_5,Constant.BIZ_RECEIPTPLAN_STATUS_6, brp.getPid());
		
		// 4.获取非vip待收计划
		List<BizReceiptPlan> plans = bizReceiptPlanService.findListByRepaymentIdNotVip(brp.getPid(), Constant.BIZ_RECEIPTPLAN_STATUS_5);
		
		// 5.将非vip的利息给投资用户
		logger.debug("获取非vip用户："+plans.size()+"个");
		if (plans != null && plans.size() > 0) {
			for (int i = 0; i < plans.size(); i++) {
				this.receiptNotVipInterest(plans.get(i), borrow.getBorrowCode(), borrow.getBorrowName(), borrow.getBorDeadline());
			}
		}
		brp.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_4);
		brp.setLastUpdateTime(new Date());
		brp.setRepPlaDesc(des);
		brp.setRepaidTime(DateUtil.format(new Date()));
		brp.setRepaidamount(benXi);
		brpm.updateByPrimaryKeySelective(brp);
		logger.debug("更新还款计划状态为："+Constant.BIZ_REPLAN_STATUS_4);
		// 如果是最后一期还款，则需将标的状态改为已结清
		if(brp.getPlanindex().equals(borrow.getBorDeadline())){
			borrow.setBorStatus(Constant.BORROW_STATUS_8);
		}else{
			borrow.setBorStatus(Constant.BORROW_STATUS_5);
		}
		bizBorrowService.updateByPrimaryKeySelective(borrow);
				
	}

	/**
	 * 
	 * Description：<br>
	 * 不是vip，进行回款利息
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @param bizReceiptPlan
	 * @param borrowCode
	 * @param borrowName
	 * @param borDeadline
	 * @throws FrameworkException
	 */
	private void receiptNotVipInterest(BizReceiptPlan bizReceiptPlan, String borrowCode, String borrowName, String borDeadline) throws FrameworkException {
		
		String userId = bizReceiptPlan.getCustomerId();
		// 3.利息
		BigDecimal interest = bizReceiptPlan.getNetInterest() == null ? BigDecimal.ZERO : bizReceiptPlan.getNetInterest();
		if (interest.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrowCode + " " + borrowName + " ," + bizReceiptPlan.getPlanIndex() + "/" + borDeadline;
			customerAccountService.updateAddAvailableAmount(interest, userId, bizReceiptPlan.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_409, note);
			logger.debug("非vip收取利息："+interest);
		}

		// 5.管理费
		BigDecimal manageFee = bizReceiptPlan.getManagementCost() == null ? BigDecimal.ZERO : bizReceiptPlan.getManagementCost();
		if (manageFee.compareTo(BigDecimal.ZERO) > 0) {
			// 用户扣除
			String note = borrowCode + " " + borrowName;
			customerAccountService.updateDeductedAvailableAmount(manageFee, userId, bizReceiptPlan.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, note);
			// 系统增加
			customerAccountService.updateDeductedInterestManagementFee(manageFee, userId, bizReceiptPlan.getBorrowId(), null);
			logger.debug("扣除非vip利息管理费："+manageFee);
		}

		bizReceiptPlan.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_6);
		bizReceiptPlan.setLastUpdateTime(new Date());
		bizReceiptPlan.setRecPlaDesc("定时器逾期/垫付结清");
		bizReceiptPlanService.updateByPrimaryKeySelective(bizReceiptPlan);
		logger.debug("更新非vip收款计划状态为："+Constant.BIZ_RECEIPTPLAN_STATUS_6);

	}

	@Override
	public int updateOverdue(BizRepaymentPlan brp,boolean flag) throws FrameworkException {
		// 获取借款人的逾期天数，由现在的时间，减去应还时间
		logger.debug("更新了逾期罚息..........................");
		
		BizBorrow borrow = bizBorrowService.getBizBorrowById(brp.getBorrowId());
		BigDecimal bMoney = borrow.getBorrowMoney();
		
		if(brp.getPlanindex().equals(borrow.getBorDeadline())){
			// 最后一期
			int d;
			try {
				d = EscfDateUtil.daysBetween(DateUtil.format(brp.getExpireTime()), new Date());
			} catch (ParseException e) {
				d = 0;
			}
			if(d<=0){
				return 0;
			}
			// 获取逾期罚息率，针对借款人
			BigDecimal oFee = new BigDecimal(0.006);
			try {
				SysParams  sp1 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.OVER_FEE_BORROW);
				oFee = new BigDecimal(sp1.getParamValue());
			} catch (Exception e) {
				// 无效
			}
		
			BigDecimal lateFee = bMoney.multiply(oFee).multiply(new BigDecimal(d));
			
			brp.setLatefee(lateFee);
			brp.setLastUpdateTime(new Date());
			brp.setLatefeecalculateTime(new Date());
			if(Constant.BIZ_REPLAN_STATUS_1.equals(brp.getReceiptPalnStatus())){
				brp.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_2);
			}
			BizRepaymentPlanMapper mp = (BizRepaymentPlanMapper) getDao();
			mp.updateByPrimaryKeySelective(brp);
			
			if(flag){
				// 更新投资人的逾期赔付
				
				// 获取逾期赔付率，针对投资人 
				BigDecimal uFee = new BigDecimal(0.003);
				try {
					SysParams  sp2 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.OVER_FEE_INVEST);
					uFee = new BigDecimal(sp2.getParamValue());
				} catch (Exception e) {
					// 无效
				}
				List<BizReceiptPlan> list = bizReceiptPlanService.findListByRepaymentId(brp.getPid());
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						BizReceiptPlan br = list.get(i);
						if (br != null) {
							if (isReceipt(br.getReceiptStatus())) {
								// 待收和已逾期的。更新其逾期赔付
								// 1.求得用户对标的投资的总本金
		
								BigDecimal benJin = bizReceiptPlanService.sumBenJin(br.getBorrowId(), br.getCustomerId());
		
								// 更新收款计划
								br.setLateFee(uFee.multiply(benJin).multiply(new BigDecimal(d)));
								br.setLateFeeCalculateTime(new Date());
								br.setLastUpdateTime(new Date());
								if(Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(br.getReceiptStatus())){
									br.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_2);
								}
		
								bizReceiptPlanService.updateByPrimaryKeySelective(br);
		
							}
						}
					}
				}
			}
			
			if(Constant.BORROW_STATUS_5.equals(borrow.getBorStatus())){
				borrow.setBorStatus(Constant.BORROW_STATUS_6);
				bizBorrowService.updateByPrimaryKeySelective(borrow);
			}
			
		}else{
			// 不是最后一期
			int d;
			try {
				d = EscfDateUtil.daysBetween(DateUtil.format(brp.getExpireTime()), new Date());
			} catch (ParseException e) {
				d = 0;
			}
			if(d<=0){
				return 0;
			}
			// 获取逾期罚息率，针对借款人
			BigDecimal oFee = new BigDecimal(0.006);
			try {
				SysParams  sp1 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.OVER_FEE_BORROW);
				oFee = new BigDecimal(sp1.getParamValue());
			} catch (Exception e) {
				// 无效
			}
		
			BigDecimal lateFee = bMoney.multiply(oFee).multiply(new BigDecimal(d));
			
			int b = 0;
			
		
			try {
				Calendar c = Calendar.getInstance();
				c.setTime(DateUtil.format(brp.getExpireTime()));
				c.add(Calendar.MONTH, 1);
				b = EscfDateUtil.daysBetween(DateUtil.format(brp.getExpireTime()),c.getTime());
			} catch (ParseException e1) {
				// 无效
			}
			BigDecimal maxFee = bMoney.multiply(oFee).multiply(new BigDecimal(b));
			
			if(lateFee.compareTo(maxFee) > 0){
				return 0;
			}
			
			
			brp.setLatefee(lateFee);
			brp.setLastUpdateTime(new Date());
			brp.setLatefeecalculateTime(new Date());
			if(Constant.BIZ_REPLAN_STATUS_1.equals(brp.getReceiptPalnStatus())){
				brp.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_2);
			}
			BizRepaymentPlanMapper mp = (BizRepaymentPlanMapper) getDao();
			mp.updateByPrimaryKeySelective(brp);
			
			if(flag){
				// 更新投资人的逾期赔付
				
				// 获取逾期赔付率，针对投资人 
				BigDecimal uFee = new BigDecimal(0.003);
				try {
					SysParams  sp2 = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.OVER_FEE_INVEST);
					uFee = new BigDecimal(sp2.getParamValue());
				} catch (Exception e) {
					// 无效
				}
				List<BizReceiptPlan> list = bizReceiptPlanService.findListByRepaymentId(brp.getPid());
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						BizReceiptPlan br = list.get(i);
						if (br != null) {
							if (isReceipt(br.getReceiptStatus())) {
								// 待收和已逾期的。更新其逾期赔付
								// 1.求得用户对标的投资的总本金
		
								BigDecimal benJin = bizReceiptPlanService.sumBenJin(br.getBorrowId(), br.getCustomerId());
		
								// 更新收款计划
								br.setLateFee(uFee.multiply(benJin).multiply(new BigDecimal(d)));
								br.setLateFeeCalculateTime(new Date());
								br.setLastUpdateTime(new Date());
								if(Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(br.getReceiptStatus())){
									br.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_2);
								}
		
								bizReceiptPlanService.updateByPrimaryKeySelective(br);
		
							}
						}
					}
				}
			}
			
			if(Constant.BORROW_STATUS_5.equals(borrow.getBorStatus())){
				borrow.setBorStatus(Constant.BORROW_STATUS_6);
				bizBorrowService.updateByPrimaryKeySelective(borrow);
			}
			
		}
		
		
		return 0;
	}

	@Override
	public void executePayment(BizRepaymentPlan brp) throws FrameworkException {
	
		// 系统用备付金进行垫付
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();
		if (brp == null) {
			throw new NullArgumentException("BizRepaymentPlan is null");
		}
		// 获取借款
		BizBorrow borrow = bizBorrowService.getBizBorrowById(brp.getBorrowId());
		if (null == borrow) {
			throw new NullArgumentException("标的不存在");
		}
		// 1.获取待还总金额
		BigDecimal benXi = brp.getTotalAmount();

		BigDecimal eMoney = bizEnsureMoneyService.getMoney();

		eMoney = eMoney == null ? BigDecimal.ZERO : eMoney;
		if (eMoney.compareTo(benXi) < 0) {
			// 备用金不足，更新其罚息
			logger.info("垫付【失败】，原因：备付金不足，开始更新借款人【"+brp.getCustomerId()+"】的罚息");
			try {
				this.updateOverdue(brp,true);
				logger.info("垫付【失败】，原因：备付金不足，更新借款人【"+brp.getCustomerId()+"】的罚息【成功】");
			} catch (Exception e) {
				logger.info("垫付【失败】，原因：备付金不足，更新借款人【"+brp.getCustomerId()+"】的罚息【失败】，原因："+e.getMessage());
				throw new RuntimeException(e);
			}
			return;
		}

		// 1.本金
		BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO : brp.getCapital();
		// 2.VIP利息
		BigDecimal vipAmount = bizReceiptPlanService.sumVipAmount(Constant.BIZ_RECEIPTPLAN_STATUS_1, brp.getPid());
		vipAmount = vipAmount == null ? BigDecimal.ZERO : vipAmount;
		BigDecimal vipAmount2 = bizReceiptPlanService.sumVipAmount(Constant.BIZ_RECEIPTPLAN_STATUS_2, brp.getPid());
		vipAmount2 = vipAmount2 == null ? BigDecimal.ZERO : vipAmount2;
		vipAmount = vipAmount.add(vipAmount2);

		// 扣除风险备用金,垫付本息的
		if (capital.add(vipAmount).compareTo(BigDecimal.ZERO) > 0) {
			bizEnsureMoneyService.updateSubProvisions(capital.add(vipAmount), brp.getCustomerId(), TradeTypeConstant.RISK_TRADE_TYPE_2002, brp.getBorrowId(), null);
		}
		brp.setLastUpdateTime(new Date());
		brp.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_3);
		brp.setPayamount(capital.add(vipAmount));
		brp.setPayamountTime(new Date());
		
		// 4.获取待收计划,进行垫付
		List<BizReceiptPlan> plans = bizReceiptPlanService.findListByRepaymentId(brp.getPid());

		// 5.进行回款
		if (plans != null && plans.size() > 0) {
			for (int i = 0; i < plans.size(); i++) {
				// 回款，给VIP用户全部还，非vip用户只还本金
				BizReceiptPlan p = plans.get(i);
				if (p != null && "1".equals(p.getIsVip())) {
					this.receiptVip(p, borrow.getBorrowCode(), borrow.getBorrowName(), borrow.getBorDeadline());
				} else {
					this.receiptCapital(p, borrow.getBorrowCode(), borrow.getBorrowName(), borrow.getBorDeadline());
				}
			}
		}

		brpm.updateByPrimaryKey(brp);
		// 更新借款状态
		borrow.setBorStatus(Constant.BORROW_STATUS_7);
		borrow.setLastUpdateTime(DateUtil.format(new Date()));
		bizBorrowService.updateByPrimaryKeySelective(borrow);
		// 更新逾期罚息
		this.updateOverdue(brp,false);
		

		
	}

	private void receiptVip(BizReceiptPlan brp, String borrowCode, String borrowName, String borDeadLine) throws FrameworkException {
		String repaidStatus = brp.getReceiptStatus();
		if(!(Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(repaidStatus) ||  
				Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(repaidStatus) ||
				Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(repaidStatus))){
			return;
		}
		// 回款
		String userId = brp.getCustomerId();
		// 2.本金
		BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO : brp.getCapital();
		if (capital.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
			customerAccountService.updateAddAvailableAmount(capital, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_407, note);
		}

		// 3.利息
		BigDecimal interest = brp.getNetInterest() == null ? BigDecimal.ZERO : brp.getNetInterest();
		if (interest.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
			customerAccountService.updateAddAvailableAmount(interest, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_409, note);
		}

		// 4.罚息
		BigDecimal lateFee = brp.getLateFee() == null ? BigDecimal.ZERO : brp.getLateFee();
		if (lateFee.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadLine;
			String des = "罚息：" + lateFee.setScale(2, BigDecimal.ROUND_DOWN);
			customerAccountService.updateAddAvailableAmount(lateFee, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_411, note + des);
		
			// 扣除系统资金，垫付罚息的
			sysAccountService.updateSubSystemAmount(lateFee, brp.getCustomerId(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1026, brp.getBorrowId(), null);
		}

		// 5.管理费
		BigDecimal manageFee = brp.getManagementCost() == null ? BigDecimal.ZERO : brp.getManagementCost();
		if (manageFee.compareTo(BigDecimal.ZERO) > 0) {
			// 用户扣除
			String note = borrowCode + " " + borrowName;
			customerAccountService.updateDeductedAvailableAmount(manageFee, userId, brp.getBorrowId(), TradeTypeConstant.OTHER_TYPE_502, note);
			// 系统增加
			customerAccountService.updateDeductedInterestManagementFee(manageFee, userId, brp.getBorrowId(), null);

		}

		brp.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_5);
		brp.setLastUpdateTime(new Date());
		brp.setRecPlaDesc("系统垫付");
		brp.setPayAmount(capital.add(interest).add(lateFee));
		brp.setPayAmountTime(new Date());

		bizReceiptPlanService.updateByPrimaryKeySelective(brp);
	}

	/**
	 * 
	 * Description：<br>
	 * 只还本金
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @param brp
	 * @param borrowCode
	 * @param borrowName
	 * @param borDeadline
	 * @throws FrameworkException
	 */
	private void receiptCapital(BizReceiptPlan brp, String borrowCode, String borrowName, String borDeadline) throws FrameworkException {
		String repaidStatus = brp.getReceiptStatus();
		if(!(Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(repaidStatus) ||  
				Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(repaidStatus) ||
				Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(repaidStatus))){
			return;
		}
		// 回款
		String userId = brp.getCustomerId();
		
		// 4.罚息
		BigDecimal lateFee = brp.getLateFee() == null ? BigDecimal.ZERO : brp.getLateFee();
		if (lateFee.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadline;
			String des = "罚息：" + lateFee.setScale(2, BigDecimal.ROUND_DOWN);
			customerAccountService.updateAddAvailableAmount(lateFee, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_411, note + des);
		
			// 扣除系统资金，垫付罚息的
			sysAccountService.updateSubSystemAmount(lateFee, brp.getCustomerId(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1026, brp.getBorrowId(), null);
		}
		
		// 2.本金
		BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO : brp.getCapital();
		if (capital.compareTo(BigDecimal.ZERO) > 0) {
			String note = borrowCode + " " + borrowName + " ," + brp.getPlanIndex() + "/" + borDeadline;
			customerAccountService.updateAddAvailableAmount(capital, userId, brp.getBorrowId(), TradeTypeConstant.RETURNS_TYPE_407, note);
		}
		brp.setPayAmount(capital.add(lateFee));
		brp.setPayAmountTime(new Date());
		brp.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_5);
		brp.setLastUpdateTime(new Date());
		brp.setRecPlaDesc("定时器还款结清");
		bizReceiptPlanService.updateByPrimaryKeySelective(brp);

	}

	@Override
	public void createForecastRepaymentPlan(BizBorrow borrow) {
		if (null == borrow) {
			throw new RuntimeException("标的不存在");
		}
		BizRepaymentPlanMapper brpm = (BizRepaymentPlanMapper) getDao();

		// 删除原有计划，产生新的计划
		brpm.deleteBatch(borrow.getPid(), borrow.getCustomerId());

		// 产生还款记录
		CalculationDto dto = new CalculationDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if (borrow.getBorFullTime() != null) {
			dto.setInterestDate(sdf.format(borrow.getBorFullTime()));
		} else {
			dto.setInterestDate(sdf.format(new Date()));
		}
		// 获取管理费率
		dto.setManagementRate(borrow.getManageExpenseRate().multiply(new BigDecimal(100)));
		dto.setRepaymentAmt(borrow.getBorrowMoney());//借款金额
		dto.setRepaymentRate(borrow.getBorrowRate().multiply(new BigDecimal(100)));//借款利率
		Integer borDeadline = Integer.parseInt(borrow.getBorDeadline());
		dto.setRepaymentDeadline(borDeadline);//借款期限
		dto.setRepaymentType(Integer.parseInt(borrow.getRepaymentType()));//还款方式
		dto.setBorrowId(borrow.getPid());//借款Id
		dto.setCustomerId(borrow.getCustomerId());//用户Id
		dto.setInterestType(borrow.getAccrualType());//计息方式
		ICalculation cal = CalculationFactory.getCalculation(dto);

		List<BizRepaymentPlan> list = new ArrayList<BizRepaymentPlan>();
		try {
			if (Constant.BORROW_TYPE_4.equals(borrow.getBorrowType()) || Constant.BORROW_TYPE_5.equals(borrow.getBorrowType())) {
				list = cal.execRepaymentCalcByDate();
			} else {
				list = cal.execRepaymentCalc();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("创建虚拟的还款数据失败：", e.getMessage());
			}
		}
		for (BizRepaymentPlan plan : list) {
			plan.setPid(plan.getPK());
		}
//		List<BizRepaymentPlan> rpList = new ArrayList<BizRepaymentPlan>();
//		for (BizRepaymentPlan plan : list) {
//			plan.setPid(plan.getPK());
//			plan.setBorDeadline(borDeadline);
//			plan.setBorrowCode(borrow.getBorrowCode());
//			plan.setBorrowId(borrow.getPid());
//			plan.setBorrowName(borrow.getBorrowName());
//			plan.setCustomerId(borrow.getCustomerId());
//			plan.setCreateUser(borrow.getCreateUser());
//			plan.setInterestType(borrow.getAccrualType());
//
//			plan.setStatus("1");
//			plan.setReceiptPalnStatus(receiptPlanStatus);
//			plan.setCreateTime(new Date());
//
//			rpList.add(plan);
//		}

		brpm.saveBatch(list);
	}

	@Override
	public BizRepaymentPlanInfoVo getRepaymentInfo(String repayId) {
		return bizRepaymentPlanInfoVoMapper.getRepaymentInfoVo(repayId);
	}

	/**
	 * 
	 * @Description : API--待还款列表
	 * @param userId
	 *            用户ID
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 客户的待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午8:43:36
	 */
	@Override
	public List<BizRepaymentPlan> selectPendingRepaymentAPI(String userId, Integer pageIndex, Integer pageSize) {
		BizRepaymentPlanMapper mapper = (BizRepaymentPlanMapper) getDao();
		List<BizRepaymentPlan> list = new ArrayList<BizRepaymentPlan>();
		try {
			// 创建查询条件Map
			Map<String, Object> map = new HashMap<String, Object>();
			// 赋值查询条件
			map.put("customerId", userId);
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = mapper.selectPendingRepaymentAPI(map);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("API--待还款列表：", e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<BizRepaymentPlan> findListByBorrowId(String borrowId) {
		BizRepaymentPlanMapper mapper = (BizRepaymentPlanMapper) getDao();
		return mapper.findListByBorrowId(borrowId);
	}
}
