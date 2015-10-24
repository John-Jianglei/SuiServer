package com.shinian.vo;

import java.io.Serializable;

public class LoginRespVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int uid;
	private String token;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
			
}
