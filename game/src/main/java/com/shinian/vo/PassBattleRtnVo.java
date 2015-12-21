package com.shinian.vo;

import java.io.Serializable;

public class PassBattleRtnVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private 	String 			token;
	private		PassReturnVo	prv;
	private		BattleReturnVo	brv;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public PassReturnVo getPrv() {
		return prv;
	}
	public void setPrv(PassReturnVo prv) {
		this.prv = prv;
	}
	public BattleReturnVo getBrv() {
		return brv;
	}
	public void setBrv(BattleReturnVo brv) {
		this.brv = brv;
	}
	
	
}
