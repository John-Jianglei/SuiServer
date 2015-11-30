package com.shinian.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Get all NPC info of player
public class NpcUpdateVo extends BaseObject implements Serializable
{
	private static final long serialVersionUID = 1L;

	protected int id;		// ID from game_npc_info;
	protected String uid;
	private int comId;
	List<ExpCardVo> expCardList;
	private String token;
	private int level;
	private int pinjie;
	private long experience;
	private int attack;
	private int health;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	
	public List<ExpCardVo> getExpCardList() {
		return expCardList;
	}
	public void setExpCardList(List<ExpCardVo> expCardList) {
		this.expCardList = expCardList;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getPinjie() {
		return pinjie;
	}
	public void setPinjie(int pinjie) {
		this.pinjie = pinjie;
	}
	
	public long getExperience() {
		return experience;
	}
	public void setExperience(long experience) {
		this.experience = experience;
	}
	

	
}
