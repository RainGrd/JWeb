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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.controller.otherinfo.CalculatorApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.send.SendVerifySmsVo;
import com.yscf.api.vo.user.LoginVo;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;

/**
 * Description：<br> 
 * 忘记密码API控制
 * @author  Yu.Zhang
 * @date    2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/user/resetPwdApi")
public class ResetPwdApi extends EscfBaseApi {
	
	@Autowired
	public ResetPwdApi(CusTomerServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(CalculatorApi.class);
	
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;	// 客户信息
	
	@Resource(name = "smsService")
	private SmsServiceImpl smsService;
	
	/**
	 * 
	 * Description：<br> 
	 * 用户忘记密码App接口
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param loginName 登录名
	 * @param password  密码
	 * @param request
	 * @param response
	 * @return JsonObject
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/resetPwd",method=RequestMethod.POST)
	@ResponseBody
	public String resetPwd(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			
			// 解析请求数据
			LoginVo loginVo = (LoginVo) ApiUtil.convertObjectByBody(request, LoginVo.class);
			
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = service.selectByLoginName(loginVo.getLoginName());
			cusTomer.setPassword(loginVo.getPassword());
			// 重置密码
			service.resetPwd(cusTomer,null);
			// 重置密码成功，给用户发送短信
			smsService.sendSmsCode(loginVo.getPassword(), "APP找回密码","REGISTERED_SUC_CODE",null);
			
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"重置密码操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.setMessage(e.getMessage());
			if(logger.isDebugEnabled()){
				logger.debug("用户App修改密码失败"+e.getMessage());
			}
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
			map.put("{客户名}", smsVo.getMobile());
			// 调用短信发送接口
			String result = smsService.sendSmsCode(smsVo.getMobile(), "APP找回密码","REST_CODE",map);
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


