package com.shinian.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class NpcJinjieRespVo implements Serializable {

	protected int id;		// ID from game_npc_info;
	protected int comsumedid;	//the rpc is to be eaten
	protected String uid;
	private String token;
	
	private int pinjie;
	private int attack;
	private int health;
	
	private BigInteger silver;
	private int jinjiedan;
	private int fivecolorstone;
	private int tigertally;
	private int eviltally;
	private int ploughtally;
	private int sttally;
	private int suitangmedal;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComsumedid() {
		return comsumedid;
	}
	public void setComsumedid(int comsumedid) {
		this.comsumedid = comsumedid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getPinjie() {
		return pinjie;
	}
	public void setPinjie(int pinjie) {
		this.pinjie = pinjie;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public BigInteger getSilver() {
		return silver;
	}
	public void setSilver(BigInteger silver) {
		this.silver = silver;
	}
	public int getJinjiedan() {
		return jinjiedan;
	}
	public void setJinjiedan(int jinjiedan) {
		this.jinjiedan = jinjiedan;
	}
	public int getFivecolorstone() {
		return fivecolorstone;
	}
	public void setFivecolorstone(int fivecolorstone) {
		this.fivecolorstone = fivecolorstone;
	}
	public int getTigertally() {
		return tigertally;
	}
	public void setTigertally(int tigertally) {
		this.tigertally = tigertally;
	}
	public int getEviltally() {
		return eviltally;
	}
	public void setEviltally(int eviltally) {
		this.eviltally = eviltally;
	}
	public int getPloughtally() {
		return ploughtally;
	}
	public void setPloughtally(int ploughtally) {
		this.ploughtally = ploughtally;
	}
	public int getSttally() {
		return sttally;
	}
	public void setSttally(int sttally) {
		this.sttally = sttally;
	}
	public int getSuitangmedal() {
		return suitangmedal;
	}
	public void setSuitangmedal(int suitangmedal) {
		this.suitangmedal = suitangmedal;
	}
	
}
