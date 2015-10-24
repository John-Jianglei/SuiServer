package com.shinian.util.mail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 * 发送邮件工具类
 * 
 * @author Jifeng.ma
 * @date 2013-06-17
 */
public class EmailUtil implements Runnable{	
	private EmailProps ep = null;
	
	public EmailUtil(EmailProps ep){
		this.ep = ep;
	}
	
	private static boolean sendEmail(EmailProps ep) {
		Date cur = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("preparing to send mail:" + sdf.format(cur));		
		
		boolean isSuccess = true;
		try {
			Email email = new SimpleEmail();
			email.setHostName(ep.getEmailHost());
			if (!ep.isUseDefaultSmtpPort()){
				email.setSmtpPort(ep.getCustomPort());
			}
			email.setAuthenticator(new DefaultAuthenticator(ep.getUsername(), ep.getPassword()));
			email.setTLS(ep.isTLS());
			email.setFrom(ep.getFromAddress(),ep.getFromDisplayName());

			for (String recipient : ep.getRecipients()) {
				if(StringUtils.isNotBlank(recipient)){
					email.addTo(recipient);
				}
			}
			 
			for (String cc : ep.getCarbonCopy()) {
				if(StringUtils.isNotBlank(cc)){
					email.addCc(cc);
				}
			}
			
			email.setCharset("UTF-8");
			email.setSubject(ep.getSubject());
			email.buildMimeMessage();
			email.getMimeMessage().setContent(ep.getContent(), "text/html;charset=UTF-8");
			
			email.sendMimeMessage();			
			System.out.println("send mail successfully");
		} catch (Exception e) {
			e.printStackTrace();
			
			isSuccess = false;
			System.out.println("fail to send mail");
		}
		
		return isSuccess;
	}

	@Override
	public void run() {
		sendEmail(ep);
	}
	
	public static void main(String[] args) {
		EmailProps ep = new EmailProps();
		
		ep.setEmailHost("smtp.exmail.qq.com");
		ep.setUseDefaultSmtpPort(true);
//		ep.setUsername("console@91fifa.com");
//		ep.setPassword("91fifa");
		ep.setUsername("bale@91fifa.com");
		ep.setPassword("ting5126331");
		ep.setTLS(false);
		ep.setFromAddress("bale@91fifa.com");
		ep.setFromDisplayName("后台系统");
		
		List<String> recipients = new ArrayList<String>();
		recipients.add("bale@91fifa.com");
		ep.setRecipients(recipients);
		
		ep.setSubject("mail subject test");
		
//		String ctt = "<table width='100%' style='border:0px solid #dbe2f0;border-radius:3px;border-spacing:0;border-collapse:collapse;'>  ";
		
//		StringBuffer content = new StringBuffer("<!DOCTYPE HTML>");
//		content.append("<html lang='en-US'>");
//		content.append("<head><meta charset='UTF-8'><title>传奇十一人日报数据</title></head><body>");
//		final String  ccccc = "<table cellspacing='0' style='margin: 0px; padding: 0px; resize: none; border: 1px solid rgb(210, 210, 210); width: 1098px; font-family: 微软雅黑, 'Lucida Grande', Geneva, Arial, Verdana, 'Lucida Sans Unicode', Helvetica, sans-serif;'>                                                                                                                                                                                                                                                                                                                                                               "    +  
//		"     <tbody style='margin: 0px; padding: 0px; resize: none;'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            "    +  
//		"     <tr style='margin: 0px; padding: 0px; resize: none;'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 99px;'>日期</th>      "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 53px;'>登陆</th>      "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 54px;'>新增</th>      "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 54px;'>活跃</th>      "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 99px;'>完成引导</th>  "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 99px;'>付费用户</th>  "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 99px;'>新增付费</th>  "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 100px;'>付费率</th>   "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 130px;'>付费金额</th> "    +  
//		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 130px;'>ARPPU</th> "    +  
////		"         <th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 0px; border-left-width: 1px; border-left-color: white; border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%, rgb(223, 223, 223) 100%); text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; box-shadow: white 0px 1px 0px inset; width: 102px;'>ARPPU</th>                                                                       "    +  
//		"     </tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               "    +  
//		"     <tr style='margin: 0px; padding: 0px; resize: none;'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>2014-10-20</td>                                                                                                                                                                                                                                                                                  "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>4</td>                                                                                                                                                                                                                                                                                        "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                          "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>4</td>                                                                                                                                                                                                                                                                                       "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                    "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                          "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                       "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                      "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                        "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                        "    +  
////		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 0px; border-left-width: 1px; border-left-color: white; border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                                                              "    +  
//		"     </tr>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               "    +  
//		"     <tr style='margin: 0px; padding: 0px; resize: none;'>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>2014-10-17</td>                                                                                                                                                                                                                                                                                                                        "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>3</td>                                                                                                                                                                                                                                                                                                                              "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                                "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>3</td>                                                                                                                                                                                                                                                                                                                             "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                          "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                                "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                             "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                            "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                              "    +  
//		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                              "    +  
////		"         <td  style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 0px; border-left-width: 1px; border-left-color: white; border-left-style: solid; word-wrap: break-word;'>0</td>                                                                                                                                                                                                                                                                                                                                                                                                    "    +  
//		"     </tr></tbody></table> ";

		StringBuffer content = new StringBuffer("<!DOCTYPE HTML>");
		content.append("<html lang='en-US'>");
		content.append("<head><meta charset='UTF-8'><title>传奇十一人日报数据</title></head><body>");
		content.append("<table cellspacing='0' style='margin: 0px; padding: 0px; resize: none; border: 1px solid rgb(210, 210, 210); ");
		content.append("width: 1098px; font-family: 微软雅黑, 'Lucida Grande', Geneva, Arial, Verdana, 'Lucida Sans Unicode', Helvetica, sans-serif;");
		content.append(" <tbody style='margin: 0px; padding: 0px; resize: none;'>");
		
		String tr = " <tr style='margin: 0px; padding: 0px; resize: none;'>  ";
		String th = "<th scope='col' style='margin: 0px; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; " +
				"border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; " +
				"border-left-style: solid; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: rgb(178, 178, 178); " +
				"background-image: -webkit-linear-gradient(top, rgb(241, 241, 241) 0%%, rgb(223, 223, 223) 100%%); " +
				"text-shadow: white 0px 1px 0px; font-weight: normal; -webkit-box-shadow: white 0px 1px 0px inset; " +
				"box-shadow: white 0px 1px 0px inset; width: %dpx;'>%s</th>";
		String td = "<td  style='margin: center; padding: 5px 4px 4px 15px; resize: none; color: rgb(22, 22, 22); border-right-width: 1px; " +
				"border-right-color: rgb(210, 210, 210); border-right-style: solid; border-left-width: 1px; border-left-color: white; " +
				"border-left-style: solid; background-color: rgb(240, 240, 240); word-wrap: break-word;'>%s</td>";
		
		content.append(tr);
		content.append(String.format(th, new Object[]{90, "日期"}));
		content.append(String.format(th, new Object[]{100, "服务器"}));
		content.append(String.format(th, new Object[]{80, "登陆人数"}));
		content.append(String.format(th, new Object[]{80, "新增人数"}));
		content.append(String.format(th, new Object[]{80, "充值人数"}));
		content.append(String.format(th, new Object[]{80, "充值金额"}));
		
		content.append(tr);
		content.append(String.format(td, new Object[]{"2014-10-21"}));
		content.append(String.format(td, new Object[]{"桑巴荣耀一区"}));
		content.append(String.format(td, new Object[]{"200"}));
		content.append(String.format(td, new Object[]{"300"}));
		content.append(String.format(td, new Object[]{"400"}));
		content.append(String.format(td, new Object[]{"500"}));
		content.append("</tr>");
		content.append("     </tbody></table></body></html>");		
		
		ep.setContent(content.toString());
		
		boolean flag = sendEmail(ep);		
		System.out.println("mail sent flag="+flag);
	}
}
