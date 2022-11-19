/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试我的投资数据信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月26日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.test.xulin;

import com.achievo.framework.json.gson.GsonUtil;
import com.achievo.framework.util.SecurityUtil;
import com.google.gson.Gson;
import com.yscf.api.util.ApiUtil;
import com.yscf.core.model.ptp.investment.InvestDueInModel;
import com.yscf.encrty.util.ComContents;
import com.yscf.encrty.util.HttpPostTool;


/**
 * Description：<br> 
 * 测试我的投资数据信息
 * @author  Lin Xu
 * @date    2016年1月26日
 * @version v1.0.0
 */
public class TestPersonCecter {
	//待收中
//	private static String httpUrl = "http://localhost:8098/api/personalCenter/userInvestmentApi/selectDueinBorrow.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!538A910FA0F442ACA8F4A4B128CDF5");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"pagesize\":\"10\",\"page\":\"1\",\"tranPKey\":\"123456789\"}");
	//招标中
//	private static String httpUrl = "http://localhost:8098/api/personalCenter/userInvestmentApi/selectInviteTendersInfo.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!88F4FC10D19048AD984F1AFE753DF6");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"pagesize\":\"10\",\"page\":\"1\"}");
	//转让 
//	private static String httpUrl = "http://localhost:8098/api/personalCenter/userInvestmentApi/selectTransferInfo.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!D4161FD9404E41C3A8ED386570A936");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"pagesize\":\"10\",\"page\":\"1\"}");
	//已完结
//	private static String httpUrl = "http://localhost:8098/api/personalCenter/userInvestmentApi/selectInvestmentInfo.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!538A910FA0F442ACA8F4A4B128CDF5");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"pagesize\":\"10\",\"page\":\"1\"}");
	
//	private static String httpUrl = "http://localhost:8098/api/index/homepController/selectAdvContentByWebTag.shtml?webTag=api_col_homePage_t_1";
	//招标中
	private static String httpUrl = "http://localhost:8098/api/personalCenter/userInvestmentApi/selectDueInDetailList.api";
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!08F210E580BC4AD28913E468790132");
	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"pid\":\"FFFFFFFFEE96CD51!5854F8BAEA624F52A52042DFA14EA4\"}");
	public static void main(String[] args) {
		String gzipCom = ApiUtil.gzipCompress(param);
		HttpPostTool.testPost(httpUrl,xtoken,gzipCom);
//		HttpPostTool.testPost(httpUrl,"","");
	}
	
	
}


