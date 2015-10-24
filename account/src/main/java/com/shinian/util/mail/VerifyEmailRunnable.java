package com.shinian.util.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


//public class VerifyEmailRunnable {
	public class VerifyEmailRunnable implements Runnable {

	private static Logger log = Logger.getLogger(VerifyEmailRunnable.class);
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("mail");
	private String email = "";
	private String username = "";
	private String verifyContent = "";
	public VerifyEmailRunnable(String username, String email,String content){
		this.username = username;
		this.email = email;
		this.verifyContent = content;
	}

	@Override
	public void run() {
		try {
			String subject = ""; //RESOURCE_BUNDLE.getString("email.verifySubject");
			
			StringBuffer content = new StringBuffer("<!DOCTYPE HTML>");
			content.append("<html lang='en-US'>");
			content.append("<head><meta charset='UTF-8'><title>艾米范未审核通知</title></head><body>");
			content.append("<table width='100%' style='border:0px solid #dbe2f0;border-radius:3px;border-spacing:0;border-collapse:collapse;'>");
			content.append("<tbody><tr><td valign='top' style='padding:0;'>");
			content.append("<font style='font:16px/30px Microsoft YaHei;color:#000;display:block;'>"+username+":"+"<br />");
			content.append("<tr><td height='20'></td></tr><tr><td valign='top' style='padding:0;'>");
			content.append("<font style='font:16px/30px Microsoft YaHei;color:#000;display:block;'>"+RESOURCE_BUNDLE.getString("email.verifyHide")+"<br />");
			content.append("<tr><td height='20'></td></tr><tr><td valign='top' style='padding:0;'>");
			content.append("<font style='font:16px/30px Microsoft YaHei;color:#000;display:block;'>"+verifyContent + RESOURCE_BUNDLE.getString("email.verifyNotify")+"<br />");
			content.append("<tr><td height='20'></td></tr><tr><td valign='top' style='padding:0;'>");
			content.append("<font style='font:16px/30px Microsoft YaHei;color:#000;display:block;'>"+RESOURCE_BUNDLE.getString("email.vefifyBottom")+"<br />");
			content.append("<tr><td height='20'></td></tr><tr><td valign='top' style='padding:0;'>");
			content.append("<font style='font:16px/30px Microsoft YaHei;color:#000;display:block;'>"+RESOURCE_BUNDLE.getString("email.authBottom1")+"<br />");	
			content.append("<tr><td valign='top' style='padding:0;'>");
			content.append("<font style='font:16px/30px Microsoft YaHei;color:#000;display:block;'>"+RESOURCE_BUNDLE.getString("email.authBottom2")+"<br />");
			content.append("</tr></tbody></table></body></html>");
			
			List<String> recipients = new ArrayList<String>();
			recipients.add(email);
			SimpleSendEmail.sendEmail(subject, content.toString(), recipients, new ArrayList<String>());
		} catch (Exception e) {
			log.error("发邮件线程执行失败"+e.getMessage());
		}
	}
	
	
	public static void main(String[] args) {
		
		String subject = "后台邮件测试";
		String content = "test";
		List<String> recipients = new ArrayList<String>();
		recipients.add("bale@91fifa.com");
		
		SimpleSendEmail.sendEmail(subject, content.toString(), recipients, new ArrayList<String>());
	}
	
}