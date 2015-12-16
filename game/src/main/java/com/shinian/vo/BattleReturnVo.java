package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class BattleReturnVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;
	private List<NpcInfoVo> defArmy;
	private List<ActionVo> actions;
	private RewardVo reward;

	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public List<ActionVo> getActions() {
		return actions;
	}
	public void setActions(List<ActionVo> actions) {
		this.actions = actions;
	}
	
	public RewardVo getReward() {
		return reward;
	}
	public void setRewards(RewardVo reward) {
		this.reward = reward;
	}
	
	public List<NpcInfoVo> getDefArmy() {
		return defArmy;
	}
	public void setDefArmy(List<NpcInfoVo> defArmy) {
		this.defArmy = defArmy;
	}
	
	
	
	
}