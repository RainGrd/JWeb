package com.yscf.ep2p.controller.securityCenter;

import java.net.URLEncoder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EncodedUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.common.util.RSAUtil;
import com.yscf.core.model.activity.PubInvestAward;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.model.user.Bank;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustEmergencyContact;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.service.activity.impl.PubInvestAwardServiceImpl;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.ICustPoinWaterService;
import com.yscf.core.service.user.impl.BankServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustEmergencyContactServiceImpl;
import com.yscf.core.ucenter.BBSApi;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;
import com.yscf.ep2p.vo.system.CustomerVo;

/**
 * Description：<br>
 * 前端--安全中心
 * 
 * @author heng.wang
 * @date 2015年11月11日
 * @version v1.0.0
 * @param <BankServiceImpl>
 * @param <BankServiceImpl>
 */
@Controller
@RequestMapping("/securityCenter/bankController")
public class BankController extends EscfBaseWebController {// EscfBaseWebController

	private Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	public BankController(BankServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return Bank.class;
	}

	// 客户紧急联系人
	@Resource(name = "custEmergencyContactService")
	private CustEmergencyContactServiceImpl custEmergencyContactService;

	// 客户投资奖励
	@Resource(name = "pubInvestAwardServiceImpl")
	private PubInvestAwardServiceImpl pubInvestAwardServiceImpl;

	// 客户资料明细
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;

	// 客户积分明细接口
	@Resource(name = "custPoinWaterServiceImpl")
	private ICustPoinWaterService custPoinWaterServiceImpl;
	
	// vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	SysVipinfoServiceImpl sysVipinfoService;
	
	//读取系统参数
	@Resource(name="sysParamsService")
	ISysParamsService sysParamsServiceImpl;
	
	
	/*@Resource(name="getBankCardInfoUtils")
	GetBankCardInfoUtils getBankCardInfoUtils;*/
/*	@Resource(name="sendMail")
	SendMail sendMail;*/

	/**
	 * Description：<br>
	 * 跳转到添加银行卡页面
	 * 
	 * @author heng.wang
	 * @date 2015年11月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAddBankCardPage")
	@ResponseBody
	public ModelAndView toAddBankCardPage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {

		ModelAndView modelAndView = new ModelAndView("/personcenter/securitycenter/addBankCard");
		String userName = getContextUser().getCustomerName();// 获取当前登录用户名
		String userId = super.getUserId();// 获取当前登录id
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 校验是否认证
	 * 
	 * @author heng.wang
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateIsAttestation")
	@ResponseBody
	public ModelAndView validateIsAttestation(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count = 0;
		try {
			String userId = super.getUserId();// 获取当前登录id
			CusTomer cusTomer = cusTomerServiceImpl.getByUserId(userId);
			if (cusTomer.getIsAttestation().equals("2")) {
				count = 1;
			}
//			 cusTomerServiceImpl.updateHomeAddressByCusPid(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("实名认证失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存银行卡信息
	 * 
	 * @author heng.wang
	 * @date 2015年11月11日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveBankInfo")
	@ResponseBody
	public ModelAndView saveBankInfo(Bank bank, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustPoinWater custPoinWater = new CustPoinWater();
		int flag = 0;
		try {
			BankServiceImpl service = (BankServiceImpl) getService();
			String userId = super.getUserId();// 获取当前登录id
			bank.setCustomerId(userId);
			bank.setPid(bank.getPK());
			bank.setStatus("1");
			bank.setCreateTime(DateUtil.format(new Date()));
			flag = service.insert(bank);
			//添加完银行卡后往t_cust_point_water表插入一条记录
			if(flag>0){
				CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(userId);
				String isVip = cusTomer.getIsVip();
				custPoinWater.setPid(custPoinWater.getPK());// 设置主键
				custPoinWater.setStatus("1");
				
				custPoinWater.setCustomerId(userId);
				//如果是vip 积分值加20  否则加10
				if(isVip.equals("是")){
					custPoinWater.setPointValue(20);
				}else{
					custPoinWater.setPointValue(10);
				}
				custPoinWater.setHappenTime(DateUtil.format(new Date()));
				custPoinWater.setPointGetType("pointGetType_bdyhk");//积分获得类型
				custPoinWater.setPointType("pointGetType_bdyhk");//积分类型
				custPoinWater.setCreateUser(userId);
				custPoinWater.setCreateTime(DateUtil.getToday().toString());// 设置创建时间
				custPoinWater.setPotWatDesc("绑定银行卡"+custPoinWater.getPointValue());
				custPoinWaterServiceImpl.insertCustPointWater(custPoinWater);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("保存银行卡信息失败" + e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("flag", flag);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到我的银行卡列表页面
	 * 
	 * @author heng.wang
	 * @date 2015年11月254日
	 * @version v1.0.0
	 */
	@RequestMapping("/toBankList")
	public String toBankList() {
		HttpServletRequest req = getRequest();
		// 添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_SECURITY);
		return "temp.usercenter.bankList";
	}

	/**
	 * 
	 * Description：<br>
	 * 根据客户Id查询银行卡列表
	 * 
	 * @author heng.wang
	 * @date 2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBankListByCusId")
	@ResponseBody
	public ModelAndView selectBankListByCusId(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BankServiceImpl service = (BankServiceImpl) getService();
			String cusPid = super.getUserId();// 获取当前登录id
			PageList<Bank> list = service.selectBankInfoById(cusPid);
			String bankCard = "";
			String lastBankCardNo = "";
			for (int i = 0; i < list.size(); i++) {
				bankCard = list.get(i).getBankcardNo();
				if (list.get(i).getBankcardNo().length() > 4) {
					lastBankCardNo = bankCard.substring(bankCard.length() - 4);
				}
				list.get(i).setBankcardNo(lastBankCardNo);
			}
			modelView.addObject("data", list);
			modelView.addObject("count", list.size());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询银行卡信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到银行卡解绑页面
	 * 
	 * @author heng.wang
	 * @date 2015年11月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toUnbundlingBankPage")
	@ResponseBody
	public ModelAndView toUnbundlingBankPage(String bankCardNo, String pid, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/personcenter/securitycenter/toUnbundlingBankPage");
		modelAndView.addObject("bankCardNo", bankCardNo);
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 修改用户名
	 * 
	 * @author heng.wang
	 * @date 2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateUserData")
	@ResponseBody
	public ModelAndView updateUserData(String userName, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count = 0;
		try {
			CusTomer cusTomer = new CusTomer();
			String userId = super.getUserId();
			// 设置客户Id
			cusTomer.setPid(userId);
			if (!"".equals(userName) && userName != null) {
				cusTomer.setCustomerName(userName);
			}
			count = cusTomerServiceImpl.updateCusNameByCusPid(cusTomer);
			cusTomer = cusTomerServiceImpl.selectByPrimaryKey(userId);

			// 设定用户名时，将用户信息同步到论坛中
			BBSApi.reg(userName, cusTomer.getPassword(), StringUtil.isEmpty(cusTomer.getEmail()) ? "11609924@qq.com" : cusTomer.getEmail());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("修改用户名信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 修改联系地址
	 * 
	 * @author heng.wang
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateHomeAddress")
	@ResponseBody
	public ModelAndView updateHomeAddress(String homeAddress, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count = 0;
		try {
			CusTomer cusTomer = new CusTomer();
			String userId = super.getUserId();
			// 设置客户Id
			cusTomer.setPid(userId);
			if (!"".equals(homeAddress) && homeAddress != null) {
				cusTomer.setHomeAddress(homeAddress);
			}
			count = cusTomerServiceImpl.updateHomeAddressByCusPid(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("修改用户名信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 修改紧急联系人
	 * 
	 * @author heng.wang
	 * @date 2015年11月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateEmergencyContact")
	@ResponseBody
	public ModelAndView updateEmergencyContact(String emergerncyName, String relation, String emergerncyPhoneNo, String emcontPid, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count = 0;
		try {
			CustEmergencyContact custEmergencyContact = new CustEmergencyContact();
			String userId = super.getUserId();
			// 设置客户Id
			custEmergencyContact.setCustomerId(userId);
			custEmergencyContact.setName(emergerncyName);
			custEmergencyContact.setRelation(relation);
			custEmergencyContact.setPhoneNo(emergerncyPhoneNo);
			// 如果紧急联系人pid不为空，就修改，否则就新增
			if (emcontPid != null && emcontPid != "") {
				custEmergencyContact.setPid(emcontPid);
				count = custEmergencyContactService.updateEmergencyContact(custEmergencyContact);
			} else {
				custEmergencyContact.setPid(custEmergencyContact.getPK());
				count = custEmergencyContactService.insert(custEmergencyContact);
			}

			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("保存紧急联系人信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据客户ID查询原始密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectOriginalByCusPid")
	@ResponseBody
	public ModelAndView selectOriginalByCusPid(String custPid, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CustEmergencyContactServiceImpl service = (CustEmergencyContactServiceImpl) getService();
			PageList<CustEmergencyContact> list = service.selectOriginalByCusPid(custPid);
			modelView.addObject("rows", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取原始密码失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存重置后的新密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveNewPassWord")
	@ResponseBody
	public ModelAndView saveNewPassWord(CustEmergencyContact custEmergencyContact, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CustEmergencyContactServiceImpl service = (CustEmergencyContactServiceImpl) getService();
			service.saveNewPassWord(custEmergencyContact);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("修改登录密码失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 客户交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveNewTradePassWord")
	@ResponseBody
	public ModelAndView saveNewTradePassWord(CustEmergencyContact custEmergencyContact, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CustEmergencyContactServiceImpl service = (CustEmergencyContactServiceImpl) getService();
			service.saveNewTradePassWord(custEmergencyContact);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("修改交易密码失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据客户账户查上次登录时间和交易时间
	 * 
	 * @author heng.wang
	 * @date 2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectLoginTimeByAccount")
	@ResponseBody
	public ModelAndView selectLoginTimeByAccount(String acccount, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CustEmergencyContactServiceImpl service = (CustEmergencyContactServiceImpl) getService();
			PageList<CustEmergencyContact> list = service.selectLoginTimeByAccount(acccount);
			modelView.addObject("rows", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据客户账户查登录时间和交易密码失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据客户银行卡号校验交易密码是否正确
	 * 
	 * @author heng.wang
	 * @date 2015年11月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBankTradingPwdByBankCar")
	@ResponseBody
	public ModelAndView selectBankTradingPwdByBankCar(String pid, String passWord, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		Bank bank = new Bank();
		int size = 0;
		boolean flag;
		try {
			BankServiceImpl service = (BankServiceImpl) getService();
			String customerId = super.getUserId();// 获取当前登录id
			passWord = new String(RSAUtil.decode(passWord));// 解密
			CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(customerId);
			flag = EncodedUtil.matchPassword(cusTomer.getTradePassword(), passWord, cusTomer.getIndex());// 1：查出的密码
																											// 2:旧密码
																											// 3：加密因子
			bank.setCustomerId(customerId);
			bank.setPid(pid);
			// 解绑后将银行卡做逻辑删除
			if (flag == true) {
				size = service.updateBankStatus(bank);
			}
			modelView.addObject("flag", flag);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据客户银行卡号查交易密码失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("size", size);
		return modelView;
	}

	/**
	 * 
	 * @Description : 跳转到我的福利页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param welfareType
	 *            跳转类型 1.我的赠劵 2.我的积分
	 * @return 我的福利jsp页面
	 * @Author : Qing.Cai
	 * @Date : 2016年1月19日 下午3:03:11
	 */
	@RequestMapping("/toWelfareList")
	public String toWelfareList(HttpServletRequest request, HttpServletResponse response, String welfareType) {
		// 添加选中的菜单
		request.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_WELFARE);
		// 跳转类型
		request.setAttribute("welfareType", welfareType);
		//查vip等级
		List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(getUserId());
		if(null != list && list.size()>0){
		String vipLevel = list.get(0).getVipLevel();
		if(null != vipLevel || !"".equals(vipLevel)){
			String vips = "VIP"+vipLevel;
			if(!"".equals(vipLevel) && vipLevel!=null){
				request.setAttribute("vipLevel",vips);
			}else{
				request.setAttribute("vipLevel","VIP");
			}
		}
		 }
		else{
			request.setAttribute("vipLevel","VIP");
		}
		return "temp.usercenter.welfare";
	}

	/**
	 * 
	 * @Description : 获取当前登录用户的积分
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 返回个人的积分信息,可用积分and累积积分
	 * @Author : Qing.Cai
	 * @Date : 2015年12月3日 下午5:40:30
	 */
	@RequestMapping("/toMyPointList")
	public ModelAndView toMyPointList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = getUserId();// 获取当前登录id
			CustPoinWater custPoinWater = custPoinWaterServiceImpl.selectUserPoint(customerId);
			modelView.addObject("data", custPoinWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的积分失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 加载我的赠劵页面
	 * 
	 * @author heng.wang
	 * @date 2015年11月24日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectMyCoupon")
	@ResponseBody
	public HashMap<String, Object> selectMyCoupon(String flag,HttpServletRequest request,Integer pageIndex,Integer pageSize, HttpServletResponse response) {
//		ModelAndView modelView = new ModelAndView();
		// 初始化分页对象
		pageIndex = pageIndex == null ? 0 : pageIndex;
		pageSize = pageSize == null ? 12 : pageSize;
		pageIndex = pageIndex * pageSize;
		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("E_PLAN");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String customerId = super.getUserId();// 获取当前登录id
			String paramVal = sysParams.getParamValue();//获取系统参数的值   1：e计划，2：e计划/e首房
			if(flag.equals("1")){
			//查我的赠劵可使用的
			List<PubInvestAward> list = pubInvestAwardServiceImpl.selectMyCoupon(customerId,pageSize,pageIndex);
			Integer total = pubInvestAwardServiceImpl.selectMyCouponCount(customerId);
			list.get(0).setLinkType(paramVal);
			if(list.size()!=0 && list!=null){
				resultMap.put("data", list);
				resultMap.put("total", total);
				processSuccess(resultMap, "查询成功");
				}
			}else{
				//查我的赠劵已过期的
				List<PubInvestAward> list = pubInvestAwardServiceImpl.selectMyCouponExpired(customerId,pageSize,pageIndex);
//				Integer total = pubInvestAwardServiceImpl.selectMyCouponExpiredCount(customerId);
				list.get(0).setLinkType(paramVal);
				if(list.size()!=0 && list!=null){
					resultMap.put("data", list);
//					resultMap.put("total", total);
					processSuccess(resultMap, "查询成功");
					}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询我的赠劵失败：", e.getMessage());
			}
		}
		return resultMap;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到我的福利--我的积分子页面
	 * 
	 * @author heng.wang
	 * @date 2015年11月24日
	 * @version v1.0.0
	 */
	@RequestMapping("/personalData")
	public String personalData() {
		HttpServletRequest req = getRequest();
		// 添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_SECURITY);
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
		return "temp.usercenter.personal_data";
	}

	/**
	 * 
	 * Description：<br>
	 * 加载个人资料页面
	 * 
	 * @author heng.wang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectPersonData")
	@ResponseBody
	public ModelAndView selectPersonData(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = super.getUserId();// 获取当前登录id
			List<CusTomer> list = cusTomerServiceImpl.selectPersonData(customerId);
			modelView.addObject("data", list);
			modelView.addObject("emergencyEcontPid", list.get(0).getEmergencyContactPid());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询个人资料失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 设定交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月30日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateTradingPwd")
	@ResponseBody
	public ModelAndView updateTradingPwd(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/personcenter/securitycenter/toUpdateTradingPwdPage");
		return modelAndView;
	}

	/**
	 * 
	 * Description：<br>
	 * 修改交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月30日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateTradingPwdList")
	@ResponseBody
	public ModelAndView updateTradingPwdList(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/updateTradingPwdList");
		return modelView;
	}

	/**
	 * Description：<br>
	 * 修改登录密码
	 * @author heng.wang
	 * @date 2015年12月14日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/toupdateLoginPwd")
	@ResponseBody
	public ModelAndView toupdateLoginPwd(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/toUpdateLoginPwdPage");
		return modelView;
	}
	
	/**
	 * Description：<br>
	 * 绑定邮箱
	 * 
	 * @author heng.wang
	 * @date 2016年2月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBindEmail")
	@ResponseBody
	public ModelAndView toBindEmail(HttpServletRequest request, HttpServletResponse response, String isEmail) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/bindEmailIndex");
		modelView.addObject("isEmail",isEmail);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 发送邮件，到验证页面
	 * @author heng.wang
	 * @date 2016年2月25日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/toValidatePage")
	@ResponseBody
	public ModelAndView toValidatePage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/toValidatePage");
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 根据登录名验证用户是否绑定邮箱
	 * @author  heng.wang
	 * @date    2016年2月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param isEmail 判断邮箱是更新还是绑定（默认为绑定、更新为1）
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public ModelAndView sendEmail(String emailAddress, String isEmail, HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try{
			CustomerVo cv = getContextUser();
			String eamilTitle = "绑定邮箱";
			// 设置邮件参数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName",cv.getSname());
			String invalidTime = DateUtil.format(EscfDateUtil.addMinute(DateUtil.getToday(), 60));
			map.put("invalidTime",invalidTime );
			// 拼接邮件连接地址
			StringBuffer sb = new StringBuffer();
			String sbStr = "invalidTime="+invalidTime+"&emailAddress="+emailAddress;
			sbStr = EncodedUtil.getEncodedStr(sbStr,Constant.RESET_PWD_SALT);
			//String enInvalidTime = EncodedUtil.getEncodedStr(invalidTime, Constant.RESET_PWD_SALT);
			sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
			.append(request.getServerPort()).append( request.getContextPath()).append("/");
			if("1".equals(isEmail)){
				eamilTitle = "修改邮箱";
				sb.append("login/userController/openUpdateEmail.shtml?query=");
			}else{
				sb.append("login/userController/updateEmail.shtml?query=");
			}
			//.append(EncodedUtil.getEncodedStr(sbStr,Constant.RESET_PWD_SALT));
			map.put("mailHref",sb.toString()+URLEncoder.encode(sbStr, "UTF-8"));
			// 发送邮件
			super.sendMail(emailAddress,eamilTitle,"bind-email.ftl", map);
			modelView.addObject("email", emailAddress);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询原始交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年12月03日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/searcherTradingPwd")
	@ResponseBody
	public ModelAndView searcherTradingPwd(String oldPwd, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = super.getUserId();// 获取当前登录id
			CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(customerId);
			oldPwd = new String(RSAUtil.decode(oldPwd));// 解密（前台加密了）
			boolean flag = EncodedUtil.matchPassword(cusTomer.getTradePassword(), oldPwd, cusTomer.getIndex());// 1：查出的密码
																											// 2:旧密码
																											// 3：加密因子
			modelView.addObject("cusTomer", cusTomer);
			modelView.addObject("flag", flag);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询交易密码失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查询原始登录密码
	 * 
	 * @author heng.wang
	 * @date 2015年12月14日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/searcherLoginPwd")
	@ResponseBody
	public ModelAndView searcherLoginPwd(String oldPwd, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = super.getUserId();// 获取当前登录id
			CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(customerId);
			oldPwd = new String(RSAUtil.decode(oldPwd));// 解密
			boolean flag = EncodedUtil.matchPassword(cusTomer.getPassword(), oldPwd, cusTomer.getIndex());// 1：查出的密码
																											// 2:旧密码
																											// 3：加密因子
			modelView.addObject("cusTomer", cusTomer);
			modelView.addObject("flag", flag);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询交易密码失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 校验登录密码是否正确
	 * 
	 * @author heng.wang
	 * @date 2015年11月30日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateTradingPwd")
	@ResponseBody
	public ModelAndView validateTradingPwd(String loginPassWord, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		Bank bank = new Bank();
		int count = 0;
		try {
			BankServiceImpl service = (BankServiceImpl) getService();
			String cusPid = super.getUserId();// 获取当前登录id
			bank.setPid(cusPid);
			bank.setLoginPassWord(loginPassWord);
			int index = cusTomerServiceImpl.selectIndexByPid(cusPid);
			bank.setIndex(index);
			count = service.validateTradingPwd(bank);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("登录密码错误", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * @Description : 查询个人积分明细
	 * @param custPoinWater
	 *            积分明细对象
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            页数
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 个人积分明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月4日 上午10:26:25
	 */
	@RequestMapping("/selectUserPointDetail")
	public ModelAndView selectUserPointDetail(CustPoinWater custPoinWater, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			custPoinWater.setCustomerId(getUserId());// 设置客户ID
			
			// 初始化分页对象
			pageIndex = pageIndex == null ? 0 : pageIndex;
			pageSize = pageSize == null ? 10 : pageSize;
			pageIndex = pageIndex * pageSize;
			
			List<CustPoinWater> list = custPoinWaterServiceImpl.selectUserPointDetail(custPoinWater, pageIndex, pageSize);
			Integer total = custPoinWaterServiceImpl.selectUserPointDetailCount(custPoinWater);
			modelView.addObject("data", list);
			modelView.addObject("total", total);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("查询个人积分明细失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存修改后的交易密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateTradingPwdByCustId")
	@ResponseBody
	public ModelAndView updateTradingPwdByCustId(String newPwd, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		Bank bank = new Bank();
		try {
			String customerId = super.getUserId();// 获取当前登录id
			bank.setCustomerId(customerId);
			newPwd = new String(RSAUtil.decode(newPwd));// 解密
			bank.setNewPwd(newPwd);
			BankServiceImpl service = (BankServiceImpl) getService();
			int count = service.saveTradingPwd(bank);
			modelView.addObject("count", count);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("保存交易密码失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存修改后的登录密码
	 * 
	 * @author heng.wang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateLoginPwdByCustId")
	@ResponseBody
	public ModelAndView updateLoginPwdByCustId(String sureNewPwd, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		Bank bank = new Bank();
		BankServiceImpl service = (BankServiceImpl) getService();
		try {
			String customerId = super.getUserId();// 获取当前登录id
			sureNewPwd = new String(RSAUtil.decode(sureNewPwd));// 解密
			bank.setCustomerId(customerId);
			bank.setLoginPassWord(sureNewPwd);
			int count = service.updateLoginPwd(bank);
			modelView.addObject("count", count);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("保存交易密码失败：", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 查系统用户和客户是否存在此名
	 * 
	 * @author heng.wang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectUserName")
	@ResponseBody
	public ModelAndView selectUserName(String userName, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count = 0;
		try {
			BankServiceImpl service = (BankServiceImpl) getService();
			// String cusPid = super.getUserId();//获取当前登录id
			count = service.selectUserName(userName);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("登录密码错误", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 忘记登录密码
	 * 
	 * @author heng.wang
	 * @date 2015年12月18日
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/toForgetLoginPwdPage")
	public ModelAndView toForgetLoginPwdPage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/toForgetLoginPwdPage");
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据用户名获取用户信息
	 * 
	 * @author heng.wang
	 * @date 2015年12月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateUserName")
	@ResponseBody
	public ModelAndView validateUserName(String userName, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CusTomer cusTomer = new CusTomer();
		try {
			String cusPid = super.getUserId();// 获取当前登录id
			cusTomer.setPid(cusPid);
			cusTomer.setSname(userName);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByUserName(cusTomer);
			if (null == cusTomer1) {
				modelView.addObject("result", false);
			} else {
				modelView.addObject("result", true);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("验证码验证信息失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 根据key 和银行卡号  调验证银行卡信息的接口
	 * @author heng.wang
	 * @date 2015年12月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateBankCard")
	@ResponseBody
	public ModelAndView validateBankCard(String card, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int i=0;
		int j=0;
		BankServiceImpl service = (BankServiceImpl) getService();
		//获取验证银行卡信息的key值
		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("BANK_KEY");
		String key = sysParams.getParamValue();
		Bank bank = service.getByCardBank(key,card);
		String provice = bank.getBelongingProvince();//省
		String city = bank.getBelongingCity();//市
		if(!"".equals(provice) && provice!=null){
			i = provice.indexOf("省");//-1不包含
			//如果返回的不带省字，就直接设置，否则就截取
			if(i == -1){
				bank.setBelongingProvince(provice);
			}else{
				bank.setBelongingProvince(provice.substring(0, provice.length()-1));
			}
		}
		if(!"".equals(city) && city!=null){
			j = city.indexOf("市");
			if(j == -1){
				bank.setBelongingCity(city);
			}else{
				bank.setBelongingCity(city.substring(0, city.length()-1));
			}
		}
		modelView.addObject("data", bank);
		MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		return modelView;
	}
}
