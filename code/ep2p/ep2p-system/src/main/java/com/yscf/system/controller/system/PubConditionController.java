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
package com.yscf.system.controller.system;

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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.PubCondition;
import com.yscf.core.service.system.impl.PubConditionServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : PubConditionController
 * @Description : 条件管理Controller
 * @Author : Qing.Cai
 * @Date : 2015年9月23日 上午10:44:02
 */
@Controller
@RequestMapping("/pubConditionController")
public class PubConditionController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(PubConditionController.class);

	@Autowired
	public PubConditionController(PubConditionServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PubCondition.class;
	}

	/**
	 * 
	 * @Description : 跳转到条件设定首页
	 * @return pubCondition_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午11:29:49
	 */
	@RequestMapping("/openPubCondition")
	public ModelAndView openPubCondition() {
		return new ModelAndView("/system/pubCondition_index");
	}

	/**
	 * 
	 * @Description : 跳转到条件设定编辑页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return pubCondition_add.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 下午4:47:21
	 */
	@RequestMapping("/openPubConditionAdd")
	public ModelAndView openPubConditionAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/system/pubCondition_add");
		// 赋值
		modelAndView.addObject("pid", request.getParameter("pid") != null ? request.getParameter("pid") : 0);
		return modelAndView;
	}

	/**
	 * 
	 * @Description : 查询条件信息,带分业
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubCondition
	 *            条件对象
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:46:33
	 */
	@RequestMapping(value = "/queryPubConditionList")
	@ResponseBody
	public ModelAndView queryPubConditionList(HttpServletRequest request, HttpServletResponse response, PubCondition pubCondition) throws HttpRequestException {
		PubConditionServiceImpl service = (PubConditionServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == pubCondition) {
				pubCondition = new PubCondition();
			}
			PageList<PubCondition> list = service.selectAllPage(pubCondition, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
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
	 * @Description : 查询所有有效的条件信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 有效的条件列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:43:41
	 */
	@RequestMapping(value = "/getAllCondition")
	@ResponseBody
	public ModelAndView getAllCondition(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		PubConditionServiceImpl service = (PubConditionServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			List<PubCondition> list = service.selectPubConditionBySatusEffective();
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
	 * @Description : 根据主键查询对象信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pid
	 *            主键PID
	 * @return 条件设定对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 下午5:00:35
	 */
	@RequestMapping(value = "/getPubConditionByPid")
	@ResponseBody
	public ModelAndView getPubConditionByPid(HttpServletRequest request, HttpServletResponse response, String pid) throws HttpRequestException {
		PubConditionServiceImpl service = (PubConditionServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			// 创建需要获取的对象名称
			PubCondition pubCondition = new PubCondition();
			pubCondition = (PubCondition) service.selectByPrimaryKey(pid);
			modelView.addObject("pubCondition", pubCondition);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询条件设定详细信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 条件设定编辑
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubCondition
	 *            条件设定对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 下午4:54:13
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response, PubCondition pubCondition) throws HttpRequestException {
		PubConditionServiceImpl service = (PubConditionServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 赋值创建人信息
			pubCondition.setCreateUser(getContextUser().getUserId());
			// 业务放到servicce层处理
			service.pubConditionEdit(pubCondition);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("条件设定新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 批量删除条件设定
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pids
	 *            条件设定主键字符串(1,2,3)
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午11:31:33
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response, String pids) throws HttpRequestException {
		PubConditionServiceImpl service = (PubConditionServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteBatch(pids);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("批量删除条件设定信息失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

}
