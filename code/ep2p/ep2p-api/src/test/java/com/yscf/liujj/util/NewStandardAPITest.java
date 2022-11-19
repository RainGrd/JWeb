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

import com.yscf.api.vo.myinvest.NewStandardArgsVo;
import com.yscf.encrty.util.ArgsUtil;
import com.yscf.encrty.util.TestPostUtil;

public class NewStandardAPITest {

	@Test
	public void unique() {
		String url = BaseUrl.baseUrl + "newStandardApi/unique.api";

		try {
			TestPostUtil.testPost("", url);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Test
	public void info() {
		String url = BaseUrl.baseUrl + "newStandardApi/info.api";
		
		try {
			NewStandardArgsVo args = new NewStandardArgsVo();
			args.setBorrowId("FFFFFFFFEE96CD51!00170D6AE2DF40FDAF114750C78BF9");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void record() {
		String url = BaseUrl.baseUrl + "newStandardApi/record.api";
		
		try {
			NewStandardArgsVo args = new NewStandardArgsVo();
			args.setBorrowId("FFFFFFFFEE96CD51!00170D6AE2DF40FDAF114750C78BF9");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void investInfo() {
		String url = BaseUrl.baseUrl + "newStandardApi/investInfo.api";
		
		try {
			NewStandardArgsVo args = new NewStandardArgsVo();
			args.setUserId("1");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	@Test
	public void invest() {
		String url = BaseUrl.baseUrl + "newStandardApi/invest.api";
		
		try {
			NewStandardArgsVo args = new NewStandardArgsVo();
			args.setUserId("1");
			args.setAmount("100");
			args.setBorrowId("FFFFFFFFEE96CD51!00170D6AE2DF40FDAF114750C78BF9");
			args.setTradePwd("123456");
			TestPostUtil.testPost(ArgsUtil.getEncryStr(args), url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

}
