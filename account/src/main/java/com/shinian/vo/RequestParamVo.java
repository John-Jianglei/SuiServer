package com.shinian.vo;

import java.io.Serializable;

public class RequestParamVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String acid;
	private String ver;
	
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getVer() {
		return ver;
	}
	public void setVer(String ver) {
		this.ver = ver;
	}
	
	
}
