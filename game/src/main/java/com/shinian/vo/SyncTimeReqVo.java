package com.shinian.vo;

import java.io.Serializable;

public class SyncTimeReqVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String token;
		private String uid;
		
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
}