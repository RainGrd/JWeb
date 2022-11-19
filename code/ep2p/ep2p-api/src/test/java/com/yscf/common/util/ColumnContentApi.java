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
 * 2016年1月13日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import com.achievo.framework.util.SecurityUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.encrty.util.ComContents;
import com.yscf.encrty.util.HttpPostTool;
import com.yscf.liujj.util.BaseUrl;

/**
 * Description：<br>
 * TODO
 * 
 * @author shiliang.feng
 * @date 2016年1月13日
 * @version v1.0.0
 */
public class ColumnContentApi {
	private static String httpUrl = BaseUrl.baseUrl
			+ "/content/sysNoticeReadRecordApi/readAllMessage.api";
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY,
			"FFFFFFFFCD1D77F1!30214E2F008F48BF8D994426DE112C");
	private static String param = SecurityUtil.encrypt(ComContents.ENKEY,
			"{'':''}");

	public static void main(String[] args) {
		// System.out.println(DateUtil.getToday().toString());
		String gzipCom = ApiUtil.gzipCompress(param);
		HttpPostTool.testPost(httpUrl, xtoken, gzipCom);
	}

	// @Test
	// public void testEplanInfo() {
	// String url = BaseUrl.baseUrl
	// + "content/columnContentApi/getContentByTag.api";
	// try {
	// ColumnContentVo args = new ColumnContentVo();
	// args.setWebTag("P2P_NOVICE_GUIDE");
	//
	// String str = ArgsUtil.getEncryStr(args);
	// TestPostUtil.testPost(str, url);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

}
