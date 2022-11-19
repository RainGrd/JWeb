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
 * 2015年10月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

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

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.util.MiscUtil;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 投标记录Controller
 * @author  Jie.Zou
 * @date    2015年10月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizBorrowDetailController")
public class BizBorrowDetailController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizBorrowDetailController.class);
	
	@Resource(name = "bizBorrowInfoVOService")
	private IBizBorrowInfoVOService bizBorrowInfoVOService;
	
	@Autowired
	public BizBorrowDetailController(BizBorrowDetailServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrowDetail.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到投标记录
	 * @author  Jie.Zou
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowDetailList")
	@ResponseBody
	public ModelAndView toBorrowDetailList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return  new ModelAndView("business/bizBorrowDetail/bizBorrowDetail");
	}
	
	/**
	 * Description：打开投资管理页面
	 * @author  JingYu.Dai
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @return ModleAndView
	 */
	@RequestMapping(value = "/openInvestManagePage")
	@ResponseBody
	public ModelAndView openInvestManagePage(){
		return  new ModelAndView("business/bizBorrowDetail/invest_manage");
	}
	/**
	 * 
	 * Description：<br> 
	 * 投标记录查询
	 * @author  Jie.Zou
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param borrowDetail
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/borrowDetailList")
	@ResponseBody
	public ModelAndView borrowDetailList(BizBorrowDetail borrowDetail,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			BizBorrowDetailServiceImpl service = (BizBorrowDetailServiceImpl)getService();
			PageList<BizBorrowDetail> pageList = service.selectAllPage(borrowDetail, info);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
				logger.info("借款项目发布列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;
	}
	
	/**
	 * Description：充值详情 关联所有子表 条件查询 分页 
	 * @author  JingYu.Dai
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param borrowDetail 充值详情Bean 
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllRelSelectivePage")
	@ResponseBody
	public ModelAndView selectAllRelSelectivePage(BizBorrowDetail borrowDetail,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			BizBorrowDetailServiceImpl service = (BizBorrowDetailServiceImpl)getService();
			PageList<BizBorrowDetail> pageList = service.selectAllRelSelectivePage(borrowDetail, info);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if(logger.isInfoEnabled()){
				e.printStackTrace();
				logger.info("借款项目发布列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款ID得到投标记录
	 * @author  Jie.Zou
	 * @date    2015年12月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/borrowDetailListByBId")
	@ResponseBody
	public ModelAndView borrowDetailListByBId(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			String BId = request.getParameter("borrowId");
			BizBorrowDetailServiceImpl service = (BizBorrowDetailServiceImpl)getService();
			BizBorrowDetail bizBorrowDetail = new BizBorrowDetail();
			bizBorrowDetail.setBorrowId(BId);
			PageList<BizBorrowDetail> pageList = service.getListByBorrowId(bizBorrowDetail, info);
			//计算投标利息
			BizBorrowInfoVO bizBorrowInfoVO = bizBorrowInfoVOService.getBizBorrowById(BId);
			pageList = MiscUtil.calculationInterest(pageList,bizBorrowInfoVO);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
 			if(logger.isDebugEnabled()){
				e.printStackTrace();
				logger.info("借款项目发布列表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投标记录
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSelectBidRecordList")
	@ResponseBody
	public ModelAndView selectBidRecordById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toSelectBidRecordList");
		modelView.addObject("pid", pid); 
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投标记录详细页面
	 * @author  heng.wang
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBidRecordDetailsById")
	@ResponseBody
	public ModelAndView selectBidRecordDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			BizBorrowDetail bizBorrowDetail = new BizBorrowDetail();
			bizBorrowDetail.setPid(pid);
			BizBorrowDetailServiceImpl service = (BizBorrowDetailServiceImpl)getService();
			PageList<BizBorrowDetail> list = service.selectBidRecordDetailsById(bizBorrowDetail, info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID查询投标记录详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户资金流水
	 * @author  heng.wang
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toSelectFundWaterList")
	@ResponseBody
	public ModelAndView toSelectFundWaterList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toSelectFundWaterList");
		modelView.addObject("pid", pid); 
		return modelView;
	}
	
	

}


