/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * APi工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月25日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.encrty.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.achievo.framework.context.SpringApplicationContext;
import com.achievo.framework.json.gson.GsonUtil;
import com.achievo.framework.util.SecurityUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.google.gson.Gson;
import com.yscf.api.vo.system.FileConfig;

/**
 * Description：<br>
 * APi工具类
 * 
 * @author Lin Xu
 * @date 2015年12月25日
 * @version v1.0.0
 */
public class ApiUtil {

	private static Logger logger = Logger.getLogger(ApiUtil.class);
	// 静态变量名
	private final static String XTOKEN_VAL = "xtoken";
	private final static String CUSTOMER_VAL = "customer";

	/**
	 * Description：<br>
	 * 获取加密后的APP请求参数
	 * 
	 * @author Lin Xu
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param robj
	 * @return
	 */
	public static String getEncryptStr(JsonObject jsonobject) {
		String regson = null;
		Gson gson = new Gson();
		String gsonObj = gson.toJson(jsonobject);
		if (StringUtil.isNotEmpty(gsonObj)) {
			FileConfig config = (FileConfig) SpringApplicationContext
					.getBean("fileConfig");
			regson = gzipCompress(SecurityUtil.encrypt(config.getEncryptKey(),
					gsonObj));
		}
		return regson;
	}

	/**
	 * Description：<br>
	 * 获取加密后的APP请求参数
	 * 
	 * @author Lin Xu
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param robj
	 * @return
	 */
	public static String getEncryptStr(Object object) {
		String regson = null;
		Gson gson = new Gson();
		String gsonObj = gson.toJson(object);
		if (StringUtil.isNotEmpty(gsonObj)) {
			FileConfig config = (FileConfig) SpringApplicationContext
					.getBean("fileConfig");
			regson = gzipCompress(SecurityUtil.encrypt(config.getEncryptKey(),
					gsonObj));
		}
		return regson;
	}

	/**
	 * Description：<br>
	 * 获取头部信息的gson数据
	 * 
	 * @author Lin Xu
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	public static String getHeadXtokenDecryptStr(HttpServletRequest request) {
		String reStr = null;
		// 获取头部信息中的关键信息
		String xtoken = request.getHeader(XTOKEN_VAL);
		if (StringUtil.isNotEmpty(xtoken)) {
			FileConfig config = (FileConfig) SpringApplicationContext
					.getBean("fileConfig");
			reStr = SecurityUtil.decrypt(config.getEncryptKey(), xtoken);
		}
		return reStr;
	}

	/**
	 * Description：<br>
	 * 获取头部信息中的用户信息
	 * 
	 * @author Lin Xu
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	public static String getHeadCustomerDecryptStr(HttpServletRequest request) {
		String reStr = null;
		// 获取头部信息中的关键信息
		String customer = request.getHeader(CUSTOMER_VAL);
		if (StringUtil.isNotEmpty(customer)) {
			FileConfig config = (FileConfig) SpringApplicationContext
					.getBean("fileConfig");
			reStr = SecurityUtil.decrypt(config.getEncryptKey(), customer);
		}
		return reStr;
	}

	/**
	 * Description: 获取解密后的APP请求参数
	 * 
	 * @author Lin Xu
	 * @date 2015年5月26日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	public static String getBodyDecryptStr(HttpServletRequest request) {
		String decStr = null;
		try {
			String secStr = ApiUtil.readRequestBody(request);
			FileConfig config = (FileConfig) SpringApplicationContext
					.getBean("fileConfig");
			String gzipuncomStr = gzipUnCompress(secStr);
			decStr = SecurityUtil.decrypt(config.getEncryptKey(), gzipuncomStr);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("获取请求数据出错：", e);
			}
			e.printStackTrace();
		}
		return decStr;
	}

	/**
	 * Description: 读取APP请求的参数
	 * 
	 * @author Lin Xu
	 * @date 2015年5月27日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	public static String readRequestBody(HttpServletRequest request) {
		InputStream is = null;
		try {
			request.setCharacterEncoding("ISO-8859-1");
			is = request.getInputStream();
			int contentLen = request.getContentLength();
			if (contentLen > 0) {
				int readLen = 0;
				int curLen = 0;
				byte[] messages = new byte[contentLen];
				while (readLen != contentLen) {
					curLen = is.read(messages, readLen, contentLen - readLen);
					if (curLen == -1) {
						break;
					}
					readLen += curLen;
				}
				return new String(messages, "ISO-8859-1");
			}
		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					if (logger.isDebugEnabled()) {
						logger.debug(e.getMessage());
					}

					e.printStackTrace();
				}
			}
		}
		return "";
	}

	/**
	 * 
	 * Description：<br>
	 * 转成对应的java对象，主要用户解析API接口参数 注意：明文必须是JSON格式的字符串
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月5日
	 * @version v1.0.0
	 * @param decryptStr
	 *            明文字符 如：{"id":"1","name":"小杰","age":"20"}
	 * @param clazz
	 *            将要转成的对象
	 * @return
	 */
	public static Object convertObjectByDecryptStr(String decryptStr,
			Class<?> clazz) {

		return GsonUtil.create().fromJson(decryptStr, clazz);
	}

	/**
	 * 
	 * Description：<br>
	 * 转成对应的java对象，主要用户解析API接口参数 注意：明文必须是JSON格式的字符串
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月5日
	 * @version v1.0.0
	 * @param decryptStr
	 *            明文字符 如：{"id":"1","name":"小杰","age":"20"}
	 * @param clazz
	 *            将要转成的对象
	 * @return
	 */
	public static Object convertObjectByBody(HttpServletRequest request,
			Class<?> clazz) {

		return GsonUtil.create().fromJson(getBodyDecryptStr(request), clazz);
	}

	/**
	 * 
	 * Description：<br>
	 * 将密文解析后，转成对应的java对象，主要用户解析API接口参数 注意：密文必须是JSON格式通过加密后的字符串，否则转换会失败 如：
	 * 加密前：{"id":"1","name":"小杰","age":"20"} 加密后：
	 * e3d519b6a30d28d966b8f3ce69fe9570847c261d665c2d6320859e2235ca2403962b0d57a176aa7c
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月5日
	 * @version v1.0.0
	 * @param encryptStr
	 *            密文：
	 *            e3d519b6a30d28d966b8f3ce69fe9570847c261d665c2d6320859e2235ca2403962b0d57a176aa7c
	 * @param clazz
	 *            将要转成的对象
	 * @return
	 */
	public static Object convertObjectByEncryptStr(String encryptStr,
			Class<?> clazz) {
		Gson gson = GsonUtil.create();
		FileConfig config = (FileConfig) SpringApplicationContext
				.getBean("fileConfig");
		return gson
				.fromJson(SecurityUtil.decrypt(config.getEncryptKey(),
						encryptStr), clazz);
	}

	/**
	 * Description：<br>
	 * 将一个字符串按照gzip方式压缩
	 * 
	 * @author Lin Xu
	 * @date 2016年1月7日
	 * @version v1.0.0
	 * @param comstr
	 * @return
	 */
	public static String gzipCompress(String comstr) {
		String reStr = "";
		ByteArrayOutputStream out = null;
		GZIPOutputStream gzip = null;
		try {
			if (comstr == null || comstr.length() == 0) {
				reStr = comstr;
			} else {
				out = new ByteArrayOutputStream();
				gzip = new GZIPOutputStream(out);
				gzip.write(comstr.getBytes());
				gzip.close();
				reStr = out.toString("ISO-8859-1");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
					out.close();
				}
				if (null != gzip) {
					gzip.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reStr;
	}

	/**
	 * Description：<br>
	 * 将一个字符串进行GZIP进行解压
	 * 
	 * @author Lin Xu
	 * @date 2016年1月7日
	 * @version v1.0.0
	 * @param uncomstr
	 * @return
	 */
	public static String gzipUnCompress(String uncomstr) {
		String reStr = "";
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		GZIPInputStream gunzip = null;
		try {
			if (uncomstr == null || uncomstr.length() == 0) {
				reStr = uncomstr;
			} else {
				out = new ByteArrayOutputStream();
				in = new ByteArrayInputStream(uncomstr.getBytes("ISO-8859-1"));
				gunzip = new GZIPInputStream(in);
				byte[] buffer = new byte[256];
				int n;
				while ((n = gunzip.read(buffer)) >= 0) {
					out.write(buffer, 0, n);
				}
				// toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
				reStr = out.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
					out.close();
				}
				if (null != in) {
					in.close();
				}
				if (null != gunzip) {
					gunzip.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reStr;
	}

	public static void main(String[] args) {
		String demo = "@#$%^&*()_aslf案发生快乐的发神经了地方叫阿斯顿路口附近阿斯顿了分开算拉克丝大卷发了速/o(￣▽￣)d︿(￣︶￣)︿(∩_∩)(∩_∩)O(∩_∩)O哈哈~<(＾－fashdkasfkjas双方均按时~~~^_^~~~~~~^_^~~~~~~^_^~~~~~~^_^~~~~~~^_^~~~~~~^_^~~~O(∩_∩)O嗯!O(∩_∩)O嗯!O(∩_∩)O嗯!O(∩_∩)O嗯!O(∩_∩)O嗯!O(∩_∩)O嗯!<(＾－＾)><(＾－＾)><(＾－＾)><(＾－＾)><(＾－＾)><(＾－＾)><(＾－＾)><(￣︶￣)><(￣︶￣)><(￣︶￣)><(￣︶￣)><(￣︶￣)><(￣︶￣)>(＾－＾)V(＾－＾)VO(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的O(∩_∩)O好的付款拉斯的＾)>b(￣▽￣)d︿(￣︶￣)︿︿(￣︶￣)︿O(∩_∩)O好的(*＾-＾*)(*＾-＾*)O(∩_∩)O好的";
		System.out.println("原始长度：" + demo.length());
		System.out.println("压缩的字符：" + gzipCompress(demo));
		System.out.println("压缩的长度：" + gzipCompress(demo).length());
		String undemo = gzipCompress(demo);
		System.out.println("解压的字符：" + gzipUnCompress(undemo));
		System.out.println("解压的长度：" + gzipUnCompress(undemo).length());
	}
}
