package com.shinian.vo;

import java.io.Serializable;

public class RegisterRespVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int uid; //>0: success, =0:fail
	private String token;
	private String qihooUserId;

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

	public String getQihooUserId() {
		return qihooUserId;
	}

	public void setQihooUserId(String qihooUserId) {
		this.qihooUserId = qihooUserId;
	}

	
	
}
