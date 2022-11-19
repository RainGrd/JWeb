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
public class CalculatorApiTest {

	 	// 收益计算器
		private static String httpURL = "http://localhost:8888/api/otherinfo/calculatorApi/execCalculator.api";
		
		// 默认参数列
		private static String param = SecurityUtil.encrypt(ComContents.ENKEY, "{'repaymentAmt':'12000','repaymentDeadline':'6.','repaymentRate':'12','repaymentType':'1'}");
		
		
		public static void main(String[] args) {
			HttpPostUtil.testPost(httpURL, param);
		}
}


