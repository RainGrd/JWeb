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
 * 2016年1月6日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.borrow.util;

import org.junit.Test;

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
 * @date 2016年1月6日
 * @version v1.0.0
 */
public class BorrowAPITest {
	private static String httpUrl = BaseUrl.baseUrl
			+ "eplan/eplanApi/appRecommendProjects.api";
	private static String xtoken = SecurityUtil.encrypt(ComContents.ENKEY,
			"FFFFFFFFCD1D77F1!C70F86781B124B86BC16ACB57375E4");
	private static String param = SecurityUtil
			.encrypt(
					ComContents.ENKEY,
					"{'sortType':'2','sortModel':'2','pageIndex':'0','pageSize':'5','borrowType':'1'}");

	@Test
	public void testEplanList() {
		String gzipCom = ApiUtil.gzipCompress(param);
		HttpPostTool.testPost(httpUrl, xtoken, gzipCom);
	}

	/*
	 * @Test public void testEplanInfo() { String url = BaseUrl.baseUrl +
	 * "eplan/eplanApi/getEplanInfo.api"; try { BizBorrowVo args = new
	 * BizBorrowVo();
	 * args.setPid("FFFFFFFFEF0724CA!1D28A1BD1E4749DD9C527F57A9CC9E");
	 * 
	 * String str = ArgsUtil.getEncryStr(args); TestPostUtil.testPost(str, url);
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * @Test public void testEplanProduct() { String url = BaseUrl.baseUrl +
	 * "eplan/eplanApi/getEplanProduct.api"; try { BizBorrowVo args = new
	 * BizBorrowVo();
	 * args.setPid("FFFFFFFFEE96CD51!FF4AFB86D23F47288288BDC378EAB4");
	 * 
	 * String str = ArgsUtil.getEncryStr(args); TestPostUtil.testPost(str, url);
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */
	/*
	 * @Test public void testGetBorrowDetailList() { String url =
	 * BaseUrl.baseUrl + "eplan/eplanApi/getBorrowDetailList.api"; try {
	 * BizBorrowDetail args = new BizBorrowDetail();
	 * args.setBorrowId("FFFFFFFFEF0724CA!AE6F094D0B394FF9AEE0C4BE05C8F0");
	 * 
	 * String str = ArgsUtil.getEncryStr(args); TestPostUtil.testPost(str, url);
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * 
	 * @Test public void testGetCustomerByPid() { String url = BaseUrl.baseUrl +
	 * "eplan/eplanApi/getCustomerByPid.api"; try { CusTomer args = new
	 * CusTomer();
	 * args.setPid("FFFFFFFFCD1D77F1!691CCCF65DAE43729AC131A6C56228");
	 * 
	 * String str = ArgsUtil.getEncryStr(args); TestPostUtil.testPost(str, url);
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	// @Test
	// public void getBorrowOtherInfo() {
	// String url = BaseUrl.baseUrl + "eplan/eplanApi/getBorrowOtherInfo.api";
	// try {
	// BizBorrowVo args = new BizBorrowVo();
	// args.setPid("FFFFFFFFEF0724CA!00E80FE103FD4653911CBB05FB728B");
	//
	// String str = ArgsUtil.getEncryStr(args);
	// TestPostUtil.testPost(str, url);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void getRiskControl() {
	// String url = BaseUrl.baseUrl
	// + "columnContentApi/batchUpdateIsReadingByPids.api";
	// try {
	// ColumnContentVo args = new ColumnContentVo();
	// args.setPid("36B19CBE!2509F0AC020949FC961C0BB62DAB2199");
	// args.setIsReading("0");
	// String str = ArgsUtil.getEncryStr(args);
	// TestPostUtil.testPost(str, url);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void saveBorrow() {
	// String url = BaseUrl.baseUrl + "eplan/eplanApi/saveBorrow.api";
	// try {
	// NewStandardArgsVo args = new NewStandardArgsVo();
	// args.setUserId("FFFFFFFFCD1D77F1!691CCCF65DAE43729AC131A6C56228");
	// args.setTradePwd("123456");
	// args.setAmount("100");
	// args.setBorrowId("FFFFFFFFEF0724CA!00E80FE103FD4653911CBB05FB728B");
	//
	// String str = ArgsUtil.getEncryStr(args);
	// TestPostUtil.testPost(str, url);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
