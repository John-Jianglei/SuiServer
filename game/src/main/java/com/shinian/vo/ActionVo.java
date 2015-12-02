package com.shinian.vo;

import java.io.Serializable;


public class ActionVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private int seq;		
	private int doer;
	private int act;		// Define in Constant.CON_NPC_BATTLE_ACT_*
	private int doee;
	private long impact;		//攻击伤害
	private long reflection;	//反弹伤害
	private long xixue;			//吸血值
	private long doer_hp;		//受到反弹、吸血之后的hp
	private long doee_hp;		//受到伤害之后的hp
	
	public long getDoer_hp() {
		return doer_hp;
	}
	public void setDoer_hp(long doer_hp) {
		this.doer_hp = doer_hp;
	}
	
	public long getDoee_hp() {
		return doee_hp;
	}
	public void setDoee_hp(long doee_hp) {
		this.doee_hp = doee_hp;
	}
	
	public long getXixue() {
		return xixue;
	}
	public void setXixue(long xixue) {
		this.xixue = xixue;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	public int getDoer() {
		return doer;
	}
	public void setDoer(int doer) {
		this.doer = doer;
	}
	
	public int getAct() {
		return act;
	}
	public void setAct(int act) {
		this.act = act;
	}
	
	public int getDoee() {
		return doee;
	}
	public void setDoee(int doee) {
		this.doee = doee;
	}
	
	public long getImpact() {
		return impact;
	}
	public void setImpact(long impact) {
		this.impact = impact;
	}
	
	public long getReflection() {
		return reflection;
	}
	public void setReflection(long reflection) {
		this.reflection = reflection;
	}	
	
}
