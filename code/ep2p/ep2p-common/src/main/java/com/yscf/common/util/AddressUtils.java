/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 获取IP对应的地址省份信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月30日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Description：<br> 
 * 获取IP对应的地址省份信息
 * @author  Lin Xu
 * @date    2015年10月30日
 * @version v1.0.0
 */
public class AddressUtils {
	//服务地址 （淘宝IP查询地址）
	private final static String IP_SERVICES_URL = "http://ip.taobao.com/service/getIpInfo.php";
	private final static String IP_CHARTS = "utf-8";
	
	
	/**
	 * Description：<br> 
	 * 返回IP指定的省份信息
	 * @author  Lin Xu
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param ip
	 * @return
	 */
	public static String getRegion(String ip){
		String params = "ip="+ip;
		String returnStr = getRs(IP_SERVICES_URL, params, IP_CHARTS);
		//JSon转码
		try {
			JSONObject json=null;
			if(returnStr != null){
				json = new JSONObject(returnStr);
				if("0".equals(json.get("code").toString())){
					StringBuffer buffer = new StringBuffer();
					buffer.append(decodeUnicode(json.optJSONObject("data").getString("region")));//省份
					return buffer.toString();
				}else{
					return "获取地址失败";
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取IP的实际地址
	 * @param params
	 * @param encoding
	 * @return
	 * @throws Exception
	 */
	public static String getAddress(String ip, String encod) throws Exception{
		String encoding = encod == null ? IP_CHARTS : encod;
		//设置服务地址
		String path = IP_SERVICES_URL;
		//获取返回结果
		String params = "ip="+ip;
		String returnStr = getRs(path, params, encoding);
		//JSon转码
		JSONObject json=null;
		if(returnStr != null){
			json = new JSONObject(returnStr);
			if("0".equals(json.get("code").toString())){
				StringBuffer buffer = new StringBuffer();
				buffer.append(decodeUnicode(json.optJSONObject("data").getString("country")));//国家
				buffer.append(decodeUnicode(json.optJSONObject("data").getString("area")));//地区
				buffer.append(decodeUnicode(json.optJSONObject("data").getString("region")));//省份
				buffer.append(decodeUnicode(json.optJSONObject("data").getString("city")));//市区
				buffer.append(decodeUnicode(json.optJSONObject("data").getString("county")));//地区
				buffer.append(decodeUnicode(json.optJSONObject("data").getString("isp")));//ISP公司
				System.out.println(buffer.toString());
				return buffer.toString();
			}else{
				return "获取地址失败";
			}
		}
		return null;
	}
	
	/**
	 * 从url获取结果
	 * @param path
	 * @param params
	 * @param encoding
	 * @return
	 */
	private static String getRs(String path, String params, String encoding){
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(path);
			connection = (HttpURLConnection)url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoInput(true);// 是否打开输出 true|false
			connection.setDoOutput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(params);
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encoding));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine())!= null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connection.disconnect();// 关闭连接
		}
		return null;
	}
	
	/**
	 * 字符转码
	 * @param theString
	 * @return
	 */
	private static String decodeUnicode(String theString){
		char aChar;
		int len = theString.length();
		StringBuffer buffer = new StringBuffer(len);
		for (int i = 0; i < len;) {
			aChar = theString.charAt(i++);
			if(aChar == '\\'){
				aChar = theString.charAt(i++);
				if(aChar == 'u'){
					int val = 0;
					for(int j = 0; j < 4; j++){
						aChar = theString.charAt(i++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
						val = (val << 4) + aChar - '0';
						break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
						val = (val << 4) + 10 + aChar - 'a';
						break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
						val = (val << 4) + 10 + aChar - 'A';
						break;
						default:
						throw new IllegalArgumentException(
							"Malformed      encoding.");
					    }
					}
					buffer.append((char) val);
					}else{
						if(aChar == 't'){
							aChar = '\t';
						}
						if(aChar == 'r'){
							aChar = '\r';
						}
						if(aChar == 'n'){
							aChar = '\n';
						}
						if(aChar == 'f'){
							aChar = '\f';
						}
						buffer.append(aChar);
					}
				}else{
					buffer.append(aChar);
				}
			}
		return buffer.toString();
	}

	/*
	public static void main(String[] args) {
		String ip = "113.116.229.39";
		System.out.println(AddressUtils.getRegion(ip));
	}
	*/
}


