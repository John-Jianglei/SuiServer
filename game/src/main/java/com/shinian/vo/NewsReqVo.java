package com.shinian.vo;

import java.io.Serializable;


public class NewsReqVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private String uid;		
	private int id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
