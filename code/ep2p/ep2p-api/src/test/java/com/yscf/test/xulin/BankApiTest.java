package com.yscf.test.xulin;

import com.achievo.framework.util.SecurityUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.encrty.util.ComContents;
import com.yscf.encrty.util.HttpPostTool;

public class BankApiTest {
	
	private static String httpUrl = "http://localhost:8098/api/securitycenter/bankCardApi/radioPopularityList.api";
//	private static String httpUrl = "http://localhost:8099/api/capitalflow/capitalFlowApi/radioList.api";
	
	// 登录
//	private static String httpUrl = "http://localhost:8098/api/user/loginApi/login.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!D4161FD9404E41C3A8ED386570A936");
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!8E85FF62A6C043469A8945319A15E0");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'loginName':'18620303880','password':'123456a','loginFlag':'yes'}");
//	
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'mount':'12','amount':'120'}");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'bankcardName':'天河银行','bankCardNo':'9876543210123456','belongingBank':'test','belongingProvince':'test','openAddress':'hy'}");
	
	public static void main(String[] args) {
		String gzipCom = ApiUtil.gzipCompress("");
		HttpPostTool.testPost(httpUrl,xtoken,"");       
	}

}
