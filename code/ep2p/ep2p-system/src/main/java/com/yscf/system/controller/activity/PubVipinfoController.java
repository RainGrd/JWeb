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
import com.yscf.core.model.activity.PubVipinfo;
import com.yscf.core.service.activity.impl.PubVipinfoServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : PubVipinfoController
 * @Description : VIP信息控制器
 * @Author : Qing.Cai
 * @Date : 2015年9月24日 下午4:32:14
 */
@Controller
@RequestMapping("/pubVipinfoController")
public class PubVipinfoController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(PubVipinfoController.class);

	@Autowired
	public PubVipinfoController(PubVipinfoServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PubVipinfo.class;
	}

	/**
	 * 
	 * @Description : 跳转到VIP信息设置列表
	 * @return pubVipinfo_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午4:58:36
	 */
	@RequestMapping("/openPubVipinfo")
	public ModelAndView openPubVipinfo() {
		return new ModelAndView("/activity/pubVipinfo_index");
	}

	/**
	 * 
	 * @Description : 跳转到新增/编辑VIP信息页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return pubVipinfo_add.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午3:33:54
	 */
	@RequestMapping("/openPubVipinfoAdd")
	public ModelAndView openPubVipinfoAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/activity/pubVipinfo_add");
		// 赋值
		modelAndView.addObject("pid", request.getParameter("pid") != null ? request.getParameter("pid") : 0);
		return modelAndView;
	}

	/**
	 * 
	 * @Description : 查询VIP信息列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubVipinfo
	 *            VIP信息对象
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午4:43:31
	 */
	@RequestMapping(value = "/queryPubVipinfoList")
	@ResponseBody
	public ModelAndView queryPubVipinfoList(HttpServletRequest request, HttpServletResponse response, PubVipinfo pubVipinfo) throws HttpRequestException {
		PubVipinfoServiceImpl service = (PubVipinfoServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == pubVipinfo) {
				pubVipinfo = new PubVipinfo();
			}
			PageList<PubVipinfo> list = service.selectAllPage(pubVipinfo, info);
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
	 * @Date : 2015年9月24日 下午2:19:08
	 */
	@RequestMapping(value = "/getPubVipinfoByPid")
	@ResponseBody
	public ModelAndView getPubVipinfoByPid(HttpServletRequest request, HttpServletResponse response, String pid) throws HttpRequestException {
		PubVipinfoServiceImpl service = (PubVipinfoServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			// 创建需要获取的对象名称
			PubVipinfo pubVipinfo = new PubVipinfo();
			pubVipinfo = (PubVipinfo) service.selectByPrimaryKey(pid);
			modelView.addObject("pubVipinfo", pubVipinfo);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询VIP信息详细信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 编辑VIP信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubVipinfo
	 *            VIP信息对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 上午11:21:39
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response, PubVipinfo pubVipinfo) throws HttpRequestException {
		PubVipinfoServiceImpl service = (PubVipinfoServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 赋值创建人信息
			pubVipinfo.setCreateUser(getContextUser().getUserId());
			// 业务放到servicce层处理
			service.pubVipinfoEdit(pubVipinfo);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("VIP信息新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 批量删除VIP信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pids
	 *            VIP信息ID(1,2,3)
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:59:14
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response, String pids) throws HttpRequestException {
		PubVipinfoServiceImpl service = (PubVipinfoServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteBatch(pids);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("除VIP信息失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

}
