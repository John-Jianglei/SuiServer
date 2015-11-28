package com.shinian.vo;

import java.io.Serializable;


public class BattleReqVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private String offUid;		
	private String defUid;		
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getOffUid() {
		return offUid;
	}
	public void setOffUid(String offUid) {
		this.offUid = offUid;
	}
	
	public String getDefUid() {
		return defUid;
	}
	public void setDefUid(String defUid) {
		this.defUid = defUid;
	}
}
