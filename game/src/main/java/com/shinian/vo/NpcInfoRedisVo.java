package com.shinian.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class NpcInfoRedisVo extends NpcInfoVo implements Serializable{
	private static final long serialVersionUID = 1L;

	public void fromList(List<String> list) {
		int idx = 0;
		super.comId = Integer.parseInt(list.get(idx++));
		super.name = list.get(idx++);
		super.gender = Integer.parseInt(list.get(idx++));
		super.star = Integer.parseInt(list.get(idx++));
		super.category = Integer.parseInt(list.get(idx++));
		super.camp = Integer.parseInt(list.get(idx++));
		super.health = Integer.parseInt(list.get(idx++));
		super.attack = Integer.parseInt(list.get(idx++));
		super.defense = Integer.parseInt(list.get(idx++));
		super.desc = list.get(idx++);
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("comId", super.comId + "");
		map.put("name", super.name + "");
		map.put("gender", super.gender + "");
		map.put("star", super.star + "");
		map.put("category", super.category + "");
		map.put("camp", super.camp + "");
		map.put("health", super.health + "");
		map.put("attack", super.attack + "");
		map.put("defense", super.defense + "");
		map.put("desc", super.desc);

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"comId", "name", "gender", "star", "category", "camp", "health", "attack", "defense", "desc"};
	}
	
	public void getAll(NpcInfoVo v){
		this.id = v.getId();
		this.comId = v.getComId();
		this.uid = v.getUid();
		this.name = v.getName();
		this.gender = v.getGender();
		this.star = v.getStar();
		this.level = v.getLevel();
		this.category = v.getCategory();
		this.camp = v.getCamp();
		this.health = v.getHealth();
		this.attack = v.getAttack();
		this.defense = v.getDefense();
		this.desc = v.getDesc();
		this.updateTime = v.getUpdateTime();
		this.status = v.getStatus();
	}
}
