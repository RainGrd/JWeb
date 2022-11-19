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
package com.yscf.core.service.sms;

import java.util.Map;

/**
 * Description：<br> 
 * 消息记录服务接口
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
public interface ISmsService {

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
	String sendSmsCode(String mobiles, String model, String tempKey,Map<String,String> map);

	/**
	 * 
	 * Description：<br> 
	 * 验证短信验证码是否正确
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param mobiles
	 * @param code
	 * @return 1 验证码已过期 2 验证码错误 3 验证通过
	 */
	String validateCode(String mobiles, String code);
	
	/**
	 * 
	 * Description：<br> 
	 * 将短信验证码添加至session中
	 * @author  Yu.Zhang
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param mobiles
	 * @param code
	 */
	void addCodeToSession(String mobiles, String code);

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
	String sendScheduledSMS(String mobiles);
	
}


