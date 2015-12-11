package com.shinian.vo;

import java.io.Serializable;

public class NpcInfoVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private  int id;		// ID from game_npc_info;
	private  int comId;		// ID from common_npc_info;
	private  String uid;	//	ID from game_player_info; 
	private  int pinjie;
	private  int level;
	private  int experience;
	private  int position;

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
	
	private  int healthBase;
	private  int attackBase;
	private  int hujiaBase;
	private  int pojiaBase;
	private  int fachuanBase;
	private  int fakangBase;
	private  int baojiBase;
	private  int renxingBase;
	private  int mingzhongBase;
	private  int shanbiBase;
	private  int xixueBase;
	private  int fantanBase;
	private  int jiyunBase;
	private  int kangyunBase;
	private  int gedangBase;
	private  int gedangPossBase;
	private  int reduceBase;
	
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
	
	public boolean isSkill1() {
		return (skill1 > 0) ? true : false;
	}
	public void setSkill1(boolean skill1) {
		this.skill1 = (skill1) ? Math.abs(this.skill1) : -Math.abs(this.skill1);
	}
	public boolean isSkill2() {
		return (skill2 > 0) ? true : false;
	}
	public void setSkill2(boolean skill2) {
		this.skill2 = (skill2) ? Math.abs(this.skill2) : -Math.abs(this.skill2);
	}
	public boolean isSkill3() {
		return (skill3 > 0) ? true : false;
	}
	public void setSkill3(boolean skill3) {
		this.skill3 = (skill3) ? Math.abs(this.skill3) : -Math.abs(this.skill3);
	}
	public boolean isSkill4() {
		return (skill4 > 0) ? true : false;
	}
	public void setSkill4(boolean skill4) {
		this.skill4 = (skill4) ? Math.abs(this.skill4) : -Math.abs(this.skill4);
	}
	public boolean isSkill5() {
		return (skill5 > 0) ? true : false;
	}
	public void setSkill5(boolean skill5) {
		this.skill5 = (skill5) ? Math.abs(this.skill5) : -Math.abs(this.skill5);
	}
	public boolean isSkill6() {
		return (skill6 > 0) ? true : false;
	}
	public void setSkill6(boolean skill6) {
		this.skill6 = (skill6) ? Math.abs(this.skill6) : -Math.abs(this.skill6);
	}
	public boolean isSkill7() {
		return (skill7 > 0) ? true : false;
	}
	public void setSkill7(boolean skill7) {
		this.skill7 = (skill7) ? Math.abs(this.skill7) : -Math.abs(this.skill7);
	}
	public boolean isSkill8() {
		return (skill8 > 0) ? true : false;
	}
	public void setSkill8(boolean skill8) {
		this.skill8 = (skill8) ? Math.abs(this.skill8) : -Math.abs(this.skill8);
	}
	public boolean isSkill9() {
		return (skill9 > 0) ? true : false;
	}
	public void setSkill9(boolean skill9) {
		this.skill9 = (skill9) ? Math.abs(this.skill9) : -Math.abs(this.skill9);
	}
	public boolean isSkill10() {
		return (skill10 > 0) ? true : false;
	}
	public void setSkill10(boolean skill10) {
		this.skill10 = (skill10) ? Math.abs(this.skill10) : -Math.abs(this.skill10);
	}
	public boolean isSkill11() {
		return (skill11 > 0) ? true : false;
	}
	public void setSkill11(boolean skill11) {
		this.skill11 = (skill11) ? Math.abs(this.skill11) : -Math.abs(this.skill11);
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}		
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
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
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getHealthBase() {
		return healthBase;
	}
	public void setHealthBase(int healthBase) {
		this.healthBase = healthBase;
	}
	
	public int getAttackBase() {
		return attackBase;
	}
	public void setAttackBase(int attackBase) {
		this.attackBase = attackBase;
	}

	public int getHujiaBase() {
		return hujiaBase;
	}
	public void setHujiaBase(int hujiaBase) {
		this.hujiaBase = hujiaBase;
	}
	
	public int getPojiaBase() {
		return pojiaBase;
	}
	public void setPojiaBase(int pojiaBase) {
		this.pojiaBase = pojiaBase;
	}
	
	public int getFachuanBase() {
		return fachuanBase;
	}
	public void setFachuanBase(int fachuanBase) {
		this.fachuanBase = fachuanBase;
	}	
	
	public int getFakangBase() {
		return fakangBase;
	}
	public void setFakangBase(int fakangBase) {
		this.fakangBase = fakangBase;
	}	
	
	public int getRenxingBase() {
		return renxingBase;
	}
	public void setRenxingBase(int renxingBase) {
		this.renxingBase = renxingBase;
	}
	
	public int getMingzhongBase() {
		return mingzhongBase;
	}
	public void setMingzhongBase(int mingzhongBase) {
		this.mingzhongBase = mingzhongBase;
	}
	
	public int getShanbiBase() {
		return shanbiBase;
	}
	public void setShanbiBase(int shanbiBase) {
		this.shanbiBase = shanbiBase;
	}
	
	public int getXixueBase() {
		return xixueBase;
	}
	public void setXixueBase(int xixueBase) {
		this.xixueBase = xixueBase;
	}
	
	public int getFantanBase() {
		return fantanBase;
	}
	public void setFantanBase(int fantanBase) {
		this.fantanBase = fantanBase;
	}
	
	public int getJiyunBase() {
		return jiyunBase;
	}
	public void setJiyunBase(int jiyunBase) {
		this.jiyunBase = jiyunBase;
	}
	
	public int getKangyunBase() {
		return kangyunBase;
	}
	public void setKangyunBase(int kangyunBase) {
		this.kangyunBase = kangyunBase;
	}
	
	public int getGedangBase() {
		return gedangBase;
	}
	public void setGedangBase(int gedangBase) {
		this.gedangBase = gedangBase;
	}
	
	public int getGedangPossBase() {
		return gedangPossBase;
	}
	public void setGedangPossBase(int gedangPossBase) {
		this.gedangPossBase = gedangPossBase;
	}
	
	public int getReduceBase() {
		return reduceBase;
	}
	public void setReduceBase(int reduceBase) {
		this.reduceBase = reduceBase;
	}
	
	public int getBaojiBase() {
		return baojiBase;
	}
	public void setBaojiBase(int baojiBase) {
		this.baojiBase = baojiBase;
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
	
	public int getPinjie() {
		return pinjie;
	}
	public void setPinjie(int pinjie) {
		this.pinjie = pinjie;
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
	
	
	public void enableYuanfen1(){
		this.yuanfen1 = Math.abs(this.yuanfen1);
	}

	public void enableYuanfen2(){
		this.yuanfen2 = Math.abs(this.yuanfen2);
	}

	public void enableYuanfen3(){
		this.yuanfen3 = Math.abs(this.yuanfen3);
	}

	public void enableYuanfen4(){
		this.yuanfen4 = Math.abs(this.yuanfen4);
	}

	public void disableYuanfen1(){
		this.yuanfen1 = -Math.abs(this.yuanfen1);
	}

	public void disableYuanfen2(){
		this.yuanfen2 = -Math.abs(this.yuanfen2);
	}

	public void disableYuanfen3(){
		this.yuanfen3 = -Math.abs(this.yuanfen3);
	}

	public void disableYuanfen4(){
		this.yuanfen4 = -Math.abs(this.yuanfen4);
	}
	
}
