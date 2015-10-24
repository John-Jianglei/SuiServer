package com.shinian.vo;

import java.io.Serializable;

public class ServerUrlRespVo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int result;
	private String url;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}		
}
