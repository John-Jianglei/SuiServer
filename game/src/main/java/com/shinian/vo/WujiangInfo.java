package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WujiangInfo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String desc;
	private int attack;
	private int defence;
	private int hp;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("id", this.id + "");
		map.put("name", this.name + "");
		map.put("desc", this.desc + "");
		map.put("attack", this.attack + "");
		map.put("defend", this.defence + "");
		map.put("hp", this.hp + "");
		
		return map;
	}
	
	public void fromList(List<String> list) {
		int idx = 0;
		this.id = Integer.parseInt(list.get(idx++));
		this.name = list.get(idx++);
		this.desc = list.get(idx++);
		this.attack = Integer.parseInt(list.get(idx++));
		this.defence = Integer.parseInt(list.get(idx++));
		this.hp = Integer.parseInt(list.get(idx++));
	}
	
	public String[] getFieldNames() {
		return new String[] {"id", "name","desc","attack","defence","hp"};
	}
}
