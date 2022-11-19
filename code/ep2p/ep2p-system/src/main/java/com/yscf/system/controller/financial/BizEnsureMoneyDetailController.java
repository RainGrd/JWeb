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
 * 2015年10月22日     jenkin.yu		Initial Version.
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
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizEnsureMoneyDetail;
import com.yscf.core.service.financial.IBizEnsureMoneyDetailService;
import com.yscf.core.service.financial.impl.BizEnsureMoneyDetailServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br> 
 * 备付金详情
 * @author  jenkin.yu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizEnsureMoneyDetailController")
public class BizEnsureMoneyDetailController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(BizEnsureMoneyDetailController.class);

	@Autowired
	public BizEnsureMoneyDetailController(BizEnsureMoneyDetailServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizEnsureMoneyDetail.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转至明细页面
	 * @author  Yu.Zhang
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toEnsureDtailList")
	@ResponseBody
	public ModelAndView toEnsureDtailList(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		ModelAndView modelView = new ModelAndView("/financial/bizEnsureMoney/ensure_detail_list");
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 明细查询
	 * @author  Yu.Zhang
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param bizEnsureMoneyDetail
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/selectForTypeToList")
	@ResponseBody
	public ModelAndView selectForTypeToList(String isDetail,BizEnsureMoneyDetail bizEnsureMoneyDetail,HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		BizEnsureMoneyDetailServiceImpl service = (BizEnsureMoneyDetailServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			bizEnsureMoneyDetail.setIsDetail(isDetail);
			// 初始化开始查询时间
			if(null!=bizEnsureMoneyDetail.getStartDate() && !"".equals(bizEnsureMoneyDetail.getStartDate())){
				bizEnsureMoneyDetail.setStartDate(EscfDateUtil.formatterDate(bizEnsureMoneyDetail.getStartDate(), 1));
			}
			
			// 初始化开结束查询时间
			if(null!=bizEnsureMoneyDetail.getEndDate() && !"".equals(bizEnsureMoneyDetail.getEndDate())){
				bizEnsureMoneyDetail.setEndDate(EscfDateUtil.formatterDate(bizEnsureMoneyDetail.getEndDate(), 2));
			}
			
			PageList<BizEnsureMoneyDetail> list = service.selectBizEnsureDetailPage(bizEnsureMoneyDetail, info);
			BigDecimal sumExpenditures = new BigDecimal(0);
			BigDecimal sumIncome = new BigDecimal(0);
			

			// 有根据收入/支出 查询，则根据收入/支出查询
			if(null!=bizEnsureMoneyDetail.getFeeType() && !"".equals(bizEnsureMoneyDetail.getFeeType())){
				// 获取 支出总计
				if(Constant.FEE_TYPE_2.equals(bizEnsureMoneyDetail.getFeeType())){
					sumExpenditures = service.selectSumHappenValue(bizEnsureMoneyDetail);
				}else if (Constant.FEE_TYPE_1.equals(bizEnsureMoneyDetail.getFeeType())){
					// 获取 收入总计
					sumIncome = service.selectSumHappenValue(bizEnsureMoneyDetail);
				}
			}else{
				// 根据类型查询
				if(Constant.ENSURE_TYPE_1.equals(bizEnsureMoneyDetail.getEnsMonDetType()) || Constant.ENSURE_TYPE_3.equals(bizEnsureMoneyDetail.getEnsMonDetType())){
					// 获取 收入总计
					bizEnsureMoneyDetail.setFeeType(Constant.FEE_TYPE_1);
					sumIncome = service.selectSumHappenValue(bizEnsureMoneyDetail);
				}else if (Constant.ENSURE_TYPE_2.equals(bizEnsureMoneyDetail.getEnsMonDetType())){
					// 获取 支出总计
					bizEnsureMoneyDetail.setFeeType(Constant.FEE_TYPE_2);
					sumExpenditures = service.selectSumHappenValue(bizEnsureMoneyDetail);
				}else{
					// 获取 收入总计
					bizEnsureMoneyDetail.setFeeType(Constant.FEE_TYPE_1);
					sumIncome = service.selectSumHappenValue(bizEnsureMoneyDetail);
					// 获取 支出总计
					bizEnsureMoneyDetail.setFeeType(Constant.FEE_TYPE_2);
					sumExpenditures = service.selectSumHappenValue(bizEnsureMoneyDetail);
				}

			}
			// 清空类型，防止页面选中
			bizEnsureMoneyDetail.setFeeType(null);
			
			BizEnsureMoneyDetail sum = new BizEnsureMoneyDetail();
			sum.setSumExpenditures(sumExpenditures.multiply(new BigDecimal(-1)));
			sum.setSumIncome(sumIncome);
			sum.setFeeType(Constant.FEE_TYPE_3);
			list.add(sum);
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
	 * 是否禁用初始化备付金按钮
	 * @author  Yu.Zhang
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param bizEnsureMoneyDetail
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/isDisabledInitBut")
	@ResponseBody
	public ModelAndView isDisabledInitBut(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		IBizEnsureMoneyDetailService service = (IBizEnsureMoneyDetailService) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			BizEnsureMoneyDetail bizEnsureMoneyDetail = new BizEnsureMoneyDetail();
			bizEnsureMoneyDetail.setStatus(Constant.ACTIVATE);
			List<BizEnsureMoneyDetail> list = service.selectAll(bizEnsureMoneyDetail);
			boolean result = false;
			if(null!=list && list.size()>0){
				result = true;
			}
			modelView.addObject("result", result);
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
	 * Description：<br> 
	 * 导出备付金明细信息
	 * @author  Lin Xu
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bba
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response,BizEnsureMoneyDetail bizEnsureMoneyDetail,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		IBizEnsureMoneyDetailService bamservice = (IBizEnsureMoneyDetailService) getService();
		try {
			if(null == bizEnsureMoneyDetail){
				bizEnsureMoneyDetail = new BizEnsureMoneyDetail();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				List<BizEnsureMoneyDetail> reList = bamservice.selectEnsureMoneyDetail(bizEnsureMoneyDetail, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_ensure_money_detail.xml";
				CheckResult checkrsl = exportExcel("备付金使用明细数据.xls", xmlpath, "ensure_money_manage", reList);
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


