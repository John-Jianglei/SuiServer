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
	List<ExpCardVo> expCardList;
	private String token;
	private int level;
	private long experience;
	
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
	public long getExperience() {
		return experience;
	}
	public void setExperience(long experience) {
		this.experience = experience;
	}
	

	
}
