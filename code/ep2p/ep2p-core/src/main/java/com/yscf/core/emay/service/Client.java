package com.yscf.core.emay.service;

import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yscf.core.emay.sdkhttp.SDKServiceBindingStub;
import com.yscf.core.emay.sdkhttp.SDKServiceLocator;
public class Client {

	private static Logger logger = LoggerFactory.getLogger(Client.class);
	
	private String softwareSerialNo; //软件序列号
	private String key;	// 序列号首次激活时自己设定
	private String serviceAddress;	// 短信平台地址
	private String srcCharset;	// 短信内容编码
	
	public Client(String sn,String key,String serviceAddress,String srcCharset){
		this.softwareSerialNo=sn;
		this.key=key;
		this.serviceAddress = serviceAddress;
		this.srcCharset = srcCharset;
		init();
	}
	
	SDKServiceBindingStub binding;
	
	
	private void init(){
		 try {
		    SDKServiceLocator sDKServiceLocator = new SDKServiceLocator();
		    sDKServiceLocator.setSDKServiceAddress(serviceAddress);
            binding = (SDKServiceBindingStub)sDKServiceLocator.getSDKService();
		}catch (javax.xml.rpc.ServiceException jre) {
			if(logger.isDebugEnabled()){
				logger.debug("短信通道初始化异常："+jre.getMessage());
			}
        }
	}
	
	public int sendSMS( String[] mobiles, String smsContent, String addSerial,int smsPriority)
			throws RemoteException {
		int value=-1;
		value=binding.sendSMS(softwareSerialNo, key,"", mobiles,smsContent, addSerial, srcCharset, smsPriority,0);
		return value;
	}
	
	public int sendScheduledSMSEx(String[] mobiles, String smsContent,String sendTime)
			throws RemoteException {
      int value=-1;
      value=binding.sendSMS(softwareSerialNo, key, sendTime, mobiles, smsContent, "", srcCharset, 3,0);
      return value;
	}

	public String sendVoice(String[] mobiles, String smsContent, String addSerial,int smsPriority,long smsID)
			throws RemoteException {
		     String value=null;
		      value=binding.sendVoice(softwareSerialNo, key,"", mobiles, smsContent,addSerial, srcCharset, smsPriority,smsID);
		      return value;
	}

}
