/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典页面控制数据交互类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月18日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.PromptMsgConstants;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowCondRel;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.autobidding.impl.AutoBiddingProcessService;
import com.yscf.core.service.business.IBizBorrowApproveService;
import com.yscf.core.service.business.IBizBorrowCondRelService;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.business.IBizBorrowFileRelService;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.util.MiscUtil;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * Description：<br>
 * 借款管理 controll
 * 
 * @author Yu.Zhang
 * @date 2015年9月18日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizBorrowController")
public class BizBorrowController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizBorrowController.class);

	@Resource(name = "bizBorrowFileRelService")
	private IBizBorrowFileRelService bizBorrowFileRelService;

	@Resource(name = "bizBorrowInfoVOService")
	private IBizBorrowInfoVOService bizBorrowInfoVOService;
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl borrowServiceImpl;

	@Resource(name = "sysDictionaryContentService")
	private SysDictionaryContentServiceImpl sysDictionaryContentServiceImpl;

	@Resource(name = "bizBorrowApproveService")
	private IBizBorrowApproveService approveService;

	@Resource(name = "bizFinanceProductsServiceImpl")
	private BizFinanceProductsServiceImpl productsServiceImpl;

	@Resource(name = "bizBorrowCondRelServiceImpl")
	private IBizBorrowCondRelService bizBorrowCondRelServiceImpl;

	@Resource(name = "sysParamsService")
	private ISysParamsService sysParamsService;

	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	// 自动投标
	@Resource(name = "abiddingProcessService")
	private AutoBiddingProcessService autoBiddingProcessService;
	
	@Resource
	private IBizRepaymentPlanService bizRepaymentPlanService;
	
	@Resource
	private IBizBorrowDetailService bizBorrowDetailService;
	
	@Resource
	private ICustomerAccountService customerAccountService;

	@Autowired
	public BizBorrowController(BizBorrowServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款客户列表页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowCusList")
	@ResponseBody
	public ModelAndView toBorrowCusList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("business/borrow/borrow_cus_list");
	}

	/**
	 * 
	 * Description：<br>
	 * 新增借款
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("business/borrow/borrow_add");
		try {
			// 查询当前客户下是否存在借款申请时间为空的数据,不存在则新增一条数据
			borrow.setBorrowStatus(Constant.ACTIVATE);
			BizBorrowServiceImpl service = (BizBorrowServiceImpl) getService();
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.getBorrowApplyTimeIsNull(borrow);
			if (list == null || list.size() == 0) {
				borrow.setCreateTime(DateUtil.format(DateUtil.getToday()));
				borrow.setCreateUser(getUserId());
				service.insert(borrow);
			} else {
				borrow = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("新增or编辑借款异常", e.getMessage());
			}
		}
		model.addObject("borrow", borrow);
		return model;
	}

	/**
	 * 
	 * Description：<br>
	 * 借款前审核查看资料
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowReviewView")
	@ResponseBody
	public ModelAndView toBorrowReviewView(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView(
				"business/borrow/borrow_review_view");
		try {
			request.setAttribute("view", request.getParameter("view"));
			// 查询借款信息
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService.selectAll(borrow);
			borrow = list.get(0);
			DecimalFormat myformat = new DecimalFormat("##,###.00");
//			if (null != borrow.getApplyTime()
//					&& !"".equals(borrow.getApplyTime())) {
//				borrow.setApplyTime(borrow.getApplyTime().substring(0,
//						(borrow.getApplyTime().length() - 2)));
//			}
			borrow.setBorrowMoneyStr(myformat.format(borrow.getBorrowMoney()));
			if (null != borrow.getHomeTotal()) {
				borrow.setHomeTotalStr(myformat.format(borrow.getHomeTotal()));
			}
			borrow.setBorrowMoneyStr(myformat.format(borrow.getBorrowMoney()));
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.info("借款前审核查看资料异常", e.getMessage());
			}
		}
		model.addObject("borrow", borrow);
		return model;
	}

	/**
	 * 
	 * Description：<br>
	 * 担保审核查看资料
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/view")
	@ResponseBody
	public ModelAndView view(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("business/borrow/borrow_add");
		try {
			request.setAttribute("view", request.getParameter("view"));
			request.setAttribute("isEdit", request.getParameter("isEdit"));
			// 查询借款信息
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService.selectAll(borrow);
			borrow = list.get(0);
			DecimalFormat myformat = new DecimalFormat("##,###.00");
//			if (null != borrow.getApplyTime()
//					&& !"".equals(borrow.getApplyTime())) {
//				borrow.setApplyTime(borrow.getApplyTime().substring(0,
//						(borrow.getApplyTime().length() - 2)));
//			}
			borrow.setBorrowMoneyStr(myformat.format(borrow.getBorrowMoney()));
			if (null != borrow.getHomeTotal()) {
				borrow.setHomeTotalStr(myformat.format(borrow.getHomeTotal()));
			}
			borrow.setBorrowMoneyStr(myformat.format(borrow.getBorrowMoney()));
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.info("担保审核查看资料异常", e.getMessage());
			}
		}
		model.addObject("borrow", borrow);
		return model;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存借款
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ModelAndView save(BizBorrow borrow, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizBorrowServiceImpl service = (BizBorrowServiceImpl) getService();
			// 1 设置最后更新人,最后更新时间
			borrow.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
			borrow.setLastUpdateUser(getUserId());
			// 2 数据保存
			service.updateByPrimaryKeySelective(borrow);
			modelView.addObject("borrow", borrow);
			// 3 结果集返回
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("编辑保存借款信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据ID查询借款信息
	 * 
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(String pid, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizBorrow borrow = (BizBorrow) getService().selectByPrimaryKey(pid);

/*			// 债权转让允许的利率 开始值
			if (!StringUtils.isEmpty(borrow.getStartValue())) {
				borrow.setStartValue(borrow.getStartValue().multiply(
						BigDecimal.valueOf(100)));
			}

			// 债权转让允许的利率 结束值
			if (!StringUtils.isEmpty(borrow.getEndValue())) {
				borrow.setEndValue(borrow.getEndValue().multiply(
						BigDecimal.valueOf(100)));
			}*/

			// 投资奖励比例
			if (!StringUtils.isEmpty(borrow.getInvestRewardScale())) {
				borrow.setInvestRewardScale(borrow.getInvestRewardScale()
						.multiply(BigDecimal.valueOf(100)));
			}

			// 管理费率
			if (!StringUtils.isEmpty(borrow.getManageExpenseRate())) {
				borrow.setManageExpenseRate(borrow.getManageExpenseRate()
						.multiply(BigDecimal.valueOf(100)));
			}

			// 年化率
			if (!StringUtils.isEmpty(borrow.getBorrowRate())) {
				borrow.setBorrowRate(borrow.getBorrowRate().multiply(
						BigDecimal.valueOf(100)));
			}

			modelView.addObject("result", borrow);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取借款信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据id查询借款信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBorrowInfoVOById")
	@ResponseBody
	public ModelAndView getBorrowInfoVOById(String pid,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizBorrowInfoVO borrow = bizBorrowInfoVOService
					.getBizBorrowById(pid);
			// 根据理财产品查询 理财产品介绍表
			BizFinanceProducts bizFinanceProducts = productsServiceImpl
					.getBizFinanceProductsByBorrowId(pid);
			modelView.addObject("result", borrow);
			modelView.addObject("products", bizFinanceProducts);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取借款信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据类型加载文件信息
	 * 
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBorrowFileRelByType")
	@ResponseBody
	public ModelAndView getBorrowFileRelByType(BizBorrowFileRel borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<BizBorrowFileRel> list = bizBorrowFileRelService.selectAll(borrow);
			modelView.addObject("result", list);
			MessageBuilder.processSuccess(modelView, null,HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据类型加载文件信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 删除文件信息
	 * 
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteFile")
	@ResponseBody
	public ModelAndView deleteFile(BizBorrowFileRel borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			borrow.setStatus(Constant.DELETE);
			borrow.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
			borrow.setLastUpdateUser(getUserId());
			bizBorrowFileRelService.updateByPrimaryKeySelective(borrow);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据类型加载文件信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 上传文件
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public void uploadFile(BizBorrowFileRel fileRel,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 文件上传
			HashMap<String, Object> remap = saveUploadFileObject(request,
					Constant.BUSINESS);

			if ((Boolean) remap.get(PromptMsgConstants.COMMON_FLG)) {
				PubFile pubFile = ((List<PubFile>) remap.get("resultList"))
						.get(0);
				fileRel.setCreateTime(DateUtil.format(DateUtil.getToday()));
				fileRel.setCreateUser(getUserId());
				fileRel.setStatus(Constant.ACTIVATE);
				fileRel.setFileId(pubFile.getPid());
				BizBorrowServiceImpl service = (BizBorrowServiceImpl) getService();
				// 保存文件信息
				service.saveFileInfo(fileRel, pubFile);
				tojson.put("message", HttpMessage.SUCCESS_CODE);
			} else {
				tojson.put("message", remap.get("message"));
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("上传资料列表出错" + e.getMessage());
			}
			tojson.put("message", "上传资料列表异常,请联系管理员!");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款项目发布列表
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toList")
	public ModelAndView toList() {
		return new ModelAndView("/business/bizBorrow/bizBorrowList");
	}

	/**
	 * 
	 * Description：<br>
	 * 借款项目发布列表
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/bizBorrowList")
	@ResponseBody
	public ModelAndView bizBorrowList(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			bizBorrow.setIsApproveList(request.getParameter("isApproveList"));
			PageList<BizBorrowInfoVO> pageList = bizBorrowInfoVOService.selectAllPage(bizBorrow, info);
			BigDecimal sumRepaymentAmt = bizBorrowInfoVOService.getSumborrowMoneyByWhere(bizBorrow);
			BizBorrowInfoVO loanSum = new BizBorrowInfoVO();
			loanSum.setIsTotal("yes");
			loanSum.setBorrowMoney(sumRepaymentAmt);
			pageList.add(loanSum);
			MiscUtil.formatBorrows(pageList);
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator()
					.getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.info("借款项目发布列表查询异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到已发布借款项目管理列表
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月29日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toReleaseBorrowManageList")
	public ModelAndView toReleaseBorrowManageList() {
		return new ModelAndView(
				"/business/releaseBorrow/releaseBorrowManageList");
	}

	/**
	 * 
	 * Description：<br>
	 * 已发布借款项目管理列表
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月29日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/releaseBorrowManageList")
	@ResponseBody
	public ModelAndView releaseBorrowManageList(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			bizBorrow.setApproveStatus(request.getParameter("approveNode"));
			bizBorrow.setIsApproveList(request.getParameter("isApproveList"));
			PageList<BizBorrowInfoVO> pageList = bizBorrowInfoVOService
					.searchAllPage(bizBorrow, info);
			BigDecimal sumRepaymentAmt = bizBorrowInfoVOService
					.getSumborrowMoneyByWhere(bizBorrow);
			BizBorrowInfoVO loanSum = new BizBorrowInfoVO();
			loanSum.setIsTotal("yes");
			loanSum.setBorrowMoney(sumRepaymentAmt);
			pageList.add(loanSum);
			pageList = MiscUtil.formatBorrows(pageList);
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator()
					.getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.info("借款项目发布列表查询异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款项目发布
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toRelease")
	@ResponseBody
	public ModelAndView toRelease(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/bizBorrow/bizBorrowRelease");
		try {
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0) {
				BizBorrowInfoVO info = list.get(list.size() - 1);
				DecimalFormat myformat = new DecimalFormat("##,##0.00");
				if (null != info.getBorrowMoney())
					info.setBorrowMoneyStr(myformat.format(info
							.getBorrowMoney()));// 借款金额格式化
				if (null != info.getStartMoney())
					info.setStartMoneyStr(myformat.format(info.getStartMoney()));// 起投金额格式化
				if (null != info.getEndMoney())
					info.setEndMoneyStr(myformat.format(info.getEndMoney()));// 投标上限格式化
				info = MiscUtil.formatBorrow(info);
				modelView.addObject("bizBorrow", info);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到借款项目发布页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 借款项目发布
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/release")
	@ResponseBody
	public ModelAndView release(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			borrow.setApproveUser(getUserId());
			BizBorrow bizBorrow = service.getBizBorrowById(borrow.getPid());
			//bizBorrow.setPublishTime(borrow.getPublishTime());
			bizBorrow.setDeadline(borrow.getDeadline());
			bizBorrow.setIsSplit(borrow.getIsSplit());
			bizBorrow.setSplitBorCount(borrow.getSplitBorCount());
			bizBorrow.setBorrowPassword(borrow.getBorrowPassword());
			List<BizBorrowFileRel> borrowFiles = bizBorrowFileRelService.selectByBorrowId(borrow.getPid());//得到标的上传文件记录集合，为分标做准备
			if (checkSplit(bizBorrow.getIsSplit(), bizBorrow.getSplitBorCount())) {// 判断是否分标
				for (int i = 0; i < Integer.parseInt(bizBorrow
						.getSplitBorCount()); i++) {// 根据分标个数生成多个标
					BizBorrow newBizBorrow = (BizBorrow) bizBorrow.clone();
					newBizBorrow.setPid(newBizBorrow.getPK());
					newBizBorrow.setParentId(borrow.getPid());
					newBizBorrow.setBorOrder(i + 1 + "");
					newBizBorrow
							.setBorrowMoney(bizBorrow.getBorrowMoney()
									.divide(new BigDecimal(bizBorrow
											.getSplitBorCount())));// 金额平均分配
					newBizBorrow.setSurplusMoney(bizBorrow.getSurplusMoney().divide(new BigDecimal(bizBorrow.getSplitBorCount())));//未投金额平均分配
					newBizBorrow.setBorrowCode(bizBorrow.getBorrowCode() + "_"
							+ (i + 1));// 设置分标编号
					newBizBorrow.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
					//设置分标父标的上传资料信息
					for (BizBorrowFileRel borrowFile : borrowFiles) {
						borrowFile.setPid(borrowFile.getPK());
						borrowFile.setBorrowId(newBizBorrow.getPid());
						borrowFile.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
						bizBorrowFileRelService.insertSelective(borrowFile);
					}
					if (i == 0) {
						newBizBorrow.setPublishTime(borrow.getPublishTime()); 
						if (publishTimeComparison(newBizBorrow.getPublishTime())) {
							newBizBorrow.setBorStatus(Constant.BORROW_STATUS_1);
						} else {
							newBizBorrow.setBorStatus(Constant.BORROW_STATUS_2);
						}
					} else {
						newBizBorrow.setBorStatus(Constant.BORROW_STATUS_1);
					}
					getService().insert(newBizBorrow);
					//如果借款发布为招标中需要进行一些其他的额外操作（自动投标）,需要先生成借款然后进行操作
					if(Constant.BORROW_STATUS_2.equals(newBizBorrow.getBorStatus())){
						//自动投标
						autoBiddingProcessService
								.updateProcessAutoBidding(newBizBorrow
										.getPid());
					}
					//借款发布产生还款计划
					bizRepaymentPlanService.createForecastRepaymentPlan(newBizBorrow);
				}
				bizBorrow.setStatus(Constant.DELETE);
			} else {
				bizBorrow.setPublishTime(borrow.getPublishTime());
				if (publishTimeComparison(bizBorrow.getPublishTime())) {
					bizBorrow.setBorStatus(Constant.BORROW_STATUS_1);
				} else {
					bizBorrow.setBorStatus(Constant.BORROW_STATUS_2);
				}
				//借款发布产生还款计划
				bizRepaymentPlanService.createForecastRepaymentPlan(bizBorrow);
			}
			service.updateByPrimaryKeySelective(bizBorrow);
			//如果借款发布为招标中需要进行一些其他的额外操作（自动投标）,需要先生成借款然后进行操作
			if(Constant.BORROW_STATUS_2.equals(bizBorrow.getBorStatus())){
				//自动投标
				autoBiddingProcessService
					.updateProcessAutoBidding(bizBorrow.getPid());
			}
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("借款修改为发布失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 借款发布时间是否为立即发布
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param publishTime
	 * @return
	 */
	private boolean publishTimeComparison(String publishTime) {
		Date pubTime = DateUtil.format(publishTime);
		Date currentTime = DateUtil.getSystemDate();
		return pubTime.getTime() > currentTime.getTime();
	}

	/**
	 * 
	 * Description：<br>
	 * 借款项目发布确认
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toReleaseConfirm")
	@ResponseBody
	public ModelAndView toReleaseConfirm(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/bizBorrow/bizBorrowReleaseConfirm");
		try {
			JSONObject json = JSON.parseObject(request.getParameter("data"));
			bizBorrow.setPid(json.getString("pid"));
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0) {
				bizBorrow = list.get(list.size() - 1);
				DecimalFormat myformat = new DecimalFormat("##,##0.00");
				bizBorrow = MiscUtil.formatBorrow(bizBorrow);
				if (null != bizBorrow.getBorrowMoney())
					bizBorrow.setBorrowMoneyStr(myformat.format(bizBorrow
							.getBorrowMoney()));// 借款金额格式化
				if (null != bizBorrow.getStartMoney())
					bizBorrow.setStartMoneyStr(myformat.format(bizBorrow
							.getStartMoney()));// 起投金额格式化
				if (null != bizBorrow.getEndMoney())
					bizBorrow.setEndMoneyStr(myformat.format(bizBorrow
							.getEndMoney()));// 投标上限格式化
				bizBorrow.setBorrowRate(MiscUtil.getBigDecimalMoney(bizBorrow
						.getBorrowRate()));// 修改年化利率格式
				bizBorrow.setInvestRewardScale(MiscUtil
						.getBigDecimalMoney(bizBorrow.getInvestRewardScale()));// 修改奖励利率格式
				bizBorrow.setPublishTime(json.getString("publishTime"));// 发布时间
				bizBorrow.setDeadline(json.getString("deadline"));// 招标时间
				bizBorrow.setIsSplit(json.getString("isSplit"));// 是否分标
				bizBorrow.setSplitBorCount(json.getString("splitNum"));
				if (checkSplit(bizBorrow.getIsSplit(),
						bizBorrow.getSplitBorCount()))
					bizBorrow.setBorrowMoney(bizBorrow.getBorrowMoney().divide(
							new BigDecimal(json.getString("splitNum")),10,BigDecimal.ROUND_HALF_UP));
				bizBorrow.setBorrowPassword(json.get("borrowPassword")
						.toString());// 投标密码
				modelView.addObject("bizBorrow", bizBorrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到借款项目发布确认页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查看发布确认信息
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/releaseConfirmView")
	@ResponseBody
	public ModelAndView releaseConfirmView(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/bizBorrow/bizBorrowReleaseConfirm");
		try {
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0) {
				bizBorrow = list.get(list.size() - 1);
				DecimalFormat myformat = new DecimalFormat("##,##0.00");
				if (null != bizBorrow.getBorrowMoney())
					bizBorrow.setBorrowMoneyStr(myformat.format(bizBorrow
							.getBorrowMoney()));// 借款金额格式化
				if (null != bizBorrow.getStartMoney())
					bizBorrow.setStartMoneyStr(myformat.format(bizBorrow
							.getStartMoney()));// 起投金额格式化
				if (null != bizBorrow.getEndMoney())
					bizBorrow.setEndMoneyStr(myformat.format(bizBorrow
							.getEndMoney()));// 投标上限格式化
				bizBorrow.setBorrowRate(MiscUtil.getBigDecimalMoney(bizBorrow
						.getBorrowRate()));// 修改年化利率格式
				bizBorrow.setInvestRewardScale(MiscUtil
						.getBigDecimalMoney(bizBorrow.getInvestRewardScale()));// 修改奖励利率格式
				modelView.addObject("bizBorrow", bizBorrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 判断是否分标
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param isSplit
	 * @param splitBorCount
	 * @return
	 */
	private boolean checkSplit(String isSplit, String splitBorCount) {
		if (null != isSplit && !isSplit.isEmpty() && isSplit.equals("1"))// 判断是否分标
			if (null != splitBorCount && !splitBorCount.isEmpty())// 判断分标个数是否为空
				return true;

		return false;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款发布项目管理列表
	 * 
	 * @author Jie.Zou
	 * @date 2015年9月30日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toManageRelease")
	@ResponseBody
	public ModelAndView toManageRelease(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/releaseBorrow/releaseBorrowManageRelease");
		try {
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0){
				BizBorrowInfoVO bizBorrowInfoVO = list.get(list.size() - 1);
				MiscUtil.formatBorrow(bizBorrowInfoVO);//借款格式化
				modelView.addObject("bizBorrow", bizBorrowInfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到借款项目管理发布页面异常", e.getMessage());
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到发布借款项目管理借款发布确认
	 * @author  Jie.Zou
	 * @date    2016年2月15日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toManageReleaseConfirm")
	@ResponseBody
	public ModelAndView toManageReleaseConfirm(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/releaseBorrow/releaseBorrowManageReleaseConfirm");
		try {
			JSONObject json = JSON.parseObject(request.getParameter("data"));
			bizBorrow.setPid(json.getString("pid"));
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0) {
				bizBorrow = list.get(list.size() - 1);
				DecimalFormat myformat = new DecimalFormat("##,##0.00");
				bizBorrow = MiscUtil.formatBorrow(bizBorrow);
				if (null != bizBorrow.getBorrowMoney())
					bizBorrow.setBorrowMoneyStr(myformat.format(bizBorrow
							.getBorrowMoney()));// 借款金额格式化
				if (null != bizBorrow.getStartMoney())
					bizBorrow.setStartMoneyStr(myformat.format(bizBorrow
							.getStartMoney()));// 起投金额格式化
				if (null != bizBorrow.getEndMoney())
					bizBorrow.setEndMoneyStr(myformat.format(bizBorrow
							.getEndMoney()));// 投标上限格式化
				bizBorrow.setBorrowRate(MiscUtil.getBigDecimalMoney(bizBorrow
						.getBorrowRate()));// 修改年化利率格式
				bizBorrow.setInvestRewardScale(MiscUtil
						.getBigDecimalMoney(bizBorrow.getInvestRewardScale()));// 修改奖励利率格式
				bizBorrow.setPublishTime(json.getString("publishTime"));// 发布时间
				bizBorrow.setDeadline(json.getString("deadline"));// 招标时间
				bizBorrow.setIsSplit(json.getString("isSplit"));// 是否分标
				bizBorrow.setSplitBorCount(json.getString("splitNum"));
				if (checkSplit(bizBorrow.getIsSplit(),
						bizBorrow.getSplitBorCount()))
					bizBorrow.setBorrowMoney(bizBorrow.getBorrowMoney().divide(
							new BigDecimal(json.getString("splitNum")),10,BigDecimal.ROUND_HALF_UP));
				bizBorrow.setBorrowPassword(json.get("borrowPassword")
						.toString());// 投标密码
				modelView.addObject("bizBorrow", bizBorrow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到借款项目发布确认页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 借款管理项目发布（待招标）
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月8日
	 * @version v1.0.0
	 * @param borrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/manageRelease")
	@ResponseBody
	public ModelAndView manageRelease(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			BizBorrow bizBorrow = service.getBizBorrowById(borrow.getPid());
			bizBorrow.setDeadline(borrow.getDeadline());// 修改招标时间
			bizBorrow.setPublishTime(borrow.getPublishTime());// 发布时间
			bizBorrow.setBorrowPassword(borrow.getBorrowPassword());// 设置投标密码
			if (DateUtil.format(bizBorrow.getPublishTime()).getTime() <= DateUtil
					.getToday().getTime()) {
				bizBorrow.setBorStatus(Constant.BORROW_STATUS_2);
			}
			service.updateByPrimaryKeySelective(bizBorrow);
			//如果借款发布为招标中需要进行一些其他的额外操作（自动投标，产生展示的还款计划）,需要先生成借款然后进行操作
			if(Constant.BORROW_STATUS_2.equals(bizBorrow.getBorStatus())){
				//自动投标
				autoBiddingProcessService
						.updateProcessAutoBidding(bizBorrow
								.getPid());
				//借款发布产生还款计划
				bizRepaymentPlanService.createForecastRepaymentPlan(bizBorrow);
			}
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("借款审批失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款项目撤销
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月8日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toRevoke")
	@ResponseBody
	public ModelAndView toRevoke(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/releaseBorrow/revokeBorrow");
		try {
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0){
				BizBorrowInfoVO bizBorrowInfoVO = list.get(list.size() - 1);
				MiscUtil.formatBorrow(bizBorrowInfoVO);
				modelView.addObject("bizBorrow", bizBorrowInfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到借款项目撤销页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 借款项目撤销
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月8日
	 * @version v1.0.0
	 * @param borrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/revoke")
	@ResponseBody
	public ModelAndView revoke(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			BizBorrow bizBorrow = service.getBizBorrowById(borrow.getPid());
			bizBorrow.setRestReason(borrow.getRestReason());//撤销原因
			bizBorrow.setBorStatus(Constant.BORROW_STATUS_0);//撤销状态
			bizBorrow.setBorFullTime(DateUtil.getSystemDate());//撤销时间
			// 撤销
			service.revokeBorrow(bizBorrow);
			//撤销拿取下一个分标，进入招标中
			borrowServiceImpl.handleBorrowBidding(bizBorrow);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("借款撤销失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款项目查询
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowSearch")
	@ResponseBody
	public ModelAndView toBorrowSearch(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/bizBorrow/bizBorrowSearchList");
		try {
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0)
				modelView.addObject("bizBorrow", list.get(list.size() - 1));
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到借款项目撤销页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 借款项目查询
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/searchBorrowList")
	@ResponseBody
	public ModelAndView searchBorrowList(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (StringUtil.isBlank(bizBorrow.getBorStatus())) {
				bizBorrow.setBorStatus(request.getParameter("BorStatus"));
			}
			PageList<BizBorrowInfoVO> pageList = bizBorrowInfoVOService
					.searchAllPage(bizBorrow, info);
			BigDecimal sumRepaymentAmt = bizBorrowInfoVOService
					.getSumborrowMoneyByWhere(bizBorrow);
			BizBorrowInfoVO loanSum = new BizBorrowInfoVO();
			loanSum.setIsTotal("yes");
			loanSum.setBorrowMoney(sumRepaymentAmt);
			pageList.add(loanSum);
			pageList = MiscUtil.formatBorrows(pageList);
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator()
					.getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.info("借款项目查询异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 通过借款ID得到借款项目
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBorrowById")
	@ResponseBody
	public ModelAndView getBorrowById(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		String url = "business/" + request.getParameter("toUrl");
		ModelAndView modelView = new ModelAndView(url);
		try {
			BizBorrowInfoVO borrow = bizBorrowInfoVOService
					.getBizBorrowById(bizBorrow.getPid());
			borrow.setBorrowRate(MiscUtil.getBigDecimalMoney(borrow
					.getBorrowRate()));
			MiscUtil.formatBorrow(borrow);
			modelView.addObject("borrow", borrow);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.info("借款项目查询异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到理财产品查询
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFinancialBorrowSearch")
	@ResponseBody
	public ModelAndView toFinancialBorrowSearch(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView(
				"/business/financialBorrow/financialBorrowSearchList");
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到理财产品撤销
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月15日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFinancialRevoke")
	@ResponseBody
	public ModelAndView toFinancialRevoke(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/financialBorrow/revokeFinancial");
		try {
			List<BizBorrowInfoVO> list = bizBorrowInfoVOService
					.selectAll(bizBorrow);
			if (null != list && list.size() > 0){
				BizBorrowInfoVO bizBorrowInfoVO = list.get(list.size() - 1);
				MiscUtil.formatBorrow(bizBorrowInfoVO);
				modelView.addObject("bizBorrow", bizBorrowInfoVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("跳转到理财产品撤销页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到新手体验标查询
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewStandard")
	@ResponseBody
	public ModelAndView toNewStandard(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {

		return new ModelAndView("/business/newStandard/newStrandardList");
	}

	/**
	 * 
	 * Description：<br>
	 * 新手体验标查询
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/newStandard")
	@ResponseBody
	public ModelAndView newStandard(BizBorrowInfoVO bizBorrowVo,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (!StringUtils.hasLength(bizBorrowVo.getNewStandardType())) {
				String newStandardType = request.getParameter("type");
				bizBorrowVo.setNewStandardType(newStandardType);
			}
			bizBorrowVo.setNewStandardTypeArray(bizBorrowVo
					.getNewStandardType().split(","));
			bizBorrowVo.setBorStatus(Constant.BORROW_STATUS_1);
			PageList<BizBorrowInfoVO> pageList = bizBorrowInfoVOService
					.searchAllPage(bizBorrowVo, info);
			BigDecimal sumBorrowMoney = bizBorrowInfoVOService
					.getSumborrowMoneyByWhere(bizBorrowVo);
			BizBorrowInfoVO sumBorrow = new BizBorrowInfoVO();
			sumBorrow.setBorrowMoney(sumBorrowMoney);
			pageList.add(sumBorrow);
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator()
					.getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.error("新手标体验标查询异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;

	}

	/**
	 * 
	 * Description：<br>
	 * 跳转新手体验标新增/编辑
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/newStandardAdd")
	@ResponseBody
	public ModelAndView newStandardAdd(BizBorrowInfoVO bizBorrowVo,
			String view, String paramsKey, String type, String customerId,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView(
				"/business/newStandard/newStrandardAdd");
		try {

			modelAndView.addObject("pid", bizBorrowVo.getPid());
			if (!"-1".equals(bizBorrowVo.getPid())) {
				// 编辑
				BizBorrowInfoVO borrow = bizBorrowInfoVOService
						.getBizBorrowById(bizBorrowVo.getPid());
				BizFinanceProducts bfp = productsServiceImpl
						.getBizFinanceProductsByBorrowId(borrow.getPid());
				modelAndView.addObject("borrow", borrow);
				modelAndView.addObject("financeProducts", bfp);

				modelAndView.addObject("view", view);
			} else {
				// 新增
				if ("2".equals(type)) {
					// 体验标
					// 获取系统借款人
					List<SysParams> sysList = sysParamsService
							.searchParamsByKey(paramsKey);
					if (sysList != null) {
						if (sysList.size() > 1) {
							logger.info("系统借款人出现多个");
							throw new RuntimeException("系统借款人出现多个");
						} else {
							// 根据系统借款人值，获取到系统用户
							SysParams sys = sysList.get(0);
							if (StringUtils.hasLength(sys.getParamValue())) {
								CusTomer cusTomer = cusTomerService
										.selectCusByCustomerName(sys
												.getParamValue());
								if (cusTomer != null) {

									BizBorrowInfoVO brrow = new BizBorrowInfoVO();
									if (StringUtils.hasLength(cusTomer
											.getCustomerName())) {
										brrow.setCustomerName(cusTomer
												.getCustomerName());
									} else {
										brrow.setCustomerName(cusTomer
												.getPhoneNo());
									}
									brrow.setCustomerId(cusTomer.getPid());
									brrow.setCustomerStatus(cusTomer
											.getCusStatus());
									brrow.setSname(cusTomer.getSname());
									modelAndView.addObject("borrow", brrow);
								} else {
									logger.info("系统借款人：【" + sys.getParamValue()
											+ "】不存在");
									throw new RuntimeException("系统借款人：【"
											+ sys.getParamValue() + "】不存在");
								}

							} else {
								logger.info("系统借款人系统值为空");
								throw new RuntimeException("系统借款人系统值为空");
							}

						}
					} else {
						logger.info("系统借款人不存在");
						throw new RuntimeException("系统借款人不存在");
					}
				} else {
					// 新手标
					CusTomer ct = cusTomerService
							.selectByPrimaryKey(customerId);
					if (ct != null) {
						BizBorrowInfoVO brrow = new BizBorrowInfoVO();
						if (StringUtils.hasLength(ct.getCustomerName())) {
							brrow.setCustomerName(ct.getCustomerName());
						} else {
							brrow.setCustomerName(ct.getPhoneNo());
						}
						brrow.setCustomerId(ct.getPid());
						brrow.setCustomerStatus(ct.getCusStatus());
						brrow.setSname(ct.getSname());
						modelAndView.addObject("borrow", brrow);
					} else {
						throw new RuntimeException("借款人不存在");
					}
				}

				modelAndView.addObject("type", type);

			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.error("跳转新手体验标新增/编辑", e.getMessage());
			}
			MessageBuilder.processError(modelAndView, e, request);
		}

		return modelAndView;

	}

	/**
	 * 
	 * 
	 * Description：<br>
	 * 删除新手标体验标
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pids
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request,
			HttpServletResponse response, String pid)
			throws HttpRequestException {
		IBizBorrowService service = (BizBorrowServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteByPrimaryKey(pid);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("删除新手标体验标失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到新手体验标查询
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewStandardRelease")
	@ResponseBody
	public ModelAndView toNewStandardRelease(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("/business/newStandard/newStrandardReleaseList");
	}

	/**
	 * 
	 * Description：<br>
	 * 新手体验标发布查询
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/newStandardRelease")
	@ResponseBody
	public ModelAndView newStandardRelease(BizBorrowInfoVO bizBorrowVo,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (!StringUtils.hasLength(bizBorrowVo.getNewStandardType())) {
				String newStandardType = request.getParameter("type");
				bizBorrowVo.setNewStandardType(newStandardType);
			}
			bizBorrowVo.setNewStandardTypeArray(bizBorrowVo
					.getNewStandardType().split(","));
			bizBorrowVo.setBorStatus(Constant.BORROW_STATUS_2);
			PageList<BizBorrowInfoVO> pageList = bizBorrowInfoVOService
					.searchAllPage(bizBorrowVo, info);
			BigDecimal sumBorrowMoney = bizBorrowInfoVOService
					.getSumborrowMoneyByWhere(bizBorrowVo);
			BizBorrowInfoVO sumBorrow = new BizBorrowInfoVO();
			sumBorrow.setBorrowMoney(sumBorrowMoney);
			pageList.add(sumBorrow);
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator()
					.getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
				logger.error("新手标体验标发布查询异常", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;

	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到新手标体验标发布
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewRelease")
	@ResponseBody
	public ModelAndView toNewRelease(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/newStandard/newStrandardRelease");
		try {
			BizBorrowInfoVO BizBorrow = bizBorrowInfoVOService
					.getBizBorrowById(bizBorrow.getPid());
			modelView.addObject("bizBorrow", BizBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到新手标体验标发布页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 新手标体验标发布确认
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewReleaseConfirm")
	@ResponseBody
	public ModelAndView toNewReleaseConfirm(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"/business/newStandard/newStrandardReleaseConfirm");
		try {
			JSONObject json = JSON.parseObject(request.getParameter("data"));
			bizBorrow = bizBorrowInfoVOService.getBizBorrowById(json
					.getString("pid"));
			bizBorrow.setDeadline(json.getString("deadline"));
			bizBorrow.setPublishTime(json.getString("publishTime"));
			modelView.addObject("bizBorrow", bizBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到借款项目发布确认页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 新手标体验标发布确认成功，存储数据
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewReleaseConfirmIsOk")
	@ResponseBody
	public ModelAndView toNewReleaseConfirmIsOk(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizBorrowService service = (BizBorrowServiceImpl) getService();
			borrow.setLastUpdateUser(getUserId());
			BizBorrow bizBorrow = service.getBizBorrowById(borrow.getPid());
			bizBorrow.setPublishTime(borrow.getPublishTime());
			bizBorrow.setDeadline(borrow.getDeadline());
			bizBorrow.setLastUpdateTime(DateUtil.format(new Date()));
			if (publishTimeComparison(bizBorrow.getPublishTime())) {
				bizBorrow.setBorStatus(Constant.BORROW_STATUS_1);
			} else {
				bizBorrow.setBorStatus(Constant.BORROW_STATUS_2);
			}
			service.updateByPrimaryKeySelectiveAndCreateRepaymentPlan(bizBorrow);

			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("新手标体验标发布确认失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到新手标体验标撤销
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewStandardRevoke")
	@ResponseBody
	public ModelAndView toNewStandardRevoke(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"business/newStandard/newStrandardRevoke");
		try {
			BizBorrowInfoVO borrow = bizBorrowInfoVOService
					.getBizBorrowById(bizBorrow.getPid());
			modelView.addObject("bizBorrow", borrow);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("跳转到借款项目撤销页面异常", e.getMessage());
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 新手标体验标撤销确认成功，更新数据
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toNewStandardRevokeConfirmIsOk")
	@ResponseBody
	public ModelAndView toNewStandardRevokeConfirmIsOk(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			
			   IBizBorrowService service = (BizBorrowServiceImpl) getService();
				// 新手标
				borrow.setLastUpdateUser(getUserId());
				BizBorrow bizBorrow = service.getBizBorrowById(borrow.getPid());
				bizBorrow.setLastUpdateTime(DateUtil.format(new Date()));
				bizBorrow.setRestReason(borrow.getRestReason());
				bizBorrow.setBorStatus(Constant.BORROW_STATUS_0);
				bizBorrow.setBorFullTime(new Date());
				// 撤销
				service.revokeBorrow(bizBorrow);
				
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("新手标体验标撤销失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到理财产品客户查询页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFinancialBorrowCusList")
	@ResponseBody
	public ModelAndView toFinancialBorrowCusList(String type,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView(
				"business/financialBorrow/financialBorrow_cus_list");
		modelView.addObject("type", type);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到 理财产品管理 展示页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFinancialBorrowManageList")
	@ResponseBody
	public ModelAndView toFinancialBorrowManageList(BizBorrowInfoVO bizBorrow,
			HttpServletRequest request, HttpServletResponse response,
			String customerId) throws HttpRequestException {
		request.setAttribute("customerId", customerId);
		return new ModelAndView(
				"/business/financialBorrow/financialBorrowManageList");
	}

	/**
	 * 
	 * Description：<br>
	 * 根据借款id 查询理财产品
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/getProductByBorrowId")
	@ResponseBody
	public void getProductByBorrowId(HttpServletRequest request,
			HttpServletResponse response, String pid)
			throws HttpRequestException, FrameworkException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		BizFinanceProducts bizFinanceProducts = (BizFinanceProducts) productsServiceImpl
				.selectByPrimaryKey(pid);
		tojson.put("product", bizFinanceProducts);
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到 理财产品管理 AddOrUpdate页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/openbizBorrowManageAddPage")
	@ResponseBody
	public ModelAndView openbizBorrowManageAddPage(String pid,
			String customerId, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException,
			FrameworkException {
		if (StringUtil.isBlank(pid)) {
			pid = request.getParameter(pid);
		}
		if (StringUtil.isBlank(customerId)) {
			customerId = request.getParameter(customerId);
		}
		ModelAndView modelAndView = new ModelAndView(
				"/business/financialBorrow/financialBorrowManageAdd");
		if (!StringUtil.isBlank(pid)) {
			modelAndView.addObject("pid", pid);
		} else {
			modelAndView.addObject("pid", "1");
		}
		CusTomer cusTomer = (CusTomer) cusTomerService
				.selectByPrimaryKey(customerId);
		modelAndView.addObject("cusTomer", cusTomer);
		modelAndView.addObject("customerId", customerId);

		// 根据理财产品查询 理财产品介绍表
		BizFinanceProducts bizFinanceProducts = productsServiceImpl
				.getBizFinanceProductsByBorrowId(pid);
		// 第一步 查询 理财产品 填写产品介绍
		BizBorrowInfoVO borrow = bizBorrowInfoVOService.getBizBorrowById(pid);
		modelAndView.addObject("bizFinanceProducts", bizFinanceProducts);
		modelAndView.addObject("borrow", borrow);

		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到 理财产品管理 AddOrUpdate页面(查看)
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月16日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/openbizBorrowManageAddView")
	@ResponseBody
	public ModelAndView openbizBorrowManageAddView(String pid,
			String customerId,String view, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException,
			FrameworkException {
		if (StringUtil.isBlank(pid)) {
			pid = request.getParameter(pid);
		}
		if (StringUtil.isBlank(customerId)) {
			customerId = request.getParameter(customerId);
		}
		ModelAndView modelAndView = new ModelAndView(
				"/business/financialBorrow/financialBorrowManageAdd");
		if (!StringUtil.isBlank(pid)) {
			modelAndView.addObject("pid", pid);
		} else {
			modelAndView.addObject("pid", "1");
		}
		modelAndView.addObject("view",view);
		CusTomer cusTomer = (CusTomer) cusTomerService
				.selectByPrimaryKey(customerId);
		modelAndView.addObject("cusTomer", cusTomer);
		modelAndView.addObject("customerId", customerId);

		// 根据理财产品查询 理财产品介绍表
		BizFinanceProducts bizFinanceProducts = productsServiceImpl
				.getBizFinanceProductsByBorrowId(pid);
		// 第一步 查询 理财产品 填写产品介绍
		BizBorrowInfoVO borrow = bizBorrowInfoVOService.getBizBorrowById(pid);
		modelAndView.addObject("bizFinanceProducts", bizFinanceProducts);
		modelAndView.addObject("borrow", borrow);

		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存借款信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param BizBorrowInfoVO
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveBorrow")
	@ResponseBody
	public void saveBorrow(HttpServletRequest request,
			HttpServletResponse response, BizBorrowInfoVO infoVO,
			BizFinanceProducts products) throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			if (StringUtil.isBlank(infoVO.getPid())) {
				infoVO.setPid(request.getParameter("borrowPid"));
			}
			if (StringUtil.isBlank(products.getPid())) {
				products.setPid(request.getParameter("productsPid"));
			}
			//合同id
			if(!StringUtil.isBlank(products.getBorrowAgreementId())){
				infoVO.setBorAgrId(products.getBorrowAgreementId());
			}
			infoVO.setApplyTime(DateUtil.format(DateUtil.getToday()));
			// 1 设置最后更新人,最后更新时间
			infoVO.setCreateTime(DateUtil.format(DateUtil.getToday()));
			infoVO.setCreateUser(getContextUser().getUserName());
			// 待招标
			infoVO.setBorStatus("1");
			bizBorrowInfoVOService.savePrimaryKeySelective(infoVO);
			// 如果 理财产品介绍主键为空
			if (StringUtil.isBlank(products.getPid())) {
				products.setPid(products.getPK());
				products.setBorrowId(infoVO.getPid());
				// 1 设置最后更新人,最后更新时间
				products.setCreateTime(DateUtil.format(DateUtil.getToday()));
				products.setCreateUser(getContextUser().getUserName());
				// 2 数据保存
				productsServiceImpl.insert(products);
			} else {
				productsServiceImpl.updateByPrimaryKey(products);
			}
			tojson.put("message", HttpMessage.SUCCESS_CODE);
			tojson.put("customerId", infoVO.getCustomerId());
			tojson.put("infoVO", infoVO);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("编辑保存理财产品信息失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 保存 理财产品介绍
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param infoVO
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveProducts")
	@ResponseBody
	public void saveProducts(HttpServletRequest request,
			HttpServletResponse response, BizFinanceProducts products)
			throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		String customerId = request.getParameter("customerId");// 取得url中的客户id
		String pageTag = request.getParameter("pageTag");
		try {

			// 如果 理财产品介绍主键为空
			if (StringUtil.isBlank(products.getPid())) {
				products.setPid(products.getPK());
				// 1 设置最后更新人,最后更新时间
				products.setCreateTime(DateUtil.format(DateUtil.getToday()));
				products.setCreateUser(getContextUser().getUserName());
				// 2 数据保存
				productsServiceImpl.insert(products);
			} else {
				productsServiceImpl.updateByPrimaryKey(products);
			}
			tojson.put("customerId", customerId);
			tojson.put("pageTag", pageTag);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
			tojson.put("products", products);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("编辑保存  理财产品介绍  失败", e.getMessage());
				e.printStackTrace();
			}
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 根据主键 删除借款信息表记录
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param products
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/deleteBorrowManageByPid")
	@ResponseBody
	public void deleteBorrowManageByPid(HttpServletRequest request,
			HttpServletResponse response, BizFinanceProducts products)
			throws HttpRequestException, FrameworkException {
		String pid = request.getParameter("pid");
		Map<String, Object> tojson = new HashMap<String, Object>();
		if (!StringUtil.isBlank(pid)) {
			bizBorrowInfoVOService.deleteBorrowManageByPid(pid);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到理财产品发布页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/toPublishPage")
	@ResponseBody
	public ModelAndView toPublishPage(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException,
			FrameworkException {
		ModelAndView modelAndView = new ModelAndView(
				"/business/financialBorrow/financialBorrowManagePublish");
		String pid = request.getParameter("pid");
		BizBorrowInfoVO borrow = bizBorrowInfoVOService.getBizBorrowById(pid);
		modelAndView.addObject("borrow", borrow);
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到理财产品发布预览页面
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/financialBorrowManagePublishPreview")
	@ResponseBody
	public ModelAndView financialBorrowManagePublishPreview(
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException, FrameworkException {
		ModelAndView modelAndView = new ModelAndView(
				"/business/financialBorrow/financialBorrowManagePublishPreview");
		String pid = request.getParameter("pid");
		BizBorrowInfoVO borrowInfo = bizBorrowInfoVOService
				.getBizBorrowById(pid);
		modelAndView.addObject("borrow", borrowInfo);
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 发布理财产品
	 * 
	 * @author shiliang.feng
	 * @date 2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/publish")
	@ResponseBody
	public void publish(HttpServletRequest request,
			HttpServletResponse response, BizBorrow borrowInfo)
			throws HttpRequestException, FrameworkException {
		borrowInfo.setPid(request.getParameter("pid"));
		Map<String, Object> tojson = new HashMap<String, Object>();
		Calendar nowdate = Calendar.getInstance();
		Calendar publishDate = Calendar.getInstance();
		publishDate.setTime(DateUtil.format(borrowInfo.getPublishTime()));
		if (publishDate.after(nowdate)) {// 当前时间在发布时间之后是 预发布
			borrowInfo.setBorStatus("1");
		} else {
			borrowInfo.setBorStatus("2");
		}
		// 标的发布
		borrowServiceImpl.updateByPrimaryKeySelective(borrowInfo);
		// 发布成功
		if ("2".equals(borrowInfo.getBorStatus())) {
			// 自动投标
			autoBiddingProcessService.updateProcessAutoBidding(borrowInfo
					.getPid());
		}
		tojson.put("message", HttpMessage.SUCCESS_CODE);
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 新增新手标/体验标
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param borrow
	 * @param brrowRateStr
	 *            // 借款利率（字符串）
	 * @param financeProducts
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveNewStandard")
	@ResponseBody
	public ModelAndView saveNewStandard(BizBorrowInfoVO borrow,
			String brrowRateStr, String conditions,
			BizFinanceProducts financeProducts, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			if (StringUtil.isBlank(borrow.getPid())
					|| "1".equals(borrow.getPid())
					|| "-1".equals(borrow.getPid())) {
				// 新增
				financeProducts.setCreateUser(getUserId());
				financeProducts.setFinanceName(borrow.getBorrowName());
				financeProducts.setCreateTime(DateUtil.format(new Date()));
				borrow.setCreateUser(getUserId());
				borrow.setCreateTime(DateUtil.format(new Date()));
				borrow.setBorStatus(Constant.BORROW_STATUS_1);
				borrow.setIsEquitableAssignment(Constant.IS_EQUITABLE_ASSIGNMENT_NO);
				borrow.setSurplusMoney(borrow.getBorrowMoney());
				borrow.setBorrowProgress(BigDecimal.ZERO);
				borrow.setApplyTime(DateUtil.format(new Date()));
			} else {
				// 编辑
				financeProducts.setFinanceName(borrow.getBorrowName());
				financeProducts.setLastUpdateUser(getUserId());
				financeProducts.setLastUpdateTime(DateUtil.format(new Date()));
				financeProducts.setPid(request.getParameter("fpid"));
				borrow.setCreateUser(getUserId());
				borrow.setCreateTime(DateUtil.format(new Date()));
				borrow.setSurplusMoney(borrow.getBorrowMoney());
			}
			if (StringUtils.hasLength(brrowRateStr))
				borrow.setBorrowRate(new BigDecimal(brrowRateStr));
			bizBorrowInfoVOService.saveOrUpdateNewStandardAndFinanceProducts(
					borrow, financeProducts);
			modelView.addObject("borType", borrow.getBorrowType());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error("新手标体验标新增失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据标的id获取已选参与条件
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param objectId
	 * @param activityType
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectNewStandardConRelByObjectId")
	@ResponseBody
	public ModelAndView selectActParConRelByObjectId(
			HttpServletRequest request, HttpServletResponse response,
			String objectId) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<BizBorrowCondRel> list = bizBorrowCondRelServiceImpl
					.selectNewStandardConRelByObjectId(objectId);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

}
