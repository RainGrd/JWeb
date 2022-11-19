/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 消息记录服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.sms.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.sms.SmsRecordsModelMapper;
import com.yscf.core.emay.service.SmsSDKClient;
import com.yscf.core.model.sms.SmsRecordsCustomer;
import com.yscf.core.model.sms.SmsRecordsModel;
import com.yscf.core.model.system.SysSmsTemplates;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.sms.ISmsService;
import com.yscf.core.service.system.ISysSmsTemplatesService;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;

/**
 * 
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年12月28日
 * @version v1.0.0
 */
@Service("smsService")
public class SmsServiceImpl extends BaseService<BaseEntity, String> implements ISmsService {
	
	private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

	
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;	// 客户信息
	
	@Resource(name = "smsRecordsService")
	private SmsRecordsServiceImpl smsRecordsService;
	
	@Resource(name = "sysSmsTemplatesService")
	private ISysSmsTemplatesService sysSmsTemplatesService;
	
	@Resource(name="smsRecordsCustomerService")
	private SmsRecordsCustomerServiceImpl smsRecordsCustomerService;
	
	@Resource(name="sysDictionaryContentService")
	private SysDictionaryContentServiceImpl sysDictionaryContentService;
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Autowired
	public SmsServiceImpl(SmsRecordsModelMapper dao) { 
		super(dao);
	}

	/**
	 * 
	 * Description：<br> 
	 * 发送短信
	 * @author  Yu.Zhang
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param mobiles	手机号码
	 * @param model		所属模块
	 * @param tempKey	短信模板key
	 * @param map		短信模板需要替换的内容值
	 * @return  1 超过限制条数 2 发送成功
	 */
	public String sendSmsCode(String mobiles, String model,String tempKey,Map<String,String> map) {
		String result = "";
		try {
			model= new String(model.getBytes("ISO8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// 获取系统参数配置
		Map<String, String> disctMap = sysDictionaryContentService.selectByDisctCodeMap(Constant.SMS_INFO);
		
		// 根据手机号码查询当天发送条数
		Integer count = smsRecordsService.selectTodayCountByMobiles(mobiles);
		String userId = null;
		Integer limitCount = 0;
		
		// 根据手机号码查询客户信息
		CusTomer customer = cusTomerService.selectByLoginName(mobiles);
		// 判断当前用户是否已经注册，根据是否注册判断是否已经超过允许发送的条数
		if(null == customer){
			// 未注册
			limitCount = Integer.parseInt(disctMap.get(Constant.NO_REGISTER));
		}else{
			// 已注册
			 limitCount = Integer.parseInt(disctMap.get(Constant.REGISTERED));
			 userId  = customer.getPid();
		}
		
		if(count > limitCount){
			// 超过限制次数，返回前台
			 result = "1";
		}else{
			result = sendSms(mobiles, model, tempKey, map, userId);
		}
		return result;
	}

	/**
	 * 
	 * Description：<br> 
	 * 发送短信，不需要验证发送短信条数
	 * @author  Yu.Zhang
	 * @date    2016年2月24日
	 * @version v1.0.0
	 * @param mobiles	手机号码
	 * @param model		所属模块
	 * @param tempKey	短信模板key
	 * @param map		短信模板需要替换的内容值
	 * @param userId   操作人
	 * @return  发送成功
	 */
	public String sendSms(String mobiles, String model, String tempKey,
			Map<String, String> map, String userId) {
		
		// 根据tempKey，查询短信模板，获取发送短信内容
		SysSmsTemplates sysSmsTemplate = sysSmsTemplatesService.selectByTempCode(tempKey);
		String content = sysSmsTemplate.getSmsContent();
		
		// 内容替换
		if(null!= map && map.size() > 0){
			for (Entry<String, String> entry : map.entrySet()) {  
				content = content.replace(entry.getKey(), entry.getValue());
			}
		}
		
		// 发送短信
		SmsSDKClient.sendEmay(1, mobiles, content, null, null, 5);
		
		try{
			// 记录日志
			SmsRecordsModel srm = new SmsRecordsModel();
			srm.setPid(srm.getPK());
			srm.setPushModel(model);
			srm.setPhoneNo(mobiles);
			srm.setSmsCode(Constant.IDENTIFYING_CODE);
			srm.setSmsContext(content);
			srm.setCreateTime(DateUtil.format(DateUtil.getToday()));
			srm.setCreateUser(userId);
		    srm.setPushType(Constant.SMS_PUSH_TYPE_1);
		    smsRecordsService.insert(srm);
			 //新增发送的人员消息记录
			if(null!=userId){
				SmsRecordsCustomer srdcm = new SmsRecordsCustomer();
				srdcm.setPid(srdcm.getPK());
				srdcm.setSmsRecordId(srm.getPid());
				srdcm.setCustomerId(userId);
				//进行插入人员发送消息信息
				smsRecordsCustomerService.insert(srdcm);
			}
		}catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("发送短信验证码日志记录失败："+e.getMessage());
			}
		}
		return "2";
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 验证短信验证码是否正确
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param mobiles
	 * @param code
	 * @return String  1 验证码已过期 2 验证码错误 3 验证通过
	 */
	public String validateCode(String mobiles, String code) {
		String result = "1"; // 1 验证码已过期 2 验证码错误 3 验证通过
		// 将生成的验证码保存至session中，替换原来的
		String sessionCode;
		try {
			sessionCode = memcachedClient.get(mobiles);
			if(null!=sessionCode && !"".equals(sessionCode)){
				// 从数据字典中获取过期时间
				Map<String, String> disctMap = sysDictionaryContentService.selectByDisctCodeMap(Constant.SMS_INFO);
				String invalidTime =disctMap.get("INVALID_TIME");	// 数据字典中配置的过期时长
				String mobileCodeTime = sessionCode.split(",")[1];	// session中发送验证码时的时间
				String mobileCode = sessionCode.split(",")[0];		// session中记录的验证码
				
				// 根据系统当前时间与发送验证码时的时间增加配置的过期时长 验证验证码是否已经过期
				if(EscfDateUtil.dateCompare(EscfDateUtil.addMinute(mobileCodeTime, Integer.parseInt(invalidTime)),DateUtil.getToday() )){
					// 未过期对比验证码是否一致
					if(code.equalsIgnoreCase(mobileCode)){
						// 一致则从session中删除验证码
						memcachedClient.delete(mobiles);
						result = "3"; 
					}else{
						// 验证码错误
						result = "2";
					}
				}else{
					// 过期返回前台提示
					 result = "1";
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("短信验证码验证失败："+e.getMessage());
			}
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 发送语音验证码
	 * 	1 从session中获取已发送的验证码<br>

		2 判断验证码是否已过期<br>
		
		3 未过期，则发送语音验证码<br>

	 * @author  Yu.Zhang
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param mobiles
	 * @return
	 */
	public String sendScheduledSMS(String mobiles){
		String result = "";	// 1 验证码已过期
		try {
			String sessionCode = memcachedClient.get(mobiles);
			if(null!=sessionCode && !"".equals(sessionCode)){
				// 从数据字典中获取过期时间
				Map<String, String> disctMap = sysDictionaryContentService.selectByDisctCodeMap(Constant.SMS_INFO);
				String invalidTime =disctMap.get("INVALID_TIME");	// 数据字典中配置的过期时长
				String mobileCodeTime = sessionCode.split(",")[1];	// session中发送验证码时的时间
				String mobileCode = sessionCode.split(",")[0];		// session中记录的验证码
				
				// 根据系统当前时间与发送验证码时的时间增加配置的过期时长 验证验证码是否已经过期
				if(EscfDateUtil.dateCompare(EscfDateUtil.addMinute(mobileCodeTime, Integer.parseInt(invalidTime)),DateUtil.getToday() )){
					// 未过期发送语音验证码
					SmsSDKClient.sendEmay(3, mobiles, null, mobileCode, null, 5);
					result = "2";
				}else{
					// 过期返回前台提示
					 result = "1";
				}
			}else{
				// session 中不存在验证码
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 
	 * Description：<br> 
	 * 将验证码保存至memcached中
	 * @author  Yu.Zhang
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param mobiles
	 * @param code
	 */
	public void addCodeToSession(String mobiles, String code) {
		try {
			memcachedClient.delete(mobiles);
			memcachedClient.add(mobiles, 900, code+","+DateUtil.format(DateUtil.getToday()));
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("验证码保存至memcached中失败："+e.getMessage());
			}
		}
	}

}


