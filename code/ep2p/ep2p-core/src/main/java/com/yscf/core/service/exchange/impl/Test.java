package com.yscf.core.service.exchange.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.http.client.ClientProtocolException;

import com.achievo.framework.util.DateUtil;

/**
 * 
 * @author Qing.Cai
 * @version 创建时间：2016年1月8日 下午2:27:00 类说明
 */
public class Test {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		try {
			httpJieKouCeshi("13145956752",2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// httpURLConnection测试
	public static void httpURLConnectionText() throws Exception {
		try {
			URL url = new URL("http://a.apix.cn/tongyu/idcardinfo/id?id=430902199110038038");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("content-type", "application/json");
			conn.setRequestProperty("apix-key", "3ba2bae0d2e7410a6e96c92aaab38c5b");
			String resultStr = inputStream2String(conn.getInputStream(), "ISO-8859-1");
			System.out.println(resultStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 充值话费,生成的订单号
	public static void generateDingDan() {
		Date date = new Date();
		SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String s = myFmt2.format(date);
		System.err.println(s);
	}

	// 外部接口调用-充值话费
	public static void httpJieKouCeshi(String phoneNo, Integer telephoneFare) throws ClientProtocolException, IOException {
		try {
			// 充值话费外部接口
			String urlApix = "http://p.apix.cn/apixlife/pay/phone/phone_recharge";
			// 获取订单号
			Date date = new Date();
			SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String orderid = myFmt2.format(date);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update((phoneNo + telephoneFare + orderid).getBytes());
			byte b[] = md.digest();
			int i;
			// MD5加密
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			// 获取32位小写加密
			String sign = buf.toString();
			// 参数
			urlApix += "?phone=" + phoneNo + "&price=" + telephoneFare + "&orderid=" + orderid + "&sign=" + sign;
			// 创建URL对象
			URL url = new URL(urlApix);
			// 创建Connection对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("accept", "application/json");
			conn.setRequestProperty("content-type", "application/json");
			conn.setRequestProperty("apix-key", "b7cb9a5777a8468259092a3e0b44d055");
			String resultStr = inputStream2String(conn.getInputStream(), "ISO-8859-1");
			System.out.println(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void dateCaoZ() {
		GregorianCalendar gcd = new GregorianCalendar();
		gcd.setTime(new Date());
		gcd.add(Calendar.DATE, 4);
		gcd.add(Calendar.MONTH, 4);
		String vipEndTime = DateUtil.format(gcd.getTime());
		System.out.println(vipEndTime);
	}

	// 生成红包的方法
	public static void testRed() {
		double totalAmount = 100;
		int redNumber = 10;
		double[] allocationMoney = new double[redNumber];
		for (int i = redNumber; i > 1; i--) {
			// 安全线
			double surplusMoney = (totalAmount - (i - 1) * 0.01) / i * 3;
			// 判断是否是最后第二个,如果是第二个的话,安全线的生成规则需要改变
			if (i == 2) {
				surplusMoney = (totalAmount - (i - 1) * 0.01);
			}
			// 随机生成红包金额
			double randomlyAssignedMoney = ((int) (Math.random() * surplusMoney * 100) + 1) * 0.01;
			// 截取小数点后两位输出
			BigDecimal aBigDecimal = new BigDecimal(randomlyAssignedMoney);
			// 格式化金额
			double moneyOfOnePerson = aBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			// double类型精确不丢失的算法 工具类
			totalAmount = (new BigDecimal(totalAmount).subtract(new BigDecimal(moneyOfOnePerson))).doubleValue();
			// 赋值
			allocationMoney[i - 1] = moneyOfOnePerson;
		}
		BigDecimal bg = new BigDecimal(totalAmount);
		// 获取最后一值
		double remainingMoney = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		allocationMoney[0] = remainingMoney;
		for (int i = 0; i < allocationMoney.length; i++) {
			System.out.println(allocationMoney[i]);
		}
	}

	// 输出流的处理
	private static String inputStream2String(InputStream is, String charset) {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = is.read()) != -1) {
				baos.write(i);
			}
			return baos.toString(charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				baos = null;
			}
		}
		return null;
	}

}
