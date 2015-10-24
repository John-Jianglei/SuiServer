package com.shinian.vo;

import java.io.Serializable;

public class ServerListVo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int serverNo;
	private String name;
	private String desc;
	private String url;
	private int flag;

	public int getServerNo() {
		return serverNo;
	}
	public void setServerNo(int serverNo) {
		this.serverNo = serverNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
