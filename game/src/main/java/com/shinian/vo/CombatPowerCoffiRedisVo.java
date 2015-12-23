package com.shinian.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombatPowerCoffiRedisVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private int id;
	private int attackC;
	private int healthC;
	private int basePower;		//基础战力，用于计算属性增加的战力
	private int pojiaC;
	private int hujiaC;
	private int fachuanC;
	private int fakangC;
	private int baojiC;
	private int renxingC;
	private int mingzhongC;
	private int shanbiC;
	private int xixueC;
	private int fantanC;
	private int jiyunC;
	private int kangyunC;
	private int gedangC;
	private int reduceC;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAttackC() {
		return attackC;
	}
	public void setAttackC(int attackC) {
		this.attackC = attackC;
	}
	public int getHealthC() {
		return healthC;
	}
	public void setHealthC(int healthC) {
		this.healthC = healthC;
	}
	public int getBasePower() {
		return basePower;
	}
	public void setBasePower(int basePower) {
		this.basePower = basePower;
	}
	public int getPojiaC() {
		return pojiaC;
	}
	public void setPojiaC(int pojiaC) {
		this.pojiaC = pojiaC;
	}
	public int getHujiaC() {
		return hujiaC;
	}
	public void setHujiaC(int hujiaC) {
		this.hujiaC = hujiaC;
	}
	public int getFachuanC() {
		return fachuanC;
	}
	public void setFachuanC(int fachuanC) {
		this.fachuanC = fachuanC;
	}
	public int getFakangC() {
		return fakangC;
	}
	public void setFakangC(int fakangC) {
		this.fakangC = fakangC;
	}
	public int getBaojiC() {
		return baojiC;
	}
	public void setBaojiC(int baojiC) {
		this.baojiC = baojiC;
	}
	public int getRenxingC() {
		return renxingC;
	}
	public void setRenxingC(int renxingC) {
		this.renxingC = renxingC;
	}
	public int getMingzhongC() {
		return mingzhongC;
	}
	public void setMingzhongC(int mingzhongC) {
		this.mingzhongC = mingzhongC;
	}
	public int getShanbiC() {
		return shanbiC;
	}
	public void setShanbiC(int shanbiC) {
		this.shanbiC = shanbiC;
	}
	public int getXixueC() {
		return xixueC;
	}
	public void setXixueC(int xixueC) {
		this.xixueC = xixueC;
	}
	public int getFantanC() {
		return fantanC;
	}
	public void setFantanC(int fantanC) {
		this.fantanC = fantanC;
	}
	public int getJiyunC() {
		return jiyunC;
	}
	public void setJiyunC(int jiyunC) {
		this.jiyunC = jiyunC;
	}
	public int getKangyunC() {
		return kangyunC;
	}
	public void setKangyunC(int kangyunC) {
		this.kangyunC = kangyunC;
	}
	public int getGedangC() {
		return gedangC;
	}
	public void setGedangC(int gedangC) {
		this.gedangC = gedangC;
	}
	public int getReduceC() {
		return reduceC;
	}
	public void setReduceC(int reduceC) {
		this.reduceC = reduceC;
	}	
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.id = Integer.parseInt(list.get(idx++));	
		this.attackC = Integer.parseInt(list.get(idx++));  		
		this.healthC = Integer.parseInt(list.get(idx++));  		
		this.basePower = Integer.parseInt(list.get(idx++));   
		this.pojiaC = Integer.parseInt(list.get(idx++));  
		this.hujiaC = Integer.parseInt(list.get(idx++));  
		this.fachuanC = Integer.parseInt(list.get(idx++));  
		this.fakangC = Integer.parseInt(list.get(idx++));  
		this.baojiC = Integer.parseInt(list.get(idx++));	
		this.renxingC = Integer.parseInt(list.get(idx++));  		
		this.mingzhongC = Integer.parseInt(list.get(idx++));  		
		this.shanbiC = Integer.parseInt(list.get(idx++));   
		this.xixueC = Integer.parseInt(list.get(idx++));  
		this.fantanC = Integer.parseInt(list.get(idx++));  
		this.jiyunC = Integer.parseInt(list.get(idx++));  
		this.kangyunC = Integer.parseInt(list.get(idx++));  
		this.gedangC = Integer.parseInt(list.get(idx++));  
		this.reduceC = Integer.parseInt(list.get(idx++)); 
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("id", 		               	this.id + "");
		map.put("attackC",                  this.attackC + "");
		map.put("healthC", 	               	this.healthC + "");
		map.put("basePower",		 	    this.basePower + "");
		map.put("pojiaC", 		            this.pojiaC + "");
		map.put("hujiaC",                   this.hujiaC + "");
		map.put("fachuanC",                 this.fachuanC + "");
		map.put("fakangC",                  this.fakangC + "");
		map.put("baojiC",                   this.baojiC + "");
		map.put("renxingC", 		        this.renxingC + "");
		map.put("mingzhongC",               this.mingzhongC + "");
		map.put("shanbiC", 	               	this.shanbiC + "");
		map.put("xixueC",		 	       	this.xixueC + "");
		map.put("fantanC", 		            this.fantanC + "");
		map.put("jiyunC",                   this.jiyunC + "");
		map.put("kangyunC",                 this.kangyunC + "");
		map.put("gedangC",                  this.gedangC + "");
		map.put("reduceC",                  this.reduceC + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"id", "attackC", "healthC", "basePower", "pojiaC", "hujiaC", "fachuanC", 
				"fakangC", "baojiC", "renxingC", "mingzhongC", "shanbiC", "xixueC", "fantanC", "jiyunC", 
				"kangyunC", "gedangC", "reduceC"};		

	}
	
}