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
 * 2015年11月4日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.report;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ArithmeticUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizBorrowTimeVO;
import com.yscf.core.model.business.BizOverdueRateVO;
import com.yscf.core.model.ptp.financial.BizBorrowStatModel;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.controller.radio.RadioStatisticsController;

/**
 * Description：<br> 
 * 借款统计报表
 * @author  Yu.Zhang
 * @date    2015年11月4日
 * @version v1.0.0
 */
@Controller
@RequestMapping("report/bizBorrowReportController")
public class BizBorrowReportController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(RadioStatisticsController.class);
	
	@Resource(name = "bizBorrowInfoVOService")
	private IBizBorrowInfoVOService bizBorrowInfoVOService;
	
	@Resource(name = "bizRepaymentPlanService")
	private IBizRepaymentPlanService bizRepaymentPlanService;
	
	@Resource(name = "bizBorrowService")
	private IBizBorrowService  bizBorrowService ;
	
	@Autowired
	public BizBorrowReportController(BizBorrowServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}
	
	/**
	 * Description：<br> 
	 * 跳转到借款统计页面
	 * @author  Yu.Zhang
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowReportPage")
	@ResponseBody
	public ModelAndView toBorrowCusList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("report/borrow_report");
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 
	 * 获取借款统计数据
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBorrowReport")
	public void getBorrowReport(BizBorrowInfoVO vo,HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		
		Map<String,Object> modelView =  new HashMap<String,Object>();
		try{
			// 1 获取全部借款的借款总申请数、申请通过数、申请通过率
			BizBorrowInfoVO bizBorrowInfoVO = bizBorrowInfoVOService.getApproverCountByBorrowType(vo);
			modelView.put("allApprove", bizBorrowInfoVO);
			
			// 2 获取e抵押的借款总申请数、申请通过数、申请通过率
			vo.setBorrowType(Constant.BORROW_TYPE_1);
			bizBorrowInfoVO = bizBorrowInfoVOService.getApproverCountByBorrowType(vo);
			modelView.put("mortgageApprove", bizBorrowInfoVO);
			
			// 3 获取e首房的借款总申请数、申请通过数、申请通过率
			vo.setBorrowType(Constant.BORROW_TYPE_2);
			bizBorrowInfoVO = bizBorrowInfoVOService.getApproverCountByBorrowType(vo);
			modelView.put("downLoanApprove", bizBorrowInfoVO);
			
			// 4 获取e计划 体验标 新手标 发布笔数
			// 新手标
			Integer newStandardCount = bizBorrowService.getBorrowCountByNewStandard(vo.getStartApproveTime(), vo.getEndApproveTime());
			
			// 体验标
			Integer experienceStandardCount = bizBorrowService.getBorrowCountByExperienceStandard(vo.getStartApproveTime(),vo.getEndApproveTime());
			
			modelView.put("newStandardCount", newStandardCount);
			modelView.put("experienceStandardCount", experienceStandardCount);
			
			modelView.put("message", HttpMessage.SUCCESS_CODE);
		}catch(Exception e){
			modelView.put("message", HttpMessage.ERROR_CODE);
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("获取借款统计数据异常"+e.getMessage());
			}
		}
		outputJson(modelView,response);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 
	 * 跳转到逾期率统计页面
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toOverdueRatePage")
	@ResponseBody
	public ModelAndView toOverdueRatePage (HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView("report/overdue_rate_report");
		try{
			// 获取总还款期数
			Integer sumReapymentPlanNum = bizRepaymentPlanService.getSumReapymentPlanNum();
			modelView.addObject("sumReapymentPlanNum",sumReapymentPlanNum);
			
			// 获取总逾期数
			Integer sumOverdunNum =  bizRepaymentPlanService.getSumOverdueNum();
			modelView.addObject("sumOverdunNum",sumOverdunNum);
			
			// 计算 总逾期率=  逾期数/总还款期数
			BigDecimal sumOverdunRate = ArithmeticUtil.mul(ArithmeticUtil.div(new BigDecimal(sumOverdunNum), new BigDecimal(sumReapymentPlanNum),4), new BigDecimal(100));
			modelView.addObject("sumOverdunRate",sumOverdunRate);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("跳转到逾期率统计页面,计算总还款期数异常"+e.getMessage());
			}
		}			
		
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 
	 * 跳转到还款数据统计统计页面
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toRepaymentDataReportPage")
	@ResponseBody
	public ModelAndView toRepaymentDataReportPage (HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView("report/repayment_data_report");
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 
	 * 获取还款数据统计
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getRepaymentDataReport")
	@ResponseBody
	public ModelAndView getRepaymentDataReport (HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			// 1 获取已还款数据
			BigDecimal hasRepaymentAmt = bizRepaymentPlanService.getHasRepaymentAmt();
			if(null == hasRepaymentAmt){
				hasRepaymentAmt =  new BigDecimal(0); 
			}
			
			// 2 获取正常待还数据 
			BigDecimal pendingRepaymentAmt = bizRepaymentPlanService.getPendingRepaymentAmt();
			if(null == pendingRepaymentAmt){
				pendingRepaymentAmt =  new BigDecimal(0); 
			}
			
			// 3 获取逾期未还数据
			BigDecimal overdueNoRepaymentAmt = bizRepaymentPlanService.getOverdueNoRepaymentAmt();
			if(null == overdueNoRepaymentAmt){
				overdueNoRepaymentAmt =  new BigDecimal(0); 
			}
			
			
			// 4 计算总金额
			BigDecimal sumAmt =ArithmeticUtil.add(overdueNoRepaymentAmt, ArithmeticUtil.add(hasRepaymentAmt, pendingRepaymentAmt)) ;
			if(null == sumAmt){
				sumAmt =  new BigDecimal(0); 
			}
			
			// 5 计算已还金额率
			BigDecimal hasRepaymentAmtRate = ArithmeticUtil.mul(ArithmeticUtil.div(hasRepaymentAmt,sumAmt,4),new BigDecimal(100));
			
			// 6 计算正常待还率
			BigDecimal pendingRepaymentAmtRate =  ArithmeticUtil.mul(ArithmeticUtil.div(pendingRepaymentAmt,sumAmt,4),new BigDecimal(100));
			
			// 7 计算逾期未还率
			BigDecimal overdueNoRepaymentAmtRate = ArithmeticUtil.mul(ArithmeticUtil.div(overdueNoRepaymentAmt,sumAmt,4),new BigDecimal(100));
			
			// 定义报表数组
			String [] [] result = new String[3][2];
			
			DecimalFormat myformat = new DecimalFormat("##,###.00");
			
			String [] hasRepaymentAmtArray =  {"已还款("+(hasRepaymentAmt.compareTo(new BigDecimal(0))==0?new BigDecimal(0):myformat.format(hasRepaymentAmt))+")",hasRepaymentAmtRate.toString()};
			String [] pendingRepaymentAmtArray =  {"正常待还("+(pendingRepaymentAmt.compareTo(new BigDecimal(0))==0?new BigDecimal(0):myformat.format(pendingRepaymentAmt))+")",pendingRepaymentAmtRate.toString()};
			String [] overdueNoRepaymentAmtArray =  {"逾期未还("+(overdueNoRepaymentAmt.compareTo(new BigDecimal(0))==0?new BigDecimal(0):myformat.format(overdueNoRepaymentAmt))+")",overdueNoRepaymentAmtRate.toString()};
			
			result[0]=  hasRepaymentAmtArray;
			result[1]=  pendingRepaymentAmtArray;
			result[2]=  overdueNoRepaymentAmtArray;
			System.out.println(result);
			modelView.addObject("result",result);
			MessageBuilder.processSuccess(modelView, null,HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			MessageBuilder.processError(modelView, e, request);
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("获取还款数据统计异常"+e.getMessage());
			}
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 
	 * 获取逾期率统计数据
	 * 
	 * @author  Yu.Zhang
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getOverdueRate")
	@ResponseBody
	public ModelAndView getOverdueRate (HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		
		ModelAndView modelView =  new ModelAndView();
		try{
			// 获取逾期率表格数据
			List<BizOverdueRateVO> list = bizRepaymentPlanService.selectOverdueRateVOList();
			modelView.addObject("rows",list);
			modelView.addObject("total",0);
			
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("获取借款统计数据异常"+e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 统计借款类型数据占比
	 * @author  JunJie.Liu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectStatByBorrowType")
	@ResponseBody
	public ModelAndView selectStatByBorrowType(HttpServletRequest request, HttpServletResponse response){
		
		return new ModelAndView("/report/bizBorrowTypeStat");
	}


	/**
	 * 
	 * Description：<br> 
	 * 统计借款类型数据占比
	 * @author  JunJie.Liu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectStatByBorrowTypeData")
	@ResponseBody
	public ModelAndView selectStatByBorrowTypeData(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelView = new ModelAndView();
		
		try {
			
			List<BizBorrowStatModel> listVo = bizBorrowService.selectStatByBorrowType(Constant.BORROW_STATUS_4);
			modelView.addObject("rows",listVo);
			modelView.addObject("total",0);
			
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("统计借款类型数据占比："+e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 跳转统计借款项目数量占比页面
	 * @author  JunJie.Liu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/selectByBorrowCount")
	@ResponseBody
	public ModelAndView selectByBorrowCount(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelView = new ModelAndView("/report/borrow_count_report");

		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转统计借款项目数量占比页面
	 * @author  JunJie.Liu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByBorrowCountData")
	@ResponseBody
	public ModelAndView findByBorrowCountData(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView modelView = new ModelAndView();
		try {
			
			List<BizBorrowStatModel> listVo = bizBorrowService.findByBorrowCountData();
			
			//总数
			Integer totalCount = 0;
			// 满标
			Integer fullScale = 0;
			// 撤销
			Integer revoke = 0;
			// 流标
			Integer  flowMark = 0;
			
			
			for(BizBorrowStatModel vo : listVo){
				if(vo!=null && StringUtils.hasLength(vo.getBorrowType())){
					try {
						if(Constant.BORROW_STATUS_3.equals(vo.getBorrowType())){
							// 流标
							flowMark += vo.getTotalCount() == null ? 0 : vo.getTotalCount();
							
						}else if(Constant.BORROW_STATUS_0.equals(vo.getBorrowType())){
							// 撤销
							revoke += vo.getTotalCount() == null ? 0 : vo.getTotalCount();
							
						}else if(Integer.parseInt(vo.getBorrowType())>=Integer.parseInt(Constant.BORROW_STATUS_4)){
							// 满标及满标以后
							fullScale += vo.getTotalCount() == null ? 0 : vo.getTotalCount();
						}
					} catch (Exception e) {
						logger.error(e.getMessage());
						continue;
					}
				
				}
				
			}
		
		   totalCount = fullScale + revoke + flowMark;
		
		
			modelView.addObject("totalCount",totalCount);
			modelView.addObject("fullScale",fullScale);
			modelView.addObject("revoke",revoke);
			modelView.addObject("flowMark",flowMark);
		
		
			MessageBuilder.processSuccess(modelView, null,HttpMessage.SUCCESS_MSG, request);
		
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("统计借款项目数量占比："+e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款期限得到分类
	 * @author  Jie.Zou
	 * @date    2015年11月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBorrowTimeP")
	@ResponseBody
	public ModelAndView getBorrowTimeP (HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			List<BizBorrowTimeVO> listVo = service.getProportionByBorrowTime();
			modelView.addObject("rows",listVo);
			modelView.addObject("total",0);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		
		return modelView;

	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到借款期限统计
	 * @author  Jie.Zou
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowTimePage")
	@ResponseBody
	public ModelAndView toBorrowTimePage (HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView("report/borrow_time_report");
		return modelView;
	}
	
}


