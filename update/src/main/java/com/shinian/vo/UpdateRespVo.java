package com.shinian.vo;

import java.io.Serializable;

public class UpdateRespVo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int way;
	private String wayDesc;
	private int serverStatus;
	private String noticeDesc;
	private int has; //has version update or not 0:no;1:has
	
	public int getWay() {
		return way;
	}
	public void setWay(int way) {
		this.way = way;
	}
	public String getWayDesc() {
		return wayDesc;
	}
	public void setWayDesc(String wayDesc) {
		this.wayDesc = wayDesc;
	}
	public int getServerStatus() {
		return serverStatus;
	}
	public void setServerStatus(int serverStatus) {
		this.serverStatus = serverStatus;
	}
	public String getNoticeDesc() {
		return noticeDesc;
	}
	public void setNoticeDesc(String noticeDesc) {
		this.noticeDesc = noticeDesc;
	}
	public int getHas() {
		return has;
	}
	public void setHas(int has) {
		this.has = has;
	}	
}
