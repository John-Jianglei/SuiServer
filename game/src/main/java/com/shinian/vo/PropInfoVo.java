package com.shinian.vo;

import java.io.Serializable;

public class PropInfoVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private  int id;		// ID from game_prop_info;
	private  int comId;		// ID from common_prop_info;
	private  String uid;	//	ID from game_player_info;
	private	 int npcId;		//	ID from game_npc_info;
	
	private  int position;
	private  int amount;
	
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getNpcId() {
		return npcId;
	}
	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}	

	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
		
}
