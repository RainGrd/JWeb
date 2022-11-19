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
import com.yscf.api.util.ApiUtil;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2016年1月5日
 * @version v1.0.0
 */
public class LoginApiTest {
	
//	private static String httpURL = "http://localhost:8888/api/myinvest/preRepaymentApi/prepayment.api";
	private static String httpURL = "http://localhost:8888/api/user/loginApi/getBbsLoginResult.api";
	// 登录
//	private static String httpURL = "http://192.168.1.222:8081/api/user/loginApi/login.api";
	//默认参数列
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'loginName':'18900000001','password':'123456.','loginFlag':'no'}");
	
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'borrowId':'FFFFFFFFEF0724CA!CACE3CE45780406295F57DDC78056F','repaymentAmount':1000.00}");
	
	private static String token = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!691CCCF65DAE43729AC131A6C56228");
	
//	private static String httpURL = "http://localhost:8888/api/withdrawal/bizWithdrawalApi/sendVerifyCode.api";
	
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'mobile':'18566637273','money':'1000.00'}");
	
//	private static String httpURL = "http://localhost:8888/api/withdrawal/bizWithdrawalApi/custWithdrawal.api";
//	
//	private static String httpURL = "http://mrliangqi.oicp.net:8081/api/withdrawal/bizWithdrawalApi/sendVerifyCode.api";
	
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'mobile':'18566637273','bankId':'6217007200025569123','money':'1000.00','verifyCode':'3149'}");
	
	// 退出
//	private static String httpURL = "http://localhost:8888/api/user/loginApi/loginout.api";
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "12312321");
	
	
	public static void main(String[] args) {
		String gzipCom = ApiUtil.gzipCompress("");
//		String gzipCom = ApiUtil.gzipCompress(param);
		HttpPostTool.testPost(httpURL,token,gzipCom);
//		HttpPostTool.testPost(httpURL,"",gzipCom);
	}
}



