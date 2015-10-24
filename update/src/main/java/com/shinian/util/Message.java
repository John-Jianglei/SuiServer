package com.shinian.util;

import java.io.Serializable;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String filter = "代开发票|第六世纪|我叫小刘|尹华伟";//要过滤的字符用|分隔
	
	public final static int msgCode_correct = 200;
	
	public final static int msgCode_exception = 300;
	public final static String msg_exception = "服务器异常";
	public final static int msgCode_Token = 4000;
	public final static String msg_Token = "Token无效";
	
	public final static int msgCode_YingJ = 4001;
	public final static String msg_YingJ = "硬件信息不完善";
	
	public final static int  acc_Msgcode_Userno = 4002;
	public final static String  acc_Msg_Userno = "您输入的账号不存在,请重新输入";
	
	public final static int  acc_Msgcode_Pwd = 4003;
	public final static String  acc_Msg_Pwd = "您输入的密码错误,请重新输入";
	
	public final static int  acc_Msgcode_Reg_UserNames = 4004;
	public final static String  acc_Msg_Reg_UserNames = "您输入的账号已被注册,请重新输入";
	
	public final static int  acc_Msgcode_Req = 4005;
	public final static String  acc_Msg_Req = "参数错误";
	
	public final static int  acc_Msgcode_Sql = 4006;
	public final static String  acc_Msg_Sql = "数据库错误";
	
	public final static int  acc_Msgcode_TeamName = 4007;
	public final static String  acc_Msg_TeamName = "队名重复";
	
	public final static int  acc_Msgcode_UserNameNoExist = 4008;
	public final static String  acc_Msg_UserNameNoExist = "用户不存在";
	
	public final static int  acc_Msgcode_Reg_Null = 4009;
	public final static String  acc_Msg_Reg_Null = "密码不能为空";
	
	public final static int  acc_Msgcode_Reg_Pwd = 4010;
	public final static String  acc_Msg_Reg_Pwd = "两次输入的秘密不一致";
	
	public final static int  acc_Msgcode_Reg_UserName = 4011;
	public final static String  acc_Msg_Reg_UserName = "用户名不能含有非法字符";
	
	public final static int  acc_Msgcode_Reg_Namenull = 4012;
	public final static String  acc_Msg_Reg_Namenull = "账号不能为空";
	
	public final static int  acc_Msgcode_Update = 4013;
	public final static String  acc_Msg_Update = "更新失败";
	
	public final static int  acc_Msgcode_UserName = 4014;
	public final static String  acc_Msg_UserName = "请输入有效的邮箱注册账号";
	
	public final static int  acc_Msgcode_UpdatePwd = 4015;
	public final static String  acc_Msg_UpdatePwd = "旧密码不对";
	
	public final static int  acc_Msgcode_NewPwd = 4016;
	public final static String  acc_Msg_NewPwd = "新密码跟旧密码不能重复";
	
	public final static int  acc_Msgcode_Mail = 4017;
	public final static String  acc_Msg_Mail = "邮箱的格式不正确";
	
	public final static int  acc_Msgcode_sendMail = 4018;
	public final static String  acc_Msg_sendMail = "邮件发送失败";
	
	public final static int  acc_Msgcode_BindingMail = 4019;
	public final static String  acc_Msg_BindingdMail = "邮箱未绑定";
	
	public final static int  acc_Msgcode_token = 4020;
	public final static String  acc_Msg_token = "token不存在";
	
	public final static int accMsgCodeEmial = 4021;
	public final static String accMsgEmial = "您输入的账号不是邮箱,请重新输入";
	
	public final static int accMsgCodePwd = 4022;
	public final static String accMsgPwd = "抱歉,密码由6-20位数字或字母组成";
}
