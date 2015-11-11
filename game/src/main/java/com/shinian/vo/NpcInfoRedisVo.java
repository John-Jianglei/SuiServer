package com.shinian.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class NpcInfoRedisVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	private  int comId;		// ID from common_npc_info;
	private  String name;
	private  int gender;					//	武将性别:0-女，1-男
	private  int star;					//	武将星级
	private  int category;				//	武将类型：1-君主；2-猛将；3-元帅；4-军师；5-智将；
	private  int camp;					//	武将阵营: 1-唐;2-隋;3-反王;
	
	private  int health;
	private  int attack;
	private  int hujia;
	private  int pojia;
	private  int fachuan;
	private  int fakang;
	private  int baoji;
	private  int renxing;
	private  int mingzhong;
	private  int shanbi;
	private  int xixue;
	private  int fantan;
	private  int jiyun;
	private  int kangyun;
	private  int gedang;
	private  int gedangPoss;
	private  int reduce;
	
	private  String talent;
	private  int talentVal;
	private  int attackStep;
	private  int healthStep;
	private  int levelupRate;
	private  int pieces;
	private  int maxPieces;
	private  String desc;
	private  String updateTime;
	private  int status;
	
	
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}

	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getHujia() {
		return hujia;
	}
	public void setHujia(int hujia) {
		this.hujia = hujia;
	}
	
	public int getPojia() {
		return pojia;
	}
	public void setPojia(int pojia) {
		this.pojia = pojia;
	}
	
	public int getFachuan() {
		return fachuan;
	}
	public void setFachuan(int fachuan) {
		this.fachuan = fachuan;
	}	
	
	public int getFakang() {
		return fakang;
	}
	public void setFakang(int fakang) {
		this.fakang = fakang;
	}	
	
	public int getRenxing() {
		return renxing;
	}
	public void setRenxing(int renxing) {
		this.renxing = renxing;
	}
	
	public int getMingzhong() {
		return mingzhong;
	}
	public void setMingzhong(int mingzhong) {
		this.mingzhong = mingzhong;
	}
	
	public int getShanbi() {
		return shanbi;
	}
	public void setShanbi(int shanbi) {
		this.shanbi = shanbi;
	}
	
	public int getXixue() {
		return xixue;
	}
	public void setXixue(int xixue) {
		this.xixue = xixue;
	}
	
	public int getFantan() {
		return fantan;
	}
	public void setFantan(int fantan) {
		this.fantan = fantan;
	}
	
	public int getJiyun() {
		return jiyun;
	}
	public void setJiyun(int jiyun) {
		this.jiyun = jiyun;
	}
	
	public int getKangyun() {
		return kangyun;
	}
	public void setKangyun(int kangyun) {
		this.kangyun = kangyun;
	}
	
	public int getGedang() {
		return gedang;
	}
	public void setGedang(int gedang) {
		this.gedang = gedang;
	}
	
	public int getGedangPoss() {
		return gedangPoss;
	}
	public void setGedangPoss(int gedangPoss) {
		this.gedangPoss = gedangPoss;
	}
	
	public int getReduce() {
		return reduce;
	}
	public void setReduce(int reduce) {
		this.reduce = reduce;
	}
	
	public int getBaoji() {
		return baoji;
	}
	public void setBaoji(int baoji) {
		this.baoji = baoji;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
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
	public int getCamp() {
		return camp;
	}
	public void setCamp(int camp) {
		this.camp = camp;
	}
	
	public String getTalent() {
		return talent;
	}
	public void setTalent(String talent) {
		this.talent = talent;
	}

	public int getTalentVal() {
		return talentVal;
	}
	public void setTalentVal(int talentVal) {
		this.talentVal = talentVal;
	}
	
	public int getAttackStep() {
		return attackStep;
	}
	public void setAttackStep(int attackStep) {
		this.attackStep = attackStep;
	}
	
	public int getHealthStep() {
		return healthStep;
	}
	public void setHealthStep(int healthStep) {
		this.healthStep = healthStep;
	}
	
	public int getLevelupRate() {
		return levelupRate;
	}
	public void setLevelupRate(int levelupRate) {
		this.levelupRate = levelupRate;
	}
	
	public int getPieces() {
		return pieces;
	}
	public void setPieces(int pieces) {
		this.pieces = pieces;
	}
	
	public int getMaxPieces() {
		return maxPieces;
	}
	public void setMaxPieces(int maxPieces) {
		this.maxPieces = maxPieces;
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
		this.gender = Integer.parseInt(list.get(idx++)); 	                  
		this.star = Integer.parseInt(list.get(idx++)); 		              
		this.category = Integer.parseInt(list.get(idx++)); 	              
		this.camp = Integer.parseInt(list.get(idx++)); 		              
		this.health = Integer.parseInt(list.get(idx++));                     
		this.attack = Integer.parseInt(list.get(idx++));                     
		this.hujia = Integer.parseInt(list.get(idx++));                      
		this.pojia = Integer.parseInt(list.get(idx++));                      
		this.fachuan = Integer.parseInt(list.get(idx++));                    
		this.fakang = Integer.parseInt(list.get(idx++));                     
		this.baoji = Integer.parseInt(list.get(idx++));                      
		this.renxing = Integer.parseInt(list.get(idx++));                    
		this.mingzhong = Integer.parseInt(list.get(idx++));                  
		this.shanbi = Integer.parseInt(list.get(idx++));                     
		this.xixue = Integer.parseInt(list.get(idx++));                      
		this.fantan = Integer.parseInt(list.get(idx++));                     
		this.jiyun = Integer.parseInt(list.get(idx++));                      
		this.kangyun = Integer.parseInt(list.get(idx++));                    
		this.gedang = Integer.parseInt(list.get(idx++));                     
		this.gedangPoss = Integer.parseInt(list.get(idx++));                 
		this.reduce = Integer.parseInt(list.get(idx++));                     
		this.talent = list.get(idx++);                     
		this.talentVal = Integer.parseInt(list.get(idx++));                  
		this.attackStep = Integer.parseInt(list.get(idx++));                 
		this.healthStep = Integer.parseInt(list.get(idx++));                 
		this.levelupRate = Integer.parseInt(list.get(idx++));                
		this.pieces = Integer.parseInt(list.get(idx++));                     
		this.maxPieces = Integer.parseInt(list.get(idx++));                  
		this.desc = list.get(idx++);                       
		this.updateTime = list.get(idx++);                 
		this.status = Integer.parseInt(list.get(idx++));                     
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("comId", 		               this.comId + "");
		map.put("name",                        this.name + "");
		map.put("gender", 	                   this.gender + "");
		map.put("star", 		               this.star + "");
		map.put("category", 	               this.category + "");
		map.put("camp", 		               this.camp + "");
		map.put("health",                      this.health + "");
		map.put("attack",                      this.attack + "");
		map.put("hujia",                       this.hujia + "");
		map.put("pojia",                       this.pojia + "");
		map.put("fachuan",                     this.fachuan + "");
		map.put("fakang",                      this.fakang + "");
		map.put("baoji",                       this.baoji + "");
		map.put("renxing",                     this.renxing + "");
		map.put("mingzhong",                   this.mingzhong + "");
		map.put("shanbi",                      this.shanbi + "");
		map.put("xixue",                       this.xixue + "");
		map.put("fantan",                      this.fantan + "");
		map.put("jiyun",                       this.jiyun + "");
		map.put("kangyun",                     this.kangyun + "");
		map.put("gedang",                      this.gedang + "");
		map.put("gedangPoss",                  this.gedangPoss + "");
		map.put("reduce",                      this.reduce + "");
		map.put("talent",                      this.talent + "");
		map.put("talentVal",                   this.talentVal + "");
		map.put("attackStep",                  this.attackStep + "");
		map.put("healthStep",                  this.healthStep + "");
		map.put("levelupRate",                 this.levelupRate + "");
		map.put("pieces",                      this.pieces + "");
		map.put("maxPieces",                   this.maxPieces + "");
		map.put("desc",                        this.desc + "");
		map.put("updateTime",                  this.updateTime + "");
		map.put("status",                      this.status + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"comId", "name", "gender", "star", "category", "camp", "health", "attack", "hujia", "pojia", "fachuan", "fakang", "baoji", "renxing", "mingzhong", "shanbi", "xixue", "fantan", "jiyun", "kangyun", "gedang", "gedangPoss", "reduce", "talent", "talentVal", "attackStep", "healthStep", "levelupRate", "pieces", "maxPieces", "desc", "updateTime", "status"};
	}

}
