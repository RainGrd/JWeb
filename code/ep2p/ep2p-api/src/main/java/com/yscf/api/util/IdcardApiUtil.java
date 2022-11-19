/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月14日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName : IdcardApiUtil
 * @Description : 外部接口调用工具类
 * @Author : Qing.Cai
 * @Date : 2016年1月27日 上午11:07:47
 */
public class IdcardApiUtil {

	private static Logger logger = LoggerFactory.getLogger(IdcardApiUtil.class);

	/** 实名认证接口 */
	private final static String IDCARDINFO_SMRZ = "https://api.tongdun.cn/identification/realname/v1?partner_code=yscf&partner_key=ff42ddfbba1645a689c5b1961ba86888";

	/**
	 * 
	 * @Description : 实名认证
	 * @param identificationNo
	 *            身份证号码
	 * @param sname
	 *            真实姓名
	 * @return 返回结果 JSON 字符串
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 上午11:12:34
	 */
	public static String checkIdcardinfo(String identificationNo, String sname) {
		String resultStr = null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id_number", identificationNo);// 设置身份证号码
		params.put("name", sname);// 设置身份证姓名
		try {
			URL urlpost = new URL(IDCARDINFO_SMRZ);
			// 组织请求参数 
            StringBuilder postBody = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null) continue;
                postBody.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(),"utf-8")).append("&");
            }
            // 创建URLConnection
			HttpURLConnection conn = (HttpURLConnection) urlpost.openConnection();
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.getOutputStream().write(postBody.toString().getBytes());
			conn.getOutputStream().flush();
			// 获取返回信息
			resultStr = inputStream2String(conn.getInputStream(),"ISO-8859-1");
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("实名认证异常:" + e.getMessage());
			}
			e.printStackTrace();
		}
		return resultStr;
	}

	/**
	 * Description：<br>
	 * inputStream转string信息
	 * 
	 * @author Lin Xu
	 * @date 2016年1月8日
	 * @version v1.0.0
	 * @param is
	 * @param charset
	 * @return
	 */
	private static String inputStream2String(InputStream is, String charset) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
			return baos.toString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
		}
		return null;
	}
}
