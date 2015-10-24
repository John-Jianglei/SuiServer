package com.shinian.vo;

import java.io.Serializable;

public class LoginReqVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
		
}
