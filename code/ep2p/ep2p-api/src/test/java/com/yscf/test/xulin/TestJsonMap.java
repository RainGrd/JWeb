/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 测试JSON转MAP
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月28日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.test.xulin;

import com.achievo.framework.util.SecurityUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.encrty.util.ComContents;
import com.yscf.encrty.util.HttpGetTool;
import com.yscf.encrty.util.HttpPostTool;


/**
 * Description：<br> 
 * 测试JSON转MAP
 * @author  Lin Xu
 * @date    2016年1月28日
 * @version v1.0.0
 */
public class TestJsonMap {
	
	public static void main(String[] args) {
		String url = "http://localhost:8098/api/homepage/homePageApi/getAlimama.api";
		String param = SecurityUtil.encrypt(ComContents.ENKEY, "{\"webTag\":\"1\"}");
		String gzipCom = ApiUtil.gzipCompress(param);
		HttpPostTool.testPost(url,"",gzipCom);
		//HttpGetTool.testGet(url, "webTag=1", "");
	}
	
}


