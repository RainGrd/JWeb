/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

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
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferModel;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.impl.BizReceiptTransferServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * 
 * Description：<br> 
 * 债权转让管理Controller
 * @author  JunJie.Liu
 * @date    2015年10月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizReceiptTransferController")
public class BizReceiptTransferController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizReceiptTransferController.class);
	
	@Autowired
	public BizReceiptTransferController(BizReceiptTransferServiceImpl service) {
		super(service);
	}
	@Override
	public Class<?> getModel() {
		return BizReceiptTransfer.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到债权转让查询页面
	 * @author  JunJie.Liu
	 * @date    2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toBizReceiptTransferList")
	@ResponseBody
	public ModelAndView toBorrowApprvoe(BizBorrowInfoVO bizBorrowInfoVO,HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return  new ModelAndView("business/bizReceiptTransfer/bizReceiptTransferList");
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 债权转让查询
	 * @author  JunJie.Liu
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param bizReceiptTransferVo
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/bizReceiptTransferList")
	@ResponseBody
	public ModelAndView bizReceiptTransferList(BizReceiptTransferModel bizReceiptTransferVo,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			IBizReceiptTransferService bizReceiptTransferVOService = (BizReceiptTransferServiceImpl) getService();
			PageList<BizReceiptTransferModel> pageList = bizReceiptTransferVOService.selectAllPage(bizReceiptTransferVo, info);
			BizReceiptTransferModel sumVo = bizReceiptTransferVOService.sumBizReceiptTransferVoWhere(bizReceiptTransferVo);
			if(sumVo==null){
				sumVo = new BizReceiptTransferModel();
			}
			pageList.add(sumVo);
			modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){ 
				e.printStackTrace();
				logger.error("债权转让查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;

	}
	
	/**
	 * 
	 * Description：<br> 
	 * 导出债权管理信息
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
			BizReceiptTransferModel bizReceiptTransferVO,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == bizReceiptTransferVO){
				bizReceiptTransferVO = new BizReceiptTransferModel();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				IBizReceiptTransferService bizReceiptTransferVOService = (BizReceiptTransferServiceImpl) getService();
				List<BizReceiptTransferModel> reList = bizReceiptTransferVOService.selectBizReceiptTransferVOEom(bizReceiptTransferVO, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_receipt_transfer.xml";
				CheckResult checkrsl = exportExcel("债权管理信息数据.xls", xmlpath, "export_receipt_transfer", reList);
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
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
}


