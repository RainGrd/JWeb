/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借款投标记录接口实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.mybatis.paginator.domain.Paginator;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.business.BizBorrowDetailMapper;
import com.yscf.core.dao.business.BizBorrowInfoVOMapper;
import com.yscf.core.dao.business.BizBorrowMapper;
import com.yscf.core.dao.financial.CustomerAccountMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustInterestTicketMapper;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.BizDetailModel;
import com.yscf.core.model.system.PubCondition;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustInterestTicket;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.IPubConditionService;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustPoinWaterServiceImpl;

/**
 * Description：<br>
 * 借款投标记录接口实现
 * 
 * @author Jie.Zou
 * @date 2015年10月10日
 * @version v1.0.0
 */
@Service("bizBorrowDeatailService")
public class BizBorrowDetailServiceImpl extends BaseService<BaseEntity, String>
		implements IBizBorrowDetailService {

	@Autowired
	public BizBorrowDetailServiceImpl(BizBorrowDetailMapper dao) {
		super(dao);
	}

	/**
	 * 投资记录
	 */
	@Resource(name = "bizBorrowDetailMapper")
	private BizBorrowDetailMapper detailMapper;
	/**
	 * 借款voi
	 */
	@Resource(name = "bizBorrowInfoVOMapper")
	private BizBorrowInfoVOMapper borrowVoMapper;
	/**
	 * 借款接口
	 */
	@Resource(name = "bizBorrowMapper")
	private BizBorrowMapper borrowMapper;
	/**
	 * 客户接口
	 */
	@Resource(name = "cusTomerMapper")
	private CusTomerMapper cusTomerMapper;
	/**
	 * 客户资金 DAO
	 */
	@Resource(name = "customerAccountMapper")
	private CustomerAccountMapper custAccMapper;
	/**
	 * 客户账户管理服务
	 */
	@Resource(name = "customerAccountService")
	private ICustomerAccountService accountService;

	/**
	 * 积分接口
	 */
	@Resource(name = "custPoinWaterServiceImpl")
	private CustPoinWaterServiceImpl custPoinWaterServiceImpl;

	// vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	private SysVipinfoServiceImpl sysVipinfoServiceImpl;
	// 系统参数
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl paramsServiceImpl;
	@Resource(name="cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	@Resource
	private IPubConditionService pubConditionService;

	@Resource
	private CustInterestTicketMapper custInterestTicketMapper;

	@Override
	public PageList<BizBorrowDetail> selectAllPage(
			BizBorrowDetail borrowDetail, PageInfo info) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.selectAllPage(borrowDetail, info);
	}

	@Override
	public PageList<BizBorrowDetail> selectAllRelSelectivePage(
			BizBorrowDetail borrowDetail, PageInfo info)
			throws FrameworkException {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		borrowDetail = borrowDetail == null ? new BizBorrowDetail()
				: borrowDetail;
		borrowDetail.setPage((info.getPage() - 1) * info.getLimit());
		borrowDetail.setLimit(info.getLimit());
		List<BizBorrowDetail> list = mapper
				.selectAllRelSelectivePage(borrowDetail);
		int total = mapper.selectAllRelSelectiveTotalCount(borrowDetail);
		Paginator paginator = new Paginator(info.getPage(), info.getLimit(),
				total);
		PageList<BizBorrowDetail> bizBorrowDetails = new PageList<BizBorrowDetail>(
				list, paginator);
		return bizBorrowDetails;
	}

	@Override
	public PageList<BizBorrowDetail> getListByBorrowId(
			BizBorrowDetail bizBorrowDetail, PageInfo info) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.selectListByBorrowId(bizBorrowDetail, info);
	}

	@Override
	public PageList<BizBorrowDetail> selectBidRecordDetailsById(
			BizBorrowDetail bizBorrowDetail, PageInfo info) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		PageList<BizBorrowDetail> bidRecord = mapper
				.selectBidRecordDetailsById(bizBorrowDetail, info);
		Map<String, BigDecimal> map = mapper
				.selectBidRecordDetailsByIdSum(bizBorrowDetail);
		BizBorrowDetail bizBorrowDetail2 = new BizBorrowDetail();
		bizBorrowDetail2.setInvestmentAmount(map.get("investmentAmount"));
		bizBorrowDetail2.setAwardAmount(map.get("awardAmount"));
		bizBorrowDetail2.setInterest(map.get("interest"));
		bidRecord.add(bizBorrowDetail2);
		return bidRecord;

	}

	@Override
	public BizBorrowDetail findBizBorrowDetail(String userId, String borrowId) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.findBizBorrowDetail(userId, borrowId);
	}

	/**
	 * 
	 * Description：<br>
	 * e计划 e首房/e抵押投标 校验内容：客户账户余额 、标的剩余金额是否小于投标金额
	 * ，更新标的的剩余金额、已投金额、投资进度、是否满标，同时新增一条投资记录 更新积分 更新冻结金额 如果使用加息券则更改加息券状态
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月21日
	 * @version v1.0.0
	 * @param borrowId
	 *            标的id
	 * @param borrowMoney
	 *            投资金额
	 * @param custId
	 *            客户id
	 * @param investAwardId
	 *            加息券id
	 * @param investmentType
	 *            投标方式 1是手动投标 2 是自动投标
	 * @return result 是否成功 msg 返回信息
	 * @throws FrameworkException
	 */
	public HashMap<String, Object> investmentAuto(
			BizBorrowDetail bizBorrowDetail, String custId,
			String investmentType) throws FrameworkException {
		HashMap<String, Object> retMap = new HashMap<String, Object>();
		BizBorrow borrow = new BizBorrow();
		// 空值处理
		if (StringUtil.isBlank(bizBorrowDetail.getBorrowId())
				|| borrowMapper == null || StringUtil.isBlank(custId)) {
			retMap.put("result", false);
			retMap.put(
					"msg",
					"传递参数出错,borrowId=" + bizBorrowDetail.getBorrowId()
							+ ",borrowMoney="
							+ bizBorrowDetail.getInvestmentAmount()
							+ ",custId=" + custId);
			return retMap;
		}
		BigDecimal borrowMoney = bizBorrowDetail.getInvestmentAmount();
		CusTomer custTomer = cusTomerMapper.selectByPrimaryKey(custId);// 查询客户信息
		CustomerAccount custAcc = custAccMapper.getByCusterId(custId);// 客户账户信息
		borrow = borrowMapper.selectByPrimaryKey(bizBorrowDetail.getBorrowId());// 查询标的信息
		if (custTomer == null) {
			retMap.put("result", false);
			retMap.put("msg", "客户信息未找到！");
			return retMap;
		}
		if (custAcc == null) {
			retMap.put("result", false);
			retMap.put("msg", "客户账号未找到！");
			return retMap;
		}
		if (borrow == null) {
			retMap.put("result", false);
			retMap.put("msg", "标的详情为找到！");
			return retMap;
		}
		if (!Constant.BORROW_STATUS_2.equals(borrow.getBorStatus())) {
			retMap.put("result", false);
			retMap.put("msg", "标的状态异常,请稍后再试！");
			return retMap;
		}
		if (custAcc.getAvailableAmount().compareTo(borrowMoney) < 0) {
			retMap.put("result", false);
			retMap.put("msg", "您的账户余额不足,请充值！");
			return retMap;
		}
		if (!"允许".equals(custTomer.getBid())) {
			retMap.put("result", false);
			retMap.put("msg", "您的账户已被禁止投资操作，如有疑问，请致电客服。");
			return retMap;
		}
		// 该用户 对该标的投资总额
		BigDecimal totalByBorrow = getInvestTotalByBorrowId(
				bizBorrowDetail.getBorrowId(), custId);
		if (borrow.getEndMoney() != null
				&& borrow.getEndMoney().compareTo(BigDecimal.ZERO) > 0
				&& totalByBorrow != null) {
			if (totalByBorrow.add(bizBorrowDetail.getInvestmentAmount())
					.compareTo(borrow.getEndMoney()) > 0) {
				retMap.put("result", false);
				retMap.put("msg", "累计投资不能超过设置的投资上限，请选择其他标的！");
				return retMap;
			}
		}
		if (borrowMoney.compareTo(borrow.getSurplusMoney() == null ? borrow
				.getBorrowMoney() : borrow.getSurplusMoney()) > 0) {
			BigDecimal temp = borrow.getSurplusMoney() == null ? borrow
					.getBorrowMoney() : borrow.getSurplusMoney();
			retMap.put("result", false);
			retMap.put("msg", "投标金额大于标的剩余金额,标的剩余金额为=" + temp);
			return retMap;
		}
		//有逾期的不能投资
		if(cusTomerService.isOverdue(custId)){
			retMap.put("result", false);
			retMap.put("msg", "逾期客户不能投资！");
			return retMap;
		}
		bizBorrowDetail.setVipinfoId(custTomer.getVipId());
		bizBorrowDetail.setCustomerId(custTomer.getPid());
		bizBorrowDetail.setCustomerName(custTomer.getSname());
		// 未投金额 =借款金额-(已投金额+本次投资金额）
		// 如果第一次投标（没有已投金额） 未投金额 =借款金额-(本次投资金额）
		BigDecimal surplusMoney = borrow.getBorrowMoney().subtract(
				borrow.getAlreadyMoney() == null ? borrowMoney : borrow
						.getAlreadyMoney().add(borrowMoney));
		// 已投金额 = 历史已投金额加上本次投资金额
		BigDecimal alreadyMoney = borrow.getAlreadyMoney() == null ? borrowMoney
				: borrow.getAlreadyMoney().add(borrowMoney);
		// 如果 已投金额（+本次投资金额）等于借款金额 标的状态为已满标
		if (borrow.getBorrowMoney().compareTo(alreadyMoney) == 0) {
			borrow.setBorStatus("4");
			borrow.setBorFullTime(DateUtil.getToday());
		}
		borrow.setSurplusMoney(surplusMoney);// 未投金额
		borrow.setAlreadyMoney(alreadyMoney);// 已投金额
		// 完成进度（保留两位小数 并且四舍五入）
		// 已投金额 = 如果没有投资 则为借款金额
		borrow.setBorrowProgress(alreadyMoney.divide(borrow.getBorrowMoney(),
				2, BigDecimal.ROUND_HALF_DOWN));
		Integer tenderCount = new Integer(0);
		if (borrow.getTenderCount() != null) {
			tenderCount = new Integer(borrow.getTenderCount()) + 1;
		}
		borrow.setTenderCount(tenderCount.toString());
		// 更新 已投金额 和 未投金额
		borrowMapper.updateByPrimaryKeySelective(borrow);
		List<SysVipinfo> vipList = sysVipinfoServiceImpl
				.selectVipLevelById(custId);
		if (vipList != null && vipList.size() > 0) {
			SysVipinfo vipInfo = vipList.get(0);
			bizBorrowDetail.setManagerRate(vipInfo.getDiscount());// 利息管理费
		} else {
			SysParams params = paramsServiceImpl
					.getParamsByParamKey("management_rate");
			BigDecimal managerRate = new BigDecimal(params.getParamValue())
					.divide(new BigDecimal(100));
			bizBorrowDetail.setManagerRate(managerRate);// 利息管理费
		}
		getInvestReward(borrow, bizBorrowDetail);// 获得预计收益和投资奖励
		bizBorrowDetail.setBorrowId(borrow.getPid());
		bizBorrowDetail.setInvestmentType(investmentType);// 投标方式 1是手动投标 2 是手动投标
		bizBorrowDetail.setCreateTime(DateUtil.getToday().toString());// 创建时间
		bizBorrowDetail.setInvestmentTime(DateUtil.getToday().toString());// 投资时间
		if ("3".equals(borrow.getBorrowType())) {
			bizBorrowDetail.setInterestTime(DateUtil.getToday().toString());// e计划投标即计息
		}
		bizBorrowDetail.setStatus("1");
		bizBorrowDetail.setInvestmentAmount(borrowMoney);// 投资金额
		if (borrow.getInvestRewardScale() != null && borrowMoney != null) {
			bizBorrowDetail.setAwardAmount(borrowMoney.multiply(borrow
					.getInvestRewardScale()));// 投资奖励值
		}
		bizBorrowDetail.setPid(bizBorrowDetail.getPK());
		// 保存投资信息
		detailMapper.insertSelective(bizBorrowDetail);
		// 可用余额转冻结金额
		accountService.updateAvailableAmountToFreezeAmount(borrowMoney,
				custTomer.getPid(), borrow.getPid());
		// 更新交易时间
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createTime", DateUtil.format(new Date()));
		map.put("userId", custId);
		cusTomerMapper.updateLastTransacTime(map);

		// 判断用户是否使用加息券，使用加息券的，更新加息券为已使用 update by zhangyu
		if (StringUtil.isNotEmpty(bizBorrowDetail.getInvestAwardId())) {
			custInterestTicketMapper.updateInterestStatus("1",
					bizBorrowDetail.getInvestAwardId());
		}

		retMap.put("result", true);
		retMap.put("result", true);
		retMap.put("msg", "投资成功");
		return retMap;
	}

	/**
	 * Description：<br>
	 * 获得预计收益和投资奖励
	 * 
	 * @author shiliang.feng
	 * @date 2016年2月2日
	 * @version v1.0.0
	 * @param borrow
	 * @param bizBorrowDetail
	 */
	private void getInvestReward(BizBorrow borrow,
			BizBorrowDetail bizBorrowDetail) {
		// 计算投资奖励 和 预期收益
		CalculationDto calculationDto = new CalculationDto();
		calculationDto.setRepaymentAmt(bizBorrowDetail.getInvestmentAmount());// 投资金额
		calculationDto
				.setRepaymentDeadline(new Integer(borrow.getBorDeadline()));// 借款期限
		calculationDto.setRepaymentRate(new BigDecimal(100).multiply(borrow
				.getBorrowRate()));// 借款利率
		calculationDto.setRepaymentType(new Integer(borrow.getRepaymentType()));// 还款方式
		if (borrow.getInvestRewardScale() != null) {
			calculationDto.setRewardRate(new BigDecimal(100).multiply(borrow
					.getInvestRewardScale()));// 投资奖励
		}
		calculationDto.setInterestDate(DateUtil.format(new Date()));// 计息时间
		calculationDto.setManagementRate(BigDecimal.ZERO);// 管理费率
		ICalculation cal = CalculationFactory.getCalculation(calculationDto);
		// 如果使用了加息券 则需要加上加息收益
		BigDecimal investmentPayoffs = BigDecimal.ZERO;
		if (StringUtil.isNotEmpty(bizBorrowDetail.getInvestAwardId())) {
			// 根据 加息券id 查询加息值
			CustInterestTicket ticket = custInterestTicketMapper
					.selectByPrimaryKey(bizBorrowDetail.getInvestAwardId());
			// 加息收益等于 加息券值*投资金额
			investmentPayoffs = bizBorrowDetail.getInvestmentAmount().multiply(
					new BigDecimal(ticket.getScale()));
		}
		bizBorrowDetail.setInvestmentPayoffs(cal.getSumInterest().add(
				investmentPayoffs));// 预计收益
	}

	@Override
	public List<BizBorrowDetail> selectBizBorrowDetailByBorrowId(
			String borrowId, Integer pageIndex, Integer pageSize) {
		return detailMapper.selectBizBorrowDetailByBorrowId(borrowId,
				pageIndex, pageSize);
	}

	@Override
	public BigDecimal sumInvestAmount(String borrowId, String userId) {

		return detailMapper.sunInvestAmount(borrowId, userId);
	}

	@Override
	public void insert(BizBorrowDetail bbd) {
		detailMapper.insertSelective(bbd);
	}

	@Override
	public List<BizBorrowDetail> selectInverstList() {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.selectInverstList();
	}

	@Override
	public Integer selectBizBorrowDetailByBorrowIdCount(String pid) {

		return detailMapper.selectBizBorrowDetailByBorrowIdCount(pid);
	}

	@Override
	public List<BizBorrowDetail> selectBorrowDetailsByBorrowId(String borrowId) {
		return detailMapper.selectBorrowDetailsByBorrowId(borrowId);
	}

	@Override
	public void updateByPrimaryKeySelective(BizBorrowDetail bizBorrowDetail) {
		detailMapper.updateByPrimaryKeySelective(bizBorrowDetail);
	}

	@Override
	public Map<String, BigDecimal> getBidRewards(BizBorrow bizBorrow,
			List<BizBorrowDetail> bids) {
		Map<String, BigDecimal> bidRewards = new HashMap<String, BigDecimal>();
		if (bizBorrow.getInvestRewardScale().compareTo(BigDecimal.ZERO) >= 0) {
			BigDecimal rewardRate = bizBorrow.getInvestRewardScale();
			for (BizBorrowDetail bid : bids) {
				BigDecimal reward = this.getBidReward(rewardRate,
						bid.getInvestmentAmount());
				bidRewards.put(bid.getPid(), reward);
			}
		}
		return bidRewards;
	}

	/**
	 * 
	 * Description：<br>
	 * 计算投标奖励
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param rewardRate
	 *            奖励比例
	 * @param bidAmount
	 *            投标金额
	 * @return
	 */
	private BigDecimal getBidReward(BigDecimal rewardRate, BigDecimal bidAmount) {
		BigDecimal reward = bidAmount.multiply(rewardRate).setScale(2,
				RoundingMode.HALF_UP);
		if (reward.compareTo(BigDecimal.ZERO) > 0) {
			return reward;
		}
		return BigDecimal.ZERO;
	}

	@Override
	public List<BizBorrowDetail> selectMyRankingList(String userId) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.selectMyRankingList(userId);
	}

	@Override
	public List<BizBorrowDetail> selectMyRankingNumber(String userId) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.selectMyRankingNumber(userId);
	}

	@Override
	public BizDetailModel getBizDetailModelByUser(String borrowId, String userId) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.getBizDetailModelByUser(borrowId, userId);
	}

	@Override
	public Integer getBizDetailCountByUser(String userId) {
		BizBorrowDetailMapper mapper = (BizBorrowDetailMapper) getDao();
		return mapper.getBizDetailCountByUser(userId);
	}

	@Override
	public BigDecimal getInvestTotalByBorrowId(String borrowId, String userId) {
		return detailMapper.getInvestTotalByBorrowId(borrowId, userId);
	}

	@Override
	public int vailidateBorrowPassword(String borrowPassword, String userId) {
		return 0;
	}

	@Override
	public boolean isPassCondition(String borrowId, String userId) {
		boolean flag = false;
		try {
			List<PubCondition> list = pubConditionService
					.findListByBorrowId(borrowId);
			if (list != null && list.size() > 0) {
				String sql = "";
				for (int i = 0; i < list.size(); i++) {
					sql = list.get(i).getCondValue();
					if (detailMapper.isPassCondition(sql, userId) < 1) {
						// 通过
						flag = true;
					} else {
						// 不通过
						flag = false;
						break;
					}
				}

			} else {
				// 该项目没有关联条件，通过
				flag = true;
			}
		} catch (Exception e) {
			// 出现异常，不通过
			flag = false;
		}

		return flag;
	}
}
