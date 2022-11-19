/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 帮助中心控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.helpcenter;

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

import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.core.service.content.impl.ContColumnServiceImpl;
import com.yscf.ep2p.controller.aboutus.AboutUsController;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br>
 * 帮助中心控制层
 * 
 * @author Lin Xu
 * @date 2015年11月12日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/help/helpCenterController")
public class HelpCenterController extends EscfBaseWebController {
	private Logger logger = LoggerFactory.getLogger(AboutUsController.class);
	/**
	 * 栏目 服务层
	 */
	@Resource(name = "contColumnServiceImpl")
	private ContColumnServiceImpl contColumnService;
	/**
	 * 栏目内容 服务层
	 */
	@Resource(name = "columnContentService")
	private ColumnContentServiceImpl columnContentService;

	@Autowired
	public HelpCenterController(ColumnContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ColumnContent.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到 帮助中心页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月18日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toHelpCenterPage")
	public ModelAndView toHelpCenterPage() {
		ModelAndView mv = new ModelAndView("helpCenter.list");
		mv.addObject("webTag", "ep2p_col_helpCenterPage_t_1");
		return mv;
	}

	/**
	 * Description：<br>
	 * 加载"帮助中心"左边的菜单数据（这是关于我们的页面）
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param webTag
	 *            顶级栏目标示 如 帮助中心 关于我们
	 * @param webTagLow
	 *            对应的是三级栏目的前台标示 如 帮助中心>>媒体报道 、关于我们>>首页广告 之类
	 * @return
	 */
	@RequestMapping("/index/toHelpCenterLeftList")
	@ResponseBody
	public ModelAndView toHelpCenterLeftList(HttpServletRequest request,
			HttpServletResponse response, String webTag, String contentPid) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (StringUtil.isBlank(webTag)) {
				webTag = request.getParameter("webTag");
			}

			// 查询 关于我们的栏目内容
			if (StringUtil.isBlank(webTag)) {
				modelAndView.addObject("retCode", "500");
				modelAndView.addObject("retMessage", "数据传输出错,请联系管理员!");
			}
			List<ColumnContent> colContenetList = columnContentService
					.selectColContentByWebTag(webTag);
			if ("".equals(contentPid) || null == contentPid
					|| "undefined".equals(contentPid)) {
				contentPid = request.getParameter("contentPid");
			} else {
				modelAndView = new ModelAndView("helpCenter.list");
				modelAndView.addObject("contentPid", contentPid);
				return modelAndView;
			}
			modelAndView.addObject("colContentList", colContenetList);
			modelAndView.addObject("retCode", "200");
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("查询出错", e.getMessage());
			}
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 查询特殊的栏目内容 如 系统公告 媒体动态 最新报道
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param webTag
	 * @param request
	 * @param response
	 * @param pageIndex
	 *            当前页 为空查所有
	 * @param pageSize
	 *            一页多少条 为空查所有
	 * @return
	 */
	@RequestMapping("/index/selectColContentByWebTagSpecial")
	@ResponseBody
	public ModelAndView selectColContentByWebTagSpecial(String webTag,
			Integer pageIndex, Integer pageSize, Integer isHome,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		// 查询 关于我们的栏目内容
		if (StringUtil.isBlank(webTag)) {
			modelAndView.addObject("retCode", "500");
			modelAndView.addObject("retMessage", "数据传输出错,请联系管理员!");
		}
		try {
			List<ColumnContent> colContentList = columnContentService
					.selectColContentListByWebTag(webTag, null, pageIndex,
							pageSize, isHome);
			modelAndView.addObject("colContentList", colContentList);
			modelAndView.addObject("retCode", "200");
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询异常", e.getMessage());
		}
		return modelAndView;
	}

	/**
	 * Description：<br>
	 * 根据webTag 查询对应的栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月2日
	 * @version v1.0.0
	 * @param webTag
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/index/selectContentByWebTag")
	@ResponseBody
	public ModelAndView selectContentByWebTag(String webTag,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		// 查询 关于我们的栏目内容
		if (StringUtil.isBlank(webTag)) {
			modelAndView.addObject("retCode", "500");
			modelAndView.addObject("retMessage", "数据传输出错,请联系管理员!");
		}
		try {
			ColumnContent colContent = columnContentService
					.selectContentByWebTag(webTag);
			modelAndView.addObject("colContent", colContent);
			modelAndView.addObject("retCode", "200");
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询异常", e.getMessage());
		}
		return modelAndView;
	}
}
