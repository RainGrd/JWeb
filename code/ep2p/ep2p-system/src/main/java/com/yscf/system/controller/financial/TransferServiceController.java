/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 确认转账管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.financial;

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
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 确认转账管理
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/transferServiceController")
public class TransferServiceController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(TransferServiceController.class);

	@Autowired
	public TransferServiceController(BizWithdrawServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizWithdraw.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到确认转账新增页面
	 * @author Allen
	 * @date 2015年9月10日
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
		ModelAndView modelView = new ModelAndView("/financial/bizWithdrawAdd");
		//BizWithdraw bizWithdraw = (BizWithdraw) getEntity(request);
		modelView.addObject("pid", request.getParameter("pid"));
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据ID查询
	 * @author Allen
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizWithdraw bizWithdraw = (BizWithdraw) getEntity(request);
			bizWithdraw = (BizWithdraw) getService().selectByPrimaryKey(
					bizWithdraw.getPid());
			modelView.addObject("result", bizWithdraw);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取系统确认转账失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到确认转账查询页面
	 * @author Allen
	 * @date 2015年9月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("/financial/transferServiceList");
	}

	/**
	 * Description：确认转账管理查询列表
	 * @author Allen
	 * @date 2015年9月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	/*@RequestMapping(value = "/transferServiceListData")
	@ResponseBody
	public ModelAndView logList(HttpServletRequest request,
			HttpServletResponse response, int page, int rows)
			throws HttpRequestException {
		BizWithdrawServiceImpl service = (BizWithdrawServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		HashMap<String,Object> parasMap = new HashMap<String,Object>();
		if(!"".equals(request.getParameter("customerName")) && null !=request.getParameter("customerName")){
			parasMap.put("customerName", request.getParameter("customerName"));
		}
		if(!"".equals(request.getParameter("sname")) && null != request.getParameter("sname")){
			parasMap.put("sname", request.getParameter("sname"));
		}
		if(!"".equals(request.getParameter("phoneNo")) && null != request.getParameter("phoneNo")){
			parasMap.put("phoneNo", request.getParameter("phoneNo"));
		}
		if(!"".equals(request.getParameter("transferedStatus")) && null != request.getParameter("transferedStatus")){
			parasMap.put("transferedStatus", request.getParameter("transferedStatus"));
		}
		if(!"".equals(request.getParameter("applyTime")) && null != request.getParameter("applyTime")){
			parasMap.put("applyTime", request.getParameter("applyTime"));
		}
		try {
			PageInfo info = getPageInfo(request);
			PageList<BizWithdraw> list = service.selectTransferServiceAllPage(parasMap, info);
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
	}*/

	/**
	 * Description：删除确认转账管理列表
	 * 
	 * @author Allen
	 * @date 2015年9月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */

	@RequestMapping(value = "/deleteBtach")
	@ResponseBody
	public ModelAndView deleteBtach(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String pids = request.getParameter("pid");
			BizWithdrawServiceImpl service = (BizWithdrawServiceImpl) getService();
			// 根据主键批量删除
			service.deleteBtach(pids);
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
	
	
	@RequestMapping(value = "/toBatchAudit")
	@ResponseBody
	public ModelAndView toBatchAudit(String pid ,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/financial/transferServiceAdd");
		String reqpid = request.getParameter("pid");
		modelView.addObject("pid", reqpid);
		return modelView;
	}

	
	/*@RequestMapping(value = "/batchAudit")
	@ResponseBody
	public ModelAndView batchAudit(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizWithdraw bizWithdraw = new BizWithdraw();
			BizWithdrawServiceImpl service = (BizWithdrawServiceImpl) getService();
			bizWithdraw.setPid(request.getParameter("pid"));
			bizWithdraw.setAuditStatus(auditStatus);
			bizWithdraw.setTransferedStatus(request.getParameter("transferedStatus"));
			bizWithdraw.setDescPromptId(request.getParameter("descPromptId"));
			bizWithdraw.setWitSureDesc(new String(request.getParameter("witSureDesc").getBytes("iso-8859-1"),"utf-8"));
			// 根据主键批量审核
			service.batchAuditTransferService(bizWithdraw);
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
*/
	
}
