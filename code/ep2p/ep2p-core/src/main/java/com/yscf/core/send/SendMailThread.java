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
 * 2015年12月19日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.send;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description：<br> 
 * 邮件发送线程类
 * @author  JingYu.Dai
 * @date    2015年12月19日
 * @version v1.0.0
 */
public class SendMailThread implements Runnable{
	
	private Logger logger = LoggerFactory.getLogger(SendMailThread.class);

	/**
	 * springMail发送邮件实现类
	 */
	private SendMail sendMail;
	
	/**
	 * Description：<br> 
	 * 发送邮件线程类
	 * @author  JingYu.Dai
	 * @date    2015年12月19日
	 * @version v1.0.0
	 * @param mailNo 接收邮箱号
	 * @param mailTitle 邮件标题
	 * @param mailContent 邮件内容
	 */
	public SendMailThread(String mailNo,String mailTitle,String mailContent,SendMail sendMail){
		this.sendMail = sendMail;
		sendMail.setMailTo(mailNo);
		sendMail.setMailTitle(mailTitle);
		sendMail.setMailContent(mailContent);
	}
	
	@Override
	public void run() {
		try {
			sendMail.sendMailHTML();
		} catch (MessagingException e) {
			e.printStackTrace();
			if (logger.isInfoEnabled()) {
				logger.info(e.getMessage());
			}
		}
	}

	public SendMail getSendMail() {
		return sendMail;
	}

	public void setSendMail(SendMail sendMail) {
		this.sendMail = sendMail;
	}

}


