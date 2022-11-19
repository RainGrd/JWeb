package com.yscf.core.send;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Description：<br>
 * springMail发送邮件实现类
 * 
 * @author JingYu.Dai
 * @date 2015年12月19日
 * @version v1.0.0
 */
@Component("sendMail")
public class SendMail implements ApplicationContextAware  {

	private Logger logger = LoggerFactory.getLogger(SendMail.class);

	// 上下文对象
	private ApplicationContext ctx = null;

	// 接收邮箱号
	private String mailTo;

	// 发送邮箱号
	private String mailFrom = "service@yscf.com";

	// 邮件标题
	private String mailTitle;

	// 邮件内容
	private String mailContent;

	/**
	 * Description：<br>
	 * 发送带有正文是html的邮件
	 * 
	 * @author JingYu.Dai
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @throws MessagingException
	 */
	public void sendMailHTML() throws MessagingException {
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");// 获取JavaMailSender
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm, true, "utf-8");
		try {
			helper.setTo(this.mailTo);// 接受者
			helper.setFrom(this.mailFrom);// 发送者
			helper.setSubject(this.mailTitle);// 主题
			helper.setText(this.mailContent, true);// 第二个参数代表发送的是正文是html
			sender.send(mm);
			logger.info("邮件接收人：" + this.mailTo + "邮件标题：" + this.mailTitle
					+ "邮件内容：" + this.mailContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description：<br>
	 * 发送简单邮件 txt格式
	 * 
	 * @author JingYu.Dai
	 * @date 2015年12月19日
	 * @version v1.0.0
	 */
	public void sendMail() {
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");// 获取JavaMailSender
		SimpleMailMessage mail = new SimpleMailMessage();
		try {
			mail.setTo("11@qq.com");// 接受者
			mail.setFrom("54haijiao@163.com");// 发送者
			mail.setSubject("邮件主题");// 主题
			mail.setText("springMail 的简单发送测试");// 邮件内容
			sender.send(mail);
			System.out.println("发送完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description：<br>
	 * 发送带有附件的邮件
	 * 
	 * @author JingYu.Dai
	 * @date 2015年12月19日
	 * @version v1.0.0
	 * @throws MessagingException
	 */
	public void sendFileMail() throws MessagingException {
		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");// 获取JavaMailSender
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mm, true, "utf-8");
		try {
			helper.setTo("95973@qq.com");// 接受者
			helper.setFrom("54haijiao@163.com");// 发送者
			helper.setSubject("邮件主题");// 主题
			helper.setText("邮件内容");// 邮件内容

			// 多个附件文件
			ClassPathResource in = new ClassPathResource("附件.txt");
			ClassPathResource in2 = new ClassPathResource("struts.xml");

			// MimeUtility.encodeWord()解决附件的文件名为中文问题
			helper.addAttachment(MimeUtility.encodeWord(in.getFilename()), in);
			helper.addAttachment(MimeUtility.encodeWord(in2.getFilename()), in2);

			sender.send(mm);
			System.out.println("发送完毕");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
		
	}

}
