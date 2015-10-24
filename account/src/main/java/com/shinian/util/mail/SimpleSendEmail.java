package com.shinian.util.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.LoggerFactory;

/**
 * 发送邮件工具类
 * 
 * @author QingLong.Cai
 * @date 2011-07-19
 */
public class SimpleSendEmail {

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("mail");
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(SimpleSendEmail.class);

	/**
	 * 发送邮件
	 * <pre>
	 * 		List<String> recipients = new ArrayList<String>();
		recipients.add("hello@mobimtech.com");
		List<String> carbonCopy = new ArrayList<String>();
		carbonCopy.add("world@mobimtech.com");
		SimpleSendEmail.sendEmail("测试邮件发送", "正文内容", recipients, carbonCopy);
		</pre>
	 * @param subject 邮件主题
	 * @param mailContent 邮件正文
	 * @param recipients 邮件接收人
	 * @param carbonCopy 邮件抄送人
	 * @throws Exception
	 */
	public static boolean sendEmail(String subject, String mailContent, List<String> recipients, List<String> carbonCopy) {
		log.info("准备发送邮件...");
		log.info("发送邮件邮件信息,主题: {}，内容： {}，接收者: {}，抄送人: {}",
				new Object[] { subject, mailContent, StringUtils.join(recipients, ","), StringUtils.join(carbonCopy, ",") });
		boolean isSuccess = true;
		try {
			Email email = new SimpleEmail();
			email.setHostName(RESOURCE_BUNDLE.getString("email.hostname"));
			if (!Boolean.parseBoolean(RESOURCE_BUNDLE.getString("email.smtpportusedefault")))
				email.setSmtpPort(Integer.parseInt(RESOURCE_BUNDLE.getString("email.smtpport")));
			email.setAuthenticator(new DefaultAuthenticator(RESOURCE_BUNDLE.getString("email.username"), RESOURCE_BUNDLE.getString("email.password")));
			email.setTLS(Boolean.parseBoolean(RESOURCE_BUNDLE.getString("email.tls")));
			email.setFrom(RESOURCE_BUNDLE.getString("email.from"),RESOURCE_BUNDLE.getString("email.fromname"));

			for (String recipient : recipients) {
				if(StringUtils.isNotBlank(recipient))
				email.addTo(recipient);
			}
			if (carbonCopy != null && !carbonCopy.isEmpty()) {
				for (String cc : carbonCopy) {
					if(StringUtils.isNotBlank(cc))
						email.addCc(cc);
				}
			}
			email.setCharset("UTF-8");
			email.setSubject(subject);
			email.buildMimeMessage();
			email.getMimeMessage().setContent(mailContent, "text/html;charset=UTF-8");
			email.sendMimeMessage();
			log.info("邮件发送成功");
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			log.info("邮件发送失败", e);
		}
		return isSuccess;
	}
	
	public static void main(String[] args) {
		List<String> recipients = new ArrayList<String>();
		recipients.add("bale@91fifa.com");
		recipients.add("bale@91fifa.com");
		boolean flag = sendEmail("邮箱绑定激活","test",recipients,new ArrayList<String>());
		System.out.println("flag="+flag);//
	}

}
