//战斗或者扫荡，或者其他，掉落的物品
package com.shinian.vo;

import java.io.Serializable;

public class DropRewardVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	private int rewardId;
	private	int	rewardType;
	private int rewardNum;
	
	public int getRewardId() {
		return rewardId;
	}
	public void setRewardId(int rewardId) {
		this.rewardId = rewardId;
	}
	public int getRewardType() {
		return rewardType;
	}
	public void setRewardType(int rewardType) {
		this.rewardType = rewardType;
	}
	public int getRewardNum() {
		return rewardNum;
	}
	public void setRewardNum(int rewardNum) {
		this.rewardNum = rewardNum;
	}
	
}
