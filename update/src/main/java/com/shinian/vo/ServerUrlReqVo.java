package com.shinian.vo;

import java.io.Serializable;

public class ServerUrlReqVo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int channelId;
	private int serverNo;
	
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public int getServerNo() {
		return serverNo;
	}
	public void setServerNo(int serverNo) {
		this.serverNo = serverNo;
	}	
}
