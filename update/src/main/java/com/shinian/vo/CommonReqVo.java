package com.shinian.vo;

import java.io.Serializable;

public class CommonReqVo extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int ts;
	private Object data;
	
	public int getTs() {
		return ts;
	}
	public void setTs(int ts) {
		this.ts = ts;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	

}
