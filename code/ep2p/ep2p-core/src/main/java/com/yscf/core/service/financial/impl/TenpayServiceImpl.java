/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 财付通支付接口实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月24日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.financial.recharge.PayConfig;

/**
 * Description：<br> 
 * 财付通支付接口实现
 * @author  Jie.Zou
 * @date    2015年11月24日
 * @version v1.0.0
 */
@Service("tenpayServiceImpl")
public class TenpayServiceImpl extends BaseRechargeOnlinePlatform  {
	
	/**
	 * 财付通充值的表单参数中需要还有
	 */
	public static final String PARAM_CLIENT_IP = "tranIP";
	
	/**
	 * 财付通前台通知的URL，其值为"/recharge/tenpayReturn/doPost.shtml"
	 */
	String TEN_PAY_RETURN_DO = "/recharge/tenpayReturn/" + Constant.SERVLET_POSTFIX ; 
	
	/**
	 * 财付通后台通知的URL，其值为"/recharge/tenpayNotify/doPost.shtml"
	 */
	String TEN_PAY_NOTIFY_DO = "/recharge/tenpayNotify/" + Constant.SERVLET_POSTFIX;

	@Override
	public String getRechargeURL(String paySystemKey) {
		return "https://gw.tenpay.com/gateway/pay.htm";
	}
	
	@Override
	public String wrapOrderId(BizRechargeOnline recharge, String mid) {
		return String.format("%s-%s-%s", DateUtil.format(DateUtil.format(recharge.getCreateTime(),"yyyyMMdd"), "yyyyMMdd"),mid,recharge.getRecOrderNo());
	}
	
	@Override
	public String resolveRechargeOnlineId(Map<String, String> feedbackData,
			String paySystemType) {
		String orderId = feedbackData.get("out_trade_no");
		List<String> tokens = this.getTokens(orderId, "-",String.class);
		if(tokens.size() >= 3){
			return tokens.get(2);
		}
		return null;
	}
	
	@Override
	public Map<String, String> getFormParams(BizRechargeOnline recharge,
			PayConfig payConfig, Map<String, String> params) {
		String webRoot = params.get(Constant.WEB_ROOT);
		String return_url = webRoot+TEN_PAY_RETURN_DO;//前台页面通知地址
		String notify_url = webRoot+TEN_PAY_NOTIFY_DO;//后台通知地址
		String mid = payConfig.getMerchantId();
		BigDecimal amountBy100 = recharge.getAmount().multiply(BigDecimal.valueOf(100)).setScale(0,RoundingMode.HALF_UP);
		String ip = params.get(PARAM_CLIENT_IP).toString();
		
		mid = "1219657001";
		
		Map<String, String> formParams = new HashMap<String, String>();
		formParams.put("body", "yscf");
		formParams.put("return_url", return_url);
		formParams.put("notify_url", notify_url);
		formParams.put("partner", mid);
		formParams.put("out_trade_no", this.wrapOrderId(recharge, mid));
		formParams.put("total_fee", amountBy100.toString());
		formParams.put("fee_type", "1");
		formParams.put("spbill_create_ip", ip);
		formParams.put("sign", this.sign4recharge(formParams, payConfig));
		return formParams;
	}
	
	@Override
	public boolean isRechargeStateSuccess(Map<String, String> feedbackData) {
		return "0".equals(feedbackData.get("trade_state"));
	}
	
	@Override
	public String sign4recharge(Map<String, String> params, PayConfig payConfig) {
		StringBuilder prestr = createLinkString(params);
		String vkey = payConfig.getSignKdy();
		vkey = "8d1c9e1bd5a9f42fe21911203426bf91";
		String finalStr = prestr.append("&key=").append(vkey).toString();
		return this.md5(finalStr, "GBK");
	}

	@Override
	public String sign4notify(Map<String, String> params, PayConfig payConfig) {
		String vkey = payConfig.getSignKdy();
		StringBuilder prestr = createLinkString(params);
		String finalStr = prestr.append("&key=").append(vkey).toString();
		return this.md5(finalStr, "GBK");
	}
	
	@Override
	public boolean checkFeedbackSign(Map<String, String> feedbackData,
			PayConfig payConfig) {
		String signFeedback = feedbackData.get("sign");
		String signCalculate = this.sign4notify(feedbackData, payConfig);
		return signCalculate.toUpperCase().equals(signFeedback);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 把数组所有元素排序，并按照"参数=参数值"的模式用"&"字符拼接成字符串
	 * @author  Jie.Zou
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return
	 */
	private StringBuilder createLinkString(Map<String,String> params){
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		
		StringBuilder prestr = new StringBuilder();
		String key = "";
		String value = "";
		for (int i = 0; i < keys.size(); i++) {
			key = (String)keys.get(i);
			value = (String)params.get(key);
			if("".equals(value) || value == null || key.equalsIgnoreCase("sign")){
				continue;
			}
			prestr.append(key).append("=").append(value).append("&");
		}
		return  prestr.deleteCharAt(prestr.length()-1);
	}
	
	public <T> List<T> getTokens(String str,String splitor,Class<T> clazz){
		List<T> target = new ArrayList<T>();
		if(StringUtils.hasText(str)){
			String[] data = str.split(splitor);
			for (String d : data) {
				if(StringUtils.hasText(d)){
					T token = getToken(d, clazz);
					if(token != null){
						target.add(token);
					}
				}
			}
		}
		return target;
	}
	
	public <T> List<T> getTokens(String str,Class<T> clazz){
		return getTokens(str,";" ,clazz);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 从一个数组构造一个token串
	 * @author  Jie.Zou
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param data 数组
	 * @param splitor 分隔符
	 * @return
	 */
	public <T> String getTokens(List<T> data,String splitor){
		String target = "";
		for (T t : data) {
			target += String.valueOf(t)+splitor;
		}
		return target;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getToken(String data,Class<T> clazz){
		try {
			if(String.class.equals(clazz))									return (T)data;
			if(Integer.class.equals(clazz) && this.isInt(data))	return (T)Integer.valueOf(data);
			if(Long.class.equals(clazz) && this.isLong(data))	return (T)Long.valueOf(data);
			return null;
		} catch (Exception e) {
			throw new RuntimeException("字符串里有类型不匹配的元素, data=" 
					+ data + ", clazz=" + Long.class);
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 判断是否为Int型
	 * @author  Jie.Zou
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param valStr
	 * @return
	 */
	public boolean isInt(String valStr){
		try {
			if(StringUtils.hasText(valStr)){
				Integer.parseInt(valStr.trim());
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 判断是否为长整型Long
	 * @author  Jie.Zou
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param valStr
	 * @return
	 */
	public boolean isLong(String valStr){
		try {
			if(StringUtils.hasText(valStr)){
				Long.parseLong(valStr);
				return true;
			}
		} catch (Exception e) {}
		return false;
	}
	
	public String md5(String text,String charSet){
		return DigestUtils.md5Hex(getContentBytes(text, charSet));
	}
	
	private static byte[] getContentBytes(String content,String charset){
		if(charset == null || "".equals(charset)){
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("签名过程中出现错误，指定的编码集不对，您目前指定的编码集是："+charset);
		}
	}

}


 