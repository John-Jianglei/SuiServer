package com.shinian.vo;

import java.io.Serializable;

public class PlayerInfoVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String name;
	private int gender;
	private int level;
	private int vip_level;
	private String createTime;
	private int current_exp;
	private int silver;
	private int fame;
	private int gold;
	private int current_strength;
	private	long combatPower;
		
	public int getVip_level() {
		return vip_level;
	}
	public void setVip_level(int vip_level) {
		this.vip_level = vip_level;
	}
	public long getCombatPower() {
		return combatPower;
	}
	public void setCombatPower(long combatPower) {
		this.combatPower = combatPower;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}	
	public int getVip_Level() {
		return vip_level;
	}
	public void setVip_Level(int level) {
		this.vip_level = level;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getCurrent_exp() {
		return current_exp;
	}
	public void setCurrent_exp(int current_exp) {
		this.current_exp = current_exp;
	}
	
	public int getSilver() {
		return silver;
	}
	public void setSilver(int silver) {
		this.silver = silver;
	}
	
	public int getFame() {
		return fame;
	}
	public void setFame(int fame) {
		this.fame = fame;
	}
	
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public int getCurrent_strength() {
		return current_strength;
	}
	public void setCurrent_strength(int current_strength) {
		this.current_strength = current_strength;
	}	
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
}
