package com.shinian.vo;

import java.io.Serializable;

public class SignupReqVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String token;
	private String name;
	private int gender;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
		
}
