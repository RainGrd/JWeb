/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试controller
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月10日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.homepage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.model.ptp.base.BeCurrentModel;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.content.IContAdvContentService;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.core.service.homepage.impl.HomePageServiceImpl;
import com.yscf.core.service.report.impl.StandbyPlatReportServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br>
 * 测试controller
 * 
 * @author Lin Xu
 * @date 2015年11月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/index/homepController")
public class HomePageController extends EscfBaseWebController {
	
	@Autowired
	public HomePageController(HomePageServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BeCurrentModel.class;
	}
	//首页banner图片展示标签
	private static String INDEX_BANNER_TAG = "ep2p_adv_homePage_t_1";

	@Resource(name = "standbyPlatReportService")
	private StandbyPlatReportServiceImpl standbyPlatReportServiceImpl;

	// 投资详情
	@Resource(name = "bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl bizBorrowDetailServiceImpl;
	
	//栏目服务
	@Resource(name = "columnContentService")
	private ColumnContentServiceImpl columnContentService;

	// 借款
	@Resource(name = "bizBorrowService")
	private IBizBorrowService bizBorrowService;

	// 广告服务
	@Resource(name = "contAdvContentServiceImpl")
	private IContAdvContentService contAdvContentService;
	 

	/**
	 * Description：<br>
	 * index.page
	 * 
	 * @author Lin Xu
	 * @date 2015年11月10日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toIndex")
	public ModelAndView toIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index.page");
		HomePageServiceImpl hpsi = (HomePageServiceImpl) getService();
		//----------------------------
		List<ContAdvContent> advContent = contAdvContentService.selectAdvContentByWebTag(INDEX_BANNER_TAG);
		mv.addObject("advContent", advContent);
		// //---------------------投资统计总预览-----------------------
		// 最高年利率
		BigDecimal maxnumAPR = hpsi.getMaximumAPR();
		// 累计投资&累计收益
		HashMap<String, Object> qresult = standbyPlatReportServiceImpl
				.selectAllTotalAmount(null);
		mv.addObject("maxnumAPR", maxnumAPR);
		mv.addObject("cumulativei", qresult.get("cumulativei"));
		mv.addObject("userbenefit", qresult.get("userbenefit"));
		// ---------------------活动专区---------------------------
		// 动态获取最新发布的体验标
		BizBorrow experienceBorrow = bizBorrowService
				.getLastBorrowDByBorrowType(Constant.BORROW_TYPE_5);
		mv.addObject("experienceBorrow", experienceBorrow);

		// 新手标
		BizBorrow newStandardBorrow = bizBorrowService
				.getLastBorrowByBorrowType(Constant.BORROW_TYPE_4);
		mv.addObject("newStandardBorrow", newStandardBorrow);
		// ---------------------E计划---------------------------
		ColumnContent transferColumnContent = columnContentService.selectContentByWebTag("ep2p_col_homePage_m_3");
		mv.addObject("transferColumnContent",transferColumnContent);
		// ------------------债权转让了解详情E-------------------
		
		return mv;
	}

	/**
	 * Description：<br>
	 * 
	 * @author heng.wang
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/selectInverstList")
	public ModelAndView selectInverstList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// ---------------------投资动态列表---------------------------
		List<BizBorrowDetail> inverstList = bizBorrowDetailServiceImpl
				.selectInverstList();
		mv.addObject("data", inverstList);
		MessageBuilder.processSuccess(mv, null, HttpMessage.SUCCESS_MSG,
				request);
		return mv;
	}

	/**
	 * Description：<br>
	 * 根据web_tag 找到广告位内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	@RequestMapping("index/selectAdvContentByWebTag")
	@ResponseBody
	public ModelAndView selectAdvContentByWebTag(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 广告位定义的前台标示
		String webTag = request.getParameter("webTag");
		try {
			// ---------------------广告内容---------------------------
			List<ContAdvContent> advContent = contAdvContentService
					.selectAdvContentByWebTag(webTag);
			mv.addObject("advContent", advContent);
			mv.addObject("retCode","200");
			MessageBuilder.processSuccess(mv, null, HttpMessage.SUCCESS_MSG,
					request);
		} catch (Exception e) {
			e.printStackTrace();
			MessageBuilder.processError(mv, e, request);
		}
		return mv;
	}
	/**
	 * Description：<br>
	 * 根据web_tag 找到栏目位内容
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月26日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	@RequestMapping("index/selectColContentByWebTag")
	@ResponseBody
	public ModelAndView selectColContentByWebTag(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 栏目位定义的前台标示
		String webTag = request.getParameter("webTag");
		try {
			// ---------------------栏目内容---------------------------
			ColumnContent advContent = columnContentService
					.selectContentByWebTag(webTag);
			mv.addObject("colContent", advContent);
			mv.addObject("retCode","200");
			MessageBuilder.processSuccess(mv, null, HttpMessage.SUCCESS_MSG,
					request);
		} catch (Exception e) {
			e.printStackTrace();
			MessageBuilder.processError(mv, e, request);
		}
		return mv;
	}
}
