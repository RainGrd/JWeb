/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试自动投标信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月7日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.test.caiq;

import com.achievo.framework.util.SecurityUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.encrty.util.ComContents;
import com.yscf.encrty.util.HttpPostTool;

/**
 * 
 * @ClassName : TestAutoTender
 * @Description : 蔡晴测试API
 * @Author : Qing.Cai
 * @Date : 2016年1月22日 上午11:01:09
 */
public class TestAutoTender {
	// APP调试
//	private static String httpUrl = "http://183.15.251.92:8081/api/activity/activityApi/selectPendingRepayment.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!307F05CE27C6481289AB49E787276B");

	// 本地调试
	private static String httpUrl = "http://localhost:8080/api/activity/receiveActictyApi/receiveRed.api";
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!3518429E1B8C4F7A819F655E45F4A6");
	private static String param = SecurityUtil.encrypt(ComContents.ENKEY,"{\"redpacketId\":\"FFFFFFFFE4E163CB!B9210F259D884EAD99600DD0601405\"}");

	// 登录
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY,"{'loginName':'15116705347','password':'caiqing520','loginFlag':'yes'}");
//	private static String httpUrl = "http://localhost:8080/api/user/loginApi/login.api";

//	private static String httpUrl = "http://183.15.248.149:8081/api/user/loginApi/login.api";
	
	public static void main(String[] args) throws Exception {
		 String gzipCom = ApiUtil.gzipCompress(param);
		 HttpPostTool.testPost(httpUrl, xtoken, gzipCom);
	}
}
