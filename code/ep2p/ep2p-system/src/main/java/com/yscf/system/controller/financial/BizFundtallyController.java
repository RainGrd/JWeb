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
 * 2015年10月28日     JingYu.Dai		Initial Version.
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
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.business.BizFundtally;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizFundtallyModel;
import com.yscf.core.service.business.IBizFundtallyService;
import com.yscf.core.service.business.impl.BizFundtallyServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br> 
 * 业务--资金流水	 控制器
 * @author  JingYu.Dai
 * @date    2015年10月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizFundtallyController")
public class BizFundtallyController extends EscfBaseController{

	private Logger logger = LoggerFactory
			.getLogger(BizFundtallyController.class);

	@Autowired
	public BizFundtallyController(BizFundtallyServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizFundtally.class;
	}
	
	/**
	 * Description：<br>
	 * 跳转到 系统收支明细页面
	 * @author JingYu.Dai
	 * @date 2015年9月28日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping("/openBizFundtallyList")
	public ModelAndView openBizFundtallyList() {
		return new ModelAndView("/financial/biz_fundtally_list");
	}
	
	/**
	 * Description：<br> 
	 * 资金流水,条件查询,带分页功能的
	 * @author  JingYu.Dai
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param bizRechargeOffline
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/selectBizFundtallyPage")
	@ResponseBody
	public ModelAndView selectBizFundtallyPage(BizFundtally fundtally,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		PageInfo info = getPageInfo(request);
		BizFundtallyServiceImpl fs = (BizFundtallyServiceImpl) getService();
		try {
			PageList<BizFundtally> list = fs.selectBizFundtallyPage(fundtally, info);
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
	 * 
	 * Description：<br> 
	 * 导出系统支出明细列表
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
			BizFundtally fundtally,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == fundtally){
				fundtally = new BizFundtally();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				IBizFundtallyService service = (BizFundtallyServiceImpl) getService();
				List<BizFundtallyModel> reList = service.selectBizFundtallyEom(fundtally, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_fund_tally.xml";
				CheckResult checkrsl = exportExcel("系统支出明细列表.xls", xmlpath, "export_fund_tally", reList);
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


