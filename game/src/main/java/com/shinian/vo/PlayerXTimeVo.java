package com.shinian.vo;

import java.io.Serializable;

public class PlayerXTimeVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String uid;
		private String lastTime;
		
		
		public String getLastTime() {
			return lastTime;
		}
		public void setLastTime(String lastTime) {
			this.lastTime = lastTime;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}

}