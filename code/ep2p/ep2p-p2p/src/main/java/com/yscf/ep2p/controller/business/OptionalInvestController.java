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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizHouses;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowFileRelServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowInfoVOServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizHousesServiceImpl;
import com.yscf.core.service.finProManage.finProManageImpl.FinancialProductsManageServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * Description：<br>
 * 前台 e首房/e抵押 controller
 * 
 * @author shiliang.feng
 * @date 2015年11月23日
 * @version v1.0.0
 */
@Controller
@RequestMapping("business/optionalInvestController")
public class OptionalInvestController extends EscfBaseWebController {

	private Logger logger = LoggerFactory
			.getLogger(OptionalInvestController.class);

	@Autowired
	public OptionalInvestController(BizBorrowServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}

	// 理财产品服务service
	@Resource(name = "financialProductsManageServiceImpl")
	private FinancialProductsManageServiceImpl serviceImpl;
	// 借款VO业务类服务接口
	@Resource(name = "bizBorrowInfoVOService")
	private BizBorrowInfoVOServiceImpl borrowInfoVOServiceImpl;
	// 投资历史业务类服务接口
	@Resource(name = "bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl detailServiceImpl;
	// 借款服务
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl bizBorrowServiceImpl;
	// 客户服务实现
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	// 借款和文件关联
	@Resource(name = "bizBorrowFileRelService")
	private BizBorrowFileRelServiceImpl bizBorrowFileRelService;
	// 借款和文件关联
	@Resource(name = "bizHousesService")
	private BizHousesServiceImpl housesService;
	// vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	private SysVipinfoServiceImpl sysVipinfoServiceImpl;
	// 客户信息表
	@Resource(name = "customerAccountService")
	private CustomerAccountServiceImpl customerAccService;
	// 系统参数
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl paramsServiceImpl;

	/**
	 * 
	 * Description：<br>
	 * 跳转到 e首房/e抵押页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月12日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toOptionalInvestList")
	public String toOptionalInvestList() {
		return "toOptionalInvestList.page";
	}

	/**
	 * 
	 * Description：<br>
	 * 加载e首房/e抵押数据
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月23日
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
	@RequestMapping(value = "/index/selectOptionalInvestList")
	public void selectOptionalInvestList(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response,
			Integer pageIndex, Integer pageSize) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			if (borrow == null) {
				borrow = new BizBorrowInfoVO();
			}
			if (StringUtil.isBlank(borrow.getBorrowType())) {
				borrow.setBorrowType("-1");
			}
			// 查询已发布的列表 借款类型为e首付 或者是e抵押的 数据
			List<BizBorrowInfoVO> borrowList = serviceImpl
					.selectPublishedFinData(borrow, pageIndex * pageSize,
							pageSize, null, null);
			List<BizBorrowInfoVO> total = serviceImpl.selectPublishedFinData(
					borrow, null, null, null, null);
			tojson.put("borrowList", borrowList);
			tojson.put("total", total.size());
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("查询借款list失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 加载e首房/e抵押详情数据
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月1日
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
	@RequestMapping(value = "/index/selectOptionalInvestDataInfoData")
	public ModelAndView selectOptionalInvestDataInfoData(String borrowId,
			BigDecimal investNumber, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(
				"toOptionalInvestInfo.page");
		try {
			// 根据borrowId 查询e首房/e抵押介绍数据
			if (!StringUtil.isBlank(borrowId)) {
				borrowId = request.getParameter("borrowId");
			}
			// 没有登录 不查询 可用余额
			if (null != getUserId()) {
				CustomerAccount custAccount = null; // 客户账户信息
				List<SysVipinfo> vipList = sysVipinfoServiceImpl
						.selectVipLevelById(getContextUser().getPid());// 得到登录用户的vip等级
				if (vipList != null && vipList.size() > 0) {
					SysVipinfo vipInfo = vipList.get(0);
					modelAndView.addObject("discount", vipInfo.getDiscount()
							.multiply(new BigDecimal(100)));// 管理费率
				} else {
					// 非vip 利息管理费 取系统参数配置值 vip 取vip对应的利息管理费
					SysParams params = paramsServiceImpl
							.getParamsByParamKey("management_rate");
					modelAndView.addObject("discount", params.getParamValue());
				}
				custAccount = customerAccService.getByCusterId(getContextUser()
						.getPid());// 查询账号信息
				modelAndView.addObject(
						"availableAmount",
						custAccount == null ? 0 : custAccount
								.getAvailableAmount());// 可用余额
			}
			// 根据borrowId 查询实体
			BizBorrowInfoVO borrow = (BizBorrowInfoVO) serviceImpl
					.selectByPrimaryKey(borrowId);
			CusTomer cusTomer = new CusTomer();
			// 查询客户信息
			if (StringUtil.isBlank(borrow.getCustomerId())) {
				cusTomer = (CusTomer) cusTomerService.selectByPrimaryKey(borrow
						.getCustomerId());
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
			modelAndView.addObject("borrowId", borrowId);// 可获积分
			modelAndView.addObject("investNumber", investNumber);// 借款金额
		} catch (Exception e) {
			logger.error("查询借款实例失败", e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 确认投资页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月1日
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
	@RequestMapping(value = "/selectOptionalInvestDataInfoDataPreview")
	@ResponseBody
	public ModelAndView selectOptionalInvestDataInfoDataPreview(
			String borrowId, BigDecimal investNumber, String expectAmount,
			String investReward, String eplanIntegral,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(
				"mywtinvest/emortgage/optionalInvestPreview");
		try {
			// 没有登录 不查询 可用余额
			if (null != getUserId()) {
				CustomerAccount custAccount = null; // 客户账户信息
				List<SysVipinfo> vipList = sysVipinfoServiceImpl
						.selectVipLevelById(getContextUser().getPid());// 得到登录用户的vip等级
				if (vipList != null && vipList.size() > 0) {
					SysVipinfo vipInfo = vipList.get(0);
					modelAndView.addObject("discount", vipInfo.getDiscount()
							.multiply(new BigDecimal(100)));// 管理费率
				} else {
					// 非vip 利息管理费 取系统参数配置值 vip 取vip对应的利息管理费
					SysParams params = paramsServiceImpl
							.getParamsByParamKey("management_rate");
					modelAndView.addObject("discount", params.getParamValue());
				}
				custAccount = customerAccService.getByCusterId(getContextUser()
						.getPid());// 查询账号信息
				modelAndView.addObject(
						"availableAmount",
						custAccount == null ? 0 : custAccount
								.getAvailableAmount());// 可用余额
			}

			// 根据borrowId 查询e首房/e抵押介绍数据
			if (!StringUtil.isBlank(borrowId)) {
				borrowId = request.getParameter("borrowId");
			}
			// 根据borrowId 查询实体
			BizBorrowInfoVO borrow = (BizBorrowInfoVO) serviceImpl
					.selectByPrimaryKey(borrowId);
			CusTomer cusTomer = new CusTomer();
			// 查询客户信息
			if (StringUtil.isBlank(borrow.getCustomerId())) {
				cusTomer = (CusTomer) cusTomerService.selectByPrimaryKey(borrow
						.getCustomerId());
			}

			modelAndView.addObject("cusTomer", cusTomer);
			modelAndView.addObject("borrow", borrow);
			modelAndView.addObject("borrowId", borrowId);// 可获积分
			modelAndView.addObject("investNumber", investNumber);// 借款金额
			modelAndView.addObject("expectAmount", expectAmount);// 预期收益
			modelAndView.addObject("investReward", investReward);// 投资奖励
			modelAndView.addObject("eplanIntegral", eplanIntegral);// 可获积分
		} catch (Exception e) {
			logger.error("查询借款实例失败", e.getMessage());
			e.printStackTrace();
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 校验交易密码
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月4日
	 * @version v1.0.0
	 * @param borrowPassword
	 * @param borrowId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index/vailidateBorrowPassword", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView vailidateBorrowPassword(String borrowPassword,
			String borrowId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			if (StringUtil.isNotEmpty(borrowPassword)) {
				BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
						.selectByPrimaryKey(borrowId);
				if (!borrowPassword.equals(borrow.getBorrowPassword())) {
					modelView.addObject("returnCode", false);
				}
				modelView.addObject("returnCode", true);
			} else {
				modelView.addObject("returnCode", false);
			}
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return modelView;
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
		try {
			String pwd = request.getParameter("pwd");
			int num = cusTomerService.vailidateTradePassword(new String(pwd),
					userId);
			if (num > -1) {
				SysParams sp = paramsServiceImpl
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
			modelView.addObject("num", -1);
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
	 * 查询投资历史
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月23日
	 * @version v1.0.0
	 * @param borrow
	 * @param request
	 * @param response
	 * @param pageIndex
	 * @param pageSize
	 */
	@RequestMapping(value = "/index/selectInvestHisByBorrowId")
	@ResponseBody
	public ModelAndView selectInvestHisByBorrowId(String borrowId,
			HttpServletRequest request, HttpServletResponse response,
			Integer pageIndex, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<BizBorrowDetail> detailList = detailServiceImpl
					.selectBizBorrowDetailByBorrowId(borrowId, pageIndex
							* pageSize, pageSize);
			List<BizBorrowDetail> detailListSize = detailServiceImpl
					.selectBizBorrowDetailByBorrowId(borrowId, null, null);
			modelAndView.addObject("detailList", detailList);
			modelAndView.addObject("total", detailListSize == null ? 0
					: detailListSize.size());
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			logger.error("查询借款投资历史", e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 得到借款人基本信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月30日
	 * @version v1.0.0
	 * @param customerId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "index/getCustomerByPid")
	@ResponseBody
	public void getCustomerByPid(String customerId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			CusTomer cus = cusTomerService.selectByPrimaryKey(customerId);
			tojson.put("cusTomer", cus);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客户失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 根据borrowId 查询借款信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月30日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index/getBorrowInfo")
	@ResponseBody
	public void getBorrowInfo(String borrowId, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
					.selectByPrimaryKey(borrowId);
			tojson.put("borrow", borrow);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取借款信息失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 查询借款信息对应的房产信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月7日
	 * @version v1.0.0
	 * @param borrowId
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/index/getHousesByBorrowId")
	@ResponseBody
	public void getHousesByBorrowId(String borrowId,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 查询借款id对应的房产信息
			BizHouses houses = housesService.selectHousesByBorrowId(borrowId);
			BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
					.selectByPrimaryKey(borrowId);
			if (houses == null) {
				houses = new BizHouses();
			}
			if (borrow == null) {
				borrow = new BizBorrow();
			}
			tojson.put("borrow", borrow);
			tojson.put("houses", houses);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据借款id查询房产信息失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 根据借款id 查询用户上传的资料 区分类型
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月1日
	 * @version v1.0.0
	 * @param borrow
	 * @param request
	 * @param response
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/index/getBorrowFileRelByType")
	@ResponseBody
	public void getBorrowFileRelByType(String borrowId, String borFileRelType,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		BizBorrowFileRel borrow = new BizBorrowFileRel();
		borrow.setBorrowId(borrowId);
		borrow.setBorFileRelType(borFileRelType);
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			List<BizBorrowFileRel> list = bizBorrowFileRelService
					.selectAll(borrow);
			tojson.put("result", list);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据类型加载文件信息失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}
}
