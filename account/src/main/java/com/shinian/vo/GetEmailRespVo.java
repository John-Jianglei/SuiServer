package com.shinian.vo;

import java.io.Serializable;

public class GetEmailRespVo extends BaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int emailStatus;///状态 1 未绑定 2已绑定
	private String email;//邮箱
	
	public int getEmailStatus() {
		return emailStatus;
	}
	public void setEmailStatus(int emailStatus) {
		this.emailStatus = emailStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
