package com.shinian.vo;

import java.io.Serializable;

public class PlayerNewsTimeVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String uid;
		private String newsTime;
		
		
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getNewsTime() {
			return newsTime;
		}
		public void setNewsTime(String newsTime) {
			this.newsTime = newsTime;
		}
		
		
}