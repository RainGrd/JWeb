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
 * 2015年11月4日    JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.report;

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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.statistics.CusBorrowStatistics;
import com.yscf.core.model.statistics.CusInvestStatistics;
import com.yscf.core.model.statistics.CusInvestStatisticsDto;
import com.yscf.core.model.system.SysIpPvHis;
import com.yscf.core.service.statistics.impl.CusBorrowStatisticsServiceImpl;
import com.yscf.core.service.statistics.impl.CusInvestStatisticsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.controller.radio.RadioStatisticsController;

/**
 * Description：<br>
 * 客户统计
 * 
 * @author JingYu.Dai
 * @date 2015年11月5日
 * @version v1.0.0
 */
@Controller
@RequestMapping("cusReportController")
public class CusReportController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(RadioStatisticsController.class);

	@Resource(name = "cusBorrowStatisticsServiceImpl")
	private CusBorrowStatisticsServiceImpl cusBorrowStatisticsServiceImpl;

	@Resource(name = "cusInvestStatisticsServiceImpl")
	private CusInvestStatisticsServiceImpl cusInvestStatisticsServiceImpl;

	@Resource(name = "cusTomerService")
	CusTomerServiceImpl cusTomerService;

	@Autowired
	public CusReportController(CusTomerServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}

	/**
	 * Description：<br>
	 * 跳转到 客户注册 与 实名制认证 趋势图
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws FrameworkException
	 */
	@RequestMapping("/toCusTendencyChart")
	public ModelAndView toCusTendencyChart(HttpServletRequest request, HttpServletResponse response) throws FrameworkException {
		// 统计用户（客户）总数集合
		// （todayAddSum：今日新增用户数、todayCertificationSum：今日实名认证人数、userSum：总用户数、certificationSum：实名认证总人数）
		Map<String, Integer> sumMap = cusTomerService.selectUserSum();
		request.setAttribute("sumMap", sumMap);
		ModelAndView modelv = new ModelAndView("/statistical/user_statistical");
		return modelv;
	}

	/**
	 * Description：<br>
	 * 系统用户（客户）统计报表
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月3日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/userStatistical")
	@ResponseBody
	public ModelAndView userStatistical(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		String type = request.getParameter("timeType");
		String param = request.getParameter("param");
		CusTomerServiceImpl service = (CusTomerServiceImpl) getService();
		try {
			// 系统用户（客户）统计报表
			Map<String, Object> map = service.userStatistical(type, param);
			MessageBuilder.processSuccess(modelView, map, HttpMessage.SUCCESS_MSG, request);
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
	 * 跳转到客户借款统计页面
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorCusStatistical")
	@ResponseBody
	public ModelAndView toBorCusStatistical(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("statistical/bor_cus_statistical");
	}

	/**
	 * Description：<br>
	 * 跳转到客户投资统计页面
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toInvCusStatistical")
	@ResponseBody
	public ModelAndView toInvCusStatistical(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("statistical/inv_cus_statistical");
	}

	/**
	 * 
	 * @Description : 跳转到投资人年龄/性别分布情况统计图
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return investment_statistics.jsp
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月11日 下午4:08:45
	 */
	@RequestMapping(value = "/toInvestmentStatistics")
	@ResponseBody
	public ModelAndView toInvestmentStatistics(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("statistical/investment_statistics");
	}

	/**
	 * 
	 * @Description :跳转到使用终端分布统计图
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return investment_statistics_terminal.jsp
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月11日 下午4:08:22
	 */
	@RequestMapping(value = "/toInvestmentStatisticsTerminal")
	@ResponseBody
	public ModelAndView toInvestmentStatisticsTerminal(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("statistical/investment_statistics_terminal");
	}

	/**
	 * Description：<br>
	 * 借款人区域分布统计 按省份统计
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月3日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBorCusStatisticsGroupProvince")
	@ResponseBody
	public ModelAndView selectBorCusStatisticsGroupProvince(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("orderByType", request.getParameter("orderByType"));
			// 借款人区域分布统计 按省份统计
			List<CusBorrowStatistics> list = cusBorrowStatisticsServiceImpl.selectBorCusStatisticsGroupProvince(queryMap);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.size());
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
	 * 借款人区域分布统计 按省份统计
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月3日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBorCusStatisticsGroupCity")
	@ResponseBody
	public ModelAndView selectBorCusStatisticsGroupCity(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("orderByType", request.getParameter("orderByType"));
			queryMap.put("province", request.getParameter("province"));
			// 借款人区域分布统计 按市统计
			List<CusBorrowStatistics> list = cusBorrowStatisticsServiceImpl.selectBorCusStatisticsGroupCity(queryMap);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.size());
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
	 * 投资人区域分布统计 按省份统计
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月3日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectInvCusStatisticsGroupProvince")
	@ResponseBody
	public ModelAndView selectInvCusStatisticsGroupProvince(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("orderByType", request.getParameter("orderByType"));
			// 借款人区域分布统计 按省份统计
			List<CusInvestStatistics> list = cusInvestStatisticsServiceImpl.selectInvCusStatisticsGroupProvince(queryMap);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.size());
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
	 * 投资人区域分布统计 按省份统计
	 * 
	 * @author JingYu.Dai
	 * @date 2015年11月3日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectInvCusStatisticsGroupCity")
	@ResponseBody
	public ModelAndView selectInvCusStatisticsGroupCity(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			Map<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("orderByType", request.getParameter("orderByType"));
			queryMap.put("province", request.getParameter("province"));
			// 借款人区域分布统计 按市统计
			List<CusInvestStatistics> list = cusInvestStatisticsServiceImpl.selectInvCusStatisticsGroupCity(queryMap);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.size());
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
	 * @Description : 投资人年龄/性别分布情况
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param type
	 *            1 年龄 2 性别
	 * @return 统计列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月6日 下午5:00:16
	 */
	@RequestMapping(value = "/selectInvestmentStatisticsByCondition")
	@ResponseBody
	public ModelAndView selectInvestmentStatisticsByCondition(HttpServletRequest request, HttpServletResponse response, Integer type) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			// 投资人年龄/性别分布情况
			List<CusInvestStatisticsDto> list = cusInvestStatisticsServiceImpl.selectInvestmentStatisticsByCondition(type);
			modelView.addObject("datas", list);
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
	 * @Description : 使用终端统计
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param sysIpPvHis
	 *            统计条件
	 * @return 统计列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月11日 下午4:01:28
	 */
	@RequestMapping(value = "/selectSysIpPvHisStatistics")
	@ResponseBody
	public ModelAndView selectSysIpPvHisStatistics(HttpServletRequest request, HttpServletResponse response, SysIpPvHis sysIpPvHis) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			// 使用终端统计
			List<CusInvestStatisticsDto> list = cusInvestStatisticsServiceImpl.selectSysIpPvHisStatistics(sysIpPvHis);
			modelView.addObject("datas", list);
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

}
