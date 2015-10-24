package com.shinian.vo;

import java.io.Serializable;

public class UpdateWay extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;

	int way;
	String updateDesc;
	
	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public String getUpdateDesc() {
		return updateDesc;
	}

	public void setUpdateDesc(String updateDesc) {
		this.updateDesc = updateDesc;
	}

}
