package com.shinian.util;

import java.io.Serializable;

import com.shinian.vo.BaseObject;

public class MessageRepVo extends BaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int ts;
	private int msgcode;
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
	public int getMsgcode() {
		return msgcode;
	}
	public void setMsgcode(int msgcode) {
		this.msgcode = msgcode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	

}
