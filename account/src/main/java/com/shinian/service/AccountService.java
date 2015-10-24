package com.shinian.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.shinian.dao.AccTokenDao;
import com.shinian.dao.AccountDao;
import com.shinian.util.Md5;
import com.shinian.util.Message;
import com.shinian.util.MessageRespVo;
import com.shinian.util.mail.EmailProps;
import com.shinian.util.mail.EmailUtil;
import com.shinian.util.mail.MailSenderInfo;
import com.shinian.util.mail.SimpleMailSender;
import com.shinian.vo.AccInfo;
import com.shinian.vo.AccInfoEmail;
import com.shinian.vo.AccPushVo;
import com.shinian.vo.AccToken;
import com.shinian.vo.BindEmailReqVo;
import com.shinian.vo.BindIngMailReqVo;
import com.shinian.vo.BindingMailRespVo;
import com.shinian.vo.CommonReqVo;
import com.shinian.vo.FindPwdReqVo;
import com.shinian.vo.GetEmailRespVo;
import com.shinian.vo.GetPushListReqVo;
import com.shinian.vo.LoginReqVo;
import com.shinian.vo.LoginRespVo;
import com.shinian.vo.RegisterReqVo;
import com.shinian.vo.RegisterRespVo;
import com.shinian.vo.UpdatePwdReqVo;

@Service
public class AccountService {	
	@Autowired
	AccountDao accountDao;
	@Autowired
	AccTokenDao acctokenDao;
	private static Logger log = Logger.getLogger(AccountService.class);

	public MessageRespVo register(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {
		MessageRespVo message = new MessageRespVo();

		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		RegisterReqVo rrv = JSON.parseObject(data.toString(), RegisterReqVo.class);
		
		//RegisterReqVo rrv = JSON.parseObject(jsonReq, RegisterReqVo.class);
		if (null == rrv) {
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}

		message.setTs(ts);

		if (null == rrv.getEmail() || "".equals(rrv.getEmail())) {

			message.setCode(Message.acc_Msgcode_Reg_Namenull);
			message.setMsg(Message.acc_Msg_Reg_Namenull);
			return message;
		}
		if( rrv.getEmail().length() > 20 || rrv.getEmail().length() < 6 )
		{
			message.setCode(Message.accMsgCodeEmialLength);
			message.setMsg(Message.accMsgEmialLength);
			return message;
		}
//		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String check = "^[a-zA-Z0-9]+$";
		Pattern regex = Pattern.compile(check);

		Matcher matcher = regex.matcher(rrv.getEmail());

		if (!matcher.matches()) {
			message.setCode(Message.accMsgCodeEmialLength);
			message.setMsg(Message.accMsgEmialLength);
			return message;

		}

		if (null == rrv.getPassword() || "".equals(rrv.getPassword())) {

			message.setCode(Message.acc_Msgcode_Reg_Null);
			message.setMsg(Message.acc_Msg_Reg_Null);
			return message;
		}
		
		String checkPwd = "^[a-zA-Z0-9_]+$";
		Pattern regexPwd = Pattern.compile(checkPwd);

		Matcher matcherPwd = regexPwd.matcher(rrv.getPassword());
		
		if( rrv.getPassword().length() < 6 || !matcherPwd.matches() )
		{
			message.setCode(Message.accMsgCodePwd);
			message.setMsg(Message.accMsgPwd);
			return message;
		}
		AccInfo ac = accountDao.isUserNoExist(rrv.getEmail());

		if (ac != null) {

			message.setCode(Message.acc_Msgcode_Reg_UserNames);
			message.setMsg(Message.acc_Msg_Reg_UserNames);
			return message;
		}

		RegisterRespVo rr = this.insert(rrv.getEmail(), rrv.getPassword(),rrv.getChannel());
		log.info("register new uid = " + rr.getUid());

		// login success l
		message.setCode(Message.msgCode_correct);

		message.setData(rr);
		// String jsonResp = JSON.toJSONString(rr);
		// ResponseParamUtil.writeJsonMessage(response, jsonResp);
		return message;
	}

	public MessageRespVo login(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {

		MessageRespVo message = new MessageRespVo();
		
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		LoginReqVo lrv = JSON.parseObject(data.toString(), LoginReqVo.class);
		LoginRespVo lr = new LoginRespVo();
		if (null == lrv) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}

		message.setTs( ts );

//		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		String check = "^[a-zA-Z0-9_]+$";
		Pattern regex = Pattern.compile(check);

		Matcher matcher = regex.matcher(lrv.getEmail());

		if (!matcher.matches()) {
			message.setCode(Message.accMsgCodeEmialLength);
			message.setMsg(Message.accMsgEmialLength);
			return message;

		}
		AccInfo ac = accountDao.isUserNoExist(lrv.getEmail());
		if (ac == null) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}

		ac = accountDao.isUserExist(lrv.getEmail(), lrv.getPassword());

		if (null == ac) {

			message.setCode(Message.acc_Msgcode_Pwd);
			message.setMsg(Message.acc_Msg_Pwd);
			return message;
		}

		// login success l
		message.setCode(Message.msgCode_correct);
		lr.setUid(ac.getUid());
		lr.setToken(genToken(ac.getUid(),ac.getChannel()));// / 生成token
		message.setData(lr);

		// jsonResp = JSON.toJSONString(lr);
		// ResponseParamUtil.writeJsonMessage(response, jsonResp);
		return message;
	}

	public MessageRespVo bindingMail(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {
		MessageRespVo message = new MessageRespVo();
		
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		BindIngMailReqVo lrv = JSON
				.parseObject(data.toString(), BindIngMailReqVo.class);

		if (null == lrv) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}

		message.setTs( ts );

		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		
		Pattern regex = Pattern.compile(check);

		Matcher matcher = regex.matcher( lrv.getEmail() );

		if (!matcher.matches()) {
			message.setCode(Message.acc_Msgcode_Mail);
			message.setMsg(Message.acc_Msg_Mail);
			return message;

		}

		AccInfo acc = accountDao.isUserNoExistUid( lrv.getUid() );
		if (acc == null) {
			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}
		AccInfo accEmail = accountDao.isEmailNoExist( lrv.getEmail(),lrv.getUid() );
		if( null != accEmail )
		{
			AccInfoEmail codevo = accountDao.getEmailById( accEmail.getUid() );
			
			if(  null != codevo && 2 == codevo.getEmailStatus()  )
			{
				message.setCode(Message.accMsgCodeEmailNull);
				message.setMsg(Message.accMsgEmailNull);
				return message;
			}
		}
		
		
		
		AccInfoEmail codevo = accountDao.getEmailById( lrv.getUid() );
		if( null != codevo && 2 == codevo.getEmailStatus() )
		{
			message.setCode(Message.accMsgCodeEmailCodeEmail);
			message.setMsg(Message.accMsgEmailCodeEmail);
			return message;
		}
		
		
		
		String code = getRandomSix()+"";
		String content = getEmailContent(code,lrv.getTeamName());
//		sendMail(lrv.getEmail(), title, content);
		sendEmailData(lrv.getEmail(), content);
		accountDao.updateBindMail(lrv.getEmail(), lrv.getUid());
		if( null == codevo )
		{
			String validTime = addTime(30);
			accountDao.insertEmail(lrv.getUid(), 1, code, validTime);
		}else
		{
			String validTime = addTime(30);
			codevo.setEmailCode( code );
			codevo.setValidTime(validTime);
			
			accountDao.updateEmail(codevo);
		}
		
		// update success l
		message.setCode(Message.msgCode_correct);
		message.setData( new Object() );
		
		return message;
	}

	public MessageRespVo getEmail(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {
		MessageRespVo message = new MessageRespVo();
		
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		BindIngMailReqVo lrv = JSON
				.parseObject(data.toString(), BindIngMailReqVo.class);

		if (null == lrv) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}

		message.setTs( ts );
		GetEmailRespVo resp = new GetEmailRespVo();
		
		int emailStatus = 1;
		String email = "";
		AccInfoEmail emmail = accountDao.getEmailById( lrv.getUid() );
		if( null != emmail && 2 == emmail.getEmailStatus() )
		{
			emailStatus = 2;
			AccInfo info = accountDao.getAccInfoById( lrv.getUid() );
			email = info.getBindEmail();
		}
		
		resp.setEmail(email);
		resp.setEmailStatus(emailStatus);
		
		
		// update success l
		message.setCode(Message.msgCode_correct);
		message.setData( resp );
		
		return message;
	}
	
	
	public MessageRespVo bindEmail(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {
		MessageRespVo message = new MessageRespVo();
		
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		BindEmailReqVo lrv = JSON
				.parseObject(data.toString(), BindEmailReqVo.class);

		if (null == lrv) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}

		message.setTs( ts );
		BindingMailRespVo resp = new BindingMailRespVo();
		
		AccInfoEmail email = accountDao.getEmailById( lrv.getUid() );
		
		AccInfo info = accountDao.getAccInfoById( lrv.getUid() );
		
		synchronized (info.getBindEmail())
		{
			if( null == email || !lrv.getEmailCode().equals( email.getEmailCode()))
			{
				message.setCode(Message.accMsgCodeEmailCode);
				message.setMsg(Message.accMsgEmailCode);
				return message;
			}
			if( 2 == email.getEmailStatus() )
			{
				message.setCode(Message.accMsgCodeEmailCodeEmail);
				message.setMsg(Message.accMsgEmailCodeEmail);
				return message;
			}
			
			AccInfo accEmail = accountDao.isEmailNoExist( info.getBindEmail(),lrv.getUid() );
			if( null != accEmail )
			{
				AccInfoEmail codevo = accountDao.getEmailById( accEmail.getUid() );
				
				if( null != codevo && 2 == codevo.getEmailStatus()  )
				{
					message.setCode(Message.accMsgCodeEmailNull);
					message.setMsg(Message.accMsgEmailNull);
					return message;
				}
			}
			
			
			if( isEnd( email.getValidTime() ) )
			{
				message.setCode(Message.accMsgCodeEmailCodeTime);
				message.setMsg(Message.accMsgEmailCodeTime);
				return message;
			}
			email.setEmailStatus( 2 );
			accountDao.updateEmail(email);
		}
		
		
		
		resp.setEmail( info.getBindEmail() );
		// update success l
		message.setCode(Message.msgCode_correct);
		message.setData( resp );
		
		return message;
	}
	
	
	public MessageRespVo updatePwd(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {
		MessageRespVo message = new MessageRespVo();
		
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		UpdatePwdReqVo lrv = JSON.parseObject(data.toString(), UpdatePwdReqVo.class);

		if (null == lrv) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}
		message.setTs( ts );

		if (null == lrv.getPwd() || "".equals(lrv.getPwd())
				|| null == lrv.getNewPwd() || "".equals(lrv.getNewPwd())
				|| null == lrv.getAffirmPwd() || "".equals(lrv.getAffirmPwd())) {
			message.setCode(Message.acc_Msgcode_Reg_Null);
			message.setMsg(Message.acc_Msg_Reg_Null);
			return message;
		}

		AccInfo acc = accountDao.getAccInfo(lrv.getEmail());
		if (acc == null) {
			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}
		acc = accountDao.isUserExist(lrv.getEmail(), lrv.getPwd());
		if (null == acc) {
			message.setCode(Message.acc_Msgcode_UpdatePwd);
			message.setMsg(Message.acc_Msg_UpdatePwd);
			return message;
		}
		if (!lrv.getNewPwd().equals(lrv.getAffirmPwd())) {
			message.setCode(Message.acc_Msgcode_Reg_Pwd);
			message.setMsg(Message.acc_Msg_Reg_Pwd);
			return message;
		}
		int result = accountDao.updatePwd(lrv.getAffirmPwd(), lrv.getEmail());
		if (result <= 0) {
			message.setCode(Message.acc_Msgcode_Update);

			message.setMsg(Message.acc_Msg_Update);
			return message;

		}

		message.setCode(Message.msgCode_correct);
		// jsonResp = JSON.toJSONString(lr);
		// ResponseParamUtil.writeJsonMessage(response, jsonResp);
		return message;
	}

	public MessageRespVo findPwd(HttpServletRequest request,
			HttpServletResponse response, String jsonReq) {

		

		MessageRespVo message = new MessageRespVo();
		
		CommonReqVo crv = JSON.parseObject(jsonReq, CommonReqVo.class);
		if(null == crv){//todo:change return value
			message.setCode(Message.acc_Msgcode_Req);
			message.setMsg(Message.acc_Msg_Req);
			return message;
		}
		
		int ts = crv.getTs();
		Object data = crv.getData();
		
		FindPwdReqVo lrv = JSON.parseObject(data.toString(), FindPwdReqVo.class);
		if (null == lrv) {

			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}
		message.setTs( ts );

		AccInfo acc = accountDao.getAccInfo(lrv.getEmail());
		if (acc == null) {
			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}
		if (null == acc.getBindEmail() || "".equals(acc.getBindEmail())) {
			message.setCode(Message.acc_Msgcode_Userno);
			message.setMsg(Message.acc_Msg_Userno);
			return message;
		}

		int max = 999999;
		int min = 100000;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;

		// login success l

		boolean mail = this.sendMail(acc.getBindEmail(), "您游戏帐号的密码为", "您的新密码为："
				+ s);

		if (!mail) {
			message.setCode(Message.acc_Msgcode_sendMail);

			message.setMsg(Message.acc_Msg_sendMail);
			return message;
		}
		int result = accountDao.updatePwd(s + "", lrv.getEmail());
		if (result <= 0) {
			message.setCode(Message.acc_Msgcode_Update);

			message.setMsg(Message.acc_Msg_Update);
			return message;

		}

		message.setCode(Message.msgCode_correct);

		// jsonResp = JSON.toJSONString(lr);
		// ResponseParamUtil.writeJsonMessage(response, jsonResp);
		return message;
	}


	public List<AccInfo> pushRegList(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		GetPushListReqVo gcrv = JSON.parseObject(jsonStr, GetPushListReqVo.class);
		List<AccInfo> accList = accountDao.getAccInfoList( gcrv.getStartTime() );
		if( null == accList )
		{
			accList = new ArrayList<AccInfo>();
		}
		return accList;
	}
	
	public List<AccPushVo> pushLoginList(HttpServletRequest request, HttpServletResponse response,String jsonStr)
	{
		GetPushListReqVo gcrv = JSON.parseObject(jsonStr, GetPushListReqVo.class);
		List<AccPushVo> pushList = acctokenDao.getAccPushList(gcrv.getStartTime(), gcrv.getEndTime());
		if( null == pushList )
		{
			pushList = new ArrayList<AccPushVo>();
		}
		return pushList;
	}
	
	private boolean sendMail(String toAdders, String title, String content) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		//通过邮箱地址解析出smtp服务器，对大多数邮箱都管用
		final String smtpHostName = "smtp." + toAdders.split("@")[1];
		
		mailInfo.setMailServerHost( smtpHostName );
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("console@91fifa.com");
		mailInfo.setPassword("91fifa");// 您的邮箱密码
		mailInfo.setFromAddress("console@91fifa.com");
		mailInfo.setToAddress(toAdders);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();

		// sms.sendHtmlMail(mailInfo);//发送html格式
//		return sms.sendTextMail(mailInfo);// 发送文体格式
		boolean result = sms.sendHtmlMail(mailInfo);
		System.out.println("result: "+result );
		System.out.println("mailInfo: "+JSON.toJSONString( mailInfo ) );
		return result;

	}
	private void sendEmailData(String email, String content){
		System.out.println("sendDayReportEmail email:" + email);
		
		EmailProps ep = new EmailProps();
		
//		final String smtpHostName = "smtp." + email.split("@")[1];
		String smtpHostName = "smtp.exmail.qq.com";
		
		List<String> recipients = new ArrayList<String>();
		recipients.add(email);
		ep.setRecipients(recipients);
		ep.setEmailHost( smtpHostName );
		ep.setUseDefaultSmtpPort(true);
		ep.setUsername("console@91fifa.com");
		ep.setPassword("91fifa");
		ep.setTLS(false);
		ep.setFromAddress("console@91fifa.com");
		ep.setFromDisplayName("传奇十一人");
		
		ep.setSubject( getEmailTitle() );
		ep.setContent(content);
		
		EmailUtil eu = new EmailUtil(ep);
		Thread newThread = new Thread(eu);
		newThread.start();
	}
	
	 private  int getRandomSix()
	 {
		  Random random = new Random();
		  return random.nextInt(899999)+100000;
	}
	private String getEmailTitle()
	{
		String title = "《传奇十一人》电子邮件确认";
		return title;
	}
	private String getEmailContent(String code,String teamName)
	{
//		String content = "【传奇十一人】您正在绑定邮箱，验证码："+getRandomSix();
		StringBuffer content = new StringBuffer("<!DOCTYPE HTML>");
		
		content.append("<html lang='en-US'>");
		content.append("<head><meta charset='UTF-8'><title>传奇十一人绑定邮箱</title></head><body>");
		content.append("</font></p><p> <font>尊敬的"+teamName+" ，您好！");
		content.append("<p> <font>您的绑定邮箱验证码为：</font> <b><font color = \"#FF5809\"> ");
		content.append(code);
		content.append("</font></b></p><p> <font>请在游戏设置中的绑定邮箱界面填写验证码，完成验证。验证码的有效期为30分钟。</font></p>");
		content.append("</font></p><p> <font>如果需要了解更多信息，请加入官方QQ群：333590195</font></p>");
		content.append("</font></p><p> <font>上海时年信息科技有限公司</font></p>");
		
		content.append("     </body></html>");
		
		
		return content.toString();
	}
	public AccToken checkToken(int uid,String token) {
		AccToken at = acctokenDao.getToken(uid);
//		if(at == null){
//			return 0;
//		}
//		
//		int result = token.equals(at.getToken()) == true?1:0;
//		if(result == 1){			
//			return at.getExpire() - System.currentTimeMillis() > 0 == true?1:0;
//		}
		
		return at;
	}
	@Transactional
	public RegisterRespVo insert(final String email,final String password,final int channel )
	{
		RegisterRespVo rr = new RegisterRespVo();
		int uid = accountDao.register(email, password,channel);
		log.info("register new uid = " + uid);
		rr.setUid(uid);
		rr.setToken(genToken(uid,channel));
		return rr;
	}
	public String genToken(int uid,int channelId) {

		long expire = (long) System.currentTimeMillis() + 5*24 * 60 * 60 * 1000;
		String token = Md5.getToken(uid + "", true);
		acctokenDao.insert(uid, token, expire);
		//acctokenDao.insertPush(uid, channelId, getCurrentTime());
		return token;
	}
	/**
	 * 得到当前时间字符串
	 * 
	 * @param args
	 */
	public static String getCurrentTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(new Date());
	}
	public static String addTime( long addTime)
	{
		Date bDate = new Date();
		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.setTime(bDate);
		calendar.add(Calendar.MINUTE, (int)addTime);
		return  DateTostr(calendar.getTime());
	}
	/**
	 *  data 转 字符
	 * @param date
	 * @return
	 */
	public static String DateTostr(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date_new="";
		try 
		{
			date_new = df.format(date);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return date_new;
	}	
	public static boolean isEnd(String endTime)
	{
		boolean isEnd = false;
		if(null != endTime)
		{
			Date now = new Date();
			Date endDate = strToDate(endTime);
			isEnd = endDate.before(now);//结束时间在当前时间之前
		}
		return isEnd;
	}
	public static Date strToDate(String dateString) {
		Date date = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df.parse(dateString);
		} catch (Exception ex) {
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = df2.parse(dateString);
			} catch (Exception ex2) {
				date = new Date();
			}
		}
		return date;
	}
}