package com.shinian.vo;

import java.io.Serializable;

public class UpdatePwdReqVo extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String email;
	private String pwd;
	private String newPwd;
	private String affirmPwd;

	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getAffirmPwd() {
		return affirmPwd;
	}
	public void setAffirmPwd(String affirmPwd) {
		this.affirmPwd = affirmPwd;
	}
	

}
