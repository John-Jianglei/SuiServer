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
	private int rewardType1;	//奖励物品1类型
	
	public int getRewardType1() {
		return rewardType1;
	}
	public void setRewardType1(int rewardType1) {
		this.rewardType1 = rewardType1;
	}
	public int getRewardId11() {
		return rewardId11;
	}
	public void setRewardId11(int rewardId11) {
		this.rewardId11 = rewardId11;
	}
	public int getRewardNum1() {
		return rewardNum1;
	}
	public void setRewardNum1(int rewardNum1) {
		this.rewardNum1 = rewardNum1;
	}
	public int getRewardP1() {
		return rewardP1;
	}
	public void setRewardP1(int rewardP1) {
		this.rewardP1 = rewardP1;
	}
	public int getRewardType2() {
		return rewardType2;
	}
	public void setRewardType2(int rewardType2) {
		this.rewardType2 = rewardType2;
	}
	public int getRewardId12() {
		return rewardId12;
	}
	public void setRewardId12(int rewardId12) {
		this.rewardId12 = rewardId12;
	}
	public int getRewardNum2() {
		return rewardNum2;
	}
	public void setRewardNum2(int rewardNum2) {
		this.rewardNum2 = rewardNum2;
	}
	public int getRewardP2() {
		return rewardP2;
	}
	public void setRewardP2(int rewardP2) {
		this.rewardP2 = rewardP2;
	}
	public int getRewardType3() {
		return rewardType3;
	}
	public void setRewardType3(int rewardType3) {
		this.rewardType3 = rewardType3;
	}
	public int getRewardId13() {
		return rewardId13;
	}
	public void setRewardId13(int rewardId13) {
		this.rewardId13 = rewardId13;
	}
	public int getRewardNum3() {
		return rewardNum3;
	}
	public void setRewardNum3(int rewardNum3) {
		this.rewardNum3 = rewardNum3;
	}
	public int getRewardP3() {
		return rewardP3;
	}
	public void setRewardP3(int rewardP3) {
		this.rewardP3 = rewardP3;
	}
	public int getRewardType4() {
		return rewardType4;
	}
	public void setRewardType4(int rewardType4) {
		this.rewardType4 = rewardType4;
	}
	public int getRewardId14() {
		return rewardId14;
	}
	public void setRewardId14(int rewardId14) {
		this.rewardId14 = rewardId14;
	}
	public int getRewardNum4() {
		return rewardNum4;
	}
	public void setRewardNum4(int rewardNum4) {
		this.rewardNum4 = rewardNum4;
	}
	public int getRewardP4() {
		return rewardP4;
	}
	public void setRewardP4(int rewardP4) {
		this.rewardP4 = rewardP4;
	}
	private int rewardId11;		//奖励物品1 ID
	private int rewardNum1;		//奖励物品1数量
	private int	rewardP1;		//奖励物品1概率(0-100)，数据库中没有定义，后补
	private int rewardType2;		//奖励物品2类型
	private int rewardId12;		//奖励物品2 ID
	private int rewardNum2;		//奖励物品2数量
	private int	rewardP2;		//奖励物品2概率(0-100)，数据库中没有定义，后补
	private int rewardType3;		//奖励物品3类型
	private int rewardId13;		//奖励物品3 ID
	private int rewardNum3;		//奖励物品3数量
	private int	rewardP3;		//奖励物品3概率(0-100)，数据库中没有定义，后补
	private int rewardType4;		//奖励物品4类型
	private int rewardId14;		//奖励物品4 ID
	private int rewardNum4;		//奖励物品4数量
	private int	rewardP4;		//奖励物品4概率(0-100)，数据库中没有定义，后补
	
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

	

}