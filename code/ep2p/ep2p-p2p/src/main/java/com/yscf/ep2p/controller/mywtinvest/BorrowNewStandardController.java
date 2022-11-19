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
 * 2015年11月16日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.mywtinvest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.exception.AvailableAmountExcepiton;
import com.yscf.core.exception.BorrowStatusChangeException;
import com.yscf.core.exception.ConditionExcepiton;
import com.yscf.core.exception.OutInvestScopeException;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.model.content.BizBorrowAgreement;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.business.IBizBorrowService;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.core.service.content.IContractManageService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.ISysVipinfoService;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.vo.system.CustomerVo;

/**
 * Description：<br> 
 * 新手标/体验标
 * @author  JunJie.Liu
 * @date    2015年11月16日
 * @version v1.0.0
 */
@Controller
@RequestMapping("mybids/borrowNewStandardController")
public class BorrowNewStandardController extends EscfBaseWebController{
	
	@Autowired
	public BorrowNewStandardController(BizBorrowServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(BorrowNewStandardController.class);


	@Override
	public Class<?> getModel() {
		
		return BizBorrow.class;
	}
	
	@Resource
	private IBizBorrowDetailService bizBorrowDetailService;
	
	@Resource(name = "bizFinanceProductsServiceImpl")
	private BizFinanceProductsServiceImpl productsServiceImpl;
	
	@Resource
	private ICustomerAccountService customerAccountService;
	
	@Resource
	private ICusTomerService customerService;
	
	@Resource
	private ISysParamsService sysParamsService;
	
	@Resource
	private ISysVipinfoService sysVipinfoService;
	
	@Resource
	private IContractManageService contractManageServiceImpl;
	 
	/**
	 * 
	 * Description：<br> 
	 * 新手标详情 
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value="/index/toBorrowStandardInfo")
	public ModelAndView toBorrowStandardInfo(BizBorrow bizBorrow,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelView = new ModelAndView("standard.info");
		if(bizBorrow!=null && bizBorrow.getPid()!=null){
			IBizBorrowService bizBorrowServiceImpl = (IBizBorrowService)getService();
			BizBorrow borrow = bizBorrowServiceImpl.getBizBorrowById(bizBorrow.getPid());
			BizFinanceProducts bfp = productsServiceImpl
					.getBizFinanceProductsByBorrowId(borrow.getPid());
			if(bfp!=null){
				BizBorrowAgreement ag = contractManageServiceImpl.getById(bfp.getBorrowAgreementId());
				modelView.addObject("ag", ag);
			}
			modelView.addObject("borrow", borrow);
			modelView.addObject("financeProducts", bfp);
			
			
			// 用户未登陆，跳转到登陆 
			CustomerVo user = getContextUser();
			String userId = (user!=null?user.getPid():null);
			CustomerAccount ca = null;
			if(StringUtils.hasLength(userId)){
				// 用户已登陆，查询其余额
				ca = customerAccountService.getByCusterId(userId);
			}
			modelView.addObject("ca", ca);
			//　发布时间
			String publishTime = borrow.getPublishTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date pt = null;
			try {
				pt = sdf.parse(publishTime);
			} catch (ParseException e) {
				if(logger.isDebugEnabled()){
					logger.debug("新手标详情异常："+e.getMessage());
				}
			}
			Calendar c = Calendar.getInstance();
			c.setTime(pt);
			Date pe = c.getTime();
			c.add(Calendar.DATE, Integer.parseInt(borrow.getDeadline()));
			
			Long time = 0L;
			if(Constant.BORROW_STATUS_2.equals(borrow.getBorStatus())){
				// 招标时间
				pe = c.getTime();
			}else{
				// 待招标时间
				time =c.getTimeInMillis() - System.currentTimeMillis();
			}
	
			
			Long timeRemain = pe.getTime() - System.currentTimeMillis();
			modelView.addObject("timeRemain", timeRemain);
			modelView.addObject("time", time);
			
			if(user!=null){
				// 获取用户现在的投资积分比率
				SysParams sysParams = sysParamsService.getParamsByParamKey(Constant.POINT_INVEST_TYPE);
				// 根据当前登录的客户ID查询客户的信息
				CusTomer cusTomer = customerService.selectByPrimaryKey(userId);
				String paramValue = sysParams.getParamValue();
				BigDecimal pointValue = BigDecimal.ZERO;
				// 判断是否存在<,>
				if (paramValue.indexOf(",") != -1) {
					String[] pointArr = paramValue.split(",");
					// 存在<,>就判断是否是VIP
					if ("1".equals(cusTomer.getIsVip())) {
						// 是VIP
						pointValue = new BigDecimal(pointArr[1]);
					} else {
						// 不是VIP
						pointValue = new BigDecimal(pointArr[0]);
					}
				} else {
					// 不存在
					pointValue = new BigDecimal(paramValue);
				}
				modelView.addObject("pointValue",pointValue.divide(new BigDecimal(100)));
				// 获取用户现在的管理费率
				SysVipinfo svip = sysVipinfoService.getByLevel(cusTomer.getVipLevel());
				BigDecimal manageFee = new BigDecimal(0.1);
				if(null != svip){
					manageFee = svip.getDiscount();
				}
				modelView.addObject("manageFee", manageFee);
			}
		}
		return modelView;
		
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 新手标/体验标   立即投资
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value="/investStandard",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView investStandard(BizBorrow bizBorrow,String pwd,BigDecimal amount,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelView = new ModelAndView();
		
		String userId = getUserId();
		try{
			int num = customerService.vailidateTradePassword(new String(RSAUtil.decode(pwd)), userId);
			
			if(num>-1){
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					// 无效
				}
				n = n == null ? 4 : n;
				//校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num",n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
			// 进行投资
			IBizBorrowService bizBorrowServiceImpl = (IBizBorrowService)getService();
			bizBorrowServiceImpl.invest(bizBorrow,amount,userId,Constant.INVEST_TYPE_1);
			MessageBuilder.processSuccess(modelView, null,HttpMessage.SUCCESS_MSG, request);
			
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
			
		}catch(ConditionExcepiton te){
			// 不满足条件
			modelView.addObject("errorMsg", te.getMessage());
			MessageBuilder.processError(modelView, te, request);
		}catch(AvailableAmountExcepiton te){
			// 扣款失败
			modelView.addObject("errorMsg", te.getMessage());
			MessageBuilder.processError(modelView, te, request);
		}catch(BorrowStatusChangeException te){
			// 标的状态已经改变
			modelView.addObject("errorMsg", te.getMessage());
			MessageBuilder.processError(modelView, te, request);
		}catch(OutInvestScopeException te){
			// 超出投资范围
			modelView.addObject("errorMsg", te.getMessage());
			MessageBuilder.processError(modelView, te, request);
			
		}catch(TradePwdIsBlankException te){
			// 交易密码为空
			modelView.addObject("errorCode", Constant.USER_TRADE_PWD_BANK);
			MessageBuilder.processError(modelView, te, request);
			
		}catch(Exception e){
			
			if(logger.isDebugEnabled()){
				logger.debug("投资新手标异常："+e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
			
		}
		return modelView;
		
	}
	/**
	 * 
	 * Description：<br> 
	 * 新手标/体验标   投资记录
	 * @author  JunJie.Liu
	 * @date    2015年11月16日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value="/index/investStandardRecord")
	@ResponseBody
	public ModelAndView investStandardRecord(BizBorrow bizBorrow,Integer pageIndex,Integer pageSize,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelView = new ModelAndView();
		try{
			pageSize = pageSize == null ? 10 : pageSize;
			pageIndex = pageIndex == null ? 0 : pageIndex * pageSize;
			
			List<BizBorrowDetail> bizBorrowDetailList = bizBorrowDetailService.selectBizBorrowDetailByBorrowId(bizBorrow.getPid(),pageIndex,pageSize);
			Integer count = bizBorrowDetailService.selectBizBorrowDetailByBorrowIdCount(bizBorrow.getPid());
			modelView.addObject("list", bizBorrowDetailList);
			modelView.addObject("total", count);
			
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		}catch(Exception e){
			if(logger.isDebugEnabled()){
				logger.debug("新手标/体验标   投资记录查询异常："+e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
		
	}

}


