package com.yscf.api.controller.securitycenter;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.common.ConstantApi;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.send.SendVerifySmsVo;
import com.yscf.api.vo.user.BankVo;
import com.yscf.api.vo.user.CustMessageFeedbackVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.api.vo.user.ValidateTradPwdVo;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.common.util.EncodedUtil;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.exception.TradePwdFreezeException;
import com.yscf.core.exception.TradePwdIsBlankException;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.radio.BizProgram;
import com.yscf.core.model.sms.CustMessRecord;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.Bank;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustMessageFeedback;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.radio.impl.ProgramListServiceImpl;
import com.yscf.core.service.sms.impl.CustMessRecordServiceImpl;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.core.service.user.impl.BankServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustMessageFeedbackServiceImpl;
import com.yscf.core.service.user.impl.CustPoinWaterServiceImpl;
/**
 * Description???<br> 
 * ??????????????????
 * @author  heng.wang
 * @date    2015???12???30???
 * @version v1.0.0
 */
@RequestMapping("/securitycenter/bankCardApi")
@Controller
public class BankCardApi extends EscfBaseApi{

	Logger logger = LoggerFactory.getLogger(BankCardApi.class);
	
	@Autowired
	private BankCardApi(BankServiceImpl service) {
		super(service);
	}
	
	//??????
	@Resource(name = "smsService")
	private com.yscf.core.service.sms.impl.SmsServiceImpl smsService;
	
	//????????????
	@Resource(name = "custMessRecordService")
	CustMessRecordServiceImpl cusMsgRecordService;
	
	// ????????????
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;
	
	//????????????
	@Resource(name="bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl bizBorrowDetailServiceImpl;
	
	//????????????
	@Resource(name="programListServiceImpl")
	private ProgramListServiceImpl programListServiceImpl;
	
	//????????????
    @Resource(name="custMessageFeedbackService")
	CustMessageFeedbackServiceImpl custMessageFeedbackServiceImpl;
    
   //??????vip ??????????????????
    @Resource(name="customerAccountService")
    CustomerAccountServiceImpl customerAccountServiceImpl;
    
	@Resource(name = "sysDictionaryContentService")
	private SysDictionaryContentServiceImpl sysDictionaryContentServiceImpl;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	//??????????????????
	@Resource(name="custPoinWaterServiceImpl")
	private CustPoinWaterServiceImpl custPoinWaterServiceImpl;
	
	//????????????
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;
	
	//vip??????
	@Resource(name = "sysVipinfoServiceImpl")
	private SysVipinfoServiceImpl sysVipinfoService;
	/**
	 * 
	 * Description???<br> 
	 * ???????????????
	 * @author  heng.wang
	 * @date    2015???12???30???
	 * @version v1.0.0
	 * @param transferId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBankList", method = RequestMethod.POST)
	@ResponseBody
	public String selectBankList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			BankServiceImpl service = (BankServiceImpl) getService();
			List<Bank> list = service.selectBankInfoById(userId);
			
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size();i++){
					String openAddress =list.get(i).getOpenAddress();
					if("".equals(openAddress) ||openAddress==null){
						list.get(i).setIsWithdrawals(0);
					}else{
						list.get(i).setIsWithdrawals(1);
					}
					//???????????????pid???????????????????????????  1?????? 0??????
					int count = service.selectQuickPayment(list.get(i).getPid());
					if(count>0){
						list.get(i).setQuickPayment(1);
					}else{
						list.get(i).setQuickPayment(0); 
						}
				}
				map.put("list",list);//????????????????????????belongingBank??????
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",true);
			}else{
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:????????????????????????
	 * @author  heng.wang
	 * @date    2016???1???27???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getSupportBank", method = RequestMethod.POST)
	@ResponseBody
	public String getSupportBank(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			List<SysDictionaryContent> list  = sysDictionaryContentServiceImpl.selectByDisctCode("BANK_NAME");
			map.put("bankName",list);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * ?????????????????????
	 * @author  heng.wang
	 * @date    2015???12???30???
	 * @version v1.0.0
	 * @param transferId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/addBankCard", method = RequestMethod.POST)
	@ResponseBody
	public String addBankCard(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			BankVo bankVo = (BankVo) ApiUtil.convertObjectByBody(request, BankVo.class);
			BankServiceImpl service = (BankServiceImpl) getService();
			map.put("pid", new BankVo().getPK());
			map.put("customerId", userId);
			map.put("bankcardName", bankVo.getBankcardName());
			map.put("bankcardNo", bankVo.getBankcardNo());
			map.put("belongingBank", bankVo.getBelongingBank());
			map.put("belongingProvince", bankVo.getBelongingProvince());
			map.put("openAddress", bankVo.getOpenAddress());
			int count = service.addBankInfo(map);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"?????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"?????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * ???????????????????????????????????????<br>
	 * @date    2015???12???30???
	 * @version v1.0.0
	 * @param mobiles ????????????
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendSmsCode",method = RequestMethod.POST)
	@ResponseBody
	public String sendSmsCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			// ??????tempKey????????????????????????????????????????????????
			String code =  SmsUtil.RandomNumber();
			Map<String,String> map = new HashMap<String,String>();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			map.put("{?????????}", code);
		    map.put("{?????????}", smsVo.getMobile());
			// ????????????????????????
			String result = smsService.sendSmsCode(smsVo.getMobile(), "???????????????","ADD_BANKCARD",map);//ADD_BANKCARD??????????????????
			if(!"1".equals(result)){
				// ??????code?????????memcached ???
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????????????????",false);
			}
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2015???12???30???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectMsgList", method = RequestMethod.POST)
	@ResponseBody
	public String selectMsgList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		CustMessRecord custMessRecord = new CustMessRecord();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			Integer pageNum = customerVo.getPageNum() == null ? 1 : customerVo.getPageNum();
			Integer pageSize = customerVo.getPageSize() ==null ? 10 :customerVo.getPageSize();
			pageNum = (pageNum - 1) * pageSize;
			custMessRecord.setCustomerId(userId);
			custMessRecord.setPageNum(pageNum);
			custMessRecord.setPageSize(pageSize);
			List<CustMessRecord> list = cusMsgRecordService.selectMsgListApi(custMessRecord);
			if(list.size()>0 && list!=null){
				map.put("data",list); 
				map.put("pageCount",list.size()); 
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateLoginPwd", method = RequestMethod.POST)
	@ResponseBody
	public String updateLoginPwd(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    CusTomer cusTomer = new CusTomer();
		int number=0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			boolean flag = EncodedUtil.matchPassword(cusTomer1.getPassword(), customerVo.getPassWord(), cusTomer1.getIndex());//1?????????????????? 2:????????? 3???????????????
			//??????flag=true ?????????????????????
			if(flag==true){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????",true);
				cusTomer.setPid(userId);
				CusTomer cusTomer2 = cusTomerServiceImpl.selectByPrimaryKey(userId);
				//???????????????
				String passWord= EncodedUtil.getEncodedPassword(customerVo.getNewPassWord(), cusTomer2.getIndex().toString());//1???????????? 2:????????????
				cusTomer.setPassword(passWord);
				//number>0 ????????????????????????
				number = cusTomerServiceImpl.updateLoginPassWord(cusTomer);
				if(number>0){
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
				}
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"???????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * ???????????????????????????????????????<br>
	 * @date    2015???12???30???
	 * @version v1.0.0
	 * @param mobiles ????????????
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendUpdateLoginPwdSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public String sendUpdateLoginPwdSmsCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			// ??????tempKey????????????????????????????????????????????????
			String code =  SmsUtil.RandomNumber();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			Map<String,String> map = new HashMap<String,String>();
			map.put("{?????????}", code);
			map.put("{?????????}", smsVo.getMobile());
			
			// ????????????????????????
			String result = smsService.sendSmsCode(smsVo.getMobile(), "??????????????????","REST_CODE",map);//REST_CODE ??????????????????
			if(!"1".equals(result)){
				// ??????code?????????memcached ???
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/resetLoginPassWord", method = RequestMethod.POST)
	@ResponseBody
	public String resetLoginPassWord(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		CusTomer cusTomer = new CusTomer();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			//???????????????
			String newPassWord= EncodedUtil.getEncodedPassword(customerVo.getNewPassWord(), cusTomer1.getIndex().toString());//1???????????? 2:????????????
			cusTomer.setPassword(newPassWord);
			int count = cusTomerServiceImpl.updateLoginPassWord(cusTomer);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????????????????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateLoginPassWord", method = RequestMethod.POST)
	@ResponseBody
	public String validateLoginPassWord(HttpServletRequest request,
								        HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		CusTomer cusTomer = new CusTomer();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			cusTomer.setPassword(customerVo.getPassWord());
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
//			String pwd = new String(RSAUtil.decode(cusTomer1.getPassword()));// ??????
			boolean flag = EncodedUtil.matchPassword(cusTomer1.getPassword(), customerVo.getPassWord(), cusTomer1.getIndex());//1?????????????????? 2:????????? 3???????????????
			//??????flag=true ?????????????????????
			if(flag==true){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"???????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/setTradPassWord", method = RequestMethod.POST)
	@ResponseBody
	public String setTradPassWord(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		CusTomer cusTomer = new CusTomer();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			//???????????????
			String tradePassWord= EncodedUtil.getEncodedPassword(customerVo.getTradPassWord(), cusTomer1.getIndex().toString());//1???????????? 2:????????????
			cusTomer.setTradePassword(tradePassWord);
			//?????????????????????count>0 ??????????????????????????????
			int count =cusTomerServiceImpl.setTradPassWord(cusTomer);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"????????????????????????",false);
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:?????????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validataTradPassWord", method = RequestMethod.POST)
	@ResponseBody
	public String validataTradPassWord(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map  = new HashMap<String, Object>();
		ValidateTradPwdVo validateTradPwdVo = new ValidateTradPwdVo();
		String userId = null;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			String pwd  =customerVo.getTradPassWord();
			int num = cusTomerServiceImpl.vailidateTradePassword(pwd, userId);
			if(num > -1 ){
				SysParams sp = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.TRADE_ERROR_COUNT);
				Integer n=null;
				n= Integer.parseInt(sp.getParamValue());
				n = n == null ? 4 : n;
				//???????????????????????????
				map.put("num",n-num);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"????????????????????????",false);
			}else{
				String pk = validateTradPwdVo.getPK();
				//??????????????????????????????
				memcachedClient.set(userId+ConstantApi.JFDH, ConstantApi.TRAD_PASSWORD_TIME,pk);
				map.put("key", pk);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",true);
			}
		}catch(TradePwdFreezeException te){
			// ???????????????,????????????????????????
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
			map.put("time", t);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"?????????????????????",false);
		}catch(TradePwdIsBlankException te){
			// ??????????????????
			if (logger.isDebugEnabled()) {
				logger.error(te.getMessage());
			}
			te.printStackTrace();
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????",false);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"????????????????????????",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateTradPassWord", method = RequestMethod.POST)
	@ResponseBody
	public String updateTradPassWord(HttpServletRequest request,HttpServletResponse response
									 )throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		CusTomer cusTomer = new CusTomer();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
		
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			//???????????????
			String tradePassWord= EncodedUtil.getEncodedPassword(customerVo.getTradPassWord(), cusTomer1.getIndex().toString());//1???????????? 2:????????????
			cusTomer.setTradePassword(tradePassWord);
			//??????????????????count=1 ????????????????????????
			int count =cusTomerServiceImpl.updateTradPassWord(cusTomer);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	/**
	 * 
	 * Description???<br> 
	 * ???????????????????????????????????????<br>
	 * @date    2015???12???30???
	 * @version v1.0.0
	 * @param mobiles ????????????
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/sendRestTradPwdSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public String sendRestTradPwdSmsCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			// ??????tempKey????????????????????????????????????????????????
			String code =  SmsUtil.RandomNumber();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			Map<String,String> map = new HashMap<String,String>();
			map.put("{?????????}", code);
			 map.put("{?????????}", smsVo.getMobile());
			// ????????????????????????
			String result = smsService.sendSmsCode(smsVo.getMobile(), "??????????????????","REST_TRADPWD_CODE",map);//REST_TRADPWD_CODE ??????????????????
			if(!"1".equals(result)){
				// ??????code?????????memcached ???
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????????????????",false);
			}
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * ?????????????????????????????????<br>
	 * @date    2016???1???14???
	 * @version v1.0.0
	 * @param mobiles ????????????
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/bindCustPhoneNoSmsCode", method = RequestMethod.POST)
	@ResponseBody
	public String bindCustPhoneNoSmsCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			// ??????tempKey????????????????????????????????????????????????
			String code =  SmsUtil.RandomNumber();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			Map<String,String> map = new HashMap<String,String>();
			map.put("{?????????}", code);
			map.put("{?????????}", smsVo.getMobile());
			// ????????????????????????
			String result = smsService.sendSmsCode(smsVo.getMobile(), "??????????????????","BIND_PHONE",map);//BIND_PHONE ??????????????????
			if(!"1".equals(result)){
				// ??????code?????????memcached ???
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????????????????",false);
			}
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectReferralCode", method = RequestMethod.POST)
	@ResponseBody
	public String selectReferralCode(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			List<CusTomer> list =cusTomerServiceImpl.selectReferralCode(userId);
			if(list!=null && list.size()>0){
				map.put("referralCode",list.get(0).getReferralCode());//???????????????
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:???????????????????????????
	 * @author  heng.wang
	 * @date    2016???1???2???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectMyReferralList", method = RequestMethod.POST)
	@ResponseBody
	public String selectMyReferralList(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			//??????????????????id????????????????????????
			List<CusTomer> list1 = cusTomerServiceImpl.selectMyReferralList(userId);
				if(list1!=null && list1.size()>0){
					map.put("list",list1);
					jsonObject.setResult(map);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"?????????????????????????????????",true);
				  }else{
					  processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"?????????????????????????????????",false);
				  }
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???3???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectMyRankingList", method = RequestMethod.POST)
	@ResponseBody
	public String selectMyRankingList(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			List<BizBorrowDetail> list =bizBorrowDetailServiceImpl.selectMyRankingList(userId);
			if(list!=null && list.size()>0){
				map.put("list",list);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:???????????????????????????
	 * @author  heng.wang
	 * @date    2016???1???3???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectMyRankingNumber", method = RequestMethod.POST)
	@ResponseBody
	public String selectMyRankingNumber(HttpServletRequest request,HttpServletResponse response)throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			List<BizBorrowDetail> list =bizBorrowDetailServiceImpl.selectMyRankingNumber(userId);
			if(list!=null && list.size()!=0){
				map.put("list",list.get(0).getPid());//????????????????????? ???XX???
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"?????????????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * 
	 * @author heng.wang
	 * @date 2016???1???12???
	 * @version v1.0.0
	 * @param pageNum
	 *            ?????????1
	 * @param pageSize
	 *            ?????????10
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/radioList", method = RequestMethod.POST)
	@ResponseBody
	public String radioList(
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			Integer pageNum = customerVo.getPageNum() == null ? 1 : customerVo.getPageNum();
			Integer pageSize = customerVo.getPageSize();
			pageNum = (pageNum - 1) * pageSize;
			//?????????
//			if(userId ==null || userId ==""){
//				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",false);
//			}else{
				//1??????????????????id???????????????
				/**List<BizProgram> list1 = programListServiceImpl.selectIsPraise(userId);
				if(list1!=null && list1.size()>0){
					for(int i=0;i<list1.size();i++){
						bizProgram.setIsPraise(list1.get(i).getIsPraise());
					}
					//map.put("isPraise", list1.get(0).getIsPraise());
				}**/
				//???????????????
				List<BizProgram> list = programListServiceImpl.selectRadioList(userId,pageNum,pageSize);
				if(list!=null && list.size()>0){
					map.put("list", list);
					map.put("pageCount",list.size());//???????????????
					jsonObject.setResult(map);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????????????????",true);
				}else{
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",false);
				}
//			}
			
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);	
		}
	
	
	
	/**
	 * 
	 * Description???<br> 
	 * Api:??????????????????
	 * @author  heng.wang
	 * @date    2016???1???14???  
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/bindPhone", method = RequestMethod.POST)
	@ResponseBody
	public String bindPhone(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    CusTomer cusTomer = new CusTomer();
		int number=0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			cusTomer.setPhoneNo(customerVo.getPhoneNo());
			number = cusTomerServiceImpl.bindPhone(cusTomer);
			//????????????
			if(number>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:????????????
	 * @author  heng.wang
	 * @date    2016???1???16???  
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/opinionFeedback", method = RequestMethod.POST)
	@ResponseBody
	public String opinionFeedback(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		CustMessageFeedback custMessageFeedback = new CustMessageFeedback();
		int number=0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustMessageFeedbackVo custMessageFeedbackVo = (CustMessageFeedbackVo) ApiUtil.convertObjectByBody(request, CustMessageFeedbackVo.class);
			custMessageFeedback.setPid(custMessageFeedbackVo.getPK());
			custMessageFeedback.setCustomerId(userId);
			custMessageFeedback.setSubmitTime(DateUtil.format(new Date()));
			custMessageFeedback.setSubmitContent(custMessageFeedbackVo.getSubmitContent());
			number = custMessageFeedbackServiceImpl.insertSelective(custMessageFeedback);
			//????????????
			if(number>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"??????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:???????????????
	 * @author  heng.wang
	 * @date    2016???1???16???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/isNewMessage", method = RequestMethod.POST)
	@ResponseBody
	public String isNewMessage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			int count = cusMsgRecordService.selectUnreadNumber(userId);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:?????????????????????
	 * @author  heng.wang
	 * @date    2016???1???18???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/perfectBankInfo", method = RequestMethod.POST)
	@ResponseBody
	public String perfectBankInfo(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    Bank bank = new Bank();
		int number=0;
		try {
			//token??????????????????
//			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
//			String userId = getCustomerId(xtoken);
			BankVo bankVo = (BankVo) ApiUtil.convertObjectByBody(request, BankVo.class);
			BankServiceImpl service = (BankServiceImpl) getService();
//			bank.setPid(userId);
			bank.setBankcardNo(bankVo.getBankcardNo());//???????????????
			bank.setBelongingCity(bankVo.getBelongingCity());//????????????
			bank.setOpenAddress(bankVo.getOpenAddress());//?????????
			number = service.updateBankInfoApi(bank);
			if(number>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"???????????????????????????",false);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:???????????????
	 * @author  heng.wang
	 * @date    2016???1???18???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
	@ResponseBody
	public String updateUserName(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    CusTomer cusTomer = new CusTomer();
		int number=0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			cusTomer.setCustomerName(customerVo.getCustomerName());
			CusTomer cusTomer1 =  cusTomerServiceImpl.selectByPrimaryKey(userId);
			if(cusTomer1.getCustomerName() !=null ){
				number = cusTomerServiceImpl.updateCustName(cusTomer);
				if(number>0){
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"?????????????????????",true);
				}
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",true);
			}
			
				
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:VIP??????????????????
	 * @author  heng.wang
	 * @date    2016???1???18???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getVipBuySelect", method = RequestMethod.POST)
	@ResponseBody
	public String getVipBuySelect(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			List<SysDictionaryContent> list  = sysDictionaryContentServiceImpl.selectByDisctCode("VIP_BUY");
			map.put("buySelect",list);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * Description???<br> 
	 * Api:??????vip
	 * @author  heng.wang
	 * @date    2016???1???21???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/shoppingVip", method = RequestMethod.POST)
	@ResponseBody
	public String shoppingVip(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    CusTomer cusTomer = new CusTomer();
	    HashMap<String, Object> map  = new HashMap<String, Object>();
	    int day =0;//?????????????????????????????????
	    int count = 0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
//			CusTomer cusTomer1 =  cusTomerServiceImpl.selectByPrimaryKey(userId);
			//???vip??????
//			List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(userId);
			cusTomer.setPid(userId);
			//??????????????????????????????????????????
			 day = cusTomerServiceImpl.selectVipTime(userId,customerVo.getMount());
			 cusTomer.setVipTime(day);
			 count = cusTomerServiceImpl.updateVipTime(cusTomer);
			 if(count>0){
				//?????????id,????????????,?????????????????????fkey???????????????id,?????????????????????
				customerAccountServiceImpl.updateAvailableAmount(userId, customerVo.getAmount(), "506", userId, "??????vip",new Date());
				// ????????????????????????????????????????????????????????????
				GregorianCalendar gcd = new GregorianCalendar();
				// ??????????????????
				gcd.setTime(new Date());
				// ?????????????????????
				gcd.add(Calendar.DATE, day);
				// ????????????????????????
				Date dt = gcd.getTime();
				map.put("vipExpireTime", DateUtil.format(dt,"yyyy-MM-dd"));
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????vip??????",true);
			 }else{
				 processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????vip??????",false);
			 }
			
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:????????????????????????
	 * @author  heng.wang
	 * @date    2016???1???18???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/signMessageRead", method = RequestMethod.POST)
	@ResponseBody
	public String signMessageRead(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    CusTomer cusTomer = new CusTomer();
		int number=0;
		String [] str= {""};
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			String messId = customerVo.getMessageId();
			if(messId.indexOf(',') != -1 ){
				str = messId.split(",");
			}else{
				str=messId.split(",");
			}
			//??????????????????
			number = cusMsgRecordService.signMessageRead(userId,str);
			if(number> 0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br> 
	 * Api:????????????????????????
	 * @author  heng.wang
	 * @date    2016???2???22???
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/signMessageAllRead", method = RequestMethod.POST)
	@ResponseBody
	public String signMessageAllRead(HttpServletRequest request,HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
	    CusTomer cusTomer = new CusTomer();
		int number=0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			cusTomer.setPid(userId);
			//??????????????????
			number = cusMsgRecordService.signMessageAllRead(userId);
			if(number> 0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????????????????",true);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description???<br>
	 * ??????????????????
	 * @author heng.wang
	 * @date 2016???1???25???
	 * @version v1.0.0
	 * @param pageSize
	 *            ?????????????????????7???
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/radioPopularityList", method = RequestMethod.POST)
	@ResponseBody
	public String radioPopularityList(
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map  = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			//?????????
//			if(userId ==null || userId ==""){
//				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????",false);
//			}
//		else{
				//1??????????????????id???????????????
				List<BizProgram> list1 = programListServiceImpl.selectIsPraise(userId);
				if(list1!=null && list1.size()>0){
					map.put("isPraise", list1.get(0).getIsPraise());
				}
			//???????????????
			List<BizProgram> list = programListServiceImpl.radioPopularityList();
			if(list!=null && list.size()>0){
				map.put("list", list);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"??????????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",false);
			}
//			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		    return ApiUtil.getEncryptStr(jsonObject);	
		}
	
	/**
	 * 
	 * Description???<br>
	 * ????????????
	 * @author heng.wang
	 * @date 2016???1???25???
	 * @version v1.0.0
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/radioPraiseNum", method = RequestMethod.POST)
	@ResponseBody
	public String radioPraiseNum(
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map  = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		BizProgram bizProgram = new BizProgram();
		int number = 0;
		Integer praiseNum = 0;
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			String radioId = customerVo.getRadioId();//??????id
			bizProgram.setPid(bizProgram.getPK());
			bizProgram.setCustomerId(userId);
			bizProgram.setProgramId(radioId);
			bizProgram.setProgramType("1");
			bizProgram.setIsPraise(1);
				//2??????????????????id??????????????????1(??????????????????)???
				number = programListServiceImpl.selectProgramTypeCountByRadioId(radioId);
				//??????number>0, ???????????????????????? 
				if(number>0){
					map.put("number", number);
					jsonObject.setResult(map);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????",true);
				}else{
					//1????????????????????????????????????
					int count = programListServiceImpl.insertBizProgramCust(bizProgram);
					if(count>0){
						List<BizProgram> list =  programListServiceImpl.selectPraiseNum(radioId);
						praiseNum = list.get(0).getPraiseNum();//????????????????????????
						praiseNum = praiseNum+1;
						bizProgram.setPid(radioId);
						bizProgram.setPraiseNum(praiseNum);
						programListServiceImpl.updatePraiseNum(bizProgram);
						map.put("number", number);
						jsonObject.setResult(map);
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"????????????",true);
					}
				}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		    return ApiUtil.getEncryptStr(jsonObject);	
		}
	
	/**
	 * 
	 * Description???<br>
	 * ?????????????????????
	 * @author heng.wang
	 * @date 2016???1???27???
	 * @version v1.0.0
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/radioListenAddPoint", method = RequestMethod.POST)
	@ResponseBody
	public String radioListenAddPoint(                         
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			//token??????????????????
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			//Constant.POINT_TYPE_6 ???????????????????????????  ?????????????????????????????????????????????
			int count = custPoinWaterServiceImpl.pointObtain(Constant.POINT_TYPE_6, null, userId);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"???????????????????????????",true);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		    return ApiUtil.getEncryptStr(jsonObject);	
		}
	
	
}
