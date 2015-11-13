package com.shinian.vo;

import java.io.Serializable;

//Get single NPC info
public class NpcAddReqVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private String token;
	private int comId;
	private String uid;
	
	
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
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	
		
}
