package com.shinian.vo;

import java.io.Serializable;

public class RegisterReqVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String password;
	private int channel;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}

	
}
