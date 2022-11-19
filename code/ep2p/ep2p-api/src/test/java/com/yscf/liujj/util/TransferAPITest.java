package com.yscf.liujj.util;
/**   
 * @projectName:dhtserver  
 * @packageName:com.dht.rest  
 * @className:RestAPITest.java  
 *   
 * @createTime:2014年3月13日-下午12:19:33  
 *  
 *    
 */

import org.junit.Test;

import com.yscf.api.vo.myinvest.TransferApiArgsVo;
import com.yscf.encrty.util.ArgsUtil;
import com.yscf.encrty.util.TestPostUtil;

public class TransferAPITest {

	@Test
	public void transferList() {

		String url = BaseUrl.baseUrl + "transferApi/list.api";

		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setDesc(true);
			args.setPageNum(1);
			args.setPageSize(10);
			args.setType("1");
			String str = ArgsUtil.getEncryStr(args);
			TestPostUtil.testPost(str, url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void transferInfo() {

		String url = BaseUrl.baseUrl + "transferApi/info.api";

		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setTransferId("1");
			String str = ArgsUtil.getEncryStr(args);
			TestPostUtil.testPost(str, url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void transferBuy() {

		String url = BaseUrl.baseUrl + "transferApi/buy.api";

		try {
			
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setUserId("FFFFFFFFCD1D77F1!25357839E189486AA82C255D4EAC8F");
			args.setTransferId("1");
			args.setTradePwd("123456");
			
			String str = ArgsUtil.getEncryStr(args);
			TestPostUtil.testPost(str, url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void centerList() {
		
		String url = BaseUrl.baseUrl + "transferApi/centerList.api";
		
		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setUserId("1");
//			args.setTransferType("1");
			args.setTransferType("2");
//			args.setTransferType("3");
			
			args.setPageNum(1);
			args.setPageSize(10);
			
			String str = ArgsUtil.getEncryStr(args);
			TestPostUtil.testPost(str, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void holdInfo() {
		
		String url = BaseUrl.baseUrl + "transferApi/holdInfo.api";
		
		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setUserId("1");
			args.setTransferId("FFFFFFFFDFE5BB0E!9058BFBDF32C492197BBD7D67457AD");
			
			String str = ArgsUtil.getEncryStr(args);
			TestPostUtil.testPost(str, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
