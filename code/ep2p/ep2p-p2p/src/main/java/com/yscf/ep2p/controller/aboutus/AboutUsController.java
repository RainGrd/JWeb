/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 关于我们控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.aboutus;

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
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br>
 * 关于我们控制层
 * 
 * @author Lin Xu
 * @date 2015年11月12日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/about/aboutUsController")
public class AboutUsController extends EscfBaseWebController {

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
	public AboutUsController(ColumnContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ColumnContent.class;
	}

	/**
	 * Description：<br>
	 * 手机客户端页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月12日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toMobileSpecial")
	public String toMobileSpecial() {
		return "mobileSpecial.page";
	}

	/**
	 * Description：<br>
	 * 关于我们主界面
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toAboutUsPage")
	public String toAboutUsPage() {
		return "aboutus.list";
	}

	/**
	 * Description：<br>
	 * 加载关于我们左边的菜单数据（这是关于我们的页面）
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/index/toAboutUsLeftList")
	@ResponseBody
	public ModelAndView toAboutUsLeftList(HttpServletRequest request,
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
			if (StringUtil.isBlank(contentPid)) {
				contentPid = request.getParameter("contentPid");
			} else {
				modelAndView = new ModelAndView("aboutus.list");
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
	 * 
	 * Description：<br>
	 * 根据父级id 查询到子栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月17日
	 * @version v1.0.0
	 * @param borrowId
	 * @param request
	 * @param response
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/index/selectColumnContentByParentId")
	@ResponseBody
	public ModelAndView selectColumnContentByParentId(String contentPid,
			HttpServletRequest request, HttpServletResponse response,
			Integer pageIndex, Integer pageSize) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			List<ColumnContent> colContentList = columnContentService
					.selectColumnContentByParentId(contentPid, pageIndex,
							pageSize);
			List<ColumnContent> clocontent = columnContentService
					.selectColumnContentByParentId(contentPid, null, null);
			int total = clocontent == null ? 0 : clocontent.size();
			modelAndView.addObject("colContentList", colContentList);
			modelAndView.addObject("total", total);
			MessageBuilder.processSuccess(modelAndView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("查询借款详情失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processSuccess(modelAndView, e,
					HttpMessage.ERROR_MSG, request);
		}
		return modelAndView;
	}
}
