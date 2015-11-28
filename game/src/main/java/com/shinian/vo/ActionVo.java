package com.shinian.vo;

import java.io.Serializable;


public class ActionVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private int seq;		
	private int doer;
	private int act;		// Define in Constant.CON_NPC_BATTLE_ACT_*
	private int doee;
	private int impact;
	private int reflection;
	
	
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
	
	public int getImpact() {
		return impact;
	}
	public void setImpact(int impact) {
		this.impact = impact;
	}
	
	public int getReflection() {
		return reflection;
	}
	public void setReflection(int reflection) {
		this.reflection = reflection;
	}	
	
}
