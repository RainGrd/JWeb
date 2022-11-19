/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 客户账户管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.financial;

import java.io.File;
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
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.CustomerAccountModel;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br>
 * 客户账户管理
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/customerAccountController")
public class CustomerAccountController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(CustomerAccountController.class);

	@Autowired
	public CustomerAccountController(CustomerAccountServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CustomerAccount.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到客户账户新增页面
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
				logger.info("根据PID获取系统客户账户失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到客户账户查询页面
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
		return new ModelAndView("/financial/customerAccountList");
	}

	/**
	 * 
	 * Description：<br> 
	 * 客户账户管理查询列表
	 * @author  JunJie.Liu
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/customerAccountListData")
	@ResponseBody
	public ModelAndView customerAccountListIntensify(HttpServletRequest request,
			HttpServletResponse response, CustomerAccountModel customerAccountVO)
			throws HttpRequestException {
		CustomerAccountServiceImpl service = (CustomerAccountServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		
		try {
			PageInfo info = getPageInfo(request);
			PageList<CustomerAccount> list = service.selectAllPage(customerAccountVO, info);
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
	}
	/**
	 * Description：删除客户账户管理列表
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
		ModelAndView modelView = new ModelAndView("/financial/bizWithdrawAdd");
		String reqpid = request.getParameter("pid");
		modelView.addObject("pid", reqpid);
		return modelView;
	}

	
	@RequestMapping(value = "/batchAudit")
	@ResponseBody
	public ModelAndView batchAudit(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizWithdraw bizWithdraw = new BizWithdraw();
			BizWithdrawServiceImpl service = (BizWithdrawServiceImpl) getService();
			bizWithdraw.setPid(request.getParameter("pid"));
			bizWithdraw.setAuditStatus(request.getParameter("auditStatus"));
			bizWithdraw.setWitDesc(new String(request.getParameter("witDesc").getBytes("iso-8859-1"),"utf-8"));
			// 根据主键批量审核
			service.updateBatchAudit(bizWithdraw);
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
	
	/**
	 * 
	 * Description：<br> 
	 * 导出客户账户列表
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
			CustomerAccountModel customerAccountVO,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == customerAccountVO){
				customerAccountVO = new CustomerAccountModel();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				ICustomerAccountService service = (CustomerAccountServiceImpl) getService();
				List<CustomerAccountModel> reList = service.selectCustomerAccountVOEom(customerAccountVO, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_customer_account.xml";
				CheckResult checkrsl = exportExcel("客户账户列表.xls", xmlpath, "export_customer_account", reList);
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
