package com.shinian.vo;

import java.io.Serializable;

public class AccPushVo extends BaseObject implements Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	DROP TABLE IF EXISTS `acc_push`;
//	CREATE TABLE `acc_push` (
//	  `uid` int(10) NOT NULL COMMENT '用户的ID',
//	  `channel_id` int(10) NOT NULL COMMENT '渠道ID',
//	  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新的时间',
//	  PRIMARY KEY (`uid`)
//	) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
	
	public int uid;
	private int channelId;
	private String updateTime;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getChannelId() {
		return channelId;
	}
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
