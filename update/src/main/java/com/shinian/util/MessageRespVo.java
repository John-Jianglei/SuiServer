package com.shinian.util;

import java.io.Serializable;

import com.shinian.vo.BaseObject;

public class MessageRespVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	private int ts;
	private int code;
	private String msg;
	
	private Object data;
	

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}


	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
