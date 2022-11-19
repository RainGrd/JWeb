package com.yscf.core.emay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * Description：<br> 
 * 发送短信接口类
 * @author  Yu.Zhang
 * @date    2015年12月15日
 * @version v1.0.0
 */
public class SmsSDKClient {
	
	private static Logger logger = LoggerFactory.getLogger(SmsSDKClient.class);
	
	/**
	 * 
	 * Description：<br> 
	 * 发送手机短信
	 * @author  Yu.Zhang
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @param sendType 发送类型 1 及时短信 2 定时短信 3 语音信息
	 * @param mobiles 手机号码 格式为：18219528321,15233031254  手机数组长度不能超过1000
	 * @param content  最多500个汉字或1000个纯英文 请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * @param voiceContent 语音验证码(最多不要超过6个字符，最少不要小于4个字符;字符必须为0至9的全英文半角数字字符)
	 * @param sendTime 定时短信发送时间 定时时间格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机（非定时发送不用设置）
	 * @param smsPriority 优先级范围1~5，数值越高优先级越高(相对于同一序列号)
	 */
	public static void sendEmay(Integer sendType,String mobiles,String content,String voiceContent,String sendTime,Integer smsPriority){
		String [] mobilesArr = mobiles.split(",");
		switch (sendType) {
		case 1:
			sendSMS(mobilesArr,content,smsPriority);
			break;
		case 2:
			sendScheduledSMS(mobilesArr,sendTime,content);
			break;
		case 3:
			sendVoice(mobilesArr,voiceContent,smsPriority);
			break;
		default:
			break;
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 发送手机短信
	 * @author  Yu.Zhang
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @param sendType 发送类型 1 及时短信 2 定时短信 3 语音信息
	 * @param mobiles 手机号码 格式为：18219528321,15233031254  手机数组长度不能超过1000
	 * @param content  最多500个汉字或1000个纯英文 请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * @param voiceContent 语音验证码(最多不要超过6个字符，最少不要小于4个字符;字符必须为0至9的全英文半角数字字符)
	 * @param sendTime 定时短信发送时间 定时时间格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机（非定时发送不用设置）
	 * @param smsPriority 优先级范围1~5，数值越高优先级越高(相对于同一序列号)
	 */
	public static void saveLog(Integer sendType,String mobiles,String content,String voiceContent,String sendTime,Integer smsPriority){
		
	}

	/**
	 * 发送短信、可以发送定时和即时短信 sendSMS(String[] mobiles,String smsContent, String，int smsPriority) 
	 * 1、mobiles 手机数组长度不能超过1000 
	 * 2、smsContent 最多500个汉字或1000个纯英文 请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * 3、优先级范围1~5，数值越高优先级越高(相对于同一序列号) 
	 */
	private static void sendSMS(String [] mobiles,String smsContent,Integer smsPriority) {
		try {
			int i = SingletonClient.getClient().sendSMS(mobiles, smsContent, "",smsPriority);// 带扩展码
			if(logger.isDebugEnabled()){
				logger.debug("手机号码 ："+mobiles.toString()+" 发送的即时短信结果为："+i);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("手机号码 ："+mobiles.toString()+" 发送的即时短信异常："+e.getMessage());
			}
		}
	}

	/**
	 * 发送定时短信 sendScheduledSMSEx(String[] mobiles, String smsContent,String
	 * sendTime,String srcCharset) 
	 * 1、mobiles 手机数组长度不能超过1000 
	 * 2、smsContent 最多500个汉字或1000个纯英文 请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
	 * 3、sendTime 定时短信发送时间 定时时间格式为：年年年年月月日日时时分分秒秒，例如20090801123030 表示2009年8月1日12点30分30秒该条短信会发送到用户手机 
	 */
	private static void sendScheduledSMS(String [] mobiles, String smsContent,String sendTime) {
		try {
			int i = SingletonClient.getClient().sendScheduledSMSEx(mobiles,smsContent, sendTime);
			if(logger.isDebugEnabled()){
				logger.debug("手机号码 ："+mobiles.toString()+" 发送的定时短信结果为："+i);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("手机号码 ："+mobiles.toString()+" 发送的定时短信异常："+e.getMessage());
			}
		}
	}

	/**
		mobiles	手机号码(字符串数组,最多为200个手机号码) 通常实际应用中只用到了单号码语音验证码,即采用单一手机号码发送
		checkCode	语音验证码(最多不要超过6个字符，最少不要小于4个字符;字符必须为0至9的全英文半角数字字符)
		smsPriority	语音验证码等级，范围1~5，数值越高优先级越高
	*/

	private static void sendVoice(String [] mobiles,String checkCode,Integer smsPriority) {
		try {
			String value = SingletonClient.getClient().sendVoice(mobiles, checkCode, "", smsPriority, System.currentTimeMillis());
			if(logger.isDebugEnabled()){
				logger.debug("手机号码 ："+mobiles.toString()+" 发送的语音短信结果为："+value);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("手机号码 ："+mobiles.toString()+" 发送的语音短信异常："+e.getMessage());
			}
		}
	}
	
}
