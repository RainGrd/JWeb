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
import java.io.UnsupportedEncodingException;
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
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.ptp.financial.BizRechargeOnlineModel;
import com.yscf.core.service.financial.IBizRechargeOnlineService;
import com.yscf.core.service.financial.impl.BizRechargeOnlineServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;


/**
 * Description：线上充值控制器
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizRechargeOnlineController")
public class BizRechargeOnlineController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizRechargeOnlineController.class);

	@Autowired
	public BizRechargeOnlineController(BizRechargeOnlineServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizRechargeOnline.class;
	}
	
	/**
	 * Description：打开客户线上充值列表页面
	 * @author  JingYu.Dai
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping("/openOnlineRechargeList")
	public ModelAndView openOnlineRechargeList(){
		return new ModelAndView("/financial/recharge/onlineClient/online_recharge_list");
	}
	
	/**
	 * Description：打开客户线上充值补单页面
	 * @author  JingYu.Dai
	 * @date    2015年10月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/openReplacementrderPage")
	public ModelAndView openReplacementrderPage(HttpServletRequest request , HttpServletResponse response){
		try {
			String onRhgId = java.net.URLDecoder.decode(
					request.getParameter("onRhgId"), "UTF-8");
			String customerName = java.net.URLDecoder.decode(
					request.getParameter("customerName"), "UTF-8");
			String sname = java.net.URLDecoder.decode(
					request.getParameter("sname"), "UTF-8");
			String amount = java.net.URLDecoder.decode(
					request.getParameter("amount"), "UTF-8");
			String recTime = java.net.URLDecoder.decode(
					request.getParameter("recTime"), "UTF-8");
			String payName = java.net.URLDecoder.decode(
					request.getParameter("payName"), "UTF-8");
			String recOrderNo = java.net.URLDecoder.decode(
					request.getParameter("recOrderNo"), "UTF-8");
			String customerId = java.net.URLDecoder.decode(
					request.getParameter("customerId"), "UTF-8");
			
			request.setAttribute("onRhgId", onRhgId);
			request.setAttribute("customerName", customerName);
			request.setAttribute("sname", sname);
			request.setAttribute("amount", amount);
			request.setAttribute("recTime", recTime);
			request.setAttribute("payName", payName);
			request.setAttribute("recOrderNo", recOrderNo);
			request.setAttribute("customerId", customerId);
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("/financial/recharge/onlineClient/replacement_order");
	}
	

	/**
	 * Description：查询线上充值列表  分页
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/selectRechargeOnlinePageList")
	@ResponseBody
	public ModelAndView selectRechargeOnlinePageList(BizRechargeOnline bizRechargeOnline,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		PageInfo info = getPageInfo(request);
		BizRechargeOnlineServiceImpl serviceImpl = (BizRechargeOnlineServiceImpl) getService();
		try {
			PageList<BizRechargeOnline> list = serviceImpl.selectOnlineRechargePage(bizRechargeOnline, info);
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
	 * Description：补单
	 * @author  JingYu.Dai
	 * @date    2015年10月8日
	 * @version v1.0.0
	 * @param bizRechargeOnline
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("replacementOrder")
	public ModelAndView replacementOrder(BizRechargeOnline bizRechargeOnline,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		BizRechargeOnlineServiceImpl serviceImpl = (BizRechargeOnlineServiceImpl) getService();
		ContextUser user= getContextUser();
		bizRechargeOnline.setLastUpdateUser(user.getUserName());
		int count = serviceImpl.replacementOrder(bizRechargeOnline);
		String message = "";
		if (count > 0) {
			message = "补单成功！";
		} else {
			message = "补单失败！";
		}
		MessageBuilder.processSuccess(view, message, HttpMessage.SUCCESS_CODE,
			request);
		return view;
	}
	

	/**
	 * 
	 * Description：<br> 
	 * 导出线上充值客户列表
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
			BizRechargeOnline bizRechargeOnline,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == bizRechargeOnline){
				bizRechargeOnline = new BizRechargeOnline();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				IBizRechargeOnlineService service = (BizRechargeOnlineServiceImpl) getService();
				List<BizRechargeOnlineModel> reList = service.selectBizRechargeOnlineEom(bizRechargeOnline, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_recharge_online.xml";
				CheckResult checkrsl = exportExcel("线上充值客户列表.xls", xmlpath, "export_recharge_online", reList);
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
