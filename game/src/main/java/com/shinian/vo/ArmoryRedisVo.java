package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmoryRedisVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;

		private int id;
		private String name;
		private int category;
		private int star;
		private int initSliver;
		private int sliverStep;
		private int attack;
		private int attackStep;
		private int attackStepJinglian;
		private int addAttack;
		private int addAttackGaoji;
		private int health;
		private int healthStep;
		private int healthStepJinglian;
		private int addHealth;
		private int addHealthGaoji;
		private int pojia;
		private int hujia;
		private int fachuan;
		private int fakang;
		private int baoji;
		private int renxing;
		private int mingzhong;
		private int shanbi;
		private int xixue;
		private int fantan;
		private int jiyun;
		private int kangyun;
		private int gedang;
		private int gedangPoss;
		private int reduce;
		private int pojiaGaoji;
		private int hujiaGaoji;
		private int fachuanGaoji;
		private int fakangGaoji;
		private int baojiGaoji;
		private int renxingGaoji;
		private int mingzhongGaoji;
		private int shanbiGaoji;
		private int xixueGaoji;
		private int fantanGaoji;
		private int jiyunGaoji;
		private int kangyunGaoji;
		private int gedangGaoji;
		private int gedangPossGaoji;
		private int reduceGaoji;
		private int pieceId;
		private int pieces;
		private int minPieces;
		private String desc;
		private String updateTime;
		private int status;
		
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
		public int getCategory() {
			return category;
		}
		public void setCategory(int category) {
			this.category = category;
		}
		public int getStar() {
			return star;
		}
		public void setStar(int star) {
			this.star = star;
		}
		public int getInitSliver() {
			return initSliver;
		}
		public void setInitSliver(int initSliver) {
			this.initSliver = initSliver;
		}
		public int getSliverStep() {
			return sliverStep;
		}
		public void setSliverStep(int sliverStep) {
			this.sliverStep = sliverStep;
		}
		public int getAttack() {
			return attack;
		}
		public void setAttack(int attack) {
			this.attack = attack;
		}
		public int getAttackStep() {
			return attackStep;
		}
		public void setAttackStep(int attackStep) {
			this.attackStep = attackStep;
		}
		public int getAttackStepJinglian() {
			return attackStepJinglian;
		}
		public void setAttackStepJinglian(int attackStepJinglian) {
			this.attackStepJinglian = attackStepJinglian;
		}
		public int getAddAttack() {
			return addAttack;
		}
		public void setAddAttack(int addAttack) {
			this.addAttack = addAttack;
		}
		public int getAddAttackGaoji() {
			return addAttackGaoji;
		}
		public void setAddAttackGaoji(int addAttackGaoji) {
			this.addAttackGaoji = addAttackGaoji;
		}
		public int getHealth() {
			return health;
		}
		public void setHealth(int health) {
			this.health = health;
		}
		public int getHealthStep() {
			return healthStep;
		}
		public void setHealthStep(int healthStep) {
			this.healthStep = healthStep;
		}
		public int getHealthStepJinglian() {
			return healthStepJinglian;
		}
		public void setHealthStepJinglian(int healthStepJinglian) {
			this.healthStepJinglian = healthStepJinglian;
		}
		public int getAddHealth() {
			return addHealth;
		}
		public void setAddHealth(int addHealth) {
			this.addHealth = addHealth;
		}
		public int getAddHealthGaoji() {
			return addHealthGaoji;
		}
		public void setAddHealthGaoji(int addHealthGaoji) {
			this.addHealthGaoji = addHealthGaoji;
		}
		public int getPojia() {
			return pojia;
		}
		public void setPojia(int pojia) {
			this.pojia = pojia;
		}
		public int getHujia() {
			return hujia;
		}
		public void setHujia(int hujia) {
			this.hujia = hujia;
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
		public int getBaoji() {
			return baoji;
		}
		public void setBaoji(int baoji) {
			this.baoji = baoji;
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
		public int getPojiaGaoji() {
			return pojiaGaoji;
		}
		public void setPojiaGaoji(int pojiaGaoji) {
			this.pojiaGaoji = pojiaGaoji;
		}
		public int getHujiaGaoji() {
			return hujiaGaoji;
		}
		public void setHujiaGaoji(int hujiaGaoji) {
			this.hujiaGaoji = hujiaGaoji;
		}
		public int getFachuanGaoji() {
			return fachuanGaoji;
		}
		public void setFachuanGaoji(int fachuanGaoji) {
			this.fachuanGaoji = fachuanGaoji;
		}
		public int getFakangGaoji() {
			return fakangGaoji;
		}
		public void setFakangGaoji(int fakangGaoji) {
			this.fakangGaoji = fakangGaoji;
		}
		public int getBaojiGaoji() {
			return baojiGaoji;
		}
		public void setBaojiGaoji(int baojiGaoji) {
			this.baojiGaoji = baojiGaoji;
		}
		public int getRenxingGaoji() {
			return renxingGaoji;
		}
		public void setRenxingGaoji(int renxingGaoji) {
			this.renxingGaoji = renxingGaoji;
		}
		public int getMingzhongGaoji() {
			return mingzhongGaoji;
		}
		public void setMingzhongGaoji(int mingzhongGaoji) {
			this.mingzhongGaoji = mingzhongGaoji;
		}
		public int getShanbiGaoji() {
			return shanbiGaoji;
		}
		public void setShanbiGaoji(int shanbiGaoji) {
			this.shanbiGaoji = shanbiGaoji;
		}
		public int getXixueGaoji() {
			return xixueGaoji;
		}
		public void setXixueGaoji(int xixueGaoji) {
			this.xixueGaoji = xixueGaoji;
		}
		public int getFantanGaoji() {
			return fantanGaoji;
		}
		public void setFantanGaoji(int fantanGaoji) {
			this.fantanGaoji = fantanGaoji;
		}
		public int getJiyunGaoji() {
			return jiyunGaoji;
		}
		public void setJiyunGaoji(int jiyunGaoji) {
			this.jiyunGaoji = jiyunGaoji;
		}
		public int getKangyunGaoji() {
			return kangyunGaoji;
		}
		public void setKangyunGaoji(int kangyunGaoji) {
			this.kangyunGaoji = kangyunGaoji;
		}
		public int getGedangGaoji() {
			return gedangGaoji;
		}
		public void setGedangGaoji(int gedangGaoji) {
			this.gedangGaoji = gedangGaoji;
		}
		public int getGedangPossGaoji() {
			return gedangPossGaoji;
		}
		public void setGedangPossGaoji(int gedangPossGaoji) {
			this.gedangPossGaoji = gedangPossGaoji;
		}
		public int getReduceGaoji() {
			return reduceGaoji;
		}
		public void setReduceGaoji(int reduceGaoji) {
			this.reduceGaoji = reduceGaoji;
		}
		public int getPieceId() {
			return pieceId;
		}
		public void setPieceId(int pieceId) {
			this.pieceId = pieceId;
		}
		public int getPieces() {
			return pieces;
		}
		public void setPieces(int pieces) {
			this.pieces = pieces;
		}
		public int getMinPieces() {
			return minPieces;
		}
		public void setMinPieces(int minPieces) {
			this.minPieces = minPieces;
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
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		public void fromList(List<String> list) {
			int idx = 0;
			
			this.id = Integer.parseInt(list.get(idx++));   
			this.name = list.get(idx++);   
			this.category = Integer.parseInt(list.get(idx++));   
			this.star = Integer.parseInt(list.get(idx++));   
			this.initSliver = Integer.parseInt(list.get(idx++));   
			this.sliverStep = Integer.parseInt(list.get(idx++));   
			this.attack = Integer.parseInt(list.get(idx++));   
			this.attackStep = Integer.parseInt(list.get(idx++));   
			this.attackStepJinglian = Integer.parseInt(list.get(idx++));   
			this.addAttack = Integer.parseInt(list.get(idx++));   
			this.addAttackGaoji = Integer.parseInt(list.get(idx++));   
			this.health = Integer.parseInt(list.get(idx++));   
			this.healthStep = Integer.parseInt(list.get(idx++));   
			this.healthStepJinglian = Integer.parseInt(list.get(idx++));   
			this.addHealth = Integer.parseInt(list.get(idx++));   
			this.addHealthGaoji = Integer.parseInt(list.get(idx++));   
			this.pojia = Integer.parseInt(list.get(idx++));   
			this.hujia = Integer.parseInt(list.get(idx++));   
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
			this.pojiaGaoji = Integer.parseInt(list.get(idx++));   
			this.hujiaGaoji = Integer.parseInt(list.get(idx++));   
			this.fachuanGaoji = Integer.parseInt(list.get(idx++));   
			this.fakangGaoji = Integer.parseInt(list.get(idx++));   
			this.baojiGaoji = Integer.parseInt(list.get(idx++));   
			this.renxingGaoji = Integer.parseInt(list.get(idx++));   
			this.mingzhongGaoji = Integer.parseInt(list.get(idx++));   
			this.shanbiGaoji = Integer.parseInt(list.get(idx++));   
			this.xixueGaoji = Integer.parseInt(list.get(idx++));   
			this.fantanGaoji = Integer.parseInt(list.get(idx++));   
			this.jiyunGaoji = Integer.parseInt(list.get(idx++));   
			this.kangyunGaoji = Integer.parseInt(list.get(idx++));   
			this.gedangGaoji = Integer.parseInt(list.get(idx++));   
			this.gedangPossGaoji = Integer.parseInt(list.get(idx++));   
			this.reduceGaoji = Integer.parseInt(list.get(idx++));   
			this.pieceId = Integer.parseInt(list.get(idx++));   
			this.pieces = Integer.parseInt(list.get(idx++));   
			this.minPieces = Integer.parseInt(list.get(idx++));   
			this.desc = list.get(idx++);   
			this.updateTime = list.get(idx++);   
			this.status = Integer.parseInt(list.get(idx++));     
		}
		
		
		public Map<String, String> toMap() {
			Map<String, String> map = new HashMap<String, String>();
					
			map.put("id", 					 		this.id + "");			
			map.put("name", 				 		this.name + "");			
			map.put("category", 			 		this.category + "");				
			map.put("star", 				 		this.star + "");								
			map.put("initSliver", 			 		this.initSliver + "");								
			map.put("sliverStep", 			 		this.sliverStep + "");												
			map.put("attack", 				 		this.attack + "");											
			map.put("attackStep", 			 		this.attackStep + "");											
			map.put("attackStepJinglian", 	 		this.attackStepJinglian + "");											
			map.put("addAttack", 			 		this.addAttack + "");									
			map.put("addAttackGaoji", 			 	this.addAttackGaoji + "");									
			map.put("health", 				 		this.health + "");											
			map.put("healthStep", 			 		this.healthStep + "");											
			map.put("healthStepJinglian", 	 		this.healthStepJinglian + "");						
			map.put("addHealth", 			 		this.addHealth + "");										
			map.put("addHealthGaoji", 		 		this.addHealthGaoji + "");										
			map.put("pojia", 				 		this.pojia + "");												
			map.put("hujia", 				 		this.hujia + "");													
			map.put("fachuan", 				 		this.fachuan + "");													
			map.put("fakang", 				 		this.fakang + "");											
			map.put("baoji", 				 		this.baoji + "");										
			map.put("renxing", 						this.renxing + "");											
			map.put("mingzhong", 					this.mingzhong + "");										
			map.put("shanbi", 						this.shanbi + "");											
			map.put("xixue", 						this.xixue + "");											
			map.put("fantan", 						this.fantan + "");													
			map.put("jiyun", 						this.jiyun + "");										
			map.put("kangyun", 						this.kangyun + "");												
			map.put("gedang", 						this.gedang + "");											
			map.put("gedangPoss", 					this.gedangPoss + "");															
			map.put("reduce", 						this.reduce + "");												
			map.put("pojiaGaoji", 					this.pojiaGaoji + "");												
			map.put("hujiaGaoji", 					this.hujiaGaoji + "");											
			map.put("fachuanGaoji", 				this.fachuanGaoji + "");											
			map.put("fakangGaoji", 					this.fakangGaoji + "");													
			map.put("baojiGaoji", 					this.baojiGaoji + "");															
			map.put("renxingGaoji", 				this.renxingGaoji + "");															
			map.put("mingzhongGaoji", 				this.mingzhongGaoji + "");											
			map.put("shanbiGaoji", 					this.shanbiGaoji + "");																		
			map.put("xixueGaoji", 					this.xixueGaoji + "");														
			map.put("fantanGaoji", 					this.fantanGaoji + "");												
			map.put("jiyunGaoji", 					this.jiyunGaoji + "");												
			map.put("kangyunGaoji", 				this.kangyunGaoji + "");															
			map.put("gedangGaoji", 					this.gedangGaoji + "");				
			map.put("gedangPossGaoji", 				this.gedangPossGaoji + "");					
			map.put("reduceGaoji", 					this.reduceGaoji + "");										
			map.put("pieceId", 						this.pieceId + "");										
			map.put("pieces", 						this.pieces + "");											
			map.put("minPieces", 					this.minPieces + "");													
			map.put("desc", 						this.desc + "");						
			map.put("updateTime", 					this.updateTime + "");							
			map.put("status", 						this.status + "");			

			return map;
		}
		
		public String[] getFieldNames() {
			return new String[] {"id", "name", "category", "star", "initSliver", "sliverStep", "attack", 
					"attackStep", "attackStepJinglian", "addAttack", "addAttackGaoji", "health", "healthStep", 
					"healthStepJinglian", "addHealth", "addHealthGaoji", "pojia", "hujia", "fachuan", "fakang", 
					"baoji", "renxing", "mingzhong", "shanbi", "xixue", "fantan", "jiyun", "kangyun", "gedang", 
					"gedangPoss", "reduce", "pojiaGaoji", "hujiaGaoji", "fachuanGaoji", "fakangGaoji", "baojiGaoji", 
					"renxingGaoji", "mingzhongGaoji", "shanbiGaoji", "xixueGaoji", "fantanGaoji", "jiyunGaoji", 
					"kangyunGaoji", "gedangGaoji", "gedangPossGaoji", "reduceGaoji", "pieceId", "pieces", "minPieces", 
					"desc", "updateTime", "status"};
		}
		
}
