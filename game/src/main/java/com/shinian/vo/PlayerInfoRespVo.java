package com.shinian.vo;

import java.io.Serializable;

public class PlayerInfoRespVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int strength;
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

}
