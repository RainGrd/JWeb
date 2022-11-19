/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 个人中心信息页面控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月19日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.activityarea;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustSignIn;
import com.yscf.core.service.activity.impl.PubActivityAreaServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustSignInServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * @ClassName : ActicityAreaController
 * @Description : 活动专区Controller
 * @Author : Qing.Cai
 * @Date : 2015年12月9日 下午3:47:38
 */
@Controller
@RequestMapping("/activityArea/acticityAreaController")
public class ActicityAreaController extends EscfBaseWebController {

	private Logger logger = LoggerFactory.getLogger(ActicityAreaController.class);

	@Autowired
	public ActicityAreaController(PubActivityAreaServiceImpl service) {
		super(service);
	}

	/** 签到操作服务接口 */
	@Resource(name = "custSignInServiceImpl")
	private CustSignInServiceImpl custSignInService;
	/** 客户服务接口 */
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;

	@Override
	public Class<?> getModel() {
		return PubActivityArea.class;
	}

	/**
	 * 
	 * @Description : 跳转到活动专区页面
	 * @return 活动专区首页
	 * @Author : Qing.Cai
	 * @Date : 2015年12月9日 下午3:43:32
	 */
	@RequestMapping("/index/toActivityArea")
	public String toActivityArea() {
		return "temp.activea.index";
	}

	/**
	 * 
	 * @Description : 查询签到达人榜(签到次数前五名)
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 签到达人榜
	 * @Author : Qing.Cai
	 * @Date : 2015年12月9日 下午8:55:02
	 */
	@RequestMapping(value = "/selectSignInDarenCharts")
	@ResponseBody
	public ModelAndView selectSignInDarenCharts(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			List<CustSignIn> list = custSignInService.selectSignInDarenCharts();
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询签到达人榜(签到次数前五名)：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 签到
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 新增签到
	 * @Author : Qing.Cai
	 * @Date : 2015年12月9日 下午9:31:22
	 */
	@RequestMapping(value = "/custSignInEdit")
	@ResponseBody
	public ModelAndView custSignInEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			CustSignIn custSignIn = new CustSignIn();
			custSignIn.setCustomerId(getUserId());// 设置用户ID
			custSignIn.setSignInPlatform(1);// 设置签到类型,web端
			custSignIn.setCreateUser(getUserId());// 设置创建人ID
			int count = custSignInService.custSignInEdit(custSignIn);
			// 判断是否签到
			if (count == 0) {
				MessageBuilder.processSuccess(modelView, null, HttpMessage.ERROR_CODE, request);
			} else {
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询签到达人榜(签到次数前五名)：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 前台_查询初始化的活动专区显示
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 活动专区初始显示列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午6:04:01
	 */
	@RequestMapping(value = "/selectShowActicityArea")
	@ResponseBody
	public ModelAndView selectShowActicityArea(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
			List<PubActivityArea> list = service.selectShowActicityArea(5);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("活动专区展示信息：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 前台_查询加载的活动专区信息
	 * @param pageIndex
	 *            第几次加载
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 活动专区加载列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午6:04:28
	 */
	@RequestMapping(value = "/selectLoadActicityArea")
	@ResponseBody
	public ModelAndView selectLoadActicityArea(Integer pageIndex, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
			// 判断是否为-1
			if (pageIndex == -1) {
				pageIndex = 5;
			}
			List<PubActivityArea> list = service.selectLoadActicityArea(pageIndex, 5);
			modelView.addObject("data", list);
			modelView.addObject("pageIndex", pageIndex + list.size());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("活动专区加载信息：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 加载客户信息
	 * @param request 
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 客户信息
	 * @Author : Qing.Cai
	 * @Date : 2016年1月19日 上午10:42:11
	 */
	@RequestMapping(value = "/selectPersonalIntegral")
	@ResponseBody
	public ModelAndView selectPersonalIntegral(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			// 根据客户ID查询客户对象
			CusTomer cusTomer = cusTomerService.getByUserId(getUserId());
			// 判断客户是否签到
			int isSignln = custSignInService.checkWhetheSignIn(getUserId());
			// 查询客户签到总数
			int signInSum = custSignInService.selectUserSignInSum(getUserId());
			modelView.addObject("sname", cusTomer.getSname());
			modelView.addObject("availablePoint", cusTomer.getAvailablePoint());
			modelView.addObject("isSignln", isSignln);
			modelView.addObject("signInSum", signInSum);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("加载客户信息：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

}
