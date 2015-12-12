package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class PassZhanyiVo  extends BaseObject implements Serializable{

	private static final long serialVersionUID = 1L;

	protected int id;		// battle ID;
	protected String name;
	private int comId0;			//0号位武将ID
	private int comId1;			//1号位武将ID
	private int comId2;			//2号位武将ID
	private int comId3;			//3号位武将ID
	private int comId4;			//4号位武将ID
	private int comId5;			//5号位武将ID
	private int attackTimes;	//战斗力倍数
	private int battleCount;	//可扫荡次数
	private int exp;			//获得经验
	private int sliver;			//获得银两
	private int comType1;		//奖励物品1类型
	private int comId11;		//奖励物品1 ID
	private int comNum1;		//奖励物品1数量
	private int	rewardP1;		//奖励物品1概率(0-100)，数据库中没有定义，后补
	private int comType2;		//奖励物品2类型
	private int comId12;		//奖励物品2 ID
	private int comNum2;		//奖励物品2数量
	private int	rewardP2;		//奖励物品2概率(0-100)，数据库中没有定义，后补
	private int comType3;		//奖励物品3类型
	private int comId13;		//奖励物品3 ID
	private int comNum3;		//奖励物品3数量
	private int	rewardP3;		//奖励物品3概率(0-100)，数据库中没有定义，后补
	private int comType4;		//奖励物品4类型
	private int comId14;		//奖励物品4 ID
	private int comNum4;		//奖励物品4数量
	private int	rewardP4;		//奖励物品4概率(0-100)，数据库中没有定义，后补
	private int comType5;		//奖励物品5类型
	private int comId15;		//奖励物品5 ID
	private int comNum5;		//奖励物品5数量
	private int	rewardP5;		//奖励物品5概率(0-100)，数据库中没有定义，后补
	private int comType6;		//奖励物品6类型
	private int comId16;		//奖励物品6 ID
	private int comNum6;		//奖励物品6数量
	private int	rewardP6;		//奖励物品6概率(0-100)，数据库中没有定义，后补
	
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
	public int getComId0() {
		return comId0;
	}
	public void setComId0(int comId0) {
		this.comId0 = comId0;
	}
	public int getComId1() {
		return comId1;
	}
	public void setComId1(int comId1) {
		this.comId1 = comId1;
	}
	public int getComId2() {
		return comId2;
	}
	public void setComId2(int comId2) {
		this.comId2 = comId2;
	}
	public int getComId3() {
		return comId3;
	}
	public void setComId3(int comId3) {
		this.comId3 = comId3;
	}
	public int getComId4() {
		return comId4;
	}
	public void setComId4(int comId4) {
		this.comId4 = comId4;
	}
	public int getComId5() {
		return comId5;
	}
	public void setComId5(int comId5) {
		this.comId5 = comId5;
	}
	public int getAttackTimes() {
		return attackTimes;
	}
	public void setAttackTimes(int attackTimes) {
		this.attackTimes = attackTimes;
	}
	public int getBattleCount() {
		return battleCount;
	}
	public void setBattleCount(int battleCount) {
		this.battleCount = battleCount;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getSliver() {
		return sliver;
	}
	public void setSliver(int sliver) {
		this.sliver = sliver;
	}
	public int getComType1() {
		return comType1;
	}
	public void setComType1(int comType1) {
		this.comType1 = comType1;
	}
	public int getComId11() {
		return comId11;
	}
	public void setComId11(int comId11) {
		this.comId11 = comId11;
	}
	public int getComNum1() {
		return comNum1;
	}
	public void setComNum1(int comNum1) {
		this.comNum1 = comNum1;
	}
	public int getRewardP1() {
		return rewardP1;
	}
	public void setRewardP1(int rewardP1) {
		this.rewardP1 = rewardP1;
	}
	public int getComType2() {
		return comType2;
	}
	public void setComType2(int comType2) {
		this.comType2 = comType2;
	}
	public int getComId12() {
		return comId12;
	}
	public void setComId12(int comId12) {
		this.comId12 = comId12;
	}
	public int getComNum2() {
		return comNum2;
	}
	public void setComNum2(int comNum2) {
		this.comNum2 = comNum2;
	}
	public int getRewardP2() {
		return rewardP2;
	}
	public void setRewardP2(int rewardP2) {
		this.rewardP2 = rewardP2;
	}
	public int getComType3() {
		return comType3;
	}
	public void setComType3(int comType3) {
		this.comType3 = comType3;
	}
	public int getComId13() {
		return comId13;
	}
	public void setComId13(int comId13) {
		this.comId13 = comId13;
	}
	public int getComNum3() {
		return comNum3;
	}
	public void setComNum3(int comNum3) {
		this.comNum3 = comNum3;
	}
	public int getRewardP3() {
		return rewardP3;
	}
	public void setRewardP3(int rewardP3) {
		this.rewardP3 = rewardP3;
	}
	public int getComType4() {
		return comType4;
	}
	public void setComType4(int comType4) {
		this.comType4 = comType4;
	}
	public int getComId14() {
		return comId14;
	}
	public void setComId14(int comId14) {
		this.comId14 = comId14;
	}
	public int getComNum4() {
		return comNum4;
	}
	public void setComNum4(int comNum4) {
		this.comNum4 = comNum4;
	}
	public int getRewardP4() {
		return rewardP4;
	}
	public void setRewardP4(int rewardP4) {
		this.rewardP4 = rewardP4;
	}
	public int getComType5() {
		return comType5;
	}
	public void setComType5(int comType5) {
		this.comType5 = comType5;
	}
	public int getComId15() {
		return comId15;
	}
	public void setComId15(int comId15) {
		this.comId15 = comId15;
	}
	public int getComNum5() {
		return comNum5;
	}
	public void setComNum5(int comNum5) {
		this.comNum5 = comNum5;
	}
	public int getRewardP5() {
		return rewardP5;
	}
	public void setRewardP5(int rewardP5) {
		this.rewardP5 = rewardP5;
	}
	public int getComType6() {
		return comType6;
	}
	public void setComType6(int comType6) {
		this.comType6 = comType6;
	}
	public int getComId16() {
		return comId16;
	}
	public void setComId16(int comId16) {
		this.comId16 = comId16;
	}
	public int getComNum6() {
		return comNum6;
	}
	public void setComNum6(int comNum6) {
		this.comNum6 = comNum6;
	}
	public int getRewardP6() {
		return rewardP6;
	}
	public void setRewardP6(int rewardP6) {
		this.rewardP6 = rewardP6;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}