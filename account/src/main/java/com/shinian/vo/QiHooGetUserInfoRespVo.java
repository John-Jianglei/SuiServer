package com.shinian.vo;

import java.io.Serializable;

public class QiHooGetUserInfoRespVo extends BaseObject implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid; //>0: success, =0:fail
	private String token;
	private String qihooUserId;
	
	private String area;
	private String avatar;
	private String id;
	private String name;
	private String nick;
	private String sex;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getQihooUserId() {
		return qihooUserId;
	}
	public void setQihooUserId(String qihooUserId) {
		this.qihooUserId = qihooUserId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
	
	
	
}
