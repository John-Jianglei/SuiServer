//扫荡返回数据
package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class PassReturnVo extends BaseObject implements Serializable{	
	private static final long serialVersionUID = 1L;

	private String token;
	
	private		String	uid;
	private 	int 	level;
	private		long	sliver;
	private		int		exp;	//当前经验	
	private		int		ste;	//当前体力
	private		int		n;		//当前战役可扫荡次数	
	private		List<RewardVo> rewardList;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public long getSliver() {
		return sliver;
	}
	public void setSliver(long sliver) {
		this.sliver = sliver;
	}

	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getSte() {
		return ste;
	}
	public void setSte(int ste) {
		this.ste = ste;
	}
	public List<RewardVo> getRewardList() {
		return rewardList;
	}
	public void setRewardList(List<RewardVo> rewardList) {
		this.rewardList = rewardList;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	
}