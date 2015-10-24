package com.shinian.vo;

import java.io.Serializable;

//Get single NPC info
public class NpcInfoReqVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String token;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
		
}
