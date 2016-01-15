package com.shinian.vo;

import java.io.Serializable;


public class NewsRespVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private String uid;	
	private int count;
	private int annexCate;
	
	
	public int getAnnexCate() {
		return annexCate;
	}
	public void setAnnexCate(int annexCate) {
		this.annexCate = annexCate;
	}
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
