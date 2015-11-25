package com.shinian.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.nextpinjie = Integer.parseInt(list.get(idx++));   
		this.silver = new BigInteger(list.get(idx++));	
		this.jinjiedan = Integer.parseInt(list.get(idx++));  		
		this.fivecolorstone = Integer.parseInt(list.get(idx++));  		
		this.tigertally = Integer.parseInt(list.get(idx++));   
		this.eviltally = Integer.parseInt(list.get(idx++));  
		this.ploughtally = Integer.parseInt(list.get(idx++));  
		this.sttally = Integer.parseInt(list.get(idx++));  
		this.suitangmedal = Integer.parseInt(list.get(idx++));  
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("nextpinjie", 		               	this.nextpinjie + "");
		map.put("silver",                        	this.silver + "");
		map.put("jinjiedan", 	               	   	this.jinjiedan + "");
		map.put("fivecolorstone",		 	       	this.fivecolorstone + "");
		map.put("tigertally", 		               	this.tigertally + "");
		map.put("eviltally",                        this.eviltally + "");
		map.put("ploughtally",                  	this.ploughtally + "");
		map.put("sttally",                      	this.sttally + "");
		map.put("suitangmedal",                     this.suitangmedal + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"nextpinjie", "silver", "jinjiedan", "fivecolorstone", "tigertally", 
				"eviltally", "ploughtally", "sttally", "suitangmedal"};
	}
	
}
