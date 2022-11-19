/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.customer;

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
import com.yscf.core.model.user.CustExchangeDetail;
import com.yscf.core.service.user.impl.CustExchangeDetailServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * 
 * @ClassName : CustExchangeDetailController
 * @Description : 兑换明细Controller
 * @Author : Qing.Cai
 * @Date : 2016年1月14日 上午11:02:28
 */
@Controller
@RequestMapping("/custExchangeDetailController")
public class CustExchangeDetailController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(CustExchangeDetailController.class);

	@Autowired
	public CustExchangeDetailController(CustExchangeDetailServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CustExchangeDetail.class;
	}

	/**
	 * 
	 * @Description : 跳转到兑换明细查询
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return custExchangeDetail_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午11:04:38
	 */
	@RequestMapping("/openCustExchangeDetail")
	public ModelAndView openCustExchangeDetail(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/statistical/custExchangeDetail_index");
	}

	/**
	 * 
	 * @Description : 跳转到积分兑换统计
	 * @param request
	 * @param response
	 * @return custExchangeDetail_statistics.jsp
	 * @Author : Qing.Cai
	 * @Date : 2016年1月15日 下午2:33:02
	 */
	@RequestMapping("/openCustExchangeDetailStatistics")
	public ModelAndView openCustExchangeDetailStatistics(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("/statistical/custExchangeDetail_statistics");
	}

	/**
	 * 
	 * @Description : 查询所有兑换明细
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @return 需要查询的对象明细集合
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午11:18:30
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request, HttpServletResponse response, CustExchangeDetail custExchangeDetail) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustExchangeDetailServiceImpl service = (CustExchangeDetailServiceImpl) getService();
		try {
			PageInfo info = getPageInfo(request);
			if (null == custExchangeDetail) {
				custExchangeDetail = new CustExchangeDetail();
			}
			// 调用接口,获取所有的满足条件的兑换明细
			PageList<CustExchangeDetail> list = service.selectAllPage(custExchangeDetail, info);
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
	 * @Description : 积分兑换统计查询
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param custExchangeDetail
	 *            兑换明细对象
	 * @return 统计兑换明细列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 下午9:10:29
	 */
	@RequestMapping(value = "/selectAllStatistics")
	@ResponseBody
	public ModelAndView selectAllStatistics(HttpServletRequest request, HttpServletResponse response, CustExchangeDetail custExchangeDetail) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustExchangeDetailServiceImpl service = (CustExchangeDetailServiceImpl) getService();
		try {
			if (null == custExchangeDetail) {
				custExchangeDetail = new CustExchangeDetail();
			}
			// 调用接口,获取所有的满足条件的统计兑换明细
			List<CustExchangeDetail> list = service.selectAllStatistics(custExchangeDetail);
			modelView.addObject("rows", list);
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
	 * @Description : 导出
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param custExchangeDetail
	 *            兑换明细对象（查询条件）
	 * @param expvo
	 *            导出对象
	 * @return 需要导出的集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月14日 上午11:06:17
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response, CustExchangeDetail custExchangeDetail, ExportObjectVo expvo) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		CustExchangeDetailServiceImpl service = (CustExchangeDetailServiceImpl) getService();
		try {
			if (null == custExchangeDetail) {
				custExchangeDetail = new CustExchangeDetail();
			}
			// 进行导出数据封装
			if (null != expvo) {
				// 通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				// 获取结果集
				List<CustExchangeDetail> reList = service.selectAllPageExport(custExchangeDetail, eom);
				// 通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_cust_exchangeDetatil.xml";
				CheckResult checkrsl = exportExcel("积分兑换查询.xls", xmlpath, "export_cust_exchangeDetail_manage", reList);
				// 获取生成的文件信息
				if (checkrsl.isPass()) {
					File file = (File) checkrsl.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
					FileDownload.downloadAbsoluteFile(response, request, file, true);
					logger.info("POI 2007 导出的结果文件为名：" + file.getName());
					processSuccess(remap, file.getName());
				} else {
					List<String> errorMsg = checkrsl.getErrorMessage();
					for (String str : errorMsg) {
						processError(remap, str);
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap, e.getMessage());
		}
		return remap;
	}

	/**
	 * 
	 * @Description : 导出统计
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param custExchangeDetail
	 *            兑换明细对象（查询条件）
	 * @param expvo
	 *            导出对象
	 * @return 需要导出的集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月18日 上午10:28:45
	 */
	@RequestMapping("/exportDownLoadFileStatistics")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFileStatistics(HttpServletRequest request, HttpServletResponse response, CustExchangeDetail custExchangeDetail, ExportObjectVo expvo) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		CustExchangeDetailServiceImpl service = (CustExchangeDetailServiceImpl) getService();
		try {
			if (null == custExchangeDetail) {
				custExchangeDetail = new CustExchangeDetail();
			}
			// 进行导出数据封装
			if (null != expvo) {
				// 通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				// 获取结果集
				List<CustExchangeDetail> reList = service.selectAllStatisticsExport(custExchangeDetail, eom);
				// 通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_cust_exchangeStatistics.xml";
				CheckResult checkrsl = exportExcel("积分兑换统计.xls", xmlpath, "export_cust_exchangeStatistics_manage", reList);
				// 获取生成的文件信息
				if (checkrsl.isPass()) {
					File file = (File) checkrsl.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
					FileDownload.downloadAbsoluteFile(response, request, file, true);
					logger.info("POI 2007 导出的结果文件为名：" + file.getName());
					processSuccess(remap, file.getName());
				} else {
					List<String> errorMsg = checkrsl.getErrorMessage();
					for (String str : errorMsg) {
						processError(remap, str);
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap, e.getMessage());
		}
		return remap;
	}

}
