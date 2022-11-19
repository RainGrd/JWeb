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
package com.yscf.ep2p.controller.forum;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.ucenter.BBSApi;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br> 
 * 用户登录控制层
 * @author  Yu.Zhang
 * @date    2015年11月16日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/forum/forumController")
public class ForumController extends EscfBaseWebController {
	
	private Logger logger = LoggerFactory.getLogger(ForumController.class);
	
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;
	
	@Autowired
	public ForumController(CusTomerServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CusTomer.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到理财论坛页面
	 * @author  Yu.Zhang
	 * @date    2015年12月7日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toForum")
	public ModelAndView toForum(String isLogin,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("bankcommuity/bankcommuity");
		try{
			if(null!=isLogin && "yes".equals(isLogin)){
				String userId = super.getUserId();//获取当前登录id
				CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
				CusTomer cusTomer = service.selectByPrimaryKey(userId);
				if(null !=cusTomer.getCustomerName() && !"".equals(cusTomer.getCustomerName())){
					// 已经设置过用户名，登录论坛。
					String result = BBSApi.login(cusTomer.getCustomerName(), cusTomer.getPassword());
					request.setAttribute("forumLoginResult", result);
				}
			}else{
				request.setAttribute("forumLoginResult", "");
			}
			
			// 查询系统参数中配置的论坛地址
			SysParams sysParams =  sysParamsService.getParamsByParamKey("FORUM_ADDRESS");
			request.setAttribute("forumAddress", sysParams.getParamValue());
			
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("跳转到理财论坛页面失败", e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到理财论坛页面
	 * @author  Yu.Zhang
	 * @date    2015年12月7日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validate")
	@ResponseBody
	public ModelAndView validate(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			// 判断用户是否登录
			HttpSession session = getSession();
			if(null == session.getAttribute(PtpConstants.CONSUMER)){
				// 用户未登录 不处理，直接跳到论坛页面
				modelView.addObject("isLogin", "no");
			}else{
				// 检查当前用户是否设置用户名，未设置则提醒用户设置用户名。已设置则登录论坛
				String userId = super.getUserId();//获取当前登录id
				CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
				CusTomer cusTomer = service.selectByPrimaryKey(userId);
				if(null == cusTomer.getCustomerName() || "".equals(cusTomer.getCustomerName())){
					// 未设置用户名
					modelView.addObject("isSetName", "no");
				}
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
	
}


