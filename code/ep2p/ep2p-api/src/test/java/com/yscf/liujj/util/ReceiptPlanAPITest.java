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
import com.yscf.api.vo.persionalcenter.ReceiptPlanArgsVo;
import com.yscf.encrty.util.ArgsUtil;
import com.yscf.encrty.util.TestPostUtil;

public class ReceiptPlanAPITest {

	@Test
	public void info() {
		String url = BaseUrl.baseUrl + "receiptPlanApi/info.api";

		try {
			ReceiptPlanArgsVo args = new ReceiptPlanArgsVo();
			args.setReceiptPlanId("1");
			args.setUserId("1");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void transferInfo() {
		String url = BaseUrl.baseUrl + "receiptPlanApi/transferInfo.api";
		
		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setBorrowId("FFFFFFFFEF0724CA!AE6F094D0B394FF9AEE0C4BE05C8F0");
			args.setTransferId("");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void borrowInfo() {
		String url = BaseUrl.baseUrl + "receiptPlanApi/borrowInfo.api";
		
		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setBorrowId("FFFFFFFFEE96CD51!00170D6AE2DF40FDAF114750C78BF9");
			args.setUserId("1");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void transferOk() {
		String url = BaseUrl.baseUrl + "receiptPlanApi/transferOk.api";
		
		try {
			TransferApiArgsVo args = new TransferApiArgsVo();
			args.setUserId("1");
			args.setReceiptPlanId("1");
			args.setTradePwd("123456.");
			args.setAmount("100");
			args.setNetAmount("95");
			args.setYearRate("0.15");
			args.setReturnedDate("8");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	

}
