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
 * 2015年9月10日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

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

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：参数管理Controller<br>
 * 
 * 
 * @author fengshiliang
 * @date 2015年9月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysParamsController")
public class SysParamsController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(SysParamsController.class);

	@Autowired
	public SysParamsController(SysParamsServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysParams.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据 key 或者 value 查询系统参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/loadSysParams")
	@ResponseBody
	public ModelAndView loadSysParams(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			SysParams params = (SysParams) getEntity(request);
			if (null == params) {
				params = new SysParams();
			}
			params.setStatus("-1");
			ISysParamsService service = (SysParamsServiceImpl) getService();
			PageList<SysParams> list = service.searchParamsByKeyOrVal(params,
					info);
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
	 * 新增或者修改参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveOrUpdateSysParams")
	@ResponseBody
	public ModelAndView saveOrUpdateSysParams(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysParams params = (SysParams) getEntity(request);
			if (params == null) {
				params = new SysParams();
			}
			if (params.getStatus() == null || params.getStatus() == "") {
				params.setStatus("1");
			}
			params.setPid(request.getParameter("pid"));
			if (params.getPid() != null && params.getPid() != "") {
				params.setLastUpdateTime(new Date());
				params.setLastUpdateUser(getContextUser().getUserName());
				ISysParamsService service = (SysParamsServiceImpl) getService();
				service.updateParamByPid(params);
			} else {
				params.setCreateTime(new Date());
				params.setLastUpdateTime(new Date());
				params.setCreateUser(getContextUser().getUserName());
				params.setLastUpdateUser(getContextUser().getUserName());
				getService().insert(params);
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
	 * 根据pid 查询系统参数
	 * 
	 * @author fengshiliang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getParamsByPid")
	@ResponseBody
	public ModelAndView getParamsByPid(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysParams params = (SysParams) getEntity(request);
			ISysParamsService service = (SysParamsServiceImpl) getService();
			params = service.getParamsByPid(params.getPid());
			modelView.addObject("params", params);
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
	 * 根据多个pid 批量更新状态为-1
	 * 
	 * @author fengshiliang
	 * @date 2015年9月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/batchDeletePara")
	@ResponseBody
	public ModelAndView batchDeletePara(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysParams params = (SysParams) getEntity(request);
			ISysParamsService service = (SysParamsServiceImpl) getService();
			if (null == params) {
				params = new SysParams();
			}
			params.setPid(request.getParameter("pids"));
			params.setStatus("0");
			params.setLastUpdateTime(new Date());
			params.setLastUpdateUser(getContextUser().getUserName());
			boolean isdel = service.batchDeletePara(params);
			modelView.addObject("isdel", isdel);
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
	 * 跳转到系统参数管理页面
	 * 
	 * @author fengshiliang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/editParamByPid")
	@ResponseBody
	public ModelAndView editParamByPid(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/sysParameterAdd");
		String pid = request.getParameter("pid");
		ISysParamsService service = (SysParamsServiceImpl) getService();
		SysParams params = service.getParamsByPid(pid);
		request.setAttribute("params", params);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到系统参数管理页面
	 * 
	 * @author fengshiliang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toPage")
	@ResponseBody
	public ModelAndView toPage(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/sysParameter");
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 新增页面
	 * 
	 * @author fengshiliang
	 * @date 2015年9月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/sysParameterAdd");
		return modelView;
	}

}
