/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体现管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.personcenter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustRechargeWithdraw;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.core.service.financial.impl.CustRechargeWithdrawServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.sms.ISmsService;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.BankServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.util.MobileVerifyUtil;
import com.yscf.ep2p.vo.system.CustomerVo;


/**
 * Description：<br> 
 * 客户个人中心	充值、提现	控制器
 * @author  JingYu.Dai
 * @date    2015年12月17日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/userinfo/custRechargeWithdrawController")
public class CustRechargeWithdrawController extends EscfBaseWebController {
	private Logger logger = LoggerFactory.getLogger(CustRechargeWithdrawController.class);

	@Autowired
	public CustRechargeWithdrawController(CustRechargeWithdrawServiceImpl service) {
		super(service);
	}
	@Override
	public Class<?> getModel() {
		return CustRechargeWithdraw.class;
	}
	
	@Resource(name="custRechargeWithdrawServiceImpl")
	CustRechargeWithdrawServiceImpl custRechargeWithdrawServiceImpl;
	
	@Resource(name="bankService")
	BankServiceImpl bankServiceImpl;
	
	@Resource(name="customerAccountService")
	private CustomerAccountServiceImpl custmoeAccountService; 

	@Resource(name="bizWithdrawService")
	private BizWithdrawServiceImpl bizWithdrawService;
	
	@Resource(name="cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name="smsService")
	private ISmsService smsService;
	
	//vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	SysVipinfoServiceImpl sysVipinfoService;

	/**
	 * Description：<br> 
	 * 跳转到 客户个人中心  充值提现 页面
	 * @author  JingYu.Dai
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @return String
	 */
	@RequestMapping(value = "/toPayIndex")
	public String toReceiptPlanList(HttpServletRequest req){
		CustomerVo cv = getContextUser();
		int bankCount = 0;
		try {
			bankCount = bankServiceImpl.selectBankCountByCusId(cv.getPid());
			String userId = getUserId();
			//获取账户信息
			CustomerAccount customerAccount = custmoeAccountService.getByCusterId(userId);
			//客户可用余额
			BigDecimal availableAmount = customerAccount.getAvailableAmount();
			//充值总金额
			BigDecimal withdrawalAmount = customerAccount.getWithdrawAmount();
			//提现总金额
			BigDecimal rechargeAmount = customerAccount.getRechargeAmount();
			//查vip等级
			List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(userId);
			if(null != list && list.size()>0){
			String vipLevel = list.get(0).getVipLevel();
			if(null != vipLevel || !"".equals(vipLevel)){
				String vips = "VIP"+vipLevel;
				if(!"".equals(vipLevel) && vipLevel!=null){
					req.setAttribute("vipLevel",vips);
				}else{
					req.setAttribute("vipLevel","VIP");
				}
			}
			  }
			else{
				req.setAttribute("vipLevel","VIP");
			}
			req.setAttribute("availableAmount", availableAmount);
			req.setAttribute("withdrawalAmount",withdrawalAmount);
			req.setAttribute("rechargeAmount",rechargeAmount);
			req.setAttribute("openStatus",req.getParameter("openStatus"));
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("根据客户 id 查询银行账户总记录条数出现异常：", e.getMessage());
			}
		}
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_CHARGE);
		req.setAttribute("bankCount", bankCount);
		return "temp.usercenter.rechargewithdraw";
	}
	
	/**
	 * Description：<br> 
	 * 跳转到 银行卡列表页面
	 * @author  JingYu.Dai
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param req HttpServletRequest 对象
	 * @return	ModelAndView
	 */
	@RequestMapping(value = "/toBankList")
	public ModelAndView toBankList(){
		//跳转到余额提现页面
		return new ModelAndView("/personcenter/rechargewithdrawal/payBackList");
	}
	
	/**
	 * Description：<br> 
	 * 根据 客户是否有银行卡	跳转到对应的页面
	 * @author  JingYu.Dai
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param req	HttpServletRequest	对象
	 * @return	ModelAndView
	 */
	@RequestMapping(value = "/toWithdrawPage")
	public ModelAndView openWithdrawPage(HttpServletRequest req){
		CustomerVo cv = getContextUser();
		int bankCount = 0;
		String userId = getUserId();
		try {
			bankCount = bankServiceImpl.selectBankCountByCusId(cv.getPid());
			Map<String,BigDecimal> map = custRechargeWithdrawServiceImpl.getAmountSumByCustId(userId);
			req.setAttribute("commonAmount",map.get("commonAmount"));
			req.setAttribute("rechargeAmount",map.get("rechargeAmount"));
			req.setAttribute("availableAmount",map.get("availableAmount"));
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
		if(new Integer(bankCount) > 0){
			//跳转到余额提现页面
			return new ModelAndView("/personcenter/rechargewithdrawal/payBalanceWithdrawa");
		}
		//跳转到余额提现页面
		return new ModelAndView("/personcenter/rechargewithdrawal/notAddBankCard");
	}
	
	/**
	 * Description：<br> 
	 * 用户个人中心	充值 列表
	 * @author  JingYu.Dai
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param request
	 * @param businessTypes
	 * @param pageIndex
	 * @param pageSize
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findUserRechargePage")
	@ResponseBody
	public ModelAndView findUserRechargeWithdrawPage(HttpServletRequest request,CustFundWater custFundWater,Integer pageIndex, Integer pageSize){
		ModelAndView modelView = new ModelAndView();
		List<CustRechargeWithdraw> vos;
		try {
			custFundWater.setCustomerId(getUserId());
			vos = custRechargeWithdrawServiceImpl.selectRechargePage(custFundWater,pageIndex,pageSize);
			Integer total = custRechargeWithdrawServiceImpl
					.selectRechargeTotal(custFundWater);
			total = (total == null ? 0 : total);
			modelView.addObject("vos", vos);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询列表出现异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 用户个人中心	提现 列表
	 * @author  JingYu.Dai
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param request
	 * @param businessTypes
	 * @param pageIndex
	 * @param pageSize
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/findUserWithdrawPage")
	@ResponseBody
	public ModelAndView findUserWithdrawPage(HttpServletRequest request,CustFundWater custFundWater,Integer pageIndex, Integer pageSize){
		ModelAndView modelView = new ModelAndView();
		List<CustRechargeWithdraw> vos;
		//customerId
		custFundWater.setCustomerId(getUserId());
		try {
			vos = custRechargeWithdrawServiceImpl.selectWithdrawPage(custFundWater,pageIndex,pageSize);
			Integer total = custRechargeWithdrawServiceImpl
					.selectWithdrawTotal(custFundWater);
			total = (total == null ? 0 : total);
			modelView.addObject("vos", vos);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询列表出现异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 用户个人中心	客户提现
	 * @author  JingYu.Dai
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param request
	 * @param businessTypes
	 * @param pageIndex
	 * @param pageSize
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/custWithdrawal")
	@ResponseBody
	public ModelAndView custWithdrawal(HttpServletRequest request,BizWithdraw bizWithdraw){
		ModelAndView modelView = new ModelAndView();
		try {
			CustomerVo customerVo = getContextUser();
			//逾期客户禁止提现!
			boolean falg = cusTomerService.isOverdue(customerVo.getPid());
			if(falg){
				MessageBuilder.processSuccess(modelView, 5,
						HttpMessage.ERROR_CODE, request);
				return modelView;
			}
			//验证交易密码
			int num = cusTomerService.vailidateTradePassword(bizWithdraw.getCustomer().getTradePassword(),customerVo.getPid());
			if(num > -1){
				MessageBuilder.processSuccess(modelView, 1,
						HttpMessage.ERROR_CODE, request);
				return modelView;
			}
			//从session中获取验证码
			String sessionCode = memcachedClient.get(customerVo.getPhoneNo());
			String[] codes = sessionCode.split(",");
			if(!bizWithdraw.getCode().equals(codes[0])){
				MessageBuilder.processSuccess(modelView, 2,
						HttpMessage.ERROR_CODE, request);
				return modelView;
			}
			//提现金额是否大于0
			if(bizWithdraw.getAmount().compareTo(new BigDecimal(0)) < 0){
				MessageBuilder.processSuccess(modelView, 3,
						HttpMessage.ERROR_CODE, request);
				return modelView;
			}
			//验证金额是否大于可提现总额
			Map<String,BigDecimal> map = custRechargeWithdrawServiceImpl.getAmountSumByCustId(customerVo.getPid());
			BigDecimal availableAmount = map.get("availableAmount");
			if(bizWithdraw.getAmount().compareTo(availableAmount) > 0){
				MessageBuilder.processSuccess(modelView, 4,
						HttpMessage.ERROR_CODE, request);
				return modelView;
			}
			bizWithdraw.getCustomer().setPid(getUserId());
			int resCount = bizWithdrawService.insert(bizWithdraw);
			MessageBuilder.processSuccess(modelView, resCount,
					HttpMessage.SUCCESS_MSG, request);
			
		}catch(TradePwdIsBlankException e){
			if(Constant.USER_TRADE_PWD_BANK.equals(e)){
				MessageBuilder.processSuccess(modelView, 6,
						HttpMessage.ERROR_CODE, request);
			}
			MessageBuilder.processError(modelView, e, request);
			
		}catch(TradePwdFreezeException te){
			MessageBuilder.processError(modelView, te, request);
			
		}catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询列表出现异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}catch (TimeoutException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("异常：", e.getMessage());
			}
		}catch (InterruptedException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("异常：", e.getMessage());
			}
		}catch (MemcachedException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("异常：", e.getMessage());
			}
		} 
		return modelView;
	}
	
	@RequestMapping(value = "/sendSmsCode")
	@ResponseBody
	public ModelAndView sendSmsCode(BigDecimal money,
		HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			String mobiles = super.getContextUser().getPhoneNo();
			CustomerVo customerVo = getContextUser();
			Map<String,String> map = new HashMap<String,String>();
			map.put("{验证码}", code);
			map.put("{账户名}", customerVo.getSname());
			map.put("{提现金额}", money.toString());
			// 调用短信发送接口
			String result = smsService.sendSmsCode(mobiles, "提现申请验证码",MobileVerifyUtil.SESSION_WITHDRAWAL_CODE,map);
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(mobiles,code);
			}
			
			// 短信code记录在memcached 中
			smsService.addCodeToSession(mobiles, code);
			modelView.addObject("result", result);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
}
