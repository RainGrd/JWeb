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

import java.util.Date;

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

import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.ExchangeConstant;
import com.yscf.common.Constant.LogConstant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.core.exception.PointNotEnoughException;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.service.exchange.IExchangeService;
import com.yscf.core.service.exchange.impl.ExchangeServiceImpl;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * @ClassName : ExchangeController
 * @Description : 系统兑换Controller
 * @Author : Qing.Cai
 * @Date : 2016年1月7日 下午7:51:44
 */
@Controller
@RequestMapping("usercenter/exchangeController")
public class ExchangeController extends EscfBaseWebController {

	private Logger logger = LoggerFactory.getLogger(ExchangeController.class);

	@Autowired
	public ExchangeController(ExchangeServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CustPoinWater.class;
	}

	/** 客户管理服务接口 */
	@Resource
	private ICusTomerService customerService;
	/** 系统参数服务接口 */
	@Resource
	private ISysParamsService sysParamsService;

	/**
	 * 
	 * @Description : 积分兑换话费
	 * @param exchangeType
	 *            兑换类型
	 * @param tradePassword
	 *            交易密码
	 * @param phoneNo
	 *            手机号码
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 返回ModelAndView对象,判断是否成功
	 * @Author : Qing.Cai
	 * @Date : 2016年1月11日 下午8:19:52
	 */
	@RequestMapping(value = "/exchangeTelephoneFare", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView exchangeTelephoneFare(String exchangeType, String tradePassword, String phoneNo, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		// 获取用户登录ID
		String userId = getUserId();
		try {
			// 校验密码是否正确
			int num = customerService.vailidateTradePassword(tradePassword, userId);
			if (num > -1) {
				// 获取交易密码可错误次数
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					// 无效
				}
				n = n == null ? 4 : n;
				
				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
			IExchangeService service = (ExchangeServiceImpl) getService();
			// 调用方法业务处理
			service.exchangePhoneFees(userId, exchangeType, phoneNo);
			// 添加日志
			addLog(request, LogConstant.LOG_OPER_TYPE_31, "兑换话费成功");
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (PointNotEnoughException e){
			// 积分不够
			modelView.addObject("errorCode",ExchangeConstant.POINY_NO_ENOUGH);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e){
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t=null;
			try{
				t= Integer.parseInt(sp.getParamValue());
			}catch(Exception te){
				
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
			// 交易密码冻结
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t",t);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdIsBlankException e) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, e, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("积分兑换话费：", e.getMessage());
			}
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 兑换加息劵
	 * @param exchangeType
	 *            兑换类型
	 * @param tradePassword
	 *            交易密码
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 返回ModelAndView对象,判断是否成功
	 * @Author : Qing.Cai
	 * @Date : 2016年1月11日 下午8:53:27
	 */
	@RequestMapping(value = "/exchangeInterestTicket", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView exchangeInterestTicket(String exchangeType, String tradePassword, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		// 获取用户登录ID
		String userId = getUserId();
		try {
			// 校验密码是否正确
			int num = customerService.vailidateTradePassword(tradePassword, userId);
			if (num > -1) {
				// 获取交易密码可错误次数
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					// 无效
				}
				n = n == null ? 4 : n;
				
				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
			IExchangeService service = (ExchangeServiceImpl) getService();
			// 调用方法业务处理
			service.exchangeInterestTicket(userId, exchangeType);
			// 添加日志
			addLog(request,  LogConstant.LOG_OPER_TYPE_32, "兑换加息劵成功");
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (PointNotEnoughException e){
			// 积分不够
			modelView.addObject("errorCode",ExchangeConstant.POINY_NO_ENOUGH);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e){
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t=null;
			try{
				t= Integer.parseInt(sp.getParamValue());
			}catch(Exception te){
				
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
			// 交易密码冻结
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t",t);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdIsBlankException e) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, e, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("兑换加息劵：", e.getMessage());
			}
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 兑换VIP
	 * @param exchangeType
	 *            兑换类型
	 * @param tradePassword
	 *            交易密码
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 返回ModelAndView对象,判断是否成功
	 * @Author : Qing.Cai
	 * @Date : 2016年1月11日 下午8:52:58
	 */
	@RequestMapping(value = "/exchangeVIP", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView exchangeVIP(String exchangeType, String tradePassword, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		// 获取用户登录ID
		String userId = getUserId();
		try {
			// 校验密码是否正确
			int num = customerService.vailidateTradePassword(tradePassword, userId);
			if (num > -1) {
				// 获取交易密码可错误次数
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					// 无效
				}
				n = n == null ? 4 : n;
				
				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
			IExchangeService service = (ExchangeServiceImpl) getService();
			// 调用方法业务处理
			String vipEndTime = service.exchangeVIP(userId, exchangeType);
			// 返回VIP到期时间
			modelView.addObject("vipEndTime", vipEndTime);
			// 添加日志
			addLog(request,  LogConstant.LOG_OPER_TYPE_33, "兑换VIP成功");
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (PointNotEnoughException e){
			// 积分不够
			modelView.addObject("errorCode",ExchangeConstant.POINY_NO_ENOUGH);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e){
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t=null;
			try{
				t= Integer.parseInt(sp.getParamValue());
			}catch(Exception te){
				
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
			// 交易密码冻结
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t",t);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdIsBlankException e) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, e, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("兑换VIP：", e.getMessage());
			}
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 兑换现金
	 * @param exchangeType
	 *            兑换类型
	 * @param tradePassword
	 *            交易密码
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 返回ModelAndView对象,判断是否成功
	 * @Author : Qing.Cai
	 * @Date : 2016年1月11日 下午8:51:37
	 */
	@RequestMapping(value = "/exchangeCash", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView exchangeCash(String exchangeType, String tradePassword, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		// 获取用户登录ID
		String userId = getUserId();
		try {
			// 校验密码是否正确
			int num = customerService.vailidateTradePassword(tradePassword, userId);
			if (num > -1) {
				// 获取交易密码可错误次数
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					// 无效
				}
				n = n == null ? 4 : n;
				
				// 校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
			IExchangeService service = (ExchangeServiceImpl) getService();
			// 调用方法业务处理
			service.exchangeCash(userId, exchangeType);
			// 添加日志
			addLog(request,  LogConstant.LOG_OPER_TYPE_34, "兑换现金成功");
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (PointNotEnoughException e){
			// 积分不够
			modelView.addObject("errorCode",ExchangeConstant.POINY_NO_ENOUGH);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e){
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t=null;
			try{
				t= Integer.parseInt(sp.getParamValue());
			}catch(Exception te){
				
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
			// 交易密码冻结
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t",t);
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdIsBlankException e) {
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, e, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("兑换现金：", e.getMessage());
			}
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

}
