package com.shinian.vo;

import java.io.Serializable;
import java.util.List;


public class ActionVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;

	private int seq;		
	private int doer;
	private long doerHP;		//受到反弹、吸血之后的hp
	private int act;			// Define in Constant.CON_NPC_BATTLE_ACT_*
	private int doee;
	private long doeeHP;		//受到伤害之后的hp
	private long impact;		//攻击伤害
	//private List<Long> reflection;		//反弹伤害（负值）&吸血值(正值)
	private long fantan;	//反弹伤害
	private long xixue;			//吸血值
	
	public long getDoerHP() {
		return doerHP;
	}
	public void setDoerHP(long doerHP) {
		this.doerHP = doerHP;
	}
	
	public long getDoeeHP() {
		return doeeHP;
	}
	public void setDoeeHP(long doeeHP) {
		this.doeeHP = doeeHP;
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
		return fantan;
	}
	public void setReflection(long reflection) {
		this.fantan = reflection;
	}	
	
}
