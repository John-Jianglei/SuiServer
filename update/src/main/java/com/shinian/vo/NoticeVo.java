package com.shinian.vo;

import java.io.Serializable;

public class NoticeVo extends BaseObject implements Serializable{
private static final long serialVersionUID = 1L;
	
	private int status;
	private String noticeDesc;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNoticeDesc() {
		return noticeDesc;
	}

	public void setNoticeDesc(String noticeDesc) {
		this.noticeDesc = noticeDesc;
	}
	
}
