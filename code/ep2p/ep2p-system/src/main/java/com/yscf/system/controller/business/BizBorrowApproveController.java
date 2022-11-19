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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

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
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.impl.BizBorrowApproveServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 借款审批Controller
 * @author  Yu.Zhang
 * @date    2015年9月24日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizBorrowApproveController")
public class BizBorrowApproveController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizBorrowApproveController.class);
	
	@Autowired
	public BizBorrowApproveController(BizBorrowApproveServiceImpl service) {
		super(service);
	}

	@Resource(name = "bizBorrowInfoVOService")
	private IBizBorrowInfoVOService bizBorrowInfoVOService;
	
	@Resource(name = "bizBorrowService")
	private IBizBorrowService bizBorrowService;
	
	@Override
	public Class<?> getModel() {
		return BizBorrowInfoVO.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到借款审核页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowApprvoe")
	@ResponseBody
	public ModelAndView toBorrowApprvoe(BizBorrowInfoVO bizBorrowInfoVO,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelView =  new ModelAndView("business/borrow/borrow_approve");
		try{
			List<BizBorrowInfoVO> bizBorrowInfoVOList = bizBorrowInfoVOService.selectAll(bizBorrowInfoVO);
			if(null!=bizBorrowInfoVOList && bizBorrowInfoVOList.size() > 0){
				BizBorrowInfoVO vo =  bizBorrowInfoVOList.get(0);
				DecimalFormat myformat = new DecimalFormat("##,###.00");
//				if(null!=vo.getApplyTime() && !"".equals(vo.getApplyTime())){
//					vo.setApplyTime(vo.getApplyTime().substring(0,(vo.getApplyTime().length()-2)));
//				}
				vo.setBorrowMoneyStr(myformat.format(vo.getBorrowMoney()));
				if(null!=vo.getHomeTotal()){
					vo.setHomeTotalStr(myformat.format(vo.getHomeTotal()));
				}
				modelView.addObject("borrow", vo);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.info("跳转到借款担保审核页面异常",e.getMessage());
			}
		}
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到借款担保审核页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowReviewList")
	@ResponseBody
	public ModelAndView toBorrowReviewList(BizBorrowInfoVO bizBorrowInfoVO,HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		ModelAndView modelView =  new ModelAndView("business/borrow/borrow_review_list");
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到借款担保审核列表页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowPrelimList")
	@ResponseBody
	public ModelAndView toBorrowPrelimList(HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("isTaskListOpen", request.getParameter("isTaskListOpen"));
		ModelAndView modelView =  new ModelAndView("business/borrow/borrow_prelim_list");
		return  modelView;
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 借款审批列表查询
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(BizBorrowInfoVO borrow,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView =  new ModelAndView();
		try{
			PageInfo info = getPageInfo(request);
			if(null!=borrow.getStartApproveTime() && "".equals(borrow.getStartApproveTime())){
				borrow.setStartApproveTime(borrow.getStartApproveTime()+EscfDateUtil.BEGIN_MINUTE);
			}
			if(null!=borrow.getEndApproveTime() && "".equals(borrow.getEndApproveTime())){
				borrow.setEndApproveTime(borrow.getEndApproveTime()+EscfDateUtil.END_MINUTE);
			}
			PageList<BizBorrowInfoVO> pageList = bizBorrowInfoVOService.selectAllPage(borrow, info);			
			// 查询借款金额合计
			BigDecimal sumRepaymentAmt = bizBorrowInfoVOService.getSumborrowMoneyByApparoveNode(borrow);
			BizBorrowInfoVO total = new BizBorrowInfoVO();
			total.setBorrowMoney(sumRepaymentAmt);
			total.setIsTotal("yes");
			pageList.add(total);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("借款列表查询异常",e.getMessage());
			}
		}
		modelView.addObject("borrow", borrow);
		return  modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 借款审批
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/submitAudit")
	@ResponseBody
	public ModelAndView submitAudit(BizBorrowInfoVO borrow,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			BizBorrowApproveServiceImpl service =  (BizBorrowApproveServiceImpl) getService();
			borrow.setApproveUser(getUserId());
			service.executeApprove(borrow);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("借款审批失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
}


