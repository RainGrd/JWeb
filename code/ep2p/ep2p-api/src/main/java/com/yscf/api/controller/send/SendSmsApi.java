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
 * 2015年12月24日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.send;

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
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.send.SendVerifySmsVo;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.system.impl.SysUserServiceImpl;

/**
 * Description：<br> 
 * 移动端	发送短信API
 * @author  JingYu.Dai
 * @date    2015年12月24日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/send/sendSmsApi")
public class SendSmsApi extends EscfBaseApi{
	
	private Logger logger = LoggerFactory.getLogger(SendSmsApi.class);

	@Resource(name="smsService")
	private SmsServiceImpl smsService ; 
	
	@Autowired
	public SendSmsApi(SysUserServiceImpl service) {
		super(service);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 验证短信验证码
	 * @author  Yu.Zhang
	 * @date    2016年1月5日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateSmsCode",method=RequestMethod.POST)
	@ResponseBody
	public String validateSmsCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			
			// 解析请求数据
			SendVerifySmsVo smsVo =(SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			String result = smsService.validateCode(smsVo.getMobile(), smsVo.getVerifyCode());
			// 调用短信发送接口
			if("3".equals(result)){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"验证成功",true);
			}else{
				if("1".equals(result)){
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"验证码已过期！",false);
				}else if("2".equals(result)){
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"验证码错误！",false);
				}
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
	
	/**
	 * 
	 * Description：<br> 
	 * 发送语音验证码
	 * @author  Yu.Zhang
	 * @date    2016年1月5日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendScheduledSMS",method=RequestMethod.POST)
	@ResponseBody
	public String sendScheduledSMS(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			
			// 解析请求数据
			SendVerifySmsVo smsVo =(SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			
			// 调用短信发送接口
			String result = smsService.sendScheduledSMS(smsVo.getMobile());
			if("2".equals(result)){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"发送成功！",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"验证码已过期！",false);
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


