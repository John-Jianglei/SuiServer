package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class BattleReturnVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;
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
	
	
	
	
}