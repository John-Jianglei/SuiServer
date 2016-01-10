//一个比较通用的vo，供各个接口用
package com.shinian.vo;

import java.io.Serializable;

public class CommonVo extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
