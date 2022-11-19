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
 * 2016年1月19日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.test.xulin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;

import com.achievo.framework.util.SecurityUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.encrty.util.ComContents;

/**
 * Description：<br>
 * TODO
 * 
 * @author Lin Xu
 * @date 2016年1月19日
 * @version v1.0.0
 */
public class TestHeadImage {

	public static void main(String[] args) {
		InputStream in = null;
		try {
			// 创建请求客户端对象信息
			HttpClient httpclient = HttpClients.createDefault();
			// 创建POST请求
			HttpPost postUtil = new HttpPost(
					"http://localhost:8098/api/personalCenter/headProcessApi/uploadHeadImage.api");

			/** 设置body信息 start 把加密厚的密码参数放到下一行的第一个字符中即可 **/
			// 设置头部信息
			String xtoken = SecurityUtil.encrypt(ComContents.ENKEY,
					"FFFFFFFFCD1D77F1!C70F86781B124B86BC16ACB57375E4");
			postUtil.setHeader("token", xtoken);
			FileEntity fileE = new FileEntity(new File(
					"D:\\用户目录\\我的图片\\Saved Pictures\\17_103910v7.jpg"));
			postUtil.setEntity(fileE);
			// 请求服务器，读取返回response中的信息
			HttpResponse response = httpclient.execute(postUtil);
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(response.getEntity().getContent()));
			in = response.getEntity().getContent();
			String resultStr = inputStream2String(in, "ISO-8859-1");
			if (StringUtil.isNotBlank(resultStr)) {
				System.out.println("接收结果===>:" + resultStr);
				System.out.println("接收长度===>:" + resultStr.length());
				System.out.println("解压字符===>:"
						+ ApiUtil.gzipUnCompress(resultStr));
				System.out.println("解压长度===>:"
						+ ApiUtil.gzipUnCompress(resultStr).length());
				String zmStr = new String(ApiUtil.gzipUnCompress(resultStr)
						.getBytes(), "UTF-8");
				System.out.println("Json:" + "\n"
						+ SecurityUtil.decrypt(ComContents.ENKEY, zmStr));
			} else {
				System.out.println("没有返回数据信息");
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Description：<br>
	 * inputStream转string信息
	 * 
	 * @author Lin Xu
	 * @date 2016年1月8日
	 * @version v1.0.0
	 * @param is
	 * @param charset
	 * @return
	 */
	public static String inputStream2String(InputStream is, String charset) {
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
