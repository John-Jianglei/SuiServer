package com.shinian.vo;

import java.io.Serializable;

public class BindIngMailReqVo extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}



	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}



	private String email;
	private int uid;
	private String teamName;
	


	
	

}
