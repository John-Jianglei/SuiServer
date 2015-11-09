package com.shinian.vo;

import java.io.Serializable;


public class PropInfoReqVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private int comId;
	private String token;
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
	
		
}
