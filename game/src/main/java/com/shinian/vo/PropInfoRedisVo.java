package com.shinian.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class PropInfoRedisVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	private  int comId;		// ID from common_prop_info;
	private  String name;
	private  int nature;	//	物品功能			
	private  int val;
	private  int star;					
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
	
	public int getNature() {
		return nature;
	}
	public void setNature(int nature) {
		this.nature = nature;
	}

	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}

	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
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
		this.nature = Integer.parseInt(list.get(idx++));  		
		this.val = Integer.parseInt(list.get(idx++));  		
		this.star = Integer.parseInt(list.get(idx++));  		
		this.desc = list.get(idx++);  
		this.updateTime = list.get(idx++);  
		this.status = Integer.parseInt(list.get(idx++));  
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("comId", 		               this.comId + "");
		map.put("name",                        this.name + "");
		map.put("nature", 	               	   this.nature + "");
		map.put("val",		 	               this.val + "");
		map.put("star", 		               this.star + "");
		map.put("desc",                        this.desc + "");
		map.put("updateTime",                  this.updateTime + "");
		map.put("status",                      this.status + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"comId", "name", "nature", "val", "star", "desc", "updateTime", "status"};
	}

}
