package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JinengRedisVo implements Serializable{
	private static final long serialVersionUID = 1L;

	private  int id;		// ID from common_jineng;
	private  String desc;
	private  int jineng_level;					//	
	private  int need_pinjie;					//	
	private  int init_nuqi;						//	初始怒气值
	private  int need_nuqi;
	private  int consum_nuqi;					//	
	private  int remain_nuqi;
	private  int attack_num;
	private  int mubiao_pos;					//	攻击目标的位置
	private  int damage_type;
	private  int damage_min;
	private  int damage_max;
	private  int add_health_type;				//	给谁加攻击和生命
	private  int add_health;					//
	private  int shuxing_type;
	private  int add_damage;
	private  int add_tianming;
	private  boolean is_relive;
	private  boolean is_wudi;
	private  int add_pojia;
	private  int add_fachuan;
	private  int add_baoji;
	private  int add_mingzhong;
	private  int add_shanbi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getJineng_level() {
		return jineng_level;
	}
	public void setJineng_level(int jineng_level) {
		this.jineng_level = jineng_level;
	}
	public int getNeed_pinjie() {
		return need_pinjie;
	}
	public void setNeed_pinjie(int need_pinjie) {
		this.need_pinjie = need_pinjie;
	}
	public int getInit_nuqi() {
		return init_nuqi;
	}
	public void setInit_nuqi(int init_nuqi) {
		this.init_nuqi = init_nuqi;
	}
	public int getNeed_nuqi() {
		return need_nuqi;
	}
	public void setNeed_nuqi(int need_nuqi) {
		this.need_nuqi = need_nuqi;
	}
	public int getConsum_nuqi() {
		return consum_nuqi;
	}
	public void setConsum_nuqi(int consum_nuqi) {
		this.consum_nuqi = consum_nuqi;
	}
	public int getRemain_nuqi() {
		return remain_nuqi;
	}
	public void setRemain_nuqi(int remain_nuqi) {
		this.remain_nuqi = remain_nuqi;
	}
	public int getAttack_num() {
		return attack_num;
	}
	public void setAttack_num(int attack_num) {
		this.attack_num = attack_num;
	}
	public int getMubiao_pos() {
		return mubiao_pos;
	}
	public void setMubiao_pos(int mubiao_pos) {
		this.mubiao_pos = mubiao_pos;
	}
	public int getDamage_type() {
		return damage_type;
	}
	public void setDamage_type(int damage_type) {
		this.damage_type = damage_type;
	}
	public int getDamage_min() {
		return damage_min;
	}
	public void setDamage_min(int damage_min) {
		this.damage_min = damage_min;
	}
	public int getDamage_max() {
		return damage_max;
	}
	public void setDamage_max(int damage_max) {
		this.damage_max = damage_max;
	}
	public int getAdd_health_type() {
		return add_health_type;
	}
	public void setAdd_health_type(int add_health_type) {
		this.add_health_type = add_health_type;
	}
	public int getAdd_health() {
		return add_health;
	}
	public void setAdd_health(int add_health) {
		this.add_health = add_health;
	}
	public int getShuxing_type() {
		return shuxing_type;
	}
	public void setShuxing_type(int shuxing_type) {
		this.shuxing_type = shuxing_type;
	}
	public int getAdd_damage() {
		return add_damage;
	}
	public void setAdd_damage(int add_damage) {
		this.add_damage = add_damage;
	}
	public int getAdd_tianming() {
		return add_tianming;
	}
	public void setAdd_tianming(int add_tianming) {
		this.add_tianming = add_tianming;
	}
	public boolean isIs_relive() {
		return is_relive;
	}
	public void setIs_relive(boolean is_relive) {
		this.is_relive = is_relive;
	}
	public boolean isIs_wudi() {
		return is_wudi;
	}
	public void setIs_wudi(boolean is_wudi) {
		this.is_wudi = is_wudi;
	}
	public int getAdd_pojia() {
		return add_pojia;
	}
	public void setAdd_pojia(int add_pojia) {
		this.add_pojia = add_pojia;
	}
	public int getAdd_fachuan() {
		return add_fachuan;
	}
	public void setAdd_fachuan(int add_fachuan) {
		this.add_fachuan = add_fachuan;
	}
	public int getAdd_baoji() {
		return add_baoji;
	}
	public void setAdd_baoji(int add_baoji) {
		this.add_baoji = add_baoji;
	}
	public int getAdd_mingzhong() {
		return add_mingzhong;
	}
	public void setAdd_mingzhong(int add_mingzhong) {
		this.add_mingzhong = add_mingzhong;
	}
	public int getAdd_shanbi() {
		return add_shanbi;
	}
	public void setAdd_shanbi(int add_shanbi) {
		this.add_shanbi = add_shanbi;
	}	

	public void fromList(List<String> list) {
		int idx = 0;
		
		this.id = Integer.parseInt(list.get(idx++));   
		this.desc = list.get(idx++); 
		this.jineng_level = Integer.parseInt(list.get(idx++)); 
		this.need_pinjie = Integer.parseInt(list.get(idx++)); 
		this.init_nuqi = Integer.parseInt(list.get(idx++)); 
		this.need_nuqi = Integer.parseInt(list.get(idx++)); 
		this.consum_nuqi = Integer.parseInt(list.get(idx++)); 
		this.remain_nuqi = Integer.parseInt(list.get(idx++)); 
		this.attack_num = Integer.parseInt(list.get(idx++)); 
		this.mubiao_pos = Integer.parseInt(list.get(idx++)); 
		this.damage_type = Integer.parseInt(list.get(idx++)); 
		this.damage_min = Integer.parseInt(list.get(idx++)); 
		this.damage_max = Integer.parseInt(list.get(idx++)); 
		this.add_health_type = Integer.parseInt(list.get(idx++)); 
		this.add_health = Integer.parseInt(list.get(idx++)); 
		this.shuxing_type = Integer.parseInt(list.get(idx++)); 
		this.add_damage = Integer.parseInt(list.get(idx++)); 
		this.add_tianming = Integer.parseInt(list.get(idx++)); 
		this.is_relive = Boolean.getBoolean(list.get(idx++)); 
		this.is_wudi = Boolean.getBoolean(list.get(idx++)); 
		this.add_pojia = Integer.parseInt(list.get(idx++)); 
		this.add_fachuan = Integer.parseInt(list.get(idx++)); 
		this.add_baoji = Integer.parseInt(list.get(idx++)); 
		this.add_mingzhong = Integer.parseInt(list.get(idx++)); 
		this.add_shanbi = Integer.parseInt(list.get(idx++)); 

	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("id", 		               	this.id + "");
		map.put("desc",                  	this.desc + "");
		map.put("jineng_level", 		    this.jineng_level + "");
		map.put("need_pinjie",              this.need_pinjie + "");
		map.put("init_nuqi", 		        this.init_nuqi + "");
		map.put("need_nuqi",                this.need_nuqi + "");
		map.put("consum_nuqi", 		        this.consum_nuqi + "");
		map.put("remain_nuqi",              this.remain_nuqi + "");		
		map.put("attack_num", 		        this.attack_num + "");
		map.put("mubiao_pos",              	this.mubiao_pos + "");	

		map.put("damage_type", 		        this.damage_type + "");
		map.put("damage_min",               this.damage_min + "");
		map.put("damage_max", 		    	this.damage_max + "");
		map.put("add_health_type",          this.add_health_type + "");
		map.put("add_health", 		        this.add_health + "");
		map.put("shuxing_type",             this.shuxing_type + "");
		map.put("add_damage", 		        this.add_damage + "");
		map.put("add_tianming",             this.add_tianming + "");		
		map.put("is_relive", 		        this.is_relive + "");
		map.put("is_wudi",              	this.is_wudi + "");	

		map.put("add_pojia",             	this.add_pojia + "");
		map.put("add_fachuan", 		        this.add_fachuan + "");
		map.put("add_baoji",             	this.add_baoji + "");		
		map.put("add_mingzhong", 		    this.add_mingzhong + "");
		map.put("add_shanbi",              	this.add_shanbi + "");	

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"id","desc","jineng_level","need_pinjie","init_nuqi","need_nuqi",
				"consum_nuqi","remain_nuqi","attack_num","mubiao_pos","damage_type","damage_min",
				"damage_max","add_health_type","add_health","shuxing_type","add_damage",
				"add_tianming","is_relive","is_wudi","add_pojia","add_fachuan","add_baoji",
				"add_mingzhong","add_shanbi"};
	}
}
