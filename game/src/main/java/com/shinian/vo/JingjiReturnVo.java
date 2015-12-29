package com.shinian.vo;

import java.io.Serializable;

public class JingjiReturnVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private 	int 			fame;
	private		JingjiPosVo		jpv;
	private		BattleReturnVo	brv;

	public int getFame() {
		return fame;
	}
	public void setFame(int fame) {
		this.fame = fame;
	}
	
	public JingjiPosVo getJpv() {
		return jpv;
	}
	public void setJpv(JingjiPosVo jpv) {
		this.jpv = jpv;
	}
	
	public BattleReturnVo getBrv() {
		return brv;
	}
	public void setBrv(BattleReturnVo brv) {
		this.brv = brv;
	}	
	
}
