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
 * 2015年9月18日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.content.IColumnContentService;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.util.FileConfig;

/**
 * Description：<br>
 * 栏目内容管理控制器
 * 
 * @author shiliang.feng
 * @date 2015年9月18日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/columnContentController")
public class ColumnContentController extends EscfBaseController {

	private Logger logger = LoggerFactory
			.getLogger(ColumnContentController.class);

	@Autowired
	public ColumnContentController(ColumnContentServiceImpl service) {
		super(service);
	}

	@Resource(name = "fileConfig")
	private FileConfig fileConfig;
	@Resource
	private ContTagController tagController;

	@Override
	public Class<?> getModel() {
		return ColumnContent.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据栏目id 查询栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toColContentList")
	@ResponseBody
	public ModelAndView toColContentList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = null;
		String tag = request.getParameter("tag");

		if (!StringUtil.isBlank(tag)) {
			if ("0".equals(tag)) {
				modelView = new ModelAndView(
						"/content/contentManage/columnContentList");
			} else {
				modelView = new ModelAndView(
						"/content/contentManage/columnContentLowList");
			}
		}
		new ModelAndView("/content/contentManage/columnContentList");
		request.setAttribute("colId", request.getParameter("colId"));
		request.setAttribute("tag", tag);
		try {
			if (!StringUtil.isBlank(request.getParameter("coluName"))) {
				request.setAttribute("coluName", new String(request
						.getParameter("coluName").getBytes("iso-8859-1"),
						"utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据栏目id 查询栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getColContentByColId")
	@ResponseBody
	public ModelAndView getColContentByColId(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = null;
		String tag = request.getParameter("tag");// 页面表示 0 为二级栏目内容 1为三级栏目内容
		if (!StringUtil.isBlank(tag)) {
			if ("0".equals(tag)) {
				modelView = new ModelAndView("/content/columnContentList");
			} else {
				modelView = new ModelAndView("/content/columnContentLowList");
			}
		}
		IColumnContentService service = (ColumnContentServiceImpl) getService();

		try {
			PageInfo info = getPageInfo(request);
			ColumnContent column = (ColumnContent) getEntity(request);
			if (column == null) {
				column = new ColumnContent();
			}
			column.setParentId(request.getParameter("parentId"));// 父级 栏目id
			PageList<ColumnContent> list = service
					.selectColuContentByParameter(column, info);
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
	 * @author shiliang.feng
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/toAddPage")
	@ResponseBody
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		String tag = request.getParameter("tag");
		if (!StringUtil.isBlank(tag)) {
			if ("0".equals(tag)) {
				view = new ModelAndView(
						"/content/contentManage/columnContentAddOrEdit");
			} else {
				view = new ModelAndView(
						"/content/contentManage/columnContentLowAddOrEdit");
			}
		}
		try {
			String parentId = request.getParameter("parentId");
			String pid = request.getParameter("pid");
			ColumnContent content = null;
			if (pid != null && pid != "") {
				// 修改需要查询
				content = (ColumnContent) getService().selectByPrimaryKey(pid);
				request.setAttribute("content", content);
			}
			request.setAttribute("tag", tag);// 页面标示
			request.setAttribute("parentId", parentId);// 父栏目id
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
	 * 保存或者修改栏目内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveOrUpdateContent")
	@ResponseBody
	public ModelAndView saveOrUpdateContent(ColumnContent content,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		IColumnContentService service = (ColumnContentServiceImpl) getService();
		PubFile pubFileImg = null;
		PubFile pubFile = null;
		// 栏目内容
		// ColumnContent content = (ColumnContent) getEntity(request);
		if (!StringUtil.isBlank(content.getEndTime())) {
			content.setEndTime(content.getEndTime() + " 23:59:59");
		}
		if (!StringUtil.isBlank(content.getBeginTime())) {
			content.setBeginTime(content.getBeginTime() + " 00:00:00");
		}
		try {
			// 文件上传
			HashMap<String, Object> remap = null;
			// 文件上传标示
			if ("1".equals(content.getIsChangeFile())) {
				remap = saveUploadFileObject(request, Constant.CONTENT);
			}
			if (remap != null) {
				if (remap.get(PromptMsgConstants.COMMON_FLG) != null) {
					if ((Boolean) remap.get(PromptMsgConstants.COMMON_FLG)) {
						// 上传了一个文件
						if (((List<PubFile>) remap.get("resultList")).size() == 1) {
							if (!"1".equals(content.getIsLowerLevel())) {
								pubFile = ((List<PubFile>) remap
										.get("resultList")).get(0);
							} else {
								pubFileImg = ((List<PubFile>) remap
										.get("resultList")).get(0);
							}
						}
						// 上传了两个文件
						if (((List<PubFile>) remap.get("resultList")).size() == 2) {
							pubFileImg = ((List<PubFile>) remap
									.get("resultList")).get(0);
							pubFile = ((List<PubFile>) remap.get("resultList"))
									.get(1);
						}

						if (content.getStatus() == null
								|| content.getStatus() == "") {
							content.setStatus("1");
						}
						// 默认为0 没有下级
						if (content.getIsLowerLevel() == null
								|| content.getIsLowerLevel() == "") {
							content.setIsLowerLevel("0");
						}
						content.setPid(request.getParameter("pid"));
						// 修改
						if (!StringUtil.isBlank(content.getPid())) {
							content.setLastUpdateTime(DateUtil.format(
									new Date(), "yyyy-MM-dd HH:mm:ss"));
							content.setLastUpdateUser(getContextUser()
									.getUserName());
							service.updateByPrimaryKey(pubFileImg, pubFile,
									content);
						} else {
							// 新增
							content.setCreateTime(DateUtil.format(new Date(),
									"yyyy-MM-dd HH:mm:ss"));
							content.setLastUpdateTime(DateUtil.format(
									new Date(), "yyyy-MM-dd HH:mm:ss"));
							content.setColuOrder(DateUtil.format(new Date(),
									"yyyy-MM-dd HH:mm:ss"));
							content.setLastUpdateUser(getContextUser()
									.getUserName());
							content.setCreateUser(getContextUser()
									.getUserName());
							service.saveFileInfo(pubFileImg, pubFile, content);
						}

					}
				}
			} else {
				// 没有上传文件就是说 需要在编辑器里面填写内容（非底级栏目外）
				// 添加内容则需要根据编辑器的内容生成html文件
				if (StringUtil.isNotEmpty(content.getContent())) {
					pubFile = new PubFile();
					upLoadHtml(request, pubFile, content);
				} else {
					content.setIsCustomFile("1");
				}
				if (content.getStatus() == null || content.getStatus() == "") {
					content.setStatus("1");
				}
				// 默认为0 没有下级
				if (content.getIsLowerLevel() == null
						|| content.getIsLowerLevel() == "") {
					content.setIsLowerLevel("0");
				} else if ("1".equals(content.getIsLowerLevel())) {
					content.setTagIds("");
				}
				content.setPid(request.getParameter("pid"));
				// 修改
				if (!StringUtil.isBlank(content.getPid())) {
					content.setLastUpdateTime(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setLastUpdateUser(getContextUser().getUserName());
					service.updateByPrimaryKey(null, pubFile, content);
				} else {
					// 新增
					content.setCreateTime(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setLastUpdateTime(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setColuOrder(DateUtil.format(new Date(),
							"yyyy-MM-dd HH:mm:ss"));
					content.setLastUpdateUser(getContextUser().getUserName());
					content.setCreateUser(getContextUser().getUserName());
					service.saveFileInfo(null, pubFile, content);
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
	 * Description：<br>
	 * 根据传入的内容 生成html文件 并在栏目内容与文件关系表中建立关系
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月3日
	 * @version v1.0.0
	 * @param content
	 * @return
	 */
	private void upLoadHtml(HttpServletRequest request, PubFile pubFile,
			ColumnContent content) {
		// -------------------开始上传文件begin---------------------
		// 计算文件上传名称和目录
		// 获取服务器地址
		// String webBasePath = request.getServletContext().getRealPath("");
		// 系统配置文件
		Date curDate = new Date();
		String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(curDate);
		// 获取配置的文件保存根路径
		String rootPath = fileConfig.getFileRoot();

		// 设置文件名
		String fileName = UUID.randomUUID() + ".html";
		String lujing = "/upload/" + Constant.CONTENT + "/" + dateStr + "/"
				+ fileName;
		File file = new File(rootPath + lujing);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content.getContent());
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 开始赋值
		pubFile.setPid(pubFile.getPK());
		pubFile.setFileUrl(lujing);
		pubFile.setFileName(fileName);
		pubFile.setFileType("html");
		pubFile.setStatus(Constant.ACTIVATE);
		pubFile.setCreateTime(DateUtil.format(DateUtil.getToday()));
		pubFile.setCreateUser(getUserId());
		// -------------------开始上传文件end---------------------
	}

	/**
	 * 
	 * Description：<br>
	 * 批量更新状态
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/batchUpdateByPids")
	@ResponseBody
	public ModelAndView batchUpdateByPids(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ColumnContent params = (ColumnContent) getEntity(request);
		IColumnContentService service = (ColumnContentServiceImpl) getService();
		if (null == params) {
			params = new ColumnContent();
		}

		params.setPid(request.getParameter("pids"));
		params.setLastUpdateTime(DateUtil.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		params.setLastUpdateUser(getContextUser().getUserName());
		params.setColuOrder(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		service.batchUpdateByPids(params);
		MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG,
				request);
		return modelView;
	}

	/**
	 * Description：<br>
	 * 上移 下移 动作保存到数据库
	 * 
	 * @author shiliang.feng
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
			ColumnContent content = new ColumnContent();
			content.setColuOrder(oldObjectOrder);
			content.setPid(newObjectId);
			getService().updateByPrimaryKey(content);
			// 第二个对象
			content = new ColumnContent();
			content.setColuOrder(newObjectOrder);
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
		return modelView;
	}
}
