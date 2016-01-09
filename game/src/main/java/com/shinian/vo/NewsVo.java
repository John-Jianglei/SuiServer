package com.shinian.vo;

import java.io.Serializable;

public class NewsVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private int id;
		private int category;
		private String title;
		private String uid;
		private String fromUid;
		private String fromName;
		private String content;
		private int annexCate;
		private int annexId;
		private int amount;
		private String updateTime;
		private int status;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCategory() {
			return category;
		}
		public void setCategory(int category) {
			this.category = category;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getFromUid() {
			return fromUid;
		}
		public void setFromUid(String fromUid) {
			this.fromUid = fromUid;
		}
		public String getFromName() {
			return fromName;
		}
		public void setFromName(String fromName) {
			this.fromName = fromName;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getAnnexCate() {
			return annexCate;
		}
		public void setAnnexCate(int annexCate) {
			this.annexCate = annexCate;
		}
		public int getAnnexId() {
			return annexId;
		}
		public void setAnnexId(int annexId) {
			this.annexId = annexId;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		
		
}