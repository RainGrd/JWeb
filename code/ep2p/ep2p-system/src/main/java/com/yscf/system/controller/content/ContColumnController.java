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
 * 2015年9月16日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

import java.util.Date;

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
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.content.ContColumn;
import com.yscf.core.service.content.IContColumnService;
import com.yscf.core.service.content.impl.ContColumnServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * Description：<br>
 * 栏目管理控制器
 * 
 * @author shiliang.feng
 * @date 2015年9月16日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/contColumnController")
public class ContColumnController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(ContColumnController.class);

	@Autowired
	public ContColumnController(ContColumnServiceImpl service) {
		super(service);
		// Auto-generated constructor stub
	}

	@Override
	public Class<?> getModel() {
		return ContColumn.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到栏目管理展示页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("/content/contentManage/contColumnList");
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
		ContColumn params = (ContColumn) getEntity(request);
		IContColumnService service = (ContColumnServiceImpl) getService();
		if (null == params) {
			params = new ContColumn();
		}
		params.setPid(request.getParameter("pids"));
		params.setLastUpdateTime(DateUtil.format(new Date(),
				"yyyy-MM-dd HH:mm:ss"));
		params.setLastUpdateUser(getContextUser().getUserName());

		ModelAndView modelView = new ModelAndView();
		service.batchUpdateByPids(params);
		MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG,
				request);
		return modelView;
	}

	/**
	 * 
	 * @Description : 查询栏目管理信息,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param sysCreateCodeRule
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 下午2:39:14
	 */
	@RequestMapping(value = "/getContentData")
	@ResponseBody
	public ModelAndView getContentData(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		IContColumnService service = (ContColumnServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			ContColumn column = (ContColumn) getEntity(request);
			if (column == null) {
				column = new ContColumn();
			}
			PageList<ContColumn> list = service.selectContentByParameter(
					column, info);
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
		ModelAndView view = new ModelAndView(
				"/content/contentManage/contColumnAddOrEdit");
		try {
			String pid = request.getParameter("pid");
			ContColumn content = null;
			if (pid != null && pid != "") {
				content = (ContColumn) getService().selectByPrimaryKey(pid);
				request.setAttribute("content", content);
			}
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
	 * 跳转到栏目管理新增页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdateContent")
	@ResponseBody
	public ModelAndView saveOrUpdateContent(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			ContColumn column = (ContColumn) getEntity(request);
			if (column == null) {
				column = new ContColumn();
			}
			if (column.getStatus() == null || column.getStatus() == "") {
				column.setStatus("1");
			}
			column.setPid(request.getParameter("pid"));
			if (column.getPid() != null && column.getPid() != "") {
				column.setLastUpdateTime(DateUtil.format(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
				column.setLastUpdateUser(getContextUser().getUserName());
				getService().updateByPrimaryKey(column);
			} else {
				column.setCreateTime(DateUtil.format(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
				column.setLastUpdateTime(DateUtil.format(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
				column.setLastUpdateUser(getContextUser().getUserName());
				column.setCreateUser(getContextUser().getUserName());
				getService().insert(column);
			}
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
