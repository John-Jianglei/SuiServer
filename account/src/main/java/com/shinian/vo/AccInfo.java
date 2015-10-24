package com.shinian.vo;

import java.io.Serializable;

public class AccInfo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int uid;
	private String username;
	private String password;
	private String bindEmail;
	private int channel;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBindEmail() {
		return bindEmail;
	}
	public void setBindEmail(String bindEmail) {
		this.bindEmail = bindEmail;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
		
}
