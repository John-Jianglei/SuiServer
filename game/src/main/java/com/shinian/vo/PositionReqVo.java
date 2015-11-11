package com.shinian.vo;

import java.io.Serializable;

//	Get all NPC info of player
public class PositionReqVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String token;
	private int id;
	private int targetPosition;
	private String uid;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
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
	
	public int getTargetPosition() {
		return targetPosition;
	}
	public void setTargetPosition(int targetPosition) {
		this.targetPosition = targetPosition;
	}
		
}
