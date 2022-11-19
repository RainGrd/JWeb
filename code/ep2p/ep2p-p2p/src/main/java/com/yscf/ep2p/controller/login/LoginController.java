/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户登录控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月16日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.login;

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
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.core.service.content.impl.ContAdvContentServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.ILoginEptpService;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.ucenter.BBSApi;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.util.CookieUtil;
import com.yscf.ep2p.vo.system.CustomerVo;
import com.yscf.ep2p.vo.system.LoginVo;

/**
 * Description：<br> 
 * 用户登录控制层
 * @author  Yu.Zhang
 * @date    2015年11月16日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/login/loginController")
public class LoginController extends EscfBaseWebController {
	
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;	// 系统参数
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name="loginEptpService")
	private ILoginEptpService loginEptpService;
	
	@Resource(name="columnContentService")
	private ColumnContentServiceImpl columnContentServiceImpl;
	
	@Resource(name="contAdvContentServiceImpl")
    ContAdvContentServiceImpl contAdvContentServiceImpl;

	@Autowired
	public LoginController(CusTomerServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CusTomer.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到用户登录页面
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String loginName = CookieUtil.getCookieLoginName(request);
		ModelAndView modelView = new ModelAndView("otherinfo/login");
		List<ContAdvContent> list = contAdvContentServiceImpl.selectAdvContentByWebTag("ep2p_adv_loginPage_t_1");
		if(null != list && list.size() > 0 ){
			modelView.addObject("url", list.get(0).getUrl());
			modelView.addObject("filePath",  list.get(0).getFileUrl());
		}
		modelView.addObject("loginName", loginName);
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 用户登录
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public ModelAndView login(LoginVo loginVO,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			// 解密用户名密码
			String loginName = new String(RSAUtil.decode(loginVO.getLoginName()));
		    String password =  new String(RSAUtil.decode(loginVO.getPassword()));
			Map<String,Object > loginResult = loginEptpService.login(loginName, password);
			Boolean result = (Boolean) loginResult.get("result");
			// 登录是否成功 
			if(result){
				// 加载数据至session中
				CustomerDto sessionCusTomer = (CustomerDto) loginResult.get(Constant.CUS_KEY);
				if(null == sessionCusTomer.getSname()){
					sessionCusTomer.setSname(sessionCusTomer.getPhoneNo());
				}
				
				CustomerVo custvo = ConvertObjectUtil.convertObject(sessionCusTomer, CustomerVo.class);
				HttpSession session = getSession();
				memcachedClient.add(session.getId(), 1800, custvo);//(session.getId(), custvo/*,new Date(System.currentTimeMillis() + 1*60*1000)*/);
				//session.setAttribute(PtpConstants.CONSUMER, custvo);
				
				// 记住用户名
				CookieUtil.saveCookie(request, response, loginName, loginVO.getIsSaveCookie());
			}
			modelView.addAllObjects(loginResult);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("用户登录失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 用户退出
	 * @author  Yu.Zhang
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/loginout")
	public String loginout(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HttpSession session = getSession();
		session.invalidate();
		BBSApi.logout();
		return "index.page";
	}
	
	
}


