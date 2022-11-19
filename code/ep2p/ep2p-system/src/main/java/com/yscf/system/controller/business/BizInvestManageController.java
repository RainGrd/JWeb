/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借款审批Controller
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.investment.BizReceiptPlanInfoModel;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.impl.BizReceiptPlanServiceImpl;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * 
 * Description：<br> 
 * 投资管理
 * @author  JunJie.Liu
 * @date    2015年12月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizInvestController")
public class BizInvestManageController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizInvestManageController.class);
	
	@Autowired
	public BizInvestManageController(BizReceiptPlanServiceImpl service) {
		super(service);
	}

	
	@Resource
	private ISysParamsService sysParamsService;
	
	@Override
	public Class<?> getModel() {
		return BizReceiptPlanInfoModel.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到投资明细
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toList")
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/business/bizBorrowDetail/invest_Info");
		try{
			List<SysParams> list = sysParamsService.searchParamsByKey(SystemParamKeyConstant.EP2P_URL);
			
			if(list!=null && list.size() > 0 ){
				modelView.addObject("ep2pUrl", list.get(0).getParamValue());
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.error("跳转到投资明细", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 投资明细取数据
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getList")
	@ResponseBody
	public ModelAndView getList(BizReceiptPlanInfoModel brpm,String timeType,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			
			// 获取时间类型
			if(StringUtils.hasLength(timeType)){
					brpm.setStartExpireTime2(getDateByTimeType(0+""));
					brpm.setEndExpireTime2(getDateByTimeType(timeType));
			}
			IBizReceiptPlanService service = (BizReceiptPlanServiceImpl)getService();
			PageList<BizReceiptPlanInfoModel> pageList = service
					.searchAllPage(brpm, info);
			BizReceiptPlanInfoModel brp = service.sumAllPage(brpm);
			if(pageList!=null){
				pageList.add(brp);
			}
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator()
					.getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.error("投资明细取数据异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
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
			BizReceiptPlanInfoModel bizReceiptPlanInfoModel,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == bizReceiptPlanInfoModel){
				bizReceiptPlanInfoModel = new BizReceiptPlanInfoModel();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				IBizReceiptPlanService service = (BizReceiptPlanServiceImpl)getService();
				//获取结果集
				List<BizReceiptPlanInfoModel> reList = service.selectBizReceiptPlanInfoModelVOEom(bizReceiptPlanInfoModel, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_receipt_plan_info.xml";
				CheckResult checkrsl = exportExcel("投资项目明细数据.xls", xmlpath, "export_receipt_plan_info", reList);
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
	
	private String getDateByTimeType(String timeType){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar c = Calendar.getInstance();
		int ty = Integer.parseInt(timeType);
		c.add(Calendar.DATE, ty);
		return sdf.format(c.getTime());
	}
	
}


