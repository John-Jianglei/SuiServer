package com.shinian.vo;

import java.util.LinkedList;  
import java.util.Queue;  

public class ActionListVo {

	private Queue<ActionVo> actionList;
	
	public Queue<ActionVo> getToken() {
		return actionList;
	}
	public void setToken(Queue<ActionVo> actionList) {
		this.actionList = actionList;
	}
	
	public ActionListVo(){
		this.actionList = new LinkedList<ActionVo>();
	}
}
