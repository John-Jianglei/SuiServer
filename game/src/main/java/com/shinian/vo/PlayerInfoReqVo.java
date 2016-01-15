package com.shinian.vo;

import java.io.Serializable;

public class PlayerInfoReqVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}
