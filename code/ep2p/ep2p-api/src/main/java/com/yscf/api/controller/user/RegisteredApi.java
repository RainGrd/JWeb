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

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.controller.otherinfo.CalculatorApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.send.SendVerifySmsVo;
import com.yscf.api.vo.user.LoginVo;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.LoginEptpServiceImpl;

/**
 * Description：<br> 
 * 用户注册API控制
 * @author  Yu.Zhang
 * @date    2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/user/registeredApi")
public class RegisteredApi extends EscfBaseApi {
	
	@Autowired
	public RegisteredApi(LoginEptpServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(CalculatorApi.class);
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;	// 客户信息
	
	@Resource(name = "smsService")
	private com.yscf.core.service.sms.impl.SmsServiceImpl smsService;

	@Override
	public Class<?> getModel() {
		return CalculationDto.class;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 用户注册
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param phoneNo
	 * @param password
	 * @param referralCode
	 * @param identifyingCode
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/registered",method=RequestMethod.POST)
	@ResponseBody
	public String registered(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			// 解析请求数据
			LoginVo loginVo = (LoginVo) ApiUtil.convertObjectByBody(request, LoginVo.class);
			
			// 验证手机号是否已经注册
			CusTomer customer = new CusTomer();
			customer.setPhoneNo(loginVo.getPhoneNo());
			boolean result = false;
			result = cusTomerService.validatePhoneNo(customer);
			if(result){
				// 判断手机验证码是否正确
				String resultFlag = smsService.validateCode(loginVo.getPhoneNo(),loginVo.getPhoneCode() );
				if("3".equals(resultFlag)){
					// 判断推介码是否为空，不为空则验证推介码是否存在
					CusTomer temp = null;
					if(StringUtil.isNotEmpty(loginVo.getReferralCode())){
						// 验证推介码是否存在
						temp = cusTomerService.selectByReferralCode(loginVo.getReferralCode());
						if(null == temp){
							result =false;
							// 推介码不存在
							processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"推介码不存在！",false);
						}
					}
					
					if(result){
						// 保存用户信息
						customer.setPhoneNo(loginVo.getPhoneNo());
						customer.setPassword(loginVo.getPassword());
						if(null!= temp)
							customer.setRefereeUser(temp.getPid());
						customer.setRegistrationTime(DateUtil.format(DateUtil.getToday()));
						customer.setCreateTime(DateUtil.format(DateUtil.getToday()));
						cusTomerService.insert(customer);
						// 注册成功，发送注册成功信息
						smsService.sendSmsCode(loginVo.getPhoneNo(), "APP用户注册","REGISTERED_SUC_CODE",null);
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"注册成功",true);
					}
				}else{
					if("1".equals(resultFlag)){
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"验证码已过期！",false);
					}else if("2".equals(resultFlag)){
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"验证码错误！",false);
					}
				}
			}else{
				// 已经注册
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"手机号已经注册！",false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.setMessage(e.getMessage());
			if(logger.isDebugEnabled()){
				logger.debug("用户App注册失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"注册失败",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 发送短信验证码<br>
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param mobiles 手机号码
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendSmsCode",method=RequestMethod.POST)
	@ResponseBody
	public String sendSmsCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			
			// 解析请求数据
			SendVerifySmsVo smsVo =(SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("{验证码}", code);
			// 调用短信发送接口
			String result = smsService.sendSmsCode(smsVo.getMobile(), "APP用户注册","REGISTERED_CODE",map);
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"发送成功",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"发送短信超过限制次数",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


