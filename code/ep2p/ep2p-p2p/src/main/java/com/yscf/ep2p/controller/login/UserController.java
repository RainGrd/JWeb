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
 * 2015年11月16日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.login;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.common.util.EncodedUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.common.util.InvitationCodeUtil;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.core.service.content.impl.ContAdvContentServiceImpl;
import com.yscf.core.service.sms.impl.SmsServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.ICusTomerDtoService;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.vo.system.CustomerVo;

/**
 * Description：<br> 
 * 用户控制层
 * @author  Yu.Zhang
 * @date    2015年11月16日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/login/userController")
public class UserController extends EscfBaseWebController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	public UserController(CusTomerServiceImpl service) {
		super(service);
	}
	
	//媒体动态
	@Resource(name = "columnContentService")
	ColumnContentServiceImpl columnContentService;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name = "smsService")
	private SmsServiceImpl smsService;
	
	@Resource(name = "cusTomerDtoService")
	private ICusTomerDtoService cusTomerDtoService;	// 客户信息

	 //邀请有奖 加载栏目位的
    @Resource(name="columnContentService")
    ColumnContentServiceImpl columnContentServiceImpl;
    
    //邀请有奖 加载广告位的
    @Resource(name="contAdvContentServiceImpl")
    ContAdvContentServiceImpl contAdvContentServiceImpl;
    
	@Resource(name="sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
    
	@Override
	public Class<?> getModel() {
		return CusTomer.class;
	}
	
	/**
	 * Description：<br> 
	 * 获取客户身份证号码
	 * @author  JingYu.Dai
	 * @date    2016年3月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getIdCard")
	@ResponseBody
	public ModelAndView getIdCard(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView();
		try {
			// 保存用户信息
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer t = service.getByUserId(getUserId());
			modelAndView.addObject("idCard", t.getIdentificationNo());
			// 3 结果集返回
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("获取用户身份证号失败！", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelAndView, e, request);
		}
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br> 
	 * 跳转到用户注册页面
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toRegistered")
	@ResponseBody
	public ModelAndView toRegistered(String referralCode,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView  = new ModelAndView("otherinfo/registered");
		List<ContAdvContent> list = contAdvContentServiceImpl.selectAdvContentByWebTag("ep2p_adv_registerPage_t_1");
		if(null != list && list.size() > 0 ){
			modelAndView.addObject("url", list.get(0).getUrl());
			modelAndView.addObject("filePath",  list.get(0).getFileUrl());
		}
		if(!"".equals(referralCode) && referralCode!=null){
			modelAndView.addObject("referralCode", referralCode);
			//如果flag=1 说明是从邀请有奖里面进去注册的，邀请码就为当前登录人的邀请码
			modelAndView.addObject("flag", flag);
		}
		return modelAndView;
	}
	
	
	
	
	
	
	/**
	 * 
	 * Description：<br> 
	 * 用户提交注册
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/registeredSubmit")
	@ResponseBody
	public ModelAndView registeredSubmit(CusTomer customer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			// 解密用户名密码
			String phoneNo = new String(RSAUtil.decode(customer.getPhoneNo()));
			String password =  new String(RSAUtil.decode(customer.getPassword()));
			customer.setPhoneNo(phoneNo);
			customer.setPassword(password);
			
			// 保存用户信息
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			customer.setRegistrationTime(DateUtil.format(DateUtil.getToday()));
			customer.setCreateTime(DateUtil.format(DateUtil.getToday()));
			service.insert(customer);
			
			// 注册成功，发送注册成功信息
			 smsService.sendSmsCode(phoneNo, "APP用户注册","REGISTERED_SUC_CODE",null);
			 
			// 加载数据至session中
			CusTomer sessionCusTomer = service.selectCusByPid(customer.getPid()).get(0);
			if(null == sessionCusTomer.getCustomerName()){
				sessionCusTomer.setCustomerName(sessionCusTomer.getPhoneNo());
			}
			
			CustomerVo custvo = ConvertObjectUtil.convertObject(sessionCusTomer, CustomerVo.class);
			HttpSession session = getSession();
			memcachedClient.add(session.getId(), 1800, custvo);
			
			// 3 结果集返回
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("用户注册验证信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 用户注册验证:<br>
	 * 1 手机号码是否已经注册<br>
	 * 2 待添加
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/registeredValidate")
	@ResponseBody
	public ModelAndView registeredValidate(CusTomer customer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			// 查询手机号码是否已经存在，过滤禁用、删除的客户
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			
			boolean result = service.validatePhoneNo(customer);
			modelView.addObject("result", result);
			// 3 结果集返回
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("用户注册验证信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取用户信息:<br>
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getCustomerVo")
	@ResponseBody
	public ModelAndView getCustomerVo(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CustomerDto customerDto = cusTomerDtoService.getCustomerDtoByPid(super.getUserId());
			CustomerVo custvo = ConvertObjectUtil.convertObject(customerDto, CustomerVo.class);
			modelView.addObject("result", custvo);
			SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("management_rate");
			modelView.addObject("managementRate", sysParams.getParamValue());
			// 3 结果集返回
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("获取用户信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 验证码验证
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/kaptchaValidate")
	@ResponseBody
	public ModelAndView kaptchaValidate(String kaptcha,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			//从session中获取google kaptcha 插件生成的验证码
			String googlecode = request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString() ;
			
			//比较验证码正确性
			if(googlecode.equalsIgnoreCase(kaptcha)){
				modelView.addObject("result", true);
			}else {
				modelView.addObject("result", false);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到忘记密码页面
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toRetrievePage")
	public String toRetrievePage(String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		request.setAttribute("flag",flag);
		return "temp.retrieve.page";
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转邮件发送成功页面
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toEmailRetrievePage")
	public ModelAndView toEmailRetrievePage(String loginName,String email,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("otherinfo/retrievePwdByEmail");
		try{
			modelView.addObject("loginName", loginName);
			modelView.addObject("email",emailEncryption(email));
			if("1".equals(flag)){
				modelView.addObject("flag",1);
			}else{
				modelView.addObject("flag",0);
			}
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * Description：<br> 
	 * 页面跳转：安全中心>>>个人资料>>>更改安全手机>>>更换安全手机选择更换方式页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param phone 手机号码
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyChanemodePage")
	public ModelAndView toSafetyChanemodePage(String phone,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyephone/safetyChanemode.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_chanemode");
		try{
			modelView.addObject("phoneEncryption",phoneNoEncryption(phone));
			modelView.addObject("phone",phone);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * Description：<br> 
	 * 页面跳转：安全中心>>>个人资料>>>更改安全手机>>>更换原手机号码页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param phone 手机号码
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyVerifyMobilePage")
	public ModelAndView toSafetyVerifyMobilePage(String phone,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyphone/safetyVerifymobile.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_verify_mobile");
		try{
			modelView.addObject("phoneEncryption",phoneNoEncryption(phone));
			modelView.addObject("phone",phone);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 页面跳转：安全中心>>>个人资料>>>更改安全手机>>>更换验证身份证页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param phone 手机号码
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyVerifyIdForMobilPage")
	public ModelAndView toSafetyVerifyIdForMobilPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyphone/safetyVerifyIdForMobil.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_verify_id_for_mobil");
		try{
			CustomerVo vo = getContextUser();
			modelView.addObject("userName",vo.getCustomerName());
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 页面跳转：安全中心>>>个人资料>>>更改安全手机>>>更换手机  验证新手机页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param phone 手机号码 (editType==2 时可以为空)
	 * @param editType 修改类型(1:手机号码加交易密码修改、2：手机号码加身份证号码)
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSsafetyNewMobilePage")
	public ModelAndView toSsafetyNewMobilePage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyphone/safetyNewmobile.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_new_mobile");
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 页面跳转：安全中心>>>个人资料>>>更改安全手机>>>更换手机绑定成功页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param phone 手机号码
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyChanewinPage")
	public ModelAndView toSafetyChanewinPage(String phone,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyphone/safetyChanewin.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_chanewin");
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = new CusTomer();
			cusTomer.setPid(getUserId());
			cusTomer.setPhoneNo(phone);
			service.updateByPrimaryKeySelective(cusTomer);
			modelView.addObject("phoneEncryption",phoneNoEncryption(phone));
			modelView.addObject("phone",phone);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	/**
	 * Description：<br> 
	 * 页面跳转： 安全中心>>>个人资料>>>更改安全邮箱>>>选择更换页面方式页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param email 邮箱号
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyChangeemailPage")
	public ModelAndView toSafetyChangeemailPage(String email,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyChangeemail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_change_email");
		try{
			modelView.addObject("encryptionEmail",emailEncryption(email));
			modelView.addObject("email",email);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 页面跳转： 安全中心>>>个人资料>>>更改安全邮箱>>>原邮箱更换页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param email 邮箱号
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyVerifyeMailforEmailPage")
	public ModelAndView toSafetyVerifyeMailforEmailPage(String email,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetyVerifyemailforemail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_verifye_mailfor_email");
		try{
			modelView.addObject("encryptionEmail",emailEncryption(email));
			modelView.addObject("email",email);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 页面跳转： 安全中心>>>个人资料>>>更改安全邮箱>>>手机号更换安全邮箱页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param email 邮箱号
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetyVerifyMobileforeMailPage")
	public ModelAndView toSafetyVerifyMobileforeMailPage(String email,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面： WEB-INF/view/personcenter/securitycenter/safetyVerifymobileforemail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_verify_mobilefor_email");
		try{
			CustomerVo vo = getContextUser();
			modelView.addObject("phoneEncryption",phoneNoEncryption(vo.getPhoneNo()));
			modelView.addObject("phone",vo.getPhoneNo());
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回邮箱页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 页面跳转： 安全中心>>>个人资料>>>更改安全邮箱>>>更换已发送提醒页面
	 * @author  JingYu.Dai
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param email 邮箱号
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSafetySentEmailPage")
	public ModelAndView toSafetySentEmailPage(String email,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		//映射页面：/WEB-INF/view/personcenter/securitycenter/safetySentEmail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_sent_email");
		try{
			modelView.addObject("encryptionEmail",emailEncryption(email));
			modelView.addObject("email",email);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 客户修改、绑定邮箱
	 * 安全中心>>>个人资料>>>更改安全邮箱>>>更换已发送提醒页面
	 * @author  JingYu.Dai
	 * @date    2016年3月7日
	 * @version v1.0.0
	 * @param emailAddres
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/updateEmail")
	public ModelAndView updateEmail(String query,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException, UnsupportedEncodingException {
		//映射页面：WEB-INF/view/personcenter/securitycenter/safetySentEmail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_end_email");
		String userId = getUserId();
		if(null == userId){
			modelView = new ModelAndView("otherinfo/login");
			return modelView;
		}
		String param = EncodedUtil.getDecodedStr(query,  Constant.RESET_PWD_SALT);
		String[] params = param.split("&");
		String invalidTime = params[0].split("=")[1];
		String emailAddress = (params[1].split("="))[1];
		try{
			// 判断日期是否过期
			if(!EscfDateUtil.dateCompare(DateUtil.format(DateUtil.getToday()), invalidTime)){
				CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
				CusTomer cusTomer = new CusTomer();
				cusTomer.setPid(getUserId());
				//emailAddress = EncodedUtil.getEncodedStr(emailAddress, Constant.RESET_PWD_SALT);
				cusTomer.setEmail(emailAddress);
				int restCount = service.updateByPrimaryKeySelective(cusTomer);
				if(0 < restCount){
					modelView.addObject("result", true);
				}else{
					modelView.addObject("result", false);
				}
			}else{
				modelView =  new ModelAndView("temp.invalid.page");
			}
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("绑定邮箱失败！", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 安全中心>>>个人资料>>>更改安全邮箱>>>更换填写新邮箱页面 
	 * @author  JingYu.Dai
	 * @date    2016年3月10日
	 * @version v1.0.0
	 * @param query
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/openUpdateEmail")
	public ModelAndView openUpdateEmail(String query,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException, UnsupportedEncodingException {
		//映射页面：WEB-INF/view/personcenter/securitycenter/safetyNewemail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_new_email");
		String userId = getUserId();
		if(null == userId){
			modelView = new ModelAndView("otherinfo/login");
			return modelView;
		}
		String param = EncodedUtil.getDecodedStr(query,  Constant.RESET_PWD_SALT);
		String[] params = param.split("&");
		String invalidTime = params[0].split("=")[1];
		String emailAddress = (params[1].split("="))[1];
		try{
			// 判断日期是否过期
			if(!EscfDateUtil.dateCompare(DateUtil.format(DateUtil.getToday()), invalidTime)){
				modelView.addObject("email",emailAddress);
			}else{
				modelView =  new ModelAndView("otherinfo/login");
			}
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("绑定邮箱失败！", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * Description：<br> 
	 * 短信更换邮箱验证
	 * 安全中心>>>个人资料>>>更改安全邮箱>>>更换填写新邮箱页面 
	 * @author  JingYu.Dai
	 * @date    2016年3月10日
	 * @version v1.0.0
	 * @param query 交易密码
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/openSmsUpdateEmail")
	public ModelAndView openSmsUpdateEmail(String query,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException, UnsupportedEncodingException {
		//映射页面：WEB-INF/view/personcenter/securitycenter/safetyNewemail.jsp
		ModelAndView modelView = new ModelAndView("temp.usercenter.safety_new_email");
		//交易密码
		String tradePassword = new String(RSAUtil.decode(query));
		//验证交易密码
		int num;
		try {
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			num = service.vailidateTradePassword(tradePassword,getUserId());
			if(num > -1){
				MessageBuilder.processSuccess(modelView, 1,
						HttpMessage.ERROR_CODE, request);
				return modelView;
			}
		} catch (TradePwdIsBlankException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("手机验证修改邮箱：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		} catch (TradePwdFreezeException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("手机验证修改邮箱：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	@RequestMapping(value = "/openUpdatePhonePage")
	public ModelAndView openUpdatePhonePage(String query,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException, UnsupportedEncodingException {
		ModelAndView modelView = new ModelAndView("personcenter/securitycenter/safetyVerifymobile");
		CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
		CusTomer cusTomer =  service.getByUserId(getUserId());
		modelView.addObject("phone",cusTomer.getPhoneNo());
		modelView.addObject("phoneEncryption",phoneNoEncryption(cusTomer.getPhoneNo()));
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到手机找回密码页面
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toPhoneRetrievePage")
	public ModelAndView toPhoneRetrievePage(String loginName,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("otherinfo/retrievePwdByPhone");
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = service.selectByLoginName(loginName);
			// 需要调用发送短信接口
			if(null != cusTomer){
				modelView.addObject("phoneNoStr",super.phoneNoEncryption(cusTomer.getPhoneNo()) );
				modelView.addObject("phoneNo",cusTomer.getPhoneNo() );
			}
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("flag", flag);
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 根据邮箱找回密码链接跳转到输入密码页面 （登录密码）
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pwdType 重置密码类型（默认登录密码 ： 1：交易密码）
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toResetPwdByEmail")
	public ModelAndView toResetPwdByEmail(String referralCode,String invalidTime,String pwdType, HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		pwdType = EncodedUtil.getDecodedStr(pwdType, Constant.RESET_PWD_SALT);
		ModelAndView modelView ;
		if("1".equals(pwdType)){
			modelView = new ModelAndView("temp.resettrad.page");
		}else{
			modelView = new ModelAndView("temp.reset.page");
		}
		try{
			referralCode= EncodedUtil.getDecodedStr(referralCode, Constant.RESET_PWD_SALT);
			invalidTime = EncodedUtil.getDecodedStr(invalidTime, Constant.RESET_PWD_SALT);
			// 判断日期是否过期
			if(!EscfDateUtil.dateCompare(DateUtil.format(DateUtil.getToday()), invalidTime)){
				// 根据注册码查询用户信息
				CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
				CusTomer cusTomer = service.selectByReferralCode(referralCode);
				modelView.addObject("loginName", cusTomer.getEmail());
			}else{
				modelView =  new ModelAndView("temp.invalid.page");
			}
			
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到重置密码页面页面失败", e.getMessage());
			}
			modelView =  new ModelAndView("temp.invalid.page");
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到重置密码页面
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toResetPwdPage")
	public ModelAndView toResetPwdPage(String loginName,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("otherinfo/resetPwd");
		try{
			modelView.addObject("loginName", loginName);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到重置密码页面页面失败", e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到重置交易密码页面
	 * @author  heng.wang
	 * @date    2015年12月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toResetTradPwdPage")
	public ModelAndView toResetTradPwdPage(String loginName,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("otherinfo/resetTradPwd");
		try{
			modelView.addObject("loginName", loginName);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到重置交易密码页面页面失败", e.getMessage());
			}
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 重置密码
	 * @author  Yu.Zhang
	 * @date    2015年12月3日
	 * @version v1.0.0
	 * @param loginName
	 * @param newPassword
	 * @param newPasswordTo
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	public ModelAndView resetPwd(String loginName,String newPassword,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			loginName = new String(RSAUtil.decode(loginName));
			newPassword =  new String(RSAUtil.decode(newPassword));
			
			// 根据登录查询客户信息
			CusTomer cusTomer = service.selectByLoginName(loginName);
			cusTomer.setPassword(newPassword);
			
			// 重置密码
			service.resetPwd(cusTomer,null);
			
			// 重置密码成，发送短信
			smsService.sendSmsCode(loginName, "找回密码","REGISTERED_SUC_CODE",null);
			
			// 重置密码成功后销毁session
			HttpSession session = getSession();
			session.invalidate();
			
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 重置交易密码
	 * @author  heng.wang
	 * @date    2015年12月19日
	 * @version v1.0.0
	 * @param loginName
	 * @param newPassword
	 * @param newPasswordTo
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/resetTradPwd")
	@ResponseBody
	public ModelAndView resetTradPwd(String loginName,String newPassword,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			loginName = new String(RSAUtil.decode(loginName));
			newPassword =  new String(RSAUtil.decode(newPassword));
			// 根据登录查询客户信息
			CusTomer cusTomer = service.selectByLoginName(loginName);
			cusTomer.setPassword(newPassword);
			// 重置交易密码
			service.resetTradPwd(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到手机找回密码页面失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 验证登录名是否存在
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateLoginName")
	@ResponseBody
	public ModelAndView validateLoginName(String loginName,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = service.selectByLoginName(loginName);
			if(null == cusTomer){
				modelView.addObject("result", false);
			}else{
				modelView.addObject("result", true);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 根据登录名验证用户是否绑定邮箱
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pwdType 重置密码类型（默认登录密码 ： 1：交易密码）
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public ModelAndView sendEmail(String loginName,HttpServletRequest request,String pwdType, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = service.selectByLoginName(loginName);
			if(null != cusTomer){
				// 设置邮件参数
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("loginName", phoneNoEncryption(cusTomer.getPhoneNo()));
				String invalidTime = DateUtil.format(EscfDateUtil.addMinute(DateUtil.getToday(), 60));
				map.put("invalidTime",invalidTime );
				
				// 拼接邮件连接地址
				StringBuffer sb = new StringBuffer();
				String enReferralCode = EncodedUtil.getEncodedStr(cusTomer.getReferralCode(), Constant.RESET_PWD_SALT);
				String enInvalidTime = EncodedUtil.getEncodedStr(invalidTime, Constant.RESET_PWD_SALT);
				sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
				.append(request.getServerPort()).append( request.getContextPath()).append("/");
				sb.append("login/userController/toResetPwdByEmail.shtml?referralCode=")
				.append(enReferralCode).append("&invalidTime=").append(enInvalidTime)
				.append("&pwdType=").append(EncodedUtil.getEncodedStr(pwdType, Constant.RESET_PWD_SALT));
				map.put("mailHref",sb.toString());
				if("1".equals(pwdType)){
					// 发送邮件
					super.sendMail(cusTomer.getEmail(),"找回交易密码","email-reset-trad-pwd.ftl", map);
				}else{
					// 发送邮件
					super.sendMail(cusTomer.getEmail(),"找回登录密码","email-reset-pwd.ftl", map);
				}
				modelView.addObject("email", cusTomer.getEmail());
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}else{
				modelView.addObject("result", false);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据登录名验证用户是否绑定邮箱
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/bingEmail")
	@ResponseBody
	public ModelAndView bingEmail(String loginName,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = service.selectByLoginName(loginName);
			if(null != cusTomer && null!=cusTomer.getEmail() && !"".equals(cusTomer.getEmail())){
				modelView.addObject("result", true);
			}else{
				modelView.addObject("result", false);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID查推荐码
	 * @author  heng.wang
	 * @date    2015年12月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectReferralCodeByCustd")
	@ResponseBody
	public ModelAndView selectReferralCodeByCustd(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			String userId = super.getUserId();//获取当前登录id
			if(!"".equals(userId) && userId !=null){
				List<CusTomer> list = service.selectReferralCodeByCustd(userId);
				CusTomer cusTomer = list.get(0);
				if(null != cusTomer){
					cusTomer.setPassword(null);
				}
				modelView.addObject("data", cusTomer);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 邀请有奖：广告位查询
	 * @author heng.wang
	 * @date 2016年3月10日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectAdvCloum")
	@ResponseBody
	public ModelAndView selectAdvCloum(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		List<ContAdvContent> list = new ArrayList<ContAdvContent>();
		try {
			list = contAdvContentServiceImpl.selectAdvContentByWebTag("ep2p_adv_userPrizePage_t_1");
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("购买vip信息：广告位查询：", e.getMessage());
			}
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 邀请有奖：栏目位查询
	 * @author heng.wang
	 * @date 2016年3月10日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectPrizeCloum")
	@ResponseBody
	public ModelAndView selectPrizeCloum(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		ColumnContent columnContent = new ColumnContent();
		try {
			columnContent = columnContentServiceImpl.selectContentByWebTag("ep2p_col_userPrizePage_b_1");
			modelView.addObject("data", columnContent);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("购买vip信息：栏目位查询：", e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 根据推介码查询客户信息
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param referralCode
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByReferralCode")
	@ResponseBody
	public ModelAndView getByReferralCode(String referralCode,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = service.selectByReferralCode(referralCode);
			if(null != cusTomer){
				cusTomer.setPassword(null);
			}
			modelView.addObject("result", cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据创建时间查最后注册用户的推荐码
	 * @author  heng.wang
	 * @date    2015年12月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectLastReferralCode")
	@ResponseBody
	public ModelAndView selectLastReferralCode(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
//			String userId = super.getUserId();//获取当前登录id
			List<CusTomer> list = service.selectLastReferralCode();
			modelView.addObject("data", list.get(0));
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 查出最后创建的邀请码，然后生成一个新的，用于用户注册
	 * @author  heng.wang
	 * @date    2015年12月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/generateInvitaCode")
	@ResponseBody
	public ModelAndView generateInvitaCode(String referralCode,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
//			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
//			String userId = super.getUserId();//获取当前登录id
//			List<CusTomer> list = service.selectLastReferralCode();
			//把最后的邀请码传过去，在此基础上生成一个新的邀请码
			if(!"".equals(referralCode) && referralCode!=null){
				String genReferralCode = InvitationCodeUtil.createStatusCode(referralCode);
				modelView.addObject("data", genReferralCode);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("生成最新邀请码失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 邀请有奖：我分享过的合伙人
	 * @author  heng.wang
	 * @date    2015年12月7日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectMySharePartner")
	@ResponseBody
	public ModelAndView selectMySharePartner(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
			String userId = super.getUserId();//获取当前登录id
			List<CusTomer> list = service.selectMySharePartner(userId);
			//如果用户名为空就显示手机号码
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("查我分享过的合伙人失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 加载最新动态
	 * 
	 * @author heng.wang
	 * @date 2015年12月21日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectNewDynamic")
	@ResponseBody
	public ModelAndView selectNewDynamic(HttpServletRequest request, HttpServletResponse response,Integer pageIndex,
			Integer pageSize) {
		ModelAndView modelView = new ModelAndView();
		try {
			String webTag = "ep2p_col_news_t_1";
			pageSize=4;
			List<ColumnContent> list =  columnContentService.selectColContentByWebTagSpecial(webTag, pageIndex, pageSize);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询媒体动态信息失败：", e.getMessage());
				e.printStackTrace();
			}
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 加载媒体报道
	 * 
	 * @author heng.wang
	 * @date 2015年12月29日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectLatestReport")
	@ResponseBody
	public ModelAndView selectLatestReport(HttpServletRequest request, HttpServletResponse response,Integer pageIndex,
			Integer pageSize) {
		ModelAndView modelView = new ModelAndView();
		try {
			String webTag = "ep2p_col_mediaCoverage_t_1";
			pageSize=3;
			List<ColumnContent> list =  columnContentService.selectColContentByWebTagSpecial(webTag, pageIndex, pageSize);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询媒体动态信息失败：", e.getMessage());
				e.printStackTrace();
			}
		}
		return modelView;
	}
	
}


