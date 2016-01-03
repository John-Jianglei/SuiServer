package com.shinian.vo;

import java.io.Serializable;


public class NewsRespVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private String uid;	
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
