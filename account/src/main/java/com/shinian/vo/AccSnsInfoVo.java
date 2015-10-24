package com.shinian.vo;

import java.io.Serializable;

public class AccSnsInfoVo extends BaseObject implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	
//	CREATE TABLE `acc_sns_info` (
//			  `uid` int(10) NOT NULL COMMENT '用户的ID',
//			  `sns_id` varchar(50) NOT NULL COMMENT '渠道用户的ID',
//			  `channel_id` int(10) NOT NULL COMMENT '渠道ID',
//			  `name` varchar(50) NOT NULL COMMENT '渠道用户的名字',
//			  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '绑定的时间',
//			  PRIMARY KEY (`uid`)
//			) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	private int uid;
	private String snsId;
	private int channelId;
	private String name;
	private String updateTime;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
	
}
