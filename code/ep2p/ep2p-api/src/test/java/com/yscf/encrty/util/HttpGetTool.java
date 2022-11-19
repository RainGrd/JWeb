/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试Get请求UTIL工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月3日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.encrty.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.achievo.framework.util.SecurityUtil;
import com.achievo.framework.util.StringUtil;

/**
 * Description：<br> 
 * 测试Get请求UTIL工具类
 * @author  Lin Xu
 * @date    2016年1月3日
 * @version v1.0.0
 */
public class HttpGetTool {

	public static void testGet(String httpURL,String param,String xtoken) {
		InputStream in = null;
		try {
			// 创建请求客户端对象信息
			HttpClient httpclient = HttpClients.createDefault();
			// 创建GET请求,并在后边设置参数值信息
			HttpGet getUtil = new HttpGet(httpURL + "?" + "a=a&" + param);

			// 设置头部信息
			getUtil.setHeader("xtoken", xtoken);
			getUtil.setHeader("customer", "");
			
			//请求服务器，读取返回response中的信息
			HttpResponse response = httpclient.execute(getUtil);
			in = response.getEntity().getContent();
			String resultStr = inputStream2String(in, "ISO-8859-1");
			if (StringUtil.isNotBlank(resultStr)) {
				System.out.println("接收结果===>:" + resultStr);
				System.out.println("接收长度===>:" + resultStr.length());
				System.out.println("解压字符===>:"
						+ ApiUtil.gzipUnCompress(resultStr));
				System.out.println("解压长度===>:"
						+ ApiUtil.gzipUnCompress(resultStr).length());
				String zmStr = new String(ApiUtil.gzipUnCompress(resultStr)
						.getBytes(), "UTF-8");
				System.out.println("Json:" + "\n"
						+ SecurityUtil.decrypt(ComContents.ENKEY, zmStr));
			} else {
				System.out.println("没有返回数据信息");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Description：<br>
	 * inputStream转string信息
	 * @author Lin Xu
	 * @date 2016年1月8日
	 * @version v1.0.0
	 * @param is
	 * @param charset
	 * @return
	 */
	public static String inputStream2String(InputStream is, String charset) {
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


