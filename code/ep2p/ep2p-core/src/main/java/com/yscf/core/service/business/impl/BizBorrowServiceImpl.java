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
 * 2015年9月18日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.activity.ActExpActDetailMapper;
import com.yscf.core.dao.business.BizBorrowApproveMapper;
import com.yscf.core.dao.business.BizBorrowFileRelMapper;
import com.yscf.core.dao.business.BizBorrowMapper;
import com.yscf.core.dao.business.BizReceiptPlanMapper;
import com.yscf.core.dao.business.BizRepaymentPlanMapper;
import com.yscf.core.dao.pub.PubFileMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.dao.user.CustInterestTicketMapper;
import com.yscf.core.exception.AvailableAmountExcepiton;
import com.yscf.core.exception.BorrowStatusChangeException;
import com.yscf.core.exception.ConditionExcepiton;
import com.yscf.core.exception.OutInvestScopeException;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowApprove;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizBorrowTimeVO;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.model.ptp.financial.BizBorrowStatModel;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.calculation.impl.AbstractCalculation;
import com.yscf.core.service.financial.IBizAccountCommonService;
import com.yscf.core.service.financial.IBizAccountExperienceService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysAccountService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.ISysVipinfoService;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.service.user.ICustExperienceService;
import com.yscf.core.service.user.ICustPoinWaterService;
import com.yscf.core.util.MiscUtil;

/**
 * Description：<br>
 * 借款业务实现
 * 
 * @author Simon.Hoo
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Service("bizBorrowService")
public class BizBorrowServiceImpl extends BaseService<BaseEntity, String> implements IBizBorrowService {

	private Logger logger = LoggerFactory.getLogger(BizBorrowServiceImpl.class);

	@Resource
	private BizBorrowFileRelMapper bizBorrowFileRelMapper;

	@Resource
	private BizBorrowApproveMapper bizBorrowApproveMapper;

	@Resource
	private PubFileMapper pubFileMapper;

	@Resource(name = "bizRepaymentPlanService")
	private IBizRepaymentPlanService bizRepaymentPlanService;

	@Resource
	private CusTomerMapper cusTomerMapper;

	@Resource(name = "customerAccountService")
	private ICustomerAccountService customerAccountService;

	@Resource
	private IBizBorrowDetailService bizBorrowDetailService;

	@Resource
	private BizBorrowMapper bizBorrowMapper;

	@Resource
	private ICusTomerService cusTomerService;

	@Resource
	private IBizAccountCommonService bizAccountCommonService;

	@Resource
	private IBizAccountExperienceService bizAccountExperienceService;

	@Resource
	private ISysAccountService sysAccountService;

	@Resource
	private BizReceiptPlanMapper bizReceiptPlanMapper;

	@Resource
	private BizRepaymentPlanMapper bizRepaymentPlanMapper;

	@Resource
	private SysCreateCodeRuleServiceImpl sysCreateCodeRuleService;

	@Resource
	private ActExpActDetailMapper actExpActDetailMapper;
	
	@Resource
	private ISysVipinfoService sysVipinfoService;
	
	@Resource
	private ICustPoinWaterService custPoinWaterService;
	
	@Resource
	private ICustExperienceService custExperienceService; 
	
	@Resource
	private ISysParamsService sysParamService;
	
	@Resource
	private CustInterestTicketMapper custInterestTicketMapper;

	@Autowired
	public BizBorrowServiceImpl(BizBorrowMapper dao) {
		super(dao);
	}

	@Override
	public PageList<BizBorrow> selectAllPage(BizBorrow bizBorrow, PageInfo info) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.selectAllPage(bizBorrow, info);
	}

	@Override
	public List<BizBorrow> selectAll(BizBorrow bizBorrow) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.selectAll(bizBorrow);
	}

	@Override
	public int updateStatusBatch(BizBorrow bizBorrow) {
		int result = 0;
		if (null != bizBorrow && null != bizBorrow.getPid()) {
			BizBorrowMapper mapper = (BizBorrowMapper) getDao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", bizBorrow.getStatus());
			map.put("idItem", bizBorrow.getPid().split(","));
			result = mapper.updateStatusBatch(map);
		}
		return result;
	}

	@Override
	public int insert(BizBorrowInfoVO bizBorrowInfoVo) throws Exception {
		int result = 0;
		// 保存借款信息
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		BizBorrow bizBorrow = new BizBorrow();
		bizBorrow.setPid(bizBorrow.getPK());
		bizBorrow.setBorrowType(bizBorrowInfoVo.getBorrowType());
		bizBorrow.setCustomerId(bizBorrowInfoVo.getCustomerId());
		bizBorrow.setCreateTime(bizBorrowInfoVo.getCreateTime());
		bizBorrow.setCreateUser(bizBorrowInfoVo.getCreateUser());
		bizBorrow.setSurplusMoney(bizBorrowInfoVo.getBorrowMoney());
		bizBorrow.setBorrowProgress(new BigDecimal(0));
		bizBorrow.setTenderCount("0");

		if (Constant.BORROW_TYPE_1.equals(bizBorrowInfoVo.getBorrowType())) {
			bizBorrow.setBorrowCode(sysCreateCodeRuleService.generatLabelNoRule("D", "7", bizBorrowInfoVo.getCreateUser()));
		} else if (Constant.BORROW_TYPE_2.equals(bizBorrowInfoVo.getBorrowType())) {
			bizBorrow.setBorrowCode(sysCreateCodeRuleService.generatLabelNoRule("S", "7", bizBorrowInfoVo.getCreateUser()));
		}
		bizBorrow.setStatus(Constant.ACTIVATE);
		result = mapper.insert(bizBorrow);

		// 往审批表中添加一条 审批节点为 资料填写 数据
		BizBorrowApprove approve = new BizBorrowApprove();
		approve.setPid(approve.getPK());
		approve.setBorId(bizBorrow.getPid());
		// approve.setApproveNode(Constant.BORROW_APPROVE_NODE_1);
		approve.setApproveStatus(Constant.BORROW_APPROVE_STATUS_1);
		approve.setCreateTime(bizBorrowInfoVo.getCreateTime());
		approve.setCreateUser(bizBorrowInfoVo.getCreateUser());
		approve.setStatus(Constant.ACTIVATE);
		bizBorrowApproveMapper.insert(approve);

		bizBorrowInfoVo.setApproveId(approve.getPid());
		bizBorrowInfoVo.setPid(bizBorrow.getPid());
		return result;
	}

	public int updateByPrimaryKeySelective(BizBorrow bizBorrow) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();

		return mapper.updateByPrimaryKeySelective(bizBorrow);
	}

	@Override
	public void saveFileInfo(BizBorrowFileRel fileRel, PubFile file) {
		// 保存文件
		file.setPid(file.getPK());
		pubFileMapper.insert(file);

		// 保存中间表
		fileRel.setFileId(file.getPid());
		fileRel.setPid(fileRel.getPK());
		bizBorrowFileRelMapper.insert(fileRel);
	}

	@Override
	public BizBorrow getBizBorrowById(String pid) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.selectByPrimaryKey(pid);
	}

	@Override
	public int deleteByPrimaryKey(String pid) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.deleteByPrimaryKey(pid);
	}

	@Override
	public Integer getBorrowCountByNewStandard(String startTime, String endTime) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		String start = null;
		if (null != startTime && !"".equals(startTime)) {
			start = EscfDateUtil.formatterDate(startTime, 1);
		}
		String end = null;
		if (null != endTime && !"".equals(endTime)) {
			end = EscfDateUtil.formatterDate(endTime, 2);
		}
		String borrowType = Constant.BORROW_TYPE_4;
		return mapper.getBorrowCountByStandard(start, end, borrowType, Constant.BORROW_STATUS_2);
	}

	@Override
	public Integer getBorrowCountByExperienceStandard(String startTime, String endTime) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		String start = null;
		if (null != startTime && !"".equals(startTime)) {
			start = EscfDateUtil.formatterDate(startTime, 1);
		}
		String end = null;
		if (null != endTime && !"".equals(endTime)) {
			end = EscfDateUtil.formatterDate(endTime, 2);
		}
		String borrowType = Constant.BORROW_TYPE_5;
		return mapper.getBorrowCountByStandard(start, end, borrowType, Constant.BORROW_STATUS_2);
	}

	@Override
	public List<BizBorrowStatModel> selectStatByBorrowType(String borrowStatus) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		List<BizBorrowStatModel> vos = mapper.selectStatByBorrowType(borrowStatus);
		List<BizBorrowStatModel> list = new ArrayList<BizBorrowStatModel>();
		BigDecimal totalAmount = BigDecimal.ZERO;
		Integer totalCount = 0;
		// 求总
		for (BizBorrowStatModel vo : vos) {
			totalAmount = totalAmount.add(vo.getTotalAmount() == null ? new BigDecimal(0) : vo.getTotalAmount());
			totalCount += vo.getTotalCount() == null ? 0 : vo.getTotalCount();
		}
		// 求占比
		for (BizBorrowStatModel vo : vos) {
			if (vo.getTotalAmount() == null || vo.getTotalAmount().compareTo(new BigDecimal(0)) == 0 || totalAmount == null || totalAmount.compareTo(new BigDecimal(0)) == 0) {
				vo.setAmountPercent(new BigDecimal(0));
			} else {

				BigDecimal amountPercent = vo.getTotalAmount().divide(totalAmount, 4, RoundingMode.DOWN);
				vo.setAmountPercent(amountPercent);
			}

			if (vo.getTotalCount() == null || vo.getTotalCount().intValue() == 0 || totalCount == null || totalCount.intValue() == 0) {
				vo.setCountPercent(new BigDecimal(0));
			} else {
				BigDecimal countPercent = new BigDecimal(vo.getTotalCount()).divide(new BigDecimal(totalCount), 4, RoundingMode.DOWN);
				vo.setCountPercent(countPercent);
			}

			list.add(vo);
		}
		// 赋值
		List<BizBorrowStatModel> results = new ArrayList<BizBorrowStatModel>();
		BizBorrowStatModel vo1 = new BizBorrowStatModel();
		vo1.setBorrowType(Constant.BORROW_TYPE_1);
		BizBorrowStatModel vo2 = new BizBorrowStatModel();
		vo2.setBorrowType(Constant.BORROW_TYPE_2);
		BizBorrowStatModel vo3 = new BizBorrowStatModel();
		vo3.setBorrowType(Constant.BORROW_TYPE_3);
		BizBorrowStatModel vo4 = new BizBorrowStatModel();
		vo4.setBorrowType(Constant.BORROW_TYPE_4);
		BizBorrowStatModel vo5 = new BizBorrowStatModel();
		vo5.setBorrowType(Constant.BORROW_TYPE_5);

		for (BizBorrowStatModel vo : list) {
			if (Constant.BORROW_TYPE_1.equals(vo.getBorrowType())) {
				vo1 = vo;
			} else if (Constant.BORROW_TYPE_2.equals(vo.getBorrowType())) {
				vo2 = vo;
			} else if (Constant.BORROW_TYPE_3.equals(vo.getBorrowType())) {
				vo3 = vo;
			} else if (Constant.BORROW_TYPE_4.equals(vo.getBorrowType())) {
				vo4 = vo;
			} else if (Constant.BORROW_TYPE_5.equals(vo.getBorrowType())) {
				vo5 = vo;
			}
		}
		// 添加到结果集
		results.add(vo1);
		results.add(vo2);
		results.add(vo3);
		results.add(vo4);
		results.add(vo5);
		BizBorrowStatModel vo = new BizBorrowStatModel();
		vo.setTotalAmount(totalAmount);
		vo.setTotalCount(totalCount);

		results.add(vo);

		return results;
	}

	@Override
	public List<BizBorrowStatModel> findByBorrowCountData() {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		// 满标 流标 撤销
		List<BizBorrowStatModel> listVo = mapper.findByBorrowCountData(Constant.BORROW_STATUS_4, Constant.BORROW_STATUS_0, Constant.BORROW_STATUS_3);

		return listVo;
	}

	@Override
	public List<BizBorrowTimeVO> getProportionByBorrowTime() {
		List<BizBorrowTimeVO> list = new ArrayList<BizBorrowTimeVO>();
		int sumBorrowTimeNum = 0;
		BigDecimal sumBorrowTimeAmt = new BigDecimal(0);

		// 借款期限为0~3个月
		BizBorrowTimeVO result = getBizBorrowTimeVo(0, 3);
		result.setTimeType("0~3");
		list.add(result);
		sumBorrowTimeNum += result.getBorrowNum();
		sumBorrowTimeAmt.add(result.getBorrowAmt());

		// 借款期限为3~6个月
		result = getBizBorrowTimeVo(3, 6);
		result.setTimeType("3~6");
		list.add(result);
		sumBorrowTimeNum += result.getBorrowNum();
		sumBorrowTimeAmt.add((null == result.getBorrowAmt() ? new BigDecimal("0") : result.getBorrowAmt()));

		// 借款期限为6~9个月
		result = getBizBorrowTimeVo(6, 9);
		result.setTimeType("6~9");
		list.add(result);
		sumBorrowTimeNum += result.getBorrowNum();
		sumBorrowTimeAmt.add((null == result.getBorrowAmt() ? new BigDecimal("0") : result.getBorrowAmt()));

		// 借款期限为9~12个月
		result = getBizBorrowTimeVo(9, 12);
		result.setTimeType("9~12");
		list.add(result);
		sumBorrowTimeNum += result.getBorrowNum();
		sumBorrowTimeAmt.add((null == result.getBorrowAmt() ? new BigDecimal("0") : result.getBorrowAmt()));

		// 借款期限为12~24个月
		result = getBizBorrowTimeVo(12, 24);
		result.setTimeType("12~24");
		list.add(result);
		sumBorrowTimeNum += result.getBorrowNum();
		sumBorrowTimeAmt.add((null == result.getBorrowAmt() ? new BigDecimal("0") : result.getBorrowAmt()));

		// 借款期限为24个月以上
		result = getBizBorrowTimeVo(24, 0);
		result.setTimeType("24");
		list.add(result);
		sumBorrowTimeNum += result.getBorrowNum();
		sumBorrowTimeAmt.add((null == result.getBorrowAmt() ? new BigDecimal("0") : result.getBorrowAmt()));

		for (BizBorrowTimeVO bizBorrowTimeVO : list) {
			if (null != bizBorrowTimeVO.getBorrowAmt() && bizBorrowTimeVO.getBorrowAmt().compareTo(new BigDecimal("0")) > 1) {
				bizBorrowTimeVO.setAmtProportion(ArithmeticUtil.mul2(ArithmeticUtil.div(bizBorrowTimeVO.getBorrowAmt(), sumBorrowTimeAmt, 4), new BigDecimal("100"), 4));
			} else {
				bizBorrowTimeVO.setAmtProportion(BigDecimal.valueOf(0));
			}

			if (null != bizBorrowTimeVO.getBorrowNum() && bizBorrowTimeVO.getBorrowNum() > 0) {
				bizBorrowTimeVO.setNumProportion(ArithmeticUtil.mul2(ArithmeticUtil.div(new BigDecimal(bizBorrowTimeVO.getBorrowNum()), new BigDecimal(sumBorrowTimeNum), 4), new BigDecimal("100"), 4));
			} else {
				bizBorrowTimeVO.setNumProportion(BigDecimal.valueOf(0));
			}
		}
		result = new BizBorrowTimeVO();
		result.setBorrowAmt(sumBorrowTimeAmt);
		result.setBorrowNum(sumBorrowTimeNum);
		list.add(result);
		return list;
	}

	/**
	 * 
	 * Description：<br>
	 * 通过借款期限得到借款总金额，笔数
	 * 
	 * @author Jie.Zou
	 * @date 2015年11月11日
	 * @version v1.0.0
	 * @param starBorTime
	 * @param endBorTime
	 * @return
	 */
	public BizBorrowTimeVO getBizBorrowTimeVo(int starBorTime, int endBorTime) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		BizBorrowTimeVO vo = new BizBorrowTimeVO();
		vo.setStartBorrowTime(starBorTime);
		vo.setEndBorrowTime(endBorTime);
		BizBorrowTimeVO result = mapper.selectBorrowTime(vo);
		if (result != null)
			return result;
		return new BizBorrowTimeVO();
	}

	@Override
	public BizBorrow getLastBorrowByBorrowType(String borrowType) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.getLastBorrowByBorrowType(borrowType);
	}

	@Override
	public int updateByPrimaryKeySelectiveAndCreateRepaymentPlan(BizBorrow bizBorrow) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		try {
			bizRepaymentPlanService.createRepaymentPlan(bizBorrow);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return mapper.updateByPrimaryKeySelective(bizBorrow);
	}

	/**
	 * 对新手标用户投资，生成投资记录，将用户可用金额减少相应的投资金额，并且在冻结金额中进行增加
	 */
	@Override
	public void invest(BizBorrow bizBorrow, BigDecimal amount, String userId, String investmentType) {

		if (amount == null || BigDecimal.ZERO.compareTo(amount) >= 0) {
			throw new NullArgumentException("amount is null or amount is 0");
		}
		if (!StringUtils.hasLength(userId)) {
			throw new NullArgumentException("userId is null");
		}

		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		// 判断用户是否满足条件
		if(!bizBorrowDetailService.isPassCondition(bizBorrow.getPid(),userId)){
			throw new ConditionExcepiton("不满足投标条件");
		}
		// 判断用户是否逾期
		if(cusTomerService.isOverdue(userId)){
			throw new ConditionExcepiton("有逾期记录，操作冻结");
		}
		
		
		// 获取标的对象
		BizBorrow borrow = mapper.selectByPrimaryKey(bizBorrow.getPid());

		if (borrow == null) {
			// 标的不存在
			throw new RuntimeException("标的不存在");
		}
		// 标的状态，判断标是否已改变状态
		if (!Constant.BORROW_STATUS_2.equals(borrow.getBorStatus())) {
			// 状态已经改变
			throw new BorrowStatusChangeException("标的状态已经发生改变");
		}
		// 起投金额,判断用户是否已大于起投金额
		if (borrow.getStartMoney() != null && borrow.getStartMoney().compareTo(amount) > 0) {
			throw new OutInvestScopeException("低于起投金额￥" + borrow.getStartMoney());
		}
		// 投资上限，判断用户是否已达到上限，获取用户历史投资该标的金额
		BigDecimal sumAmount = bizBorrowDetailService.sumInvestAmount(bizBorrow.getPid(), userId);
		sumAmount = sumAmount == null ? BigDecimal.ZERO : sumAmount;
		sumAmount = sumAmount.add(amount);
		if (borrow.getEndMoney() != null && borrow.getEndMoney().compareTo(BigDecimal.ZERO) > 0 && borrow.getEndMoney().compareTo(sumAmount) < 0) {
			throw new OutInvestScopeException("您的投资总金额已超出￥" + borrow.getEndMoney());
		}
		// 标的完成度,判断标是否已满标
		if (borrow.getBorrowProgress() == null) {
			borrow.setBorrowProgress(BigDecimal.ZERO);
		} else if (borrow.getBorrowProgress().equals(BigDecimal.ONE)) {
			// 满标
			throw new BorrowStatusChangeException("已经满标了");
		}
		// 标的剩余金额,判断剩余金额是否还大于投资金额
		if (borrow.getSurplusMoney().compareTo(amount) < 0) {
			// 超出可投范围了
			throw new OutInvestScopeException("超出标的剩余金额了");
		}
		// 已投金额,判断用户投资金额是否满足条件
		BigDecimal alreadyMoney = borrow.getAlreadyMoney() == null ? BigDecimal.ZERO : borrow.getAlreadyMoney();
		if (borrow.getBorrowMoney().subtract(alreadyMoney).compareTo(amount) < 0) {
			// 超出可投范围了
			throw new OutInvestScopeException("超出可投范围了");
		}
		// 是否倍增，判断用户是否满足倍增条件
		if ("1".equals(borrow.getIsTimesInvest())) {
			if (amount.doubleValue() % borrow.getStartMoney().doubleValue() != 0) {
				throw new OutInvestScopeException("标的为倍数投资，不满足此条件");
			}
		}

		// 标的投标次数增加1
		Integer bidCount = borrow.getTenderCount() == null ? 0 : Integer.parseInt(borrow.getTenderCount());
		bidCount++;
		// 标的剩余金额减少
		BigDecimal sMoney = borrow.getSurplusMoney() == null ? BigDecimal.ZERO : borrow.getSurplusMoney();
		sMoney = sMoney.subtract(amount);
		if (sMoney.compareTo(BigDecimal.ZERO) < 0) {
			throw new OutInvestScopeException("超出标的剩余金额了");
		}
		// 标的已投金额进行增加
		BigDecimal aMoney = borrow.getAlreadyMoney() == null ? BigDecimal.ZERO : borrow.getAlreadyMoney();
		aMoney = aMoney.add(amount);
		if (aMoney.compareTo(borrow.getBorrowMoney()) > 0) {
			throw new OutInvestScopeException("标的已投金额大于借款金额");
		}

		borrow.setTenderCount(bidCount + "");
		borrow.setBorrowProgress(aMoney.divide(borrow.getBorrowMoney(), 4, BigDecimal.ROUND_DOWN));
		borrow.setSurplusMoney(sMoney);
		borrow.setAlreadyMoney(aMoney);
		if (sMoney.compareTo(BigDecimal.ZERO) == 0 && aMoney.compareTo(borrow.getBorrowMoney()) == 0 && borrow.getBorrowProgress().compareTo(BigDecimal.ONE) == 0) {
			// 已经满标了
			borrow.setBorStatus(Constant.BORROW_STATUS_4);
		}
		// 新增投标记录
		BizBorrowDetail bbd = new BizBorrowDetail();

		bbd.setPid(bbd.getPK());
		bbd.setBorrowId(borrow.getPid());
		bbd.setCustomerId(userId);
		bbd.setInvestmentAmount(amount);
		bbd.setInvestmentType(investmentType);
		bbd.setInvestmentTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
		bbd.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
		bbd.setCreateUser(userId);
		bbd.setLastUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
		bbd.setLastUpdateUser(userId);
		bbd.setStatus("1");
		// 用户投资的管理费率
		BigDecimal manageRate = sysVipinfoService.selectFeeByUserId(userId);
		manageRate = manageRate == null ? new BigDecimal(0.1) : manageRate;
		bbd.setManagerRate(manageRate);

		// 冻结投资金额
		try {
			customerAccountService.updateAvailableAmountToFreezeAmount(amount, userId, borrow.getPid());
		} catch (FrameworkException e) {
			throw new AvailableAmountExcepiton("扣款失败");
		}

		int result2 = mapper.updateByPrimaryKeySelective(borrow);
		if (result2 < 1) {
			throw new UpdateChangeVersionException("新手标投资时，更新标的信息失败");
		}

		bizBorrowDetailService.insert(bbd);

		// 更新交易时间
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("createTime", DateUtil.format(new Date()));
		map.put("userId", userId);
		cusTomerMapper.updateLastTransacTime(map);

	}

	/**
	 * 
	 */
	@Override
	public void executeTimer() {
		System.out.println("标的发布 定时器执行中");
		// 更改 标的状态
		try {
			bizBorrowMapper.updateBorrowPublish();
		} catch (Exception e) {
			e.printStackTrace();
			throw new UpdateChangeVersionException("修改待发布 定时任务失败,失败原因：" + e.getMessage());
		}
	}

	@Override
	public BizBorrow getLastBorrowDByBorrowType(String borrowType) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		BizBorrow bizBorrow = mapper.getLastBorrowByBorrowType(borrowType);
		if (null != bizBorrow) {
			if (null != bizBorrow.getBorrowRate())
				bizBorrow.setBorrowRate(bizBorrow.getBorrowRate().multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
			if (null != bizBorrow.getBorrowProgress())
				bizBorrow.setBorrowProgress(bizBorrow.getBorrowProgress().multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
			else
				bizBorrow.setBorrowProgress(BigDecimal.ZERO);
		}
		return bizBorrow;
	}

	@Override
	public void investExperience(BizBorrow bizBorrow, BigDecimal amount, String userId, String investmentType, String[] epces) {
		if (amount == null || BigDecimal.ZERO.compareTo(amount) >= 0) {
			throw new NullArgumentException("amount is null or amount is 0");
		}
		if (!StringUtils.hasText(userId)) {
			throw new NullArgumentException("userId is null");
		}
		// 判断用户是否逾期
		if(cusTomerService.isOverdue(userId)){
			throw new ConditionExcepiton("有逾期记录，操作冻结");
		}
		// 用户选择的体验标集合
		List<ActExpActDetail> aeads = actExpActDetailMapper.selectExperienceGoldByPidArr(epces);
		// 判断体验标是否过期
		for (ActExpActDetail aead : aeads) {
			if (DateUtil.format(aead.getExpireTime()).getTime() < DateUtil.getSystemDate().getTime()) {
				throw new NullArgumentException("操作超时，投资失败，请重新投资");
			}
		}
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		// 获取标的对象
		BizBorrow borrow = mapper.selectByPrimaryKey(bizBorrow.getPid());

		if (borrow == null) {
			// 标的不存在
			throw new RuntimeException("数据发生改变");
		}
		// 标的状态，判断标是否已改变状态
		if (!Constant.BORROW_STATUS_2.equals(borrow.getBorStatus())) {
			// 状态已经改变
			throw new RuntimeException("状态已经发生改变");
		}
		// 起投金额,判断用户是否已大于起投金额
		if (borrow.getStartMoney() != null && borrow.getStartMoney().compareTo(amount) > 0) {
			throw new OutInvestScopeException("低于起投金额￥" + borrow.getStartMoney());
		}
		// 投资上限，判断用户是否已达到上限，获取用户历史投资该标的金额
		BigDecimal sumAmount = bizBorrowDetailService.sumInvestAmount(bizBorrow.getPid(), userId);
		sumAmount = sumAmount == null ? BigDecimal.ZERO : sumAmount;
		sumAmount = sumAmount.add(amount);
		if (borrow.getEndMoney() != null && borrow.getEndMoney().compareTo(sumAmount) < 0) {
			throw new OutInvestScopeException("您的投资总金额已超出￥" + borrow.getEndMoney());
		}
		// 标的完成度,判断标是否已满标
		if (borrow.getBorrowProgress() == null) {
			borrow.setBorrowProgress(BigDecimal.ZERO);
		} else if (borrow.getBorrowProgress().compareTo(BigDecimal.ONE) == 0) {
			// 满标
			throw new BorrowStatusChangeException("已经满标了");
		}
		// 标的剩余金额,判断剩余金额是否还大于投资金额
		if (borrow.getSurplusMoney().compareTo(amount) < 0) {
			// 超出可投范围了
			throw new OutInvestScopeException("超出标的剩余金额了");
		}
		// 已投金额,判断用户投资金额是否满足条件
		BigDecimal alreadyMoney = borrow.getAlreadyMoney() == null ? BigDecimal.ZERO : borrow.getAlreadyMoney();
		if (borrow.getBorrowMoney().subtract(alreadyMoney).compareTo(amount) < 0) {
			// 超出可投范围了
			throw new OutInvestScopeException("超出可投范围了");
		}
		// 是否倍增，判断用户是否满足倍增条件
		if ("1".equals(borrow.getIsTimesInvest())) {
			if (amount.doubleValue() % borrow.getStartMoney().doubleValue() != 0) {
				throw new OutInvestScopeException("标的为倍数投资，不满足此条件");
			}
		}
		// 冻结投资金额
		try {
			customerAccountService.updateExperienceAmountToFreezeAmount(amount, userId, borrow.getPid(), epces);
		} catch (FrameworkException e) {
			throw new AvailableAmountExcepiton("扣款失败");
		}

		// 减少标的剩余金额
		BigDecimal surplusMoney = borrow.getSurplusMoney() == null ? BigDecimal.ZERO : borrow.getSurplusMoney();
		surplusMoney = surplusMoney.subtract(amount);
		if (surplusMoney.compareTo(BigDecimal.ZERO) < 0) {
			throw new OutInvestScopeException("标的剩余金额减少时出现异常");
		}
		// 增加标的已投金额
		alreadyMoney = alreadyMoney.add(amount);
		if (alreadyMoney.compareTo(borrow.getBorrowMoney()) > 0) {
			throw new OutInvestScopeException("标的已投金额增加时出现异常");
		}

		// 标的投标次数增加1
		Integer bidCount = borrow.getTenderCount() == null ? 0 : Integer.parseInt(borrow.getTenderCount());
		
		borrow.setBorrowProgress(alreadyMoney.divide(borrow.getBorrowMoney()));
		borrow.setSurplusMoney(surplusMoney);
		borrow.setAlreadyMoney(alreadyMoney);
		if (surplusMoney.compareTo(BigDecimal.ZERO) == 0 && alreadyMoney.compareTo(borrow.getBorrowMoney()) == 0 && borrow.getBorrowProgress().compareTo(BigDecimal.ONE) == 0) {
			// 判断是否满标
			borrow.setBorStatus(Constant.BORROW_STATUS_4);
		}

		// 投标记录
		for (int i = 0; i < epces.length; i++) {
			BizBorrowDetail bbd = new BizBorrowDetail();
			bbd.setPid(bbd.getPK());
			bbd.setBorrowId(borrow.getPid());
			bbd.setCustomerId(userId);
			bbd.setExperienceTicketId(epces[i]);
			ActExpActDetail expDetail = actExpActDetailMapper.selectExperienceGoldById(epces[i]);
			if(null!=expDetail){
				bbd.setInvestmentAmount(expDetail.getExpAmount());
			}else{
				throw new RuntimeException("体验金卷异常");
			}
			bbd.setInvestmentType(investmentType);
			bbd.setInvestmentTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
			bbd.setCreateTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
			bbd.setCreateUser(userId);
			bbd.setLastUpdateTime(DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss"));
			bbd.setLastUpdateUser(userId);
			bbd.setStatus("1");
			bbd.setManagerRate(borrow.getManageExpenseRate());
			bizBorrowDetailService.insert(bbd);
			
			//每一张卷增加一次投标记录
			bidCount++;
		}

		borrow.setTenderCount(bidCount.toString());
		int result2 = mapper.updateByPrimaryKeySelective(borrow);
		if (result2 < 1) {
			throw new UpdateChangeVersionException("体验标投资时，更新标的信息失败");
		}
		//更新最后交易时间
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("createTime", DateUtil.getSystemDate());
		cusTomerMapper.updateLastTransacTime(map);
	}

	@Override
	public void checkBorrowSuccess() {
		int count = 0;
		int failCount = 0;
		int sumBorrowCount = 0;
		try {
			List<BizBorrow> borrows = this.getBidOkBorrows();
			sumBorrowCount = borrows.size();
			for (BizBorrow bizBorrow : borrows) {
				modifyBorrowSuccess(bizBorrow);
				count++;
			}
		} catch (Exception e) {
			failCount++;
			logger.error(e.getMessage());
		}
		logger.info("总共满标：" + sumBorrowCount + "个，成功满标：" + count + "个，失败满标：" + failCount + "个");
	}

	/**
	 * 
	 * Description：<br>
	 * 得到所有借款成功的借款集合
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @return
	 */
	private List<BizBorrow> getBidOkBorrows() {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.selectBidOkBorrows();
	}

	/**
	 * 
	 * Description：<br>
	 * 更新借款成功状态，更新标信息
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @throws Exception
	 */
	private void modifyBorrowSuccess(BizBorrow bizBorrow) throws Exception {
		if (bizBorrow.getBorStatus().equals(Constant.BORROW_STATUS_4)) {
			bizBorrow.setBorFullTime(DateUtil.getSystemDate());// 更新借款最后时间（满标成功时间）
			bizBorrow.setBorStatus(Constant.BORROW_STATUS_5);// 更新借款状态为还款中
			List<BizBorrowDetail> bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid());
			bizBorrow.setTenderCount(bids.size() + "");
			bizBorrowMapper.updateByPrimaryKeySelective(bizBorrow);
			borrowSuccessWhenRepayment(bizBorrow);
		}
	}

	/**
	 * 
	 * Description：<br>
	 * 借款成功放款时所需处理
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @throws Exception
	 */
	private void borrowSuccessWhenRepayment(BizBorrow bizBorrow) throws Exception {
		if (bizBorrow.getBorStatus() == Constant.BORROW_STATUS_5) {
			this.checkBorrowStatus(bizBorrow.getBorStatus());
			List<BizBorrowDetail> bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid()); // 得到借款的所有投标记录
			// List<CusTomer> customers =
			// cusTomerService.selectAllRelateUsers(bizBorrow,
			// bids);//得到借款相关的客户信息
			
			List<BizRepaymentPlan> repaymentPlans = new ArrayList<BizRepaymentPlan>();
			List<BizReceiptPlan> receiptPlans = new ArrayList<BizReceiptPlan>();
			try {
			CalculationDto dto = getCalculationDtoByBizBorrow(bizBorrow);
			ICalculation cal = CalculationFactory.getCalculation(dto);

			if (Constant.BORROW_TYPE_4.equals(bizBorrow.getBorrowType()) || Constant.BORROW_TYPE_5.equals(bizBorrow.getBorrowType())) {
				receiptPlans = getReceiptPlans(bizBorrow, bids);// 收款计划
				repaymentPlans = cal.execRepaymentCalcByDate();// 还款计划
			} else {
				try {
					receiptPlans = getReceiptPlans(bizBorrow, bids);// 收款计划
					repaymentPlans = cal.execRepaymentCalc();// 还款计划
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//如果借款为e计划需要计算招标中到满标这段时间的利息
			if(Constant.BORROW_TYPE_3.equals(bizBorrow.getBorrowType())){
				bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid());
				this.borrowPlanHandle(bids, bizBorrow, repaymentPlans, receiptPlans);
			}
			
			Date time = DateUtil.getSystemDate();
			this.handleBorrowerAmount(bizBorrow, time);// 处理借款金额到借款人账户
			bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid());
			this.handleBidders(bizBorrow, bids, time);// 处理投资人投标金额实际扣除
			bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid()); // 得到借款的所有投标记录
			this.handleReward(bizBorrow, bids, time, cal);// 处理投标奖励
			this.handlePlans(repaymentPlans, receiptPlans, bizBorrow);// 添加收款、还款计划
			this.handleBorrowBidding(bizBorrow);//分标处理
			bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid()); // 得到借款的所有投标记录
			this.handleBorrowOther(bids,bizBorrow);//进行一些其他操作
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * Description：<br>
	 * 判断放款借款的状态是否正常
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param status
	 */
	private void checkBorrowStatus(String status) {
		List<String> relateStatus = new ArrayList<String>();
		relateStatus.add(Constant.BORROW_STATUS_2);
		if (relateStatus.contains(status)) {
			throw new RuntimeException("在错误的状态下执行放款操作，目前状态=" + status);
		}
	}
	
	/**
	 * 
	 * Description：<br>
	 * 通过借款得到还款计划
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @return
	 */
	private CalculationDto getCalculationDtoByBizBorrow(BizBorrow bizBorrow) {
		CalculationDto dto = new CalculationDto(); 
		dto.setRepaymentType(Integer.parseInt(bizBorrow.getRepaymentType()));// 计息方式
		dto.setInterestDate(DateUtil.format(bizBorrow.getBorFullTime()));// 满标时间
		dto.setManagementRate((bizBorrow.getManageExpenseRate().multiply(new BigDecimal(100))));// 借款管理费率
		dto.setRepaymentAmt(bizBorrow.getBorrowMoney());// 借款金额
		dto.setRepaymentRate((bizBorrow.getBorrowRate().multiply(new BigDecimal(100))));// 借款利率
		dto.setRepaymentDeadline(Integer.parseInt(bizBorrow.getBorDeadline()));// 借款期限
		dto.setRewardRate(((bizBorrow.getInvestRewardScale()==null ? BigDecimal.ZERO : bizBorrow.getInvestRewardScale()).multiply(new BigDecimal(100))));// 投标奖励
		dto.setBorrowId(bizBorrow.getPid());//借款Id
		dto.setCustomerId(bizBorrow.getCustomerId());//客户Id
		dto.setInterestType(bizBorrow.getAccrualType());//计息类型
		// 加息收益
		// 加息费用
		dto.setInterestRate(bizBorrow.getManageExpenseRate().multiply(new BigDecimal(100)));// 利息管理费
		return dto;
	}

	private List<BizReceiptPlan> getReceiptPlans(BizBorrow bizBorrow, List<BizBorrowDetail> bids) throws Exception {
		List<BizReceiptPlan> receiptPlan = new ArrayList<BizReceiptPlan>();
		Map<String, BizBorrowDetail> map = new HashMap<String, BizBorrowDetail>();
		// 循环投标记录，把每个人的投标金额汇总
		for (BizBorrowDetail bid : bids) {
			if (null != map.get(bid.getCustomerId())) {
				BizBorrowDetail bizBorrowDetail = map.get(bid.getCustomerId());
				if(null!=bid.getScale()){
					bizBorrowDetail.setScale(bid.getScale());
				}
				bizBorrowDetail.setInvestmentAmount(bizBorrowDetail.getInvestmentAmount().add(bid.getInvestmentAmount()));
				map.put(bizBorrowDetail.getCustomerId(), bizBorrowDetail);
			} else {
				map.put(bid.getCustomerId(), bid);
			}
		}
		//然后生成收款计划
		for (Entry<String, BizBorrowDetail> en : map.entrySet()) {
			BizBorrowDetail bid = en.getValue();
			CalculationDto dto = new CalculationDto();
			dto.setRepaymentType(Integer.parseInt(bizBorrow.getRepaymentType()));// 计息方式
			dto.setInterestDate(DateUtil.format(bizBorrow.getBorFullTime()));// 满标时间
			dto.setManagementRate((bizBorrow.getManageExpenseRate().multiply(new BigDecimal(100))));// 借款管理费率
			dto.setRepaymentAmt(bid.getInvestmentAmount());// 投资金额
			dto.setRepaymentRate((bizBorrow.getBorrowRate().multiply(new BigDecimal(100))));// 借款利率
			dto.setRepaymentDeadline(Integer.parseInt(bizBorrow.getBorDeadline()));// 借款期限
			dto.setInterestType(bizBorrow.getAccrualType());//计息类型
			if(null!=bid.getScale()){
				dto.setAddInterest(bid.getScale().multiply(new BigDecimal(100)));
			}
			List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(bid.getCustomerId());
			if(null!=bid.getManagerRate()){
				dto.setInterestRate(bid.getManagerRate().multiply(new BigDecimal(100)));// 利息管理费
				dto.setAddInterestRate(bid.getManagerRate().multiply(new BigDecimal(100)));
			}
			dto.setBorrowId(bizBorrow.getPid());//借款ID
			List<BizReceiptPlan> newReceiptPlan = new ArrayList<BizReceiptPlan>();
			if (Constant.BORROW_TYPE_4.equals(bizBorrow.getBorrowType()) || Constant.BORROW_TYPE_5.equals(bizBorrow.getBorrowType()))
				newReceiptPlan = CalculationFactory.getCalculation(dto).execReceivablesCalcByDate();
			else
				newReceiptPlan = CalculationFactory.getCalculation(dto).execReceivablesCalc();
			for (BizReceiptPlan bizReceiptPlan : newReceiptPlan) {
				bizReceiptPlan.setCustomerId(bid.getCustomerId());
				bizReceiptPlan.setPlayType("1");
				if(null != list && list.size()>0){
					SysVipinfo vipinfo =list.get(0);
					if(!"".equals(vipinfo.getVipLevel()) && vipinfo.getVipLevel()!=null){
						bizReceiptPlan.setIsVip("1");
					}else{
						bizReceiptPlan.setIsVip("0");
					}
				}else{
					bizReceiptPlan.setIsVip("0");
				}
			}
			receiptPlan.addAll(newReceiptPlan);
		}
		return receiptPlan;
	}

	/**
	 * 
	 * Description：<br>
	 * 处理借款金额到借款者账户
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @throws FrameworkException
	 */
	private void handleBorrowerAmount(BizBorrow bizBorrow, Date time) throws FrameworkException {
		// 判断借款不是体验标
		if (!bizBorrow.getBorrowType().equals(Constant.BORROW_TYPE_5))
			bizAccountCommonService.addSuccessBorrowAmount(bizBorrow, time);
	}

	/**
	 * 
	 * Description：<br>
	 * 放款时处理投资人数据 每个投标人分别从其冻结金额中减去投标金额 需要产生流水，产生类别资金变化明细
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param bids
	 * @param time
	 * @throws FrameworkException
	 */
	private void handleBidders(BizBorrow bizBorrow, List<BizBorrowDetail> bids, Date time) throws FrameworkException {
		//获得系统路径（为备注路径做准备）
		String baseUrl = sysParamService.getParamsByParamKey(SystemParamKeyConstant.EP2P_URL).getParamValue();
		// 处理投标记录，加入投标计息时间
		for (BizBorrowDetail bid : bids) {
			//如果是e首房、e抵押、新手标、体验标则满标加入计息时间
			if(Constant.BORROW_TYPE_1.equals(bizBorrow.getBorrowType())||Constant.BORROW_TYPE_2.equals(bizBorrow.getBorrowType())||Constant.BORROW_TYPE_4.equals(bizBorrow.getBorrowType())||Constant.BORROW_TYPE_5.equals(bizBorrow.getBorrowType())){
				bid.setInterestTime(DateUtil.format(DateUtil.getSystemDate()));
				bizBorrowDetailService.updateByPrimaryKeySelective(bid);
			}
		}
		// 判断是不是体验金
		if (bizBorrow.getBorrowType().equals(Constant.BORROW_TYPE_5)) {
			Map<String, BizBorrowDetail> map = new HashMap<String, BizBorrowDetail>();
			for (BizBorrowDetail bid : bids) {
				if (null != map.get(bid.getCustomerId())) {
					BizBorrowDetail bizBorrowDetail = map.get(bid.getCustomerId());
					bizBorrowDetail.setInvestmentAmount(bizBorrowDetail.getInvestmentAmount().add(bid.getInvestmentAmount()));
					map.put(bizBorrowDetail.getCustomerId(), bizBorrowDetail);
				} else {
					map.put(bid.getCustomerId(), bid);
				}
			}
			for (Entry<String, BizBorrowDetail> en : map.entrySet()) {
				BizBorrowDetail bid = en.getValue();
				bizAccountExperienceService.unfreezeAmountWhenBiddingSuccess(bid, time,"投资成功"+bid.getInvestmentAmount()+"元,"+"<a href='"+baseUrl+"mybids/experienceBorrowController/index/toBorrowStandardInfo.shtml?pid="+bizBorrow.getPid()+"' >"+bizBorrow.getBorrowCode()+bizBorrow.getBorrowName()+"</a>");// 体验金满标扣除金额
			}
		} else {
			// 非体验标
			// 满标扣除充值资金或普通资金
			Map<String, BizBorrowDetail> map = new HashMap<String, BizBorrowDetail>();
			// 循环投标记录，把每个人的投标金额汇总，为生成一条资金流水做准备
			for (BizBorrowDetail bid : bids) {
				if (null != map.get(bid.getCustomerId())) {
					BizBorrowDetail bizBorrowDetail = map.get(bid.getCustomerId());
					bizBorrowDetail.setInvestmentAmount(bizBorrowDetail.getInvestmentAmount().add(bid.getInvestmentAmount()));
					map.put(bizBorrowDetail.getCustomerId(), bizBorrowDetail);
				} else {
					map.put(bid.getCustomerId(), bid);
				}
			}
			// 循环生成的Map，然后进行扣款，生成资金流水
			for (Entry<String, BizBorrowDetail> en : map.entrySet()) {
				BizBorrowDetail bid = en.getValue();
				customerAccountService.updateDeductedFreezeAmount(bid.getInvestmentAmount(), bid.getCustomerId(), bid.getBorrowId(), TradeTypeConstant.INVEST_TYPE_302, "投资成功"+bid.getInvestmentAmount()+"元,"+"<a href='"+baseUrl+"business/optionalInvestController/index/selectOptionalInvestDataInfoData.shtml?borrowId="+bizBorrow.getPid()+"&pageTag=1' >"+bizBorrow.getBorrowCode()+bizBorrow.getBorrowName()+"</a>");
			}
		}

	}

	/**
	 * 
	 * Description：<br>
	 * 处理投标奖励
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param bids
	 * @param time
	 * @throws FrameworkException
	 */
	private void handleReward(BizBorrow bizBorrow, List<BizBorrowDetail> bids, Date time, ICalculation cal) throws FrameworkException {
		// 判断是否有投标奖励
		if (null != bizBorrow.getInvestRewardScale()) {
			if (bizBorrow.getInvestRewardScale().compareTo(BigDecimal.ZERO) != 0) {

				//BigDecimal total = cal.getInvestmentReward();// 总投标奖励
				//sysAccountService.updateSubSystemAmount(total, null, TradeTypeConstant.SYSTEM_TRADE_TYPE_1014, bizBorrow.getPid(), "");

				// 处理投资者的投标奖励，归入的资金类别由选项决定
				Map<String, BigDecimal> bidRewards = bizBorrowDetailService.getBidRewards(bizBorrow, bids);
				for (BizBorrowDetail bid : bids) {
					BigDecimal reward = bidRewards.get(bid.getPid());
					// 系统账户支出投标奖励
					sysAccountService.updateSubSystemAmount(reward, bid.getCustomerId(), TradeTypeConstant.SYSTEM_TRADE_TYPE_1014, bizBorrow.getPid(),bid.getCustomerName()+","+bizBorrow.getBorrowName()+",奖励"+reward+"元("+bid.getInvestmentAmount()+"*"+bizBorrow.getInvestRewardScale()+")");
					if (reward.compareTo(BigDecimal.ZERO) > 0) {
						bizAccountCommonService.modifyAvailableAmountWithTallyCreateNoTran(bid.getCustomerId(), Constant.CUST_FUND_BUSINESS_TYPE_1, TradeTypeConstant.RETURNS_TYPE_403, reward, bizBorrow.getBorrowName()+",奖励"+reward+"元("+bid.getInvestmentAmount()+"*"+bizBorrow.getInvestRewardScale()+")", time);
					}
				}
			}
		}
	}

	/**
	 * 
	 * Description：<br>
	 * 添加还款和收款计划
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param repaymentPlans
	 *            还款计划
	 * @param receiptPlans
	 *            收款计划
	 */
	private void handlePlans(List<BizRepaymentPlan> repaymentPlans, List<BizReceiptPlan> receiptPlans, BizBorrow bizBorrow) {
		Map<String, BizRepaymentPlan> indexPlanMap = new HashMap<String, BizRepaymentPlan>();
		//删除之前的虚拟还款计划，保证只有一套生效的还款计划
		bizRepaymentPlanMapper.deleteBatch(bizBorrow.getPid(), bizBorrow.getCustomerId());
		//生成还款计划保存
		for (BizRepaymentPlan plan : repaymentPlans) {
			plan.setPid(plan.getPK());
			plan.setCustomerId(bizBorrow.getCustomerId());
			indexPlanMap.put(plan.getPlanindex(), plan);
			bizRepaymentPlanMapper.insertSelective(plan);
		}
		
		//生成收款计划保存
		for (BizReceiptPlan plan : receiptPlans) {
			BizRepaymentPlan repaymentPlan = indexPlanMap.get(plan.getPlanIndex());
			plan.setPid(plan.getPK());
			plan.setRepPlaPid(repaymentPlan.getPid());
			plan.setNetHikeStatus(Constant.NET_HIKE_STATUS_0);
			bizReceiptPlanMapper.insertSelective(plan);
		}

	}
	
	/**
	 * 
	 * Description：<br> 
	 * 处理需要进入招标中的借款
	 * @author  Jie.Zou
	 * @date    2016年2月18日
	 * @version v1.0.0
	 */
	public void handleBorrowBidding(BizBorrow bizBorrow){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("parentId", bizBorrow.getParentId());
			map.put("borOrder", bizBorrow.getBorOrder());
			//得到满标借款的
			List<BizBorrow> list = bizBorrowMapper.getToBorrowBidding(map);
			if(0 < list.size()){
				//得到该借款的下一级分标
				BizBorrow borrow = list.get(0);
				//得到所有招标中的借款
				List<BizBorrow> borrows = bizBorrowMapper.getBorrowByBorStauts(Constant.BORROW_STATUS_2);
				Map<String, Object> borrowMap = MiscUtil.toMap(borrows);
				//判断需要进入招标中的借款是否有同样的父级
				//如果  有  则不进入招标中
				//如果 没有 则进入招标中
				if(null == borrowMap.get(borrow.getParentId())){
					if(Constant.BORROW_STATUS_1.equals(borrow.getBorStatus())){
						borrow.setBorStatus(Constant.BORROW_STATUS_2);
						borrow.setPublishTime(DateUtil.format(DateUtil.getSystemDate()));
						bizBorrowMapper.updateByPrimaryKeySelective(borrow);
						//进入招标中的借款生成虚拟的还款计划
						bizRepaymentPlanService.createForecastRepaymentPlan(borrow);
					}
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error("处理分标进入待招标操作异常:"+e.getMessage());
			}
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * e计划满标的利息特殊处理
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 */
	public void borrowPlanHandle(List<BizBorrowDetail> bids,BizBorrow bizBorrow,List<BizRepaymentPlan> repaymentPlans, List<BizReceiptPlan> receiptPlans){
		for (BizBorrowDetail bid : bids) {
			Integer days = MiscUtil.daysByTwoDate(DateUtil.format(bid.getInvestmentTime()), bizBorrow.getBorFullTime());
			if(null!=days&&days>0){
				//得到投标金额的天利息，然后乘以计息天数
				BigDecimal interest = MiscUtil.getBigDecimalMoney(AbstractCalculation.getMonthInterestDay(bid.getInvestmentAmount(), bizBorrow.getBorrowRate()).multiply(new BigDecimal(days)));
				//计算投标金额的加息利息，然后乘以计息天数
				BigDecimal investAward = MiscUtil.getBigDecimalMoney(AbstractCalculation.getMonthInterestDay(bid.getInvestmentAmount(), bid.getScale()).multiply(new BigDecimal(days)));
				//计算利息管理费
				BigDecimal managementCost = MiscUtil.getBigDecimalMoney(interest.multiply(bid.getManagerRate()));
				//计算加息利息管理费
				BigDecimal increaseInterest = MiscUtil.getBigDecimalMoney(investAward.multiply(bid.getManagerRate()));
				if(bizBorrow.getRepaymentType().equals("3")){
					//把利息加入最后一期还款计划
					if(0<repaymentPlans.size()){
						BizRepaymentPlan repayment = repaymentPlans.get(repaymentPlans.size()-1);
						repayment.setInterest(repayment.getInterest().add(interest));
						repayment.setManagementCost(repayment.getManagementCost().add(managementCost));
					}
					//设置最后一期收款计划的利息和利息管理费
					if(0<receiptPlans.size()){
						for (BizReceiptPlan receipt : receiptPlans) {
							if(receipt.getCustomerId().equals(bid.getCustomerId())&&receipt.getPlanIndex().equals(receiptPlans.size())){
								receipt.setReceivableHike(receipt.getReceivableHike().add(investAward).subtract(increaseInterest));//加息净利息
								receipt.setNetHike(receipt.getNetHike().add(investAward));//加息利息
								receipt.setReceivableInterest(receipt.getReceivableInterest().add(interest).subtract(managementCost));//利息净利息
								receipt.setNetInterest(receipt.getNetInterest().add(interest));//利息
								receipt.setInterest(receipt.getReceivableHike().add(receipt.getReceivableInterest()));//总净利息
								receipt.setManagementCost(receipt.getManagementCost().add(managementCost));//利息管理费
								receipt.setIncreaseInterest(receipt.getIncreaseInterest().add(increaseInterest));//加息利息管理费						
							}
						}
					}
				}else{
					//把利息加入第一期还款计划
					if(0<repaymentPlans.size()){
						BizRepaymentPlan repayment = repaymentPlans.get(0);
						repayment.setInterest(repayment.getInterest().add(interest));
						repayment.setManagementCost(repayment.getManagementCost().add(managementCost));
					}
					//设置第一期收款计划的利息和利息管理费
					if(0<receiptPlans.size()){
						for (BizReceiptPlan receipt : receiptPlans) {
							if(receipt.getCustomerId().equals(bid.getCustomerId())&&receipt.getPlanIndex().equals("1")){
								receipt.setReceivableHike(receipt.getReceivableHike().add(investAward).subtract(increaseInterest));//加息净利息
								receipt.setNetHike(receipt.getNetHike().add(investAward));//加息利息
								receipt.setReceivableInterest(receipt.getReceivableInterest().add(interest).subtract(managementCost));//利息净利息
								receipt.setNetInterest(receipt.getNetInterest().add(interest));//利息
								receipt.setInterest(receipt.getReceivableHike().add(receipt.getReceivableInterest()));//总净利息
								receipt.setManagementCost(receipt.getManagementCost().add(managementCost));//利息管理费
								receipt.setIncreaseInterest(receipt.getIncreaseInterest().add(increaseInterest));//加息利息管理费						
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 处理满标需要进行的操作（1.增加VIP经验值,2.增加客户投资积分）
	 * @author  Jie.Zou
	 * @date    2016年2月26日
	 * @version v1.0.0
	 */
	public void handleBorrowOther(List<BizBorrowDetail> bids,BizBorrow bizBorrow){
		Map<String, BizBorrowDetail> map = new HashMap<String, BizBorrowDetail>();
		try {
			List<CusTomer> userIds = new ArrayList<CusTomer>();
			// 循环投标记录，把每个人的投标金额汇总
			for (BizBorrowDetail bid : bids) {
				if (null != map.get(bid.getCustomerId())) {
					BizBorrowDetail bizBorrowDetail = map.get(bid.getCustomerId());
					bizBorrowDetail.setInvestmentAmount(bizBorrowDetail.getInvestmentAmount().add(bid.getInvestmentAmount()));
					map.put(bizBorrowDetail.getCustomerId(), bizBorrowDetail);
				} else {
					map.put(bid.getCustomerId(), bid);
				}
			}
			for (Entry<String, BizBorrowDetail> en : map.entrySet()) {
				BizBorrowDetail bid = en.getValue();
				//1.增加VIP经验值
				custExperienceService.updateUserExperience(bid.getInvestmentAmount(), Integer.parseInt(bizBorrow.getBorDeadline()) , bid.getCustomerId());
				//2.增加客户投资积分
				custPoinWaterService.pointObtain(Constant.POINT_TYPE_8, bid.getInvestmentAmount(), bid.getCustomerId());
				
				userIds.add(cusTomerService.selectByPrimaryKey(bid.getCustomerId()));
			}
			//3.vip等级
			cusTomerService.batchUpdateVipLeavel(userIds, null);
		}catch (NumberFormatException e) {
			if(logger.isDebugEnabled()){
				logger.error("处理满标的其他操作异常:"+e.getMessage());
			}
			e.printStackTrace();
		} catch (FrameworkException e) {
			if(logger.isDebugEnabled()){
				logger.error("处理满标的其他操作异常:"+e.getMessage());
			}
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * Description：<br>
	 * 得到到期的借款ID
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param time
	 * @return
	 */
	private List<BizBorrow> getExpireBorrowIds(Date time) {
		return bizBorrowMapper.selectExpireBorrowIds(time);
	}

	@Override
	public void checkBorrowExpire(Date time) {
		List<BizBorrow> bizBorrows = this.getExpireBorrowIds(time);
		int sumCount = bizBorrows.size();
		int succesCount = 0;
		int failCount = 0;
		try {
			for (BizBorrow bizBorrow : bizBorrows) {
				this.updateBorrowExpire(bizBorrow);// 修改借款状态
				this.unfrozenBids(bizBorrow);
				//拿取流标借款的下一个分标借款
				this.handleBorrowBidding(bizBorrow);
				succesCount++;
			}
		} catch (Exception e) {
			failCount++;
			logger.error(e.getMessage());
		}

		logger.info("总共处理流标标：" + sumCount + "个，成功处理：" + succesCount + "个，失败处理：" + failCount + "个");
	}

	/**
	 * 
	 * Description：<br>
	 * 修改借款流标状态
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param bizBorrow
	 */
	private void updateBorrowExpire(BizBorrow bizBorrow) {
		List<BizBorrowDetail> bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid());
		bizBorrow.setTenderCount(bids.size() + "");// 投标总数
		bizBorrow.setBorFullTime(DateUtil.getSystemDate());//设置流标时间（根据流标时间来判断）
		//判断是否为体验标
		if (bizBorrow.getBorrowType().equals(Constant.BORROW_TYPE_5)) {
			// 满标或流标时金额
			String status = (bids.size() > 0) ? Constant.BORROW_STATUS_5 : Constant.BORROW_STATUS_3;
			if (status.equals(Constant.BORROW_STATUS_5)) {
				bizBorrow.setBorStatus(Constant.BORROW_STATUS_5);
			} else if (status.equals(Constant.BORROW_STATUS_3)) {
				if(!Constant.BORROW_STATUS_0.equals(bizBorrow.getBorStatus())){
					bizBorrow.setBorStatus(Constant.BORROW_STATUS_3);
				}
			}
		} else {
			if(!Constant.BORROW_STATUS_0.equals(bizBorrow.getBorStatus())){
				bizBorrow.setBorStatus(Constant.BORROW_STATUS_3);
			}
			//流标,撤销删除虚拟的还款计划
			bizRepaymentPlanMapper.deleteBatch(bizBorrow.getPid(), bizBorrow.getCustomerId());
		}
		bizBorrowMapper.updateByPrimaryKeySelective(bizBorrow);
	}

	/**
	 * 
	 * Description：<br>
	 * 解冻借款投标 1.删除资金类别使用明细数据 2.解冻账户金额 3.改变投标状态及投标资金
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param bizBorrow
	 */
	private void unfrozenBids(BizBorrow bizBorrow) {
		List<BizBorrowDetail> bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid());
		updateUnfrozenBidderAccount(bids, bizBorrow);
		if (Constant.BORROW_TYPE_3.equals(bizBorrow.getBorrowType())) {
			bids = bizBorrowDetailService.selectBorrowDetailsByBorrowId(bizBorrow.getPid());
			this.planBorrowExpire(bids, bizBorrow);
		}
	}

	/**
	 * 
	 * Description：<br>
	 * 解冻投标资金账户资金
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param bids
	 * @param bizBorrow
	 */
	private void updateUnfrozenBidderAccount(List<BizBorrowDetail> bids, BizBorrow bizBorrow) {
		if (!bids.isEmpty()) {
			if (bizBorrow.getBorrowType().equals(Constant.BORROW_TYPE_5)) {
				if (bizBorrow.getStatus().equals(Constant.BORROW_STATUS_5)) {
					try {
						// 处理成功投资近的金额信息
						// 立即还款
						borrowSuccessWhenRepayment(bizBorrow);
						List<BizRepaymentPlan> list = bizRepaymentPlanService.selectRepaymentsByBorrowId(bizBorrow.getPid());
						for (BizRepaymentPlan bizRepaymentPlan : list) {
							bizRepaymentPlanService.executeRepayment(bizRepaymentPlan,"手动还款成功");
						}
						// 修改借款状态为已结清
						bizBorrow.setBorStatus(Constant.BORROW_STATUS_8);
						bizBorrowMapper.updateByPrimaryKeySelective(bizBorrow);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (bizBorrow.getStatus().equals(Constant.BORROW_STATUS_3)) {
					
				}
			} else {
				//加息卷ID集合
				List<String> investAwardIds = new ArrayList<String>();
				Map<String, BizBorrowDetail> map = new HashMap<String, BizBorrowDetail>();
				// 循环投标记录，把每个人的投标金额汇总，为生成一条资金流水做准备
				for (BizBorrowDetail bid : bids) {
					if (null != map.get(bid.getCustomerId())) {
						BizBorrowDetail bizBorrowDetail = map.get(bid.getCustomerId());
						bizBorrowDetail.setInvestmentAmount(bizBorrowDetail.getInvestmentAmount().add(bid.getInvestmentAmount()));
						map.put(bizBorrowDetail.getCustomerId(), bizBorrowDetail);
					} else {
						map.put(bid.getCustomerId(), bid);
					}
					if(StringUtils.hasText(bid.getInvestAwardId())){
						investAwardIds.add(bid.getInvestAwardId());
					}
					//投标记录变成无效
					//bid.setInvestmentAmount(BigDecimal.ZERO);
					//bizBorrowDetailService.updateByPrimaryKeySelective(bid);
				}
				for (Entry<String, BizBorrowDetail> en : map.entrySet()) {
					BizBorrowDetail bid = en.getValue();
					try {
						customerAccountService.updateFreezeAmountToAvailableAmount(bid.getInvestmentAmount(), bid.getCustomerId(), bizBorrow.getPid());
					} catch (FrameworkException e) {
						e.printStackTrace();
					}
				}
				if(investAwardIds.size()>0){
					//加息卷批量修改
					custInterestTicketMapper.updateUseStatusBatch("2",(String[])investAwardIds.toArray(new String[investAwardIds.size()]));
				}
			}
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * e计划流标和撤销都需要对投资人进行计算利息
	 * @author  Jie.Zou
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param bids
	 * @param bizBorrow
	 */
	public void planBorrowExpire(List<BizBorrowDetail> bids,BizBorrow bizBorrow){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> costMap = new HashMap<String, Object>();
		//e计划计算投资利息
		for (BizBorrowDetail bid : bids) {
			Integer days = MiscUtil.daysByTwoDate(DateUtil.format(bid.getInvestmentTime()), bizBorrow.getBorFullTime());
			if(null!=days&&days>0){
				BigDecimal interest = MiscUtil.getBigDecimalMoney(AbstractCalculation.getMonthInterestDay(bid.getInvestmentAmount(), bizBorrow.getBorrowRate()).multiply(new BigDecimal(days)));
				BigDecimal interestManage = MiscUtil.getBigDecimalMoney(interest.multiply(bid.getManagerRate()));
				//利息汇总
				if (null != map.get(bid.getCustomerId())) {
					BigDecimal amount = (BigDecimal)map.get(bid.getCustomerId());
					map.put(bid.getCustomerId(), amount.add(interest));
				} else {
					map.put(bid.getCustomerId(), interest);
				}
				//利息管理费汇总
				if (null != costMap.get(bid.getCustomerId())) {
					BigDecimal amount = (BigDecimal)costMap.get(bid.getCustomerId());
					costMap.put(bid.getCustomerId(), amount.add(interestManage));
				} else {
					costMap.put(bid.getCustomerId(), interestManage);
				}
			}
		}
		if(map.size()>0){
			try {
				BigDecimal interestSum = BigDecimal.ZERO;
				//生成还款计划
				BizRepaymentPlan repay = new BizRepaymentPlan();
				repay.setPid(repay.getPK());
				for (Entry<String, Object> en : map.entrySet()) {
					interestSum=interestSum.add((BigDecimal) en.getValue());
					//生成收款计划
					BizReceiptPlan receipt = new BizReceiptPlan();
					receipt.setPid(receipt.getPK());
					receipt.setCustomerId(en.getKey());
					receipt.setRepPlaPid(repay.getPid());
					receipt.setBorrowId(bizBorrow.getPid());
					receipt.setPlayType("1");
					receipt.setReceivableInterest(((BigDecimal)en.getValue()).subtract((BigDecimal)costMap.get(en.getKey())));
					receipt.setNetInterest((BigDecimal)en.getValue());
					receipt.setManagementCost((BigDecimal)costMap.get(en.getKey()));
					receipt.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_1);
					receipt.setExpireTime(DateUtil.format(DateUtil.getSystemDate()));
					receipt.setPlanIndex("1");
					receipt.setInterestType(bizBorrow.getAccrualType());
					receipt.setInterest(((BigDecimal)en.getValue()).subtract((BigDecimal)costMap.get(en.getKey())));
					receipt.setStatus(Constant.PUBLIC_STATUS);
					receipt.setCreateTime(DateUtil.getSystemDate());
					bizReceiptPlanMapper.insertSelective(receipt);
				}
				if(interestSum.compareTo(BigDecimal.ZERO)>0){
					repay.setCustomerId(bizBorrow.getCustomerId());
					repay.setExpireTime(DateUtil.format(DateUtil.getSystemDate()));
					repay.setPlanindex("1");
					repay.setInterest((BigDecimal)interestSum);
					repay.setInterestType(bizBorrow.getAccrualType());
					repay.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_1);
					repay.setStatus(Constant.PUBLIC_STATUS);
					repay.setCreateTime(DateUtil.getSystemDate());
					bizRepaymentPlanMapper.insertSelective(repay);
					//借款人支出利息和
					customerAccountService.updateDeductedAvailableAmount(interestSum, bizBorrow.getCustomerId(), bizBorrow.getPid(), TradeTypeConstant.OTHER_TYPE_512, "");
				}
				for (Entry<String, Object> en : costMap.entrySet()) {
					//用户扣除利息管理费
					customerAccountService.updateDeductedInterestManagementFee((BigDecimal)en.getValue(), en.getKey(), bizBorrow.getPid(), "");
					en.setValue(((BigDecimal)en.getValue()).negate());
				}
				//批量给用户增加利息和资金流水
				bizAccountCommonService.updateAmountByCustIds(map, Constant.CUST_FUND_BUSINESS_TYPE_1, TradeTypeConstant.RETURNS_TYPE_409, "", bizBorrow.getPid());
				
				//批量给用户扣除利息管理费和生成资金流水
				bizAccountCommonService.updateAmountByCustIds(costMap, Constant.CUST_FUND_BUSINESS_TYPE_2, TradeTypeConstant.OTHER_TYPE_502, "", bizBorrow.getPid());
			
				//所有操作处理完成后需要进行修改还款计划和收款计划状态
				//还款计划处理
				repay.setReceiptPalnStatus(Constant.BIZ_REPLAN_STATUS_4);
				repay.setRepaidTime(DateUtil.format(DateUtil.getSystemDate()));
				repay.setLastUpdateTime(DateUtil.getSystemDate());
				repay.setRepaidamount(interestSum);
				bizRepaymentPlanMapper.updateByPrimaryKey(repay);
				//收款计划处理
				List<BizReceiptPlan> receiptPlans = bizReceiptPlanMapper.findValidByRepaymentId(repay.getPid());
				for (BizReceiptPlan bizReceiptPlan : receiptPlans) {
					bizReceiptPlan.setReceiptStatus(Constant.BIZ_RECEIPTPLAN_STATUS_6);
					bizReceiptPlan.setRepaidTime(DateUtil.getSystemDate());
					bizReceiptPlan.setLastUpdateTime(DateUtil.getSystemDate());
					bizReceiptPlan.setRepaidInterest(bizReceiptPlan.getInterest());
					bizReceiptPlanMapper.updateByPrimaryKey(bizReceiptPlan);
				}
			} catch (FrameworkException e) {
				if(logger.isDebugEnabled()){
					logger.error("e计划流标计算利息:"+e.getMessage());
				}
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public BizBorrow formatBorrow(BizBorrow bizBorrow) {
		if (null != bizBorrow) {
			if (null != bizBorrow.getBorrowRate())
				bizBorrow.setBorrowRate(bizBorrow.getBorrowRate().multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
			if (null != bizBorrow.getBorrowProgress())
				bizBorrow.setBorrowProgress(bizBorrow.getBorrowProgress().multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP));
			else
				bizBorrow.setBorrowProgress(BigDecimal.ZERO);
		}
		return bizBorrow;
	}

	/**
	 * 
	 * @Description : API>>已还款列表
	 * @param userId
	 *            用户ID
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 客户已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午9:52:14
	 */
	@Override
	public List<BizBorrow> selectAlreadyRepaymentAPI(String userId, Integer pageIndex, Integer pageSize) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		List<BizBorrow> list = new ArrayList<BizBorrow>();
		try {
			// 创建查询条件Map
			Map<String, Object> map = new HashMap<String, Object>();
			// 赋值查询条件
			map.put("customerId", userId);
			map.put("pageIndex", pageIndex);
			map.put("pageSize", pageSize);
			list = mapper.selectAlreadyRepaymentAPI(map);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(":"+e.getMessage());
			}
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getNoSettleCount(String userId) {
		BizBorrowMapper mapper = (BizBorrowMapper) getDao();
		return mapper.getNoSettleCount(userId);
	}

	@Override
	public void revokeBorrow(BizBorrow borrow) {
		this.updateBorrowExpire(borrow);
		this.unfrozenBids(borrow);
	}

}
