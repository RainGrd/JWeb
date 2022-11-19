/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.business;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustInterestTicket;
import com.yscf.core.service.activity.impl.ActInvAwaActDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.core.service.business.impl.BizRepaymentPlanServiceImpl;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.content.impl.ContAdvContentServiceImpl;
import com.yscf.core.service.finProManage.finProManageImpl.FinancialProductsManageServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustPoinWaterServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br>
 * 前台 理财产品管理controller
 * 
 * @author shiliang.feng
 * @date 2015年11月12日
 * @version v1.0.0
 */
@Controller
@RequestMapping("business/financialProductsManageController")
public class FinancialProductsManageController extends EscfBaseWebController {

	private Logger logger = LoggerFactory
			.getLogger(FinancialProductsManageController.class);

	@Autowired
	public FinancialProductsManageController(BizBorrowServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrowInfoVO.class;
	}

	// 借款VO业务类服务接口
	@Resource(name = "financialProductsManageServiceImpl")
	private FinancialProductsManageServiceImpl serviceImpl;
	// 借款详情业务类服务接口
	@Resource(name = "bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl detailServiceImpl;
	// 理财产品介绍 服务实现
	@Resource(name = "bizFinanceProductsServiceImpl")
	private BizFinanceProductsServiceImpl bizFinanceProductsServiceImpl;
	// 客户服务实现
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	// 可获得积分
	@Resource(name = "custPoinWaterServiceImpl")
	private CustPoinWaterServiceImpl custPoinWaterServiceImpl;
	// 系统参数
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
	// 投资奖励获得明细
	@Resource(name = "actInvAwaActDetailServiceImpl")
	private ActInvAwaActDetailServiceImpl actDetailServiceImpl;
	// 借款服务
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl bizBorrowServiceImpl;
	// 客户信息表
	@Resource(name = "customerAccountService")
	private CustomerAccountServiceImpl customerAccService;
	// vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	private SysVipinfoServiceImpl sysVipinfoServiceImpl;
	// 还款计划服务
	@Resource(name = "bizRepaymentPlanService")
	private BizRepaymentPlanServiceImpl bizRepaymentPlanServiceImpl;
	// 广告位服务
	@Resource(name = "contAdvContentServiceImpl")
	private ContAdvContentServiceImpl contAdvContentServiceImpl;

	/**
	 * 
	 * Description：<br>
	 * 跳转到 理财产品管理页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月12日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toFinProdPage")
	public String toFinProdPage() {
		return "financialProductsManage.page";
	}

	/**
	 * 
	 * Description：<br>
	 * 加载理财产品数据
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月12日
	 * @version v1.0.0
	 * @param borrow
	 *            查询实体
	 * @param request
	 * @param response
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@RequestMapping(value = "/index/selectFinProdData")
	@ResponseBody
	public ModelAndView selectFinProdData(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response,
			Integer pageIndex, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (borrow == null) {
				borrow = new BizBorrowInfoVO();
			}
			borrow.setBorrowType("3");
			// 借款类型为e理财 并且是已发布的集合
			List<BizBorrowInfoVO> borrowList = serviceImpl
					.selectPublishedFinData(borrow, pageIndex * pageSize,
							pageSize, null, null);
			List<BizBorrowInfoVO> size = serviceImpl.selectPublishedFinData(
					borrow, null, null, null, null);
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
			modelAndView.addObject("borrowList", borrowList);
			modelAndView.addObject("total", size.size());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("查询借款list失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到 理财产品管理详情页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月12日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toFinProdInfoPage")
	public String toFinProdInfoPage() {
		return "financialProductsInfoManage.page";
	}

	/**
	 * 
	 * Description：<br>
	 * 加载理财产品详情数据
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @param pageTag
	 *            页面标示
	 * @param investNumber
	 *            借款金额
	 * @param expectAmount
	 *            预期收益
	 * @param investReward
	 *            投资奖励
	 * @param eplanIntegral
	 *            可获积分
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index/selectFinProdInfoData")
	public ModelAndView selectFinProdInfoData(String borrowId,
			BigDecimal investNumber, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView = new ModelAndView("financialProductsInfoManage.page");
		// 根据borrowId 查询理财产品介绍数据
		BizFinanceProducts product = bizFinanceProductsServiceImpl
				.getBizFinanceProductsByBorrowId(borrowId);
		modelAndView.addObject("product", product);
		try {
			// 根据borrowId 查询实体
			BizBorrowInfoVO borrow = (BizBorrowInfoVO) serviceImpl
					.selectByPrimaryKey(borrowId);
			CusTomer cusTomer = new CusTomer();
			// 查询客户信息
			if (StringUtil.isBlank(borrow.getCustomerId())) {
				cusTomer = (CusTomer) cusTomerService.selectByPrimaryKey(borrow
						.getCustomerId());
			}
			// 没有登录 不查询 可用余额
			if (null != getUserId()) {
				List<SysVipinfo> vipList = sysVipinfoServiceImpl
						.selectVipLevelById(getContextUser().getPid());// 得到登录用户的vip等级
				if (vipList != null && vipList.size() > 0) {
					SysVipinfo vipInfo = vipList.get(0);
					modelAndView.addObject("discount", vipInfo.getDiscount());// 管理费率
				} else {
					// 非vip 利息管理费 取系统参数配置值 vip 取vip对应的利息管理费
					SysParams params = sysParamsServiceImpl
							.getParamsByParamKey("management_rate");
					modelAndView.addObject("discount", params.getParamValue());
				}
				CustomerAccount custAccount = customerAccService
						.getByCusterId(getContextUser().getPid());// 查询账号信息
				if (StringUtil.isBlank(borrowId)) {
					borrowId = request.getParameter("borrowId");
				}
				modelAndView.addObject(
						"availableAmount",
						custAccount == null ? 0 : custAccount
								.getAvailableAmount());// 可用余额
			}
			// 　发布时间
			String publishTime = borrow.getPublishTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date pt = null;
			try {
				pt = sdf.parse(publishTime);
			} catch (ParseException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("e计划详情异常：" + e.getMessage());
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(pt);
			Date pe = c.getTime();
			c.add(Calendar.DATE, Integer.parseInt(borrow.getDeadline()));

			Long time = 0L;
			if (Constant.BORROW_STATUS_2.equals(borrow.getBorStatus())) {
				pe = c.getTime();
			} else {
				time = c.getTimeInMillis() - pe.getTime();
			}

			Long timeRemain = pe.getTime() - System.currentTimeMillis();
			modelAndView.addObject("timeRemain", timeRemain);
			modelAndView.addObject("time", time);

			modelAndView.addObject("cusTomer", cusTomer);
			modelAndView.addObject("borrow", borrow);
			modelAndView.addObject("borrowId", borrowId);
			modelAndView.addObject("investNumber", investNumber);// 借款金额
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("查询借款实例失败", e.getMessage());
				e.printStackTrace();
			}
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 确认投资页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param borrowId
	 * @param investNumber
	 * @param expectAmount
	 * @param investReward
	 * @param eplanIntegral
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectFinProdInfoDataPreview")
	@ResponseBody
	public ModelAndView selectFinProdInfoDataPreview(String borrowId,
			BigDecimal investNumber, String expectAmount, String investReward,
			String eplanIntegral, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView = new ModelAndView(
				"mywtinvest/eplan/financialProductInvPreview");
		try {
			// 根据borrowId 查询实体
			BizBorrowInfoVO borrow = (BizBorrowInfoVO) serviceImpl
					.selectByPrimaryKey(borrowId);
			// 没有登录 不查询 可用余额
			if (null != getUserId()) {
				List<SysVipinfo> vipList = sysVipinfoServiceImpl
						.selectVipLevelById(getContextUser().getPid());// 得到登录用户的vip等级
				if (vipList != null && vipList.size() > 0) {
					SysVipinfo vipInfo = vipList.get(0);
					modelAndView.addObject("discount", vipInfo.getDiscount()
							.multiply(new BigDecimal(100)));// 管理费率
				} else {
					// 非vip 利息管理费 取系统参数配置值 vip 取vip对应的利息管理费
					SysParams params = sysParamsServiceImpl
							.getParamsByParamKey("management_rate");
					modelAndView.addObject("discount", params.getParamValue());
				}
				CustomerAccount custAccount = customerAccService
						.getByCusterId(getContextUser().getPid());// 查询账号信息
				if (StringUtil.isBlank(borrowId)) {
					borrowId = request.getParameter("borrowId");
				}
				modelAndView.addObject(
						"availableAmount",
						custAccount == null ? 0 : custAccount
								.getAvailableAmount());// 可用余额
			}
			modelAndView.addObject("expectAmount", expectAmount);// 预期收益
			modelAndView.addObject("investReward", investReward);// 投资奖励
			modelAndView.addObject("eplanIntegral", eplanIntegral);// 可获积分
			modelAndView.addObject("borrow", borrow);
			modelAndView.addObject("borrowId", borrowId);
			modelAndView.addObject("investNumber", investNumber);
		} catch (Exception e) {
			logger.error("查询借款实例失败", e.getMessage());
			e.printStackTrace();
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询投标记录 根据借款id
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月26日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @param request
	 * @param response
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 */
	@RequestMapping(value = "/index/selectBizBorrowDetailByBorrowId")
	@ResponseBody
	public ModelAndView selectBizBorrowDetailByBorrowId(String borrowId,
			HttpServletRequest request, HttpServletResponse response,
			Integer pageIndex, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<BizBorrowDetail> detailList = detailServiceImpl
					.selectBizBorrowDetailByBorrowId(borrowId, pageIndex
							* pageSize, pageSize);
			Integer total = detailServiceImpl
					.selectBizBorrowDetailByBorrowIdCount(borrowId);
			modelAndView.addObject("detailList", detailList);
			modelAndView.addObject("total", total);
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("查询借款详情失败", e.getMessage());
				e.printStackTrace();
			}
		}
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存投资记录
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月18日
	 * @version v1.0.0
	 * @param borrow
	 * @param buyMoney
	 *            投资金额
	 * @param interestId
	 *            投资奖励id
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView save(BizBorrowDetail detail,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		String userId = getUserId();
		String pwd = request.getParameter("pwd");
		String borrowPassword = request.getParameter("borrowPassword");
		try {
			if (StringUtil.isNotEmpty(borrowPassword)
					&& !"undefined".equals(borrowPassword)) {
				BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
						.selectByPrimaryKey(detail.getBorrowId());
				if (!borrowPassword.equals(borrow.getBorrowPassword())) {
					modelView.addObject("retCode", false);
					modelView.addObject("errorCode", "招标密码错误");
					throw new RuntimeException("校验招标密码错误！");
				}
			}
			int num = cusTomerService.vailidateTradePassword(new String(pwd),
					userId);
			if (num > -1) {
				SysParams sp = sysParamsServiceImpl
						.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n = null;
				try {
					n = Integer.parseInt(sp.getParamValue());
				} catch (Exception e) {
				}
				n = n == null ? 4 : n;
				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n - num);
				modelView.addObject("t", -1);
				modelView.addObject("retCode", false);
				modelView.addObject("retMessage", "交易密码校验失败!");
				throw new RuntimeException("校验交易密码失败！");
			}
			// 保存投资信息
			HashMap<String, Object> result = detailServiceImpl.investmentAuto(
					detail, userId, "1");
			modelView.addObject("detail", detail);
			modelView.addObject("num", -1);
			modelView.addObject("t", -1);
			modelView.addObject("errorCode", "");
			modelView.addObject("retCode", result.get("result"));
			modelView.addObject("retMessage", result.get("msg"));
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (TradePwdIsBlankException te) {
			// 交易密码为空
			modelView.addObject("num", -1);
			modelView.addObject("t", -1);
			modelView.addObject("retCode", false);
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			modelView.addObject("retMessage", "用户交易密码未设置");
			MessageBuilder.processError(modelView, te, request);
		} catch (TradePwdFreezeException te) {
			// 用户被冻结,获取剩余冻结分钟
			Date time = cusTomerService.getTradeFreezeTime(userId);
			int t = 60;
			if (time != null) {
				Long l = time.getTime() - System.currentTimeMillis();
				if (l > 0) {
					t = (int) (l / (1000 * 60));
				} else {
					t = 0;
				}
			}
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t", t);
			modelView.addObject("retCode", false);
			modelView.addObject("retMessage", "交易密码冻结,请稍后再试!");
			MessageBuilder.processError(modelView, te, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("保存投资信息失败", e.getMessage());
				e.printStackTrace();
				modelView.addObject("retCode", false);
				modelView.addObject("retMessage", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询投资奖励 可获得积分
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param pointGetType
	 *            积分类型
	 * @param investmentMoney
	 *            投资金额
	 * @param customerId
	 *            客户id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index/getEplanIntegral")
	@ResponseBody
	public void getEplanIntegral(String pointGetType,
			BigDecimal investmentMoney, String customerId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		Integer eplanIntegral = custPoinWaterServiceImpl.pointObtainTemp(
				investmentMoney, getUserId());

		tojson.put("eplanIntegral", eplanIntegral);
		outputJson(tojson, response);
	}

	/**
	 * Description：<br>
	 * 得到 投资奖励 和 预期收益
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index/getInvestReward")
	@ResponseBody
	public ModelAndView getInvestReward(CalculationDto calculationDto,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		// 对于借款人来说 管理费率 是0
		BigDecimal managementRate = BigDecimal.ZERO;
		calculationDto.setManagementRate(managementRate);
		// 计息时间 为当前时间
		calculationDto.setInterestDate(DateUtil.format(new Date()));
		ICalculation cal = CalculationFactory.getCalculation(calculationDto);
		if (cal == null) {
			modelAndView.addObject("retCode", "500");
			modelAndView.addObject("retMessage", "查询失败");
			return modelAndView;
		}

		if (calculationDto.getRepaymentAmt() != null) {
			modelAndView.addObject("eplanIntegral", cal.getSumInterest());// 获得预期收益
			modelAndView.addObject("investmentReward",
					cal.getInvestmentReward());// 获得投资奖励
		}
		try {
			modelAndView.addObject("retCode", "200");
			modelAndView.addObject("retMessage", "查询成功");
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询还款计划失败", e.getMessage());
				e.printStackTrace();
				modelAndView.addObject("retCode", "500");
				modelAndView.addObject("retMessage", e.getMessage());
			}
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 根据borrowId 查询还款计划
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param customerId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index/selectRepaymentsByBorrowId")
	@ResponseBody
	public void selectRepaymentsByBorrowId(String borrowId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		// 还款计划
		List<BizRepaymentPlan> planList = bizRepaymentPlanServiceImpl
				.selectRepaymentsByBorrowId(borrowId);
		tojson.put("planList", planList);
		outputJson(tojson, response);

	}

	/**
	 * 
	 * Description：<br>
	 * 根据客户id 查询客户拥有的加息券
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param customerId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index/selectAllPageByTypeId")
	@ResponseBody
	public void selectAllPageByTypeId(String customerId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		// 查询客户拥有的 加息券
		List<CustInterestTicket> actdetailList = cusTomerService
				.selectUserInterestTicketAPI(getUserId(), 0, 100);
		tojson.put("actdetailList", actdetailList);
		outputJson(tojson, response);

	}

	/**
	 * Description：<br>
	 * 登录用户 对一个标的的投资总额
	 * 
	 * @author shiliang.feng
	 * @date 2016年2月2日
	 * @version v1.0.0
	 * @param borrowId
	 *            标的id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index/getInvestTotalByBorrowId")
	@ResponseBody
	public ModelAndView getInvestTotalByBorrowId(String borrowId,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (!StringUtil.isBlank(borrowId)
					&& !StringUtil.isBlank(getUserId())) {
				BigDecimal investTotal = detailServiceImpl
						.getInvestTotalByBorrowId(borrowId, getUserId());
				modelAndView.addObject("investTotal", investTotal);// 该用户对该标的的投资总额
				MessageBuilder.processSuccess(modelAndView, null,
						HttpMessage.SUCCESS_MSG, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 校验招标密码
	 * 
	 * @author shiliang.feng
	 * @date 2016年2月17日
	 * @version v1.0.0
	 * @param borrowId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/vailidateBorrowPassword")
	@ResponseBody
	public ModelAndView vailidateBorrowPassword(String borrowPassword,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (!StringUtil.isBlank(borrowPassword)
					&& !StringUtil.isBlank(getUserId())) {
				int num = detailServiceImpl.vailidateBorrowPassword(
						borrowPassword, getUserId());
				modelAndView.addObject("returnMsg", num > 0 ? true : false);//
				MessageBuilder.processSuccess(modelAndView, null,
						HttpMessage.SUCCESS_MSG, request);
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
	/**
	 * Description：<br> 
	 * 根据web 加载广告
	 * @author  shiliang.feng
	 * @date    2016年3月11日
	 * @version v1.0.0
	 * @param webTag
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "index/initAdvBanner")
	@ResponseBody
	public ModelAndView initAdvBanner(String webTag,
			HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<ContAdvContent> advList = contAdvContentServiceImpl
					.selectAdvContentByWebTag(webTag);
			modelAndView.addObject("advList", advList);
			MessageBuilder.processSuccess(modelAndView, null,
						HttpMessage.SUCCESS_MSG, request); 
		} catch (Exception e) {
			e.printStackTrace();
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}
}
