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
 * 2016年2月17日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.achievo.framework.util.StringUtil;

/**
 * Description：<br>
 * TODO
 * 
 * @author Yu.Zhang
 * @date 2016年2月17日
 * @version v1.0.0
 */
public class CookieUtil {
	
	private static final String COOKIE_NAME = "login_name";

	/**
	 * 
	 * Description：<br> 
	 * 将用户名保存至cookie中
	 * @author  Yu.Zhang
	 * @date    2016年2月17日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param loginName
	 * @param issave
	 * @throws Exception
	 */
	public static void saveCookie(HttpServletRequest request,
			HttpServletResponse response,String loginName,String issave) throws Exception {
		Cookie[] cookies = request.getCookies();
		boolean flag = false;
		Cookie cookie = null;
		if (cookies != null) {
			System.out.println("cookie 不为空");
			for (Cookie c : cookies) {

				if (COOKIE_NAME.equals(c.getName())) {
					System.out.println("找到了 " + COOKIE_NAME);
					System.out.println("cookie的值为 " + c.getValue());
					try {
						c.setValue(URLEncoder.encode(loginName,"utf-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					cookie = c;
					flag = true;
					break;
				}
			}

		}
		if (!flag) {
			System.out.println("没有找到 " + COOKIE_NAME);

			try {
				cookie = new Cookie(COOKIE_NAME, URLEncoder.encode(loginName, "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		if (null != cookie) {

			if (StringUtil.isNotBlank(issave) && issave.equalsIgnoreCase("1")) {
				cookie.setMaxAge(10000000);
				response.addCookie(cookie);
				System.out.println("保存cookie");
			} else {
				System.out.println("让 cookie 失效");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 取出当前cookie保存的用户名
	 * @author  Yu.Zhang
	 * @date    2016年2月17日
	 * @version v1.0.0
	 * @param request
	 * @return
	 */
	public static String getCookieLoginName(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();  
		String loginName="";  
		if (cookies != null) {  
		    for (Cookie c : cookies) {  
		        if (COOKIE_NAME.equals(c.getName())) {  
		            try {
						loginName=URLDecoder.decode(c.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}  
		            break;  
		        }  
		    }  
		} 
		return loginName;
	}
}
