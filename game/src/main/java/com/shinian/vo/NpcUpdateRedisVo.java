package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NpcUpdateRedisVo  implements Serializable{
	private static final long serialVersionUID = 1L;

	private  int level;		// ID from common_npc_experience;
	private  int experience;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.level = Integer.parseInt(list.get(idx++));   
		this.experience = Integer.parseInt(list.get(idx++));  		

	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("level", 		               	this.level + "");
		map.put("experience",                  	this.experience + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"level", "experience"};
	}
}
