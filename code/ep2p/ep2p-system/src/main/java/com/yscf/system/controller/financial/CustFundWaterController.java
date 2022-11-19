/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 客户资金流水管理
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
import java.math.BigDecimal;
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
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.CustFundWaterModel;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.financial.ICustFundWaterService;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.core.service.financial.impl.CustFundWaterServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br>
 * 客户资金流水管理
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/custFundWaterController")
public class CustFundWaterController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(CustFundWaterController.class);

	@Autowired
	public CustFundWaterController(CustFundWaterServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CustomerAccount.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到客户资金流水新增页面
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
		ModelAndView modelView = new ModelAndView("/financial/custFundWaterAdd");
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
				logger.info("根据PID获取系统客户资金流水失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到客户资金流水查询页面
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
		return new ModelAndView("/financial/custFundWaterList");
	}

	/**
	 * Description：客户资金流水管理查询列表
	 * 
	 * @author Allen
	 * @date 2015年9月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/custFundWaterListData")
	@ResponseBody
	public ModelAndView customerAccountList(HttpServletRequest request,
			HttpServletResponse response, int page, int rows)
			throws HttpRequestException {
		CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
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
		if(!"".equals(request.getParameter("happenTime")) && null != request.getParameter("happenTime")){
			parasMap.put("happenTime", request.getParameter("happenTime"));
		}
		if(!"".equals(request.getParameter("fundType")) && null != request.getParameter("fundType")){
			parasMap.put("fundType", request.getParameter("fundType"));
		}
		if(!"".equals(request.getParameter("businessType")) && null != request.getParameter("businessType")){
			parasMap.put("businessType", request.getParameter("businessType"));
		}
		try {
			PageInfo info = getPageInfo(request);
			PageList<CustFundWater> list = service.selectAllPage(parasMap, info);
			CustFundWater cfw = new CustFundWater();
			BigDecimal totalAccountBalance = new BigDecimal(0);
			List<CustFundWater> cfwListData = service.getTotalData(parasMap);
			for(int i=0;i<cfwListData.size();i++ ){
				CusTomer cu = new CusTomer();
				cu.setCustomerName("总计");
				cfw.setCustomer(cu);
				if(cfwListData.get(i).getBusinessType().equals("1")){
					cfw.setBusinessType("999");
					cfw.setTotalIncome(cfwListData.get(i).getFundValue());
				}
				if(cfwListData.get(i).getBusinessType().equals("2")){
					cfw.setBusinessType("999");
					cfw.setTotalPay(cfwListData.get(i).getFundValue());
				}
				totalAccountBalance = totalAccountBalance.add(cfwListData.get(i).getAccountBalance());
			}
			cfw.setAccountBalance(totalAccountBalance);
			if(list.size()>0){
				list.add(cfw);
			}
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
	 * Description：删除客户资金流水管理列表
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
	 * 跳转到查看客户账户余额资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAccountTotalDetailsList")
	@ResponseBody
	public ModelAndView toAccountTotalDetailsList(String pid,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toAccountTotalDetailsList");
		modelView.addObject("pid", pid);
		modelView.addObject("flag", flag);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户账户总额详细页面
	 * @author  heng.wang
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAccountTotalDetailsById")
	@ResponseBody
	public ModelAndView selectAccountTotalDetailsById(String pid,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
			CustFundWater custFundWater = new CustFundWater();
			custFundWater.setCustomerId(pid);
			custFundWater.setFlag(flag);
			PageList<CustFundWater> list  = service.selectAllPages(custFundWater, info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取账户总额资金流水详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客户账户总额查询列表页面
	 * @author  heng.wang
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAccountTotalDetailList")
	@ResponseBody
	public ModelAndView selectAccountTotalDetailList(CustomerAccount customerAccount,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
		CustFundWater custFundWater = new CustFundWater();
		
	   try {
			PageInfo info = getPageInfo(request);
			if(null == customerAccount){
				customerAccount = new CustomerAccount();
			}
			custFundWater.setHappenBeginTime(customerAccount.getHappenBeginTime());
			custFundWater.setHappenEndTime(customerAccount.getHappenEndTime());
			custFundWater.setFlag(customerAccount.getFlag());
			custFundWater.setCustomerId(customerAccount.getPid());
 			PageList<CustFundWater> list = service.selectAllPages(custFundWater,info);
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
	 * 
	 * Description：<br> 
	 * 跳转到查看客户投资总额资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toTouZilDetailsList")
	@ResponseBody
	public ModelAndView toTouZilDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toTouZilDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投资总额详细页面
	 * @author  heng.wang
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectTouziDetailsById")
	@ResponseBody
	public ModelAndView selectTouziDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
			PageList<CustFundWater> list  = service.selectTouziDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取账户总额资金流水详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户投标奖励资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toTouBiaoDetailsList")
	@ResponseBody
	public ModelAndView toTouBiaoDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toTouBiaoDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户资金流水类型页面
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFundWaterList")
	@ResponseBody
	public ModelAndView toFundWaterList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toFundWaterList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 查询资金流水类型
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectWaterType")
	@ResponseBody
	public ModelAndView selectWaterType(CustFundWater custFundWater,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
			PageList<CustFundWater> list  = service.selectWaterType(custFundWater,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询资金流水类型详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectZiJinWater")
	@ResponseBody
	public ModelAndView selectZiJinWater(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
			CustFundWater custFundWater = new CustFundWater();
			custFundWater.setCustomerId(pid);
			PageList<CustFundWater> list  = service.selectZiJinWaterAllPage(custFundWater,info);
//			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取账户总额资金流水详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据条件查询资金流水
	 * @author  heng.wang
	 * @date    2015年10月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectZiJinWaterAllPage")
	@ResponseBody
	public ModelAndView selectZiJinWaterAllPage(CustFundWater custFundWater,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			CustFundWaterServiceImpl service = (CustFundWaterServiceImpl) getService();
			String waterType=custFundWater.getWaterType();
			custFundWater.setWaterType(waterType);
			custFundWater.setCustomerId(custFundWater.getPid());
			PageList<CustFundWater> list  = service.selectZiJinWaterAllPage(custFundWater,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据条件查询资金流水详细失败",e.getMessage());
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
			CustFundWaterModel custFundWaterVO,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == custFundWaterVO){
				custFundWaterVO = new CustFundWaterModel();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				ICustFundWaterService service = (CustFundWaterServiceImpl) getService();
				List<CustFundWaterModel> reList = service.selectCustFundWaterVOEom(custFundWaterVO, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_cust_fund_water.xml";
				CheckResult checkrsl = exportExcel("客户账户明细列表.xls", xmlpath, "export_cust_fund_water", reList);
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
