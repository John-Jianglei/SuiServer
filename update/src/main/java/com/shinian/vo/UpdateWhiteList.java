package com.shinian.vo;

import java.io.Serializable;

public class UpdateWhiteList extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int channelId;
	private String uids;
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getUids() {
		return uids;
	}
	public void setUids(String uids) {
		this.uids = uids;
	}
}
