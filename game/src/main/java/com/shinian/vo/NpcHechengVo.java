package com.shinian.vo;

import java.io.Serializable;

public class NpcHechengVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private int cId;		//武将将魂id,同时也是武将Id
	private int cNum;		//消耗武将将魂数量
	private int gId;		//武将万能将魂id
	private int gNum;		//消耗武将万能将魂数量
	private int star;		//武将星级
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getCId() {
		return cId;
	}
	public void setCId(int cId) {
		this.cId = cId;
	}
	
	public int getCNum() {
		return cNum;
	}
	public void setCNum(int cNum) {
		this.cNum = cNum;
	}
	
	public int getGId() {
		return gId;
	}
	public void setGId(int gId) {
		this.gId = gId;
	}
	
	public int getGNum() {
		return gNum;
	}
	public void setGNum(int gNum) {
		this.gNum = gNum;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}

}
