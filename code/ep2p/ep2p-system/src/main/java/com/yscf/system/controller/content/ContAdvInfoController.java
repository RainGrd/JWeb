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
import com.yscf.core.model.content.adv.ContAdvInfo;
import com.yscf.core.service.content.IContAdvInfoService;
import com.yscf.core.service.content.impl.ContAdvInfoServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 广告信息 管理控制器
 * 
 * @author fengshiliang
 * @date 2015年9月24日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/contAdvInfoController")
public class ContAdvInfoController extends EscfBaseController {

	private Logger logger = LoggerFactory
			.getLogger(ContAdvInfoController.class);

	@Autowired
	public ContAdvInfoController(ContAdvInfoServiceImpl service) {
		super(service);
		// Auto-generated constructor stub
	}

	@Override
	public Class<?> getModel() {
		return ContAdvInfo.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到广告 信息管理页面
	 * 
	 * @author fengshiliang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("/content/advertisementManage/advColumnList");
	}

	/**
	 * 加载 广告信息 数据 Description：<br>
	 * 
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getContentData")
	@ResponseBody
	public ModelAndView getContentData(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();

		IContAdvInfoService service = (ContAdvInfoServiceImpl) getService();

		try {
			PageInfo info = getPageInfo(request);
			ContAdvInfo column = (ContAdvInfo) getEntity(request);
			if (column == null) {
				column = new ContAdvInfo();
			}
			PageList<ContAdvInfo> list = service.selectContentByParameter(
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
	 * 跳转到新增页面
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAddPage")
	@ResponseBody
	public ModelAndView toAddPage(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/content/advertisementManage/advColumnAddOrEdit");
		try {
			String pid = request.getParameter("pid");
			ContAdvInfo content = null;
			if (pid != null && pid != "") {
				content = (ContAdvInfo) getService().selectByPrimaryKey(pid);
				request.setAttribute("content", content);
			}
		} catch (FrameworkException e) {
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
	 * 新增or修改 广告信息
	 * 
	 * @author fengshiliang
	 * @date 2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveOrUpdateAdv")
	@ResponseBody
	public ModelAndView saveOrUpdateAdv(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("");
		ContAdvInfo column = (ContAdvInfo) getEntity(request);
		try {
			if (column == null) {
				column = new ContAdvInfo();
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

	/**
	 * 
	 * Description：<br>
	 * 批量更新状态
	 * 
	 * @author fengshiliang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/batchUpdateByPids")
	@ResponseBody
	public ModelAndView batchUpdateByPids(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ContAdvInfo params = (ContAdvInfo) getEntity(request);
		IContAdvInfoService service = (ContAdvInfoServiceImpl) getService();
		if (null == params) {
			params = new ContAdvInfo();
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

}
