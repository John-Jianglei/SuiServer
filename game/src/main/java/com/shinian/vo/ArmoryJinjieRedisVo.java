package com.shinian.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class ArmoryJinjieRedisVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int star;
	private int category;
	private int nextPinjie;
	private int sliver;
	private int jinHuaStone;
	private int amber;
	private int xuantie;
	private int copper;
	private int madeng;
	private int pige;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getNextPinjie() {
		return nextPinjie;
	}

	public void setNextPinjie(int nextPinjie) {
		this.nextPinjie = nextPinjie;
	}

	public int getSliver() {
		return sliver;
	}

	public void setSliver(int sliver) {
		this.sliver = sliver;
	}

	public int getJinHuaStone() {
		return jinHuaStone;
	}

	public void setJinHuaStone(int jinHuaStone) {
		this.jinHuaStone = jinHuaStone;
	}

	public int getAmber() {
		return amber;
	}

	public void setAmber(int amber) {
		this.amber = amber;
	}

	public int getXuantie() {
		return xuantie;
	}

	public void setXuantie(int xuantie) {
		this.xuantie = xuantie;
	}

	public int getCopper() {
		return copper;
	}

	public void setCopper(int copper) {
		this.copper = copper;
	}

	public int getMadeng() {
		return madeng;
	}

	public void setMadeng(int madeng) {
		this.madeng = madeng;
	}

	public int getPige() {
		return pige;
	}

	public void setPige(int pige) {
		this.pige = pige;
	}

	public void fromList(List<String> list) {
		int idx = 0;

		this.id = Integer.parseInt(list.get(idx++));
		this.star = Integer.parseInt(list.get(idx++));
		this.category = Integer.parseInt(list.get(idx++));
		this.nextPinjie = Integer.parseInt(list.get(idx++));
		this.sliver = Integer.parseInt(list.get(idx++));
		this.jinHuaStone = Integer.parseInt(list.get(idx++));
		this.amber = Integer.parseInt(list.get(idx++));
		this.xuantie = Integer.parseInt(list.get(idx++));
		this.copper = Integer.parseInt(list.get(idx++));
		this.madeng = Integer.parseInt(list.get(idx++));
		this.pige = Integer.parseInt(list.get(idx++));
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("id",			this.id + "");			
		map.put("star",			this.star + "");		
		map.put("category",		this.category + "");		
		map.put("nextPinjie",	this.nextPinjie + "");			
		map.put("sliver",		this.sliver + "");				
		map.put("jinHuaStone",	this.jinHuaStone + "");					
		map.put("amber",		this.amber + "");					
		map.put("xuantie",		this.xuantie + "");					
		map.put("copper",		this.copper + "");							
		map.put("madeng",		this.madeng + "");								
		map.put("pige",			this.pige + "");		

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"id", "star", "category", "nextPinjie", "sliver", "jinHuaStone", "amber", "xuantie", "copper", "madeng", "pige"};
	}

}
