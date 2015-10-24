package com.shinian.vo;

import java.io.Serializable;

public class NpcInfoVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected  int id;		// ID from game_npc_info;
	protected  int comId;		// ID from common_npc_info;
	protected  String uid;	//	ID from game_player_info; 
	protected  String name;
	protected  int gender;
	protected  int star;
	protected  int level;
	protected  int category;
	protected  int camp;
	protected  int health;
	protected  int attack;
	protected  int defense;
	protected  String desc;
	protected  String updateTime;
	protected  int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}		
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}	
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

	public int getCamp() {
		return camp;
	}
	public void setCamp(int camp) {
		this.camp = camp;
	}
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}	
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}	
	
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
