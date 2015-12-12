package com.shinian.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.shinian.util.Nature;



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
	private	 int pieceId;
	private	 int skill1;
	private	 int skill2;
	private	 int skill3;
	private	 int skill4;
	private	 int skill5;
	private	 int skill6;
	private	 int skill7;
	private	 int skill8;
	private	 int skill9;
	private	 int skill10;
	private	 int skill11;
	private int yuanfen1;
	private int yuanfen2;
	private int yuanfen3;
	private int yuanfen4;
	
	public int getYuanfen1() {
		return yuanfen1;
	}
	
	public void setYuanfen1(int yuanfen1) {
		this.yuanfen1 = yuanfen1;
	}	
	
	public int getYuanfen2() {
		return yuanfen2;
	}
	
	public void setYuanfen2(int yuanfen2) {
		this.yuanfen2 = yuanfen2;
	}	
	
	public int getYuanfen3() {
		return yuanfen3;
	}
	
	public void setYuanfen3(int yuanfen3) {
		this.yuanfen3 = yuanfen3;
	}	
	
	public int getYuanfen4() {
		return yuanfen4;
	}
	
	public void setYuanfen4(int yuanfen4) {
		this.yuanfen4 = yuanfen4;
	}	

	public int getPieceId() {
		return pieceId;
	}
	public void setPieceId(int pieceId) {
		this.pieceId = pieceId;
	}
	
	public int getSkill1() {
		return skill1;
	}
	public void setSkill1(int skill1) {
		this.skill1 = skill1;
	}
	
	public int getSkill2() {
		return skill2;
	}
	public void setSkill2(int skill2) {
		this.skill2 = skill2;
	}
	
	public int getSkill3() {
		return skill3;
	}
	public void setSkill3(int skill3) {
		this.skill3 = skill3;
	}
	
	public int getSkill4() {
		return skill4;
	}
	public void setSkill4(int skill4) {
		this.skill4 = skill4;
	}
	
	public int getSkill5() {
		return skill5;
	}
	public void setSkill5(int skill5) {
		this.skill5 = skill5;
	}
	
	public int getSkill6() {
		return skill6;
	}
	public void setSkill6(int skill6) {
		this.skill6 = skill6;
	}
	
	public int getSkill7() {
		return skill7;
	}
	public void setSkill7(int skill7) {
		this.skill7 = skill7;
	}
	
	public int getSkill8() {
		return skill8;
	}
	public void setSkill8(int skill8) {
		this.skill8 = skill8;
	}
	
	public int getSkill9() {
		return skill9;
	}
	public void setSkill9(int skill9) {
		this.skill9 = skill9;
	}
	
	public int getSkill10() {
		return skill10;
	}
	public void setSkill10(int skill10) {
		this.skill10 = skill10;
	}
	
	public int getSkill11() {
		return skill11;
	}
	public void setSkill11(int skill11) {
		this.skill11 = skill11;
	}
	
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
		this.pieceId = Integer.parseInt(list.get(idx++));
		this.skill1 = Integer.parseInt(list.get(idx++));
		this.skill2 = Integer.parseInt(list.get(idx++));
		this.skill3 = Integer.parseInt(list.get(idx++));
		this.skill4 = Integer.parseInt(list.get(idx++));
		this.skill5 = Integer.parseInt(list.get(idx++));
		this.skill6 = Integer.parseInt(list.get(idx++));
		this.skill7 = Integer.parseInt(list.get(idx++));
		this.skill8 = Integer.parseInt(list.get(idx++));
		this.skill9 = Integer.parseInt(list.get(idx++));
		this.skill10 = Integer.parseInt(list.get(idx++));
		this.skill11 = Integer.parseInt(list.get(idx++));
		this.yuanfen1 = Integer.parseInt(list.get(idx++));
		this.yuanfen2 = Integer.parseInt(list.get(idx++));
		this.yuanfen3 = Integer.parseInt(list.get(idx++));
		this.yuanfen4 = Integer.parseInt(list.get(idx++));

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
		map.put("pieceId",                     this.pieceId + "");
		map.put("skill1",                      this.skill1 + "");
		map.put("skill2",                      this.skill2 + "");
		map.put("skill3",                      this.skill3 + "");
		map.put("skill4",                      this.skill4 + "");
		map.put("skill5",                      this.skill5 + "");
		map.put("skill6",                      this.skill6 + "");
		map.put("skill7",                      this.skill7 + "");
		map.put("skill8",                      this.skill8 + "");
		map.put("skill9",                      this.skill9 + "");
		map.put("skill10",                     this.skill10 + "");
		map.put("skill11",                     this.skill11 + "");
		map.put("yuanfen1",                     this.yuanfen1 + "");
		map.put("yuanfen2",                     this.yuanfen2 + "");
		map.put("yuanfen3",                     this.yuanfen3 + "");
		map.put("yuanfen4",                     this.yuanfen4 + "");

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"comId", "name", "gender", "star", "category", "camp", 
				"health", "attack", "hujia", "pojia", "fachuan", "fakang", "baoji", 
				"renxing", "mingzhong", "shanbi", "xixue", "fantan", "jiyun", "kangyun", 
				"gedang", "gedangPoss", "reduce", "talent", "talentVal", "attackStep", 
				"healthStep", "levelupRate", "pieces", "maxPieces", "desc", "updateTime", 
				"status", "pieceId", "skill1", "skill2", "skill3", "skill4", "skill5", 
				"skill6", "skill7", "skill8", "skill9", "skill10", "skill11", "yuanfen1", "yuanfen2", "yuanfen3", "yuanfen4"};
	}

	public NpcInfoVo initGameNpc()
	{
		NpcInfoVo npc = new NpcInfoVo();
		
		npc.setComId(this.comId);
		npc.setHealthBase(this.health); 
		npc.setAttackBase(this.attack); 
		npc.setHujiaBase(this.hujia); 
		npc.setPojiaBase(this.pojia); 
		npc.setFachuanBase(this.fachuan); 
		npc.setFakangBase(this.fakang); 
		npc.setBaojiBase(this.baoji); 
		npc.setRenxingBase(this.renxing); 
		npc.setMingzhongBase(this.mingzhong); 
		npc.setShanbiBase(this.shanbi); 
		npc.setXixueBase(this.xixue); 
		npc.setFantanBase(this.fantan); 
		npc.setJiyunBase(this.jiyun); 
		npc.setKangyunBase(this.kangyun); 
		npc.setGedangBase(this.gedang); 
		npc.setGedangPossBase(this.gedangPoss); 
		npc.setReduceBase(this.reduce);
		npc.setSkill1(-this.skill1);
		npc.setSkill2(-this.skill2);
		npc.setSkill3(-this.skill3);
		npc.setSkill4(-this.skill4);
		npc.setSkill5(-this.skill5);
		npc.setSkill6(-this.skill6);
		npc.setSkill7(-this.skill7);
		npc.setSkill8(-this.skill8);
		npc.setSkill9(-this.skill9);
		npc.setSkill10(-this.skill10);
		npc.setSkill11(-this.skill11);
		npc.setYuanfen1(-this.yuanfen1);
		npc.setYuanfen2(-this.yuanfen2);
		npc.setYuanfen3(-this.yuanfen3);
		npc.setYuanfen4(-this.yuanfen4);
		
		String tlt = this.talent.trim().toLowerCase();
		int tval = this.talentVal;
		if (tlt.equalsIgnoreCase(Nature.NT_MSG_HEALTH))
			npc.setHealthBase(this.health + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_ATTACK))
			npc.setAttackBase(this.attack + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_HUJIA))
			npc.setHujiaBase(this.hujia + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_POJIA))
			npc.setPojiaBase(this.pojia + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_FACHUAN))
			npc.setFachuanBase(this.fachuan + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_FAKANG))
			npc.setFakangBase(this.fakang + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_BAOJI))
			npc.setBaojiBase(this.baoji + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_RENXING))
			npc.setRenxingBase(this.renxing + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_MINGZHONG))
			npc.setMingzhongBase(this.mingzhong + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_SHANBI))
			npc.setShanbiBase(this.shanbi + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_XIXUE))
			npc.setXixueBase(this.xixue + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_FANTAN))
			npc.setFantanBase(this.fantan + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_JIYUN))
			npc.setJiyunBase(this.jiyun + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_KANGYUN))
			npc.setKangyunBase(this.kangyun + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_GEDANG))
			npc.setGedangBase(this.gedang + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_GEDANGPOSS))
			npc.setGedangPossBase(this.gedangPoss + tval);
		else if (tlt.equalsIgnoreCase(Nature.NT_MSG_REDUCE))
			npc.setReduceBase(this.reduce + tval);
		
		return npc;
	}
}
