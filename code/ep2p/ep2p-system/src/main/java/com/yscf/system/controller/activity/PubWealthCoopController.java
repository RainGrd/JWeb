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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.activity.PubWealthCooperation;
import com.yscf.core.service.activity.IPubWealthCoopService;
import com.yscf.core.service.activity.impl.PubWealthCoopServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : PubWealthCoopGoldController
 * @Description : 财富合伙人信息控制器
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午4:32:14
 */
@Controller
@RequestMapping("/pubWealthCoopController")
public class PubWealthCoopController extends EscfBaseController {
	private Logger logger = LoggerFactory
			.getLogger(PubWealthCoopController.class);

	@Autowired
	public PubWealthCoopController(PubWealthCoopServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PubWealthCooperation.class;
	}

	/**
	 * 
	 * @Description : 跳转到财富合伙人设置列表
	 * @return pubWealthCoop_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:58:36
	 */
	@RequestMapping(value = "/openPubWealthCoop")
	public ModelAndView openPubWealthCoop() {
		return new ModelAndView("/activity/pubWealthCoop_index");
	}

	/**
	 * 
	 * @Description : 跳转到新增/编辑财富合伙人页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return pubWealthCoop_add.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年9月18日 下午3:33:54
	 */
	@RequestMapping(value = "/openPubWealthCoopAdd")
	public ModelAndView openPubWealthCoopAdd(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(
				"/activity/pubWealthCoop_add");
		// 赋值
		modelAndView.addObject(
				"pid",
				request.getParameter("pid") != null ? request
						.getParameter("pid") : 0);
		return modelAndView;
	}

	/**
	 * 
	 * @Description : 查询财富合伙人信息列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubWealthCoop
	 *            财富合伙人对象
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:43:31
	 */
	@RequestMapping(value = "/queryPubWealthCoopList")
	@ResponseBody
	public ModelAndView queryPubWealthCoopList(HttpServletRequest request,
			HttpServletResponse response, PubWealthCooperation pubWealthCoop)
			throws HttpRequestException {
		IPubWealthCoopService service = (PubWealthCoopServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == pubWealthCoop) {
				pubWealthCoop = new PubWealthCooperation();
			}
			PageList<PubWealthCooperation> list = service.selectAllPage(
					pubWealthCoop, info);
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

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pid
	 *            主键ID
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午2:19:08
	 */
	@RequestMapping(value = "/getPubWealthCoopByPid")
	@ResponseBody
	public ModelAndView getPubWealthCoopByPid(HttpServletRequest request,
			HttpServletResponse response, String pid)
			throws HttpRequestException {
		PubWealthCoopServiceImpl service = (PubWealthCoopServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			// 创建需要获取的对象名称
			PubWealthCooperation pubWealthCoop = new PubWealthCooperation();
			pubWealthCoop = (PubWealthCooperation) service
					.selectByPrimaryKey(pid);
			modelView.addObject("pubWealthCoop", pubWealthCoop);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询财富合伙人活动详细信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 编辑财富合伙人活动
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubWealthCoop
	 *            财富合伙人对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月21日 上午11:21:39
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response,
			PubWealthCooperation pubWealthCoop) throws HttpRequestException {
		PubWealthCoopServiceImpl service = (PubWealthCoopServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {// 赋值创建人信息
			pubWealthCoop.setCreateUser(getContextUser().getUserName());
			// 业务放到servicce层处理
			service.PubWealthCoopEdit(pubWealthCoop);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("财富合伙人活动新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 批量删除财富合伙人活动
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pids
	 *            财富合伙人ID(1,2,3)
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:59:14
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request,
			HttpServletResponse response, String pids)
			throws HttpRequestException {
		PubWealthCoopServiceImpl service = (PubWealthCoopServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteBatch(pids);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("除财富合伙人失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

}
