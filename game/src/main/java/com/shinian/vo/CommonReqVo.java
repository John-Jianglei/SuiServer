package com.shinian.vo;

import java.io.Serializable;

/**
 * 
 * @author yinhuawei
 * 2014-02-26 15:53:00
 */
public class CommonReqVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int ts;
	private String token;
	private Object data;
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	

}
