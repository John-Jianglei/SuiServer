package com.shinian.vo;

import java.io.Serializable;

public class PassVo extends BaseObject implements Serializable{

	protected	String 	uid;
	private		int		battle;			//battleId
	private		int		flag;			//1表示战斗，0表示扫荡
	private		int		battleStars;
	private		int		passStars;	

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
	public int getBattleStars() {
		return battleStars;
	}
	public void setBattleStars(int battleStars) {
		this.battleStars = battleStars;
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public int getPassStars() {
		return passStars;
	}
	public void setPassStars(int passStars) {
		this.passStars = passStars;
	}
	
}
