package com.shinian.vo;

import java.io.Serializable;

public class UpdateReqVo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int channelId;
	private String version;
	private String uid;
	
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
}
