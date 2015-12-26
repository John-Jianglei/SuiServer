package com.shinian.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JingjiRedisVo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int 	id;
	private String 	title;		//在竞技场获得的称号
	private	int 	posTop;		//排名上限
	private int		posBot;		//排名下限
	private int		interval;	//竞技场中最近一名玩家与自己的排名间隔
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPosTop() {
		return posTop;
	}
	public void setPosTop(int posTop) {
		this.posTop = posTop;
	}
	public int getPosBot() {
		return posBot;
	}
	public void setPosBot(int posBot) {
		this.posBot = posBot;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}	
	
	public void fromList(List<String> list) {
		int idx = 0;
		
		this.id = Integer.parseInt(list.get(idx++));   
		this.title = list.get(idx++); 
		this.posTop = Integer.parseInt(list.get(idx++)); 
		this.posBot = Integer.parseInt(list.get(idx++)); 
		this.interval = Integer.parseInt(list.get(idx++)); 

	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
				
		map.put("id", 		            this.id + "");
		map.put("title",                this.title + "");
		map.put("posTop", 		    	this.posTop + "");
		map.put("posBot",              	this.posBot + "");
		map.put("interval", 		    this.interval + "");	

		return map;
	}
	
	public String[] getFieldNames() {
		return new String[] {"id","title","posTop","posBot","interval"};
	}
}
