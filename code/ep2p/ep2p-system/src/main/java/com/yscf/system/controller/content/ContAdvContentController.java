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
 * 2015年9月24日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

import java.util.Date;
import java.util.HashMap;
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.PromptMsgConstants;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.content.IContAdvContentService;
import com.yscf.core.service.content.impl.ContAdvContentServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 广告内容管理控制器
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/contAdvContentController")
public class ContAdvContentController extends EscfBaseController {
	private Logger logger = LoggerFactory
			.getLogger(ContAdvContentController.class);

	@Autowired
	public ContAdvContentController(ContAdvContentServiceImpl service) {
		super(service);
		// Auto-generated constructor stub
	}

	@Override
	public Class<?> getModel() {
		return ContAdvContent.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据栏目id 查询栏目内容
	 * 
	 * @author fengshiliang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdvContentList")
	@ResponseBody
	public ModelAndView toAdvContentList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/content/advertisementManage/advContentList");
		request.setAttribute("advId", request.getParameter("advId"));
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 广告栏目查询广告内容
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getAdvContentByAdvId")
	@ResponseBody
	public ModelAndView getAdvContentByAdvId(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		String tag = request.getParameter("tag");// 页面表示 0 为二级栏目内容 1为三级栏目内容
		IContAdvContentService service = (ContAdvContentServiceImpl) getService();
		try {
			PageInfo info = getPageInfo(request);
			ContAdvContent column = (ContAdvContent) getEntity(request);
			if (column == null) {
				column = new ContAdvContent();
			}
			column.setAdvId(request.getParameter("advId"));// 父级 栏目id

			PageList<ColumnContent> list = service.selectAdvContentByParameter(
					column, info);
			request.setAttribute("tag", tag);
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
	 * Description：<br>
	 * 跳转到栏目管理新增页面
	 * 
	 * @author fengshiliang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/toAddPage")
	@ResponseBody
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView(
				"/content/advertisementManage/advContentAddOrEdit");
		try {
			String advId = request.getParameter("advId");
			String pid = request.getParameter("pid");
			ContAdvContent content = null;
			if (pid != null && pid != "") {
				// 修改需要查询
				content = (ContAdvContent) getService().selectByPrimaryKey(pid);
				request.setAttribute("content", content);
			}
			request.setAttribute("pid", pid);
			request.setAttribute("advId", advId);// 父栏目id
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存或者修改广告内容
	 * 
	 * @author fengshiliang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveOrUpdateContent")
	@ResponseBody
	public ModelAndView saveOrUpdateContent(ContAdvContent content,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		IContAdvContentService service = (ContAdvContentServiceImpl) getService();
		try {
			HashMap<String, Object> remap = saveUploadFileObject(request,
					Constant.CONTENT);

			if (remap.get(PromptMsgConstants.COMMON_FLG) != null) {
				if ((Boolean) remap.get(PromptMsgConstants.COMMON_FLG)) {
					PubFile pubFile = ((List<PubFile>) remap.get("resultList"))
							.get(0);
					if (content == null) {
						content = new ContAdvContent();
					}
					if (content.getStatus() == null
							|| content.getStatus() == "") {
						content.setStatus("1");
					}
					content.setFileId(pubFile.getPid());
					content.setPid(request.getParameter("pid"));
					// 修改
					if (!StringUtil.isBlank(content.getPid())) {
						content.setLastUpdateTime(DateUtil.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						content.setLastUpdateUser(getContextUser()
								.getUserName());
						service.updateByPrimaryKey(pubFile, content);
					} else {
						// 新增
						content.setPid(content.getPK());
						content.setCreateTime(DateUtil.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						content.setLastUpdateTime(DateUtil.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						content.setAdvOrder(DateUtil.format(new Date(),
								"yyyy-MM-dd HH:mm:ss"));
						content.setLastUpdateUser(getContextUser()
								.getUserName());
						content.setCreateUser(getContextUser().getUserName());
						service.saveFileInfo(pubFile, content);
					}
				}

			} else {
				if (content == null) {
					content = new ContAdvContent();
				}
				if (content.getStatus() == null || content.getStatus() == "") {
					content.setStatus("1");
				}
				content.setPid(request.getParameter("pid"));
				// 修改
				if (!StringUtil.isBlank(content.getPid())) {
					content.setLastUpdateTime(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setLastUpdateUser(getContextUser().getUserName());
					getService().updateByPrimaryKey(content);
				} else {
					// 新增
					content.setPid(content.getPK());
					content.setCreateTime(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setLastUpdateTime(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setAdvOrder(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setLastUpdateUser(getContextUser().getUserName());
					content.setCreateUser(getContextUser().getUserName());
					getService().insert(content);
				}
			}
			modelView.addObject("pid", content.getPid());
			modelView.addObject("content", content);
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
	 * 批量更新状态
	 * 
	 * @author fengshiliang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/batchUpdateByPids")
	@ResponseBody
	public ModelAndView batchUpdateByPids(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ContAdvContent params = (ContAdvContent) getEntity(request);
		IContAdvContentService service = (ContAdvContentServiceImpl) getService();
		if (null == params) {
			params = new ContAdvContent();
		}

		params.setPid(request.getParameter("pids"));
		params.setLastUpdateTime(DateUtil.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		params.setLastUpdateUser(getContextUser().getUserName());
		params.setAdvOrder(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		service.batchUpdateByPids(params);
		MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG,
				request);
		return modelView;
	}

	/**
	 * Description：<br>
	 * 上移 下移 动作保存到数据库
	 * 
	 * @author fengshiliang
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateOrder")
	@ResponseBody
	public ModelAndView updateOrder(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		String newObjectId = request.getParameter("newObjectId");
		String newObjectOrder = request.getParameter("newObjectOrder");
		String oldObjectOrder = request.getParameter("oldObjectOrder");
		String oldObjectId = request.getParameter("oldObjectId");
		ModelAndView modelView = new ModelAndView();
		try {
			// 第一个对象
			ContAdvContent content = new ContAdvContent();
			content.setAdvOrder(oldObjectOrder);
			content.setPid(newObjectId);
			getService().updateByPrimaryKey(content);
			// 第二个对象
			content = new ContAdvContent();
			content.setAdvOrder(newObjectOrder);
			content.setPid(oldObjectId);
			getService().updateByPrimaryKey(content);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return null;
	}

	/**
	 * 
	 * Description：<br>
	 * 预览
	 * 
	 * @author fengshiliang
	 * @date 2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/preview")
	@ResponseBody
	public ModelAndView preview(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/content/preview");
		String fileName = request.getParameter("fileUrl");
		if (".html".equals(fileName.substring(fileName.lastIndexOf(".")))
				|| ".htm".equals(fileName.substring(fileName.lastIndexOf(".")))) {
			modelAndView.addObject("type", "html");
		} else {
			modelAndView.addObject("type", "jpg");
		}
		modelAndView.addObject("fileId", request.getParameter("fileId"));
		modelAndView.addObject("fileUrl", request.getParameter("fileUrl"));
		modelAndView
				.addObject(
						"url",
						"null".equals(request.getParameter("url")) ? "javascript:void(0)"
								: request.getParameter("url"));
		return modelAndView;
	}
}
