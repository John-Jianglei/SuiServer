//write by syq
package com.shinian.vo;

import java.io.Serializable;
import java.util.List;

public class NpcFenjieVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String uid;
	private List<CommonVo> npcList;			//被分解武将的id

	private long sliver;	//玩家分解后的银两总数
	private int h5;			//五星万能将魂
	private int h4;
	private int h3;
	
	private int j;			//jinjiedan
	private int f;			//fivecolorstone
	private int t;			//tigertally
	private int e;			//eviltally
	private int p;			//ploughtally
	
	public void init(){
		
		sliver = 0;
		h5 = 0;
		h4 = 0;
		h3 = 0;
		j = 0;
		f = 0;
		t = 0;
		e = 0;
		p = 0;
		
	}
	
	public List<CommonVo> getNpcList() {
		return npcList;
	}
	public void setNpcList(List<CommonVo> npcList) {
		this.npcList = npcList;
	}
	public long getSliver() {
		return sliver;
	}
	public void setSliver(long sliver) {
		this.sliver = sliver;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public int getH5() {
		return h5;
	}
	public void setH5(int h5) {
		this.h5 = h5;
	}
	public int getH4() {
		return h4;
	}
	public void setH4(int h4) {
		this.h4 = h4;
	}
	public int getH3() {
		return h3;
	}
	public void setH3(int h3) {
		this.h3 = h3;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	
}
