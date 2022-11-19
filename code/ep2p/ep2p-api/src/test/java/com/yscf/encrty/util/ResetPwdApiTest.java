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
 * 2016年1月5日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.encrty.util;

import com.achievo.framework.util.SecurityUtil;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2016年1月5日
 * @version v1.0.0
 */
public class ResetPwdApiTest {

		// 注册
		private static String httpURL = "http://localhost:8888/api/user/resetPwdApi/resetPwd.api";
		
		// 默认参数列
		private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'loginName':'18900000002','password':'456789.','referralCode':'A0002'}");
		
		// 注册发送短信
//		private static String httpURL = "http://localhost:8888/api/user/resetPwdApi/sendSmsCode.api";
//		
//		private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'mobile':'18566637273'}");
		
		
		public static void main(String[] args) {
			HttpPostUtil.testPost(httpURL, param);
		}
}


