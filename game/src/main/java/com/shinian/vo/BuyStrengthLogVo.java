package com.shinian.vo;

import java.io.Serializable;

public class BuyStrengthLogVo extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	protected 	int 	id;			//ID;
	private		String	uid;
	private		String	date;
	private		int		gold;		//本次购买需要元宝数
	private		int		Count;		//已购买体力次数
	
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
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getCount() {
		return Count;
	}
	public void setCount(int count) {
		Count = count;
	}

}
