package com.shinian.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class YuanfenInfoRedisVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private  int comId;		// ID from common_prop_info;
	private  String name;
	private  int npcId;		//	ID from common_npc_info;			
	private  int category;	
	private  int objId;					
	private  int addAttack;					
	private  int addHealth;					
	private  String desc;
	private  String updateTime;
	private  int status;
	
	
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNpcId() {
		return npcId;
	}
	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}

	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	
	public int getAddAttack() {
		return addAttack;
	}
	public void setAddAttack(int addAttack) {
		this.addAttack = addAttack;
	}
	
	public int getAddHealth() {
		return addHealth;
	}
	public void setAddHealth(int addHealth) {
		this.addHealth = addHealth;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.comId = Integer.parseInt(list.get(idx++));   
		this.name = list.get(idx++);   
		this.npcId = Integer.parseInt(list.get(idx++));   
		this.category = Integer.parseInt(list.get(idx++));   
		this.objId = Integer.parseInt(list.get(idx++));   
		this.addAttack = Integer.parseInt(list.get(idx++));   
		this.addHealth = Integer.parseInt(list.get(idx++));    
		this.desc = list.get(idx++);   
		this.updateTime = list.get(idx++);    
		this.status = Integer.parseInt(list.get(idx++));   
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("comId",                  this.comId + "");
		map.put("name",                   this.name + "");
		map.put("npcId",                  this.npcId + "");
		map.put("category",               this.category + "");
		map.put("objId",                  this.objId + "");
		map.put("addAttack",              this.addAttack + "");
		map.put("addHealth",              this.addHealth + ""); 
		map.put("desc",                   this.desc + "");
		map.put("updateTime",             this.updateTime + ""); 
		map.put("status",                 this.status + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"comId", "name", "npcId", "category", "objId", "addAttack", "addHealth", "desc", "updateTime", "status"};
	}

}
