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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import com.achievo.framework.util.SecurityUtil;

/**
 * Description：<br> 
 * 测试Get请求UTIL工具类
 * @author  Lin Xu
 * @date    2016年1月3日
 * @version v1.0.0
 */
public class HttpGetUtil {
	
	private static String httpURL = "http://localhost:8098/api/personalCenter/autoTenderApi/queryAllAutoTender.api";
	//默认参数列
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!36B6DC5DCED446FA8DF43E0CD98822");

	public static void testGet() {
		BufferedReader in = null;
		try {
			// 创建请求客户端对象信息
			HttpClient httpclient = HttpClients.createDefault();
			// 创建GET请求,并在后边设置参数值信息
			HttpGet getUtil = new HttpGet(httpURL + "?" + "a=a");

			// 设置头部信息
			getUtil.setHeader("xtoken", xtoken);
			getUtil.setHeader("customer", "");
			
			//请求服务器，读取返回response中的信息
			HttpResponse response = httpclient.execute(getUtil);
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
	
	//主函数
	public static void main(String[] args) {
		HttpGetUtil.testGet();
	}
	
	
}


