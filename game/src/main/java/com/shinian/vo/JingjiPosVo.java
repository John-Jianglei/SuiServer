package com.shinian.vo;

import java.io.Serializable;

public class JingjiPosVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;
	
	private		String	uid;
	private		int		p;				//排名
	private		int 	p1;				//排名最近5个中的第一个
	private		int 	p2;
	private		int 	p3;
	private		int 	p4;
	private		int 	p5;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public int getP1() {
		return p1;
	}
	public void setP1(int p1) {
		this.p1 = p1;
	}
	public int getP2() {
		return p2;
	}
	public void setP2(int p2) {
		this.p2 = p2;
	}
	public int getP3() {
		return p3;
	}
	public void setP3(int p3) {
		this.p3 = p3;
	}
	public int getP4() {
		return p4;
	}
	public void setP4(int p4) {
		this.p4 = p4;
	}
	public int getP5() {
		return p5;
	}
	public void setP5(int p5) {
		this.p5 = p5;
	}	

}
