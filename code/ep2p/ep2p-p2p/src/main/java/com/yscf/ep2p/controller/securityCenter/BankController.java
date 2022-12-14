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
 * Description???<br>
 * ??????--????????????
 * 
 * @author heng.wang
 * @date 2015???11???11???
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

	// ?????????????????????
	@Resource(name = "custEmergencyContactService")
	private CustEmergencyContactServiceImpl custEmergencyContactService;

	// ??????????????????
	@Resource(name = "pubInvestAwardServiceImpl")
	private PubInvestAwardServiceImpl pubInvestAwardServiceImpl;

	// ??????????????????
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;

	// ????????????????????????
	@Resource(name = "custPoinWaterServiceImpl")
	private ICustPoinWaterService custPoinWaterServiceImpl;
	
	// vip??????
	@Resource(name = "sysVipinfoServiceImpl")
	SysVipinfoServiceImpl sysVipinfoService;
	
	//??????????????????
	@Resource(name="sysParamsService")
	ISysParamsService sysParamsServiceImpl;
	
	
	/*@Resource(name="getBankCardInfoUtils")
	GetBankCardInfoUtils getBankCardInfoUtils;*/
/*	@Resource(name="sendMail")
	SendMail sendMail;*/

	/**
	 * Description???<br>
	 * ??????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???11???
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
		String userName = getContextUser().getCustomerName();// ???????????????????????????
		String userId = super.getUserId();// ??????????????????id
		modelAndView.addObject("userName", userName);
		modelAndView.addObject("userId", userId);
		return modelAndView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???12???8???
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
			String userId = super.getUserId();// ??????????????????id
			CusTomer cusTomer = cusTomerServiceImpl.getByUserId(userId);
			if (cusTomer.getIsAttestation().equals("2")) {
				count = 1;
			}
//			 cusTomerServiceImpl.updateHomeAddressByCusPid(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("??????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ?????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???11???
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
			String userId = super.getUserId();// ??????????????????id
			bank.setCustomerId(userId);
			bank.setPid(bank.getPK());
			bank.setStatus("1");
			bank.setCreateTime(DateUtil.format(new Date()));
			flag = service.insert(bank);
			//????????????????????????t_cust_point_water?????????????????????
			if(flag>0){
				CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(userId);
				String isVip = cusTomer.getIsVip();
				custPoinWater.setPid(custPoinWater.getPK());// ????????????
				custPoinWater.setStatus("1");
				
				custPoinWater.setCustomerId(userId);
				//?????????vip ????????????20  ?????????10
				if(isVip.equals("???")){
					custPoinWater.setPointValue(20);
				}else{
					custPoinWater.setPointValue(10);
				}
				custPoinWater.setHappenTime(DateUtil.format(new Date()));
				custPoinWater.setPointGetType("pointGetType_bdyhk");//??????????????????
				custPoinWater.setPointType("pointGetType_bdyhk");//????????????
				custPoinWater.setCreateUser(userId);
				custPoinWater.setCreateTime(DateUtil.getToday().toString());// ??????????????????
				custPoinWater.setPotWatDesc("???????????????"+custPoinWater.getPointValue());
				custPoinWaterServiceImpl.insertCustPointWater(custPoinWater);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????" + e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("flag", flag);
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ????????????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???254???
	 * @version v1.0.0
	 */
	@RequestMapping("/toBankList")
	public String toBankList() {
		HttpServletRequest req = getRequest();
		// ?????????????????????
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_SECURITY);
		return "temp.usercenter.bankList";
	}

	/**
	 * 
	 * Description???<br>
	 * ????????????Id?????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???16???
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
			String cusPid = super.getUserId();// ??????????????????id
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
				logger.info("???????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???25???
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
	 * Description???<br>
	 * ???????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???13???
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
			// ????????????Id
			cusTomer.setPid(userId);
			if (!"".equals(userName) && userName != null) {
				cusTomer.setCustomerName(userName);
			}
			count = cusTomerServiceImpl.updateCusNameByCusPid(cusTomer);
			cusTomer = cusTomerServiceImpl.selectByPrimaryKey(userId);

			// ??????????????????????????????????????????????????????
			BBSApi.reg(userName, cusTomer.getPassword(), StringUtil.isEmpty(cusTomer.getEmail()) ? "11609924@qq.com" : cusTomer.getEmail());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("???????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???12???8???
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
			// ????????????Id
			cusTomer.setPid(userId);
			if (!"".equals(homeAddress) && homeAddress != null) {
				cusTomer.setHomeAddress(homeAddress);
			}
			count = cusTomerServiceImpl.updateHomeAddressByCusPid(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("???????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ?????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???12???
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
			// ????????????Id
			custEmergencyContact.setCustomerId(userId);
			custEmergencyContact.setName(emergerncyName);
			custEmergencyContact.setRelation(relation);
			custEmergencyContact.setPhoneNo(emergerncyPhoneNo);
			// ?????????????????????pid???????????????????????????????????????
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
				logger.info("?????????????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ????????????ID??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???13???
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
				logger.info("??????PID????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ???????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???13???
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
				logger.info("????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???13???
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
				logger.info("????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???16???
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
				logger.info("??????????????????????????????????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???16???
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
			String customerId = super.getUserId();// ??????????????????id
			passWord = new String(RSAUtil.decode(passWord));// ??????
			CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(customerId);
			flag = EncodedUtil.matchPassword(cusTomer.getTradePassword(), passWord, cusTomer.getIndex());// 1??????????????????
																											// 2:?????????
																											// 3???????????????
			bank.setCustomerId(customerId);
			bank.setPid(pid);
			// ????????????????????????????????????
			if (flag == true) {
				size = service.updateBankStatus(bank);
			}
			modelView.addObject("flag", flag);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("?????????????????????????????????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("size", size);
		return modelView;
	}

	/**
	 * 
	 * @Description : ???????????????????????????
	 * @param request
	 *            HttpServletRequest ??????
	 * @param response
	 *            HttpServletResponse ??????
	 * @param welfareType
	 *            ???????????? 1.???????????? 2.????????????
	 * @return ????????????jsp??????
	 * @Author : Qing.Cai
	 * @Date : 2016???1???19??? ??????3:03:11
	 */
	@RequestMapping("/toWelfareList")
	public String toWelfareList(HttpServletRequest request, HttpServletResponse response, String welfareType) {
		// ?????????????????????
		request.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_WELFARE);
		// ????????????
		request.setAttribute("welfareType", welfareType);
		//???vip??????
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
	 * @Description : ?????????????????????????????????
	 * @param request
	 *            HttpServletRequest ??????
	 * @param response
	 *            HttpServletResponse ??????
	 * @return ???????????????????????????,????????????and????????????
	 * @Author : Qing.Cai
	 * @Date : 2015???12???3??? ??????5:40:30
	 */
	@RequestMapping("/toMyPointList")
	public ModelAndView toMyPointList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = getUserId();// ??????????????????id
			CustPoinWater custPoinWater = custPoinWaterServiceImpl.selectUserPoint(customerId);
			modelView.addObject("data", custPoinWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???24???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectMyCoupon")
	@ResponseBody
	public HashMap<String, Object> selectMyCoupon(String flag,HttpServletRequest request,Integer pageIndex,Integer pageSize, HttpServletResponse response) {
//		ModelAndView modelView = new ModelAndView();
		// ?????????????????????
		pageIndex = pageIndex == null ? 0 : pageIndex;
		pageSize = pageSize == null ? 12 : pageSize;
		pageIndex = pageIndex * pageSize;
		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("E_PLAN");
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String customerId = super.getUserId();// ??????????????????id
			String paramVal = sysParams.getParamValue();//????????????????????????   1???e?????????2???e??????/e??????
			if(flag.equals("1")){
			//???????????????????????????
			List<PubInvestAward> list = pubInvestAwardServiceImpl.selectMyCoupon(customerId,pageSize,pageIndex);
			Integer total = pubInvestAwardServiceImpl.selectMyCouponCount(customerId);
			list.get(0).setLinkType(paramVal);
			if(list.size()!=0 && list!=null){
				resultMap.put("data", list);
				resultMap.put("total", total);
				processSuccess(resultMap, "????????????");
				}
			}else{
				//???????????????????????????
				List<PubInvestAward> list = pubInvestAwardServiceImpl.selectMyCouponExpired(customerId,pageSize,pageIndex);
//				Integer total = pubInvestAwardServiceImpl.selectMyCouponExpiredCount(customerId);
				list.get(0).setLinkType(paramVal);
				if(list.size()!=0 && list!=null){
					resultMap.put("data", list);
//					resultMap.put("total", total);
					processSuccess(resultMap, "????????????");
					}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return resultMap;
	}

	/**
	 * 
	 * Description???<br>
	 * ?????????????????????--?????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???24???
	 * @version v1.0.0
	 */
	@RequestMapping("/personalData")
	public String personalData() {
		HttpServletRequest req = getRequest();
		// ?????????????????????
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_SECURITY);
		//???vip??????
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
	 * Description???<br>
	 * ????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???28???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectPersonData")
	@ResponseBody
	public ModelAndView selectPersonData(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = super.getUserId();// ??????????????????id
			List<CusTomer> list = cusTomerServiceImpl.selectPersonData(customerId);
			modelView.addObject("data", list);
			modelView.addObject("emergencyEcontPid", list.get(0).getEmergencyContactPid());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???30???
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
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???30???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateTradingPwdList")
	@ResponseBody
	public ModelAndView updateTradingPwdList(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/updateTradingPwdList");
		return modelView;
	}

	/**
	 * Description???<br>
	 * ??????????????????
	 * @author heng.wang
	 * @date 2015???12???14???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/toupdateLoginPwd")
	@ResponseBody
	public ModelAndView toupdateLoginPwd(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/toUpdateLoginPwdPage");
		return modelView;
	}
	
	/**
	 * Description???<br>
	 * ????????????
	 * 
	 * @author heng.wang
	 * @date 2016???2???25???
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
	 * Description???<br>
	 * ??????????????????????????????
	 * @author heng.wang
	 * @date 2016???2???25???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/toValidatePage")
	@ResponseBody
	public ModelAndView toValidatePage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/toValidatePage");
		return modelView;
	}
	
	/**
	 * Description???<br> 
	 * ?????????????????????????????????????????????
	 * @author  heng.wang
	 * @date    2016???2???26???
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param isEmail ???????????????????????????????????????????????????????????????1???
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
			String eamilTitle = "????????????";
			// ??????????????????
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName",cv.getSname());
			String invalidTime = DateUtil.format(EscfDateUtil.addMinute(DateUtil.getToday(), 60));
			map.put("invalidTime",invalidTime );
			// ????????????????????????
			StringBuffer sb = new StringBuffer();
			String sbStr = "invalidTime="+invalidTime+"&emailAddress="+emailAddress;
			sbStr = EncodedUtil.getEncodedStr(sbStr,Constant.RESET_PWD_SALT);
			//String enInvalidTime = EncodedUtil.getEncodedStr(invalidTime, Constant.RESET_PWD_SALT);
			sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
			.append(request.getServerPort()).append( request.getContextPath()).append("/");
			if("1".equals(isEmail)){
				eamilTitle = "????????????";
				sb.append("login/userController/openUpdateEmail.shtml?query=");
			}else{
				sb.append("login/userController/updateEmail.shtml?query=");
			}
			//.append(EncodedUtil.getEncodedStr(sbStr,Constant.RESET_PWD_SALT));
			map.put("mailHref",sb.toString()+URLEncoder.encode(sbStr, "UTF-8"));
			// ????????????
			super.sendMail(emailAddress,eamilTitle,"bind-email.ftl", map);
			modelView.addObject("email", emailAddress);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e){
			if (logger.isDebugEnabled()) {
				logger.info("???????????????????????????", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???12???03???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/searcherTradingPwd")
	@ResponseBody
	public ModelAndView searcherTradingPwd(String oldPwd, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = super.getUserId();// ??????????????????id
			CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(customerId);
			oldPwd = new String(RSAUtil.decode(oldPwd));// ???????????????????????????
			boolean flag = EncodedUtil.matchPassword(cusTomer.getTradePassword(), oldPwd, cusTomer.getIndex());// 1??????????????????
																											// 2:?????????
																											// 3???????????????
			modelView.addObject("cusTomer", cusTomer);
			modelView.addObject("flag", flag);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???12???14???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/searcherLoginPwd")
	@ResponseBody
	public ModelAndView searcherLoginPwd(String oldPwd, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String customerId = super.getUserId();// ??????????????????id
			CusTomer cusTomer = cusTomerServiceImpl.selectByPrimaryKey(customerId);
			oldPwd = new String(RSAUtil.decode(oldPwd));// ??????
			boolean flag = EncodedUtil.matchPassword(cusTomer.getPassword(), oldPwd, cusTomer.getIndex());// 1??????????????????
																											// 2:?????????
																											// 3???????????????
			modelView.addObject("cusTomer", cusTomer);
			modelView.addObject("flag", flag);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???30???
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
			String cusPid = super.getUserId();// ??????????????????id
			bank.setPid(cusPid);
			bank.setLoginPassWord(loginPassWord);
			int index = cusTomerServiceImpl.selectIndexByPid(cusPid);
			bank.setIndex(index);
			count = service.validateTradingPwd(bank);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("??????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * @Description : ????????????????????????
	 * @param custPoinWater
	 *            ??????????????????
	 * @param pageIndex
	 *            ??????
	 * @param pageSize
	 *            ??????
	 * @param request
	 *            HttpServletRequest ??????
	 * @param response
	 *            HttpServletResponse ??????
	 * @return ????????????????????????
	 * @Author : Qing.Cai
	 * @Date : 2015???12???4??? ??????10:26:25
	 */
	@RequestMapping("/selectUserPointDetail")
	public ModelAndView selectUserPointDetail(CustPoinWater custPoinWater, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		try {
			custPoinWater.setCustomerId(getUserId());// ????????????ID
			
			// ?????????????????????
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
				logger.debug("?????????????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???28???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateTradingPwdByCustId")
	@ResponseBody
	public ModelAndView updateTradingPwdByCustId(String newPwd, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		Bank bank = new Bank();
		try {
			String customerId = super.getUserId();// ??????????????????id
			bank.setCustomerId(customerId);
			newPwd = new String(RSAUtil.decode(newPwd));// ??????
			bank.setNewPwd(newPwd);
			BankServiceImpl service = (BankServiceImpl) getService();
			int count = service.saveTradingPwd(bank);
			modelView.addObject("count", count);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???28???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/updateLoginPwdByCustId")
	@ResponseBody
	public ModelAndView updateLoginPwdByCustId(String sureNewPwd, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelView = new ModelAndView();
		Bank bank = new Bank();
		BankServiceImpl service = (BankServiceImpl) getService();
		try {
			String customerId = super.getUserId();// ??????????????????id
			sureNewPwd = new String(RSAUtil.decode(sureNewPwd));// ??????
			bank.setCustomerId(customerId);
			bank.setLoginPassWord(sureNewPwd);
			int count = service.updateLoginPwd(bank);
			modelView.addObject("count", count);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("???????????????????????????", e.getMessage());
			}
		}
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???11???28???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/selectUserName")
	@ResponseBody
	public ModelAndView selectUserName(String userName, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		int count = 0;
		try {
			BankServiceImpl service = (BankServiceImpl) getService();
			// String cusPid = super.getUserId();//??????????????????id
			count = service.selectUserName(userName);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("??????????????????", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		modelView.addObject("count", count);
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???12???18???
	 * @version v1.0.0
	 */
	@RequestMapping(value = "/toForgetLoginPwdPage")
	public ModelAndView toForgetLoginPwdPage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/personcenter/securitycenter/toForgetLoginPwdPage");
		return modelView;
	}

	/**
	 * 
	 * Description???<br>
	 * ?????????????????????????????????
	 * 
	 * @author heng.wang
	 * @date 2015???12???18???
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
			String cusPid = super.getUserId();// ??????????????????id
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
				logger.info("???????????????????????????", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description???<br>
	 * ??????key ???????????????  ?????????????????????????????????
	 * @author heng.wang
	 * @date 2015???12???18???
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
		//??????????????????????????????key???
		SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("BANK_KEY");
		String key = sysParams.getParamValue();
		Bank bank = service.getByCardBank(key,card);
		String provice = bank.getBelongingProvince();//???
		String city = bank.getBelongingCity();//???
		if(!"".equals(provice) && provice!=null){
			i = provice.indexOf("???");//-1?????????
			//???????????????????????????????????????????????????????????????
			if(i == -1){
				bank.setBelongingProvince(provice);
			}else{
				bank.setBelongingProvince(provice.substring(0, provice.length()-1));
			}
		}
		if(!"".equals(city) && city!=null){
			j = city.indexOf("???");
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
