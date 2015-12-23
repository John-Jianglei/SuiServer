package com.shinian.vo;

import java.io.Serializable;
import java.util.Date;

public class PassLogVo extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;

	protected 	int 	id;			//ID;
	private		String	uid;
	private		String	date;
	private		int		battleId;
	private		int		Count;	//可扫荡次数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBattleId() {
		return battleId;
	}
	public void setBattleId(int battleId) {
		this.battleId = battleId;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}

	
}
