/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借后管理-controller层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.google.common.collect.Lists;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowAfter;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BorrowAfterManageServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br> 
 * 借后管理-controller层
 * @author  Lin Xu
 * @date    2015年10月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/borrowAfterManageController")
public class BorrowAfterManageController extends EscfBaseController {
	
	//日志对象
	private Logger logger = Logger.getLogger(BorrowAfterManageController.class);
	
	@Resource(name="bizBorrowService")
	private BizBorrowServiceImpl bizBorrowService;
	
	@Autowired
	public BorrowAfterManageController(BorrowAfterManageServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}
	
	/**
	 * Description：<br> 
	 * 跳转到借后管理的页面
	 * @author  Lin Xu
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toBorrowAfterMPage")
	public ModelAndView toBorrowAfterMPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String isTaskListOpen = request.getParameter("isTaskListOpen");
		if("true".equals(isTaskListOpen)){
			//获取传入时间的下一个月日期第一天 和最后一天
			Map<String,String> map = EscfDateUtil.getNextMonthFirstAndLastDay(new Date());
			//设置一个时间参数 （当前时间的下一个月）
			request.setAttribute("repaidTime", map.get("firstDay"));
			request.setAttribute("repaidEndTime", map.get("lastDay"));
		}
		return new ModelAndView("/business/borrowAfterManage/borrowAfterMList");
	}
	
	/**
	 * Description：<br> 
	 * 加载页面信息页面信息
	 * @author  Lin Xu
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bba
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/getBorrowAfterMList")
	@ResponseBody
	public ModelAndView getBorrowAfterMList(HttpServletRequest request, HttpServletResponse response,
		BizBorrowAfter bba) throws HttpRequestException {
		ModelAndView modelv = new ModelAndView();
		BorrowAfterManageServiceImpl bamservice = (BorrowAfterManageServiceImpl) getService();
		try {
			   PageInfo info = getPageInfo(request);
			   if(null == bba){
				   bba = new BizBorrowAfter();
			   }else{
				   //处理时间
				   if(StringUtil.isNotEmpty(bba.getRepaidTime())){
					   bba.setRepaidTime(bba.getRepaidTime() + " 00:00:00");
				   }
				   if(StringUtil.isNotEmpty(bba.getRepaidEndTime())){
					   bba.setRepaidEndTime(bba.getRepaidEndTime() + " 00:00:00");
				   }
			   }
			   //结果集
			   List<BizBorrowAfter> bizlist = Lists.newArrayList();
			   PageList<BizBorrowAfter> bizpagelist = bamservice.selectBorrowAfterAll(bba, info);
			   int total = 0;
			   //获取结果集
			   if(null != bba && null != bizpagelist && bizpagelist.size() > 0){
				   bizlist = bizpagelist;
				   total = bizpagelist.getPaginator().getTotalCount() ;
			   }
			   //设置结果集
			   modelv.addObject("rows", bizlist);
			   modelv.addObject("total", total);
	 		   MessageBuilder.processSuccess(modelv, null, HttpMessage.SUCCESS_MSG, request);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.info(e.getMessage());
					e.printStackTrace();
				}
				MessageBuilder.processError(modelv, e, request);
			}
		return modelv;
	}

	/**
	 * Description：<br> 
	 * 跳转至信息发送页面
	 * @author  Lin Xu
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bb
	 * @return
	 */
	@RequestMapping("/toSendSMSPage")
	public ModelAndView toSendSMSPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/business/borrowAfterManage/borrowSendSMS");
		//获取选中的PIDS的信息数据信息
		String pidarry = request.getParameter("pids");
		modelv.addObject("pids", pidarry);
		return modelv;
	}
	
	/**
	 * Description：<br> 
	 * 跳转至选中信息页面
	 * @author  Lin Xu
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toChoseSMSPage")
	public ModelAndView toChoseSMSPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/business/borrowAfterManage/borrowChoseSMS");
		return modelv;
	}
	
	/**
	 * Description：<br> 
	 * 跳转至数据下载页面
	 * @author  Lin Xu
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toDownloadPage")
	public ModelAndView toDownloadPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/business/borrowAfterManage/borrowAfterDownload");
		return modelv;	
	}
	
	/**
	 * Description：<br> 
	 * 修改备注信息
	 * @author  Lin Xu
	 * @date    2015年10月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toUpdateDescPage")
	public ModelAndView toUpdateDescPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/business/borrowAfterManage/borrowAfterUpdate");
		String bizPid = request.getParameter("fpid");
		String pypid = request.getParameter("pid");
		String repPlaDesc = request.getParameter("repPlaDesc");
		BizBorrow bizb = new BizBorrow();
		try {
			bizb = (BizBorrow) bizBorrowService.selectByPrimaryKey(bizPid);
			if(null != bizb){
				modelv.addObject("bizb", bizb);
				modelv.addObject("pypid", pypid);
				modelv.addObject("repPlaDesc", StringUtil.isEmpty(repPlaDesc)?"":repPlaDesc);
			}
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return modelv;	
	}
	
	/**
	 * Description：<br> 
	 * 导出借款信息
	 * @author  Lin Xu
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bba
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response,
			BizBorrowAfter bba,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		BorrowAfterManageServiceImpl bamservice = (BorrowAfterManageServiceImpl) getService();
		try {
			if(null == bba){
				bba = new BizBorrowAfter();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				List<BizBorrowAfter> reList = bamservice.selectBorrowAfter(bba, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_borrowafter.xml";
				CheckResult checkrsl = exportExcel("借后管理信息数据.xls", xmlpath, "borrow_after_manage", reList);
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


