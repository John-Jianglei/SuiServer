package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnexPackRedisVo extends BaseObject implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private int id;
		private String name;
		private int type1;
		private int comId1;
		private int amount1;
		private int type2;
		private int comId2;
		private int amount2;
		private int type3;
		private int comId3;
		private int amount3;
		private int type4;
		private int comId4;
		private int amount4;
		private int type5;
		private int comId5;
		private int amount5;
		private int type6;
		private int comId6;
		private int amount6;
		private String updateTime;
		private int status;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getType1() {
			return type1;
		}
		public void setType1(int type1) {
			this.type1 = type1;
		}
		public int getComId1() {
			return comId1;
		}
		public void setComId1(int comId1) {
			this.comId1 = comId1;
		}
		public int getAmount1() {
			return amount1;
		}
		public void setAmount1(int amount1) {
			this.amount1 = amount1;
		}
		public int getType2() {
			return type2;
		}
		public void setType2(int type2) {
			this.type2 = type2;
		}
		public int getComId2() {
			return comId2;
		}
		public void setComId2(int comId2) {
			this.comId2 = comId2;
		}
		public int getAmount2() {
			return amount2;
		}
		public void setAmount2(int amount2) {
			this.amount2 = amount2;
		}
		public int getType3() {
			return type3;
		}
		public void setType3(int type3) {
			this.type3 = type3;
		}
		public int getComId3() {
			return comId3;
		}
		public void setComId3(int comId3) {
			this.comId3 = comId3;
		}
		public int getAmount3() {
			return amount3;
		}
		public void setAmount3(int amount3) {
			this.amount3 = amount3;
		}
		public int getType4() {
			return type4;
		}
		public void setType4(int type4) {
			this.type4 = type4;
		}
		public int getComId4() {
			return comId4;
		}
		public void setComId4(int comId4) {
			this.comId4 = comId4;
		}
		public int getAmount4() {
			return amount4;
		}
		public void setAmount4(int amount4) {
			this.amount4 = amount4;
		}
		public int getType5() {
			return type5;
		}
		public void setType5(int type5) {
			this.type5 = type5;
		}
		public int getComId5() {
			return comId5;
		}
		public void setComId5(int comId5) {
			this.comId5 = comId5;
		}
		public int getAmount5() {
			return amount5;
		}
		public void setAmount5(int amount5) {
			this.amount5 = amount5;
		}
		public int getType6() {
			return type6;
		}
		public void setType6(int type6) {
			this.type6 = type6;
		}
		public int getComId6() {
			return comId6;
		}
		public void setComId6(int comId6) {
			this.comId6 = comId6;
		}
		public int getAmount6() {
			return amount6;
		}
		public void setAmount6(int amount6) {
			this.amount6 = amount6;
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
		
		public void fromList(List<String> list) {
			int idx = 0;
						
			this.id= Integer.parseInt(list.get(idx++));    
			this.name= list.get(idx++);    
			this.type1= Integer.parseInt(list.get(idx++));    
			this.comId1= Integer.parseInt(list.get(idx++));    
			this.amount1= Integer.parseInt(list.get(idx++));   
			this.type2= Integer.parseInt(list.get(idx++));    
			this.comId2= Integer.parseInt(list.get(idx++));    
			this.amount2= Integer.parseInt(list.get(idx++));   
			this.type3= Integer.parseInt(list.get(idx++));    
			this.comId3= Integer.parseInt(list.get(idx++));    
			this.amount3= Integer.parseInt(list.get(idx++));   
			this.type4= Integer.parseInt(list.get(idx++));    
			this.comId4= Integer.parseInt(list.get(idx++));    
			this.amount4= Integer.parseInt(list.get(idx++));   
			this.type5= Integer.parseInt(list.get(idx++));    
			this.comId5= Integer.parseInt(list.get(idx++));    
			this.amount5= Integer.parseInt(list.get(idx++));   
			this.type6= Integer.parseInt(list.get(idx++));    
			this.comId6= Integer.parseInt(list.get(idx++));    
			this.amount6= Integer.parseInt(list.get(idx++));   
			this.updateTime= list.get(idx++);   
			this.status= Integer.parseInt(list.get(idx++));  
		}
		
		
		public Map<String, String> toMap() {
			Map<String, String> map = new HashMap<String, String>();
					
			map.put("id", 			this.id + "");
			map.put("name", 		this.name + "");
			map.put("type1", 		this.type1 + "");
			map.put("comId1", 		this.comId1 + "");
			map.put("amount1", 		this.amount1 + "");
			map.put("type2", 		this.type2 + "");
			map.put("comId2", 		this.comId2 + "");
			map.put("amount2", 		this.amount2 + "");
			map.put("type3", 		this.type3 + "");
			map.put("comId3", 		this.comId3 + "");
			map.put("amount3", 		this.amount3 + "");
			map.put("type4", 		this.type4 + "");
			map.put("comId4", 		this.comId4 + "");
			map.put("amount4", 		this.amount4 + "");
			map.put("type5", 		this.type5 + "");
			map.put("comId5", 		this.comId5 + "");
			map.put("amount5", 		this.amount5 + "");
			map.put("type6", 		this.type6 + "");
			map.put("comId6", 		this.comId6 + "");
			map.put("amount6", 		this.amount6 + "");
			map.put("updateTime", 	this.updateTime + "");
			map.put("status", 		this.status + ""); 	

			return map;
		}
		
		public String[] getFieldNames() {
			return new String[] {"id", "name", "type1", "comId1", "amount1", "type2", "comId2", "amount2", "type3", "comId3", "amount3", "type4", "comId4", "amount4", "type5", "comId5", "amount5", "type6", "comId6", "amount6", "updateTime", "status"};
		}
		
}