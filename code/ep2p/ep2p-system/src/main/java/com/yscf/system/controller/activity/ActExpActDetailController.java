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
package com.yscf.system.controller.activity;

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
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.impl.ActExpActDetailServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * 
 * @ClassName : ActExpActDetailController
 * @Description : 体验金明细Controller
 * @Author : Qing.Cai
 * @Date : 2015年10月19日 下午3:35:23
 */
@Controller
@RequestMapping("/actExpActDetailController")
public class ActExpActDetailController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(ActExpActDetailController.class);

	@Autowired
	public ActExpActDetailController(ActExpActDetailServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ActExpActDetail.class;
	}

	/**
	 * 
	 * @Description : 跳转到体验金查询页面
	 * @return actExpActDetail_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:36:21
	 */
	@RequestMapping("/openActExpActDetail")
	public ModelAndView openActExpActDetail() {
		return new ModelAndView("/activity/actExpActDetail_index");
	}

	/**
	 * 
	 * @Description : 跳转到红包明细列表
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return actRedpActDetail_list.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 上午11:33:14
	 */
	@RequestMapping("/openActExpActDetailList")
	public ModelAndView openActExpActDetailList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/activity/detail/actExpActDetail_list");
		try {
			// 赋值
			String actName = java.net.URLDecoder.decode(request.getParameter("actName"), "UTF-8");
			String actCode = java.net.URLDecoder.decode(request.getParameter("actCode"), "UTF-8");
			modelAndView.addObject("activityId", request.getParameter("activityId") != null ? request.getParameter("activityId") : 0);
			modelAndView.addObject("expNumber", request.getParameter("expNumber") != null ? request.getParameter("expNumber") : 0);
			modelAndView.addObject("actCode", actCode != null ? actCode : "");
			modelAndView.addObject("actName", actName != null ? actName : "");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
		}
		return modelAndView;
	}

	/**
	 * 
	 * @Description :体验金活动查询
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @return 体验金明细列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:36:46
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request, HttpServletResponse response, ActExpActDetail actExpActDetail) throws HttpRequestException {
		ActExpActDetailServiceImpl service = (ActExpActDetailServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == actExpActDetail) {
				actExpActDetail = new ActExpActDetail();
			}
			PageList<ActExpActDetail> list = service.selectAllPageByCondition(actExpActDetail, info);
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
	 * @Description : 查询赠送体验金活动明细列表,带分页
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @return 体验金明细列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:37:23
	 */
	@RequestMapping(value = "/selectAllPageDetail")
	@ResponseBody
	public ModelAndView selectAllPageDetail(HttpServletRequest request, HttpServletResponse response, ActExpActDetail actExpActDetail) throws HttpRequestException {
		ActExpActDetailServiceImpl service = (ActExpActDetailServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == actExpActDetail) {
				actExpActDetail = new ActExpActDetail();
			}
			PageList<ActExpActDetail> list = service.selectAllPageDetail(actExpActDetail, info);
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
	 * @Description : 导出
	 * @param request
	 *            HttpServletRequest对象
	 * @param response
	 *            HttpServletResponse对象
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @param expvo
	 *            导出对象
	 * @return
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 下午3:34:49
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response, ActExpActDetail actExpActDetail, ExportObjectVo expvo) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ActExpActDetailServiceImpl service = (ActExpActDetailServiceImpl) getService();
		try {
			if (null == actExpActDetail) {
				actExpActDetail = new ActExpActDetail();
			}
			// 进行导出数据封装
			if (null != expvo) {
				// 通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				// 获取结果集
				List<ActExpActDetail> reList = service.selectAllPageDetailExport(actExpActDetail, eom);
				// 通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_activity_exp_detail.xml";
				CheckResult checkrsl = exportExcel(actExpActDetail.getActName()+"_体验金赠送明细.xls", xmlpath, "export_activity_manage", reList);
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
