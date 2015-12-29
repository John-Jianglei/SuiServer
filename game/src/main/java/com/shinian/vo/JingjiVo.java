package com.shinian.vo;

import java.io.Serializable;

public class JingjiVo  extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String 	uid;		//进攻方uid
	private String 	duid;		//防御方uid
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public String getDuid() {
		return duid;
	}
	public void setDuid(String duid) {
		this.duid = duid;
	}

}
