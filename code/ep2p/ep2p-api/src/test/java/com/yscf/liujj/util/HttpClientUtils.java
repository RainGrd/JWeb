package com.yscf.liujj.util;
/**
 * @projectName:dhtserver
 * @packageName:com.dht.base.util
 * @className:HttpClientUtils.java
 *
 * @createTime:2014年3月13日-下午2:13:40
 *
 *
 */

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * 
 * Description：<br> 
 * TODO
 * @author  JunJie.Liu
 * @date    2015年12月31日
 * @version v1.0.0
 */
public class HttpClientUtils {

    /**
     * 
     * Description：<br> 
     * TODO
     * @author  JunJie.Liu
     * @date    2015年12月31日
     * @version v1.0.0
     * @param url
     * @return
     */
    public static String doGet(String url) {
        String result = null;
        try {
            HttpClient httpClient = HttpClients.createDefault();
            // Get请求
            HttpGet httpget = new HttpGet(url);
            // 设置参数

            // 发送请求
            HttpResponse httpresponse = httpClient.execute(httpget);
            // 获取返回数据
            HttpEntity entity = httpresponse.getEntity();
            result = EntityUtils.toString(entity);
            if (entity != null) {
                EntityUtils.consume(entity);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String doPost(String url, List<NameValuePair> params) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPost httpost = new HttpPost(url);
            httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            HttpResponse response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } catch (Exception e1) {

            e1.printStackTrace();
        } finally {

            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return result;

    }

}
