package com.shinian.vo;

import java.io.Serializable;

public class JingjiVo  extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String 	uid;		//进攻方uid
	private int		defPos;		//防御方排名
	private String 	defUid;		//防御方uid
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getDefPos() {
		return defPos;
	}
	public void setDefPos(int defPos) {
		this.defPos = defPos;
	}
	
	public String getDefUid() {
		return defUid;
	}
	public void setDefUid(String defUid) {
		this.defUid = defUid;
	}

}
