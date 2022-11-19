/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月10日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.mywtinvest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.BizReceiptTransferCenterModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferFrontModel;
import com.yscf.core.model.ptp.financial.BizReceiptTransferModel;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizReceiptTransferService;
import com.yscf.core.service.business.impl.BizReceiptTransferServiceImpl;
import com.yscf.core.service.content.IContractManageService;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.pub.IPubFileService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.util.RepalceUtil;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.vo.system.FileConfig;

/**
 * Description：<br>
 * 债权转让Controller
 * 
 * @author JunJie.Liu
 * @date 2015年11月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("mybids/transferController")
public class TransferController extends EscfBaseWebController {

	@Autowired
	public TransferController(BizReceiptTransferServiceImpl service) {
		super(service);

	}

	@Override	
	public Class<?> getModel() {

		return BizReceiptTransfer.class;
	}

	private Logger logger = LoggerFactory.getLogger(TransferController.class);

	@Resource
	private ICustomerAccountService customerAccountService;
	
	@Resource
	private ICusTomerService customerService;
	
	@Resource
	public ISysParamsService sysParamsService;
	
	@Resource
	public IBizReceiptPlanService bizReceiptPlanService;
	
	@Resource
	public IContractManageService contractManageService;
	
	@Resource
	public IPubFileService pubFileService;
	
	@Resource
	public FileConfig fileConfig;
	
	@Resource(name = "columnContentService")
	private ColumnContentServiceImpl columnContentService;
	
	
	//vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	SysVipinfoServiceImpl sysVipinfoService;
	
	@RequestMapping("/index/toTransferList")
	public String toList() {
		return "transfer.list";
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到债权购买列表
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index/findTransferListData")
	@ResponseBody
	public ModelAndView toTransferList(BizReceiptTransferFrontModel condition,
			Integer pageIndex, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
			List<BizReceiptTransferFrontModel> vos = bizReceiptTransferService
					.findTransferList(condition, pageIndex, pageSize);
			Integer total = bizReceiptTransferService
					.findTransferCount(condition);
			total = total == null ? 0 : total;
			modelView.addObject("vos", vos);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询首页债权购买异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到债权购买详情
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月13日
	 * @version v1.0.0
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */

	@RequestMapping(value = "index/toTransferInfo")
	public ModelAndView toTransferInfo(String pid, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView("transfer.info");
		try {
			String userId = getUserId();
			if(StringUtils.hasLength(userId)){
				// 用户已登陆，查询其余额
				CustomerAccount ca = customerAccountService.getByCusterId(userId);
				modelView.addObject("ca", ca);
				modelView.addObject("user", true);
			}
			// 　判断pid是否为空，不为空进行下一步
			if (StringUtils.hasLength(pid)) {
				// 查询转让详情
				IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
				BizReceiptTransferModel vo = bizReceiptTransferService.getByPid(pid);
				if(null != vo){
					vo.setCreateUserName(RepalceUtil.replaceCustomerNameToStar(vo.getCreateUserName()));
				}
				modelView.addObject("vo", vo);
				// 失效时间
				if(vo.getExpireTime()!=null){
					modelView.addObject("expireDate",DateUtil.format(vo.getExpireTime()).getTime());
				}else{
					modelView.addObject("expireDate",System.currentTimeMillis());
				}
				// 系统当前时间
				modelView.addObject("sysDate",System.currentTimeMillis());
				
				// 获取对应的收款计划
				BizReceiptTransfer bizReceiptTransfer = bizReceiptTransferService.getBizReceiptTransferById(pid);
				List<BizReceiptPlan> list = new ArrayList<BizReceiptPlan>();
				if(bizReceiptTransfer!=null){
						
					if(StringUtils.hasLength(bizReceiptTransfer.getParentTransferId())){
						// 否则是债权再次转让
						list = bizReceiptPlanService.findListByTransfer(bizReceiptTransfer.getParentTransferId(),Constant.INVEST_SRC_TYPE_2);
					}else{
						// 如果父id为null，表示原标转让
						list = bizReceiptPlanService.findListByTransfer(bizReceiptTransfer.getPid(),Constant.INVEST_SRC_TYPE_1);
					}
					
				}
				modelView.addObject("list", list);
				// 获取应收总本金
				BigDecimal benjin = BigDecimal.ZERO;
				// 获取应收总利息
				BigDecimal lixi = BigDecimal.ZERO;
				// 获取应收总本息
				BigDecimal benxi = BigDecimal.ZERO;
				for(BizReceiptPlan rp : list){
					BigDecimal capital = rp.getCapital() == null ? BigDecimal.ZERO : rp.getCapital();
					BigDecimal interest = rp.getInterest() == null ? BigDecimal.ZERO : rp.getInterest();
					benjin = benjin.add(capital);
					lixi = lixi.add(interest);
				}
				benxi = benjin.add(lixi);
				
				modelView.addObject("benjin", benjin);
				modelView.addObject("lixi", lixi);
				modelView.addObject("benxi", benxi);
				
				
				
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("跳转到债权购买详情异常：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 购买债权
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月13日
	 * @version v1.0.0
	 * @param pid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/buyTransfer", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView buyTransfer(String pid, String pwd,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		String userId = getUserId();
		try {

			int num = customerService.vailidateTradePassword(new String(RSAUtil.decode(pwd)), userId);
			if(num>-1){
				//校验交易密码不通过
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					
				}
				n = n == null ? 4 : n;
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num", n-num);
				throw new RuntimeException("校验交易密码失败！");
			}

			// 　判断pid是否为空，不为空进行下一步
			if (StringUtils.hasLength(pid)) {
				IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
				bizReceiptTransferService.buyTransfer(pid, userId,Constant.INVEST_TYPE_1);
			}

			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
			
			
		}catch(TradePwdFreezeException te){
			// 用户被冻结,获取剩余冻结分钟
			Date time = customerService.getTradeFreezeTime(userId);
			SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_FREEZE_TIME);
			Integer t=null;
			try{
				t= Integer.parseInt(sp.getParamValue());
			}catch(Exception e){
				
			}
			t = t == null ? 60 : t;
			if(time!=null){
				Long l = time.getTime() - System.currentTimeMillis();
				if(l > 0){
					t = (int) (l/(1000*60));
				}else{
					t = 0;
				}
			}
			modelView.addObject("errorCode", Constant.USER_TRADE_FREEZE);
			modelView.addObject("t",t);
			MessageBuilder.processError(modelView, te, request);
			
		}catch(TradePwdIsBlankException te){
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, te, request);
			
		}catch (Exception e) {
			
			if (logger.isDebugEnabled()) {
				logger.debug("跳转到债权购买详情异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 债权转让撤销
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/revokeTransfer", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView updateReceiptPlanById(
			BizReceiptTransferFrontModel condition,String pwd, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {

			if (condition == null || condition.getTransferId() == null) {
				throw new RuntimeException(" id is null");
			}
			String userId = getUserId();
			int num = customerService.vailidateTradePassword(pwd, userId);
			if(num > -1){
				//校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num",num);
				throw new RuntimeException("校验交易密码失败！");
			}

			IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
			BizReceiptTransfer rt = new BizReceiptTransfer();
			rt.setPid(condition.getTransferId());
			rt.setRecTraDesc(Constant.USER_REVOKE_MSG);
			bizReceiptTransferService.revokeReceiptTransfer(rt, null);

			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
			
		}catch(TradePwdFreezeException te){
			// 交易已经被冻结
			modelView.addObject("errorCode", te.getMessage());
			MessageBuilder.processError(modelView, te, request);
		}catch(TradePwdIsBlankException te){
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, te, request);
			
		}catch (Exception e) {
			if (logger.isDebugEnabled()) {
				String id = "";
				if (condition != null) {
					id = condition.getTransferId();
				}
				logger.debug("债权转让用户撤销异常：id=【" + id + "】", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 债权管理
	 * @author  JunJie.Liu
	 * @date    2015年11月18日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toTransferManage")
	public String toTransferManage(String flag){
		HttpServletRequest req = getRequest();
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_BONDS);
		req.setAttribute("flag", flag);
		ColumnContent transferColumnContent = columnContentService.selectContentByWebTag("ep2p_adv_transferManagePage_b_1");
		req.setAttribute("bannerUrl", transferColumnContent!=null?transferColumnContent.getUrl():"");
		//查vip等级
		List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(getUserId());
		if(null != list && list.size()>0){
		String vipLevel = list.get(0).getVipLevel();
		if(null != vipLevel || !"".equals(vipLevel)){
			String vips = "VIP"+vipLevel;
			if(!"".equals(vipLevel) && vipLevel!=null){
				req.setAttribute("vipLevel",vips);
			}else{
				req.setAttribute("vipLevel","VIP");
			}
		}
		  }
		else{
			req.setAttribute("vipLevel","VIP");
		}
		return "temp.usercenter.transfer";
	}
	
	/**
	 * 
	 * Description：<br>
	 * 债权管理 个人中心数据查询
	 * 
	 * @author JunJie.Liu
	 * @date 2015年10月23日
	 * @version v1.0.0
	 * @param bizBorrowInfoVO
	 * @param request
	 * @param response
	 * @param flag
	 *            1.持有用 2.转让中 3.已转让
	 * @return
	 */
	@RequestMapping(value = "/findTransferCenterListData")
	@ResponseBody
	public ModelAndView toTransferList(String flag,BizReceiptTransferCenterModel condition,
			Integer pageIndex, Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			IBizReceiptTransferService bizReceiptTransferService = (IBizReceiptTransferService) getService();
			
			String userId = getUserId();
			
			List<BizReceiptTransferCenterModel> vos = bizReceiptTransferService
					.findTrasferCenterVos(condition, userId, pageIndex,
							pageSize, flag);
			Integer total = bizReceiptTransferService
					.findTrasferCenterVosCount(condition,userId, flag);
			total = (total == null ? 0 : total);
			modelView.addObject("vos", vos);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询债权管理持有中异常：", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	

	
}
