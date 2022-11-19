/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 消息发送服务控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.sms;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.push.jpush.AudienceType;
import com.achievo.framework.push.jpush.JPushMessage;
import com.achievo.framework.push.jpush.JPusher;
import com.achievo.framework.push.jpush.MessageType;
import com.achievo.framework.push.jpush.PlatformType;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.emay.service.SmsSDKClient;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.sms.CustMessRecord;
import com.yscf.core.model.sms.SmsRecordsCustomer;
import com.yscf.core.model.sms.SmsRecordsModel;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.sms.impl.CustMessRecordServiceImpl;
import com.yscf.core.service.sms.impl.SmsRecordsCustomerServiceImpl;
import com.yscf.core.service.sms.impl.SmsRecordsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.system.constort.PageMessageConstants;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.util.StringArrayUtil;

/**
 * Description：<br> 
 * 消息发送服务控制层
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/smsRecordsController")
public class SmsRecordsController extends EscfBaseController {
	
	Logger logger = Logger.getLogger(SmsRecordsController.class);
	private static String SPLIT_CHARTS = ",";
	
	@Resource(name="bizBorrowService")
	private BizBorrowServiceImpl  bizBorrowService;
	
	@Resource(name="cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	
	@Resource(name="smsRecordsCustomerService")
	private SmsRecordsCustomerServiceImpl smsRecordsCustomerService;
	//系统消息发送
	@Resource(name="custMessRecordService")
	private CustMessRecordServiceImpl custMessRecordService;
	
	@Resource(name="jPushUtilTool")
	private JPusher jpusherService;
	
	@Autowired
	public SmsRecordsController(SmsRecordsServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SmsRecordsModel.class;
	}
	
	/**
	 * Description：<br> 
	 * 借后管理的消息
	 * @author  Lin Xu
	 * @date    2015年10月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/sendBorrowAfterSms")
	@ResponseBody
	public HashMap<String, Object> sendBorrowAfterSms(HttpServletRequest request,
			HttpServletResponse response,SmsRecordsModel srm,String borpid,String[] pushTypes){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		SmsRecordsServiceImpl srservice = (SmsRecordsServiceImpl) getService();
		try{
			if(null != srm && StringUtil.isNotEmpty(borpid) 
					&& null != pushTypes && pushTypes.length > 0){
				String[] bpid = StringUtil.split(borpid, ",");
				for(String p : bpid){
				if(!"1".equals(p)){
					//1.插入发送消息记录
					String pid = srm.getPK();
					srm.setPid(pid);
					srm.setPushModel("借后管理");
					srm.setCreateTime(DateUtil.format(DateUtil.getToday()));
					srm.setCreateUser(getContextUser().getUserId());
					String pushtype = StringArrayUtil.strAtoString(pushTypes, SPLIT_CHARTS);
				    srm.setPushType(pushtype);
					//查询需要发送的人员信息
				    BizBorrow bb = (BizBorrow) bizBorrowService.selectByPrimaryKey(p);
				    CusTomer cust = (CusTomer) cusTomerService.selectByPrimaryKey(bb.getCustomerId());
				    if(null != cust){
				    	//进行信息推送设置（检查需要推送的设备信息） 可以提出到消息发送工具中
					    for(String pty : pushTypes){
					    	//发送短信
					    	if(Constant.SMS_PUSH_TYPE_1.equals(pty)){
					    		logger.info("发送短信");
					    		SmsSDKClient.sendEmay(1, cust.getPhoneNo(), srm.getSmsContext(), null, null, 1);
					    	}
					    	//发送微信
					    	if(Constant.SMS_PUSH_TYPE_2.equals(pty)){
					    		logger.info("发送微信");
					    	}
					    	//发送APP
					    	if(Constant.SMS_PUSH_TYPE_3.equals(pty)){
					    		logger.info("发送APP");
					    		sendJpushMsg(cust.getMobileDeviceMachineCode(),srm.getSmsContext());
					    	}
					    	//发送系统消息
					    	if(Constant.SMS_PUSH_TYPE_4.equals(pty)){
					    		logger.info("发送系统消息");
					    		CustMessRecord cusmesst = new CustMessRecord();
					    		cusmesst.setPid(cusmesst.getPK());
					    		cusmesst.setCustomerId(cust.getPid());
					    		cusmesst.setSendType(4);
					    		cusmesst.setMsgType(1);
					    		cusmesst.setSendModel("借后管理");
					    		cusmesst.setSendContent(srm.getSmsContext());
					    		cusmesst.setIsRead(0);
					    		cusmesst.setCreateUser(cust.getPid());
					    		cusmesst.setCreateTime(DateUtil.format(new Date()));
					    		cusmesst.setStatus("1");
					    		custMessRecordService.insertSelective(cusmesst);
					    	}
					    }
					    //新增发送的人员消息记录
				    	SmsRecordsCustomer srdcm = new SmsRecordsCustomer();
				    		srdcm.setPid(srdcm.getPK());
				    		srdcm.setSmsRecordId(pid);
				    		srdcm.setCustomerId(cust.getPid());
				    	//进行插入人员发送消息信息
				    	smsRecordsCustomerService.insert(srdcm);
				    }
				    //插入发送消息记录
				    srservice.insert(srm);
				}
				}
				processSuccess(remap, PageMessageConstants.COMMON_MESSAGE_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	/**
	 * Description：<br> 
	 * 利用极光推送信息到
	 * @author  Lin Xu
	 * @date    2016年3月8日
	 * @version v1.0.0
	 * @param sedList
	 * @param sendMsg
	 * @throws FrameworkException 
	 */
	private void sendJpushMsg(String sendrgid,String sendMsg){
		//设置发送的类型注册ID信息
		JPushMessage jpushmessage = new JPushMessage();
		jpushmessage.setAudienceType(AudienceType.RegistrationId);
		jpushmessage.setRegistrationId(sendrgid);
		jpushmessage.setPlatformType(PlatformType.Android_iOS);
		jpushmessage.setMessageType(MessageType.Message);
		jpushmessage.setTitle("借后提示信息");
		jpushmessage.setMessage(sendMsg);
		jpushmessage.setParameter(new HashMap<String, String>());
		//进行推送信息
		boolean rpbool;
		try {
			rpbool = jpusherService.push(jpushmessage);
			if(!rpbool){
				logger.info("JPush发送信息失败！");
			}
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	

}


