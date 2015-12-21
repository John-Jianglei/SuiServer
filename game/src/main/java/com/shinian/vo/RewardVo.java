package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class RewardVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;
	private int star;
	private List<DropRewardVo> dropRewardlist;
	private int playerExp;
	
	public int getPlayerExp() {
		return playerExp;
	}
	public void setPlayerExp(int playerExp) {
		this.playerExp = playerExp;
	}
	public List<DropRewardVo> getDropRewardlist() {
		return dropRewardlist;
	}
	public void setDropRewardlist(List<DropRewardVo> dropRewardlist) {
		this.dropRewardlist = dropRewardlist;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}