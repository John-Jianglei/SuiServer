package com.shinian.vo;

import java.io.Serializable;
import java.math.BigInteger;

public class JinjieMaterialRedisVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private  int nextpinjie;
	private  BigInteger silver;
	private  int jinjiedan;	//	物品功能			
	private  int fivecolorstone;
	private  int tigertally;	
	private  int eviltally;	
	private  int ploughtally;	
	private  int sttally;	
	private  int suitangmedal;
	
	public int getNextpinjie() {
		return nextpinjie;
	}
	public void setNextpinjie(int nextpinjie) {
		this.nextpinjie = nextpinjie;
	}
	public BigInteger getSilver() {
		return silver;
	}
	public void setSilver(BigInteger silver) {
		this.silver = silver;
	}
	public int getJinjiedan() {
		return jinjiedan;
	}
	public void setJinjiedan(int jinjiedan) {
		this.jinjiedan = jinjiedan;
	}
	public int getFivecolorstone() {
		return fivecolorstone;
	}
	public void setFivecolorstone(int fivecolorstone) {
		this.fivecolorstone = fivecolorstone;
	}
	public int getTigertally() {
		return tigertally;
	}
	public void setTigertally(int tigertally) {
		this.tigertally = tigertally;
	}
	public int getEviltally() {
		return eviltally;
	}
	public void setEviltally(int eviltally) {
		this.eviltally = eviltally;
	}
	public int getPloughtally() {
		return ploughtally;
	}
	public void setPloughtally(int ploughtally) {
		this.ploughtally = ploughtally;
	}
	public int getSttally() {
		return sttally;
	}
	public void setSttally(int sttally) {
		this.sttally = sttally;
	}
	public int getSuitangmedal() {
		return suitangmedal;
	}
	public void setSuitangmedal(int suitangmedal) {
		this.suitangmedal = suitangmedal;
	}	
	
}
