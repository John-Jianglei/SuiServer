package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class BattleReturnVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;
	private List<NpcInfoVo> offArmy;
	private List<NpcInfoVo> defArmy;
	private List<ActionVo> actions;
	List<RewardVo> rewards;

	
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
	
	public List<RewardVo> getRewards() {
		return rewards;
	}
	public void setRewards(List<RewardVo> rewards) {
		this.rewards = rewards;
	}
	
	public List<NpcInfoVo> getOffArmy() {
		return offArmy;
	}
	public void setOffArmy(List<NpcInfoVo> offArmy) {
		this.offArmy = offArmy;
	}
	
	public List<NpcInfoVo> getDefArmy() {
		return defArmy;
	}
	public void setDefArmy(List<NpcInfoVo> defArmy) {
		this.defArmy = defArmy;
	}
	
	
	
	
}