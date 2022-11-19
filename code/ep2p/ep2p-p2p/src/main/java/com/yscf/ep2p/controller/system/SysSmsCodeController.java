/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典内容
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.system;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jpush.api.utils.StringUtils;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.sms.ISmsService;
import com.yscf.core.service.sms.impl.SmsRecordsCustomerServiceImpl;
import com.yscf.core.service.sms.impl.SmsRecordsServiceImpl;
import com.yscf.core.service.system.ISysSmsTemplatesService;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * Description：<br> 
 * 手机验证码controll
 * @author  Yu.Zhang
 * @date    2015年12月15日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sms/sysSmsCodeController")
public class SysSmsCodeController extends EscfBaseWebController {

	private Logger logger = LoggerFactory.getLogger(SysSmsCodeController.class);
	
	@Resource(name = "smsRecordsService")
	private SmsRecordsServiceImpl smsRecordsService;
	
	@Resource(name = "sysSmsTemplatesService")
	private ISysSmsTemplatesService sysSmsTemplatesService;
	
	@Resource(name="smsRecordsCustomerService")
	private SmsRecordsCustomerServiceImpl smsRecordsCustomerService;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name="smsService")
	private ISmsService smsService ; 
	
	@Autowired
	public SysSmsCodeController(SysDictionaryContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysDictionaryContent.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 发送短信验证码<br>
	 *  1 判断当前手机号码是否超过数据字典中配置的条数<br>

		2 超过条数不发送短息，返回前台提示<br>
		
		3 未超过，发送短信；短信验证码，验证码存放至sesion中，覆盖之前的验证码<br>
	 * @author  Yu.Zhang
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param mobiles 手机号码
	 * @param tempKey 短信模板key
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendSmsCode")
	@ResponseBody
	public ModelAndView sendSmsCode(String mobiles,String model, String tempKey,HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			
			Map<String,String> map = new HashMap<String,String>();
			if(StringUtils.isEmpty(mobiles)){
				mobiles = super.getContextUser().getPhoneNo();
			}
			
			map.put("{验证码}", code);
			map.put("{客户名}", mobiles);
			//1 超过限制条数 2 发送成功
			String result = smsService.sendSmsCode(mobiles, model,tempKey,map);
			
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
	
	/**
	 * 
	 * Description：<br> 
	 * 验证手机验证码
	 * @author  Yu.Zhang
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param mobiles
	 * @param tempKey
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateSmsCode")
	@ResponseBody
	public ModelAndView validateSmsCode(String mobiles, String code, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String result = smsService.validateCode(mobiles, code);
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

	
	
	/**
	 * 
	 * Description：<br> 
	 * 发送语音短信验证码<br>
	 * @author  Yu.Zhang
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param mobiles 手机号码
	 * @param tempKey 短信模板key
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendScheduledSMS")
	@ResponseBody
	public ModelAndView sendScheduledSMS(String mobiles, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String result = smsService.sendScheduledSMS(mobiles);
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
