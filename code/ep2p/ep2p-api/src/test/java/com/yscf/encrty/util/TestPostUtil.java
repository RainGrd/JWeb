/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试Post请求UTIL工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月3日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.encrty.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import com.achievo.framework.util.SecurityUtil;

/**
 * Description：<br> 
 * 测试Post请求UTIL工具类
 * @author  Lin Xu
 * @date    2016年1月3日
 * @version v1.0.0
 */
public class TestPostUtil {
	
	//默认参数列
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!36B6DC5DCED446FA8DF43E0CD98822");

	public static void testPost(String str,String httpUrl) {
		BufferedReader in = null;
		try {
			// 创建请求客户端对象信息
			HttpClient httpclient = HttpClients.createDefault();
			// 创建POST请求
			HttpPost postUtil = new HttpPost(httpUrl);
			/** 设置body信息 start 把加密厚的密码参数放到下一行的第一个字符中即可  **/
			postUtil.setEntity(new StringEntity(str, "UTF-8"));
			/** 设置body信息 end **/

			// 设置头部信息
			postUtil.setHeader("xtoken", xtoken);
			postUtil.setHeader("customer", "");
			
			//请求服务器，读取返回response中的信息
			HttpResponse response = httpclient.execute(postUtil);
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
			StringBuffer string = new StringBuffer("");
			String lineStr = "";
			while ((lineStr = in.readLine()) != null) {
				string.append(lineStr + "\n");
			}
			
			String resultStr = string.toString();
			if(null != string && !"".equals(string))
				resultStr = SecurityUtil.decrypt(ComContents.ENKEY, resultStr);
				
			System.out.println("结果===>："+resultStr);
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
	
}


