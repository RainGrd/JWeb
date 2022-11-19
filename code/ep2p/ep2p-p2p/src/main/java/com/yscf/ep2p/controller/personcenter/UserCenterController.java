/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 个人中心信息页面控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月19日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.personcenter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.common.util.ImageCutHelpUtil;
import com.yscf.common.util.ImageReduceUtil;
import com.yscf.common.util.PageInfoUtil;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.autobidding.AutoBidding;
import com.yscf.core.model.ptp.autobidding.AutoBiddingQueue;
import com.yscf.core.model.ptp.base.BeCurrentModel;
import com.yscf.core.model.ptp.financial.BizReceiptPlanModel;
import com.yscf.core.model.ptp.investment.InvestDueInModel;
import com.yscf.core.model.ptp.investment.InvestmentInfoModel;
import com.yscf.core.model.ptp.investment.InviteTendersModel;
import com.yscf.core.model.ptp.investment.TransferTendersMode;
import com.yscf.core.model.sms.CustMessRecord;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.activity.impl.PubActivityAreaServiceImpl;
import com.yscf.core.service.autobidding.impl.AutoBiddingQueueServiceImpl;
import com.yscf.core.service.autobidding.impl.AutoBiddingServiceImpl;
import com.yscf.core.service.business.impl.BizReceiptPlanServiceImpl;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.core.service.financial.impl.CustFundWaterServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.sms.impl.CustMessRecordServiceImpl;
import com.yscf.core.service.sms.impl.SmsRecordsCustomerServiceImpl;
import com.yscf.core.service.system.ISysAccountService;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.ICustLargessVipWaterService;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.vo.system.CustomerVo;
import com.yscf.ep2p.vo.system.FileConfig;
import com.yscf.ep2p.vo.usercenter.AccountInfoVo;
import com.yscf.ep2p.vo.usercenter.AutoBiddingVo;

/**
 * Description：<br> 
 * 个人中心信息页面控制层
 * @author  Lin Xu
 * @date    2015年11月19日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/userinfo/centerController")
public class UserCenterController extends EscfBaseWebController  {
	
	Logger logger = LoggerFactory.getLogger(UserCenterController.class);
	
	//存刚头像上传的临时地址信息
	private static String TEMPORARY_PATH = "upload/temporary/avatar/";
	
	private static String CURRENTLY_PATH = "upload/currently/avatar/";

	@Autowired
	public UserCenterController(UserCenterServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BeCurrentModel.class;
	}
	
	@Resource(name="customerAccountService")
	private CustomerAccountServiceImpl custmoeAccountService;
	
	@Resource(name="bizReceiptPlanService")
	private BizReceiptPlanServiceImpl bizReceiptPlanServiceImpl;
	
	//客户资料明细
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;
	
	//客户消息中心
	@Resource(name = "smsRecordsCustomerService")
	private SmsRecordsCustomerServiceImpl smsRecordsCustomerService;
	
	//自动投标服务
	@Resource(name = "autoBiddingServiceImpl")
	private AutoBiddingServiceImpl autoBiddingServiceImpl;
	
	//自动投标增加排队序列服务
	@Resource(name = "autoBiddingQueueServiceImpl")
	private AutoBiddingQueueServiceImpl autoBiddingQueueServiceImpl;
	
	//消息中心
	@Resource(name = "custMessRecordService")
	private CustMessRecordServiceImpl cusMsgRecordService;
	
	//vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	private SysVipinfoServiceImpl sysVipinfoService;
	
	//文档配置对象
	@Resource(name="fileConfig")
	private FileConfig fileconfig;
	
	//资金流水
	@Resource(name = "custFundWaterService")
	private CustFundWaterServiceImpl custFundWaterService;
	
	//系统参数
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;
	
	//获取Memcache客户端信息
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	//购买vip 扣除账户余额
    @Resource(name="customerAccountService")
    CustomerAccountServiceImpl customerAccountServiceImpl;
    
    //购买vip 扣除账户余额
    @Resource(name="pubActivityAreaServiceImpl")
    private PubActivityAreaServiceImpl pubAcAreaServiceImpl;
    
    @Resource(name="sysAcountService")
    ISysAccountService sysAccountService;
    
    //赠送 vip流水表
    @Resource(name="custLargessVipWaterServiceImpl")
    ICustLargessVipWaterService custLargessVipWaterService;
    
    //购买vip  加载栏目位的
    @Resource(name="columnContentService")
    ColumnContentServiceImpl columnContentServiceImpl;
	//---------------------------------------------账户总览---------------------------------------------------
	/**
	 * Description：<br> 
	 * 转到个人中心账户总览
	 * @author  Lin Xu
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toUserCenterHome")
	public String toUserCenterHome(){
		//添加选中的菜单
		HttpServletRequest req = getRequest();
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_HOME);
		UserCenterServiceImpl ucservice = (UserCenterServiceImpl) getService();
		//个人中心我的账户对象
		AccountInfoVo aivo = new AccountInfoVo();
		String userId = getUserId();
		//获取账户信息
		CustomerAccount customerAccount = custmoeAccountService.getByCusterId(userId);
		if(null != customerAccount){
			aivo.setAvailableBalance(customerAccount.getAvailableAmount());
			aivo.setFreezingAmount(customerAccount.getFreezeAmount());
		}
		//获取待收金额
		HashMap<String, BigDecimal> durInMoney = ucservice.getUserDueInMoney(userId);
		if(null != durInMoney){
			aivo.setDueinAmount(durInMoney.get("tatolcapital"));
			aivo.setDueinInterest(durInMoney.get("tatolinterest"));
		}
		//获取利息信息   投资利息   加息利息   投资奖励   红包收益  推荐奖励
		aivo.setInvestInterest(ucservice.getUserInvestInterest(userId));
		aivo.setRateInterest(ucservice.getUserRateInterest(userId));
		aivo.setInvestmentIncentive(ucservice.getUserInIncentive(userId));
		aivo.setRedEnvelope(ucservice.getUserRedEnvelope(userId));
		aivo.setRecommendedAwards(ucservice.getUserRecommendedAwards(userId));
		//获取当天的的待收记录
		Date today = new Date();
		req.setAttribute("today", today);
		BizReceiptPlanModel brpm = new BizReceiptPlanModel();
		brpm.setReceiptStatus("1");
		brpm.setCustomerId(userId);
		brpm.setStartExpireTime(DateUtil.format(today, "yyyy-MM-dd"));
		brpm.setEndExpireTime(DateUtil.format(today, "yyyy-MM-dd"));
		//添加账户预览对象
		req.setAttribute("aivo", aivo);
		List<BizReceiptPlanModel> brpmlist = bizReceiptPlanServiceImpl.findList(brpm, null, null);
		req.setAttribute("brpmlist", brpmlist);   
		//账户余额
		req.setAttribute("accountBalance", aivo.getAccountBalance());
		//总资产
		req.setAttribute("totalAssets", aivo.getTotalAssets());
		//总收益
		req.setAttribute("totalRevenue", aivo.getTotalRevenue());
		
		//底部活动广告信息
		List<PubActivityArea> pubActivList = pubAcAreaServiceImpl.selectLoadActicityArea(5, 5);
		req.setAttribute("pubActivList", pubActivList);
		
		return "temp.usercenter.page";
	}
	
	
	/**
	 * Description：<br> 
	 * 获取待收列明
	 * @author  Lin Xu
	 * @date    2015年11月28日
	 * @version v1.0.0
	 * @param str
	 * @return
	 */
	@RequestMapping("/getDueinMoenyList")
	public String getDueinMoenyList(String data,HttpServletResponse response){
		//添加选中的菜单
		HttpServletRequest req = getRequest();
		req.setAttribute("brpmlisto", null);
		//获取用户id
		String userId = getUserId();
		BizReceiptPlanModel brpm = new BizReceiptPlanModel();
		brpm.setReceiptStatus("1");
		brpm.setCustomerId(userId);
		brpm.setStartExpireTime(data);
		brpm.setEndExpireTime(data);
		List<BizReceiptPlanModel> brpmlist = bizReceiptPlanServiceImpl.findList(brpm, null, null);
		req.setAttribute("brpmlisto", brpmlist);
		return "personcenter/accountoverview/myDueinList";
	}
	
	/**
	 * Description：<br> 
	 * 跳转自动投标页面
	 * @author  Lin Xu
	 * @date    2015年12月4日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toAutomaticBidPage")
	public String toAutomaticBidPage(){
		//添加选中的菜单
		HttpServletRequest req = getRequest();
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_HOME);
		//获取个人自动投标的列表
		List<AutoBidding> ablist = autoBiddingServiceImpl.selectAutoBiddingList(getUserId());
		req.setAttribute("autolist", ablist);
		return "temp.usercenter.automaticbid";
	}
	
	/**
	 * Description：<br> 
	 * 添加自动投标的项目信息
	 * @author  Lin Xu
	 * @date    2015年12月9日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value="/addAutoBiddingInfo",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> addAutoBiddingInfo(HttpServletRequest request,AutoBiddingVo abvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String tuserid = getUserId();
			//获取个人自动投标的列表
			List<AutoBidding> ablist = autoBiddingServiceImpl.selectAutoBiddingList(tuserid);
			if(null != ablist && ablist.size() > 3){
				//历史已启动的信息未成功禁用
				processFail(remap,"2");
			}else{
				if(null != abvo && StringUtil.isNotEmpty(tuserid)){
					AutoBidding abmodel = new AutoBidding();
					abmodel = ConvertObjectUtil.convertObject(abvo, AutoBidding.class);
					abmodel.setPid(abmodel.getPK());
					abmodel.setCustomerId(tuserid);
					abmodel.setCreateUser(tuserid);
					abmodel.setLastUpdateUser(tuserid);
					//新增投标-启动-检查是否是有启动状态的数据   失效以前的启动的数据
					if(abmodel.getAutostatus() == 1){
						boolean bool = autoBiddingServiceImpl.updateInvalidStartBidding(tuserid);
						if(bool){
							//自动投标成功,并加入到队列中
							autoBiddingServiceImpl.insert(abmodel);
							//添加对象
							AutoBiddingQueue abqmodel = new AutoBiddingQueue();
							abqmodel.setPid(abqmodel.getPK());
							abqmodel.setCustomerId(tuserid);
							abqmodel.setAutoBiddingId(abmodel.getPid());
							abqmodel.setCreateUser(tuserid);
							abqmodel.setLastUpdateUser(tuserid);
							autoBiddingQueueServiceImpl.insert(abqmodel);
							processSuccess(remap,"0");
						}else{
							//历史已启动的信息未成功禁用
							processFail(remap,"2");
						}
					//新增投标-禁用-直接加入现有的自动投标信息
					}else{
						//自动投标成功
						autoBiddingServiceImpl.insert(abmodel);
						processSuccess(remap,"0");
					}
				}else{
					//数据上传异常
					processFail(remap,"1");
				}
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("前台==添加自动投标信息异常：" + e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	/**
	 * Description：<br> 
	 * 转向编辑页面
	 * @author  Lin Xu
	 * @date    2015年12月8日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toUpdateAutomaticBidPage")
	public String toUpdateAutomaticBidPage(HttpServletRequest request,String pid){
		String repath = "personcenter/accountoverview/automaticBid/editAutomaticbid";
		try {
			AutoBidding abmodel = (AutoBidding) autoBiddingServiceImpl.selectByPrimaryKey(pid);
			request.setAttribute("abmodel", abmodel);
		} catch (FrameworkException e) {
			e.printStackTrace();
			request.setAttribute("errorinfo", "没有找到对应的自动投标的信息");
			repath = "common/comErrorInfo";
		}
		return repath;
	}
	
	/**
	 * Description：<br> 
	 * 修改自动投标信息
	 * @author  Lin Xu
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param request
	 * @param abvo
	 * @return
	 */
	@RequestMapping(value="/editAutoBiddingInfo",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> editAutoBiddingInfo(HttpServletRequest request,AutoBiddingVo abvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String userid = getUserId();
			if(null != abvo && StringUtil.isNotEmpty(userid)){
				String version = request.getParameter("version");
				AutoBidding abmodel = autoBiddingServiceImpl.selectKeyVersionAutoBidding(userid, abvo.getPid(), version);
				if(null != abmodel){
					abmodel = ConvertObjectUtil.convertObject(abvo, AutoBidding.class);
					abmodel.setLastUpdateTime(DateUtil.format(new Date()));
					//修改自动投标--启动--把以前启动的进行停止重新排队
					if(abmodel.getAutostatus() == 1){
						//移除排队及清除以前启动信息
						boolean bool = autoBiddingServiceImpl.updateInvalidStartBidding(userid);
						if(bool){
							//修改投标信息
							autoBiddingServiceImpl.updateByPrimaryKeySelective(abmodel);
							//重新排队
							AutoBiddingQueue abqmodel = new AutoBiddingQueue();
							abqmodel.setPid(abqmodel.getPK());
							abqmodel.setCustomerId(userid);
							abqmodel.setAutoBiddingId(abmodel.getPid());
							abqmodel.setCreateUser(userid);
							abqmodel.setLastUpdateUser(userid);
							autoBiddingQueueServiceImpl.insert(abqmodel);
							processSuccess(remap,"0");
						}else{
							processFail(remap,"2");
						}
					//修改自动投标--禁用--不做任何操作
					}else{
						//移除排队
						List<AutoBiddingQueue> selabqlist = autoBiddingQueueServiceImpl.selectByPrimaryUbdId(userid,abmodel.getPid());
						if(null != selabqlist && selabqlist.size() > 0){
							for(AutoBiddingQueue abqmodel : selabqlist){
								autoBiddingQueueServiceImpl.updateRemoveQueue(abqmodel);
							}
						}
						//修改投标信息
						autoBiddingServiceImpl.updateByPrimaryKeySelective(abmodel);
						processSuccess(remap,"0");
					}
				}else{
					//数据上传异常
					processFail(remap,"1");
				}
			}else{
				//数据上传异常
				processFail(remap,"1");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("前台==添加自动投标信息异常：" + e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	/**
	 * Description：<br> 
	 * 删除自动投标信息数据
	 * @author  Lin Xu
	 * @date    2015年12月11日
	 * @version v1.0.0
	 * @param request
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/deleteAutoBiddingInfo",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> deleteAutoBiddingInfo(HttpServletRequest request,String pid){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String userid = getUserId();
			if(StringUtil.isNotEmpty(pid) && StringUtil.isNotEmpty(userid)){
				AutoBidding delabmodel = (AutoBidding) autoBiddingServiceImpl.selectByPrimaryKey(pid);
				if(null != delabmodel){
					delabmodel.setStatus("0");
					delabmodel.setLastUpdateUser(userid);
					autoBiddingServiceImpl.updateByPrimaryKeySelective(delabmodel);
					//移除排队信息
					List<AutoBiddingQueue> selabqlist = autoBiddingQueueServiceImpl.selectByPrimaryUbdId(userid, pid);
					if(null != selabqlist && selabqlist.size() > 0){
						for(AutoBiddingQueue abqmodel : selabqlist){
							autoBiddingQueueServiceImpl.updateRemoveQueue(abqmodel);
						}
					}
					processSuccess(remap,"0");
				}else{
					processFail(remap,"2");
				}
			}else{
				//数据上传异常
				processFail(remap,"1");
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("前台==添加自动投标信息异常：" + e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	
	
	//-----------------------------------------我的投资--------------------------------------------------
	/**
	 * Description：<br> 
	 * 转到个人中心我的投资页面
	 * @author  Lin Xu
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toUserCenterInvest")
	public String toUserCenterInvest(){
		HttpServletRequest req = getRequest();
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_INVEST);
		UserCenterServiceImpl ucservice = (UserCenterServiceImpl) getService();
		String userid = getUserId();
		//保存排名信息
		HashMap<String, Object> rankInfo = ucservice.selectInvestmentRanking(userid);
		//查vip等级
		List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(userid);
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
		req.setAttribute("rankInfo", rankInfo);
		//计算投资的总额信息
		req.setAttribute("investmoney", ucservice.getUserInvestMoney(userid) == null ? 0 : ucservice.getUserInvestMoney(userid));
		req.setAttribute("idueinmoney", ucservice.getUserIDueinMoney(userid) == null ? 0 : ucservice.getUserIDueinMoney(userid));
		req.setAttribute("netmargin", ucservice.getUserNetmargin(userid) == null ? 0 : ucservice.getUserNetmargin(userid));
		
		return "temp.usercenter.invest";
	}
	
	/**
	 * Description：<br> 
	 * 获取用户的投资信息数据
	 * @author  Lin Xu
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param type （招标中，待收中，已结清）的类型
	 * @param startInvestmentTime 投资的开始时间
	 * @param endInvestmentTime  投资的结束时间
	 * @param page 当前页
	 * @param pageRows 当前页显示的行数
	 * @return
	 */
	@RequestMapping(value="/getUserInvestInfo",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getUserInvestInfo(String type,
			String startInvestmentTime, String endInvestmentTime, Integer page,
			Integer pageRows) {
		HashMap<String, Object> relutMap = new HashMap<String, Object>();
		UserCenterServiceImpl ucservice = (UserCenterServiceImpl) getService();
		try{
			String customerId = getUserId();
			if(StringUtil.isNotEmpty(type) && StringUtil.isNotEmpty(customerId)){
				//查询参数
				HashMap<String, Object> param = new HashMap<String, Object>();
				//添加分页信息
				PageInfo pageinfo = new PageInfo();
				if(null != pageRows && null != page){
					pageinfo = PageInfoUtil.getIncePageInfo(pageRows, page+1);
				}
				//添加查询信息
				param.put("customerId", customerId);
				param.put("startInvestmentTime", startInvestmentTime);
				param.put("endInvestmentTime", endInvestmentTime);
				//判断返回值 1 待收中    2  招标中  3  转让   4 已完结
				if("1".equals(type)){
					//查询数据信息
					PageList<InvestDueInModel> duemList = ucservice.selectDueinBorrow(param, pageinfo);
					Integer tatol = duemList.getPaginator().getTotalCount();
					relutMap.put("list", duemList);
					relutMap.put("total", tatol);
					processSuccess(relutMap, "查询成功");
				}
				if("2".equals(type)){
					//查询数据信息
					PageList<InviteTendersModel> duemList = ucservice.selectInviteTendersInfo(param, pageinfo);
					Integer tatol = duemList.getPaginator().getTotalCount();
					relutMap.put("list", duemList);
					relutMap.put("total", tatol);
					processSuccess(relutMap, "查询成功");
				}
				if("3".equals(type)){
					//查询数据信息
					PageList<TransferTendersMode> duemList = ucservice.selectTransferInfo(param, pageinfo);
					Integer tatol = duemList.getPaginator().getTotalCount();
					relutMap.put("list", duemList);
					relutMap.put("total", tatol);
					processSuccess(relutMap, "查询成功");
				}
				if("4".equals(type)){
					//查询数据信息
					PageList<InvestmentInfoModel> duemList = ucservice.selectInvestmentInfo(param, pageinfo);
					Integer tatol = duemList.getPaginator().getTotalCount();
					relutMap.put("list", duemList);
					relutMap.put("total", tatol);
					processSuccess(relutMap, "查询成功");
				}
			}else{
				processFail(relutMap, "查询参数异常");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(relutMap,e.getMessage());
		}
		return relutMap;
	}
	
	
	
	//------------------------------------------------------------------------------------------------
	
	/**
	 * Description：<br> 
	 * 消息中心
	 * @author  Lin Xu
	 * @date    2015年12月2日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toUserMessage")
	public String toUserMessage(){
		HttpServletRequest req = getRequest();
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_INFODTL);
		//查vip等级
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
		return "temp.usercenter.msglist";
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 消息中心列表页面
	 * @author  heng.wang
	 * @date    2015年12月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectMsgList")
	@ResponseBody
	public  HashMap<String, Object> selectMsgList(CustMessRecord custMessRecord,String beginTime,
			Integer pageIndex,Integer pageSize,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		pageSize = pageSize == null ? 10 : pageSize;
		pageIndex = pageIndex == null ? 0 : pageIndex * pageSize;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
	   try {
			//添加分页信息
			custMessRecord.setCustomerId(super.getUserId());
			custMessRecord.setBeginTime(beginTime);
			List<CustMessRecord> list = cusMsgRecordService.selectMsgList(custMessRecord,pageIndex,pageSize);
			Integer total = cusMsgRecordService.selectTotalMsgList(custMessRecord);
			resultMap.put("data", list);
			resultMap.put("total", total);
			processSuccess(resultMap, "查询成功");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processSuccess(resultMap, "查询失败");
		}
		return resultMap;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 消息中心：批量标记为已读
	 * @author heng.wang
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/markRead")
	@ResponseBody
	public ModelAndView markRead(String pid,HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count=0;
		try {
			count = cusMsgRecordService.batchUpdateMarkReadByPid(pid);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}
    
	
	/**
	 * Description：<br> 
	 * 邀请有奖
	 * @author  Lin Xu
	 * @date    2015年12月2日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toUserPrize")
	public String toUserPrize(){
		HttpServletRequest req = getRequest();
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_INVITE);
		List<CusTomer> list1 = cusTomerServiceImpl.selectReferralCodeByCustd(getUserId());
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
		 }else{
			req.setAttribute("vipLevel","VIP");
		}
		if(!"".equals(list1) && list1!=null){
			String referCode = list1.get(0).getReferralCode();
			req.setAttribute("referCode",referCode);
		}
		return "temp.usercenter.prize";
	}
	
	/**
	 * Description：<br> 
	 * 个人中心，资金流水
	 * @author  Lin Xu
	 * @date    2015年12月3日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toUserCashflow")
	public String toUserCashflow(){
		HttpServletRequest req = getRequest();
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_FUNDDTL);
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
		return "temp.usercenter.cashflow";
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 根据条件查询资金流水
	 * @author  heng.wang
	 * @date    2015年10月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectZiJinWater")
	@ResponseBody
	public ModelAndView selectZiJinWater(String beginTime,String endTime,String watrType, Integer page,
			Integer pageRows,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String userId = super.getUserId();
			//查询参数
			HashMap<String, Object> param = new HashMap<String, Object>();
			//添加分页信息
			param.put("limitpage", 1);
			param.put("startNum", getStartNumbers(page, pageRows));
			param.put("pageRows", pageRows);
			param.put("userId", userId);
			param.put("waterType", watrType);
			param.put("beginTime", beginTime);
			param.put("endTime", endTime);
			List<CustFundWater> list  = custFundWaterService.selectZiJinWater(param);
			modelView.addObject("rows", list);
//			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据条件查询资金流水详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	
	/**
	 * Description：<br> 
	 * 个人中心，vip等级信息页面
	 * @author  Lin Xu
	 * @date    2015年12月11日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toVipLevelInfo")
	public String toVipLevelInfo(String vipLevel,HttpServletRequest request){
		request.setAttribute("vipLevel", vipLevel);
		return "temp.usercenter.toVipLevelInfo";
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到vip购买页面
	 * @author heng.wang
	 * @date 2015年12月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toShoppingPage")
	@ResponseBody
	public ModelAndView toShoppingPage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/personcenter/vipInfo/toShoppingPage");
		String userId=super.getUserId();
		cusTomerServiceImpl.selectByPrimaryKey(userId);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 购买vip信息：查个人信息
	 * @author heng.wang
	 * @date 2015年12月12日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/getUserInfoByPid")
	@ResponseBody
	public ModelAndView getUserInfoByPid(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
		    String customerId = super.getUserId();//获取当前登录id
			List<CusTomer> list =  cusTomerServiceImpl.getUserInfoByPid(customerId);
			modelView.addObject("data", list.get(0));
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("购买vip信息：查个人信息：", e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br>
	 * 购买vip信息：查个人经验值
	 * @author heng.wang
	 * @date 2016年2月24日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectVipInfo")
	@ResponseBody
	public ModelAndView selectVipInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
		    String customerId = super.getUserId();//获取当前登录id
			CusTomer cusTomer =  cusTomerServiceImpl.selectByPrimaryKey(customerId);
			//查vip等级
			List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(customerId);
			String isVip = cusTomer.getIsVip();
			BigDecimal discount = list.get(0).getDiscount();//当前信息服务管理费
			Integer experienceStart =list.get(0).getExperienceStart();//当前等级的起始经验值
			Integer experienceEnd =list.get(0).getExperienceEnd();//当前等级的最大经验值
			Integer empiricalValue =  list.get(0).getEmpiricalValue();//当前经验值
			String vipId = list.get(0).getVipId();
			if(!"".equals(cusTomer) && cusTomer!=null){
				if(list!=null &&list.size()>0){
					String vipLevel = list.get(0).getVipLevel();
					if(isVip.equals("否") || vipLevel.equals("VIP")){
						if(vipId.equals("") || null ==vipId){
							cusTomer.setEmpiricalValue(0);
							cusTomer.setVipLevel("VIP");
							cusTomer.setServiceCharge(null);//当前信息服务管理费
						}else{
							//非vip的时候，如果vip id经验值显示为当前经验值
							cusTomer.setEmpiricalValue(empiricalValue);
							cusTomer.setVipLevel(vipLevel);
							cusTomer.setServiceCharge(discount);//服务费
							cusTomer.setExperienceStart(experienceStart);//当前等级开始值
							cusTomer.setMaxEmpiricalValueEnd(experienceEnd);//当前等级最大值
						}
//						cusTomer.setExpirationTime(new Date());
				}else{
					// 计算出当前时间加上几个月后的天数是多少天
					GregorianCalendar gcd = new GregorianCalendar();
					// 获取当前时间
					gcd.setTime(new Date());
					// 添加原来的日期
					gcd.add(Calendar.DATE, cusTomer.getVipTime());
					// 获取购买后的日期
					Date dt = gcd.getTime();
					cusTomer.setExpirationTime(dt);//到期时间
					cusTomer.setVipLevel(vipLevel);//当前等级
					cusTomer.setExperienceStart(experienceStart);//当前等级开始值
					cusTomer.setMaxEmpiricalValueEnd(experienceEnd);//当前等级最大值
					cusTomer.setServiceCharge(discount);//服务费
			}
			   }
				 }
			modelView.addObject("data", cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("购买vip信息：查个人经验值：", e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br>
	 * 购买vip信息：栏目位查询
	 * @author heng.wang
	 * @date 2016年3月10日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectVipCloum")
	@ResponseBody
	public ModelAndView selectVipCloum(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		ColumnContent columnContent = new ColumnContent();
		try {
			 columnContent = columnContentServiceImpl.selectContentByWebTag("ep2p_col_vipLevelPage_t_1");
			modelView.addObject("data", columnContent);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("购买vip信息：栏目位查询：", e.getMessage());
			}
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br>
	 * 验证交易密码是否正确
	 * @author heng.wang
	 * @date 2015年12月12日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/validataTradPwd")
	@ResponseBody
	public ModelAndView validataTradPwd(String tradPwd,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		String userId = getUserId();
		int num=0;
		try{
			   num = cusTomerServiceImpl.vailidateTradePassword(new String(RSAUtil.decode(tradPwd)), userId);
			if(num>-1){
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				try{
					n= Integer.parseInt(sp.getParamValue());
				}catch(Exception e){
					
				}
				n = n == null ? 4 : n;
				//校验交易密码不通过
				modelView.addObject("errorCode", Constant.USER_TRADE_PWD_ERROR);
				modelView.addObject("num",n-num);
				throw new RuntimeException("校验交易密码失败！");
			}
		}catch(TradePwdFreezeException te){
			// 用户被冻结,获取剩余冻结分钟
			Date time = cusTomerServiceImpl.getTradeFreezeTime(userId);
			int t = 60;
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
			if(logger.isDebugEnabled()){
				logger.debug("购买vip异常："+e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("data",num);
		MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 更新vip时长
	 * @author  heng.wang
	 * @date    2015年11月28日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateCustVipTime")
	@ResponseBody
	public ModelAndView updateCustVipTime(BigDecimal amount,Integer month,HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		//CusTomer cusTomer = new CusTomer();
		    int day =0;//距离到期时间还有多少天
		    int count = 0;
			try {
				 String userId =super.getUserId();//获取当前登录id
				 //查当前日期到过期的时间的天数
				 day = cusTomerServiceImpl.selectVipTime(userId,month);
				 CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
				 cusTomer1.setPid(userId);
				 cusTomer1.setVipTime(day);
				 cusTomer1.setLastUpdateTime(DateUtil.format(new Date()));
				 String isVip = cusTomer1.getIsVip();//是否vip
				 String vipId = cusTomer1.getVipId();
				//查询vipInfo表的pid,如果不为空，就把客户表的vipid值更新
				 SysVipinfo sysVipinfo = sysVipinfoService.selectVipInfoPid();
				 String vipPid=sysVipinfo.getPid();
				//如果是非vip要变成vip
				if((isVip.equals("否")) && (vipId==null || "".equals(""))){
					cusTomerServiceImpl.updateIsVipByCustId(userId);
					 if(vipPid !=null && "" !=vipPid ){
						 cusTomer1.setVipId(vipPid);
					 }
					count = cusTomerServiceImpl.updateVipTime(cusTomer1);//更新vip时长和vipid
					if(count>0){
						 //购买vip时，要确保t_biz_account_common有数据，否则报错  
						//传客户id,扣款金额,交易类型，这里fkey可以是客户id,备注，当前时间
						customerAccountServiceImpl.updateAvailableAmount(userId,amount, "506", userId, "购买vip",new Date());
						//往系统账户加钱
						sysAccountService.updateAddSystemAmount(amount, userId, "1001", "", "购买vip");
						modelView.addObject("count", count);
						modelView.addObject("data", count);
					 }
				}
				//现在是vip
				else if((isVip.equals("是")) && (vipId != null || vipId !="")){
					count = cusTomerServiceImpl.updateVipTime(cusTomer1);//更新vip时长
					if(count>0){
						 //购买vip时，要确保t_biz_account_common有数据，否则报错  
						//传客户id,扣款金额,交易类型，这里fkey可以是客户id,备注，当前时间
						customerAccountServiceImpl.updateAvailableAmount(userId,amount, "506", userId, "购买vip",new Date());
						//往系统账户加钱
						sysAccountService.updateAddSystemAmount(amount, userId, "1001", "", "购买vip");
						modelView.addObject("count", count);
						modelView.addObject("data", count);
					 }
				}
				//以前是vip 现在变成非vip了
				else if((isVip.equals("否")) && (vipId !=null ||vipId !="")){
					cusTomerServiceImpl.updateIsVipByCustId(userId);//变成vip
					count = cusTomerServiceImpl.updateVipTime(cusTomer1);//更新vip时长和vipid
					if(count>0){
						 //购买vip时，要确保t_biz_account_common有数据，否则报错  
						//传客户id,扣款金额,交易类型，这里fkey可以是客户id,备注，当前时间
						customerAccountServiceImpl.updateAvailableAmount(userId,amount, "506", userId, "购买vip",new Date());
						//往系统账户加钱
						sysAccountService.updateAddSystemAmount(amount, userId, "1001", "", "购买vip");
						modelView.addObject("count", count);
						modelView.addObject("data", count);
					 }
				}
				else{
					 if(vipPid !=null && "" !=vipPid ){
						 cusTomer1.setVipId(vipPid);
					 }
					count = cusTomerServiceImpl.updateVipTime(cusTomer1);//更新vip时长和vipid
					if(count>0){
						 //购买vip时，要确保t_biz_account_common有数据，否则报错  
						//传客户id,扣款金额,交易类型，这里fkey可以是客户id,备注，当前时间
						customerAccountServiceImpl.updateAvailableAmount(userId,amount, "506", userId, "购买vip",new Date());
						//往系统账户加钱
						sysAccountService.updateAddSystemAmount(amount, userId, "1001", "", "购买vip");
						modelView.addObject("count", count);
						modelView.addObject("data", count);
					 }
				}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("更新vip时长失败：", e.getMessage());
			}
		}
		return modelView;
	}
	
	//-----------------------------------------头像信息--------------------------------------------
	/**
	 * Description：<br> 
	 * 跳转到修改我的头像信息页面
	 * @author  Lin Xu
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toAccountAvatar")
	public String toAccountAvatar(){
		return "temp.usercenter.avatar";
	}
	
	/**
	 * Description：<br> 
	 * 保留
	 * @author  Lin Xu
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/saveTemporaryAvatar")
	@ResponseBody
	public HashMap<String, Object> saveTemporaryAvatar(HttpServletRequest request , MultipartFile avatarfile){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String userid = getUserId();
			
			String httpurl = "";
			if(StringUtil.isNotEmpty(userid) && avatarfile != null){
				//1.获取保存路径
				String basepath = fileconfig.getFileRoot() + "/" + TEMPORARY_PATH;
				String filelis = getContextUser().getPhoneNo() + "_ls_original" + System.currentTimeMillis() + ".jpg";
				String filename = getContextUser().getPhoneNo() + "_original" + ".jpg";
				//保存文件
				File jfile = new File(basepath);
				if (!jfile.exists()) {
					jfile.mkdirs();
				}
				//查看上传图片是否是空，进行保存图片信息
				if (!avatarfile.isEmpty()) {
					//进行保存数据
					File localFile = new File(basepath+filelis);
					try {
						avatarfile.transferTo(localFile);
					} catch (IllegalStateException e) {
						e.printStackTrace();
						processFail(resultMap, "网络异常");
					} catch (IOException e) {
						e.printStackTrace();
						processFail(resultMap, "网络异常");
					}
				}
				//进行图片压缩
				ImageReduceUtil.compressImage(basepath+filelis,basepath+filename,400);
				//保存成功后返回前端进行图片显示
				httpurl = TEMPORARY_PATH + filename;
				processSuccess(resultMap, httpurl);
			}else{
				processFail(resultMap, "网络异常");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(resultMap, "网络异常");
		}
		return resultMap;
	}
	
	/**
	 * Description：<br> 
	 * 保存上传后的图片信息数据
	 * @author  Lin Xu
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param request
	 * @param imgleft
	 * @param imgtop
	 * @param imgwidth
	 * @param imgheight
	 * @return
	 */
	@RequestMapping(value="/saveCurrentlyAvatar",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> saveCurrentlyAvatar(HttpServletRequest request,
			Integer imgleft,Integer imgtop,Integer imgwidth,Integer imgheight  ){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String userid = getUserId();
			if(StringUtil.isNotEmpty(userid)){
				//1.获取保存路径
				String basepath = fileconfig.getFileRoot() + "/" + CURRENTLY_PATH;
				String filename = getContextUser().getPhoneNo() + "_currently_" + System.currentTimeMillis() +".jpg";
				//保存文件
				File jfile = new File(basepath);
				if (!jfile.exists()) {
					jfile.mkdirs();
				}
				//获取原始文件的路径信息
				String originalPath = fileconfig.getFileRoot() + "/" + TEMPORARY_PATH + getContextUser().getPhoneNo() + "_original" + ".jpg";
				String currentlyPath = basepath + filename;
				ImageCutHelpUtil imgctool =new ImageCutHelpUtil(originalPath, currentlyPath, imgleft, imgtop, imgwidth, imgheight);
				imgctool.cut();
				//通过cusTomerService 修改信息
				CusTomer avatarimg = new CusTomer();
				avatarimg.setPid(userid);
				avatarimg.setImageUrl(CURRENTLY_PATH + filename);
				avatarimg.setLastUpdateUser(userid);
				avatarimg.setLastUpdateTime(DateUtil.format(new Date()));
				cusTomerServiceImpl.updateByPrimaryKeySelective(avatarimg);
				//重新获取用户信息放入缓存中
				String sessionId = request.getSession().getId();
				CustomerVo user = (CustomerVo) memcachedClient.get(sessionId);
		    	//获取当前session的值如果为空，就设置信息数据
		    	if(null != user){
		    		user.setImageUrl(CURRENTLY_PATH + filename);
		    		memcachedClient.replace(sessionId, 1800, user);
		    		request.getSession().setAttribute(PtpConstants.CONSUMER, user);
		    	}
				//返回成功
				processSuccess(resultMap, "保存成功");
			}else{
				processFail(resultMap, "网络异常");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(resultMap, "网络异常");
		}finally{
			//删除上传的临时文件
			String delBasePath = fileconfig.getFileRoot() + "/"  + TEMPORARY_PATH;
			ImageReduceUtil.deleteFileAll(delBasePath,getContextUser().getCustomerName());
		}
		return resultMap;
	}
	
}


