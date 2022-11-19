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
package com.yscf.test.xulin;

import com.achievo.framework.util.SecurityUtil;
import com.yscf.encrty.util.ComContents;
import com.yscf.encrty.util.HttpPostTool;


/**
 * Description：<br> 
 * 测试自动投标信息
 * @author  Lin Xu
 * @date    2016年1月7日
 * @version v1.0.0
 */
public class TestAutoTender {
//	private static String httpUrl = "http://localhost:8080/api/personalCenter/autoTenderApi/queryAllAutoTender.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!C70F86781B124B86BC16ACB57375E4");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"amount\":\"1000\",\"borrowType\":\"1,2\",\"autostatus\":\"0\"}");
	
	//private static String httpUrl = "http://localhost:8098/api/personalCenter/autoTenderApi/addAutoTender.api";
	//private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!C70F86781B124B86BC16ACB57375E4");
	//private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"amount\":\"1000\",\"borrowType\":\"1,2\",\"autostatus\":\"0\"}");
	
	
//	private static String httpUrl = "http://localhost:8098/api/personalCenter/autoTenderApi/deleteAutoTender.api";
//	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!C70F86781B124B86BC16ACB57375E4");
//	private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"pid\":\"FFFFFFFFE33112F8!F85D093CB4654F3BBAFE75DBEEC75C\"}");
	
	//资产统计
	private static String httpUrl = "http://localhost:8098/api/personalCenter/personDateApi/getUserProperty.api";
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY, "FFFFFFFFCD1D77F1!C70F86781B124B86BC16ACB57375E4");
	public static void main(String[] args) {
		HttpPostTool.testPost(httpUrl,xtoken,"");
	}
}


