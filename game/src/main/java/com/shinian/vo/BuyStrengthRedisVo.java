package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyStrengthRedisVo extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int seq;		//第Seq次购买体力
	private int gold;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.seq = Integer.parseInt(list.get(idx++));  
		this.gold = Integer.parseInt(list.get(idx++));
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("seq", 		        this.seq + "");
		map.put("gold",          	this.gold + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"seq", "gold"};
	}
	
}
