package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class playerExpRedisVo extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int level;
	private int exp;		//注意，改经验不是累积经验，是升级到下一级所需的经验
	private	int attackAdd;
	private int healthAdd;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getAttackAdd() {
		return attackAdd;
	}
	public void setAttackAdd(int attackAdd) {
		this.attackAdd = attackAdd;
	}
	public int getHealthAdd() {
		return healthAdd;
	}
	public void setHealthAdd(int healthAdd) {
		this.healthAdd = healthAdd;
	}
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.level = Integer.parseInt(list.get(idx++));  
		this.exp = Integer.parseInt(list.get(idx++)); 
		this.attackAdd = Integer.parseInt(list.get(idx++));
		this.healthAdd = Integer.parseInt(list.get(idx++));

	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("level", 		               	this.level + "");
		map.put("exp",          this.exp + "");
		map.put("attackAdd", 		        this.attackAdd + "");
		map.put("healthAdd", 		    this.healthAdd + "");	

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"level", "exp", "attackAdd", "healthAdd"};
	}

}
