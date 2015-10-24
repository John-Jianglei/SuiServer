package com.shinian.vo;

import java.io.Serializable;

public class AccInfoEmail extends BaseObject implements Serializable{
	
//	DROP TABLE IF EXISTS `acc_info_email`;
//	CREATE TABLE `acc_info_email` (
//	  `uid` int(11) NOT NULL COMMENT '用户的id',
//	  `email_status` int(10) NOT NULL COMMENT '绑定邮箱的状态 1 未绑定 2 已绑定',
//	  `email_code` varchar(100) NOT NULL COMMENT '验证码',
//	  `valid_time` varchar(50) DEFAULT NULL COMMENT '验证码有效时间',
//	  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新的时间',
//	  PRIMARY KEY (`uid`)
//	) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;
	private int emailStatus;
	private String emailCode;
	private String validTime;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	
	
	
	
}
