/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.financial;

import java.io.File;
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

import com.achievo.framework.constant.ComExcelConstants;
import com.achievo.framework.excel.domain.CheckResult;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizRechargeOffline;
import com.yscf.core.model.ptp.financial.BizRechargeOfflineModel;
import com.yscf.core.service.financial.impl.BizRechargeOfflineServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br>
 * 线下充值控制器
 * @author JingYu.Dai
 * @date 2015年9月25日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizRechargeOfflineController")
public class BizRechargeOfflineController extends EscfBaseController {

	private Logger logger = LoggerFactory
			.getLogger(BizRechargeOfflineController.class);

	@Autowired
	public BizRechargeOfflineController(BizRechargeOfflineServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizRechargeOffline.class;
	}

	/**
	 * Description：打开充值首页
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月28日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping("/openOfflineIndex")
	public ModelAndView openOfflineIndex() {
		return new ModelAndView("/financial/recharge/offlineClient/index");
	}

	/**
	 * Description：打开充值页面
	 * 
	 * @author JingYu.Dai
	 * @date 2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/openClientRecharge")
	public ModelAndView openClientRecharge(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("clientPid", request.getParameter("clientPid"));
		return new ModelAndView(
				"/financial/recharge/offlineClient/client_recharge");
	}

	/**
	 * Description：打开客户线下充值列表页面
	 * @author  JingYu.Dai
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping("/openOfflineRechargeList")
	public ModelAndView openOfflineRechargeList(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return new ModelAndView("/financial/recharge/offlineClient/offline_recharge_list");
	}
	
	/**
	 * Description：打开线下充值审核页面
	 * @author  JingYu.Dai
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/openCheckRechargePage")
	public ModelAndView openCheckRechargePage(HttpServletRequest request,
			HttpServletResponse response){
		request.setAttribute("clientPid", request.getParameter("clientPid"));
		request.setAttribute("offRhgId", request.getParameter("offRhgId"));
		request.setAttribute("recTime", request.getParameter("recTime"));
		request.setAttribute("amount", request.getParameter("amount"));
		return new ModelAndView("/financial/recharge/offlineClient/client_recharge_check");
	}
	
	/**
	 * Description：保存充值数据
	 * @author JingYu.Dai
	 * @date 2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping("/addRccountRecharge")
	@ResponseBody
	public ModelAndView addRccountRecharge(
			BizRechargeOffline bizRechargeOffline, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		if (null == bizRechargeOffline.getAmount()) {
			MessageBuilder.processSuccess(view, "充值金额不能为空！", HttpMessage.ERROR_CODE, request);
			return view;
		}
		if (null == bizRechargeOffline.getCustomerId()) {
			MessageBuilder.processSuccess(view, "关联的客户出现问题请联系系统管理员！", HttpMessage.ERROR_CODE,
					request);
			return view;
		} else {
			BizRechargeOfflineServiceImpl serviceImpl = (BizRechargeOfflineServiceImpl) getService();
			String pid = bizRechargeOffline.getPid();
			if (null == pid) {
				ContextUser user = getContextUser();
				bizRechargeOffline.setUserId(user.getUserId());
				bizRechargeOffline.setCreateUser(user.getUserName());
				bizRechargeOffline.setStatus(Constant.ACTIVATE);
				String currentTime = DateUtil.format(new Date());
				bizRechargeOffline.setCreateTime(currentTime);
				bizRechargeOffline.setRecTime(currentTime);
				bizRechargeOffline.setRecStatus(Constant.REC_STATUS_REFERENDUM);
			}
			try {
				int count = serviceImpl.insert(bizRechargeOffline);
				if (count > 0) {
					String message = "提交审核成功！";
					MessageBuilder.processSuccess(view, message, HttpMessage.SUCCESS_CODE,
							request);
				} else {
					String message = "提交充值失败！";
					MessageBuilder.processSuccess(view, message, HttpMessage.ERROR_CODE,
							request);
				}
			} catch (FrameworkException e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
				MessageBuilder.processError(view, e, request);
			}
		}
		return view;
	}

	/**
	 * Description：查询线下充值列表  分页
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/selectRechargeOfflinePageList")
	@ResponseBody
	public ModelAndView selectRechargeOfflinePageList(BizRechargeOffline bizRechargeOffline,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		PageInfo info = getPageInfo(request);
		BizRechargeOfflineServiceImpl serviceImpl = (BizRechargeOfflineServiceImpl) getService();
		try {
			PageList<BizRechargeOffline> list = serviceImpl.selectOfflineRechargePage(bizRechargeOffline, info);
			view.addObject("rows",list);
			view.addObject("total",list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_CODE,
					request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	
	
	/**
	 * Description：保存充值审核数据
	 * @author JingYu.Dai
	 * @date 2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping("/addOfflineRechargeCheck")
	@ResponseBody
	public ModelAndView addOfflineRechargeCheck(
			BizRechargeOffline bizRechargeOffline, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		BizRechargeOfflineServiceImpl serviceImpl = (BizRechargeOfflineServiceImpl) getService();
		try {
				ContextUser user = getContextUser();
				String currentTime = DateUtil.format(new Date());
				bizRechargeOffline.setLastUpdateUser(user.getUserName());
				bizRechargeOffline.setLastUpdateTime(currentTime);
			int count = serviceImpl.updateOfflineRechargeCheck(bizRechargeOffline);
			if (count > 0) {
				String message = "审核成功！";
				MessageBuilder.processSuccess(view, message, HttpMessage.SUCCESS_CODE,
						request);
			} else {
				String message = "审核失败！";
				MessageBuilder.processSuccess(view, message, HttpMessage.ERROR_CODE,
						request);
			}
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 导出线下充值客户列表
	 * @author  JunJie.Liu
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bizReceiptTransferVO
	 * @param expvo
	 * @return
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response,
			BizRechargeOffline bizRechargeOffline,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == bizRechargeOffline){
				bizRechargeOffline = new BizRechargeOffline();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				BizRechargeOfflineServiceImpl serviceImpl = (BizRechargeOfflineServiceImpl) getService();
				List<BizRechargeOfflineModel> reList = serviceImpl.selectBizRechargeOfflineEom(bizRechargeOffline, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_recharge_offline.xml";
				CheckResult checkrsl = exportExcel("线下充值客户列表.xls", xmlpath, "export_recharge_offline", reList);
				//获取生成的文件信息
				if(checkrsl.isPass()){
					File file =  (File) checkrsl.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
					FileDownload.downloadAbsoluteFile(response, request, file, true);
					logger.info("POI 2007 导出的结果文件为名：" + file.getName());
					processSuccess(remap, file.getName());
				}else{
					List<String> errorMsg = checkrsl.getErrorMessage();
					for(String str : errorMsg){
						processError(remap , str);
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	

	
}
