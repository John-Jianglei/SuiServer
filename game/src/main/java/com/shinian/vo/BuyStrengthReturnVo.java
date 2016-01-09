package com.shinian.vo;

import java.io.Serializable;

public class BuyStrengthReturnVo extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uid;
	private int gold;			//玩家当前元宝
	private int sh;		//玩家当前体力
	private int n;			//玩家已购买体力次数
	private String token;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getSh() {
		return sh;
	}
	public void setSh(int sh) {
		this.sh = sh;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
