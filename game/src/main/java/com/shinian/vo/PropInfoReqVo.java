package com.shinian.vo;

import java.io.Serializable;


public class PropInfoReqVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private int action;		//	1: add property;
	private int comId;
	private String uid;		
	private int amount;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
		
}
