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
 * 2015年12月28日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.user;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.controller.otherinfo.CalculatorApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.user.AccountInfoVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.api.vo.user.LoginVo;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.financial.impl.CustRechargeWithdrawServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerDtoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.LoginEptpServiceImpl;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;
/**
 * Description：<br> 
 * 用户登录API控制
 * @author  Yu.Zhang
 * @date    2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/user/customerApi")
public class CustomerApi extends EscfBaseApi {
	
	@Autowired
	public CustomerApi(LoginEptpServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(CalculatorApi.class);
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name = "cusTomerDtoService")
	private CusTomerDtoServiceImpl cusTomerDtoService;	// 客户信息
	
	//用户中心数据信息
	@Resource(name="userCenterServiceImpl")
	private UserCenterServiceImpl ucservice;
		
	//客户账户信息数据
	@Resource(name="customerAccountService")
	private CustomerAccountServiceImpl custmoeAccountService;
	
	@Resource(name="bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl bizBorrowDetailService;
	
	@Resource(name="sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
	
	@Resource(name="custRechargeWithdrawServiceImpl")
	CustRechargeWithdrawServiceImpl custRechargeWithdrawServiceImpl;
	
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;	// 客户信息
	
	/**
	 * Description：<br> 
	 * 获取用户新手任务进度信息
	 * @author  Lin Xu
	 * @date    2015年12月25日
	 * @version v1.0.0
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getTaskSchedule",method=RequestMethod.POST)
	@ResponseBody
	public String getTaskSchedule(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		Map<String, Object> loginResult = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerVo tcustomer = getCustomer(xtoken);
			loginResult.put("isRegistered", "1");
			loginResult.put("isAttestation", tcustomer.getIsAttestation());
			
			// 设置是否投资
			Integer count = bizBorrowDetailService.getBizDetailCountByUser(xtoken);
			if(count == 0){
				loginResult.put("isInvestment", "2");
			}else{
				loginResult.put("isInvestment", "1");
			}
			jsonObject.setResult(loginResult);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("用户App登录失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * Description：<br> 
	 * 查询当前登录人信息
	 * @author  Lin Xu
	 * @date    2015年12月25日
	 * @version v1.0.0
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getCustomer",method=RequestMethod.POST)
	@ResponseBody
	public String getCustomer(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		Map<String, Object> loginResult = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerDto customerVo = cusTomerDtoService.getCustomerDtoByPid(xtoken);
			CustomerVo tcustomer = ConvertObjectUtil.convertObject(customerVo, CustomerVo.class);
			//获取总资产信息
			AccountInfoVo acinfovo = getAccountInfoVo(xtoken);
			if(null != acinfovo){
				tcustomer.setTotalAssets(acinfovo.getTotalAssets());
			}
			// 查询普通资金，充值资金
			Map<String,BigDecimal> map = custRechargeWithdrawServiceImpl.getAmountSumByCustId(xtoken);
			tcustomer.setCommonAmount(map.get("commonAmount"));
			tcustomer.setRechargeDetaiAmount(map.get("rechargeAmount"));

			//
			loginResult.put("customerVo", tcustomer);
			loginResult.put("systemTime", DateUtil.getToday().getTime());
			
			// 获取短信发送间隔时间
			SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("SEND_SMS_TIME");
			loginResult.put("smsTime",sysParams.getParamValue());
			
			jsonObject.setResult(loginResult);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("用户App登录失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * Description：<br> 
	 * 获取用户账户信息
	 * @author  Lin Xu
	 * @date    2016年1月21日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	private AccountInfoVo getAccountInfoVo(String userId){
		//个人中心我的账户对象
		AccountInfoVo aivo = new AccountInfoVo();
		//获取账户信息
		CustomerAccount customerAccount = custmoeAccountService.getByCusterId(userId);
		if(null != customerAccount){
			aivo.setAvailableBalance(customerAccount.getAvailableAmount());
			aivo.setFreezingAmount(customerAccount.getFreezeAmount());
		}
		//获取待收金额
		HashMap<String, BigDecimal> durInMoney = ucservice.getUserDueInMoney(userId);
		if(null != durInMoney){
			BigDecimal dueinamount = durInMoney.get("tatolcapital") == null ? new BigDecimal(0) : durInMoney.get("tatolcapital");
			aivo.setDueinAmount(dueinamount);
			BigDecimal dueininterest = durInMoney.get("tatolinterest") == null ? new BigDecimal(0) : durInMoney.get("tatolinterest");
			aivo.setDueinInterest(dueininterest);
		}
		//获取利息信息   投资利息   加息利息   投资奖励   红包收益  推荐奖励
		aivo.setInvestInterest(ucservice.getUserInvestInterest(userId));
		aivo.setRateInterest(ucservice.getUserRateInterest(userId));
		aivo.setInvestmentIncentive(ucservice.getUserInIncentive(userId));
		aivo.setRedEnvelope(ucservice.getUserRedEnvelope(userId));
		aivo.setRecommendedAwards(ucservice.getUserRecommendedAwards(userId));
		return aivo;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 设置APP端设备ID
	 * @author  Yu.Zhang
	 * @date    2016年5月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/setRegistrationID",method=RequestMethod.POST)
	@ResponseBody
	public String setRegistrationID(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			
			LoginVo loginVo = (LoginVo) ApiUtil.convertObjectByBody(request, LoginVo.class);
			
			// 更新移动设备的机器代码
			cusTomerService.updateMobileDeviceMachineCode(loginVo.getMobileDeviceMachineCode(),getCustomerId(xtoken));
			
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("设置APP端设备ID失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


