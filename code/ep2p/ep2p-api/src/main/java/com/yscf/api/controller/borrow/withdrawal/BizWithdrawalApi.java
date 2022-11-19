/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.borrow.withdrawal;

import java.util.HashMap;
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.json.gson.GsonUtil;
import com.achievo.framework.vo.JsonObject;
import com.google.gson.Gson;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.controller.send.MobileVerifyUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.api.vo.withdrawal.WithdrawalVerifyCodeVo;
import com.yscf.api.vo.withdrawal.WithdrawalVo;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.model.user.Bank;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;

/**
 * Description：<br> 
 * 提现管理	移动端API
 * @author  JingYu.Dai
 * @date    2015年12月23日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/withdrawal/bizWithdrawalApi")
public class BizWithdrawalApi extends EscfBaseApi{

	Logger logger = LoggerFactory.getLogger(BizWithdrawalApi.class);
	
	@Autowired
	public BizWithdrawalApi(BizWithdrawServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizWithdraw.class;
	}
	
	@Resource(name="customerAccountService")
	private CustomerAccountServiceImpl custmoeAccountService;
	
	@Resource(name="cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	
	@Resource(name="bizWithdrawService")
	private BizWithdrawServiceImpl bizWithdrawService;
	
	@Resource(name="smsService")
	private SmsServiceImpl smsService;
	
	
	/**
	 * Description：<br> 
	 * 发送短信验证码
	 * @author  JingYu.Dai
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @return String json
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendVerifyCode", method = RequestMethod.POST)
	@ResponseBody
	public String sendVerifyCode(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		Map<String, Object> loginResult = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			CustomerVo tcustomer = getCustomer(xtoken);
			//获取个人自动投标的列表
			String sName = tcustomer.getSname();
			String reqStr = ApiUtil.getBodyDecryptStr(request);
			Gson gson = GsonUtil.create();
			WithdrawalVerifyCodeVo wvc = gson.fromJson(reqStr, WithdrawalVerifyCodeVo.class);
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			Map<String,String> map = new HashMap<String,String>();
			map.put("{验证码}", code);
			map.put("{账户名}", sName);
			map.put("{提现金额}", wvc.getMoney().toString());
			// 调用短信发送接口
			String result = smsService.sendSmsCode(wvc.getMobile(), "APP提现申请验证码",MobileVerifyUtil.SESSION_WITHDRAWAL_CODE,map);
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(wvc.getMobile(),code);
			}
			loginResult.put("result", result);
			jsonObject.setResult(loginResult);
			jsonObject.setStatus(true);
			jsonObject.setCode(ApiCode.HTTP_CODE_200);
			jsonObject.setMessage("短信验证码发送成功");
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
			}
			processResultStatus(jsonObject,"900","验证码发送失败",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * Description：<br> 
	 * 客户	提现 
	 * @author  JingYu.Dai
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param verifyCode 验证码
	 * @param mobile 手机号码
	 * @param bankId 银行卡ID
	 * @param money 提现金额
	 * @return JsonObject
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/custWithdrawal", method = RequestMethod.POST)
	@ResponseBody
	public String custWithdrawal(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		String reqStr = ApiUtil.getBodyDecryptStr(request);
		Gson gson = GsonUtil.create();
		WithdrawalVo wv = gson.fromJson(reqStr, WithdrawalVo.class);
		JsonObject jsonObject = new JsonObject();
		BizWithdraw bizWithdraw = new BizWithdraw();
		Bank bank = new Bank();
		bank.setPid(wv.getBankId());
		//判断客户是否逾期
		boolean falg = cusTomerService.isOverdue(getCustomerId(xtoken));
		if(falg){
			processResultStatus(jsonObject,"903","逾期客户不能提现",false);
			return ApiUtil.getEncryptStr(jsonObject);
		}
		//1 验证码已过期 2 验证码错误 3 验证通过
		String resStatus = smsService.validateCode(wv.getMobile(), wv.getVerifyCode());
		if("1".equals(resStatus)){
			processResultStatus(jsonObject,"900","验证码已过期",false);
			return ApiUtil.getEncryptStr(jsonObject);
		}else if("2".equals(resStatus)){
			processResultStatus(jsonObject,"901","验证码错误",false);
			return ApiUtil.getEncryptStr(jsonObject);
		}else if("3".equals(resStatus)){
			CusTomer customer = new CusTomer();
			customer.setPid(getCustomerId(xtoken));
			bizWithdraw.setCustomer(customer);
			bizWithdraw.setBank(bank);
			bizWithdraw.setAmount(wv.getMoney());
			try{
				bizWithdrawService.insert(bizWithdraw);
				processResultStatus(jsonObject,"200","提现申请成功",true);
			} catch (FrameworkException e) {
				if (logger.isDebugEnabled()) {
					logger.debug("查询列表出现异常：", e.getMessage());
				}
				processResultStatus(jsonObject,"902","提现失败",false);
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
}


