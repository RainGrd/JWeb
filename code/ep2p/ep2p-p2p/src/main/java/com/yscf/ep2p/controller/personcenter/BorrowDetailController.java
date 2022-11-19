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
 * 2015年11月11日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.personcenter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.ptp.financial.BizReceiptPlanModel;
import com.yscf.core.model.ptp.investment.InvestmentInfoModel;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.impl.BizReceiptPlanServiceImpl;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.ISysVipinfoService;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.service.userinfo.IUserCenterService;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br>
 * 项目明细列表
 * 
 * @author JunJie.Liu
 * @date 2015年11月11日
 * @version v1.0.0
 */
@Controller
@RequestMapping("usercenter/borrowDetailController")
public class BorrowDetailController extends EscfBaseWebController {

	private Logger logger = LoggerFactory
			.getLogger(BorrowDetailController.class);

	@Autowired
	public BorrowDetailController(BizReceiptPlanServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizReceiptPlan.class;
	}

	@Resource
	private IBizBorrowDetailService bizBorrowDetailService;

	@Resource
	private IBizBorrowService bizBorrowService;

	@Resource
	private ISysVipinfoService sysVipinfoService;

	@Resource
	private ICusTomerService customerService;

	@Resource
	private ISysParamsService sysParamsService;
	
	@Resource
	private IBizReceiptTransferService bizReceiptTransferService;
	
	@Resource
	private IUserCenterService userCenterService;

	/**
	 * 
	 * Description：<br>
	 * 跳转到项目明细列表
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toReceiptPlanList")
	public String toReceiptPlanList(String borrowId) {
		HttpServletRequest req = getRequest();

		// 添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU,
				PtpConstants.USERCENTER_MENU_INVEST);
		req.setAttribute("borrowId", borrowId);
		return "temp.usercenter.invest.pro.info";
	}

	/**
	 * 
	 * Description：<br>
	 * 项目明细列表取数据
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findReceiptPlanListData")
	@ResponseBody
	public ModelAndView findReceiptPlanListData(BizReceiptPlanModel condition,
			Integer pageIndex, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			String userId = getUserId();
			if (condition != null) {
				condition.setCustomerId(userId);
			}
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlanModel> vos = bizReceiptPlanService.findList(
					condition, pageIndex, pageSize);
			Integer total = bizReceiptPlanService.findTotalCount(condition);
			total = total == null ? 0 : total;
			modelView.addObject("isVip", customerService.isVip(userId));
			modelView.addObject("vos", vos);
			modelView.addObject("total", total);

			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询项目明细异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询用户转让详情
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findReceiptPlanById")
	public ModelAndView findReceiptPlanById(String borrowId, String transferId,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView(
				"temp.usercenter.invest.pro.info.transferinfo");
		try {
			String userId = getUserId();
			if (!StringUtils.hasLength(borrowId)) {
				throw new NullArgumentException("标的id为null");
			}

			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			if (StringUtils.hasLength(transferId)) {
				// 表示再次债权转让
				brpList = bizReceiptPlanService.findList(userId, borrowId, Constant.INVEST_SRC_TYPE_2,
						transferId);
			} else {
				// 表示原标的转让
				brpList = bizReceiptPlanService.findList(userId, borrowId,Constant.INVEST_SRC_TYPE_1
						,null);

			}

			// 投资本金
			BigDecimal investCapital = BigDecimal.ZERO;
			// 已收本息
			BigDecimal alreadyBenxi = BigDecimal.ZERO;
			// 剩余本金
			BigDecimal surperCapital = BigDecimal.ZERO;
			// 当期全部利息
			BigDecimal currentAllInterest = BigDecimal.ZERO;
			// 当期至今利息
			BigDecimal currentInterest = BigDecimal.ZERO;
			// 剩余期次
			int surperDead = 0;
			// 总期次
			int totalDead = 0;
			// 当期待收时间
			String currentExpireTime = "";
			// 上一期待收时间
			String lastExpireTime = "";

			if (brpList != null) {
				totalDead = brpList.size();
				for (int i = 0; i < brpList.size(); i++) {
					BizReceiptPlan brp = brpList.get(i);
					BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO
							: brp.getCapital();
					BigDecimal netInterest = brp.getNetInterest() == null ? BigDecimal.ZERO
							: brp.getNetInterest();

					investCapital = investCapital.add(capital);
					if (Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(brp.getReceiptStatus())) {
						// 待收中
						surperCapital = surperCapital.add(capital);

						if (surperDead == 0) {
							// 第一次循环到待收中表示当期
							currentAllInterest = netInterest;

							currentExpireTime = brp.getExpireTime();

							// 上一期待收时间
							if (i - 1 > -1) {
								BizReceiptPlan brp2 = brpList.get(i - 1);
								lastExpireTime = brp2.getExpireTime();
							}

						}
						surperDead++;
					} else {

						alreadyBenxi = alreadyBenxi.add(capital).add(netInterest);

					}
				}
			}

			// 求出上一期待收时间与当前时间的日期差
			Date lastDate = DateUtil.format(lastExpireTime);
			Date currentDate = DateUtil.format(currentExpireTime);

			Calendar aCalendar = Calendar.getInstance();

			aCalendar.setTime(lastDate);

			int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

			aCalendar.setTime(new Date());

			int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

			int b = day2 - day1;
			
			if(b > 0 && System.currentTimeMillis() < currentDate.getTime()){
				currentInterest = currentAllInterest.multiply(new BigDecimal(b)).divide(new BigDecimal(30), 2, BigDecimal.ROUND_DOWN);
			}
			
			modelView.addObject("investCapital", investCapital);
			modelView.addObject("alreadyBenxi", alreadyBenxi);
			modelView.addObject("surperCapital", surperCapital);
			modelView.addObject("currentAllInterest", currentAllInterest);
			modelView.addObject("currentInterest", currentInterest);
			modelView.addObject("surperDead", surperDead);
			modelView.addObject("totalDead", totalDead);
			modelView.addObject("currentExpireTime",DateUtil.format(currentDate, "yyyy-MM-dd"));

			// 查询转让手续费率
			SysParams sysParams = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.BIZ_TRANSFER_FEE);
			modelView.addObject("bizTransferFee", sysParams.getParamValue());
			// 查询百分比最小值
			SysParams sysParamsTransfer = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.TRANSFER_CAPITAL_PERCENTER);
			BigDecimal transferPercenter = null;
			try{
				transferPercenter = new BigDecimal(sysParamsTransfer.getParamValue());
			}catch(Exception e){
				//  默认值
				transferPercenter = new BigDecimal(0.97);
			}
			
			BigDecimal minValue = surperCapital.multiply(transferPercenter);
			BigDecimal maxValue = surperCapital.add(currentInterest);
			
			modelView.addObject("minValue", minValue);
			modelView.addObject("maxValue", maxValue);
			
			modelView.addObject("transferId", transferId);
			
			BizBorrow borrow = bizBorrowService.getBizBorrowById(borrowId);
			
			modelView.addObject("borrow", borrow);
			// 添加选中的菜单
			request.setAttribute(PtpConstants.CHECKMENU,PtpConstants.USERCENTER_MENU_INVEST);
		} catch (Exception e) {
			
			if (logger.isDebugEnabled()) {
				logger.debug("查询用户转让详情异常：", e.getMessage());
			}

		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 债权转让确认
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateReceiptPlanById", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateReceiptPlanById(String transferMoney,String borrowId,String transferId,
			String pwd, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		String userId = getUserId();
		try {

			
			int num = customerService.vailidateTradePassword(new String(RSAUtil.decode(pwd)), userId);
			
			if(num>-1){
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					
				}
				n = n == null ? 4 : n;
				//校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num",n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
			
			if (!StringUtils.hasLength(borrowId)) {
				throw new NullArgumentException("标的id为null");
			}
			if (!StringUtils.hasLength(transferMoney)) {
				throw new NullArgumentException("转让金额为null");
			}
			BigDecimal transferAmount = new BigDecimal(transferMoney);
			if (transferAmount==null || transferAmount.compareTo(BigDecimal.ZERO) < 1) {
				throw new NullArgumentException("转让金额为null或者为0");
			}

			// 重新计算一次，防止跨天转让时，数据出现差异
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			if (StringUtils.hasLength(transferId)) {
				// 表示再次债权转让
				brpList = bizReceiptPlanService.findList(userId, borrowId, Constant.INVEST_SRC_TYPE_2,
						transferId);
			} else {
				// 表示原标的转让
				brpList = bizReceiptPlanService.findList(userId, borrowId,Constant.INVEST_SRC_TYPE_1
						,null);

			}

			// 投资本金
			BigDecimal investCapital = BigDecimal.ZERO;
			// 已收本息
			BigDecimal alreadyBenxi = BigDecimal.ZERO;
			// 剩余本金
			BigDecimal surperCapital = BigDecimal.ZERO;
			// 当期全部利息
			BigDecimal currentAllInterest = BigDecimal.ZERO;
			// 当期至今利息
			BigDecimal currentInterest = BigDecimal.ZERO;
			// 剩余期次
			int surperDead = 0;
			// 总期次
			@SuppressWarnings("unused")
			int totalDead = 0;
			// 已结清的数量
			int jieqing = 0;
			// 逾期
			int yuqi = 0;
			// 当期待收时间
			String currentExpireTime = "";
			// 上一期待收时间
			String lastExpireTime = "";

			if (brpList != null) {
				totalDead = brpList.size();
				for (int i = 0; i < brpList.size(); i++) {
					BizReceiptPlan brp = brpList.get(i);
					BigDecimal capital = brp.getCapital() == null ? BigDecimal.ZERO
							: brp.getCapital();
					BigDecimal netInterest = brp.getNetInterest() == null ? BigDecimal.ZERO
							: brp.getNetInterest();

					investCapital = investCapital.add(capital);
					if (Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(brp.getReceiptStatus())) {
						// 待收中
						surperCapital = surperCapital.add(capital);

						if (surperDead == 0) {
							// 第一次循环到待收中表示当期
							currentAllInterest = netInterest;

							currentExpireTime = brp.getExpireTime();

							// 上一期待收时间
							if (i - 1 > -1) {
								BizReceiptPlan brp2 = brpList.get(i - 1);
								lastExpireTime = brp2.getExpireTime();
							}

						}
						surperDead++;
					} else {

						alreadyBenxi = alreadyBenxi.add(capital).add(netInterest);
						
						if(Constant.BIZ_RECEIPTPLAN_STATUS_6.equals(brp.getReceiptStatus())){
							jieqing ++;
						}else if(Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(brp.getReceiptStatus())){
							yuqi++;
						}
					}
				}
			}

			// 求出上一期待收时间与当前时间的日期差
			Date lastDate = DateUtil.format(lastExpireTime);
			Date currentDate = DateUtil.format(currentExpireTime);

			Calendar aCalendar = Calendar.getInstance();

			aCalendar.setTime(lastDate);

			int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

			aCalendar.setTime(new Date());

			int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

			int b = day2 - day1;
			
			if(b > 0 && System.currentTimeMillis() < currentDate.getTime()){
				currentInterest = currentAllInterest.multiply(new BigDecimal(b)).divide(new BigDecimal(30), 2, BigDecimal.ROUND_DOWN);
			}
			
			// 查询转让手续费率
			SysParams sysParams = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.BIZ_TRANSFER_FEE);
			
			// 查询百分比最小值
			SysParams sysParamsTransfer = sysParamsService
					.getParamsByParamKey(SystemParamKeyConstant.TRANSFER_CAPITAL_PERCENTER);
			BigDecimal transferPercenter = null;
			try{
				transferPercenter = new BigDecimal(sysParamsTransfer.getParamValue());
			}catch(Exception e){
				//  默认值
				transferPercenter = new BigDecimal(0.97);
			}
			
			BigDecimal minValue = surperCapital.multiply(transferPercenter);
			BigDecimal maxValue = surperCapital.add(currentInterest);
			
			if(transferAmount.compareTo(minValue) < 0 || transferAmount.compareTo(maxValue) > 0){
				throw new NullArgumentException("转让价格不在转让范围内");
			}
			
			BizBorrow borrow = bizBorrowService.getBizBorrowById(borrowId);
			
			// 校验用户是否可以转让
			// 1.是否允许债权转让
			if(!"1".equals(borrow.getIsEquitableAssignment())){
				throw new RuntimeException("标的不允许债权转让");
			}
			// 2.用户是vip
			if(!customerService.isVip(userId)){
				throw new RuntimeException("用户不是vip");
			}
			// 3.还款一次以上
			if(jieqing == 0){
				throw new RuntimeException("没有产生一次还款");
			}
			// 4.待收时间前三天
			aCalendar.setTime(currentDate);
			int day3 = aCalendar.get(Calendar.DAY_OF_YEAR);
			if(day3 - day2 < 3){
				throw new RuntimeException("待收时间不是前三天");
			}
			// 5.原项目的没有处于逾期状态 
			if(yuqi>0){
				throw new RuntimeException("标的逾期，不可转让");
			}
			
			
			// 进行转让
			BizReceiptTransfer brt = new BizReceiptTransfer();
			brt.setBorrowId(borrowId);
			brt.setCreateUser(userId);
			brt.setParentTransferId(transferId);
			brt.setCreateTime(new Date());
			aCalendar.setTime(new Date());
			aCalendar.add(Calendar.DATE, 1);
			brt.setExpireTime(aCalendar.getTime());
			BigDecimal fee = null;
			try{
				fee = new BigDecimal(sysParams.getParamValue());
			}catch(Exception e){
				fee = new BigDecimal(0.004);
			}
			brt.setFee(transferAmount.multiply(fee));
			brt.setInterestData(lastDate);
			brt.setInterestToday(currentInterest);
			brt.setPid(brt.getPK());
			brt.setProfitRate(currentAllInterest.multiply(new BigDecimal(12)).divide(surperCapital, 4, BigDecimal.ROUND_DOWN));
			brt.setProjectValue(surperCapital.add(currentAllInterest));
			brt.setResidualPrincipal(surperCapital);
			brt.setStatus(Constant.BIZ_TRANSFER_STATUS_1);
			brt.setSuccessAmount(transferAmount);
			brt.setTimeRemaining(surperDead);
			
			
			String transferCode = bizReceiptPlanService
					.updateTransfer(borrowId,transferId,userId,transferAmount,brt);
			
			
			modelView.addObject("transferCode", transferCode);
			
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
			
		}catch(TradePwdFreezeException te){
			// 用户被冻结,获取剩余冻结分钟
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t=null;
			try{
				t= Integer.parseInt(sp.getParamValue());
			}catch(Exception e){
				
			}
			t = t == null ? 60 : t;
			if(time!=null){
				Long l = time.getTime() - System.currentTimeMillis();
				if(l > 0){
					t = (int) (l/(1000*60));
				}else{
					t = 0;
				}
			}
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t",t);
			MessageBuilder.processError(modelView, te, request);
			
		}  catch (TradePwdIsBlankException te) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);

			MessageBuilder.processError(modelView, te, request);

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("债权转让确认异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	
	
	/**
	 * 
	 * Description：<br>
	 * 查询用户已完结项目详情
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/projectInfo")
	public ModelAndView projectInfo(String borrowId, String transferId,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView(
				"temp.usercenter.invest.pro.info.projectinfo");
		try {
			String userId = getUserId();
			if (!StringUtils.hasLength(borrowId)) {
				throw new NullArgumentException("标的id为null");
			}
		
			// 借款信息
			BizBorrow borrow = bizBorrowService.getBizBorrowById(borrowId);
			
			modelView.addObject("borrow", borrow);
			
			// 投资信息
			InvestmentInfoModel investModel = userCenterService.selectInvestmentInfoByBorrowId(userId,borrowId);
			modelView.addObject("investModel", investModel);
			// 收益明细
			IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService) getService();
			List<BizReceiptPlan> brpList = new ArrayList<BizReceiptPlan>();
			BizReceiptTransfer brt = null;
			if (StringUtils.hasLength(transferId)) {
				// 表示债权转让过
				brpList = bizReceiptPlanService.findList(userId, borrowId, Constant.INVEST_SRC_TYPE_2,
						transferId);
				
				brt = bizReceiptTransferService.getBizReceiptTransferById(transferId);
			} else {
				// 表示原标
				brpList = bizReceiptPlanService.findList(userId, borrowId,Constant.INVEST_SRC_TYPE_1
						,null);

			}
			modelView.addObject("brpList", brpList);
			
			
			// 转让信息
			modelView.addObject("brt", brt);

			// 添加选中的菜单
			request.setAttribute(PtpConstants.CHECKMENU,PtpConstants.USERCENTER_MENU_INVEST);
		} catch (Exception e) {
			
			if (logger.isDebugEnabled()) {
				logger.debug("查询用户转让详情异常：", e.getMessage());
			}

		}
		return modelView;
	}

	
	

}
