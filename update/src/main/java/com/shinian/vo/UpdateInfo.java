package com.shinian.vo;

import java.io.Serializable;

public class UpdateInfo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	int way;
	int serverStatus; //0:close;1:normal
	String desc;
	
	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(int serverStatus) {
		this.serverStatus = serverStatus;
	}
}
