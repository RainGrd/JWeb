/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;
import com.yscf.core.model.activity.ActParConRel;
import com.yscf.core.service.activity.IActActivityService;
import com.yscf.core.service.activity.impl.ActActivityServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : ActActivityController
 * @Description : 活动管理控制器
 * @Author : Qing.Cai
 * @Date : 2015年9月22日 下午3:24:10
 */
@Controller
@RequestMapping("/actActivityController")
public class ActActivityController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(ActActivityController.class);

	@Autowired
	public ActActivityController(ActActivityServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ActActivity.class;
	}

	/**
	 * 
	 * @Description : 跳转到系统活动查询页面
	 * @return actActivity_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 下午2:53:24
	 */
	@RequestMapping("/openActActivity")
	public ModelAndView openActActivity(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("status", request.getParameter("status"));
		return new ModelAndView("/activity/actActivity_index");
	}

	/**
	 * 
	 * @Description : 查询系统活动列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param actActivity
	 *            系统活动对象
	 * @return 系统活动列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 下午3:47:13
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request, HttpServletResponse response, ActActivity actActivity) throws HttpRequestException {
		ActActivityServiceImpl service = (ActActivityServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == actActivity) {
				actActivity = new ActActivity();
			}
			PageList<ActActivity> list = service.selectAllPage(actActivity, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 根据活动ID查询活动已有条件信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param objectId
	 *            某个活动ID
	 * @param activityType
	 *            活动类型
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午11:05:24
	 */
	@RequestMapping(value = "/selectActParConRelByObjectId")
	@ResponseBody
	public ModelAndView selectActParConRelByObjectId(HttpServletRequest request, HttpServletResponse response, String objectId, String activityType) throws HttpRequestException {
		ActActivityServiceImpl service = (ActActivityServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			List<ActParConRel> list = service.selectActParConRelByObjectId(objectId, activityType);
			modelView.addObject("data", list);
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

	@RequestMapping(value = "/selectBirPriByObjectId")
	@ResponseBody
	public ModelAndView selectBirPriByObjectId(HttpServletRequest request, HttpServletResponse response, String objectId) throws HttpRequestException {
		IActActivityService service = (ActActivityServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			List<ActBirPriItemActivityRel> list = service.selectBirPriByObjectId(objectId);
			modelView.addObject("data", list);
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
	 * 跳转到 vip生日特权查询页面
	 * 
	 * @author fengshiliang
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param objectId
	 * @param activityType
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBirthdayPrivilegePage")
	@ResponseBody
	public ModelAndView toBirthdayPrivilegePage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("activity/birthdayPrivilegeList");
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 
	 * 
	 * @author fengshiliang
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param objectId
	 * @param activityType
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBirPriPage")
	@ResponseBody
	public ModelAndView toBirPriAddPage(HttpServletRequest request, HttpServletResponse response, String pid) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("activity/birthdayPrivilegeAddOrUpdate");
		modelView.addObject("pid", pid);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 加载vip生日特权设置 集合页面数据
	 * 
	 * @author fengshiliang
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBirPriListData")
	@ResponseBody
	public ModelAndView toBirPriListData(HttpServletRequest request, HttpServletResponse response, ActActivity actActivity) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("");
		PageInfo info = getPageInfo(request);
		IActActivityService service = (ActActivityServiceImpl) getService();
		// 状态为-1 设置为空
		if (!StringUtil.isBlank(actActivity.getStatus()) && "0".equals(actActivity.getStatus())) {
			actActivity.setStatus("");
		}

		PageList<ActActivity> actList = service.selectActActivityByParam(actActivity, info);
		modelView.addObject("rows", actList);
		modelView.addObject("total", actList.getPaginator().getTotalCount());
		MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存vip生日特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param actActivity
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveOrUpdatePir")
	@ResponseBody
	public void saveOrUpdatePir(ActActivity actActivity, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			IActActivityService service = (ActActivityServiceImpl) getService();
			// -1 为 新增
			if ("-1".equals(actActivity.getPid()) || !StringUtil.isBlank(actActivity.getPid())) {
				actActivity.setCreateUser(getContextUser().getUserName());
			} else {
				actActivity.setLastUpdateUser(getContextUser().getUserName());
			}

			service.saveOrUpdatePir(actActivity);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}

			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 批量删除
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pids
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response, String pids) throws HttpRequestException {
		IActActivityService service = (ActActivityServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteBatch(pids);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("删除Vip生日特权信息失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到vip生日特权修改页面
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/openVIPBirPriEditPage")
	public ModelAndView openVIPBirPriEditPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/activity/birthdayPrivilegeAddOrUpdate");
		// 赋值
		modelAndView.addObject("pid", !StringUtil.isBlank(request.getParameter("pid")) ? request.getParameter("pid") : "-1");
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据id获取vip活动信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pids
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getActivityByPid")
	@ResponseBody
	public ModelAndView getActivityByPid(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		ModelAndView modelAndView = new ModelAndView();
		IActActivityService service = (ActActivityServiceImpl) getService();
		String pid = request.getParameter("pid");
		try {
			// 业务放到servicce层处理
			ActActivity actActivity = (ActActivity) service.selectByPrimaryKey(pid);
			modelAndView.addObject("actActivity", actActivity);
			MessageBuilder.processSuccess(modelAndView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("除体验金失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		return modelAndView;
	}
}
