/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体现管理
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
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.model.ptp.financial.BizWithdrawModel;
import com.yscf.core.service.financial.IBizWithdrawService;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：提现管理
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizWithdrawController")
public class BizWithdrawController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(BizWithdrawController.class);

	@Autowired
	public BizWithdrawController(BizWithdrawServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizWithdraw.class;
	}

	/**
	 * Description： 跳转到提现新增页面
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
	 * Description：跳转到提现审核查询页面
	 * @author  JingYu.Dai
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/openWithdrawCheckList")
	@ResponseBody
	public ModelAndView openWithdrawCheckList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return new ModelAndView("/financial/biz_withdraw_check_list");
	}
	
	/**
	 * Description：跳转到转账确认查询页面
	 * @author  JingYu.Dai
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/openTransferAffirmList")
	@ResponseBody
	public ModelAndView openTransferAffirmList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return new ModelAndView("/financial/transfer_affirm_list");
	}
	
	/**
	 * Description：跳转到转账已确认查询页面
	 * @author  JingYu.Dai
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/openTransferAlreadyAffirmList")
	@ResponseBody
	public ModelAndView openTransferAlreadyAffirmList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return new ModelAndView("/financial/transfer_already_affirm_list");
	}
	
	/**
	 * Description：跳转到提现查询页面
	 * @author  JingYu.Dai
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/openWithdrawQueryList")
	@ResponseBody
	public ModelAndView openWithdrawQueryList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return new ModelAndView("/financial/withdraw_query_list");
	}
	

	/**
	 * Description： 根据ID查询
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
				logger.info("根据PID获取系统体现失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * Description：提现管理->提现审核列表查询
	 * @author Allen
	 * @date 2015年9月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/bizWithdrawList")
	@ResponseBody
	public ModelAndView bizWithdrawList(HttpServletRequest request,
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
		if(!"".equals(request.getParameter("auditStatus")) && null != request.getParameter("auditStatus")){
			parasMap.put("auditStatus", request.getParameter("auditStatus"));
		}
		if(!"".equals(request.getParameter("applyTime")) && null != request.getParameter("applyTime")){
			parasMap.put("applyTime", request.getParameter("applyTime"));
		}
		if(!"".equals(request.getParameter("queryStatus")) && null != request.getParameter("queryStatus")){
			 /**
			 * 这个字段用于查询逻辑处理
			 * 查询状态queryStatus（提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4）
			 */
			parasMap.put("queryStatus", request.getParameter("queryStatus"));
		}
		try {
			PageInfo info = getPageInfo(request);
			PageList<BizWithdraw> list = service.selectSelectivePage(parasMap, info);
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
	 * Description：删除提现管理列表
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
			HttpServletResponse response, BizWithdraw bizWithdraw, String ifStatus) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizWithdrawServiceImpl service = (BizWithdrawServiceImpl) getService();
			//此状态用来判断是（1：提现审核  还是 2： 转账确认）
			if("1".equals(ifStatus)){
				bizWithdraw.setUserId(getUserId());
			}else if("2".equals(ifStatus)){
				bizWithdraw.setTransferedUserId(getUserId());
			}
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
			BizWithdrawModel bizWithdrawVO,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == bizWithdrawVO){
				bizWithdrawVO = new BizWithdrawModel();
			}
			
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				IBizWithdrawService service = (BizWithdrawServiceImpl) getService();
				List<BizWithdrawModel> reList = service.selectBizWithdrawVOEom(bizWithdrawVO, eom);
				//通过信息进行配置文件
				//提现审核列表：1、转账确认列表：2、转账已确认列表：3、提现列表：4
				String title = null;
				if("1".equals(bizWithdrawVO.getQueryStatus())){
					title = "提现审核列表";
				}else if("2".equals(bizWithdrawVO.getQueryStatus())){
					title = "转账确认列表";
				}else if("3".equals(bizWithdrawVO.getQueryStatus())){
					title = "转账已确认列表";
				}else if("4".equals(bizWithdrawVO.getQueryStatus())){
					title = "提现列表";
					List<BizWithdrawModel> copyList = new ArrayList<BizWithdrawModel>();
					
					BigDecimal standAmount = new BigDecimal(50000);
					for(BizWithdrawModel bwm  : reList){
						BigDecimal amount = bwm.getAmount();
						
						if(bwm!=null && amount.compareTo(standAmount)>0 && !Constant.AGGREGATE_STRING.equals(bwm.getCustomerName())){
							
							List<BizWithdrawModel> newAmountList = getBizWithdrawList(bwm,standAmount);
							
							copyList.addAll(newAmountList);
							
						}else{
							copyList.add(bwm);
						}
					}
					
					reList = copyList;
				}
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_biz_withdraw.xml";
				CheckResult checkrsl = exportExcel(title+".xls", xmlpath, "export_biz_withdraw", reList);
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
	
	/**
	 * 
	 * Description：<br> 
	 * 拆分提现记录，以标准值获取新的list
	 * @author  JunJie.Liu
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param bwm
	 * @param standAmount
	 * 			// 标准值
	 * @return
	 * @throws InvocationTargetException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IllegalArgumentException 
	 * @throws SecurityException 
	 */
	private List<BizWithdrawModel> getBizWithdrawList(BizWithdrawModel bwm,BigDecimal standAmount) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		List<BizWithdrawModel> list = new ArrayList<BizWithdrawModel>();
		BigDecimal amount = bwm.getAmount();
		if(amount!=null ){
			while(amount.compareTo(standAmount)>0){
				BizWithdrawModel bw = ConvertObjectUtil.convertObject(bwm, BizWithdrawModel.class);
				amount = amount.subtract(standAmount);
				bw.setAmount(standAmount);
				list.add(bw);
			}
			if(amount.compareTo(BigDecimal.ZERO) > 0){
				BizWithdrawModel bw = ConvertObjectUtil.convertObject(bwm, BizWithdrawModel.class);
				bw.setAmount(amount);
				list.add(bw);
			}
		}
		return list;
	}
	
	
}
