package com.shinian.vo;

import java.io.Serializable;

public class PassVo  extends BaseObject implements Serializable{

	protected	String 	uid;
	private		int		battle;			//battleId
	private		int		a;				//1表示战斗，0表示扫荡
	

	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}	
	public int getBattle() {
		return battle;
	}
	public void setBattle(int battle) {
		this.battle = battle;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	
}
