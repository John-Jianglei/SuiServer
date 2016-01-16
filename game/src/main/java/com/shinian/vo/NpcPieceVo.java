package com.shinian.vo;

import java.io.Serializable;

import com.shinian.util.DateUtil;

public class NpcPieceVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int comId;
	private String uid;
	private int amount;
	private String updateTime;
	
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public void init(){		
		id 		= 0;
		comId	= 0;
		uid 	= "";
		amount	= 0;		
		updateTime = DateUtil.getCurrentTime();
	}

}
