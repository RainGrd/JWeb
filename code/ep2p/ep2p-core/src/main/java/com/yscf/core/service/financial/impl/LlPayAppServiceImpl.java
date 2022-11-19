/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 连连支付APP充值接口实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月16日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.util.DateUtil;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.financial.recharge.PayConfig;
import com.yscf.core.service.financial.impl.llpay.enums.SignTypeEnum;
import com.yscf.core.service.financial.impl.llpay.security.Md5Algorithm;
import com.yscf.core.service.financial.impl.llpay.security.TraderRSAUtil;


/**
 * Description：<br> 
 * 连连支付APP充值接口实现
 * @author  Jie.Zou
 * @date    2016年1月15日
 * @version v1.0.0
 */
@Service("llPayAppServiceImpl")
public class LlPayAppServiceImpl extends BaseRechargeOnlinePlatform {
	
	@Override
	public String wrapOrderId(BizRechargeOnline recharge, String mid) {
		return String.format("%s-%s-%s", DateUtil.format(DateUtil.format(recharge.getCreateTime(),"yyyyMMdd"), "yyyyMMdd"),mid,recharge.getRecOrderNo());
	}
	
	@Override
	public String resolveRechargeOnlineId(Map<String, String> feedbackData,
			String paySystemType) {
		String orderId = feedbackData.get("no_order");
		List<String> tokens = this.getTokens(orderId, "-",String.class);
		if(tokens.size() >= 3){
			return tokens.get(2);
		}
		return null;
	}
	
	@Override
	public boolean isRechargeStateSuccess(Map<String, String> feedbackData) {
		return "SUCCESS".equals(feedbackData.get("result_pay"));
	}
	
	@Override
	public String sign4notify(Map<String, String> params, PayConfig payConfig) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
		for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);
            if ("sign".equals(key))
            {
                continue;
            }
            String value = params.get(key);
            // 空串不参与签名
            if (!StringUtils.hasText(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
	}
	
	@Override
	public boolean checkFeedbackSign(Map<String, String> feedbackData,
			PayConfig payConfig) {
		String sign_type = feedbackData.get("sign_type");
		String md5_key = PartnerConfig.MD5_KEY;
		String rsa_public = PartnerConfig.YT_PUB_KEY;
		if (SignTypeEnum.MD5.getCode().equals(sign_type))
        {
            return checkSignMD5(feedbackData, md5_key);
        } else
        {
            return checkSignRSA(feedbackData, rsa_public);
        }
		
	}
	
	/**
	 * 
	 * Description：<br> 
	 * MD5签名验证O
	 * @author  Jie.Zou
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @param reqObj
	 * @param md5_key
	 * @return
	 */
	private boolean checkSignMD5(Map<String, String> feedbackData, String md5_key)
    {
		if (feedbackData == null)
        {
            return false;
        }
		//TODO
		feedbackData.put("no_order", "YSCF2016022316022010120");
        System.out.println("进入商户[" + feedbackData.get("oid_partner")
                + "]MD5签名验证");
        String sign = feedbackData.get("sign");
        // 生成待签名串
        String sign_src = sign4notify(feedbackData, null);
        System.out.println("商户[" + feedbackData.get("oid_partner") + "]待签名原串"
                + sign_src);
        System.out.println("商户[" + feedbackData.get("oid_partner") + "]签名串"
                + sign);
        sign_src += "&key=" + md5_key;
        try
        {
            if (sign.equals(Md5Algorithm.getInstance().md5Digest(
                    sign_src.getBytes("utf-8"))))
            {
                System.out.println("商户[" + feedbackData.get("oid_partner")
                        + "]MD5签名验证通过");
                return true;
            } else
            {
                System.out.println("商户[" + feedbackData.get("oid_partner")
                        + "]MD5签名验证未通过");
                return false;
            }
        } catch (UnsupportedEncodingException e)
        {
            System.out.println("商户[" + feedbackData.get("oid_partner")
                    + "]MD5签名验证异常" + e.getMessage());
            return false;
        }
    }
	
	/**
	 * 
	 * Description：<br> 
	 * @author  Jie.Zou
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @param reqObj
	 * @param rsa_public
	 * @return
	 */
	private boolean checkSignRSA(Map<String, String> feedbackData, String rsa_public)
    {

		if (feedbackData == null)
        {
            return false;
        }
		//TODO
		feedbackData.put("no_order", "2013051500001");
        System.out.println("进入商户[" + feedbackData.get("oid_partner")
                + "]RSA签名验证");
        String sign = feedbackData.get("sign");
        // 生成待签名串
        String sign_src = sign4notify(feedbackData, null);
        System.out.println("商户[" + feedbackData.get("oid_partner") + "]待签名原串"
                + sign_src);
        System.out.println("商户[" + feedbackData.get("oid_partner") + "]签名串"
                + sign);
        try
        {
            if (TraderRSAUtil.checksign(rsa_public, sign_src, sign))
            {
                System.out.println("商户[" + feedbackData.get("oid_partner")
                        + "]RSA签名验证通过");
                return true;
            } else
            {
                System.out.println("商户[" + feedbackData.get("oid_partner")
                        + "]RSA签名验证未通过");
                return false;
            }
        } catch (Exception e)
        {
            System.out.println("商户[" + feedbackData.get("oid_partner")
                    + "]RSA签名验证异常" + e.getMessage());
            return false;
        }
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

	@Override
	public String sign4recharge(Map<String, String> params, PayConfig payConfig) {
		return null;
	}

	@Override
	public String getRechargeURL(String paySystemKey) {
		return null;
	}

	@Override
	public Map<String, String> getFormParams(BizRechargeOnline recharge,
			PayConfig payConfig, Map<String, String> params) {
		return null;
	}
}


