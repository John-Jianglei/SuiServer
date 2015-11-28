package com.shinian.vo;

import java.io.Serializable;

public class RewardVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}