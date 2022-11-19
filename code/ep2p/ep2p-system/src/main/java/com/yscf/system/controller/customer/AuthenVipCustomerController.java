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
import java.util.Map;

import javax.annotation.Resource;
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
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.CusBirWarnHis;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustAuthenticationHis;
import com.yscf.core.service.user.impl.CusBirWarnHisServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustAuthenticationHisServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * 
 * @ClassName : AuthenVipCustomerController
 * @Description : 实名认证orVIP设置Controller
 * @Author : Qing.Cai
 * @Date : 2015年10月21日 下午4:23:13
 */
@Controller
@RequestMapping("/authenVipCustomerController")
public class AuthenVipCustomerController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(AuthenVipCustomerController.class);

	@Autowired
	public AuthenVipCustomerController(CusTomerServiceImpl service) {
		super(service);
	}

	// VIP生日提醒历史商务接口
	@Resource(name = "cusBirWarnHisServiceImpl")
	public CusBirWarnHisServiceImpl cusBirWarnHisServiceImpl;

	@Resource(name = "custAuthenticationHisServiceImpl")
	public CustAuthenticationHisServiceImpl custAuthenticationHisServiceImpl;

	@Override
	public Class<?> getModel() {
		return CusTomer.class;
	}

	/**
	 * 
	 * @Description : 跳转到实名认证页面
	 * @return custAuthenticationHis_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月21日 下午4:26:27
	 */
	@RequestMapping("/openCustAuthenticationHis")
	public ModelAndView openCustAuthenticationHis(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		return new ModelAndView("/customer/custAuthenticationHis_index");
	}

	/**
	 * 
	 * @Description : 跳转到实名认证审核页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return custAuthenticationHis_auditing.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 下午3:20:14
	 */
	@RequestMapping("/openCustAuthenticationHisEdit")
	public ModelAndView openCustAuthenticationHisEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/customer/custAuthenticationHis_auditing");
		// 赋值
		modelAndView.addObject("pid", request.getParameter("pid") != null ? request.getParameter("pid") : 0);
		return modelAndView;
	}

	/**
	 * 
	 * @Description : VIP生日提醒页面
	 * @return cusBirWarnHis_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午6:55:23
	 */
	@RequestMapping("/openCusBirWarnHis")
	public ModelAndView openCusBirWarnHis() {
		return new ModelAndView("/customer/cusBirWarnHis_index");
	}

	/**
	 * 
	 * @Description : 查看所有提交实名认证的客户列表
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param cusTomer
	 *            客户对象
	 * @return 提交实名认证的客户列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月21日 下午4:26:50
	 */
	@RequestMapping(value = "/selectAuthenticationByCondition")
	@ResponseBody
	public ModelAndView selectAuthenticationByCondition(HttpServletRequest request, HttpServletResponse response, CusTomer cusTomer) throws HttpRequestException {
		CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == cusTomer) {
				cusTomer = new CusTomer();
			}
			PageList<CusTomer> list = service.selectAuthenticationByCondition(cusTomer, info);
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
	 * @Description : 实名认证列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pid
	 *            用户ID
	 * @return 当前用户的实名认证历史记录
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 下午4:16:06
	 */
	@RequestMapping(value = "/selectAuthenticationByPid")
	@ResponseBody
	public ModelAndView selectAuthenticationByPid(HttpServletRequest request, HttpServletResponse response, String pid) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			CustAuthenticationHis custAuthenticationHis = new CustAuthenticationHis();
			custAuthenticationHis.setCustomerId(pid);
			PageList<CustAuthenticationHis> list = custAuthenticationHisServiceImpl.selectAllPage(custAuthenticationHis, info);
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
	 * @Description : 新增实名认证
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param custAuthenticationHis
	 *            实名认证对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月27日 上午9:37:36
	 */
	@RequestMapping(value = "/saveCustAuthenticationHis")
	@ResponseBody
	public void saveCustAuthenticationHis(HttpServletRequest request, HttpServletResponse response, CustAuthenticationHis custAuthenticationHis) throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 赋值创建人信息
			custAuthenticationHis.setCreateUser(getContextUser().getUserId());
			// 业务放到servicce层处理
			custAuthenticationHisServiceImpl.saveCustAuthenticationHis(custAuthenticationHis);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("VIP生日提醒失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : VIP生日提醒列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @return 当年VIP生日提醒列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午6:52:23
	 */
	@RequestMapping(value = "/selectCusBirWarnHisByCondition")
	@ResponseBody
	public ModelAndView selectCusBirWarnHisByCondition(HttpServletRequest request, HttpServletResponse response, CusBirWarnHis cusBirWarnHis) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == cusBirWarnHis) {
				cusBirWarnHis = new CusBirWarnHis();
			}
			PageList<CusBirWarnHis> list = cusBirWarnHisServiceImpl.selectAllPage(cusBirWarnHis, info);
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
	 * @Description : 新增VIP生日短信提醒
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 上午2:39:41
	 */
	@RequestMapping(value = "/saveCusBirWarnHis")
	@ResponseBody
	public void saveCusBirWarnHis(HttpServletRequest request, HttpServletResponse response, CusBirWarnHis cusBirWarnHis) throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 赋值创建人信息
			cusBirWarnHis.setCreateUser(getContextUser().getUserId());
			// 业务放到servicce层处理
			cusBirWarnHisServiceImpl.saveCusBirWarnHis(cusBirWarnHis);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("VIP生日提醒失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}
	
	/**
	 * 
	 * @Description : 导出
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param actVipActDetail
	 *            赠送VIP明细对象
	 * @param expvo
	 *            导出对象
	 * @return
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 上午10:59:03
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response, CusBirWarnHis cusBirWarnHis, ExportObjectVo expvo) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if (null == cusBirWarnHis) {
				cusBirWarnHis = new CusBirWarnHis();
			}
			// 进行导出数据封装
			if (null != expvo) {
				// 通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				// 获取结果集
				List<CusBirWarnHis> reList = cusBirWarnHisServiceImpl.selectAllPageDetailExport(cusBirWarnHis, eom);
				// 通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_customer_birWarnHis.xml";
				CheckResult checkrsl = exportExcel("VIP生日提醒列表.xls", xmlpath, "export_customer_manage", reList);
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
