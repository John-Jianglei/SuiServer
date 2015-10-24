package com.shinian.vo;

import java.io.Serializable;

public class Channel extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	int channelId;

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
}
