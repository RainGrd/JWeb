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
 * Description：<br> 
 * 安全中心接口
 * @author  heng.wang
 * @date    2015年12月30日
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
	
	//短信
	@Resource(name = "smsService")
	private com.yscf.core.service.sms.impl.SmsServiceImpl smsService;
	
	//消息中心
	@Resource(name = "custMessRecordService")
	CustMessRecordServiceImpl cusMsgRecordService;
	
	// 客户资料
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;
	
	//投资详情
	@Resource(name="bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl bizBorrowDetailServiceImpl;
	
	//媒体列表
	@Resource(name="programListServiceImpl")
	private ProgramListServiceImpl programListServiceImpl;
	
	//意见反馈
    @Resource(name="custMessageFeedbackService")
	CustMessageFeedbackServiceImpl custMessageFeedbackServiceImpl;
    
   //购买vip 扣除账户余额
    @Resource(name="customerAccountService")
    CustomerAccountServiceImpl customerAccountServiceImpl;
    
	@Resource(name = "sysDictionaryContentService")
	private SysDictionaryContentServiceImpl sysDictionaryContentServiceImpl;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	//客户积分流水
	@Resource(name="custPoinWaterServiceImpl")
	private CustPoinWaterServiceImpl custPoinWaterServiceImpl;
	
	//系统参数
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;
	
	//vip信息
	@Resource(name = "sysVipinfoServiceImpl")
	private SysVipinfoServiceImpl sysVipinfoService;
	/**
	 * 
	 * Description：<br> 
	 * 银行卡列表
	 * @author  heng.wang
	 * @date    2015年12月30日
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
			//token封装用户信息
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
					//根据银行卡pid去查是否可快捷支付  1：是 0：否
					int count = service.selectQuickPayment(list.get(i).getPid());
					if(count>0){
						list.get(i).setQuickPayment(1);
					}else{
						list.get(i).setQuickPayment(0); 
						}
				}
				map.put("list",list);//银行卡名字取的是belongingBank字段
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询银行卡列表成功",true);
			}else{
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询银行卡列表没数据",false);
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
	 * Description：<br> 
	 * Api:查支持银行的接口
	 * @author  heng.wang
	 * @date    2016年1月27日
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
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
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
	 * Description：<br> 
	 * 添加银行卡列表
	 * @author  heng.wang
	 * @date    2015年12月30日
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
			//token封装用户信息
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
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"添加银行卡成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"添加银行卡失败",false);
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
	 * Description：<br> 
	 * 添加银行卡：发送短信验证码<br>
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param mobiles 手机号码
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
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			Map<String,String> map = new HashMap<String,String>();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			map.put("{验证码}", code);
		    map.put("{客户名}", smsVo.getMobile());
			// 调用短信发送接口
			String result = smsService.sendSmsCode(smsVo.getMobile(), "添加银行卡","ADD_BANKCARD",map);//ADD_BANKCARD短信模板名称
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"发送成功",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"发送短信超过限制次数",false);
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
	 * Description：<br> 
	 * Api:查询消息中心
	 * @author  heng.wang
	 * @date    2015年12月30日
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
			//token封装用户信息
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
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询消息中心成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"没查到消息中心的数据",false);
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
	 * Description：<br> 
	 * Api:修改登录密码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			boolean flag = EncodedUtil.matchPassword(cusTomer1.getPassword(), customerVo.getPassWord(), cusTomer1.getIndex());//1：查出的密码 2:旧密码 3：加密因子
			//如果flag=true 说明原密码正确
			if(flag==true){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"原密码正确",true);
				cusTomer.setPid(userId);
				CusTomer cusTomer2 = cusTomerServiceImpl.selectByPrimaryKey(userId);
				//把密码加密
				String passWord= EncodedUtil.getEncodedPassword(customerVo.getNewPassWord(), cusTomer2.getIndex().toString());//1：原密码 2:加密因子
				cusTomer.setPassword(passWord);
				//number>0 说明修改密码成功
				number = cusTomerServiceImpl.updateLoginPassWord(cusTomer);
				if(number>0){
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"修改登录密码成功",true);
				}
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"原密码错误",false);
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
	 * Description：<br> 
	 * 重置登录密码发送短信验证码<br>
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param mobiles 手机号码
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
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			Map<String,String> map = new HashMap<String,String>();
			map.put("{验证码}", code);
			map.put("{客户名}", smsVo.getMobile());
			
			// 调用短信发送接口
			String result = smsService.sendSmsCode(smsVo.getMobile(), "重置登录密码","REST_CODE",map);//REST_CODE 短信模板名称
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"发送成功",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"发送短信超过限制次数",false);
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
	 * Description：<br> 
	 * Api:重置登录密码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			//把密码加密
			String newPassWord= EncodedUtil.getEncodedPassword(customerVo.getNewPassWord(), cusTomer1.getIndex().toString());//1：原密码 2:加密因子
			cusTomer.setPassword(newPassWord);
			int count = cusTomerServiceImpl.updateLoginPassWord(cusTomer);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"重置登录密码成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"重置登录密码失败",false);
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
	 * Description：<br> 
	 * Api:设置交易密码之前验证登录密码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			cusTomer.setPassword(customerVo.getPassWord());
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
//			String pwd = new String(RSAUtil.decode(cusTomer1.getPassword()));// 解密
			boolean flag = EncodedUtil.matchPassword(cusTomer1.getPassword(), customerVo.getPassWord(), cusTomer1.getIndex());//1：查出的密码 2:旧密码 3：加密因子
			//如果flag=true 说明原密码正确
			if(flag==true){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"原密码正确",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"原密码错误",false);
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
	 * Description：<br> 
	 * Api:设定交易密码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			//把密码加密
			String tradePassWord= EncodedUtil.getEncodedPassword(customerVo.getTradPassWord(), cusTomer1.getIndex().toString());//1：原密码 2:加密因子
			cusTomer.setTradePassword(tradePassWord);
			//设置交易密码：count>0 说明交易密码设置成功
			int count =cusTomerServiceImpl.setTradPassWord(cusTomer);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"设置交易密码成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"设置交易密码失败",false);
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
	 * Description：<br> 
	 * Api:校验原交易密码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
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
				//校验交易密码不通过
				map.put("num",n-num);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"校验交易密码失败",false);
			}else{
				String pk = validateTradPwdVo.getPK();
				//　保存交易密码对象值
				memcachedClient.set(userId+ConstantApi.JFDH, ConstantApi.TRAD_PASSWORD_TIME,pk);
				map.put("key", pk);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"交易密码正确",true);
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
			map.put("time", t);
			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"交易密码被冻结",false);
		}catch(TradePwdIsBlankException te){
			// 交易密码为空
			if (logger.isDebugEnabled()) {
				logger.error(te.getMessage());
			}
			te.printStackTrace();
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"交易密码为空",false);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"交易密码校验失败",false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * Api:修改交易密码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
		
			CusTomer cusTomer1 = cusTomerServiceImpl.selectByPrimaryKey(userId);
			//把密码加密
			String tradePassWord= EncodedUtil.getEncodedPassword(customerVo.getTradPassWord(), cusTomer1.getIndex().toString());//1：原密码 2:加密因子
			cusTomer.setTradePassword(tradePassWord);
			//验证原密码：count=1 修改交易密码成功
			int count =cusTomerServiceImpl.updateTradPassWord(cusTomer);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"修改交易密码成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"修改交易密码失败",false);
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
	 * Description：<br> 
	 * 重置交易密码发送短信验证码<br>
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param mobiles 手机号码
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
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			Map<String,String> map = new HashMap<String,String>();
			map.put("{验证码}", code);
			 map.put("{客户名}", smsVo.getMobile());
			// 调用短信发送接口
			String result = smsService.sendSmsCode(smsVo.getMobile(), "重置交易密码","REST_TRADPWD_CODE",map);//REST_TRADPWD_CODE 短信模板名称
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"发送成功",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"发送短信超过限制次数",false);
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
	 * Description：<br> 
	 * 手机绑定发送短信验证码<br>
	 * @date    2016年1月14日
	 * @version v1.0.0
	 * @param mobiles 手机号码
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
			// 根据tempKey，查询短信模板，获取发送短信内容
			String code =  SmsUtil.RandomNumber();
			SendVerifySmsVo smsVo = (SendVerifySmsVo) ApiUtil.convertObjectByBody(request, SendVerifySmsVo.class);
			Map<String,String> map = new HashMap<String,String>();
			map.put("{验证码}", code);
			map.put("{客户名}", smsVo.getMobile());
			// 调用短信发送接口
			String result = smsService.sendSmsCode(smsVo.getMobile(), "绑定手机号码","BIND_PHONE",map);//BIND_PHONE 短信模板名称
			if(!"1".equals(result)){
				// 短信code记录在memcached 中
				smsService.addCodeToSession(smsVo.getMobile(),code);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"发送成功",true);
			}else{
				Map<String, Object> errorResult = new HashMap<String, Object>();
				errorResult.put("errorType", 1);
				jsonObject.setExtension(errorResult);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"发送短信超过限制次数",false);
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
	 * Description：<br> 
	 * Api:查我的邀请码
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			List<CusTomer> list =cusTomerServiceImpl.selectReferralCode(userId);
			if(list!=null && list.size()>0){
				map.put("referralCode",list.get(0).getReferralCode());//我的邀请码
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查我的邀请码成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"没邀请码数据",false);
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
	 * Description：<br> 
	 * Api:查我邀请的客户列表
	 * @author  heng.wang
	 * @date    2016年1月2日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			//根据推荐人的id去查我邀请的客户
			List<CusTomer> list1 = cusTomerServiceImpl.selectMyReferralList(userId);
				if(list1!=null && list1.size()>0){
					map.put("list",list1);
					jsonObject.setResult(map);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查我邀请的客户列表成功",true);
				  }else{
					  processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"没我邀请的客户列表数据",false);
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
	 * Description：<br> 
	 * Api:查投资排行版
	 * @author  heng.wang
	 * @date    2016年1月3日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			List<BizBorrowDetail> list =bizBorrowDetailServiceImpl.selectMyRankingList(userId);
			if(list!=null && list.size()>0){
				map.put("list",list);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询投资排行版成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"投资排行版无数据",false);
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
	 * Description：<br> 
	 * Api:查我在投资的排行数
	 * @author  heng.wang
	 * @date    2016年1月3日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			List<BizBorrowDetail> list =bizBorrowDetailServiceImpl.selectMyRankingNumber(userId);
			if(list!=null && list.size()!=0){
				map.put("list",list.get(0).getPid());//查我的排行位置 第XX位
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询我在投资的排行数成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"我在投资的排行数没数据",false);
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
	 * Description：<br>
	 * 媒体播放列表
	 * 
	 * @author heng.wang
	 * @date 2016年1月12日
	 * @version v1.0.0
	 * @param pageNum
	 *            页码：1
	 * @param pageSize
	 *            条数：10
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			Integer pageNum = customerVo.getPageNum() == null ? 1 : customerVo.getPageNum();
			Integer pageSize = customerVo.getPageSize();
			pageNum = (pageNum - 1) * pageSize;
			//未登录
//			if(userId ==null || userId ==""){
//				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"你还没有登录",false);
//			}else{
				//1：查根据客户id查是否点赞
				/**List<BizProgram> list1 = programListServiceImpl.selectIsPraise(userId);
				if(list1!=null && list1.size()>0){
					for(int i=0;i<list1.size();i++){
						bizProgram.setIsPraise(list1.get(i).getIsPraise());
					}
					//map.put("isPraise", list1.get(0).getIsPraise());
				}**/
				//查节目列表
				List<BizProgram> list = programListServiceImpl.selectRadioList(userId,pageNum,pageSize);
				if(list!=null && list.size()>0){
					map.put("list", list);
					map.put("pageCount",list.size());//返回总条数
					jsonObject.setResult(map);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询媒体播放列表成功",true);
				}else{
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"媒体播放列表无数据",false);
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
	 * Description：<br> 
	 * Api:绑定手机号码
	 * @author  heng.wang
	 * @date    2016年1月14日  
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			cusTomer.setPhoneNo(customerVo.getPhoneNo());
			number = cusTomerServiceImpl.bindPhone(cusTomer);
			//修改成功
			if(number>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"绑定手机成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"绑定手机失败",false);
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
	 * Description：<br> 
	 * Api:意见反馈
	 * @author  heng.wang
	 * @date    2016年1月16日  
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustMessageFeedbackVo custMessageFeedbackVo = (CustMessageFeedbackVo) ApiUtil.convertObjectByBody(request, CustMessageFeedbackVo.class);
			custMessageFeedback.setPid(custMessageFeedbackVo.getPK());
			custMessageFeedback.setCustomerId(userId);
			custMessageFeedback.setSubmitTime(DateUtil.format(new Date()));
			custMessageFeedback.setSubmitContent(custMessageFeedbackVo.getSubmitContent());
			number = custMessageFeedbackServiceImpl.insertSelective(custMessageFeedback);
			//修改成功
			if(number>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"意见反馈成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"意见反馈失败",false);
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
	 * Description：<br> 
	 * Api:是否新消息
	 * @author  heng.wang
	 * @date    2016年1月16日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			int count = cusMsgRecordService.selectUnreadNumber(userId);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"有未读新消息",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"没有未读消息",false);
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
	 * Description：<br> 
	 * Api:完善银行卡信息
	 * @author  heng.wang
	 * @date    2016年1月18日
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
			//token封装用户信息
//			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
//			String userId = getCustomerId(xtoken);
			BankVo bankVo = (BankVo) ApiUtil.convertObjectByBody(request, BankVo.class);
			BankServiceImpl service = (BankServiceImpl) getService();
//			bank.setPid(userId);
			bank.setBankcardNo(bankVo.getBankcardNo());//银行卡卡号
			bank.setBelongingCity(bankVo.getBelongingCity());//开户城市
			bank.setOpenAddress(bankVo.getOpenAddress());//开户行
			number = service.updateBankInfoApi(bank);
			if(number>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"完善银行卡信息成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"完善银行卡信息失败",false);
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
	 * Description：<br> 
	 * Api:修改用户名
	 * @author  heng.wang
	 * @date    2016年1月18日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			cusTomer.setPid(userId);
			cusTomer.setCustomerName(customerVo.getCustomerName());
			CusTomer cusTomer1 =  cusTomerServiceImpl.selectByPrimaryKey(userId);
			if(cusTomer1.getCustomerName() !=null ){
				number = cusTomerServiceImpl.updateCustName(cusTomer);
				if(number>0){
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"设置用户名成功",true);
				}
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"用户名只能设置一次",true);
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
	 * Description：<br> 
	 * Api:VIP购买期限选择
	 * @author  heng.wang
	 * @date    2016年1月18日
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
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询成功",true);
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
	 * Description：<br> 
	 * Api:购买vip
	 * @author  heng.wang
	 * @date    2016年1月21日
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
	    int day =0;//距离到期时间还有多少天
	    int count = 0;
		try {
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
//			CusTomer cusTomer1 =  cusTomerServiceImpl.selectByPrimaryKey(userId);
			//查vip等级
//			List<SysVipinfo> list = sysVipinfoService.selectVipLevelById(userId);
			cusTomer.setPid(userId);
			//查当前日期到过期的时间的天数
			 day = cusTomerServiceImpl.selectVipTime(userId,customerVo.getMount());
			 cusTomer.setVipTime(day);
			 count = cusTomerServiceImpl.updateVipTime(cusTomer);
			 if(count>0){
				//传客户id,扣款金额,交易类型，这里fkey可以是客户id,备注，当前时间
				customerAccountServiceImpl.updateAvailableAmount(userId, customerVo.getAmount(), "506", userId, "购买vip",new Date());
				// 计算出当前时间加上几个月后的天数是多少天
				GregorianCalendar gcd = new GregorianCalendar();
				// 获取当前时间
				gcd.setTime(new Date());
				// 添加原来的日期
				gcd.add(Calendar.DATE, day);
				// 获取购买后的日期
				Date dt = gcd.getTime();
				map.put("vipExpireTime", DateUtil.format(dt,"yyyy-MM-dd"));
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"购买vip成功",true);
			 }else{
				 processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"购买vip失败",false);
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
	 * Description：<br> 
	 * Api:标记消息单条已读
	 * @author  heng.wang
	 * @date    2016年1月18日
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
			//token封装用户信息
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
			//标记消息已读
			number = cusMsgRecordService.signMessageRead(userId,str);
			if(number> 0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"标记消息已读成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"标记消息已读失败",true);
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
	 * Description：<br> 
	 * Api:标记消息全部已读
	 * @author  heng.wang
	 * @date    2016年2月22日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			cusTomer.setPid(userId);
			//标记消息已读
			number = cusMsgRecordService.signMessageAllRead(userId);
			if(number> 0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"消息已标识为全部已读",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"消息全部已读失败",true);
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
	 * Description：<br>
	 * 电台人气列表
	 * @author heng.wang
	 * @date 2016年1月25日
	 * @version v1.0.0
	 * @param pageSize
	 *            查收听数最高的7条
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			//未登录
//			if(userId ==null || userId ==""){
//				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"你还没有登录",false);
//			}
//		else{
				//1：查根据客户id查是否点赞
				List<BizProgram> list1 = programListServiceImpl.selectIsPraise(userId);
				if(list1!=null && list1.size()>0){
					map.put("isPraise", list1.get(0).getIsPraise());
				}
			//查节目列表
			List<BizProgram> list = programListServiceImpl.radioPopularityList();
			if(list!=null && list.size()>0){
				map.put("list", list);
				jsonObject.setResult(map);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询人气播放列表成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"人气播放列表无数据",false);
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
	 * Description：<br>
	 * 电台点赞
	 * @author heng.wang
	 * @date 2016年1月25日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			CustomerVo customerVo = (CustomerVo) ApiUtil.convertObjectByBody(request, CustomerVo.class);
			String radioId = customerVo.getRadioId();//节目id
			bizProgram.setPid(bizProgram.getPK());
			bizProgram.setCustomerId(userId);
			bizProgram.setProgramId(radioId);
			bizProgram.setProgramType("1");
			bizProgram.setIsPraise(1);
				//2：查根据客户id查节目类型为1(代表的是点赞)的
				number = programListServiceImpl.selectProgramTypeCountByRadioId(radioId);
				//如果number>0, 就说明已经点赞了 
				if(number>0){
					map.put("number", number);
					jsonObject.setResult(map);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"你已经点赞",true);
				}else{
					//1：往节目客户关系表插数据
					int count = programListServiceImpl.insertBizProgramCust(bizProgram);
					if(count>0){
						List<BizProgram> list =  programListServiceImpl.selectPraiseNum(radioId);
						praiseNum = list.get(0).getPraiseNum();//查出之前的点赞数
						praiseNum = praiseNum+1;
						bizProgram.setPid(radioId);
						bizProgram.setPraiseNum(praiseNum);
						programListServiceImpl.updatePraiseNum(bizProgram);
						map.put("number", number);
						jsonObject.setResult(map);
						processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"点赞成功",true);
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
	 * Description：<br>
	 * 电台收听加积分
	 * @author heng.wang
	 * @date 2016年1月27日
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
			//token封装用户信息
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			String userId = getCustomerId(xtoken);
			//Constant.POINT_TYPE_6 积分类型：电台收听  加多少积分是在系统参数里面配的
			int count = custPoinWaterServiceImpl.pointObtain(Constant.POINT_TYPE_6, null, userId);
			if(count>0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"电台收听加积分成功",true);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"电台收听加积分失败",true);
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
