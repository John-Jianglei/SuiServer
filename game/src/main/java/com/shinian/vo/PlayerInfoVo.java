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
	private int	jingjiPos;	
	private String jingjiTitle;
	private int	abovePos1;
	private int	abovePos2;
	private int	abovePos3;
	private int	abovePos4;
	private int	abovePos5;
	private int camp;

	public int getCamp() {
		return camp;
	}
	public void setCamp(int camp) {
		this.camp = camp;
	}
	
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
	
	public int getJingjiPos() {
		return jingjiPos;
	}
	public void setJingjiPos(int jingjiPos) {
		this.jingjiPos = jingjiPos;
	}
	public String getJingjiTitle() {
		return jingjiTitle;
	}
	public void setJingjiTitle(String jingjiTitle) {
		this.jingjiTitle = jingjiTitle;
	}
	public int getAbovePos1() {
		return abovePos1;
	}
	public void setAbovePos1(int abovePos1) {
		this.abovePos1 = abovePos1;
	}
	public int getAbovePos2() {
		return abovePos2;
	}
	public void setAbovePos2(int abovePos2) {
		this.abovePos2 = abovePos2;
	}
	public int getAbovePos3() {
		return abovePos3;
	}
	public void setAbovePos3(int abovePos3) {
		this.abovePos3 = abovePos3;
	}
	public int getAbovePos4() {
		return abovePos4;
	}
	public void setAbovePos4(int abovePos4) {
		this.abovePos4 = abovePos4;
	}
	public int getAbovePos5() {
		return abovePos5;
	}
	public void setAbovePos5(int abovePos5) {
		this.abovePos5 = abovePos5;
	}
	
}
