/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 *  
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月13日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.activity;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;
import com.yscf.core.service.activity.IActBirPriItemActivityRelService;
import com.yscf.core.service.activity.impl.ActBirPriItemActivityRelServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 生日特权项和活动关系表 控制器
 * 
 * @author fengshiliang
 * @date 2015年10月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/actBirPriItemActivityRelController")
public class ActBirPriItemActivityRelController extends EscfBaseController {

	@Autowired
	public ActBirPriItemActivityRelController(
			ActBirPriItemActivityRelServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory
			.getLogger(ActBirPriItemActivityRelController.class);

	@Override
	public Class<?> getModel() {
		return ActBirPriItemActivityRel.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询 t_pub_jiaxi_ticket 、t_pub_invest_award 、t_pub_redpacket t_pub_exp_gold
	 * 、t_pub_jingyan_ticket 、t_pub_point_ticket 表内特权是vip特权生日 并且当前时间还在适用时间内的特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBirthdayPrivilegePage")
	@ResponseBody
	public ModelAndView toBirthdayPrivilegePage(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"activity/birthdayPrivilegeList");
		try {
			IActBirPriItemActivityRelService service = (ActBirPriItemActivityRelServiceImpl) getService();

			List<ActBirPriItemActivityRel> priList = service.selectPrivilege();
			modelView.addObject("priList", priList);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
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
	 * 跳转到vip生日特权明细 页面
	 * 
	 * @author fengshiliang
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param activityRel
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBirPriDetails")
	@ResponseBody
	public ModelAndView toBirPriDetails(HttpServletRequest request,
			HttpServletResponse response, ActBirPriItemActivityRel activityRel)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView(
				"/activity/actBirPriDetails_index");
		String activityId = request.getParameter("activityId");
		modelAndView.addObject("activityId", activityId);
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * VIP生日特权活动查询
	 * 
	 * @author fengshiliang
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param info
	 * @return
	 */
	@RequestMapping(value = "/selectBirPriItem")
	@ResponseBody
	public ModelAndView selectBirPriItem(HttpServletRequest request,
			HttpServletResponse response, ActBirPriItemActivityRel activityRel)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		IActBirPriItemActivityRelService service = (ActBirPriItemActivityRelServiceImpl) getService();
		try {
			PageInfo info = getPageInfo(request);
			if (null == activityRel) {
				activityRel = new ActBirPriItemActivityRel();
			}
			PageList<ActBirPriItemActivityRel> list = service.selectBirPriItem(
					activityRel, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
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
