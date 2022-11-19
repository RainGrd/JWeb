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
 * 2015年11月10日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.LogConstant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.core.exception.AvailableAmountExcepiton;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;
import com.yscf.core.model.business.RepaymentDto;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.IRepaymentService;
import com.yscf.core.service.business.impl.BizBorrowInfoVOServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizRepaymentPlanServiceImpl;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.util.MiscUtil;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * @ClassName : MyLoanController
 * @Description : 我的借款Controller
 * @Author : Qing.Cai
 * @Date : 2015年11月20日 上午11:05:11
 */
@Controller
@RequestMapping("business/myLoanController")
public class MyLoanController extends EscfBaseWebController {

	private Logger logger = LoggerFactory.getLogger(MyLoanController.class);

	@Autowired
	public MyLoanController(BizBorrowInfoVOServiceImpl service) {
		super(service);

	}

	/** 还款计划服务接口 */
	@Resource(name = "bizRepaymentPlanService")
	private BizRepaymentPlanServiceImpl bizRepaymentPlanService;
	/** 借款服务接口 */
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl bizBorrowServiceImpl;

	// vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	SysVipinfoServiceImpl sysVipinfoService;

	@Resource
	private ICustomerAccountService customerAccountService;

	@Resource(name = "repaymentService")
	private IRepaymentService repaymentService; // 客户信息

	/** 客户管理服务接口 */
	@Resource
	private ICusTomerService customerService;

	/** 系统参数服务接口 */
	@Resource
	private ISysParamsService sysParamsService;

	@Override
	public Class<?> getModel() {
		return BizBorrowInfoVO.class;
	}

	/**
	 * 
	 * @Description : 跳转到我的借款页面
	 * @return myLoanList.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:07:27
	 */
	@RequestMapping("/toBorrowList")
	public String toBorrowList() {
		HttpServletRequest req = getRequest();
		// 添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_BORROW);
		// 查vip等级
		List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(getUserId());
		if (null != list && list.size() > 0) {
			String vipLevel = list.get(0).getVipLevel();
			if (null != vipLevel || !"".equals(vipLevel)) {
				String vips = "VIP" + vipLevel;
				if (!"".equals(vipLevel) && vipLevel != null) {
					req.setAttribute("vipLevel", vips);
				} else {
					req.setAttribute("vipLevel", "VIP");
				}
			} else {
				req.setAttribute("vipLevel", "VIP");
			}
		}
		return "temp.usercenter.borrow";
	}

	/**
	 * 
	 * @Description : 前台_我的借款_待还款
	 * @param bizRepaymentPlan
	 *            还款计划javaBean
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            页数
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 我的借款待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:15:10
	 */
	@RequestMapping(value = "/selectPendingRepayment")
	@ResponseBody
	public ModelAndView selectPendingRepayment(BizRepaymentPlan bizRepaymentPlan, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			bizRepaymentPlan.setCustomerId(getUserId());// 设置客户ID
			// 初始化分页对象
			pageIndex = pageIndex == null ? 0 : pageIndex;
			pageSize = pageSize == null ? 10 : pageSize;
			pageIndex = pageIndex * pageSize;

			List<BizRepaymentPlan> list = bizRepaymentPlanService.selectPendingRepayment(bizRepaymentPlan, pageIndex, pageSize);
			Integer total = bizRepaymentPlanService.selectPendingRepaymentCount(bizRepaymentPlan);
			total = total == null ? 0 : total;
			modelView.addObject("data", list);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的借款待还款：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_已还款
	 * @param bizRepaymentPlan
	 *            还款计划javaBean
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            页数
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 我的借款已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:42:32
	 */
	@RequestMapping(value = "/selectRepayment")
	@ResponseBody
	public ModelAndView selectRepayment(BizRepaymentPlan bizRepaymentPlan, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			bizRepaymentPlan.setCustomerId(getUserId());// 设置客户ID
			// 初始化分页对象
			pageIndex = pageIndex == null ? 0 : pageIndex;
			pageSize = pageSize == null ? 10 : pageSize;
			pageIndex = pageIndex * pageSize;

			List<BizRepaymentPlan> list = bizRepaymentPlanService.selectRepayment(bizRepaymentPlan, pageIndex, pageSize);
			Integer total = bizRepaymentPlanService.selectRepaymentCount(bizRepaymentPlan);
			total = total == null ? 0 : total;
			modelView.addObject("data", list);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的借款已还款：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_招标中
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            页数
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 我的借款招标中
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:52:24
	 */
	@RequestMapping(value = "/selectReceptionTender")
	@ResponseBody
	public ModelAndView selectReceptionTender(BizBorrowInfoVO bizBorrowInfoVO, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			bizBorrowInfoVO.setCustomerId(getUserId());// 设置客户ID
			IBizBorrowInfoVOService service = (IBizBorrowInfoVOService) getService();

			// 初始化分页对象
			pageIndex = pageIndex == null ? 0 : pageIndex;
			pageSize = pageSize == null ? 10 : pageSize;
			pageIndex = pageIndex * pageSize;

			List<BizBorrowInfoVO> list = service.selectReceptionTender(bizBorrowInfoVO, pageIndex, pageSize);
			Integer total = service.selectReceptionTenderCount(bizBorrowInfoVO);
			total = total == null ? 0 : total;
			modelView.addObject("data", list);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的借款招标中：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 前台_我的借款_申请进度
	 * @param bizBorrowInfoVO
	 *            借款信息VO
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            页数
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 我的借款申请进度
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:53:12
	 */
	@RequestMapping(value = "/selectReceptionApplication")
	@ResponseBody
	public ModelAndView selectReceptionApplication(BizBorrowInfoVO bizBorrowInfoVO, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			bizBorrowInfoVO.setCustomerId(getUserId());// 设置客户ID
			IBizBorrowInfoVOService service = (IBizBorrowInfoVOService) getService();

			// 初始化分页对象
			pageIndex = pageIndex == null ? 0 : pageIndex;
			pageSize = pageSize == null ? 10 : pageSize;
			pageIndex = pageIndex * pageSize;

			List<BizBorrowInfoVO> list = service.selectReceptionApplication(bizBorrowInfoVO, pageIndex, pageSize);
			Integer total = service.selectReceptionApplicationCount(bizBorrowInfoVO);
			total = total == null ? 0 : total;
			modelView.addObject("data", list);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的借款申请进度：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 删除我的借款_申请进度的数据
	 * @param pid
	 *            主键ID
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 是否成功
	 * @Author : Qing.Cai
	 * @Date : 2016年1月5日 下午4:48:18
	 */
	@RequestMapping(value = "/deleteApplicationProgress")
	@ResponseBody
	public ModelAndView deleteApplicationProgress(String pid, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			bizBorrowServiceImpl.deleteByPrimaryKey(pid);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("删除我的借款_申请进度的数据：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到待还款详情页面
	 * 
	 * @author Jie.Zou
	 * @date 2016年2月24日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toPRepayBorrowInfo")
	public ModelAndView toPRepayBorrowInfo(String pid, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView("/personcenter/myborrowing/loanBorrowPRepayInfo");
		try {
			IBizBorrowInfoVOService service = (IBizBorrowInfoVOService) getService();
			// 待还款借款信息
			BizBorrowInfoVO borrow = service.getBizBorrowById(pid);
			// 待还款计划列表
			List<BizRepaymentPlan> repays = bizRepaymentPlanService.selectRepaymentsByBorrowId(pid);
			BigDecimal availableAmount = customerAccountService.getUserAvailableAmountExculdExperience(borrow.getCustomerId());
			RepaymentDto dto = repaymentService.getPrepaymentInfo(pid, DateUtil.format(DateUtil.getSystemDate()));
			BizRepaymentPlanInfoVo repayInfoVo = new BizRepaymentPlanInfoVo();
			StringBuffer repayIndex = new StringBuffer();
			StringBuffer repayDesc = new StringBuffer();
			repayDesc.append("（含：");
			// 得到逾期还款计划
			List<BizRepaymentPlanInfoVo> list = repaymentService.getOverdueRepayments(pid);
			if(list.size() >0 ){
				for (int i = 0; i < list.size(); i++) {
					BizRepaymentPlanInfoVo plan = list.get(i);
					if(i==list.size()-1){
						repayIndex.append(plan.getCurrentPlanindex()+"/"+plan.getMaxPlanindex());
						repayDesc.append(plan.getCurrentPlanindex()+"/"+plan.getMaxPlanindex()+"期还款金额<i>￥"+MiscUtil.getBigDecimalMoney((plan.getCapital().add(plan.getInterest()).add(plan.getManagementCost()).add(plan.getLatefee())))+"</i>");
					}else{
						repayIndex.append(plan.getCurrentPlanindex()+"/"+plan.getMaxPlanindex()+",");
						repayDesc.append(plan.getCurrentPlanindex()+"/"+plan.getMaxPlanindex()+"期还款金额<i>￥"+MiscUtil.getBigDecimalMoney((plan.getCapital().add(plan.getInterest()).add(plan.getManagementCost()).add(plan.getLatefee())))+"</i>,");
					}
				}
				repayInfoVo = repaymentService.getOverdueRepayBorrow(pid);
			}else{
				repayInfoVo = repaymentService.getRepayInfoByTime(pid);
				if(null!=repayInfoVo){
					repayIndex.append(repayInfoVo.getCurrentPlanindex()+"/"+repayInfoVo.getMaxPlanindex());
					repayDesc.append(repayInfoVo.getCurrentPlanindex()+"/"+repayInfoVo.getMaxPlanindex()+"期还款金额<i>￥"+MiscUtil.getBigDecimalMoney((repayInfoVo.getCapital().add(repayInfoVo.getInterest()).add(repayInfoVo.getManagementCost()).add(repayInfoVo.getLatefee())))+"</i>");
				}
			}
			repayDesc.append("）");
			modelView.addObject("repayDesc", repayDesc);
			modelView.addObject("repayIndex", repayIndex);
			modelView.addObject("repayInfoVo", repayInfoVo);
			modelView.addObject("repaymentDto", dto);
			modelView.addObject("availableAmount", availableAmount);
			modelView.addObject("borrow", borrow);
			modelView.addObject("repays", repays);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("我的借款_待还款借款详情错误：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到已完结还款详情
	 * @author  Jie.Zou
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toERepayBorrowInfo")
	public ModelAndView toERepayBorrowInfo(String pid, HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelView = new ModelAndView("/personcenter/myborrowing/loanBorrowERepayInfo");
		try {
			IBizBorrowInfoVOService service = (IBizBorrowInfoVOService) getService();
			//借款信息
			BizBorrowInfoVO borrow = service.getBizBorrowById(pid);
			//还款计划信息
			List<BizRepaymentPlan> repays = bizRepaymentPlanService.selectRepaymentsByBorrowId(pid);
			modelView.addObject("borrow", borrow);
			modelView.addObject("repays",repays);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("我的借款_已结清借款详情错误：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 跳转到提前还款确认页面
	 * @return myLoanList.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:07:27
	 */
	@RequestMapping("/prerepayment")
	public ModelAndView prerepayment(String borrowId, String tradePassword, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		// 获取用户登录ID
		String userId = getUserId();
		try {
			// 校验密码是否正确
			int num = customerService.vailidateTradePassword(tradePassword, userId);
			if (num > -1) {
				// 获取交易密码可错误次数
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n = null;
				try {
					n = Integer.parseInt(sp.getParamValue());
				} catch (Exception e) {
					// 无效
				}
				n = n == null ? 4 : n;

				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n - num);
				throw new RuntimeException("校验交易密码失败！");
			}

			// 调用方法业务处理
			repaymentService.prepayment(borrowId, userId);
			// 添加日志
			addLog(request, LogConstant.LOG_OPER_TYPE_172, "提前还款成功");
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (AvailableAmountExcepiton e) {
			// 余额不够
			modelView.addObject("errorCode", "availableAmountError");
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e) {
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t = null;
			try {
				t = Integer.parseInt(sp.getParamValue());
			} catch (Exception te) {

			}
			t = t == null ? 60 : t;
			if (time != null) {
				Long l = time.getTime() - System.currentTimeMillis();
				if (l > 0) {
					t = (int) (l / (1000 * 60));
				} else {
					t = 0;
				}
			}
			// 交易密码冻结
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t", t);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdIsBlankException e) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, e, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("提前还款：", e.getMessage());
			}
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	@RequestMapping("/payrepayment")
	public ModelAndView payrepayment(String borrowId, String tradePassword, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		// 获取用户登录ID
		String userId = getUserId();
		try {
			// 校验密码是否正确
			int num = customerService.vailidateTradePassword(tradePassword, userId);
			if (num > -1) {
				// 获取交易密码可错误次数
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n = null;
				try {
					n = Integer.parseInt(sp.getParamValue());
				} catch (Exception e) {
					// 无效
				}
				n = n == null ? 4 : n;

				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n - num);
				MessageBuilder.processError(modelView, new RuntimeException("校验交易密码失败！"), request);
				throw new RuntimeException("校验交易密码失败！");
			}
			// 还款成功信息
			BizRepaymentPlanInfoVo planVo = new BizRepaymentPlanInfoVo();
			List<BizRepaymentPlan> list = repaymentService.selectOverdueRepayment(borrowId);
			BigDecimal availableAmount = customerAccountService.getUserAvailableAmountExculdExperience(userId);
			BigDecimal repayAmount = BigDecimal.ZERO;
			if (list.size() > 0) {
				BizRepaymentPlanInfoVo repayInfoVo = repaymentService.getOverdueRepayBorrow(borrowId);
				repayAmount = repayInfoVo.getCapital().add(repayInfoVo.getInterest()).add(repayInfoVo.getLatefee()).add(repayInfoVo.getManagementCost());
				if(availableAmount.compareTo(repayAmount)>=0){
					for (BizRepaymentPlan bizRepaymentPlan : list) {
						if (bizRepaymentPlan.getReceiptPalnStatus().equals(Constant.BIZ_REPLAN_STATUS_3)) {
							bizRepaymentPlanService.executeRepaymentToSystem(bizRepaymentPlan, "手动立即还款");
							planVo.setCapital(planVo.getCapital().add(bizRepaymentPlan.getCapital() == null ? BigDecimal.ZERO : bizRepaymentPlan.getCapital()));
							planVo.setInterest(planVo.getInterest().add(bizRepaymentPlan.getInterest() == null ? BigDecimal.ZERO : bizRepaymentPlan.getInterest()));
							planVo.setManagementCost(planVo.getManagementCost().add(bizRepaymentPlan.getManagementCost() == null ? BigDecimal.ZERO : bizRepaymentPlan.getManagementCost()));
							planVo.setLatefee(planVo.getLatefee().add(bizRepaymentPlan.getLatefee() == null ? BigDecimal.ZERO : bizRepaymentPlan.getLatefee()));
							planVo.setRepaidTime(DateUtil.format(DateUtil.getSystemDate()));
						} else if (bizRepaymentPlan.getReceiptPalnStatus().equals(Constant.BIZ_REPLAN_STATUS_2)) {
							bizRepaymentPlanService.executeRepayment(bizRepaymentPlan, "手动立即还款");
							planVo.setCapital(planVo.getCapital().add(bizRepaymentPlan.getCapital() == null ? BigDecimal.ZERO : bizRepaymentPlan.getCapital()));
							planVo.setInterest(planVo.getInterest().add(bizRepaymentPlan.getInterest() == null ? BigDecimal.ZERO : bizRepaymentPlan.getInterest()));
							planVo.setManagementCost(planVo.getManagementCost().add(bizRepaymentPlan.getManagementCost() == null ? BigDecimal.ZERO : bizRepaymentPlan.getManagementCost()));
							planVo.setLatefee(planVo.getLatefee().add(bizRepaymentPlan.getLatefee() == null ? BigDecimal.ZERO : bizRepaymentPlan.getLatefee()));
							planVo.setRepaidTime(DateUtil.format(DateUtil.getSystemDate()));
						}
					}
				}else{
					throw new AvailableAmountExcepiton("还款账户余额不足！");
				}
			} else {
				// 如果没有逾期还款计划，则得到当期还款计划
				BizRepaymentPlan plan = repaymentService.selectRepayByTime(borrowId);
				if (null != plan) {
					// 如果当期已还款则不处理
					if (plan.getReceiptPalnStatus().equals(Constant.BIZ_REPLAN_STATUS_1)) {
						repayAmount = (plan.getCapital() == null ? BigDecimal.ZERO : plan.getCapital()).add((plan.getInterest() == null ? BigDecimal.ZERO : plan.getInterest()))
								.add((plan.getLatefee() == null ? BigDecimal.ZERO : plan.getLatefee())).add((plan.getManagementCost() == null ? BigDecimal.ZERO : plan.getManagementCost()));
						if(availableAmount.compareTo(repayAmount)>=0){
							bizRepaymentPlanService.executeRepayment(plan, "手动立即还款");
							planVo.setCapital(planVo.getCapital().add(plan.getCapital() == null ? BigDecimal.ZERO : plan.getCapital()));
							planVo.setInterest(planVo.getInterest().add(plan.getInterest() == null ? BigDecimal.ZERO : plan.getInterest()));
							planVo.setManagementCost(planVo.getManagementCost().add(plan.getManagementCost() == null ? BigDecimal.ZERO : plan.getManagementCost()));
							planVo.setLatefee(planVo.getLatefee().add(plan.getLatefee() == null ? BigDecimal.ZERO : plan.getLatefee()));
							planVo.setRepaidTime(DateUtil.format(DateUtil.getSystemDate()));
						}else{
							throw new AvailableAmountExcepiton("还款账户余额不足！");
						}
					}
				}
			}
			addLog(request, LogConstant.LOG_OPER_TYPE_26, "立即还款成功");
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			e.printStackTrace();
		} catch (AvailableAmountExcepiton e) {
			// 余额不够
			modelView.addObject("errorCode", "availableAmountError");
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e) {
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t = null;
			try {
				t = Integer.parseInt(sp.getParamValue());
			} catch (Exception te) {

			}
			t = t == null ? 60 : t;
			if (time != null) {
				Long l = time.getTime() - System.currentTimeMillis();
				if (l > 0) {
					t = (int) (l / (1000 * 60));
				} else {
					t = 0;
				}
			}
			// 交易密码冻结
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t", t);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdIsBlankException e) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

}
