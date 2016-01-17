package com.shinian.vo;

import java.io.Serializable;

public class SyncTimeRespVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String token;
		private String currentTime;
		
		
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getCurrentTime() {
			return currentTime;
		}
		public void setCurrentTime(String currentTime) {
			this.currentTime = currentTime;
		}

}